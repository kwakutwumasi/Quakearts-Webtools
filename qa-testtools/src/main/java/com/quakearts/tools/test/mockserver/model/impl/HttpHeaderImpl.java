package com.quakearts.tools.test.mockserver.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.quakearts.tools.test.mockserver.model.HttpHeader;

public class HttpHeaderImpl implements Serializable, HttpHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7866173095474046853L;

	private String name;
	private List<String> values = new ArrayList<>();
	
	public HttpHeaderImpl() {
	}
	
	public HttpHeaderImpl(String name, String value) {
		this.name = name;
		this.values.add(value);
	}

	public HttpHeaderImpl(String name, List<String> values) {
		this.name = name;
		this.values = values;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.tools.test.mockserver.model.HttpHeader#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.tools.test.mockserver.model.HttpHeader#getValue()
	 */
	@Override
	public String getValue() {
		return values.iterator().next();
	}

	public void setValue(String value) {
		this.values.clear();
		this.values.add(value);
	}
	
	public void addValue(String value) {
		values.add(value);
	}
	
	@Override
	public List<String> getValues() {
		return Collections.unmodifiableList(values);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((values == null) ? 0 : values.hashCode());
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
		HttpHeaderImpl other = (HttpHeaderImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (values == null) {
			if (other.values != null)
				return false;
		} else if (!values.equals(other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HttpHeaderImpl [name=" + name + ", values=" + values + "]";
	}
}
