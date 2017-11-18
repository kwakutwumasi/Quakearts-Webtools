package com.quakearts.utilities;

import java.util.List;

public interface CommandParameter {
	String getName();
	String getValue();
	List<String> getValues();
	void setValue(String value);
}
