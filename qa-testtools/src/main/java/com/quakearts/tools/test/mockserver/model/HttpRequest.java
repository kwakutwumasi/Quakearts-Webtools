package com.quakearts.tools.test.mockserver.model;

import java.util.List;

public interface HttpRequest extends HttpMessage {
	String getMethod();
	String getResource();
	HttpResponse getResponse();
	List<String> getParameterValue(String name);
	boolean hasParameter(String name);
	byte[] getContentBytes();
	String getId();
}