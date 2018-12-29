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
package com.quakearts.test.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8572545408111725259L;
	private int id;
	private String name;
	private String description;
	private List<String> otherNotes;
	private Map<String, String> otherDetails;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getOtherNotes() {
		return otherNotes;
	}

	public void setOtherNotes(List<String> otherDetails) {
		this.otherNotes = otherDetails;
	}

	public Map<String, String> getOtherDetails() {
		return otherDetails;
	}
	
	public void setOtherDetails(Map<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}
}
