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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.PrintStream;

import static com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState.*;

import java.io.BufferedOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.slf4j.Logger;

import com.quakearts.rest.client.net.HttpClient;
import com.quakearts.rest.client.net.http.HttpURLConnectionImpl;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.X509Certificate;

final class HttpsClient extends HttpClient implements HandshakeCompletedListener {
	private static final int HTTPS_PORT_NUMBER = 443;

	private static final String DEFAULT_HV_CANONICAL_NAME = "javax.net.ssl.HttpsURLConnection.DefaultHostnameVerifier";

	@Override
	protected int getDefaultPort() {
		return HTTPS_PORT_NUMBER;
	}

	private HostnameVerifier hostnameVerifier;
	private SSLSocketFactory sslSocketFactory;

	private SSLSession session;

	private String[] getCipherSuites() {
		String[] ciphers;
		String cipherString = System.getProperty("https.cipherSuites");

		if (cipherString == null || "".equals(cipherString)) {
			ciphers = null;
		} else {
			StringTokenizer tokenizer;
			List<String> v = new ArrayList<>();

			tokenizer = new StringTokenizer(cipherString, ",");
			while (tokenizer.hasMoreTokens())
				v.add(tokenizer.nextToken());
			ciphers = new String[v.size()];
			for (int i = 0; i < ciphers.length; i++)
				ciphers[i] = v.get(i);
		}
		return ciphers;
	}

	private String[] getProtocols() {
		String[] protocols;
		String protocolString = System.getProperty("https.protocols");

		if (protocolString == null || "".equals(protocolString)) {
			protocols = null;
		} else {
			StringTokenizer tokenizer;
			List<String> v = new ArrayList<>();

			tokenizer = new StringTokenizer(protocolString, ",");
			while (tokenizer.hasMoreTokens())
				v.add(tokenizer.nextToken());
			protocols = new String[v.size()];
			for (int i = 0; i < protocols.length; i++) {
				protocols[i] = v.get(i);
			}
		}
		return protocols;
	}

	HttpsClient(SSLSocketFactory socketFactory, URL url, String proxyHost, int proxyPort) throws IOException {
		this(socketFactory, url, proxyHost, proxyPort, -1);
	}

	HttpsClient(SSLSocketFactory socketFactory, URL url, String proxyHost, int proxyPort, int connectTimeout) throws IOException {
		this(socketFactory, url, (proxyHost == null ? null : HttpClient.newHttpProxy(proxyHost, proxyPort, "https")),
				connectTimeout);
	}

	HttpsClient(SSLSocketFactory socketFactory, URL url, Proxy proxy, int connectTimeout) throws IOException {
		Logger logger = HttpURLConnectionImpl.getHttpLogger();
		if (logger.isTraceEnabled()) {
			logger.trace("Creating new HttpsClient with url: {}  and proxy:{} with connect timeout: {}", url, proxy,
					connectTimeout);
		}
		this.proxy = proxy;
		setSSLSocketFactory(socketFactory);
		this.proxyDisabled = true;

		this.host = url.getHost();
		this.url = url;
		port = url.getPort();
		if (port == -1) {
			port = getDefaultPort();
		}
		setConnectTimeout(connectTimeout);
		openServer();
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, HttpURLConnectionImpl httpUrlConnection)
			throws IOException {
		return HttpsClient.createNew(socketFactory, url, hostnameVerifier, true, httpUrlConnection);
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, boolean useCache,
			HttpURLConnectionImpl httpUrlConnection) throws IOException {
		return HttpsClient.createNew(socketFactory, url, hostnameVerifier, (String) null, -1, useCache, httpUrlConnection);
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, String proxyHost, int proxyPort,
			HttpURLConnectionImpl httpUrlConnection) throws IOException {
		return HttpsClient.createNew(socketFactory, url, hostnameVerifier, proxyHost, proxyPort, true, httpUrlConnection);
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, String proxyHost, int proxyPort,
			boolean useCache, HttpURLConnectionImpl httpUrlConnection) throws IOException {
		return HttpsClient.createNew(socketFactory, url, hostnameVerifier, proxyHost, proxyPort, useCache, -1, httpUrlConnection);
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, String proxyHost, int proxyPort,
			boolean useCache, int connectTimeout, HttpURLConnectionImpl httpUrlConnection) throws IOException {

		return HttpsClient.createNew(socketFactory, url, hostnameVerifier,
				(proxyHost == null ? null : HttpClient.newHttpProxy(proxyHost, proxyPort, "https")), useCache,
				connectTimeout, httpUrlConnection);
	}

	static HttpClient createNew(SSLSocketFactory socketFactory, URL url, HostnameVerifier hostnameVerifier, Proxy p, boolean useCache,
			int connectTimeout, HttpURLConnectionImpl httpUrlConnection) throws IOException {
		if (p == null) {
			p = Proxy.NO_PROXY;
		}

		Logger logger = HttpURLConnectionImpl.getHttpLogger();

		if (logger.isTraceEnabled()) {
			logger.trace("Looking for HttpClient for URL {} and proxy value of {}", url, p);
		}

		HttpsClient retainedClient = null;
		if (useCache) {

			retainedClient = (HttpsClient) keepAliveCache.get(url, socketFactory);
			if (retainedClient != null && httpUrlConnection != null && httpUrlConnection.streaming() && !retainedClient.available()) {
				retainedClient = null;
			}

			if (retainedClient != null) {
				if ((retainedClient.proxy != null && retainedClient.proxy.equals(p))
						|| (retainedClient.proxy == null && p == Proxy.NO_PROXY)) {
					synchronized (retainedClient) {
						retainedClient.cachedHttpClient = true;
						assert retainedClient.inCache;
						retainedClient.inCache = false;
						if (httpUrlConnection != null && retainedClient.needsTunneling())
							httpUrlConnection.setTunnelState(TUNNELING);
						if (logger.isTraceEnabled()) {
							logger.trace("KeepAlive stream retrieved from the cache, {}", retainedClient);
						}
					}
				} else {
					// We cannot return this connection to the cache as it's
					// KeepAliveTimeout will get reset. We simply close the connection.
					// This should be fine as it is very rare that a connection
					// to the same host will not use the same proxy.
					synchronized (retainedClient) {
						if (logger.isTraceEnabled()) {
							logger.trace("Not returning this connection to cache: {}", retainedClient);
						}
						retainedClient.inCache = false;
						retainedClient.closeServer();
					}
					retainedClient = null;
				}
			}
		}

		if (retainedClient == null) {
			retainedClient = new HttpsClient(socketFactory, url, p, connectTimeout);
		} else {
			SecurityManager security = System.getSecurityManager();
			if (security != null) {
				if (retainedClient.proxy == Proxy.NO_PROXY || retainedClient.proxy == null) {
					security.checkConnect(InetAddress.getByName(url.getHost()).getHostAddress(), url.getPort());
				} else {
					security.checkConnect(url.getHost(), url.getPort());
				}
			}
			retainedClient.url = url;
		}
		retainedClient.setHostnameVerifier(hostnameVerifier);

		return retainedClient;
	}

	void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
		this.hostnameVerifier = hostnameVerifier;
	}

	void setSSLSocketFactory(SSLSocketFactory socketFactory) {
		sslSocketFactory = socketFactory;
	}

	SSLSocketFactory getSSLSocketFactory() {
		return sslSocketFactory;
	}

	@Override
	protected Socket createSocket() throws IOException {
		try {
			return sslSocketFactory.createSocket();
		} catch (SocketException se) {
			Throwable t = se.getCause();
			if (t instanceof UnsupportedOperationException) {
				return super.createSocket();
			} else {
				throw se;
			}
		}
	}

	@Override
	public boolean needsTunneling() {
		return (proxy != null && proxy.type() != Proxy.Type.DIRECT && proxy.type() != Proxy.Type.SOCKS);
	}

	@SuppressWarnings("resource")
	@Override
	public void afterConnect() throws IOException {
		if (!isCachedConnection()) {
			SSLSocket s = null;
			SSLSocketFactory factory = sslSocketFactory;
			try {
				if (!(serverSocket instanceof SSLSocket)) {
					s = (SSLSocket) factory.createSocket(serverSocket, host, port, true);
				} else {
					s = (SSLSocket) serverSocket;
				}
			} catch (IOException ex) {
				try {
					s = (SSLSocket) factory.createSocket(host, port);
				} catch (IOException ignored) {
					throw ex;
				}
			}

			String[] protocols = getProtocols();
			String[] ciphers = getCipherSuites();
			if (protocols != null) {
				s.setEnabledProtocols(protocols);
			}
			if (ciphers != null) {
				s.setEnabledCipherSuites(ciphers);
			}
			s.addHandshakeCompletedListener(this);

			boolean needToCheckSpoofing = true;
			String identification = s.getSSLParameters().getEndpointIdentificationAlgorithm();
			if (identification != null && identification.length() != 0) {
				if (identification.equalsIgnoreCase("HTTPS")) {
					needToCheckSpoofing = false;
				}
			} else {
				boolean isDefaultHostnameVerifier = false;
				if (hostnameVerifier != null) {
					String canonicalName = hostnameVerifier.getClass().getCanonicalName();
					if (canonicalName != null && canonicalName.equalsIgnoreCase(DEFAULT_HV_CANONICAL_NAME)) {
						isDefaultHostnameVerifier = true;
					}
				} else {
					isDefaultHostnameVerifier = true;
				}

				if (isDefaultHostnameVerifier) {
					SSLParameters paramaters = s.getSSLParameters();
					paramaters.setEndpointIdentificationAlgorithm("HTTPS");
					s.setSSLParameters(paramaters);

					needToCheckSpoofing = false;
				}
			}

			s.startHandshake();
			session = s.getSession();

			serverSocket = s;
			try {
				serverOutput = new PrintStream(new BufferedOutputStream(serverSocket.getOutputStream()), false,
						encoding);
			} catch (UnsupportedEncodingException e) {
				throw new InternalError(encoding + " encoding not found");
			}

			if (needToCheckSpoofing) {
				checkURLSpoofing(hostnameVerifier);
			}
		} else {
			session = ((SSLSocket) serverSocket).getSession();
		}
	}

	private void checkURLSpoofing(HostnameVerifier hostnameVerifier) throws IOException {
		String host = url.getHost();

		if (host != null && host.startsWith("[") && host.endsWith("]")) {
			host = host.substring(1, host.length() - 1);
		}

		Certificate[] peerCerts = null;
		String cipher = session.getCipherSuite();
		try {
			HostnameChecker checker = HostnameChecker.getInstance(HostnameChecker.TYPE_TLS);

			if (cipher.startsWith("TLS_KRB5")) {
				if (!HostnameChecker.match(host, getPeerPrincipal())) {
					throw new SSLPeerUnverifiedException("Hostname checker" + " failed for Kerberos");
				}
			} else {
				peerCerts = session.getPeerCertificates();

				java.security.cert.X509Certificate peerCert;
				if (peerCerts[0] instanceof java.security.cert.X509Certificate) {
					peerCert = (java.security.cert.X509Certificate) peerCerts[0];
				} else {
					throw new SSLPeerUnverifiedException("");
				}
				checker.match(host, peerCert);
			}

			return;
		} catch (SSLPeerUnverifiedException | CertificateException cpe) {
			// Do nothing
		}

		if ((cipher != null && (cipher.indexOf("_anon_") != -1))
				|| (hostnameVerifier != null && hostnameVerifier.verify(host, session))) {
			return;
		}

		serverSocket.close();
		session.invalidate();

		throw new IOException("HTTPS hostname wrong:  should be <" + url.getHost() + ">");
	}

	@Override
	protected synchronized void putInKeepAliveCache() {
		if (inCache) {
			assert false : "Duplicate put to keep alive cache";
			return;
		}
		inCache = true;
		keepAliveCache.put(url, sslSocketFactory, this);
	}

	@Override
	public void closeIdleConnection() {
		HttpClient http = keepAliveCache.get(url, sslSocketFactory);
		if (http != null) {
			http.closeServer();
		}
	}

	String getCipherSuite() {
		return session.getCipherSuite();
	}

	public java.security.cert.Certificate[] getLocalCertificates() {
		return session.getLocalCertificates();
	}

	Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
		return session.getPeerCertificates();
	}

	X509Certificate[] getServerCertificateChain() throws SSLPeerUnverifiedException {
		return session.getPeerCertificateChain();
	}

	Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
		Principal principal;
		try {
			principal = session.getPeerPrincipal();
		} catch (AbstractMethodError e) {
			Certificate[] certs = session.getPeerCertificates();
			principal = ((java.security.cert.X509Certificate) certs[0]).getSubjectX500Principal();
		}
		return principal;
	}

	Principal getLocalPrincipal() {
		Principal principal;
		try {
			principal = session.getLocalPrincipal();
		} catch (AbstractMethodError e) {
			principal = null;
			Certificate[] certs = session.getLocalCertificates();
			if (certs != null) {
				principal = ((java.security.cert.X509Certificate) certs[0]).getSubjectX500Principal();
			}
		}
		return principal;
	}

	public void handshakeCompleted(HandshakeCompletedEvent event) {
		session = event.getSession();
	}

	@Override
	public String getProxyHostUsed() {
		if (!needsTunneling()) {
			return null;
		} else {
			return super.getProxyHostUsed();
		}
	}

	@Override
	public int getProxyPortUsed() {
		return (proxy == null || proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) ? -1
				: ((InetSocketAddress) proxy.address()).getPort();
	}
}
