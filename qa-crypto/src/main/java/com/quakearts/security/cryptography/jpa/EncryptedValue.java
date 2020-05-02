package com.quakearts.security.cryptography.jpa;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

import com.quakearts.security.cryptography.CryptoResource;

public class EncryptedValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2984594581882621613L;
	private String dataStoreName;
	private byte[] value;
	private String stringValue;

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
	
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getStringValue() {
		return value!=null?new String(value):stringValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(value);
		result = prime * result + Objects.hash(dataStoreName, stringValue);
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
		EncryptedValue other = (EncryptedValue) obj;
		return Objects.equals(dataStoreName, other.dataStoreName) && Objects.equals(stringValue, other.stringValue)
				&& Arrays.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "EncryptedValue [dataStoreName=" + dataStoreName + ", value=" + CryptoResource.byteAsHex(value) + "]";
	}
	
	
}
