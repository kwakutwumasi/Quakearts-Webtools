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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.rest.client.HttpClient;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;

public class MockHttpClient extends HttpClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = -97084266199855889L;

	MockHttpClient() {}
	
	public HttpResponse sendRequest(HttpRequest request) 
			throws MalformedURLException, UnsupportedEncodingException, 
			IOException, HttpClientException {
		if(request.getHeaders()==null 
				|| request.getHeaders().isEmpty()) {
			return sendRequest(request.getResource(), request.getContentBytes()!=null?request.getContent():null, 
					request.getMethod()!=null?HttpVerb.valueOf(request.getMethod()):null,
							"application/json");
		} else {
			Map<String, List<String>> additionalHeaders = new HashMap<>();
			for(HttpHeader header:request.getHeaders()) {
				additionalHeaders.put(header.getName(), header.getValues());
			}
			return sendRequest(request.getResource(), request.getContentBytes()!=null?request.getContent():null, 
					HttpVerb.valueOf(request.getMethod()), "application/json",
					additionalHeaders);
		}
	}
}
