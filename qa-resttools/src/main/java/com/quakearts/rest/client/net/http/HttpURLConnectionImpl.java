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

import java.util.Arrays;
import java.net.URL;
import java.net.URLConnection;
import java.net.ProtocolException;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.quakearts.rest.client.net.http.AuthScheme.BASIC;
import static com.quakearts.rest.client.net.http.AuthScheme.DIGEST;
import static com.quakearts.rest.client.net.http.AuthScheme.UNKNOWN;

import java.io.FileNotFoundException;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.CookieHandler;
import java.net.ResponseCache;
import java.net.CacheResponse;
import java.net.SecureCacheResponse;
import java.net.CacheRequest;
import java.net.Authenticator.RequestorType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.rest.client.exception.HttpClientRuntimeException;
import com.quakearts.rest.client.net.ChunkedInputStream;
import com.quakearts.rest.client.net.ChunkedOutputStream;
import com.quakearts.rest.client.net.HeaderParser;
import com.quakearts.rest.client.net.HttpClient;
import com.quakearts.rest.client.net.IPAddressUtil;
import com.quakearts.rest.client.net.MessageHeader;
import com.quakearts.rest.client.net.MeteredStream;
import com.quakearts.rest.client.net.NetProperties;
import com.quakearts.rest.client.net.NetworkClient;
import com.quakearts.rest.client.net.URIUtil;
import com.quakearts.rest.client.net.PosterOutputStream;
import com.quakearts.rest.client.net.ProgressMonitor;
import com.quakearts.rest.client.net.ProgressSource;

import java.net.MalformedURLException;
import java.nio.ByteBuffer;

public class HttpURLConnectionImpl extends HttpURLConnection {

	private static final String ALREADY_CONNECTED = "Already connected";
	private static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
	private static final String WWW_AUTHENTICATE = "WWW-Authenticate";
	private static final String KEEP_ALIVE = "keep-alive";
	private static final String COOKIE2 = "Cookie2";
	private static final String COOKIE = "Cookie";
	private static final String AUTHORIZATION = "Authorization";
	private static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
	private static final String TRANSFER_ENCODING = "Transfer-Encoding";
	private static final String CONTENT_LENGTH = "Content-Length";
	private static final String CONNECTION = "Connection";
	private static final Logger logger = LoggerFactory.getLogger(HttpURLConnectionImpl.class);
	protected static final String HTTP_CONNECT = "CONNECT";
	protected static final String VERSION;
	protected static final String USER_AGENT;
	protected static final int DEFAULT_MAX_REDIRECTS = 20;
	protected static final int MAX_REDIRECTS;
	protected static final boolean VALIDATE_PROXY;
	protected static final boolean VALIDATE_SERVER;
	protected static final Set<String> DISABLED_PROXYING_SCHEMES;
	protected static final Set<String> DISABLED_TUNNELING_SCHEMES;
	private StreamingOutputStream streamingOutputStream;
	private static final String RETRY_MSG1 = "cannot retry due to proxy authentication, in streaming mode";
	private static final String RETRY_MSG2 = "cannot retry due to server authentication, in streaming mode";
	private static final String RETRY_MSG3 = "cannot retry due to redirection, in streaming mode";
	private static boolean enableESBuffer = false;
	private static int timeout4ESBuffer = 0;
	private static int bufSize4ES = 0;
	private static final boolean ALLOW_RESTRICTED_HEADERS;
	private static final Set<String> RESTRICTED_HEADER_SET;
	private static final String[] RESTRICTED_HEADERS = { "Access-Control-Request-Headers",
			"Access-Control-Request-Method", CONNECTION, CONTENT_LENGTH, "Content-Transfer-Encoding", "Host",
			"Keep-Alive", "Origin", "Trailer", TRANSFER_ENCODING, "Upgrade", "Via" };

	private static Set<String> schemesListToSet(String list) {
		if (list == null || list.isEmpty())
			return Collections.emptySet();

		Set<String> s = new HashSet<>();
		String[] parts = list.split("\\s*,\\s*");
		for (String part : parts)
			s.add(part.toLowerCase(Locale.ROOT));
		return s;
	}

	static {
		MAX_REDIRECTS = Integer.getInteger("com.quakearts.net.maxRedirects", DEFAULT_MAX_REDIRECTS).intValue();
		VERSION = System.getProperty("java.version");
		String agent = System.getProperty("com.quakearts.net.agent");
		if (agent == null) {
			agent = "Java/" + VERSION;
		} else {
			agent = agent + " Java/" + VERSION;
		}
		USER_AGENT = agent;

		String value = NetProperties.get("com.quakearts.net.auth.tunneling.disabledSchemes");
		DISABLED_TUNNELING_SCHEMES = schemesListToSet(value);
		value = NetProperties.get("com.quakearts.net.auth.proxying.disabledSchemes");
		DISABLED_PROXYING_SCHEMES = schemesListToSet(value);

		VALIDATE_PROXY = Boolean.getBoolean("com.quakearts.net.auth.digest.validateProxy");
		VALIDATE_SERVER = Boolean.getBoolean("com.quakearts.net.auth.digest.validateServer");

		enableESBuffer = Boolean.getBoolean("com.quakearts.net.errorstream.enableBuffering");
		timeout4ESBuffer = Integer.getInteger("com.quakearts.net.errorstream.timeout", 300).intValue();
		if (timeout4ESBuffer <= 0) {
			timeout4ESBuffer = 300;
		}

		bufSize4ES = Integer.getInteger("com.quakearts.net.errorstream.bufferSize", 4096).intValue();
		if (bufSize4ES <= 0) {
			bufSize4ES = 4096;
		}

		ALLOW_RESTRICTED_HEADERS = Boolean.getBoolean("com.quakearts.net.allowRestrictedHeaders");
		if (!ALLOW_RESTRICTED_HEADERS) {
			RESTRICTED_HEADER_SET = new HashSet<>(RESTRICTED_HEADERS.length);
			for (int i = 0; i < RESTRICTED_HEADERS.length; i++) {
				RESTRICTED_HEADER_SET.add(RESTRICTED_HEADERS[i].toLowerCase());
			}
		} else {
			RESTRICTED_HEADER_SET = null;
		}
	}

	protected static final String HTTP_VERSION = "HTTP/1.1";
	protected static final String ACCEPT_STRING = "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2";
	private static final String[] EXCLUDE_HEADERS = { PROXY_AUTHORIZATION, AUTHORIZATION };
	private static final String[] EXCLUDE_HEADERS2 = { PROXY_AUTHORIZATION, AUTHORIZATION, COOKIE, COOKIE2 };
	protected HttpClient http;
	protected HttpHandler handler;
	protected Proxy instanceProxy;
	private CookieHandler cookieHandler;
	private final ResponseCache cacheHandler;
	protected CacheResponse cachedResponse;
	private MessageHeader cachedHeaders;
	private InputStream cachedInputStream;
	protected PrintStream printStream = null;
	private InputStream errorStream = null;
	private boolean setUserCookies = true;
	private String userCookies = null;
	private String userCookies2 = null;
	private MessageHeader requests;
	private MessageHeader userHeaders;
	private boolean connecting = false;
	String domain;
	DigestAuthentication.Parameters digestparams;
	AuthenticationInfo currentProxyCredentials = null;
	AuthenticationInfo currentServerCredentials = null;
	boolean needToCheck = true;
	private Object authObj;
	protected boolean isUserServerAuth;
	protected boolean isUserProxyAuth;
	protected String serverAuthKey;
	protected String proxyAuthKey;
	protected ProgressSource progressSource;
	private MessageHeader responses;
	private InputStream inputStream = null;
	private PosterOutputStream poster = null;
	private boolean setRequests = false;
	private boolean failedOnce = false;
	private Exception rememberedException = null;
	private HttpClient reuseClient = null;

	public enum TunnelState {
		NONE, SETUP, TUNNELING
	}

	private TunnelState tunnelState = TunnelState.NONE;
	private int connectTimeout = NetworkClient.DEFAULT_CONNECT_TIMEOUT;
	private int readTimeout = NetworkClient.DEFAULT_READ_TIMEOUT;

	private static PasswordAuthentication requestPasswordAuthentication(PasswordParameters parameters) {
		if (logger.isTraceEnabled()) {
			logger.trace("Requesting Authentication: host = {}  url = {}", parameters.host, parameters.url);
		}

		PasswordAuthentication pass = Authenticator.requestPasswordAuthentication(parameters.host, parameters.addr,
				parameters.port, parameters.protocol, parameters.prompt, parameters.scheme, parameters.url,
				parameters.authType);
		if (logger.isTraceEnabled()) {
			logger.trace("Authentication returned: {}", (pass != null ? pass.toString() : "null"));
		}
		return pass;
	}

	private boolean isRestrictedHeader(String key, String value) {
		if (ALLOW_RESTRICTED_HEADERS) {
			return false;
		}

		key = key.toLowerCase();
		if (RESTRICTED_HEADER_SET.contains(key)) {
			return (key.equals("connection") && value.equalsIgnoreCase("close"));
		} else if (key.startsWith("sec-")) {
			return true;
		}
		return false;
	}

	private boolean isExternalMessageHeaderAllowed(String key, String value) {
		checkMessageHeader(key, value);
		return (!isRestrictedHeader(key, value));
	}

	public static Logger getHttpLogger() {
		return logger;
	}

	public Object authObj() {
		return authObj;
	}

	public void authObj(Object authObj) {
		this.authObj = authObj;
	}

	private void checkMessageHeader(String key, String value) {
		char lineFeed = '\n';
		int index = key.indexOf(lineFeed);
		int index1 = key.indexOf(':');
		if (index != -1 || index1 != -1) {
			throw new IllegalArgumentException("Illegal character(s) in message header field: " + key);
		} else {
			if (value == null) {
				return;
			}

			index = value.indexOf(lineFeed);
			while (index != -1) {
				index++;
				if (index < value.length()) {
					char c = value.charAt(index);
					if ((c == ' ') || (c == '\t')) {
						index = value.indexOf(lineFeed, index);
						continue;
					}
				}
				throw new IllegalArgumentException("Illegal character(s) in message header value: " + value);
			}
		}
	}

	@Override
	public synchronized void setRequestMethod(String method) throws ProtocolException {
		if (connecting) {
			throw new IllegalStateException("connect in progress");
		}
		super.setRequestMethod(method);
	}

	private void writeRequests() throws IOException {
		if (http.isUsingProxy() && tunnelState() != TunnelState.TUNNELING) {
			setPreemptiveProxyAuthentication(requests);
		}

		if (!setRequests) {
			if (!failedOnce) {
				requests.prepend(method + " " + getRequestURI() + " " + HTTP_VERSION, null);
			}

			if (!getUseCaches()) {
				requests.setIfNotSet("Cache-Control", "no-cache");
				requests.setIfNotSet("Pragma", "no-cache");
			}

			requests.setIfNotSet("User-Agent", USER_AGENT);

			writeHostHeader();

			requests.setIfNotSet("Accept", ACCEPT_STRING);

			writeConnectionHeader();
			writeIfNotModified();
			writeAuthenticationHeader();
			writePostContentTypeIfEmpty();

			boolean chunked = doWriteBody();
			handleBodyEncoding(chunked);

			setCookieHeader();
			setRequests = true;
		}

		if (logger.isDebugEnabled()) {
			logger.debug(requests.toString());
		}

		http.writeRequests(requests, poster, streaming());

		checkPrintStreamErrorAndRetry();
	}

	private void writeHostHeader() {
		int port = url.getPort();

		String host = url.getHost();

		if (port != -1 && port != url.getDefaultPort()) {
			host += ":" + port;
		}

		String reqHost = requests.findValue("Host");

		if (reqHost == null || (!reqHost.equalsIgnoreCase(host))) {
			requests.set("Host", host);
		}
	}

	private void writeConnectionHeader() {
		if (!failedOnce && http.getHttpKeepAliveSet()) {
			if (http.isUsingProxy() && tunnelState() != TunnelState.TUNNELING) {
				requests.setIfNotSet("Proxy-Connection", KEEP_ALIVE);
			} else {
				requests.setIfNotSet(CONNECTION, KEEP_ALIVE);
			}
		} else {
			requests.setIfNotSet(CONNECTION, "close");
		}
	}

	private void writeIfNotModified() {
		long modTime = getIfModifiedSince();

		if (modTime != 0) {
			Date date = new Date(modTime);
			SimpleDateFormat fo = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
			fo.setTimeZone(TimeZone.getTimeZone("GMT"));
			requests.setIfNotSet("If-Modified-Since", fo.format(date));
		}
	}

	private void writeAuthenticationHeader() {
		AuthenticationInfo sauth = AuthenticationInfo.getServerAuth(url);

		if (sauth != null && sauth.supportsPreemptiveAuthorization()) {
			requests.setIfNotSet(sauth.getHeaderName(), sauth.getHeaderValue(url, method));
			currentServerCredentials = sauth;
		}
	}

	private void writePostContentTypeIfEmpty() {
		if (!method.equals("PUT") && (poster != null || streaming())) {
			requests.setIfNotSet("Content-type", "application/x-www-form-urlencoded");
		}
	}

	private boolean doWriteBody() throws IOException {
		boolean chunked = false;
		if (streaming()) {
			if (chunkLength != -1) {
				requests.set(TRANSFER_ENCODING, "chunked");
				chunked = true;
			} else {
				if (fixedContentLengthLong != -1) {
					requests.set(CONTENT_LENGTH, String.valueOf(fixedContentLengthLong));
				} else if (fixedContentLength != -1) {
					requests.set(CONTENT_LENGTH, String.valueOf(fixedContentLength));
				}
			}
		} else if (poster != null) {
			synchronized (poster) {
				poster.close();
				requests.set(CONTENT_LENGTH, String.valueOf(poster.size()));
			}
		}
		return chunked;
	}

	private void handleBodyEncoding(boolean chunked) {
		if (!chunked && requests.findValue(TRANSFER_ENCODING) != null) {
			requests.remove(TRANSFER_ENCODING);
			if (logger.isWarnEnabled()) {
				logger.warn("use streaming mode for chunked encoding");
			}
		}
	}

	private void checkPrintStreamErrorAndRetry() throws IOException {
		if (printStream.checkError()) {
			String proxyHost = http.getProxyHostUsed();
			int proxyPort = http.getProxyPortUsed();
			disconnectInternal();
			if (failedOnce) {
				throw new IOException("Error writing to server");
			} else {
				failedOnce = true;
				if (proxyHost != null) {
					setProxiedClient(url, proxyHost, proxyPort);
				} else {
					setCreateNewClient(url);
				}
				printStream = (PrintStream) http.getOutputStream();
				connected = true;
				responses = new MessageHeader();
				setRequests = false;
				writeRequests();
			}
		}
	}

	protected void setCreateNewClient(URL url) throws IOException {
		setCreateNewClient(url, false);
	}

	protected void setCreateNewClient(URL url, boolean useCache) throws IOException {
		http = HttpClient.createNew(url, null, -1, useCache, connectTimeout, this);
		http.setReadTimeout(readTimeout);
	}

	protected void setProxiedClient(URL url, String proxyHost, int proxyPort) throws IOException {
		setProxiedClient(url, proxyHost, proxyPort, false);
	}

	protected void setProxiedClient(URL url, String proxyHost, int proxyPort, boolean useCache) throws IOException {
		proxiedConnect(url, proxyHost, proxyPort, useCache);
	}

	protected void proxiedConnect(URL url, String proxyHost, int proxyPort, boolean useCache) throws IOException {
		http = HttpClient.createNew(url, proxyHost, proxyPort, useCache, connectTimeout, this);
		http.setReadTimeout(readTimeout);
	}

	private static URL checkURL(URL u) throws IOException {
		if (u != null && u.toExternalForm().indexOf('\n') > -1) {
			throw new MalformedURLException("Illegal character in URL");
		}
		String s = IPAddressUtil.checkAuthority(u);
		if (s != null) {
			throw new MalformedURLException(s);
		}
		return u;
	}

	public HttpURLConnectionImpl(URL url, Proxy proxy, HttpHandler handler) throws IOException {
		super(checkURL(url));
		requests = new MessageHeader();
		responses = new MessageHeader();
		userHeaders = new MessageHeader();
		this.handler = handler;
		instanceProxy = proxy;
		cookieHandler = CookieHandler.getDefault();
		cacheHandler = ResponseCache.getDefault();
	}

	public static InputStream openConnectionCheckRedirects(URLConnection c) throws IOException {
		boolean redir;
		int redirects = 0;
		InputStream in;

		do {
			if (c instanceof HttpURLConnectionImpl) {
				((HttpURLConnectionImpl) c).setInstanceFollowRedirects(false);
			}

			in = c.getInputStream();
			redir = false;

			if (c instanceof HttpURLConnectionImpl) {
				HttpURLConnectionImpl http = (HttpURLConnectionImpl) c;
				int stat = http.getResponseCode();
				if (stat >= 300 && stat <= 307 && stat != 306 && stat != HttpURLConnection.HTTP_NOT_MODIFIED) {
					URL base = http.getURL();
					String loc = http.getHeaderField("Location");
					URL target = null;
					if (loc != null) {
						target = new URL(base, loc);
					}
					http.disconnect();
					checkIllegalRedirect(redirects, base, target);
					redir = true;
					c = target.openConnection();
					redirects++;
				}
			}
		} while (redir);
		return in;
	}

	private static void checkIllegalRedirect(int redirects, URL base, URL target) {
		if (target == null || !base.getProtocol().equals(target.getProtocol()) || base.getPort() != target.getPort()
				|| !hostsEqual(base, target) || redirects >= 5) {
			throw new SecurityException("illegal URL redirect");
		}
	}

	private static boolean hostsEqual(URL u1, URL u2) {
		final String h1 = u1.getHost();
		final String h2 = u2.getHost();

		if (h1 == null) {
			return h2 == null;
		} else if (h2 == null) {
			return false;
		} else if (h1.equalsIgnoreCase(h2)) {
			return true;
		}

		final boolean[] result = { false };

		try {
			InetAddress a1 = InetAddress.getByName(h1);
			InetAddress a2 = InetAddress.getByName(h2);
			result[0] = a1.equals(a2);
		} catch (UnknownHostException | SecurityException e) {
			// Do nothing
		}

		return result[0];
	}

	@Override
	public void connect() throws IOException {
		synchronized (this) {
			connecting = true;
		}
		plainConnect();
	}

	private boolean checkReuseConnection() {
		if (connected) {
			return true;
		}
		if (reuseClient != null) {
			http = reuseClient;
			http.setReadTimeout(getReadTimeout());
			http.setReuse(false);
			reuseClient = null;
			connected = true;
			return true;
		}
		return false;
	}

	protected void plainConnect() throws IOException {
		synchronized (this) {
			if (connected) {
				return;
			}
		}

		doPlainConnect();
	}

	protected void doPlainConnect() throws IOException {
		if (cacheHandler != null && getUseCaches()) {
			try {
				URI uri = URIUtil.toURI(url);
				if (uri != null) {
					cachedResponse = cacheHandler.get(uri, getRequestMethod(), getUserSetHeaders().getHeaders());
					if ("https".equalsIgnoreCase(uri.getScheme()) && !(cachedResponse instanceof SecureCacheResponse)) {
						cachedResponse = null;
					}

					if (logger.isTraceEnabled()) {
						logger.trace("Cache Request for {}  /  {}", uri, getRequestMethod());
						logger.trace("From cache: {} ", (cachedResponse != null ? cachedResponse.toString() : "null"));
					}

					if (cachedResponse != null) {
						cachedHeaders = mapToMessageHeader(cachedResponse.getHeaders());
						cachedInputStream = cachedResponse.getBody();
					}
				}
			} catch (IOException ioex) {
				// Do nothing
			}

			if (cachedHeaders != null && cachedInputStream != null) {
				connected = true;
				return;
			} else {
				cachedResponse = null;
			}
		}
		if (instanceProxy == null) {

			ProxySelector selector = ProxySelector.getDefault();
			if (selector != null) {
				URI uri = URIUtil.toURI(url);
				if (logger.isTraceEnabled()) {
					logger.trace("ProxySelector Request for {}", uri);
				}
				Iterator<Proxy> it = selector.select(uri).iterator();
				Proxy p;
				while (it.hasNext()) {
					p = it.next();
					try {
						if (!failedOnce) {
							http = getCreateNewHttpClient(url, p, connectTimeout);
							http.setReadTimeout(readTimeout);
						} else {
							http = getCreateNewHttpClient(url, p, connectTimeout, false);
							http.setReadTimeout(readTimeout);
						}
						if (logger.isTraceEnabled() && p != null) {
							logger.trace("Proxy used: {}", p);
						}

						break;
					} catch (IOException ioex) {
						if (p != Proxy.NO_PROXY) {
							selector.connectFailed(uri, p.address(), ioex);
							if (!it.hasNext()) {
								http = getCreateNewHttpClient(url, null, connectTimeout, false);
								http.setReadTimeout(readTimeout);
								break;
							}
						} else {
							throw ioex;
						}
					}
				}
			} else {
				if (!failedOnce) {
					http = getCreateNewHttpClient(url, null, connectTimeout);
					http.setReadTimeout(readTimeout);
				} else {
					http = getCreateNewHttpClient(url, null, connectTimeout, false);
					http.setReadTimeout(readTimeout);
				}
			}
		} else {
			if (!failedOnce) {
				http = getCreateNewHttpClient(url, instanceProxy, connectTimeout);
				http.setReadTimeout(readTimeout);
			} else {
				http = getCreateNewHttpClient(url, instanceProxy, connectTimeout, false);
				http.setReadTimeout(readTimeout);
			}
		}

		printStream = (PrintStream) http.getOutputStream();
		connected = true;
	}

	protected HttpClient getCreateNewHttpClient(URL url, Proxy p, int connectTimeout) throws IOException {
		return HttpClient.createNew(url, p, connectTimeout, this);
	}

	protected HttpClient getCreateNewHttpClient(URL url, Proxy p, int connectTimeout, boolean useCache)
			throws IOException {
		return HttpClient.createNew(url, p, connectTimeout, useCache, this);
	}

	private void expect100Continue() throws IOException {
		int oldTimeout = http.getReadTimeout();
		boolean enforceTimeOut = false;
		boolean timedOut = false;
		if (oldTimeout <= 0) {
			http.setReadTimeout(5000);
			enforceTimeOut = true;
		}

		try {
			http.parseHTTP(responses, progressSource, this);
		} catch (SocketTimeoutException se) {
			if (!enforceTimeOut) {
				throw se;
			}
			timedOut = true;
			http.setIgnoreContinue(true);
		}
		if (!timedOut) {
			String responseLineString = responses.getValue(0);
			if (responseLineString != null && responseLineString.startsWith("HTTP/")) {
				String[] responseLineParts = responseLineString.split("\\s+");
				responseCode = -1;
				try {
					if (responseLineParts.length > 1)
						responseCode = Integer.parseInt(responseLineParts[1]);
				} catch (NumberFormatException numberFormatException) {
					//Do nothing
				}
			}
			if (responseCode != 100) {
				throw new ProtocolException("Server rejected operation");
			}
		}

		http.setReadTimeout(oldTimeout);

		responseCode = -1;
		responses.reset();
	}

	@Override
	public synchronized OutputStream getOutputStream() throws IOException {
		connecting = true;
		return doGetOutputStream();
	}

	@SuppressWarnings("resource")
	private synchronized OutputStream doGetOutputStream() throws IOException {
		try {
			if (!doOutput) {
				throw new ProtocolException(
						"cannot write to a URLConnection" + " if doOutput=false - call setDoOutput(true)");
			}

			if ("TRACE".equals(method) && "http".equals(url.getProtocol())) {
				throw new ProtocolException("HTTP method TRACE" + " doesn't support output");
			}

			if (inputStream != null) {
				throw new ProtocolException("Cannot write output after reading input.");
			}

			if (!checkReuseConnection())
				connect();

			boolean expectContinue = false;
			String expects = requests.findValue("Expect");
			if ("100-Continue".equalsIgnoreCase(expects) && streaming()) {
				http.setIgnoreContinue(false);
				expectContinue = true;
			}

			if (streaming() && streamingOutputStream == null) {
				writeRequests();
			}

			if (expectContinue) {
				expect100Continue();
			}
			printStream = (PrintStream) http.getOutputStream();
			if (streaming()) {
				if (streamingOutputStream == null) {
					if (chunkLength != -1) {
						streamingOutputStream = new StreamingOutputStream(new ChunkedOutputStream(printStream, chunkLength),
								-1L);
					} else {
						long length = 0L;
						if (fixedContentLengthLong != -1) {
							length = fixedContentLengthLong;
						} else if (fixedContentLength != -1) {
							length = fixedContentLength;
						}
						streamingOutputStream = new StreamingOutputStream(printStream, length);
					}
				}
				return streamingOutputStream;
			} else {
				if (poster == null) {
					poster = new PosterOutputStream();
				}
				return poster;
			}
		} catch (ProtocolException e) {
			int i = responseCode;
			disconnectInternal();
			responseCode = i;
			throw e;
		} catch (IOException | RuntimeException e) {
			disconnectInternal();
			throw e;
		}
	}

	public boolean streaming() {
		return (fixedContentLength != -1) || (fixedContentLengthLong != -1) || (chunkLength != -1);
	}

	private void setCookieHeader() throws IOException {
		if (cookieHandler != null) {
			synchronized (this) {
				if (setUserCookies) {
					int k = requests.getKey(COOKIE);
					if (k != -1)
						userCookies = requests.getValue(k);
					k = requests.getKey(COOKIE2);
					if (k != -1)
						userCookies2 = requests.getValue(k);
					setUserCookies = false;
				}
			}

			requests.remove(COOKIE);
			requests.remove(COOKIE2);

			URI uri = URIUtil.toURI(url);
			if (uri != null) {
				if (logger.isTraceEnabled()) {
					logger.trace("CookieHandler request for {} ", uri);
				}
				Map<String, List<String>> cookies = cookieHandler.get(uri, requests.getHeaders(EXCLUDE_HEADERS));
				if (!cookies.isEmpty()) {
					if (logger.isTraceEnabled()) {
						logger.trace("Cookies retrieved: {}", cookies);
					}

					for (Map.Entry<String, List<String>> entry : cookies.entrySet()) {
						String key = entry.getKey();

						if (!COOKIE.equalsIgnoreCase(key) && !COOKIE2.equalsIgnoreCase(key)) {
							continue;
						}

						List<String> l = entry.getValue();
						if (l != null && !l.isEmpty()) {
							StringBuilder cookieValue = new StringBuilder();
							for (String value : l) {
								cookieValue.append(value).append("; ");
							}

							try {
								requests.add(key, cookieValue.substring(0, cookieValue.length() - 2));
							} catch (StringIndexOutOfBoundsException ignored) {
								// Do nothing
							}
						}
					}
				}
			}

			if (userCookies != null) {
				int k;
				if ((k = requests.getKey(COOKIE)) != -1)
					requests.set(COOKIE, requests.getValue(k) + ";" + userCookies);
				else
					requests.set(COOKIE, userCookies);
			}

			if (userCookies2 != null) {
				int k;
				if ((k = requests.getKey(COOKIE2)) != -1)
					requests.set(COOKIE2, requests.getValue(k) + ";" + userCookies2);
				else
					requests.set(COOKIE2, userCookies2);
			}

		}
	}

	@Override
	public synchronized InputStream getInputStream() throws IOException {
		connecting = true;
		return doGetInputStream();
	}

	private synchronized InputStream doGetInputStream() throws IOException {

		if (!doInput) {
			throw new ProtocolException("Cannot read from URLConnection if doInput=false (call setDoInput(true))");
		}

		if (rememberedException != null) {
			if (rememberedException instanceof RuntimeException)
				throw new HttpClientRuntimeException(rememberedException);
			else {
				throw getChainedException((IOException) rememberedException);
			}
		}

		if (inputStream != null) {
			return inputStream;
		}

		if (streaming()) {
			if (streamingOutputStream == null) {
				getOutputStream();
			}

			streamingOutputStream.close();
			if (!streamingOutputStream.writtenOK()) {
				throw new IOException("Incomplete output stream");
			}
		}

		int redirects = 0;
		int respCode = 0;
		long cl = -1;
		AuthenticationInfo serverAuthentication = null;
		AuthenticationInfo proxyAuthentication = null;
		AuthenticationHeader serverHeader = null;

		isUserServerAuth = requests.getKey(AUTHORIZATION) != -1;
		isUserProxyAuth = requests.getKey(PROXY_AUTHORIZATION) != -1;

		try {
			do {
				if (!checkReuseConnection())
					connect();

				if (cachedInputStream != null) {
					return cachedInputStream;
				}

				boolean meteredInput = ProgressMonitor.getDefault().shouldMeterInput(url, method);

				if (meteredInput) {
					progressSource = new ProgressSource(url, method);
					progressSource.beginTracking();
				}

				printStream = (PrintStream) http.getOutputStream();

				if (!streaming()) {
					writeRequests();
				}

				http.parseHTTP(responses, progressSource, this);

				if (logger.isDebugEnabled()) {
					logger.debug(responses.toString());
				}

				inputStream = http.getInputStream();

				respCode = getResponseCode();
				if (respCode == -1) {
					disconnectInternal();
					throw new IOException("Invalid Http response");
				}

				if (respCode == HTTP_PROXY_AUTH) {
					if (streaming()) {
						disconnectInternal();
						throw new HttpRetryException(RETRY_MSG1, HTTP_PROXY_AUTH);
					}

					AuthenticationHeader authhdr = new AuthenticationHeader(PROXY_AUTHENTICATE, responses,
							DISABLED_PROXYING_SCHEMES);

					proxyAuthentication = resetProxyAuthentication(proxyAuthentication, authhdr);
					if (proxyAuthentication != null) {
						redirects++;
						disconnectInternal();
						continue;
					}
				} else {
					if (!isUserProxyAuth)
						requests.remove(PROXY_AUTHORIZATION);
				}

				if (proxyAuthentication != null) {
					proxyAuthentication.addToCache();
				}

				if (respCode == HTTP_UNAUTHORIZED) {
					if (streaming()) {
						disconnectInternal();
						throw new HttpRetryException(RETRY_MSG2, HTTP_UNAUTHORIZED);
					}

					serverHeader = new AuthenticationHeader(WWW_AUTHENTICATE, responses);

					String raw = serverHeader.raw();
					if (serverAuthentication != null) {
						if (serverAuthentication.isAuthorizationStale(raw)) {
							disconnectWeb();
							redirects++;
							requests.set(serverAuthentication.getHeaderName(),
									serverAuthentication.getHeaderValue(url, method));
							currentServerCredentials = serverAuthentication;
							setCookieHeader();
							continue;
						} else {
							serverAuthentication.removeFromCache();
						}
					}
					serverAuthentication = getServerAuthentication(serverHeader);
					currentServerCredentials = serverAuthentication;

					if (serverAuthentication != null) {
						disconnectWeb();
						redirects++;
						setCookieHeader();
						continue;
					}
				}

				if (serverAuthentication != null) {
					if (!(serverAuthentication instanceof DigestAuthentication) || (domain == null)) {
						if (serverAuthentication instanceof BasicAuthentication) {
							String npath = AuthenticationInfo.reducePath(url.getPath());
							String opath = serverAuthentication.path;
							if (!opath.startsWith(npath) || npath.length() >= opath.length()) {
								npath = BasicAuthentication.getRootPath(opath, npath);
							}

							BasicAuthentication a = new BasicAuthentication(serverAuthentication);
							serverAuthentication.removeFromCache();
							a.path = npath;
							serverAuthentication = a;
						}
						serverAuthentication.addToCache();
					} else {
						DigestAuthentication server = (DigestAuthentication) serverAuthentication;
						StringTokenizer tokenizer = new StringTokenizer(domain, " ");
						String realm = server.realm;
						PasswordAuthentication pw = server.passwordAuthentication;
						digestparams = server.params;
						while (tokenizer.hasMoreTokens()) {
							String path = tokenizer.nextToken();
							try {
								URL u = new URL(url, path);
								DigestAuthentication d = new DigestAuthentication(false, u, realm, "Digest", pw,
										digestparams);
								d.addToCache();
							} catch (Exception e) {
								//Do nothing
							}
						}
					}
				}

				if (!isUserServerAuth)
					requests.remove(AUTHORIZATION);
				if (!isUserProxyAuth)
					requests.remove(PROXY_AUTHORIZATION);

				if (respCode == HTTP_OK) {
					checkResponseCredentials(false);
				} else {
					needToCheck = false;
				}

				needToCheck = true;

				if (followRedirect()) {
					redirects++;
					setCookieHeader();

					continue;
				}

				try {
					cl = Long.parseLong(responses.findValue("content-length"));
				} catch (NumberFormatException exc) {
					// Do nothing
				}

				if (method.equals("HEAD") || cl == 0 || respCode == HTTP_NOT_MODIFIED || respCode == HTTP_NO_CONTENT) {

					if (progressSource != null) {
						progressSource.finishTracking();
						progressSource = null;
					}
					http.finished();
					http = null;
					inputStream = new EmptyInputStream();
					connected = false;
				}

				if ((respCode == 200 || respCode == 203 || respCode == 206 || respCode == 300 || respCode == 301
						|| respCode == 410) && cacheHandler != null && getUseCaches()) {

					URI uri = URIUtil.toURI(url);
					if (uri != null) {
						URLConnection urlConnectionToCache = this;
						if (getAlternateURLConnection()!=null) {
							urlConnectionToCache = getAlternateURLConnection();
						}

						CacheRequest cacheRequest = cacheHandler.put(uri, urlConnectionToCache);

						if (cacheRequest != null && http != null) {
							http.setCacheRequest(cacheRequest);
							inputStream = new HttpInputStream(inputStream, cacheRequest);
						}
					}
				}

				if (!(inputStream instanceof HttpInputStream)) {
					inputStream = new HttpInputStream(inputStream);
				}

				if (respCode >= 400) {
					if (respCode == 404 || respCode == 410) {
						throw new FileNotFoundException(url.toString());
					} else {
						throw new IOException(
								"Server returned HTTP" + " response code: " + respCode + " for URL: " + url.toString());
					}
				}
				poster = null;
				streamingOutputStream = null;
				return inputStream;
			} while (redirects < MAX_REDIRECTS);

			throw new ProtocolException("Server redirected too many " + " times (" + redirects + ")");
		} catch (RuntimeException e) {
			disconnectInternal();
			rememberedException = e;
			throw e;
		} catch (IOException e) {
			rememberedException = e;

			String te = responses.findValue(TRANSFER_ENCODING);
			if (http != null && http.isKeepingAlive() && enableESBuffer
					&& (cl > 0 || (te != null && te.equalsIgnoreCase("chunked")))) {
				errorStream = ErrorStream.getErrorStream(inputStream, cl, http);
			}
			throw e;
		} finally {
			if (proxyAuthKey != null) {
				AuthenticationInfo.endAuthRequest(proxyAuthKey);
			}
			if (serverAuthKey != null) {
				AuthenticationInfo.endAuthRequest(serverAuthKey);
			}
		}
	}

	protected URLConnection getAlternateURLConnection() {
		return null;
	}
	
	private IOException getChainedException(final IOException rememberedException) {
		try {
			final Object[] args = { rememberedException.getMessage() };
			IOException chainedException = rememberedException.getClass().getConstructor(String.class)
					.newInstance(args);
			chainedException.initCause(rememberedException);
			return chainedException;
		} catch (Exception ignored) {
			return rememberedException;
		}
	}

	@Override
	public InputStream getErrorStream() {
		if (connected && responseCode >= 400) {
			if (errorStream != null) {
				return errorStream;
			} else if (inputStream != null) {
				return inputStream;
			}
		}
		return null;
	}

	private AuthenticationInfo resetProxyAuthentication(AuthenticationInfo proxyAuthentication,
			AuthenticationHeader auth) throws IOException {
		if (proxyAuthentication != null) {
			String raw = auth.raw();
			if (proxyAuthentication.isAuthorizationStale(raw)) {
				String value;
				if (proxyAuthentication instanceof DigestAuthentication) {
					DigestAuthentication digestProxy = (DigestAuthentication) proxyAuthentication;
					if (tunnelState() == TunnelState.SETUP) {
						value = digestProxy.getHeaderValue(connectRequestURI(url), HTTP_CONNECT);
					} else {
						value = digestProxy.getHeaderValue(getRequestURI(), method);
					}
				} else {
					value = proxyAuthentication.getHeaderValue(url, method);
				}
				requests.set(proxyAuthentication.getHeaderName(), value);
				currentProxyCredentials = proxyAuthentication;
				return proxyAuthentication;
			} else {
				proxyAuthentication.removeFromCache();
			}
		}
		proxyAuthentication = getHttpProxyAuthentication(auth);
		currentProxyCredentials = proxyAuthentication;
		return proxyAuthentication;
	}

	protected TunnelState tunnelState() {
		return tunnelState;
	}

	public void setTunnelState(TunnelState tunnelState) {
		this.tunnelState = tunnelState;
	}

	public synchronized void doTunneling() throws IOException {
		int retryTunnel = 0;
		String statusLine = "";
		int respCode = 0;
		AuthenticationInfo proxyAuthentication = null;
		String proxyHost = null;
		int proxyPort = -1;

		MessageHeader savedRequests = requests;
		requests = new MessageHeader();

		try {
			setTunnelState(TunnelState.SETUP);

			do {
				if (!checkReuseConnection()) {
					proxiedConnect(url, proxyHost, proxyPort, false);
				}

				sendCONNECTRequest();
				responses.reset();

				http.parseHTTP(responses, null, this);

				if (logger.isDebugEnabled()) {
					logger.debug(responses.toString());
				}

				statusLine = responses.getValue(0);
				StringTokenizer stringTokenizer = new StringTokenizer(statusLine);
				stringTokenizer.nextToken();
				respCode = Integer.parseInt(stringTokenizer.nextToken().trim());
				if (respCode == HTTP_PROXY_AUTH) {
					AuthenticationHeader authhdr = new AuthenticationHeader(PROXY_AUTHENTICATE, responses,
							DISABLED_TUNNELING_SCHEMES);
					proxyAuthentication = resetProxyAuthentication(proxyAuthentication, authhdr);
					if (proxyAuthentication != null) {
						proxyHost = http.getProxyHostUsed();
						proxyPort = http.getProxyPortUsed();
						disconnectInternal();
						retryTunnel++;
						continue;
					}
				}

				if (proxyAuthentication != null) {
					proxyAuthentication.addToCache();
				}

				if (respCode == HTTP_OK) {
					setTunnelState(TunnelState.TUNNELING);
					break;
				}

				disconnectInternal();
				setTunnelState(TunnelState.NONE);
				break;
			} while (retryTunnel < MAX_REDIRECTS);

			if (retryTunnel >= MAX_REDIRECTS || (respCode != HTTP_OK)) {
				if (respCode != HTTP_PROXY_AUTH) {
					responses.reset();
				}
				throw new IOException("Unable to tunnel through proxy." + " Proxy returns \"" + statusLine + "\"");
			}
		} finally {
			if (proxyAuthKey != null) {
				AuthenticationInfo.endAuthRequest(proxyAuthKey);
			}
		}

		requests = savedRequests;

		responses.reset();
	}

	static String connectRequestURI(URL url) {
		String host = url.getHost();
		int port = url.getPort();
		port = port != -1 ? port : url.getDefaultPort();

		return host + ":" + port;
	}

	private void sendCONNECTRequest() throws IOException {
		int port = url.getPort();

		requests.set(0, HTTP_CONNECT + " " + connectRequestURI(url) + " " + HTTP_VERSION, null);
		requests.setIfNotSet("User-Agent", USER_AGENT);

		String host = url.getHost();
		if (port != -1 && port != url.getDefaultPort()) {
			host += ":" + port;
		}

		requests.setIfNotSet("Host", host);

		requests.setIfNotSet("Accept", ACCEPT_STRING);

		if (http.getHttpKeepAliveSet()) {
			requests.setIfNotSet("Proxy-Connection", KEEP_ALIVE);
		}

		setPreemptiveProxyAuthentication(requests);

		if (logger.isDebugEnabled()) {
			logger.debug(requests.toString());
		}

		http.writeRequests(requests, null);
	}

	private void setPreemptiveProxyAuthentication(MessageHeader requests) throws IOException {
		AuthenticationInfo pauth = AuthenticationInfo.getProxyAuth(http.getProxyHostUsed(), http.getProxyPortUsed());
		if (pauth != null && pauth.supportsPreemptiveAuthorization()) {
			String value;
			if (pauth instanceof DigestAuthentication) {
				DigestAuthentication digestProxy = (DigestAuthentication) pauth;
				if (tunnelState() == TunnelState.SETUP) {
					value = digestProxy.getHeaderValue(connectRequestURI(url), HTTP_CONNECT);
				} else {
					value = digestProxy.getHeaderValue(getRequestURI(), method);
				}
			} else {
				value = pauth.getHeaderValue(url, method);
			}

			requests.set(pauth.getHeaderName(), value);
			currentProxyCredentials = pauth;
		}
	}

	private AuthenticationInfo getHttpProxyAuthentication(AuthenticationHeader authenticationHeader) throws UnknownHostException {
		AuthenticationInfo ret = null;
		String raw = authenticationHeader.raw();
		String host = http.getProxyHostUsed();
		int port = http.getProxyPortUsed();
		if (host != null && authenticationHeader.hasPreferredParserPresent()) {
			HeaderParser p = authenticationHeader.headerParser();
			String realm = p.findValue("realm");
			String scheme = authenticationHeader.scheme();
			AuthScheme authScheme = UNKNOWN;
			if ("basic".equalsIgnoreCase(scheme)) {
				authScheme = BASIC;
			} else if ("digest".equalsIgnoreCase(scheme)) {
				authScheme = DIGEST;
			}

			if (realm == null)
				realm = "";
			proxyAuthKey = AuthenticationInfo.getProxyAuthKey(host, port, realm, authScheme);
			ret = AuthenticationInfo.getProxyAuth(proxyAuthKey);
			if (ret == null) {
				switch (authScheme) {
				case BASIC:
					InetAddress addr = null;
					final String finalHost = host;
					addr = InetAddress.getByName(finalHost);
					PasswordAuthentication passwordAuthentication = requestPasswordAuthentication(
							new PasswordParameters().withHostAs(host).withAddrAs(addr).withPortAs(port)
									.withProtocolAs("http").withPromptAs(realm).withSchemeAs(scheme).withUrlAs(url)
									.withAuthTypeAs(RequestorType.PROXY));
					if (passwordAuthentication != null) {
						ret = new BasicAuthentication(true, host, port, realm, passwordAuthentication);
					}
					break;
				case DIGEST:
					passwordAuthentication = requestPasswordAuthentication(new PasswordParameters().withHostAs(host)
							.withPortAs(port).withProtocolAs(url.getProtocol()).withPromptAs(realm).withSchemeAs(scheme)
							.withUrlAs(url).withAuthTypeAs(RequestorType.PROXY));
					if (passwordAuthentication != null) {
						DigestAuthentication.Parameters params = new DigestAuthentication.Parameters();
						ret = new DigestAuthentication(true, host, port, realm, scheme, passwordAuthentication, params);
					}
					break;
				case UNKNOWN:
					if (logger.isTraceEnabled()) {
						logger.trace("Unknown/Unsupported authentication scheme: {}", scheme);
					}
					throw new HttpClientRuntimeException("Unsupported scheme: " + scheme);
				}
			}

			if (ret != null && !ret.setHeaders(this, p, raw)) {
				ret = null;
			}
		}
		if (logger.isTraceEnabled()) {
			logger.trace("Proxy Authentication for {} returned {}", authenticationHeader, (ret != null ? ret.toString() : "null"));
		}
		return ret;
	}

	private AuthenticationInfo getServerAuthentication(AuthenticationHeader authenticationHeader) {
		AuthenticationInfo ret = null;
		String raw = authenticationHeader.raw();
		if (authenticationHeader.hasPreferredParserPresent()) {
			HeaderParser parser = authenticationHeader.headerParser();
			String realm = parser.findValue("realm");
			String scheme = authenticationHeader.scheme();
			AuthScheme authScheme = UNKNOWN;
			if ("basic".equalsIgnoreCase(scheme)) {
				authScheme = BASIC;
			} else if ("digest".equalsIgnoreCase(scheme)) {
				authScheme = DIGEST;
			}

			domain = parser.findValue("domain");
			if (realm == null)
				realm = "";
			serverAuthKey = AuthenticationInfo.getServerAuthKey(url, realm, authScheme);
			ret = AuthenticationInfo.getServerAuth(serverAuthKey);
			InetAddress addr = null;
			if (ret == null) {
				try {
					addr = InetAddress.getByName(url.getHost());
				} catch (java.net.UnknownHostException ignored) {
					// Do nothing
				}
			}

			int port = url.getPort();
			if (port == -1) {
				port = url.getDefaultPort();
			}
			if (ret == null) {
				switch (authScheme) {
				case BASIC:
					PasswordAuthentication passwordAuthentication = requestPasswordAuthentication(
							new PasswordParameters().withHostAs(url.getHost()).withAddrAs(addr).withPortAs(port)
									.withProtocolAs(url.getProtocol()).withPromptAs(scheme).withSchemeAs(scheme)
									.withUrlAs(url).withAuthTypeAs(RequestorType.SERVER));
					if (passwordAuthentication != null) {
						ret = new BasicAuthentication(false, url, realm, passwordAuthentication);
					}
					break;
				case DIGEST:
					passwordAuthentication = requestPasswordAuthentication(
							new PasswordParameters().withHostAs(url.getHost()).withAddrAs(addr).withPortAs(port)
									.withProtocolAs(url.getProtocol()).withPromptAs(realm).withSchemeAs(scheme)
									.withUrlAs(url).withAuthTypeAs(RequestorType.SERVER));
					if (passwordAuthentication != null) {
						digestparams = new DigestAuthentication.Parameters();
						ret = new DigestAuthentication(false, url, realm, scheme, passwordAuthentication, digestparams);
					}
					break;
				case UNKNOWN:
					if (logger.isTraceEnabled()) {
						logger.trace("Unknown/Unsupported authentication scheme: {}", scheme);
					}
					throw new HttpClientRuntimeException("Unsupported scheme: " + scheme);
				}
			}

			if (ret != null && !ret.setHeaders(this, parser, raw)) {
				ret = null;
			}
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Server Authentication for {}  returned {}", authenticationHeader, (ret != null ? ret.toString() : "null"));
		}
		return ret;
	}

	private void checkResponseCredentials(boolean inClose) throws IOException {
		try {
			if (!needToCheck)
				return;
			if ((VALIDATE_PROXY && currentProxyCredentials != null)
					&& (currentProxyCredentials instanceof DigestAuthentication)) {
				String raw = responses.findValue("Proxy-Authentication-Info");
				if (inClose || (raw != null)) {
					DigestAuthentication da = (DigestAuthentication) currentProxyCredentials;
					da.checkResponse(raw, method, getRequestURI());
					currentProxyCredentials = null;
				}
			}
			if ((VALIDATE_SERVER && currentServerCredentials != null)
					&& (currentServerCredentials instanceof DigestAuthentication)) {
				String raw = responses.findValue("Authentication-Info");
				if (inClose || (raw != null)) {
					DigestAuthentication da = (DigestAuthentication) currentServerCredentials;
					da.checkResponse(raw, method, url);
					currentServerCredentials = null;
				}
			}
			if ((currentServerCredentials == null) && (currentProxyCredentials == null)) {
				needToCheck = false;
			}
		} catch (IOException e) {
			disconnectInternal();
			connected = false;
			throw e;
		}
	}

	String requestURI = null;

	String getRequestURI() throws IOException {
		if (requestURI == null) {
			requestURI = http.getURLFile();
		}
		return requestURI;
	}

	private boolean followRedirect() throws IOException {
		if (!getInstanceFollowRedirects()) {
			return false;
		}

		final int stat = getResponseCode();

		if (stat < 300 || stat > 307 || stat == 306 || stat == HTTP_NOT_MODIFIED) {
			return false;
		}

		final String loc = getHeaderField("Location");

		if (loc == null) {
			return false;
		}

		URL locUrl;
		try {
			locUrl = new URL(loc);
			if (!url.getProtocol().equalsIgnoreCase(locUrl.getProtocol())) {
				return false;
			}

		} catch (MalformedURLException mue) {
			locUrl = new URL(url, loc);
		}

		return doFollowRedirect(loc, stat, locUrl);
	}

	private boolean doFollowRedirect(String loc, int stat, URL locUrl) throws IOException {
		disconnectInternal();
		if (streaming()) {
			throw new HttpRetryException(RETRY_MSG3, stat, loc);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Redirected from {} to {}", url, locUrl);
		}

		responses = new MessageHeader();
		if (stat == HTTP_USE_PROXY) {
			String proxyHost = locUrl.getHost();
			int proxyPort = locUrl.getPort();

			SecurityManager security = System.getSecurityManager();
			if (security != null) {
				security.checkConnect(proxyHost, proxyPort);
			}

			setProxiedClient(url, proxyHost, proxyPort);
			requests.set(0, method + " " + getRequestURI() + " " + HTTP_VERSION, null);
			connected = true;
		} else {
			final URL prevURL = url;

			url = locUrl;
			requestURI = null;
			if (method.equals("POST") && !Boolean.getBoolean("com.quakearts.net.http.strictPostRedirect")
					&& (stat != 307)) {

				requests = new MessageHeader();
				setRequests = false;
				super.setRequestMethod("GET");
				poster = null;
				if (!checkReuseConnection())
					connect();

				if (!sameDestination(prevURL, url)) {
					userCookies = null;
					userCookies2 = null;
				}
			} else {
				if (!checkReuseConnection())
					connect();

				if (http != null) {
					requests.set(0, method + " " + getRequestURI() + " " + HTTP_VERSION, null);
					int port = url.getPort();
					String host = url.getHost();
					if (port != -1 && port != url.getDefaultPort()) {
						host += ":" + port;
					}
					requests.set("Host", host);
				}

				if (!sameDestination(prevURL, url)) {
					userCookies = null;
					userCookies2 = null;
					requests.remove(COOKIE);
					requests.remove(COOKIE2);
					requests.remove(AUTHORIZATION);

					writeAuthenticationHeader();
				}
			}
		}
		return true;
	}

	private static boolean sameDestination(URL firstURL, URL secondURL) {
		assert firstURL.getProtocol().equalsIgnoreCase(secondURL.getProtocol())
				: "protocols not equal: " + firstURL + " - " + secondURL;

		if (!firstURL.getHost().equalsIgnoreCase(secondURL.getHost()))
			return false;

		int firstPort = firstURL.getPort();
		if (firstPort == -1)
			firstPort = firstURL.getDefaultPort();
		int secondPort = secondURL.getPort();
		if (secondPort == -1)
			secondPort = secondURL.getDefaultPort();
		return (firstPort != secondPort);
	}

	byte[] cdata = new byte[128];

	private void reset() throws IOException {
		http.setReuse(true);

		reuseClient = http;
		InputStream is = http.getInputStream();
		if (!method.equals("HEAD")) {
			try {
				if ((is instanceof ChunkedInputStream) || (is instanceof MeteredStream)) {
					while (is.read(cdata) > 0)
						;
				} else {
					long cl = 0;
					int n = 0;
					String cls = responses.findValue(CONTENT_LENGTH);
					if (cls != null) {
						try {
							cl = Long.parseLong(cls);
						} catch (NumberFormatException e) {
							cl = 0;
						}
					}
					for (long i = 0; i < cl;) {
						if ((n = is.read(cdata)) == -1) {
							break;
						} else {
							i += n;
						}
					}
				}
			} catch (IOException e) {
				http.setReuse(false);
				reuseClient = null;
				disconnectInternal();
				return;
			}
			try {
				if (is instanceof MeteredStream) {
					is.close();
				}
			} catch (IOException e) {
				// Do nothing
			}
		}
		responseCode = -1;
		responses = new MessageHeader();
		connected = false;
	}

	private void disconnectWeb() throws IOException {
		if (usingProxy() && http.isKeepingAlive()) {
			responseCode = -1;
			reset();
		} else {
			disconnectInternal();
		}
	}

	private void disconnectInternal() {
		responseCode = -1;
		inputStream = null;
		if (progressSource != null) {
			progressSource.finishTracking();
			progressSource = null;
		}
		if (http != null) {
			http.closeServer();
			http = null;
			connected = false;
		}
	}

	@Override
	public void disconnect() {

		responseCode = -1;
		if (progressSource != null) {
			progressSource.finishTracking();
			progressSource = null;
		}

		if (http != null) {
			if (inputStream != null) {
				HttpClient hc = http;

				boolean ka = hc.isKeepingAlive();

				try {
					inputStream.close();
				} catch (IOException e) {
					// Do nothing
				}

				if (ka) {
					hc.closeIdleConnection();
				}

			} else {
				http.setDoNotRetry(true);
				http.closeServer();
			}
			http = null;
			connected = false;
		}
		cachedInputStream = null;
		if (cachedHeaders != null) {
			cachedHeaders.reset();
		}
	}

	public boolean usingProxy() {
		if (http != null) {
			return (http.getProxyHostUsed() != null);
		}
		return false;
	}

	private static final String SET_COOKIE = "set-cookie";
	private static final String SET_COOKIE2 = "set-cookie2";

	private String filterHeaderField(String name, String value) {
		if (value == null)
			return null;

		if (SET_COOKIE.equalsIgnoreCase(name) || SET_COOKIE2.equalsIgnoreCase(name)) {

			if (cookieHandler == null || value.length() == 0)
				return value;

			StringBuilder retValue = new StringBuilder();
			List<HttpCookie> cookies = HttpCookie.parse(value);
			boolean multipleCookies = false;
			for (HttpCookie cookie : cookies) {
				if (cookie.isHttpOnly())
					continue;
				if (multipleCookies)
					retValue.append(',');
				retValue.append(cookie.getHeader());
				multipleCookies = true;
			}

			return retValue.length() == 0 ? "" : retValue.toString();
		}

		return value;
	}

	private Map<String, List<String>> filteredHeaders;

	private Map<String, List<String>> getFilteredHeaderFields() {
		if (filteredHeaders != null)
			return filteredHeaders;

		Map<String, List<String>> headers;
		Map<String, List<String>> tempMap = new HashMap<>();

		if (cachedHeaders != null)
			headers = cachedHeaders.getHeaders();
		else
			headers = responses.getHeaders();

		for (Map.Entry<String, List<String>> e : headers.entrySet()) {
			String key = e.getKey();
			List<String> values = e.getValue();
			List<String> filteredVals = new ArrayList<>();
			for (String value : values) {
				String fVal = filterHeaderField(key, value);
				if (fVal != null)
					filteredVals.add(fVal);
			}
			if (!filteredVals.isEmpty())
				tempMap.put(key, Collections.unmodifiableList(filteredVals));
		}

		filteredHeaders = Collections.unmodifiableMap(tempMap);
		return filteredHeaders;
	}

	@Override
	public String getHeaderField(String name) {
		try {
			getInputStream();
		} catch (IOException e) {
			// Do nothing
		}

		if (cachedHeaders != null) {
			return filterHeaderField(name, cachedHeaders.findValue(name));
		}

		return filterHeaderField(name, responses.findValue(name));
	}

	@Override
	public Map<String, List<String>> getHeaderFields() {
		try {
			getInputStream();
		} catch (IOException e) {
			// Do nothing
		}

		return getFilteredHeaderFields();
	}

	@Override
	public String getHeaderField(int n) {
		try {
			getInputStream();
		} catch (IOException e) {
			//Do nothing
		}

		if (cachedHeaders != null) {
			return filterHeaderField(cachedHeaders.getKey(n), cachedHeaders.getValue(n));
		}
		return filterHeaderField(responses.getKey(n), responses.getValue(n));
	}

	@Override
	public String getHeaderFieldKey(int n) {
		try {
			getInputStream();
		} catch (IOException e) {
			//Do nothing
		}

		if (cachedHeaders != null) {
			return cachedHeaders.getKey(n);
		}

		return responses.getKey(n);
	}

	@Override
	public synchronized void setRequestProperty(String key, String value) {
		if (connected || connecting)
			throw new IllegalStateException(ALREADY_CONNECTED);
		if (key == null)
			throw new NullPointerException("key is null");

		if (isExternalMessageHeaderAllowed(key, value)) {
			requests.set(key, value);
			if (!key.equalsIgnoreCase("Content-Type")) {
				userHeaders.set(key, value);
			}
		}
	}

	MessageHeader getUserSetHeaders() {
		return userHeaders;
	}

	@Override
	public synchronized void addRequestProperty(String key, String value) {
		if (connected || connecting)
			throw new IllegalStateException(ALREADY_CONNECTED);
		if (key == null)
			throw new NullPointerException("key is null");

		if (isExternalMessageHeaderAllowed(key, value)) {
			requests.add(key, value);
			if (!key.equalsIgnoreCase("Content-Type")) {
				userHeaders.add(key, value);
			}
		}
	}

	public void setAuthenticationProperty(String key, String value) {
		checkMessageHeader(key, value);
		requests.set(key, value);
	}

	@Override
	public synchronized String getRequestProperty(String key) {
		if (key == null) {
			return null;
		}

		for (int i = 0; i < EXCLUDE_HEADERS.length; i++) {
			if (key.equalsIgnoreCase(EXCLUDE_HEADERS[i])) {
				return null;
			}
		}
		if (!setUserCookies) {
			if (key.equalsIgnoreCase(COOKIE)) {
				return userCookies;
			}
			if (key.equalsIgnoreCase(COOKIE2)) {
				return userCookies2;
			}
		}
		return requests.findValue(key);
	}

	@Override
	public synchronized Map<String, List<String>> getRequestProperties() {
		if (connected)
			throw new IllegalStateException(ALREADY_CONNECTED);

		if (setUserCookies) {
			return requests.getHeaders(EXCLUDE_HEADERS);
		}

		Map<String, List<String>> userCookiesMap = null;
		if (userCookies != null || userCookies2 != null) {
			userCookiesMap = new HashMap<>();
			if (userCookies != null) {
				userCookiesMap.put(COOKIE, Arrays.asList(userCookies));
			}
			if (userCookies2 != null) {
				userCookiesMap.put(COOKIE2, Arrays.asList(userCookies2));
			}
		}
		return requests.filterAndAddHeaders(EXCLUDE_HEADERS2, userCookiesMap);
	}

	@Override
	public void setConnectTimeout(int timeout) {
		if (timeout < 0)
			throw new IllegalArgumentException("timeouts can't be negative");
		connectTimeout = timeout;
	}

	@Override
	public int getConnectTimeout() {
		return (connectTimeout < 0 ? 0 : connectTimeout);
	}

	@Override
	public void setReadTimeout(int timeout) {
		if (timeout < 0)
			throw new IllegalArgumentException("timeouts can't be negative");
		readTimeout = timeout;
	}

	@Override
	public int getReadTimeout() {
		return readTimeout < 0 ? 0 : readTimeout;
	}

	public CookieHandler getCookieHandler() {
		return cookieHandler;
	}

	String getMethod() {
		return method;
	}

	private MessageHeader mapToMessageHeader(Map<String, List<String>> map) {
		MessageHeader headers = new MessageHeader();
		if (map == null || map.isEmpty()) {
			return headers;
		}
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			for (String value : values) {
				if (key == null) {
					headers.prepend(key, value);
				} else {
					headers.add(key, value);
				}
			}
		}
		return headers;
	}

	class HttpInputStream extends FilterInputStream {
		private CacheRequest cacheRequest;
		private OutputStream outputStream;
		private boolean marked = false;
		private int inCache = 0;
		private int markCount = 0;
		private boolean closed;

		public HttpInputStream(InputStream is) {
			super(is);
			this.cacheRequest = null;
			this.outputStream = null;
		}

		public HttpInputStream(InputStream is, CacheRequest cacheRequest) {
			super(is);
			this.cacheRequest = cacheRequest;
			try {
				this.outputStream = cacheRequest.getBody();
			} catch (IOException ioex) {
				this.cacheRequest.abort();
				this.cacheRequest = null;
				this.outputStream = null;
			}
		}

		@Override
		public synchronized void mark(int readlimit) {
			super.mark(readlimit);
			if (cacheRequest != null) {
				marked = true;
				markCount = 0;
			}
		}

		@Override
		public synchronized void reset() throws IOException {
			super.reset();
			if (cacheRequest != null) {
				marked = false;
				inCache += markCount;
			}
		}

		private void ensureOpen() throws IOException {
			if (closed)
				throw new IOException("stream is closed");
		}

		@Override
		public int read() throws IOException {
			ensureOpen();
			try {
				byte[] b = new byte[1];
				int ret = read(b);
				return (ret == -1 ? ret : (b[0] & 0x00FF));
			} catch (IOException ioex) {
				if (cacheRequest != null) {
					cacheRequest.abort();
				}
				throw ioex;
			}
		}

		@Override
		public int read(byte[] b) throws IOException {
			return read(b, 0, b.length);
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			ensureOpen();
			try {
				int newLen = super.read(b, off, len);
				int nWrite;

				if (inCache > 0) {
					if (inCache >= newLen) {
						inCache -= newLen;
						nWrite = 0;
					} else {
						nWrite = newLen - inCache;
						inCache = 0;
					}
				} else {
					nWrite = newLen;
				}
				if (nWrite > 0 && outputStream != null)
					outputStream.write(b, off + (newLen - nWrite), nWrite);
				if (marked) {
					markCount += newLen;
				}
				return newLen;
			} catch (IOException ioex) {
				if (cacheRequest != null) {
					cacheRequest.abort();
				}
				throw ioex;
			}
		}

		private byte[] skipBuffer;
		private static final int SKIP_BUFFER_SIZE = 8096;

		@Override
		public long skip(long n) throws IOException {
			ensureOpen();
			long remaining = n;
			int nr;
			if (skipBuffer == null)
				skipBuffer = new byte[SKIP_BUFFER_SIZE];

			byte[] localSkipBuffer = skipBuffer;

			if (n <= 0) {
				return 0;
			}

			while (remaining > 0) {
				nr = read(localSkipBuffer, 0, (int) Math.min(SKIP_BUFFER_SIZE, remaining));
				if (nr < 0) {
					break;
				}
				remaining -= nr;
			}

			return n - remaining;
		}

		@Override
		public void close() throws IOException {
			if (closed)
				return;

			try {
				if (outputStream != null) {
					if (read() != -1) {
						cacheRequest.abort();
					} else {
						outputStream.close();
					}
				}
				super.close();
			} catch (IOException ioex) {
				if (cacheRequest != null) {
					cacheRequest.abort();
				}
				throw ioex;
			} finally {
				closed = true;
				HttpURLConnectionImpl.this.http = null;
				checkResponseCredentials(true);
			}
		}
	}

	class StreamingOutputStream extends FilterOutputStream {

		long expected;
		long written;
		boolean closed;
		boolean error;
		IOException errorExcp;

		StreamingOutputStream(OutputStream os, long expectedLength) {
			super(os);
			expected = expectedLength;
			written = 0L;
			closed = false;
			error = false;
		}

		@Override
		public void write(int b) throws IOException {
			checkError();
			written++;
			if (expected != -1L && written > expected) {
				throw new IOException("too many bytes written");
			}
			out.write(b);
		}

		@Override
		public void write(byte[] b) throws IOException {
			write(b, 0, b.length);
		}

		@Override
		public void write(byte[] b, int off, int len) throws IOException {
			checkError();
			written += len;
			if (expected != -1L && written > expected) {
				out.close();
				throw new IOException("too many bytes written");
			}
			out.write(b, off, len);
		}

		void checkError() throws IOException {
			if (closed) {
				throw new IOException("Stream is closed");
			}
			if (error) {
				throw errorExcp;
			}
			if (((PrintStream) out).checkError()) {
				throw new IOException("Error writing request body to server");
			}
		}

		boolean writtenOK() {
			return closed && !error;
		}

		@Override
		public void close() throws IOException {
			if (closed) {
				return;
			}
			closed = true;
			if (expected != -1L) {
				if (written != expected) {
					error = true;
					errorExcp = new IOException("insufficient data written");
					out.close();
					throw errorExcp;
				}
				super.flush();
			} else {
				super.close();
				OutputStream o = http.getOutputStream();
				o.write('\r');
				o.write('\n');
				o.flush();
			}
		}
	}

	static class ErrorStream extends InputStream {
		ByteBuffer buffer;
		InputStream is;

		private ErrorStream(ByteBuffer buf) {
			buffer = buf;
			is = null;
		}

		private ErrorStream(ByteBuffer buf, InputStream is) {
			buffer = buf;
			this.is = is;
		}

		public static InputStream getErrorStream(InputStream is, long cl, HttpClient http) {

			if (cl == 0) {
				return null;
			}

			try {
				int oldTimeout = http.getReadTimeout();
				http.setReadTimeout(timeout4ESBuffer / 5);

				long expected = 0;
				boolean isChunked = false;

				if (cl < 0) {
					expected = bufSize4ES;
					isChunked = true;
				} else {
					expected = cl;
				}
				if (expected <= bufSize4ES) {
					int exp = (int) expected;
					byte[] buffer = new byte[exp];
					int count = 0;
					int time = 0;
					int len = 0;
					do {
						try {
							len = is.read(buffer, count, buffer.length - count);
							if (len < 0) {
								if (isChunked) {
									break;
								}
								throw new IOException("the server closes" + " before sending " + cl + " bytes of data");
							}
							count += len;
						} catch (SocketTimeoutException ex) {
							time += timeout4ESBuffer / 5;
						}
					} while (count < exp && time < timeout4ESBuffer);

					http.setReadTimeout(oldTimeout);

					if (count == 0) {
						return null;
					} else if ((count == expected && !(isChunked)) || (isChunked && len < 0)) {
						is.close();
						return new ErrorStream(ByteBuffer.wrap(buffer, 0, count));
					} else {
						return new ErrorStream(ByteBuffer.wrap(buffer, 0, count), is);
					}
				}
				return null;
			} catch (IOException ioex) {
				return null;
			}
		}

		@Override
		public int available() throws IOException {
			if (is == null) {
				return buffer.remaining();
			} else {
				return buffer.remaining() + is.available();
			}
		}

		@Override
		public int read() throws IOException {
			byte[] b = new byte[1];
			int ret = read(b);
			return (ret == -1 ? ret : (b[0] & 0x00FF));
		}

		@Override
		public int read(byte[] b) throws IOException {
			return read(b, 0, b.length);
		}

		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			int rem = buffer.remaining();
			if (rem > 0) {
				int ret = rem < len ? rem : len;
				buffer.get(b, off, ret);
				return ret;
			} else {
				if (is == null) {
					return -1;
				} else {
					return is.read(b, off, len);
				}
			}
		}

		@Override
		public void close() throws IOException {
			buffer = null;
			if (is != null) {
				is.close();
			}
		}
	}
}

class EmptyInputStream extends InputStream {

	@Override
	public int available() {
		return 0;
	}

	public int read() {
		return -1;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		return -1;
	}
}

class PasswordParameters {
	String host;
	InetAddress addr;
	int port;
	String protocol;
	String prompt;
	String scheme;
	URL url;
	RequestorType authType;

	PasswordParameters withHostAs(String host) {
		this.host = host;
		return this;
	}

	PasswordParameters withAddrAs(InetAddress addr) {
		this.addr = addr;
		return this;
	}

	PasswordParameters withPortAs(int port) {
		this.port = port;
		return this;
	}

	PasswordParameters withProtocolAs(String protocol) {
		this.protocol = protocol;
		return this;
	}

	PasswordParameters withPromptAs(String prompt) {
		this.prompt = prompt;
		return this;
	}

	PasswordParameters withSchemeAs(String scheme) {
		this.scheme = scheme;
		return this;
	}

	PasswordParameters withUrlAs(URL url) {
		this.url = url;
		return this;
	}

	PasswordParameters withAuthTypeAs(RequestorType authType) {
		this.authType = authType;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addr, authType, host, port, prompt, protocol, scheme, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof PasswordParameters))
			return false;
		PasswordParameters other = (PasswordParameters) obj;
		return Objects.equals(addr, other.addr) && authType == other.authType && Objects.equals(host, other.host)
				&& port == other.port && Objects.equals(prompt, other.prompt)
				&& Objects.equals(protocol, other.protocol) && Objects.equals(scheme, other.scheme)
				&& Objects.equals(url, other.url);
	}
}
