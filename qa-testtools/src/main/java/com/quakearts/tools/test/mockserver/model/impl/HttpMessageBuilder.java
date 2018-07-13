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

import java.util.Arrays;
import java.util.Optional;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpMessage;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.util.HttpVerbUtil;

/**Create a {@linkplain HttpMessage}
 * @author kwakutwumasi-afriyie
 *
 */
public class HttpMessageBuilder {
	HttpMessage message;
	private HttpMessageBuilder() {}
	
	/**Create a new {@linkplain HttpRequest}
	 * @return a {@linkplain HttpRequestBuilder} object for method chaining
	 */
	public static HttpRequestBuilder createNewHttpRequest() {
		return new HttpRequestBuilder();
	}
	
	/**Create a new {@linkplain HttpRequest} using the HTTP request as the base
	 * @param request the {@linkplain HttpRequest}
	 * @return a {@linkplain HttpRequestBuilder} object for method chaining
	 */
	public static HttpRequestBuilder use(HttpRequest request) {
		return new HttpRequestBuilder(request);
	}
	
	/**Builder for {@linkplain HttpRequest} 
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static class HttpRequestBuilder extends HttpMessageBuilder {
		
		HttpRequestBuilder() {
			message = new HttpRequestImpl();
		}
		
		HttpRequestBuilder(HttpRequest request) {
			if(request instanceof HttpRequestImpl) {
				message = request;
			} else {
				message = new HttpRequestImpl(request);
			}
		}

		/**Set the HTTP method of the request
		 * @param method the HTTP method
		 * @return this object for method chaining
		 */
		public HttpRequestBuilder setMethodAs(String method) {
			if(method==null)
				throw new BuilderException("method cannot be null");

			if(!HttpVerbUtil.isValidVerb(method))
				throw new BuilderException("method must be a valid http verb");
		
			asHttpRequestImpl().method = method.toUpperCase();
			return this;
		}

		/**Set the HTTP resource of the request
		 * @param resource the HTTP resource
		 * @return this object for method chaining
		 */
		public HttpRequestBuilder setResourceAs(String resource) {
			if(resource==null)
				throw new BuilderException("resource cannot be null");
				
			asHttpRequestImpl().resource = resource;
			return this;
		}
		
		/**Set the {@linkplain HttpResponse}
		 * @param httpResponse the {@linkplain HttpResponse}
		 * @return this object for method chaining
		 */
		public HttpRequestBuilder setResponseAs(HttpResponse httpResponse) {
			asHttpRequestImpl().response = httpResponse;
			return this;
		}

		/**The ID to save the {@linkplain HttpMessage} in the 
		 * {@link com.quakearts.tools.test.mockserver.store.HttpMessageStore HttpMessageStore}
		 * @param id the ID
		 * @return this object for method chaining
		 */
		public HttpRequestBuilder setId(String id) {
			asHttpRequestImpl().id = id;
			return this;
		}
		
		@Override
		public HttpRequestBuilder addHeaders(HttpHeader... headers) {
			super.addHeaders(headers);
			return this;
		}
		
		@Override
		public HttpRequestBuilder setContentBytes(byte[] body) {
			super.setContentBytes(body);
			return this;
		}
		
		@Override
		public HttpRequestBuilder setContentEncoding(String contentEncoding) {
			super.setContentEncoding(contentEncoding);
			return this;
		}
		
		private HttpRequestImpl asHttpRequestImpl() {
			if(message instanceof HttpRequestImpl)
				return (HttpRequestImpl) message;
			throw new BuilderException("HttpMessage is not a HttpRequest");
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public HttpRequest thenBuild() {
			HttpRequest request = asHttpRequestImpl();
			if(request.getMethod() == null)
				throw new BuilderException("method is not a valid HTTP Verb.");
				
			if(request.getResource() == null) {
				throw new BuilderException("resource is not a valid. It must be a valid resource string");
			}
			
			if(request.getId() == null) {
				throw new BuilderException("id is not a valid. It must be a valid string");
			}
			
			return request;
		}
	}
	
	/**Create a new {@linkplain HttpResponse}
	 * @return a {@linkplain HttpResponseBuilder} object for method chaining
	 */
	public static HttpResponseBuilder createNewHttpResponse() {
		return new HttpResponseBuilder();
	}
	
	/**Builder for {@linkplain HttpResponse}
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static class HttpResponseBuilder extends HttpMessageBuilder {
		HttpResponseBuilder() {
			message = new HttpResponseImpl();
		}
		
		/**Set the HTTP response code
		 * @param responseCode the HTTP response code
		 * @return this object for method chaining
		 */
		public HttpResponseBuilder setResponseCodeAs(int responseCode) {
			if(responseCode < 200 || responseCode > 599)
				throw new BuilderException("HttpResponse must have a valid responseCode in the 200-599 range");

			asHttpResponseImpl().responseCode = responseCode;
			return this;
		}
		
		@Override
		public HttpResponseBuilder addHeaders(HttpHeader... headers) {
			super.addHeaders(headers);
			return this;
		}
		
		@Override
		public HttpResponseBuilder setContentBytes(byte[] body) {
			super.setContentBytes(body);
			return this;
		}
		
		@Override
		public HttpResponseBuilder setContentEncoding(String contentEncoding) {
			super.setContentEncoding(contentEncoding);
			return this;
		}
				
		private HttpResponseImpl asHttpResponseImpl() {
			if(message instanceof HttpResponseImpl)
				return (HttpResponseImpl) message;
			throw new BuilderException("HttpMessage is not a HttpResponse");
		}
		
		@Override
		@SuppressWarnings("unchecked")
		public HttpResponse thenBuild() {
			HttpResponse response = asHttpResponseImpl();
			if(response.getResponseCode() == 0)
				throw new BuilderException("HttpResponse must have a valid responseCode in the 200-599 range");
			return response;
		}
	}
	
	/**Add the list of {@linkplain HttpHeader}s to the message
	 * @param headers the {@linkplain HttpHeader}s
	 * @return this object for method chaining
	 */
	public HttpMessageBuilder addHeaders(HttpHeader... headers) {
		for(HttpHeader header:headers) {
			if(header.getName() == null 
					|| header.getName().trim().isEmpty())
				continue;
				
			HttpMessageImpl impl = asHttpMessageImpl();
			impl.headers.put(header.getName(), header);
			if(header.getName().trim().equalsIgnoreCase("Content-Type")
					&& header.getValue().indexOf(";") != -1) {
				Optional<String> foundCharset = Arrays.asList(header.getValue().split(";")).stream()
				.filter((string)-> {
					String[] parts = string.split("=");
					if(parts.length==2 
							&& parts[0].trim().equalsIgnoreCase("charset"))
						return true;
					return false;
				}).map((string)->{
					return string.split("=")[1].trim();
				}).findFirst();
				
				if(foundCharset.isPresent())
					impl.contentEncoding = foundCharset.get().toUpperCase().trim();
			}
		}
		return this;
	}
	
	/**Set the contents of this message
	 * @param body the array of bytes making up the body
	 * @return this object for method chaining
	 */
	public HttpMessageBuilder setContentBytes(byte[] body) {
		asHttpMessageImpl().contentBytes = body;
		return this;
	}
	
	/**Set the encoding of the contents of the message
	 * @param contentEncoding the encoding
	 * @return this object for method chaining
	 */
	public HttpMessageBuilder setContentEncoding(String contentEncoding) {
		asHttpMessageImpl().contentEncoding = contentEncoding;
		return this;
	}
			
	private HttpMessageImpl asHttpMessageImpl() {
		if(message instanceof HttpMessageImpl)
			return (HttpMessageImpl) message;
		throw new BuilderException("message has not been initialized");
	}

	/**Terminal method in the fluid API. Returns the created HttpMessage.
	 * @return the HttpMessage
	 */
	public<T extends HttpMessage> T thenBuild() {
		throw new UnsupportedOperationException("Unimplemented");
	}
}
