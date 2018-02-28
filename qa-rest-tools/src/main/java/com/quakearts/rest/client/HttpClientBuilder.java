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

	public abstract T thenBuild();
}
