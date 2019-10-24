package com.quakearts.rest.client.test.helpers;

import com.quakearts.rest.client.HttpClientBuilder;

public class MockHttpObjectClientBuilder 
	extends HttpClientBuilder<MockHttpObjectClient> {

	public MockHttpObjectClientBuilder() {
		httpClient = new MockHttpObjectClient();
	}
	
	@Override
	public MockHttpObjectClient thenBuild() {
		return httpClient;
	}
}
