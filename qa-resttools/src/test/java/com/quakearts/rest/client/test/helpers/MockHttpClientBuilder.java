/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie (kwaku.twumasi@quakearts.com)
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.rest.client.test.helpers;

import com.quakearts.rest.client.HttpClientBuilder;

public class MockHttpClientBuilder extends HttpClientBuilder<MockHttpClient> {

	private MockHttpClientBuilder() {}

	private static MockHttpClientBuilder instance = new MockHttpClientBuilder();
	
	public static MockHttpClientBuilder getInstance() {
		return instance;
	}
	
	public HttpClientBuilder<MockHttpClient> createNewHttpClient(){
		httpClient = new MockHttpClient();
		return this;
	}
	
	@Override
	public MockHttpClient thenBuild() {
		return httpClient;
	}

}
