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

public abstract class HttpClientBuilder<T extends HttpClient> {
	protected HttpClient httpClient;
		
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

	public HttpClientBuilder<T> setMatchesHostname(boolean matchHostname){
		httpClient.matchHostname = true;
		return this;
	}
	
	public abstract T thenBuild();
}
