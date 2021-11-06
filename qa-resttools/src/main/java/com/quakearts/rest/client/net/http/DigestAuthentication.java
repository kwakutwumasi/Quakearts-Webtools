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
package com.quakearts.rest.client.net.http;

import static com.quakearts.rest.client.net.http.HttpURLConnectionImpl.HTTP_CONNECT;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.ProtocolException;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.quakearts.rest.client.net.HeaderParser;
import com.quakearts.rest.client.net.NetProperties;

import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class DigestAuthentication extends AuthenticationInfo {

	private static final long serialVersionUID = 100L;

	private String authMethod;

	private static final String COMPATPROPNAME = "com.quakearts.net.http.auth.digest.quoteParameters";

	private static final boolean DELIMCOMPATFLAG;

	static {
		Boolean b = NetProperties.getBoolean(COMPATPROPNAME);
		DELIMCOMPATFLAG = b.booleanValue();
	}

	static class Parameters implements java.io.Serializable {
		private static final long serialVersionUID = -3584543755194526252L;

		private boolean serverQop;
		private String opaque;
		private String cnonce;
		private String nonce;
		private String algorithm;
		private int nCcount = 0;

		private String cachedHA1;

		private boolean redoCachedHA1 = true;
		private static final int CNONCE_REPEAT = 5;
		private static final int CNONCE_LEN = 40;
		private static Random random;

		static {
			random = new Random();
		}

		Parameters() {
			serverQop = false;
			opaque = null;
			algorithm = null;
			cachedHA1 = null;
			nonce = null;
			setNewCnonce();
		}

		boolean authQop() {
			return serverQop;
		}

		synchronized void incrementNC() {
			nCcount++;
		}

		synchronized int getNCCount() {
			return nCcount;
		}

		int cnonceCount = 0;

		synchronized String getCnonce() {
			if (cnonceCount >= CNONCE_REPEAT) {
				setNewCnonce();
			}
			cnonceCount++;
			return cnonce;
		}

		synchronized void setNewCnonce() {
			byte[] bb = new byte[CNONCE_LEN / 2];
			char[] cc = new char[CNONCE_LEN];
			random.nextBytes(bb);
			for (int i = 0; i < (CNONCE_LEN / 2); i++) {
				int x = bb[i] + 128;
				cc[i * 2] = (char) ('A' + x / 16);
				cc[i * 2 + 1] = (char) ('A' + x % 16);
			}
			cnonce = new String(cc, 0, CNONCE_LEN);
			cnonceCount = 0;
			redoCachedHA1 = true;
		}

		synchronized void setQop(String qop) {
			if (qop != null) {
				StringTokenizer st = new StringTokenizer(qop, " ");
				while (st.hasMoreTokens()) {
					if (st.nextToken().equalsIgnoreCase("auth")) {
						serverQop = true;
						return;
					}
				}
			}
			serverQop = false;
		}

		synchronized String getOpaque() {
			return opaque;
		}

		synchronized void setOpaque(String s) {
			opaque = s;
		}

		synchronized String getNonce() {
			return nonce;
		}

		synchronized void setNonce(String s) {
			if (!s.equals(nonce)) {
				nonce = s;
				nCcount = 0;
				redoCachedHA1 = true;
			}
		}

		synchronized String getCachedHA1() {
			if (redoCachedHA1) {
				return null;
			} else {
				return cachedHA1;
			}
		}

		synchronized void setCachedHA1(String s) {
			cachedHA1 = s;
			redoCachedHA1 = false;
		}

		synchronized String getAlgorithm() {
			return algorithm;
		}

		synchronized void setAlgorithm(String s) {
			algorithm = s;
		}
	}

	Parameters params;

	public DigestAuthentication(boolean isProxy, URL url, String realm, String authMethod, PasswordAuthentication pw,
			Parameters params) {
		super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION, AuthScheme.DIGEST, url, realm);
		this.authMethod = authMethod;
		this.passwordAuthentication = pw;
		this.params = params;
	}

	public DigestAuthentication(boolean isProxy, String host, int port, String realm, String authMethod,
			PasswordAuthentication pw, Parameters params) {
		super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION, AuthScheme.DIGEST, host, port, realm);
		this.authMethod = authMethod;
		this.passwordAuthentication = pw;
		this.params = params;
	}

	@Override
	public boolean supportsPreemptiveAuthorization() {
		return true;
	}

	@Override
	public String getHeaderValue(URL url, String method) {
		return getHeaderValueImpl(url.getFile(), method);
	}

	String getHeaderValue(String requestURI, String method) {
		return getHeaderValueImpl(requestURI, method);
	}

	@Override
	public boolean isAuthorizationStale(String header) {
		HeaderParser p = new HeaderParser(header);
		String s = p.findValue("stale");
		if (s == null || !s.equals("true"))
			return false;
		String newNonce = p.findValue("nonce");
		if (newNonce == null || "".equals(newNonce)) {
			return false;
		}
		params.setNonce(newNonce);
		return true;
	}

	@Override
	public boolean setHeaders(HttpURLConnectionImpl conn, HeaderParser p, String raw) {
		params.setNonce(p.findValue("nonce"));
		params.setOpaque(p.findValue("opaque"));
		params.setQop(p.findValue("qop"));

		String uri = "";
		String method;
		if (type == PROXY_AUTHENTICATION && conn.tunnelState() == HttpURLConnectionImpl.TunnelState.SETUP) {
			uri = HttpURLConnectionImpl.connectRequestURI(conn.getURL());
			method = HTTP_CONNECT;
		} else {
			try {
				uri = conn.getRequestURI();
			} catch (IOException e) {
				//Do nothing
			}
			method = conn.getMethod();
		}

		if (params.nonce == null || authMethod == null || passwordAuthentication == null || realm == null) {
			return false;
		}
		if (authMethod.length() >= 1) {
			authMethod = Character.toUpperCase(authMethod.charAt(0)) + authMethod.substring(1).toLowerCase();
		}
		String algorithm = p.findValue("algorithm");
		if (algorithm == null || "".equals(algorithm)) {
			algorithm = "MD5";
		}
		params.setAlgorithm(algorithm);

		if (params.authQop()) {
			params.setNewCnonce();
		}

		String value = getHeaderValueImpl(uri, method);
		if (value != null) {
			conn.setAuthenticationProperty(getHeaderName(), value);
			return true;
		} else {
			return false;
		}
	}

	private String getHeaderValueImpl(String uri, String method) {
		String response;
		char[] passwordChars = passwordAuthentication.getPassword();
		boolean qop = params.authQop();
		String opaque = params.getOpaque();
		String cnonce = params.getCnonce();
		String nonce = params.getNonce();
		String algorithm = params.getAlgorithm();
		params.incrementNC();
		int nccount = params.getNCCount();
		String ncstring = null;

		if (nccount != -1) {
			ncstring = Integer.toHexString(nccount).toLowerCase();
			int len = ncstring.length();
			if (len < 8)
				ncstring = zeroPad[len] + ncstring;
		}

		try {
			response = computeDigest(true, passwordAuthentication.getUserName(), passwordChars, realm, method, uri, nonce, cnonce, ncstring);
		} catch (NoSuchAlgorithmException ex) {
			return null;
		}

		String ncfield = "\"";
		if (qop) {
			ncfield = "\", nc=" + ncstring;
		}

		String algoS;
		String qopS;

		if (DELIMCOMPATFLAG) {
			algoS = ", algorithm=\"" + algorithm + "\"";
			qopS = ", qop=\"auth\"";
		} else {
			algoS = ", algorithm=" + algorithm;
			qopS = ", qop=auth";
		}

		String value = authMethod + " username=\"" + passwordAuthentication.getUserName() + "\", realm=\"" + realm + "\", nonce=\"" + nonce
				+ ncfield + ", uri=\"" + uri + "\", response=\"" + response + "\"" + algoS;
		if (opaque != null) {
			value += ", opaque=\"" + opaque + "\"";
		}
		
		if (cnonce != null) {
			value += ", cnonce=\"" + cnonce + "\"";
		}
		
		if (qop) {
			value += qopS;
		}
		
		return value;
	}

	public void checkResponse(String header, String method, URL url) throws IOException {
		checkResponse(header, method, url.getFile());
	}

	public void checkResponse(String header, String method, String uri) throws IOException {
		char[] passwordChars = passwordAuthentication.getPassword();
		String username = passwordAuthentication.getUserName();
		String cnonce = params.cnonce;
		String nonce = params.getNonce();
		int nccount = params.getNCCount();
		String ncstring = null;

		if (header == null) {
			throw new ProtocolException("No authentication information in response");
		}

		if (nccount != -1) {
			ncstring = Integer.toHexString(nccount).toUpperCase();
			int len = ncstring.length();
			if (len < 8)
				ncstring = zeroPad[len] + ncstring;
		}

		try {
			String expected = computeDigest(false, username, passwordChars, realm, method, uri, nonce, cnonce, ncstring);
			HeaderParser headerParser = new HeaderParser(header);
			String rspauth = headerParser.findValue("rspauth");
			if (rspauth == null) {
				throw new ProtocolException("No digest in response");
			}

			if (!rspauth.equals(expected)) {
				throw new ProtocolException("Response digest invalid");
			}

			String nextnonce = headerParser.findValue("nextnonce");
			if (nextnonce != null && !"".equals(nextnonce)) {
				params.setNonce(nextnonce);
			}

		} catch (NoSuchAlgorithmException ex) {
			throw new ProtocolException("Unsupported algorithm in response");
		}
	}

	private String computeDigest(boolean isRequest, String userName, char[] password, String realm, String connMethod,
			String requestURI, String nonceString, String cnonce, String ncValue) throws NoSuchAlgorithmException {

		String a1;
		String hashA1;
		String algorithm = params.getAlgorithm();
		boolean md5sess = algorithm.equalsIgnoreCase("MD5-sess");

		MessageDigest md = MessageDigest.getInstance(md5sess ? "MD5" : algorithm);

		if (md5sess) {
			if ((hashA1 = params.getCachedHA1()) == null) {
				String s = userName + ":" + realm + ":";
				String s1 = encode(s, password, md);
				a1 = s1 + ":" + nonceString + ":" + cnonce;
				hashA1 = encode(a1, null, md);
				params.setCachedHA1(hashA1);
			}
		} else {
			a1 = userName + ":" + realm + ":";
			hashA1 = encode(a1, password, md);
		}

		String a2;
		if (isRequest) {
			a2 = connMethod + ":" + requestURI;
		} else {
			a2 = ":" + requestURI;
		}
		String hashA2 = encode(a2, null, md);
		String combo;
		String finalHash;

		if (params.authQop()) {
			combo = hashA1 + ":" + nonceString + ":" + ncValue + ":" + cnonce + ":auth:" + hashA2;

		} else {
			combo = hashA1 + ":" + nonceString + ":" + hashA2;
		}
		finalHash = encode(combo, null, md);
		return finalHash;
	}

	private static final char[] charArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	private static final String[] zeroPad = {
			"00000000", "0000000", "000000", "00000", "0000", "000", "00", "0" };

	private String encode(String src, char[] passwd, MessageDigest md) {
		md.update(src.getBytes(StandardCharsets.ISO_8859_1));
		if (passwd != null) {
			byte[] passwdBytes = new byte[passwd.length];
			for (int i = 0; i < passwd.length; i++)
				passwdBytes[i] = (byte) passwd[i];
			md.update(passwdBytes);
			Arrays.fill(passwdBytes, (byte) 0x00);
		}
		byte[] digest = md.digest();

		StringBuilder res = new StringBuilder(digest.length * 2);
		for (int i = 0; i < digest.length; i++) {
			int hashchar = ((digest[i] >>> 4) & 0xf);
			res.append(charArray[hashchar]);
			hashchar = (digest[i] & 0xf);
			res.append(charArray[hashchar]);
		}
		return res.toString();
	}
}
