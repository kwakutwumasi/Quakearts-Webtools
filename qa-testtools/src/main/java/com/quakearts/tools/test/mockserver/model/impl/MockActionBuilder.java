package com.quakearts.tools.test.mockserver.model.impl;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.fi.HttpRequestMatcher;
import com.quakearts.tools.test.mockserver.fi.HttpResponseAction;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.model.MockAction;

public class MockActionBuilder {
	MockActionImpl mockAction;
	
	MockActionBuilder() {
		mockAction = new MockActionImpl();
	}
	
	public static MockActionBuilder createNewMockAction() {
		return new MockActionBuilder();
	}
	
	public MockActionBuilder addRequest(HttpRequest httpRequest) {
		mockAction.request = httpRequest;
		return this;
	}
	
	public MockActionBuilder addMatcher(HttpRequestMatcher matcher) {
		mockAction.matcher = matcher;
		return this;
	}
	
	public MockActionBuilder addResponseAction(HttpResponseAction responseAction) {
		mockAction.responseAction = responseAction;
		return this;
	}
	
	public MockAction thenBuild() {
		if(mockAction.request == null)
			throw new BuilderException("HttpRequest is required");

		if(mockAction.request.getResponse() == null)
			throw new BuilderException("HttpResponse is required");
		
		return mockAction;
	}
	
	class MockActionImpl implements MockAction {
		HttpRequest request;
		HttpRequestMatcher matcher = (request,requestToMatch) -> { return request.equals(requestToMatch);};
		HttpResponseAction responseAction = (response) -> { return response;};
		
		@Override
		public boolean requestMatches(HttpRequest request) {
			if(matcher == null)
				return false;
			else
				return matcher.canMatch(request, request);
		}
		
		@Override
		public HttpResponse executeAction() throws MockServerProcessingException {
			if(responseAction != null) {
				HttpResponse newResponse = responseAction.perfomResponseAction(request.getResponse());
				if(newResponse != null)
					return newResponse;
			}
			
			return request.getResponse();
		}
	}
}
