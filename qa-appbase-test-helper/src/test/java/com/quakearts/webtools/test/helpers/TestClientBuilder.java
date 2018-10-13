package com.quakearts.webtools.test.helpers;

import com.quakearts.rest.client.HttpClientBuilder;

public class TestClientBuilder extends HttpClientBuilder<TestClient>{

	public TestClientBuilder() {
		httpClient = new TestClient();
	}
	
	@Override
	public TestClient thenBuild() {
		return httpClient;
	}
	
}