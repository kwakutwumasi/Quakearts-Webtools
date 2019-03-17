package com.quakearts.security.cryptography.jpa;

import java.io.Serializable;

public class EncryptedValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2984594581882621613L;
	private String dataStoreName;
	private byte[] value;

	public String getDataStoreName() {
		return dataStoreName;
	}

	public void setDataStoreName(String dataStoreName) {
		this.dataStoreName = dataStoreName;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
	
	public void setStringValue(String value) {
		setValue(value!=null? value.getBytes():null);
	}

	public String getStringValue() {
		return value!=null?new String(value):null;
	}
	
}
