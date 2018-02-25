package com.quakearts.tools.test.mockserver.model.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpMessage;

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