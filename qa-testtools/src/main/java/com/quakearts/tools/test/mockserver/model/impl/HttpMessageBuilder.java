package com.quakearts.tools.test.mockserver.model.impl;

import java.util.Arrays;
import java.util.Optional;

import com.quakearts.tools.test.mockserver.exception.BuilderException;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpMessage;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;
import com.quakearts.tools.test.mockserver.util.HttpVerbUtil;

public class HttpMessageBuilder {
	HttpMessage message;
	private HttpMessageBuilder() {}
	
	public static HttpRequestBuilder createNewHttpRequest() {
		return new HttpRequestBuilder();
	}
	
	public static HttpRequestBuilder use(HttpRequest request) {
		return new HttpRequestBuilder(request);
	}
	
	public static class HttpRequestBuilder extends HttpMessageBuilder {
		
		HttpRequestBuilder() {
			message = new HttpRequestImpl();
		}
		
		public HttpRequestBuilder(HttpRequest request) {
			if(request instanceof HttpRequestImpl) {
				message = request;
			} else {
				message = new HttpRequestImpl(request);
			}
		}

		public HttpRequestBuilder setMethodAs(String method) {
			if(method==null)
				throw new BuilderException("method cannot be null");

			if(!HttpVerbUtil.isValidVerb(method))
				throw new BuilderException("method must be a valid http verb");
		
			asHttpRequestImpl().method = method.toUpperCase();
			return this;
		}

		public HttpRequestBuilder setResourceAs(String resource) {
			if(resource==null)
				throw new BuilderException("resource cannot be null");
				
			asHttpRequestImpl().resource = resource;
			return this;
		}
		
		public HttpRequestBuilder setResponseAs(HttpResponse httpResponse) {
			asHttpRequestImpl().response = httpResponse;
			return this;
		}

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
	
	public static HttpResponseBuilder createNewHttpResponse() {
		return new HttpResponseBuilder();
	}
	
	public static class HttpResponseBuilder extends HttpMessageBuilder {
		HttpResponseBuilder() {
			message = new HttpResponseImpl();
		}
		
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
	
	public HttpMessageBuilder setContentBytes(byte[] body) {
		asHttpMessageImpl().contentBytes = body;
		return this;
	}
	
	public HttpMessageBuilder setContentEncoding(String contentEncoding) {
		asHttpMessageImpl().contentEncoding = contentEncoding;
		return this;
	}
			
	private HttpMessageImpl asHttpMessageImpl() {
		if(message instanceof HttpMessageImpl)
			return (HttpMessageImpl) message;
		throw new BuilderException("message has not been initialized");
	}

	public<T extends HttpMessage> T thenBuild() {
		throw new UnsupportedOperationException("Unimplemented");
	}
}
