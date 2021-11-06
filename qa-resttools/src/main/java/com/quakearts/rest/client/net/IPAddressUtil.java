/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net;

import java.net.URL;

import com.quakearts.rest.client.exception.InvalidIPAddressException;

public class IPAddressUtil {
	private static final int INADDR4SZ = 4;
	private static final int INADDR16SZ = 16;
	private static final int INT16SZ = 2;
	private static final long L_IPV6_DELIMS = 0x0L;
	private static final long H_IPV6_DELIMS = 0x28000000L;
	private static final long L_AUTH_DELIMS = 0x400000000000000L;
	private static final long H_AUTH_DELIMS = 0x28000001L;
	private static final long L_COLON = 0x400000000000000L;
	private static final long H_COLON = 0x0L;
	private static final long L_NON_PRINTABLE = 0xffffffffL;
	private static final long H_NON_PRINTABLE = 0x8000000000000000L;
	private static final long L_EXCLUDE = 0x84008008ffffffffL;
	private static final long H_EXCLUDE = 0x8000000038000001L;

	private IPAddressUtil() {}

	public static boolean isIPv4LiteralAddress(String src) {
		try {
			textToNumericFormatV4(src);
		} catch (InvalidIPAddressException e) {
			return false;
		}
		return true;
	}

	private static byte[] textToNumericFormatV4(String src) 
			throws InvalidIPAddressException {
		byte[] result = new byte[INADDR4SZ];

		long tmpValue = 0;
		int currByte = 0;
		boolean newOctet = true;

		int len = src.length();
		if (len == 0 || len > 15) {
			throw new InvalidIPAddressException();
		}

		for (int i = 0; i < len; i++) {
			char c = src.charAt(i);
			if (c == '.') {
				if (newOctet || tmpValue < 0 || tmpValue > 0xff || currByte == 3) {
					throw new InvalidIPAddressException();
				}
				result[currByte++] = (byte) (tmpValue & 0xff);
				tmpValue = 0;
				newOctet = true;
			} else {
				int digit = Character.digit(c, 10);
				if (digit < 0) {
					throw new InvalidIPAddressException();
				}
				tmpValue *= 10;
				tmpValue += digit;
				newOctet = false;
			}
		}
		
		if (newOctet || tmpValue < 0 || tmpValue >= (1L << ((4 - currByte) * 8))) {
			throw new InvalidIPAddressException();
		}
		
		return writeToResult(result, tmpValue, currByte);
	}

	private static byte[] writeToResult(byte[] result, long tmpValue, int currByte) {
		result[0] = (byte) ((tmpValue >> 24) & 0xff);
		if(currByte >= 1)
			result[1] = (byte) ((tmpValue >> 16) & 0xff);
		if(currByte >= 2)
			result[2] = (byte) ((tmpValue >> 8) & 0xff);
		if(currByte >= 3)
			result[3] = (byte) (tmpValue & 0xff);

		return result;
	}
	
	public static boolean isIPv6LiteralAddress(String src) {
		try {
			textToNumericFormatV6(src);
		} catch (InvalidIPAddressException e) {
			return false;
		}
		
		return true;
	}

	private static byte[] textToNumericFormatV6(String src) throws InvalidIPAddressException {
		// Shortest valid string is "::", hence at least 2 chars
		if (src.length() < 2) {
			throw new InvalidIPAddressException();
		}

		int colonp;
		char ch;
		boolean sawxDigit;
		int val;
		char[] srcb = src.toCharArray();
		byte[] dst = new byte[INADDR16SZ];

		int srcbLength = srcb.length;
		int pc = src.indexOf("%");
		if (pc == srcbLength - 1) {
			throw new InvalidIPAddressException();
		}

		if (pc != -1) {
			srcbLength = pc;
		}

		colonp = -1;
		int i = 0;
		int j = 0;
		/* Leading :: requires some special handling. */
		if (srcb[i] == ':' && srcb[++i] != ':')
			throw new InvalidIPAddressException();
		int curtok = i;
		sawxDigit = false;
		val = 0;
		while (i < srcbLength) {
			ch = srcb[i++];
			int chval = Character.digit(ch, 16);
			if (chval != -1) {
				val <<= 4;
				val |= chval;
				if (val > 0xffff)
					throw new InvalidIPAddressException();
				sawxDigit = true;
				continue;
			}
			if (ch == ':') {
				curtok = i;
				if (!sawxDigit) {
					if (colonp != -1)
						throw new InvalidIPAddressException();
					colonp = j;
					continue;
				} else if (i == srcbLength) {
					throw new InvalidIPAddressException();
				}
				if (j + INT16SZ > INADDR16SZ)
					throw new InvalidIPAddressException();
				dst[j++] = (byte) ((val >> 8) & 0xff);
				dst[j++] = (byte) (val & 0xff);
				sawxDigit = false;
				val = 0;
				continue;
			}
			if (ch == '.' && ((j + INADDR4SZ) <= INADDR16SZ)) {
				String ia4 = src.substring(curtok, srcbLength);

				int dotCount = 0;
				int index = 0;
				while ((index = ia4.indexOf('.', index)) != -1) {
					dotCount++;
					index++;
				}
				if (dotCount != 3) {
					throw new InvalidIPAddressException();
				}
				byte[] v4addr = textToNumericFormatV4(ia4);
				if (v4addr == null) {
					throw new InvalidIPAddressException();
				}
				for (int k = 0; k < INADDR4SZ; k++) {
					dst[j++] = v4addr[k];
				}
				sawxDigit = false;
				break;
			}
			throw new InvalidIPAddressException();
		}
		
		if (sawxDigit) {
			if (j + INT16SZ > INADDR16SZ)
				throw new InvalidIPAddressException();
			dst[j++] = (byte) ((val >> 8) & 0xff);
			dst[j++] = (byte) (val & 0xff);
		}

		if (colonp != -1) {
			int n = j - colonp;

			if (j == INADDR16SZ)
				throw new InvalidIPAddressException();
			for (i = 1; i <= n; i++) {
				dst[INADDR16SZ - i] = dst[colonp + n - i];
				dst[colonp + n - i] = 0;
			}
			j = INADDR16SZ;
		}
		if (j != INADDR16SZ)
			throw new InvalidIPAddressException();
		byte[] newdst = convertFromIPv4MappedAddress(dst);
		if (newdst != null) {
			return newdst;
		} else {
			return dst;
		}
	}
	
	private static byte[] convertFromIPv4MappedAddress(byte[] addr) {
		if (isIPv4MappedAddress(addr)) {
			byte[] newAddr = new byte[INADDR4SZ];
			System.arraycopy(addr, 12, newAddr, 0, INADDR4SZ);
			return newAddr;
		}
		return null;
	}

	private static boolean isIPv4MappedAddress(byte[] addr) {
		if (addr.length < INADDR16SZ) {
			return false;
		}
		return ((addr[0] == 0x00) && (addr[1] == 0x00) && (addr[2] == 0x00) && (addr[3] == 0x00) && (addr[4] == 0x00)
				&& (addr[5] == 0x00) && (addr[6] == 0x00) && (addr[7] == 0x00) && (addr[8] == 0x00) && (addr[9] == 0x00)
				&& (addr[10] == (byte) 0xff) && (addr[11] == (byte) 0xff));
	}

	private static int scan(String s, long lowMask, long highMask) {
		int i = -1;
		int len;
		if (s == null || (len = s.length()) == 0)
			return -1;
		boolean match = false;
		while (++i < len && !(match = match(s.charAt(i), lowMask, highMask)))
			;
		if (match)
			return i;
		return -1;
	}

	private static boolean match(char c, long lowMask, long highMask) {
		if (c < 64)
			return ((1L << c) & lowMask) != 0;
		if (c < 128)
			return ((1L << (c - 64)) & highMask) != 0;
		return false;
	}

	private static String describeChar(char c) {
		if (c < 32 || c == 127) {
			if (c == '\n')
				return "LF";
			if (c == '\r')
				return "CR";
			return "control char (code=" + (int) c + ")";
		}
		if (c == '\\')
			return "'\\'";
		return "'" + c + "'";
	}

	private static String checkUserInfo(String str) {
		if(str != null) {
			int index = scan(str, L_EXCLUDE & ~L_COLON, H_EXCLUDE & ~H_COLON);
			if (index >= 0) {
				return "Illegal character found in user-info: " + describeChar(str.charAt(index));
			}
		}
		return null;
	}

	private static String checkHost(String str) {
		int index;
		if (str.startsWith("[") && str.endsWith("]")) {
			str = str.substring(1, str.length() - 1);
			if (isIPv6LiteralAddress(str)) {
				index = str.indexOf('%');
				if (index >= 0) {
					index = scan(str = str.substring(index), L_NON_PRINTABLE | L_IPV6_DELIMS,
							H_NON_PRINTABLE | H_IPV6_DELIMS);
					if (index >= 0) {
						return "Illegal character found in IPv6 scoped address: " + (str != null? describeChar(str.charAt(index)) : "");
					}
				}
				return null;
			}
			return "Unrecognized IPv6 address format";
		} else {
			index = scan(str, L_EXCLUDE, H_EXCLUDE);
			if (index >= 0) {
				return "Illegal character found in host: " + describeChar(str.charAt(index));
			}
		}
		return null;
	}

	private static String checkAuth(String str) {
		if(str != null) {
			int index = scan(str, L_EXCLUDE & ~L_AUTH_DELIMS, H_EXCLUDE & ~H_AUTH_DELIMS);
			if (index >= 0) {
				return "Illegal character found in authority: " + describeChar(str.charAt(index));
			}
		}
		return null;
	}

	public static String checkAuthority(URL url) {
		String s; 
		String u;
		String h;
		if (url == null)
			return null;
		u = url.getUserInfo();
		if ((s = checkUserInfo(u)) != null) {
			return s;
		}
		
		h = url.getHost();
		s = checkHost(h);
		
		if (s != null) {
			return s;
		}
		
		if (u == null) {
			return checkAuth(url.getAuthority());
		}
		
		return null;
	}
}
