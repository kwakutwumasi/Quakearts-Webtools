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
package com.quakearts.syshub.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="max_id")
public class MaxID implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -671685671840967969L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false, length=50)
	private String maxIDName;
	@Column(nullable=false, length=50)
	private long maxIDValue;

	public MaxID() {
	}

	public MaxID(String maxPtidName) {
		this.maxIDName = maxPtidName;
	}

	public MaxID(String maxIDName, long maxIDValue) {
		this.maxIDName = maxIDName;
		this.maxIDValue = maxIDValue;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getMaxIDName() {
		return maxIDName;
	}

	public void setMaxIDName(String maxIDName) {
		this.maxIDName = maxIDName;
	}

	public long getMaxIDValue() {
		return maxIDValue;
	}

	public void setMaxIDValue(long maxIDValue) {
		this.maxIDValue = maxIDValue;
	}

	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(id>0?id:0));
	}

	@Override
	public int hashCode() {
		return Objects.hash(maxIDName);
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
		MaxID other = (MaxID) obj;
		return Objects.equals(maxIDName, other.maxIDName);
	}
}
