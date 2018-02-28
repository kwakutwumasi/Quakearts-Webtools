package com.quakearts.rest.client.test.helpers;

import com.quakearts.rest.client.HttpClientBuilder;

public class MockHttpClientBuilder extends HttpClientBuilder<MockHttpClient> {

	public MockHttpClientBuilder() {
		httpClient = new MockHttpClient();
	}
	
	@Override
	public MockHttpClient thenBuild() {
		return (MockHttpClient)httpClient;
	}

}
