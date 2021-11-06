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

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.HashMap;

public final class HttpCookie {
	private boolean httpOnly;
	private final String header;
	private static final String SET_COOKIE = "set-cookie:";
	private static final String SET_COOKIE2 = "set-cookie2:";

	private HttpCookie(String header) {
		this.header = header;
	}

	protected String getHeader() {
		return header;
	}

	protected static List<HttpCookie> parse(String header) {
		int version = guessCookieVersion(header);

		if (startsWithIgnoreCase(header, SET_COOKIE2)) {
			header = header.substring(SET_COOKIE2.length());
		} else if (startsWithIgnoreCase(header, SET_COOKIE)) {
			header = header.substring(SET_COOKIE.length());
		}

		List<HttpCookie> cookies = new ArrayList<>();
		if (version == 0) {
			HttpCookie cookie = parseInternal(header);
			cookies.add(cookie);
		} else {
			List<String> cookieStrings = splitMultiCookies(header);
			for (String cookieStr : cookieStrings) {
				HttpCookie cookie = parseInternal(cookieStr);
				cookies.add(cookie);
			}
		}

		return cookies;
	}

	private static int guessCookieVersion(String header) {
		int version = 0;

		header = header.toLowerCase();
		if (header.indexOf("expires=") != -1) {
			version = 0;
		} else if (header.indexOf("version=") != -1) {
			version = 1;
		} else if (header.indexOf("max-age") != -1) {
			version = 1;
		} else if (startsWithIgnoreCase(header, SET_COOKIE2)) {
			version = 1;
		}

		return version;
	}
	
	private static boolean startsWithIgnoreCase(String s, String start) {
		if (s == null || start == null)
			return false;

		return (s.length() >= start.length() && start.equalsIgnoreCase(s.substring(0, start.length())));
	}

	protected boolean isHttpOnly() {
		return httpOnly;
	}

	protected void setHttpOnly(boolean httpOnly) {
		this.httpOnly = httpOnly;
	}

	private static HttpCookie parseInternal(String header) {
		HttpCookie cookie = null;
		String namevaluePair = null;

		StringTokenizer tokenizer = new StringTokenizer(header, ";");

		try {
			namevaluePair = tokenizer.nextToken();
			int index = namevaluePair.indexOf('=');
			if (index != -1) {
				cookie = new HttpCookie(header);
			} else {
				throw new IllegalArgumentException("Invalid cookie name-value pair");
			}
		} catch (NoSuchElementException ignored) {
			throw new IllegalArgumentException("Empty cookie header string");
		}

		while (tokenizer.hasMoreTokens()) {
			namevaluePair = tokenizer.nextToken();
			int index = namevaluePair.indexOf('=');
			String name;
			String value;
			if (index != -1) {
				name = namevaluePair.substring(0, index).trim();
				value = namevaluePair.substring(index + 1).trim();
			} else {
				name = namevaluePair.trim();
				value = null;
			}

			assignAttribute(cookie, name, value);
		}

		return cookie;
	}

	static interface CookieAttributeAssignor {
		public void assign(HttpCookie cookie, String attrName, String attrValue);
	}

	static final Map<String, CookieAttributeAssignor> assignors = new HashMap<>();
	static {
		assignors.put("httponly", (cookie, attrName, attrValue) -> cookie.setHttpOnly(true));
	}

	private static void assignAttribute(HttpCookie cookie, String attrName, String attrValue) {
		attrValue = stripOffSurroundingQuote(attrValue);

		CookieAttributeAssignor assignor = assignors.get(attrName.toLowerCase());
		if (assignor != null) {
			assignor.assign(cookie, attrName, attrValue);
		} else {
			// Do nothing
		}
	}
	
	private static String stripOffSurroundingQuote(String str) {
		if (str != null && str.length() > 2 && str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"') {
			return str.substring(1, str.length() - 1);
		}
		if (str != null && str.length() > 2 && str.charAt(0) == '\'' && str.charAt(str.length() - 1) == '\'') {
			return str.substring(1, str.length() - 1);
		}
		return str;
	}

	private static List<String> splitMultiCookies(String header) {
		List<String> cookies = new java.util.ArrayList<>();
		int quoteCount = 0;
		int p;
		int q;

		for (p = 0, q = 0; p < header.length(); p++) {
			char c = header.charAt(p);
			if (c == '"')
				quoteCount++;
			if (c == ',' && (quoteCount % 2 == 0)) {
				cookies.add(header.substring(q, p));
				q = p + 1;
			}
		}

		cookies.add(header.substring(q));

		return cookies;
	}
}
