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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;

import com.quakearts.rest.client.net.HeaderParser;

public abstract class AuthenticationInfo extends AuthCacheValue implements Cloneable {

	static final long serialVersionUID = -2588378268010453259L;

	public static final char SERVER_AUTHENTICATION = 's';
	public static final char PROXY_AUTHENTICATION = 'p';

	static final boolean SERIALIZEAUTH;
	static {
		SERIALIZEAUTH = Boolean.getBoolean("com.quakearts.net.http.auth.serializeRequests");
	}

	protected transient PasswordAuthentication passwordAuthentication;

	public PasswordAuthentication credentials() {
		return passwordAuthentication;
	}

	public AuthCacheValue.Type getAuthType() {
		return type == SERVER_AUTHENTICATION ? AuthCacheValue.Type.SERVER : AuthCacheValue.Type.PROXY;
	}

	AuthScheme getAuthScheme() {
		return authScheme;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getRealm() {
		return realm;
	}

	public String getPath() {
		return path;
	}

	public String getProtocolScheme() {
		return protocol;
	}

	protected boolean useAuthCache() {
		return true;
	}

	private static HashMap<String, Thread> requests = new HashMap<>();

	private static boolean requestIsInProgress(String key) {
		if (!SERIALIZEAUTH) {
			return false;
		}
		synchronized (requests) {
			Thread c = Thread.currentThread();
			Thread t = requests.computeIfAbsent(key, newKey-> c );			
			if (t == c) {
				return false;
			}
			
			while (requests.containsKey(key)) {
				try {
					requests.wait();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}

		return true;
	}

	private static void requestCompleted(String key) {
		synchronized (requests) {
			Thread thread = requests.get(key);
			if (thread != null && thread == Thread.currentThread()) {
				boolean waspresent = requests.remove(key) != null;
				assert waspresent;
			}
			requests.notifyAll();
		}
	}

	char type;
	AuthScheme authScheme;
	String protocol;
	String host;
	int port;
	String realm;
	String path;

	protected AuthenticationInfo(char type, AuthScheme authScheme, String host, int port, String realm) {
		this.type = type;
		this.authScheme = authScheme;
		this.protocol = "";
		this.host = host.toLowerCase();
		this.port = port;
		this.realm = realm;
		this.path = null;
	}

	protected AuthenticationInfo(char type, AuthScheme authScheme, URL url, String realm) {
		this.type = type;
		this.authScheme = authScheme;
		this.protocol = url.getProtocol().toLowerCase();
		this.host = url.getHost().toLowerCase();
		this.port = url.getPort();
		if (this.port == -1) {
			this.port = url.getDefaultPort();
		}
		this.realm = realm;

		String urlPath = url.getPath();
		if (urlPath.length() == 0)
			this.path = urlPath;
		else {
			this.path = reducePath(urlPath);
		}

	}
	
	protected AuthenticationInfo(AuthenticationInfo toCopy) {
		this.type = toCopy.type;
		this.authScheme = toCopy.authScheme;
		this.protocol = toCopy.protocol;
		this.host = toCopy.host;
		this.port = toCopy.port;
		this.realm = toCopy.realm;
		this.path = toCopy.path;
	}

	static String reducePath(String urlPath) {
		int sepIndex = urlPath.lastIndexOf('/');
		int targetSuffixIndex = urlPath.lastIndexOf('.');
		if (sepIndex != -1) {
			if (sepIndex < targetSuffixIndex) {
				return urlPath.substring(0, sepIndex + 1);
			} else {
				return urlPath;
			}
		} else {
			return urlPath;
		}
	}

	static AuthenticationInfo getServerAuth(URL url) {
		int port = url.getPort();
		if (port == -1) {
			port = url.getDefaultPort();
		}
		String key = SERVER_AUTHENTICATION + ":" + url.getProtocol().toLowerCase() + ":" + url.getHost().toLowerCase()
				+ ":" + port;
		return getAuth(key, url);
	}

	static String getServerAuthKey(URL url, String realm, AuthScheme scheme) {
		int port = url.getPort();
		if (port == -1) {
			port = url.getDefaultPort();
		}
		return SERVER_AUTHENTICATION + ":" + scheme + ":" + url.getProtocol().toLowerCase() + ":"
				+ url.getHost().toLowerCase() + ":" + port + ":" + realm;
	}

	static AuthenticationInfo getServerAuth(String key) {
		AuthenticationInfo cached = getAuth(key, null);
		if ((cached == null) && requestIsInProgress(key)) {
			cached = getAuth(key, null);
		}
		return cached;
	}

	static AuthenticationInfo getAuth(String key, URL url) {
		if (url == null) {
			return (AuthenticationInfo) cache.get(key, null);
		} else {
			return (AuthenticationInfo) cache.get(key, url.getPath());
		}
	}

	static AuthenticationInfo getProxyAuth(String host, int port) {
		String key = PROXY_AUTHENTICATION + "::" + host.toLowerCase() + ":" + port;
		return (AuthenticationInfo) cache.get(key, null);
	}

	static String getProxyAuthKey(String host, int port, String realm, AuthScheme scheme) {
		return PROXY_AUTHENTICATION + ":" + scheme + "::" + host.toLowerCase() + ":" + port + ":" + realm;
	}

	static AuthenticationInfo getProxyAuth(String key) {
		AuthenticationInfo cached = (AuthenticationInfo) cache.get(key, null);
		if ((cached == null) && requestIsInProgress(key)) {
			cached = (AuthenticationInfo) cache.get(key, null);
		}
		return cached;
	}

	void addToCache() {
		String key = cacheKey(true);
		if (useAuthCache()) {
			cache.put(key, this);
			if (supportsPreemptiveAuthorization()) {
				cache.put(cacheKey(false), this);
			}
		}
		endAuthRequest(key);
	}

	static void endAuthRequest(String key) {
		if (!SERIALIZEAUTH) {
			return;
		}
		synchronized (requests) {
			requestCompleted(key);
		}
	}

	void removeFromCache() {
		cache.remove(cacheKey(true), this);
		if (supportsPreemptiveAuthorization()) {
			cache.remove(cacheKey(false), this);
		}
	}

	public abstract boolean supportsPreemptiveAuthorization();

	public String getHeaderName() {
		if (type == SERVER_AUTHENTICATION) {
			return "Authorization";
		} else {
			return "Proxy-authorization";
		}
	}

	public abstract String getHeaderValue(URL url, String method);

	public abstract boolean setHeaders(HttpURLConnectionImpl conn, HeaderParser p, String raw);

	public abstract boolean isAuthorizationStale(String header);

	String cacheKey(boolean includeRealm) {
		if (includeRealm) {
			return type + ":" + authScheme + ":" + protocol + ":" + host + ":" + port + ":" + realm;
		} else {
			return type + ":" + protocol + ":" + host + ":" + port;
		}
	}

	String s1;
	String s2; 
	
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		passwordAuthentication = new PasswordAuthentication(s1, s2.toCharArray());
		s1 = null;
		s2 = null;
	}

	private synchronized void writeObject(java.io.ObjectOutputStream s) throws IOException {
		s1 = passwordAuthentication.getUserName();
		s2 = new String(passwordAuthentication.getPassword());
		s.defaultWriteObject();
	}
}