package com.quakearts.tools.test.mockserver.model;

import java.util.List;

public interface HttpHeader {
	String getName();
	String getValue();
	List<String> getValues();
}