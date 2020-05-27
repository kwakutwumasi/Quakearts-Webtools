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
import java.util.Objects;

import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpResponse;

class HttpResponseImpl extends HttpMessageImpl implements HttpResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3293157181552148306L;

	int responseCode;
	
	HttpResponseImpl(HttpResponse response) {
		contentBytes = response.getContentBytes();
		contentEncoding = response.getContentEncoding();
		if(response.getHeaders() != null) {
			for(HttpHeader header:response.getHeaders()) {
				headers.put(header.getName(), header);
			}
		}
		responseCode = response.getResponseCode();
	}
	
	HttpResponseImpl() {
	}

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
		result = prime * result + Objects.hash(responseCode);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HttpResponseImpl other = (HttpResponseImpl) obj;
		return responseCode == other.responseCode;
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
		
		return "HttpResponseImpl [responseCode=" + responseCode + ", headers=" + headers + ", contentBytes="
				+ content + ", contentEncoding=" + contentEncoding + "]";
	}
}