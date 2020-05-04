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
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="variable_cache")
public class VariableCache implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7183482948134910057L;
	@Id
	@Column(length=100, nullable=false)
	private String appKey;
	@Column(length=4096, nullable=false)
	private byte[] appData;

	public VariableCache() {
	}

	public VariableCache(String appKey) {
		this.appKey = appKey;
	}

	public VariableCache(String appKey, byte[] appData) {
		this.appKey = appKey;
		this.appData = appData;
	}

	public String getAppKey() {
		return this.appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public byte[] getAppData() {
		return this.appData;
	}

	public void setAppData(byte[] appData) {
		this.appData = appData;
	}

	@Override
	public int hashCode() {
		return Objects.hash(appKey);
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
		VariableCache other = (VariableCache) obj;
		return Objects.equals(appKey, other.appKey);
	}

	@Override
	public String toString() {
		return Integer.toHexString(appKey!=null ? appKey.hashCode() : this.hashCode());
	}
}
