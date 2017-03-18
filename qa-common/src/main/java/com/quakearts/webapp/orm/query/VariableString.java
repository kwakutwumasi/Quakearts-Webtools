package com.quakearts.webapp.orm.query;

import java.io.Serializable;

public class VariableString implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5170445172572016710L;
	private String value;

	public VariableString(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}