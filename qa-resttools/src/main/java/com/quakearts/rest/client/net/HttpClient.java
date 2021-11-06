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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.CacheRequest;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URL;
import java.util.Locale;

import org.slf4j.Logger;

import com.quakearts.rest.client.net.http.HttpURLConnectionImpl;
import com.quakearts.rest.client.net.http.HttpURLConnectionImpl.TunnelState;

public class HttpClient extends NetworkClient {
	private static final String HTTP_CLIENT_AVAILABLE = "HttpClient.available(): ";
	protected boolean cachedHttpClient = false;
	protected boolean inCache;
	MessageHeader requests;
	PosterOutputStream poster = null;
	boolean streaming;
	boolean failedOnce = false;
	private boolean ignoreContinue = true;
	private static final int HTTP_CONTINUE = 100;
	static final int HTTPPORTNUMBER = 80;

	protected int getDefaultPort() {
		return HTTPPORTNUMBER;
	}

	private static int getDefaultPort(String proto) {
		if ("http".equalsIgnoreCase(proto))
			return 80;
		if ("https".equalsIgnoreCase(proto))
			return 443;
		return -1;
	}

	protected boolean proxyDisabled;

	private boolean usingProxy = false;
	protected String host;
	protected int port;
	protected static KeepAliveCache keepAliveCache = new KeepAliveCache();
	private static boolean keepAliveProp = true;
	volatile boolean keepingAlive = false;
	int keepAliveConnections = -1;
	int keepAliveTimeout = 0;
	private CacheRequest cacheRequest = null;
	protected URL url;
	private boolean reuse = false;
	private HttpCapture capture = null;
	private static final Logger logger = HttpURLConnectionImpl.getHttpLogger();

	private static void trace(String message, Object... parameters) {
		if (logger.isTraceEnabled()) {
			logger.trace(message, parameters);
		}
	}

	int getKeepAliveTimeout() {
		return keepAliveTimeout;
	}

	static {
		String keepAlive = System.getProperty("com.quakearts.net.http.keepAlive");

		if (keepAlive != null) {
			keepAliveProp = Boolean.parseBoolean(keepAlive);
		} else {
			keepAliveProp = true;
		}
	}

	protected HttpClient() {}

	protected HttpClient(URL url, Proxy proxy, int timeout) throws IOException {
		this.proxy = (proxy == null) ? Proxy.NO_PROXY : proxy;
		this.host = url.getHost();
		this.url = url;
		port = url.getPort();
		if (port == -1) {
			port = getDefaultPort();
		}
		setConnectTimeout(timeout);

		capture = HttpCapture.getCapture(url);
		openServer();
	}

	protected static Proxy newHttpProxy(String proxyHost, int proxyPort, String proto) {
		if (proxyHost == null || proto == null)
			return Proxy.NO_PROXY;
		int pport = proxyPort < 0 ? getDefaultPort(proto) : proxyPort;
		InetSocketAddress saddr = InetSocketAddress.createUnresolved(proxyHost, pport);
		return new Proxy(Proxy.Type.HTTP, saddr);
	}

	public static HttpClient createNew(URL url, Proxy proxy, int to, boolean useCache,
			HttpURLConnectionImpl httpURLConnection) throws IOException {
		if (proxy == null) {
			proxy = Proxy.NO_PROXY;
		}
		HttpClient retainedClient = null;
		/* see if one's already around */
		if (useCache) {
			retainedClient = keepAliveCache.get(url, null);
			if (retainedClient != null && httpURLConnection != null && httpURLConnection.streaming()
					&& !retainedClient.available()) {
				retainedClient.inCache = false;
				retainedClient.closeServer();
				retainedClient = null;
			}

			if (retainedClient != null) {
				if ((retainedClient.proxy != null && retainedClient.proxy.equals(proxy))
						|| (retainedClient.proxy == null && proxy == null)) {
					synchronized (retainedClient) {
						retainedClient.cachedHttpClient = true;
						assert retainedClient.inCache;
						retainedClient.inCache = false;
						if (httpURLConnection != null && retainedClient.needsTunneling())
							httpURLConnection.setTunnelState(TunnelState.TUNNELING);
						trace("KeepAlive stream retrieved from the cache, {}", retainedClient);
					}
				} else {
					synchronized (retainedClient) {
						retainedClient.inCache = false;
						retainedClient.closeServer();
					}
					retainedClient = null;
				}
			}
		}

		if (retainedClient == null) {
			retainedClient = new HttpClient(url, proxy, to);
		} else {
			retainedClient.url = url;
		}
		return retainedClient;
	}

	public static HttpClient createNew(URL url, Proxy p, int to, HttpURLConnectionImpl httpuc) throws IOException {
		return createNew(url, p, to, true, httpuc);
	}

	public static HttpClient createNew(URL url, String proxyHost, int proxyPort, boolean useCache, int to,
			HttpURLConnectionImpl httpuc) throws IOException {
		return createNew(url, newHttpProxy(proxyHost, proxyPort, "http"), to, useCache, httpuc);
	}

	public boolean isUsingProxy() {
		return usingProxy;
	}

	public void setReuse(boolean reuse) {
		this.reuse = reuse;
	}

	protected Socket createSocket() throws IOException {
		return new Socket();
	}

	public boolean getHttpKeepAliveSet() {
		return keepAliveProp;
	}

	public void finished() {
		if (reuse)
			return;
		keepAliveConnections--;
		poster = null;
		if (keepAliveConnections > 0 && isKeepingAlive() && !(serverOutput.checkError())) {
			putInKeepAliveCache();
		} else {
			closeServer();
		}
	}

	protected synchronized boolean available() {
		boolean available = true;
		int old = -1;

		try {
			try {
				old = serverSocket.getSoTimeout();
				serverSocket.setSoTimeout(1);
				BufferedInputStream tmpbuf = new BufferedInputStream(serverSocket.getInputStream());
				int r = tmpbuf.read();
				if (r == -1) {
					trace(HTTP_CLIENT_AVAILABLE + "read returned -1: not available");
					available = false;
				}
			} catch (SocketTimeoutException e) {
				trace(HTTP_CLIENT_AVAILABLE + "SocketTimeout: its available");
			} finally {
				if (old != -1)
					serverSocket.setSoTimeout(old);
			}
		} catch (IOException e) {
			trace(HTTP_CLIENT_AVAILABLE + "SocketException: not available");
			available = false;
		}
		return available;
	}

	protected synchronized void putInKeepAliveCache() {
		if (inCache) {
			assert false : "Duplicate put to keep alive cache";
			return;
		}
		inCache = true;
		keepAliveCache.put(url, null, this);
	}

	protected synchronized boolean isInKeepAliveCache() {
		return inCache;
	}

	public void closeIdleConnection() {
		HttpClient http = keepAliveCache.get(url, null);
		if (http != null) {
			http.closeServer();
		}
	}

	@Override
	public void openServer(String server, int port) throws IOException {
		serverSocket = doConnect(server, port);
		try {
			OutputStream out = serverSocket.getOutputStream();
			if (capture != null) {
				out = new HttpCaptureOutputStream(out, capture);
			}
			serverOutput = new PrintStream(new BufferedOutputStream(out), false, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new InternalError(encoding + " encoding not found", e);
		}
		serverSocket.setTcpNoDelay(true);
	}

	public boolean needsTunneling() {
		return false;
	}

	public synchronized boolean isCachedConnection() {
		return cachedHttpClient;
	}

	public void afterConnect() throws IOException {
		// Do nothing
	}

	private synchronized void privilegedOpenServer(final InetSocketAddress server) throws IOException {
		try {
			java.security.AccessController.doPrivileged(new java.security.PrivilegedExceptionAction<Void>() {
				public Void run() throws IOException {
					openServer(server.getHostString(), server.getPort());
					return null;
				}
			});
		} catch (java.security.PrivilegedActionException pae) {
			throw (IOException) pae.getException();
		}
	}

	protected synchronized void openServer() throws IOException {

		SecurityManager security = System.getSecurityManager();

		if (security != null) {
			security.checkConnect(host, port);
		}

		if (keepingAlive) {
			return;
		}

		if (url.getProtocol().equals("http") || url.getProtocol().equals("https")) {

			if ((proxy != null) && (proxy.type() == Proxy.Type.HTTP)) {
				com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost(host);
				privilegedOpenServer((InetSocketAddress) proxy.address());
				usingProxy = true;
			} else {
				openServer(host, port);
				usingProxy = false;
			}
		} else {
			if ((proxy != null) && (proxy.type() == Proxy.Type.HTTP)) {
				com.quakearts.rest.client.net.URLConnectionImpl.setProxiedHost(host);
				privilegedOpenServer((InetSocketAddress) proxy.address());
				usingProxy = true;
			} else {
				super.openServer(host, port);
				usingProxy = false;
			}
		}
	}

	public String getURLFile() throws IOException {
		String fileName;

		if (usingProxy && !proxyDisabled) {
			StringBuilder result = new StringBuilder(128);
			result.append(url.getProtocol());
			result.append(":");
			if (url.getAuthority() != null && url.getAuthority().length() > 0) {
				result.append("//");
				result.append(url.getAuthority());
			}
			if (url.getPath() != null) {
				result.append(url.getPath());
			}
			if (url.getQuery() != null) {
				result.append('?');
				result.append(url.getQuery());
			}

			fileName = result.toString();
		} else {
			fileName = url.getFile();

			if ((fileName == null) || (fileName.length() == 0)) {
				fileName = "/";
			} else if (fileName.charAt(0) == '?') {
				fileName = "/" + fileName;
			}
		}

		if (fileName.indexOf('\n') == -1)
			return fileName;
		else
			throw new java.net.MalformedURLException("Illegal character in URL");
	}

	public void writeRequests(MessageHeader head, PosterOutputStream pos) throws IOException {
		requests = head;
		requests.print(serverOutput);
		poster = pos;
		if (poster != null)
			poster.writeTo(serverOutput);
		serverOutput.flush();
	}

	public void writeRequests(MessageHeader head, PosterOutputStream pos, boolean streaming) throws IOException {
		this.streaming = streaming;
		writeRequests(head, pos);
	}

	public boolean parseHTTP(MessageHeader responses, ProgressSource progressSource, HttpURLConnectionImpl httpuc)
			throws IOException {
		try {
			serverInput = serverSocket.getInputStream();
			if (capture != null) {
				serverInput = new HttpCaptureInputStream(serverInput, capture);
			}
			serverInput = new BufferedInputStream(serverInput);
			return (parseHTTPHeader(responses, progressSource, httpuc));
		} catch (SocketTimeoutException stex) {
			if (ignoreContinue) {
				closeServer();
			}
			throw stex;
		} catch (IOException e) {
			closeServer();
			cachedHttpClient = false;
			if (!failedOnce && requests != null) {
				failedOnce = true;
				if (getRequestMethod().equals("CONNECT") || streaming) {
					// Do nothing
				} else {
					openServer();
					if (needsTunneling()) {
						httpuc.doTunneling();
					}
					afterConnect();
					writeRequests(requests, poster);
					return parseHTTP(responses, progressSource, httpuc);
				}
			}
			throw e;
		}

	}

	private boolean parseHTTPHeader(MessageHeader responses, ProgressSource progressSource, HttpURLConnectionImpl httpuc)
			throws IOException {
		keepAliveConnections = -1;
		keepAliveTimeout = 0;

		boolean ret = false;
		byte[] b = new byte[8];

		int nread = 0;
		serverInput.mark(10);
		while (nread < 8) {
			int r = serverInput.read(b, nread, 8 - nread);
			if (r < 0) {
				break;
			}
			nread += r;
		}
		String keep = null;
		ret = b[0] == 'H' && b[1] == 'T' && b[2] == 'T' && b[3] == 'P' && b[4] == '/' && b[5] == '1' && b[6] == '.';
		serverInput.reset();
		if (ret) {
			responses.parseHeader(serverInput);

			CookieHandler cookieHandler = httpuc.getCookieHandler();
			if (cookieHandler != null) {
				URI uri = URIUtil.toURI(url);
				if (uri != null)
					cookieHandler.put(uri, responses.getHeaders());
			}

			if (usingProxy) {
				keep = responses.findValue("Proxy-Connection");
			}

			if (keep == null) {
				keep = responses.findValue("Connection");
			}

			if (keep != null && keep.toLowerCase(Locale.US).equals("keep-alive")) {
				HeaderParser p = new HeaderParser(responses.findValue("Keep-Alive"));
				keepAliveConnections = p.findInt("max", usingProxy ? 50 : 5);
				keepAliveTimeout = p.findInt("timeout", usingProxy ? 60 : 5);
			} else if (b[7] != '0') {
				if (keep != null) {
					keepAliveConnections = 1;
				} else {
					keepAliveConnections = 5;
				}
			}
		} else if (nread != 8) {
			if (!failedOnce && requests != null) {
				failedOnce = true;
				if (getRequestMethod().equals("CONNECT") || streaming) {
					// Do nothing
				} else {
					closeServer();
					cachedHttpClient = false;
					openServer();
					if (needsTunneling()) {
						MessageHeader origRequests = requests;
						httpuc.doTunneling();
						requests = origRequests;
					}
					afterConnect();
					writeRequests(requests, poster);
					return parseHTTP(responses, progressSource, httpuc);
				}
			}
			throw new SocketException("Unexpected end of file from server");
		} else {
			responses.set("Content-type", "unknown/unknown");
		}

		int code = -1;
		try {
			String resp;
			resp = responses.getValue(0);
			int ind;
			ind = resp.indexOf(' ');
			while (resp.charAt(ind) == ' ')
				ind++;
			code = Integer.parseInt(resp.substring(ind, ind + 3));
		} catch (Exception e) {
			// Do nothing
		}

		if (code == HTTP_CONTINUE && ignoreContinue) {
			responses.reset();
			return parseHTTPHeader(responses, progressSource, httpuc);
		}

		long contentLength = -1;

		String te = responses.findValue("Transfer-Encoding");
		if (te != null && te.equalsIgnoreCase("chunked")) {
			serverInput = new ChunkedInputStream(serverInput, this, responses);

			if (keepAliveConnections <= 1) {
				keepAliveConnections = 1;
				keepingAlive = false;
			} else {
				keepingAlive = true;
			}
			failedOnce = false;
		} else {
			String contentLengthString = responses.findValue("content-length");
			if (contentLengthString != null) {
				try {
					contentLength = Long.parseLong(contentLengthString);
				} catch (NumberFormatException e) {
					contentLength = -1;
				}
			}
			
			String requestLine = requests.getKey(0);

			if ((requestLine != null && (requestLine.startsWith("HEAD"))) || code == HttpURLConnection.HTTP_NOT_MODIFIED
					|| code == HttpURLConnection.HTTP_NO_CONTENT) {
				contentLength = 0;
			}

			if (keepAliveConnections > 1 && (contentLength >= 0 || code == HttpURLConnection.HTTP_NOT_MODIFIED
					|| code == HttpURLConnection.HTTP_NO_CONTENT)) {
				keepingAlive = true;
				failedOnce = false;
			} else if (keepingAlive) {
				keepingAlive = false;
			}
		}

		if (contentLength > 0) {
			if (progressSource != null) {
				progressSource.setContentType(responses.findValue("content-type"));
			}

			boolean useKeepAliveStream = isKeepingAlive();
			if (useKeepAliveStream) {
				trace("KeepAlive stream used: {}", url);
				serverInput = new KeepAliveStream(serverInput, progressSource, contentLength, this);
				failedOnce = false;
			} else {
				serverInput = new MeteredStream(serverInput, progressSource, contentLength);
			}
		} else if (contentLength == -1) {
			if (progressSource != null) {
				progressSource.setContentType(responses.findValue("content-type"));
				serverInput = new MeteredStream(serverInput, progressSource, contentLength);
			}
		} else {
			if (progressSource != null)
				progressSource.finishTracking();
		}

		return ret;
	}

	public synchronized InputStream getInputStream() {
		return serverInput;
	}

	public OutputStream getOutputStream() {
		return serverOutput;
	}

	@Override
	public String toString() {
		return getClass().getName() + "(" + url + ")";
	}

	public final boolean isKeepingAlive() {
		return getHttpKeepAliveSet() && keepingAlive;
	}

	public void setCacheRequest(CacheRequest cacheRequest) {
		this.cacheRequest = cacheRequest;
	}

	CacheRequest getCacheRequest() {
		return cacheRequest;
	}

	String getRequestMethod() {
		if (requests != null) {
			String requestLine = requests.getKey(0);
			if (requestLine != null) {
				return requestLine.split("\\s+")[0];
			}
		}
		return "";
	}

	public void setDoNotRetry(boolean value) {
		failedOnce = value;
	}

	public void setIgnoreContinue(boolean value) {
		ignoreContinue = value;
	}

	@Override
	public void closeServer() {
		try {
			keepingAlive = false;
			serverSocket.close();
		} catch (Exception e) {
			// Do nothing
		}
	}

	public String getProxyHostUsed() {
		if (!usingProxy) {
			return null;
		} else {
			return ((InetSocketAddress) proxy.address()).getHostString();
		}
	}

	public int getProxyPortUsed() {
		if (usingProxy)
			return ((InetSocketAddress) proxy.address()).getPort();
		return -1;
	}
}
