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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;

class HttpRequestImpl extends HttpMessageImpl implements HttpRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8948720128836835212L;
	String method;
	String resource;
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
	public List<String> getURIParameterValue(String name) {
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
		return super.equals(obj);
	}

	@Override
	public String toString() {
		String content=null;
		if(contentBytes!=null)
			try {
				content = getContent();
			} catch (UnsupportedEncodingException e) {
				content = "!UnsupportedEncodingException!";
			}

		return "HttpRequestImpl [method=" + method + ", resource=" + resource + ", uriParameters=" + uriParameters
				+ ", response=" + response + ", id=" + id + ", headers=" + headers + ", contentBytes="
				+ content + ", contentEncoding=" + contentEncoding + "]";
	}
}