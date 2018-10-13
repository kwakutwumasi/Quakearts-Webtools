package com.quakearts.webtools.test.helpers;

import java.io.IOException;

import com.quakearts.rest.client.HttpClient;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;

public class TestClient extends HttpClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5787860644255145883L;

	public String makeCall() throws IOException, HttpClientException {
		HttpResponse httpResponse = sendRequest("/", null, HttpVerb.GET, null);
		return httpResponse.getOutput();
	}
}