package com.quakearts.utilities.impl;

import java.util.ArrayList;
import java.util.List;

import com.quakearts.utilities.CommandParameter;

public class CommandParameterImpl implements CommandParameter {

	private String name; 
	List<String> values = new ArrayList<>();
	
	public CommandParameterImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getValue() {
		return values.isEmpty()?null:values.get(0);
	}

	@Override
	public void setValue(String value) {
		this.values.add(value);
	}

	@Override
	public List<String> getValues() {
		return values;
	}
}
