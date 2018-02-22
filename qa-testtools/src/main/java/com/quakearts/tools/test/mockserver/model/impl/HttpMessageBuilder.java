package com.quakearts.tools.test.mockserver.model.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
	
	abstract class HttpMessageImpl implements Serializable, HttpMessage {
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 2699839481501156721L;
		Map<String, HttpHeader> headers = new HashMap<String, HttpHeader>();
		byte[] contentBytes;
		String contentEncoding = "UTF-8";
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpMessage#getHeaders()
		 */
		@Override
		public Collection<HttpHeader> getHeaders() {
			return headers.entrySet().stream().map((entry)->{
				return entry.getValue();
			}).collect(Collectors.toCollection(ArrayList::new));
		}
		
		@Override
		public String getHeaderValue(String name) {
			if(headers.containsKey(name) 
					&& headers.get(name)!=null)
				return headers.get(name).getValue();
				
			return null;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.HttpMessage#getContentBytes()
		 */
		@Override
		public byte[] getContentBytes() {
			return contentBytes;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpMessage#getContentEncoding()
		 */
		@Override
		public String getContentEncoding() {
			return contentEncoding;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpMessage#getContent()
		 */
		@Override
		public String getContent() throws UnsupportedEncodingException {
			return new String(contentBytes, contentEncoding);
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(contentBytes);
			result = prime * result + ((contentEncoding == null) ? 0 : contentEncoding.hashCode());
			result = prime * result + ((headers == null) ? 0 : headers.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HttpMessageImpl other = (HttpMessageImpl) obj;
			if (!Arrays.equals(contentBytes, other.contentBytes))
				return false;
			if (contentEncoding == null) {
				if (other.contentEncoding != null)
					return false;
			} else if (!contentEncoding.equals(other.contentEncoding))
				return false;
			if (headers == null) {
				if (other.headers != null)
					return false;
			} else if (!headers.equals(other.headers))
				return false;
			return true;
		}		
	}

	class HttpRequestImpl extends HttpMessageImpl implements HttpRequest {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8948720128836835212L;
		String method, resource;
		Map<String, List<String>> uriParameters;
		HttpResponse response;
		String id;
		
		HttpRequestImpl(HttpRequest request) {
			method = request.getMethod();
			resource = request.getResource();
			contentBytes = request.getContentBytes();
			contentEncoding = request.getContentEncoding();
			if(request.getHeaders() != null) {
				for(HttpHeader header:request.getHeaders()) {
					headers.put(header.getName(), header);
				}
			}
			id = request.getId();
			response = request.getResponse();
		}

		HttpRequestImpl() {
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpRequest#getMethod()
		 */
		@Override
		public String getMethod() {
			return method;
		}

		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpRequest#getResource()
		 */
		@Override
		public String getResource() {
			return resource;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpRequest#getResponse()
		 */
		@Override
		public HttpResponse getResponse() {
			return response;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.HttpRequest#getId()
		 */
		@Override
		public String getId() {
			return id;
		}
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpRequest#getParameter(java.lang.String)
		 */
		@Override
		public List<String> getParameterValue(String name) {
			if(uriParameters == null) {
				if(resource != null) {
					uriParameters = new HashMap<>();
					if(resource.contains("?") 
							&& resource.indexOf("?")!=resource.length()-1) {
						String parametersString = resource.substring(resource.indexOf("?")+1);
						for(String parameterPair:parametersString.split("&")) {
							String[] pair = parameterPair.split("=",2);
							if(pair.length == 2) {
								List<String> values;
								if(uriParameters.containsKey(pair[0])) {
									values = uriParameters.get(pair[0]);
								} else {
									values = new ArrayList<>();
									uriParameters.put(pair[0], values);
								}
								values.add(pair[1]);
							} else {
								uriParameters.put(pair[0], null);
							}
						}
					}
				} else {
					return null;
				}
			}
			return uriParameters.get(name);
		}

		@Override
		public boolean hasParameter(String name) {
			return uriParameters!=null?uriParameters.containsKey(name):false;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((method == null) ? 0 : method.hashCode());
			result = prime * result + ((resource == null) ? 0 : resource.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			HttpRequestImpl other = (HttpRequestImpl) obj;
			if (method == null) {
				if (other.method != null)
					return false;
			} else if (!method.equals(other.method))
				return false;
			if (resource == null) {
				if (other.resource != null)
					return false;
			} else if (!resource.equals(other.resource))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return super.equals(obj);
		}
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
		public HttpRequestBuilder addHeader(HttpHeader header) {
			super.addHeader(header);
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
	
	class HttpResponseImpl extends HttpMessageImpl implements HttpResponse {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3293157181552148306L;

		int responseCode;
		
		/* (non-Javadoc)
		 * @see com.quakearts.tools.test.mockserver.model.impl.HttpResponse#getResponseCode()
		 */
		@Override
		public int getResponseCode() {
			return responseCode;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + responseCode;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!super.equals(obj))
				return false;
			if (getClass() != obj.getClass())
				return false;
			HttpResponseImpl other = (HttpResponseImpl) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (responseCode != other.responseCode)
				return false;
			return super.equals(obj);
		}

		private HttpMessageBuilder getOuterType() {
			return HttpMessageBuilder.this;
		}
		
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
		public HttpResponseBuilder addHeader(HttpHeader header) {
			super.addHeader(header);
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
	
	public HttpMessageBuilder addHeader(HttpHeader header) {
		HttpMessageImpl impl = asHttpMessageImpl();
		impl.headers.put(header.getName(), header);
		if(impl.contentEncoding == null
				&& header.getName().trim().equalsIgnoreCase("Content-Type")
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
				impl.contentEncoding = foundCharset.get().trim();
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
