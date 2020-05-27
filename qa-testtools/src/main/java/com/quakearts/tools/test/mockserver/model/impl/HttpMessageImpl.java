package com.quakearts.tools.test.mockserver.model.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpMessage;

abstract class HttpMessageImpl implements Serializable, HttpMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2699839481501156721L;
	Map<String, HttpHeader> headers = new HashMap<>();
	byte[] contentBytes;
	String contentEncoding = "UTF-8";
	
	/* (non-Javadoc)
	 * @see com.quakearts.tools.test.mockserver.model.impl.HttpMessage#getHeaders()
	 */
	@Override
	public Collection<HttpHeader> getHeaders() {
		return headers.entrySet().stream().map(Entry::getValue)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	@Override
	public String getHeaderValue(String name) {
		if(headers.containsKey(name) 
				&& headers.get(name)!=null)
			return headers.get(name).getValue();
			
		return null;
	}
	
	@Override
	public List<String> getHeaderValues(String name) {
		if(headers.containsKey(name) 
				&& headers.get(name)!=null)
			return headers.get(name).getValues();
			
		return Collections.emptyList();
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
		result = prime * result + Objects.hash(contentEncoding, headers);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		HttpMessageImpl other = (HttpMessageImpl) obj;
		return Arrays.equals(contentBytes, other.contentBytes) && Objects.equals(contentEncoding, other.contentEncoding)
				&& Objects.equals(headers, other.headers);
	}
}