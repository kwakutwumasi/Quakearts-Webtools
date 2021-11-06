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

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.PasswordAuthentication;
import java.util.Base64;

import com.quakearts.rest.client.net.HeaderParser;

class BasicAuthentication extends AuthenticationInfo {

	private static final String BASIC = "Basic ";

	private static final long serialVersionUID = 100L;

	String auth;

	public BasicAuthentication(boolean isProxy, String host, int port, String realm, PasswordAuthentication passwordAuthentication) {
		super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION, AuthScheme.BASIC, host, port, realm);
		String plain = passwordAuthentication.getUserName() + ":";
		byte[] nameBytes = plain.getBytes(StandardCharsets.ISO_8859_1);

		char[] passwordChars = passwordAuthentication.getPassword();
		byte[] passwordBytes = new byte[passwordChars.length];
		for (int i = 0; i < passwordChars.length; i++)
			passwordBytes[i] = (byte) passwordChars[i];

		byte[] concat = new byte[nameBytes.length + passwordBytes.length];
		System.arraycopy(nameBytes, 0, concat, 0, nameBytes.length);
		System.arraycopy(passwordBytes, 0, concat, nameBytes.length, passwordBytes.length);
		this.auth = BASIC + Base64.getEncoder().encodeToString(concat);
		this.passwordAuthentication = passwordAuthentication;
	}

	public BasicAuthentication(boolean isProxy, URL url, String realm, PasswordAuthentication pw) {
		super(isProxy ? PROXY_AUTHENTICATION : SERVER_AUTHENTICATION, AuthScheme.BASIC, url, realm);
		String plain = pw.getUserName() + ":";
		byte[] nameBytes = plain.getBytes(StandardCharsets.ISO_8859_1);

		char[] passwd = pw.getPassword();
		byte[] passwdBytes = new byte[passwd.length];
		for (int i = 0; i < passwd.length; i++)
			passwdBytes[i] = (byte) passwd[i];

		byte[] concat = new byte[nameBytes.length + passwdBytes.length];
		System.arraycopy(nameBytes, 0, concat, 0, nameBytes.length);
		System.arraycopy(passwdBytes, 0, concat, nameBytes.length, passwdBytes.length);
		this.auth = BASIC + Base64.getEncoder().encodeToString(concat);
		this.passwordAuthentication = pw;
	}

	public BasicAuthentication(AuthenticationInfo toCopy) {
		super(toCopy);
	}

	@Override
	public boolean supportsPreemptiveAuthorization() {
		return true;
	}

	@Override
	public boolean setHeaders(HttpURLConnectionImpl conn, HeaderParser p, String raw) {
		conn.setAuthenticationProperty(getHeaderName(), getHeaderValue(null, null));
		return true;
	}

	@Override
	public String getHeaderValue(URL url, String method) {
		return auth;
	}

	@Override
	public boolean isAuthorizationStale(String header) {
		return false;
	}

	static String getRootPath(String npath, String opath) {
		int index = 0;
		int toindex;

		try {
			npath = new URI(npath).normalize().getPath();
			opath = new URI(opath).normalize().getPath();
		} catch (URISyntaxException e) {
			// Do nothing
		}

		while (index < opath.length()) {
			toindex = opath.indexOf('/', index + 1);
			if (toindex != -1 && opath.regionMatches(0, npath, 0, toindex + 1))
				index = toindex;
			else
				return opath.substring(0, index + 1);
		}

		return npath;
	}
}
