package com.quakearts.orm.test;

import java.io.Serializable;

public class ORMTestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6571015626508207L;
	private String id;
	
	public ORMTestObject(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ORMTestObject other = (ORMTestObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
