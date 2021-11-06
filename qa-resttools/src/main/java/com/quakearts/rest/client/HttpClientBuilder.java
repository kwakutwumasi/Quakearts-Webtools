/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie (kwaku.twumasi@quakearts.com)
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.rest.client;

import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import com.quakearts.rest.client.HttpClient.AuthenticationScheme;
import com.quakearts.rest.client.exception.HttpClientRuntimeException;

/**Base class for creating builders for {@linkplain HttpClient}
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
/**
 * @author kwaku
 *
 * @param <T>
 */
public abstract class HttpClientBuilder<T extends HttpClient> {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	protected T httpClient;
		
	/**Set the URL of the target server
	 * @param url the target server URL in HTTP format
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setURLAs(String url){
		try {
			return setURLAs(new URL(url));
		} catch (MalformedURLException e) {
			throw new HttpClientRuntimeException("The URL was malformed", e);
		}
	}
	
	/**Set the URL of the target server
	 * @param url the target server URL. Must have a protocol type of HTTP or HTTPS
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setURLAs(URL url){
		if(!url.getProtocol().equals(HTTP)
				&& !url.getProtocol().equals(HTTPS))
			throw new HttpClientRuntimeException("Only http and https protocols are supported");
		
		setHostAs(url.getHost())
			.setPortAs(getPort(url))
			.setSecuredAs(url.getProtocol().equals(HTTPS));
		
		return this;		
	}
	
	private int getPort(URL url) {
		if(url.getPort()==-1) {
			return url.getProtocol().equals(HTTPS)?443:80;
		}
		return url.getPort();
	}

	/**Set the HTTP port
	 * @param port the HTTP port
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setPortAs(int port) {
		httpClient.port = port;
		return this;
	}

	/**Set the HTTP host
	 * @param host the HTTP host
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setHostAs(String host) {
		httpClient.host = host;
		return this;
	}

	/**Set the default {@link HttpCookie}
	 * @param cookie the cookie
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setDefaultCookieAs(HttpCookie cookie) {
		httpClient.defaultCookie = cookie;
		return this;
	}

	/**Set the client to secured mode
	 * @param secured true if the client connects using HTTPS
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setSecuredAs(boolean secured) {
		httpClient.secured = secured;
		return this;
	}

	/**Set the {@linkplain AuthenticationScheme} to use
	 * @param authenticationScheme the AuthenticationScheme
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setAuthenticationSchemeAs(AuthenticationScheme authenticationScheme) {
		httpClient.authenticationScheme = authenticationScheme;
		return this;
	}

	/**Set the Basic Authentication username
	 * @param username the username
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setUsernameAs(String username) {
		httpClient.username = username;
		return this;
	}

	/**Set the Basic Authentication password
	 * @param password the password
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setPasswordAs(String password) {
		httpClient.password = password;
		return this;
	}

	/**Set the Bearer Authentication token
	 * @param token the password
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setTokenAs(String token) {
		httpClient.password = token;
		return this;
	}

	/**Set the User-Agent string
	 * @param userAgent the User-Agent string
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setUserAgentAs(String userAgent) {
		httpClient.userAgent = userAgent;
		return this;
	}

	/**Set the 'matches hostnames property'
	 * @param matchHostname true if the client will by pass client certificate validation for localhost and 127.0.0.10
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setMatchesHostnameAs(boolean matchHostname){
		httpClient.matchHostname = matchHostname;
		return this;
	}
	
	/**Set the follow redirects property of the {@link java.net.HttpURLConnection}
	 * @param followRedirects true if this client should follow redirects
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setFollowRedirectAs(boolean followRedirects){
		httpClient.followRedirects = followRedirects;
		return this;
	}
	
	/**Set the connect timeout property of the {@link java.net.HttpURLConnection}
	 * @param connectTimeout the connect timeout as a decimal. The value is in seconds
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setConnectTimeoutAs(double connectTimeout){
		httpClient.connectTimeout = (int) (connectTimeout*1000);
		return this;
	}
	
	/**Set the read timeout property of the {@link java.net.HttpURLConnection}
	 * @param readTimeout the read timeout as a decimal. The value is in seconds
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setReadTimeoutAs(double readTimeout){
		httpClient.readTimeout = (int) (readTimeout*1000);
		return this;
	}
	
	/**Set the character set for reading and writing content to the connection streams
	 * @param charset the name of the registered character set. Defaults to UTF-8
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setCharacterSetAs(String charset){
		httpClient.charset = charset;
		return this;
	}

	/**Set the acceptLanguage for locale and formatting information
	 * @param acceptLanguage the name of the ISO language code. Defaults to en-US
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setAcceptLanguageAs(String acceptLanguage){
		httpClient.acceptLanguage = acceptLanguage;
		return this;
	}
	
	/**Set the {@link Proxy} to use making HTTP requests
	 * @param proxy the Proxy to use
	 * @return this object for method chaining
	 */
	public HttpClientBuilder<T> setProxyAs(Proxy proxy){
		httpClient.proxy = proxy;
		return this;
	}

	/**Implement this method to return the configured {@link HttpClient}
	 * @return the configured client
	 */
	public abstract T thenBuild();
}
