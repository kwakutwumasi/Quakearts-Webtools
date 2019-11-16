package com.quakearts.webapp.orm.query;

import java.io.Serializable;

public class Not implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1914247278147256542L;
	private Serializable value;
	
	public Not(Serializable value) {
		this.value = value;
	}

	public Serializable getValue() {
		return value;
	}
}
