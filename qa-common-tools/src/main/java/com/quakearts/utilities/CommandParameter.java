package com.quakearts.utilities;

import java.util.List;

public interface CommandParameter {
	String DEFAULT = "com.quakearst.utilities.DEFAULT";
	String getName();
	String getValue();
	List<String> getValues();
	void setValue(String value);
}
