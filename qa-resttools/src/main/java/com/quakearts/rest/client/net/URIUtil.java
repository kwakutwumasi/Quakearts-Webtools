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

import java.util.BitSet;
import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;

public class URIUtil {
	private static BitSet encodedInPath;
	private static final long L_DIGIT = lowMask('0', '9');
	private static final long H_DIGIT = 0L;

	private static final long L_HEX = L_DIGIT;
	private static final long H_HEX = highMask('A', 'F') | highMask('a', 'f');

	private static final long L_UPALPHA = 0L;
	private static final long H_UPALPHA = highMask('A', 'Z');

	private static final long L_LOWALPHA = 0L;
	private static final long H_LOWALPHA = highMask('a', 'z');

	private static final long L_ALPHA = L_LOWALPHA | L_UPALPHA;
	private static final long H_ALPHA = H_LOWALPHA | H_UPALPHA;

	private static final long L_ALPHANUM = L_DIGIT | L_ALPHA;
	private static final long H_ALPHANUM = H_DIGIT | H_ALPHA;

	private static final long L_MARK = lowMask("-_.!~*'()");
	private static final long H_MARK = highMask("-_.!~*'()");

	private static final long L_UNRESERVED = L_ALPHANUM | L_MARK;
	private static final long H_UNRESERVED = H_ALPHANUM | H_MARK;

	private static final long L_RESERVED = lowMask(";/?:@&=+$,[]");
	private static final long H_RESERVED = highMask(";/?:@&=+$,[]");

	private static final long L_ESCAPED = 1L;
	private static final long H_ESCAPED = 0L;

	private static final long L_DASH = lowMask("-");
	private static final long H_DASH = highMask("-");

	private static final long L_URIC = L_RESERVED | L_UNRESERVED | L_ESCAPED;
	private static final long H_URIC = H_RESERVED | H_UNRESERVED | H_ESCAPED;

	private static final long L_PCHAR = L_UNRESERVED | L_ESCAPED | lowMask(":@&=+$,");
	private static final long H_PCHAR = H_UNRESERVED | H_ESCAPED | highMask(":@&=+$,");

	private static final long L_PATH = L_PCHAR | lowMask(";/");
	private static final long H_PATH = H_PCHAR | highMask(";/");

	private static final long L_USERINFO = L_UNRESERVED | L_ESCAPED | lowMask(";:&=+$,");
	private static final long H_USERINFO = H_UNRESERVED | H_ESCAPED | highMask(";:&=+$,");

	private static final long L_REG_NAME = L_UNRESERVED | L_ESCAPED | lowMask("$,;:@&=+");
	private static final long H_REG_NAME = H_UNRESERVED | H_ESCAPED | highMask("$,;:@&=+");

	private static final long L_SERVER = L_USERINFO | L_ALPHANUM | L_DASH | lowMask(".:@[]");
	private static final long H_SERVER = H_USERINFO | H_ALPHANUM | H_DASH | highMask(".:@[]");

	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	static {
		encodedInPath = new BitSet(256);

		encodedInPath.set('=');
		encodedInPath.set(';');
		encodedInPath.set('?');
		encodedInPath.set('/');
		encodedInPath.set('#');
		encodedInPath.set(' ');
		encodedInPath.set('<');
		encodedInPath.set('>');
		encodedInPath.set('%');
		encodedInPath.set('"');
		encodedInPath.set('{');
		encodedInPath.set('}');
		encodedInPath.set('|');
		encodedInPath.set('\\');
		encodedInPath.set('^');
		encodedInPath.set('[');
		encodedInPath.set(']');
		encodedInPath.set('`');

		for (int i = 0; i < 32; i++)
			encodedInPath.set(i);
		encodedInPath.set(127);
	}

	private static long lowMask(char first, char last) {
		long m = 0;
		int f = Math.max(Math.min(first, 63), 0);
		int l = Math.max(Math.min(last, 63), 0);
		for (int i = f; i <= l; i++)
			m |= 1L << i;
		return m;
	}

	private static long lowMask(String chars) {
		int n = chars.length();
		long m = 0;
		for (int i = 0; i < n; i++) {
			char c = chars.charAt(i);
			if (c < 64)
				m |= (1L << c);
		}
		return m;
	}

	private static long highMask(char first, char last) {
		long m = 0;
		int f = Math.max(Math.min(first, 127), 64) - 64;
		int l = Math.max(Math.min(last, 127), 64) - 64;
		for (int i = f; i <= l; i++)
			m |= 1L << i;
		return m;
	}

	private static long highMask(String chars) {
		int n = chars.length();
		long m = 0;
		for (int i = 0; i < n; i++) {
			char c = chars.charAt(i);
			if ((c >= 64) && (c < 128))
				m |= (1L << (c - 64));
		}
		return m;
	}

	private URIUtil() {}
	
	public static URI toURI(URL url) {
		String protocol = url.getProtocol();
		String auth = url.getAuthority();
		String path = url.getPath();
		String query = url.getQuery();
		String ref = url.getRef();
		if (path != null && !(path.startsWith("/")))
			path = "/" + path;

		if (auth != null && auth.endsWith(":-1"))
			auth = auth.substring(0, auth.length() - 3);

		java.net.URI uri;
		try {
			uri = createURI(protocol, auth, path, query, ref);
		} catch (java.net.URISyntaxException e) {
			uri = null;
		}
		return uri;
	}

	private static URI createURI(String scheme, String authority, String path, String query, String fragment)
			throws URISyntaxException {
		String s = toString(scheme, null, authority, null, null, -1, path, query, fragment);
		checkPath(s, scheme, path);
		return new URI(s);
	}

	private static void checkPath(String s, String scheme, String path) throws URISyntaxException {
		if (scheme != null && (path != null) && ((path.length() > 0) && (path.charAt(0) != '/')))
			throw new URISyntaxException(s, "Relative path in absolute URI");
	}

	private static String toString(String scheme, String opaquePart, String authority, String userInfo, String host,
			int port, String path, String query, String fragment) {
		StringBuilder sb = new StringBuilder();
		if (scheme != null) {
			sb.append(scheme);
			sb.append(':');
		}
		appendSchemeSpecificPart(sb, opaquePart, authority, userInfo, host, port, path, query);
		appendFragment(sb, fragment);
		return sb.toString();
	}

	private static void appendSchemeSpecificPart(StringBuilder sb, String opaquePart, String authority, String userInfo,
			String host, int port, String path, String query) {
		if (opaquePart != null) {
			/*
			 * check if SSP begins with an IPv6 address because we must not quote a literal
			 * IPv6 address
			 */
			if (opaquePart.startsWith("//[")) {
				int end = opaquePart.indexOf("]");
				if (end != -1 && opaquePart.indexOf(":") != -1) {
					String doquote;
					String dontquote;
					if (end == opaquePart.length()) {
						dontquote = opaquePart;
						doquote = "";
					} else {
						dontquote = opaquePart.substring(0, end + 1);
						doquote = opaquePart.substring(end + 1);
					}
					sb.append(dontquote);
					sb.append(quote(doquote, L_URIC, H_URIC));
				}
			} else {
				sb.append(quote(opaquePart, L_URIC, H_URIC));
			}
		} else {
			appendAuthority(sb, authority, userInfo, host, port);
			if (path != null)
				sb.append(quote(path, L_PATH, H_PATH));
			if (query != null) {
				sb.append('?');
				sb.append(quote(query, L_URIC, H_URIC));
			}
		}
	}
	
	private static String quote(String s, long lowMask, long highMask) {
		StringBuilder sb = null;
		boolean allowNonASCII = ((lowMask & L_ESCAPED) != 0);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '\u0080') {
				if (!match(c, lowMask, highMask) && !isEscaped(s, i)) {
					if (sb == null) {
						sb = new StringBuilder();
						sb.append(s.substring(0, i));
					}
					appendEscape(sb, (byte) c);
				} else {
					if (sb != null)
						sb.append(c);
				}
			} else if (allowNonASCII && (Character.isSpaceChar(c) || Character.isISOControl(c))) {
				if (sb == null) {
					sb = new StringBuilder();
					sb.append(s.substring(0, i));
				}
				appendEncoded(sb, c);
			} else {
				if (sb != null)
					sb.append(c);
			}
		}
		return (sb == null) ? s : sb.toString();
	}
	
	private static boolean match(char c, long lowMask, long highMask) {
		if (c < 64)
			return ((1L << c) & lowMask) != 0;
		if (c < 128)
			return ((1L << (c - 64)) & highMask) != 0;
		return false;
	}

	private static boolean isEscaped(String s, int pos) {
		if (s == null || (s.length() <= (pos + 2)))
			return false;

		return s.charAt(pos) == '%' && match(s.charAt(pos + 1), L_HEX, H_HEX) && match(s.charAt(pos + 2), L_HEX, H_HEX);
	}

	private static void appendEscape(StringBuilder sb, byte b) {
		sb.append('%');
		sb.append(hexDigits[(b >> 4) & 0x0f]);
		sb.append(hexDigits[(b >> 0) & 0x0f]);
	}

	private static void appendEncoded(StringBuilder sb, char c) {
		ByteBuffer bb = null;
		try {
			bb = ThreadLocalCoders.encoderFor("UTF-8").encode(CharBuffer.wrap("" + c));
		} catch (CharacterCodingException x) {
			assert false;
		}
		while (bb.hasRemaining()) {
			int b = bb.get() & 0xff;
			if (b >= 0x80)
				appendEscape(sb, (byte) b);
			else
				sb.append((char) b);
		}
	}

	private static void appendAuthority(StringBuilder sb, String authority, String userInfo, String host, int port) {
		if (host != null) {
			sb.append("//");
			if (userInfo != null) {
				sb.append(quote(userInfo, L_USERINFO, H_USERINFO));
				sb.append('@');
			}
			boolean needBrackets = ((host.indexOf(':') >= 0) && !host.startsWith("[") && !host.endsWith("]"));
			if (needBrackets)
				sb.append('[');
			sb.append(host);
			if (needBrackets)
				sb.append(']');
			if (port != -1) {
				sb.append(':');
				sb.append(port);
			}
		} else if (authority != null) {
			sb.append("//");
			if (authority.startsWith("[")) {
				int end = authority.indexOf("]");
				if (end != -1 && authority.indexOf(":") != -1) {
					String doquote;
					String dontquote;
					if (end == authority.length()) {
						dontquote = authority;
						doquote = "";
					} else {
						dontquote = authority.substring(0, end + 1);
						doquote = authority.substring(end + 1);
					}
					sb.append(dontquote);
					sb.append(quote(doquote, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
				}
			} else {
				sb.append(quote(authority, L_REG_NAME | L_SERVER, H_REG_NAME | H_SERVER));
			}
		}
	}

	private static void appendFragment(StringBuilder sb, String fragment) {
		if (fragment != null) {
			sb.append('#');
			sb.append(quote(fragment, L_URIC, H_URIC));
		}
	}
}
