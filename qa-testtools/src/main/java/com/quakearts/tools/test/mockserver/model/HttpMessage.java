package com.quakearts.tools.test.mockserver.model;

import java.io.UnsupportedEncodingException;
import java.util.Collection;

public interface HttpMessage {
	Collection<HttpHeader> getHeaders();
	String getHeaderValue(String name);
	String getContentEncoding();
	String getContent() throws UnsupportedEncodingException;
	byte[] getContentBytes();
}