package com.quakearts.webtools.resteasy.cdi.test.helpers;

import com.quakearts.rest.client.HttpClientBuilder;

public class TestHttpClientBuilder extends HttpClientBuilder<TestHttpClient>{

	public TestHttpClientBuilder() {
		httpClient = new TestHttpClient();
	}
	
	@Override
	public TestHttpClient thenBuild() {
		return (TestHttpClient) httpClient;
	}
	
}