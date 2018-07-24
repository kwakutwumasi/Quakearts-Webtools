/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.tools.test.mockserver.model.impl;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;
import com.quakearts.tools.test.mockserver.fi.HttpRequestMatcher;
import com.quakearts.tools.test.mockserver.fi.HttpResponseAction;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.model.MockAction;

/**Builder for {@linkplain MockAction}s
 * @author kwakutwumasi-afriyie
 *
 */
public class MockActionBuilder {
	MockActionImpl mockAction;
	
	MockActionBuilder() {
		mockAction = new MockActionImpl();
	}
	
	/**Create a new {@linkplain MockAction}
	 * @return this object for method chaining
	 */
	public static MockActionBuilder createNewMockAction() {
		return new MockActionBuilder();
	}
	
	/**Set the HTTP request for the {@linkplain MockAction}
	 * @param httpRequest the {@linkplain HttpRequest}
	 * @return this object for method chaining
	 */
	public MockActionBuilder setRequestAs(HttpRequest httpRequest) {
		mockAction.request = httpRequest;
		return this;
	}

	/**Set the {@linkplain HttpRequestMatcher} to use in matching incoming
	 * {@linkplain HttpRequest}s to the stored {@linkplain HttpRequest}
	 * @param matcher the {@linkplain HttpRequestMatcher}
	 * @return this object for method chaining
	 */
	public MockActionBuilder setMatcherAs(HttpRequestMatcher matcher) {
		mockAction.matcher = matcher;
		return this;
	}

	/**Set the {@linkplain HttpResponseAction} to use when processing the response
	 * @param responseAction the {@linkplain HttpResponseAction}
	 * @return this object for method chaining
	 */
	public MockActionBuilder setResponseActionAs(HttpResponseAction responseAction) {
		mockAction.responseAction = responseAction;
		return this;
	}

	/**Terminal method in the fluid API. Return the created {@linkplain MockAction}
	 * @return
	 */
	public MockAction thenBuild() {
		if(mockAction.request == null)
			throw new BuilderException("HttpRequest is required");

		if(mockAction.request.getResponse() == null)
			throw new BuilderException("HttpResponse is required");
		
		return mockAction;
	}
	
	class MockActionImpl implements MockAction {
		HttpRequest request;
		HttpRequestMatcher matcher = (request,requestToMatch) -> { 
			return request.getMethod().equals(requestToMatch.getMethod())
					&& request.getResource().equals(requestToMatch.getResource());
		};
		HttpResponseAction responseAction = (request, response) -> { 
			return null;
		};
		
		@Override
		public boolean requestMatches(HttpRequest request) {
			if(matcher == null)
				return false;
			else
				return matcher.canMatch(this.request, request);
		}
		
		@Override
		public HttpResponse executeAction(HttpRequest recievedRequest) throws MockServerProcessingException {
			if(responseAction != null) {
				HttpResponse newResponse = responseAction.perfomResponseAction(recievedRequest, 
						request.getResponse());
				if(newResponse != null)
					return newResponse;
			}
			
			return request.getResponse();
		}
	}
}
