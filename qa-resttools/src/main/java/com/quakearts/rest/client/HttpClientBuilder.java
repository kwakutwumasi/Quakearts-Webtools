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
import java.net.URL;

import com.quakearts.rest.client.exception.HttpClientRuntimeException;

/**Base class for creating builders for {@linkplain HttpClient}
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public abstract class HttpClientBuilder<T extends HttpClient> {
	private static final String HTTP = "http";
	private static final String HTTPS = "https";
	protected T httpClient;
		
	public HttpClientBuilder<T> setURLAs(String url){
		try {
			return setURLAs(new URL(url));
		} catch (MalformedURLException e) {
			throw new HttpClientRuntimeException("The URL was malformed", e);
		}
	}
	
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

	public HttpClientBuilder<T> setPortAs(int port) {
		httpClient.port = port;
		return this;
	}

	public HttpClientBuilder<T> setHostAs(String host) {
		httpClient.host = host;
		return this;
	}

	public HttpClientBuilder<T> setDefaultCookieAs(HttpCookie cookie) {
		httpClient.defaultCookie = cookie;
		return this;
	}

	public HttpClientBuilder<T> setSecuredAs(boolean secured) {
		httpClient.secured = secured;
		return this;
	}

	public HttpClientBuilder<T> setUsernameAs(String username) {
		httpClient.username = username;
		return this;
	}

	public HttpClientBuilder<T> setPasswordAs(String password) {
		httpClient.password = password;
		return this;
	}

	public HttpClientBuilder<T> setUserAgentAs(String userAgent) {
		httpClient.userAgent = userAgent;
		return this;
	}

	public HttpClientBuilder<T> setMatchesHostnameAs(boolean matchHostname){
		httpClient.matchHostname = matchHostname;
		return this;
	}
	
	public HttpClientBuilder<T> setFollowRedirectAs(boolean followRedirects){
		httpClient.followRedirects = followRedirects;
		return this;
	}
	
	public abstract T thenBuild();
}
