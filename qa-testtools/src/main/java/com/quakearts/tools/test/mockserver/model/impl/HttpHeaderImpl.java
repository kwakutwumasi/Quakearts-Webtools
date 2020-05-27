/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.tools.test.mockserver.model.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
		return Objects.hash(name, values);
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
		HttpHeaderImpl other = (HttpHeaderImpl) obj;
		return Objects.equals(name, other.name) && Objects.equals(values, other.values);
	}

	@Override
	public String toString() {
		return "HttpHeaderImpl [name=" + name + ", values=" + values + "]";
	}
}
