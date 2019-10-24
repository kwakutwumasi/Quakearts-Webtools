package com.quakearts.rest.client.test.helpers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.rest.client.HttpObjectClient;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;
import com.quakearts.tools.test.mockserver.model.HttpRequest;

public class MockHttpObjectClient extends HttpObjectClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6459208394047608265L;

	public MockResponse sendRequest(HttpRequest httpRequest, String returnCode) 
			throws IOException, HttpClientException {
		Map<String, List<String>> additionalHeaders = new HashMap<>();
		additionalHeaders.put("Authorization", Arrays.asList("Token something"));
		return execute(HttpVerb.POST, "/test-mock-object?return={0}", 
				httpRequest, "text/plain", MockResponse.class, additionalHeaders, encode(returnCode));
	}
	
	public MockResponse sendRequest(HttpRequest httpRequest) 
			throws IOException, HttpClientException {
		return execute(HttpVerb.POST, "/test-mock-object", 
				httpRequest, "text/plain", MockResponse.class);
	}
	
	public MockResponse sendRequest() 
			throws IOException, HttpClientException {
		return executeGet("/test-mock-object", MockResponse.class);
	}
	
	public String throwHttpClientException() 
			throws IOException, HttpClientException {
		return executeGet("/test-mock-object", String.class);
	}
	
	@Override
	protected String writeValueAsString(Object requestValue) throws HttpClientException {
		try {
			return requestValue instanceof HttpRequest? ((HttpRequest)requestValue).getContent():requestValue.toString();
		} catch (UnsupportedEncodingException e) {
			throw new HttpClientException("Unable to write value to string", e);
		}
	}

	@Override
	protected HttpClientException nonSuccessResponseUsing(HttpResponse httpResponse) {
		return new HttpClientException(httpResponse.getOutput());
	}
	
	@Override
	protected <R> Converter<R> createConverter(Class<R> targetClass) {
		return (httpResponse)->{
			Constructor<R> constructor;
			try {
				constructor = targetClass.getConstructor(String.class, int.class);
				return constructor.newInstance(httpResponse.getOutput(), httpResponse.getHttpCode());
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new HttpClientException("Unable to coerce HTTP response to "+targetClass.getName(), e);
			}
		};
	}
	
	private boolean return100;
	
	public void setReturn100(boolean return100) {
		this.return100 = return100;
	}
	
	@Override
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType,
			Map<String, List<String>> additionalHeaders) throws IOException, HttpClientException {
		if(return100){
			return new HttpResponse("Test Response".getBytes(), "text/plain", 100, null);
		}
		
		return super.sendRequest(file, requestValue, method, contentType, additionalHeaders);
	}
}
