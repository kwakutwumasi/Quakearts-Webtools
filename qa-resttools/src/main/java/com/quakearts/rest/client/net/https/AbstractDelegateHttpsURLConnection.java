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
package com.quakearts.rest.client.net.https;

import java.net.URL;
import java.net.Proxy;
import java.net.SecureCacheResponse;
import java.security.Principal;
import java.security.cert.Certificate;
import java.io.IOException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

import com.quakearts.rest.client.net.HttpClient;
import com.quakearts.rest.client.net.http.HttpURLConnectionImpl;

public abstract class AbstractDelegateHttpsURLConnection extends HttpURLConnectionImpl {

	private static final Certificate[] EMPTY_CERTIFICATE = new Certificate[0];
	private static final String CONNECTION_NOT_YET_OPEN = "connection not yet open";

	protected AbstractDelegateHttpsURLConnection(URL url, Proxy proxy, HttpsHandler handler)
			throws IOException {
		super(url, proxy, handler);
	}

	protected abstract SSLSocketFactory getSSLSocketFactory();
	protected abstract HostnameVerifier getHostnameVerifier();

	public void setNewClient(URL url) throws IOException {
		setNewClient(url, false);
	}

	public void setNewClient(URL url, boolean useCache) throws IOException {
		http = HttpsClient.createNew(getSSLSocketFactory(), url, getHostnameVerifier(), useCache, this);
		((HttpsClient) http).afterConnect();
	}

	@Override
	public void setProxiedClient(URL url, String proxyHost, int proxyPort) throws IOException {
		setProxiedClient(url, proxyHost, proxyPort, false);
	}

	@Override
	public void setProxiedClient(URL url, String proxyHost, int proxyPort, boolean useCache) throws IOException {
		proxiedConnect(url, proxyHost, proxyPort, useCache);
		if (!http.isCachedConnection()) {
			doTunneling();
		}
		((HttpsClient) http).afterConnect();
	}

	@Override
	protected void proxiedConnect(URL url, String proxyHost, int proxyPort, boolean useCache) throws IOException {
		if (connected)
			return;
		http = HttpsClient.createNew(getSSLSocketFactory(), url, getHostnameVerifier(), proxyHost, proxyPort, useCache, this);
		connected = true;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean conn) {
		connected = conn;
	}

	@Override
	public void connect() throws IOException {
		if (connected)
			return;
		plainConnect();
		if (cachedResponse != null) {
			return;
		}
		if (!http.isCachedConnection() && http.needsTunneling()) {
			doTunneling();
		}
		((HttpsClient) http).afterConnect();
	}

	@Override
	protected HttpClient getCreateNewHttpClient(URL url, Proxy p, int connectTimeout) throws IOException {
		return HttpsClient.createNew(getSSLSocketFactory(), url, getHostnameVerifier(), p, true, connectTimeout, this);
	}

	@Override
	protected HttpClient getCreateNewHttpClient(URL url, Proxy p, int connectTimeout, boolean useCache) throws IOException {
		return HttpsClient.createNew(getSSLSocketFactory(), url, getHostnameVerifier(), p, useCache, connectTimeout, this);
	}

	public String getCipherSuite() {
		if (cachedResponse != null) {
			return ((SecureCacheResponse) cachedResponse).getCipherSuite();
		}
		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return ((HttpsClient) http).getCipherSuite();
		}
	}

	public Certificate[] getLocalCertificates() {
		if (cachedResponse != null) {
			List<Certificate> l = ((SecureCacheResponse) cachedResponse).getLocalCertificateChain();
			if (l == null) {
				return EMPTY_CERTIFICATE;
			} else {
				return l.toArray(new java.security.cert.Certificate[0]);
			}
		}
		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return (((HttpsClient) http).getLocalCertificates());
		}
	}

	public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
		if (cachedResponse != null) {
			List<Certificate> l = ((SecureCacheResponse) cachedResponse).getServerCertificateChain();
			if (l == null) {
				return EMPTY_CERTIFICATE;
			} else {
				return l.toArray(new java.security.cert.Certificate[0]);
			}
		}

		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return (((HttpsClient) http).getServerCertificates());
		}
	}

	public X509Certificate[] getServerCertificateChain() throws SSLPeerUnverifiedException {
		if (cachedResponse != null) {
			throw new UnsupportedOperationException("this method is not supported when using cache");
		}
		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return ((HttpsClient) http).getServerCertificateChain();
		}
	}

	Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
		if (cachedResponse != null) {
			return ((SecureCacheResponse) cachedResponse).getPeerPrincipal();
		}

		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return (((HttpsClient) http).getPeerPrincipal());
		}
	}

	Principal getLocalPrincipal() {
		if (cachedResponse != null) {
			return ((SecureCacheResponse) cachedResponse).getLocalPrincipal();
		}

		if (http == null) {
			throw new IllegalStateException(CONNECTION_NOT_YET_OPEN);
		} else {
			return (((HttpsClient) http).getLocalPrincipal());
		}
	}
}
