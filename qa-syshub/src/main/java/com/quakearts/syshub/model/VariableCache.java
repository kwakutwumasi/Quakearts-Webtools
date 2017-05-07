package com.quakearts.syshub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="variable_cache", schema="dbo")
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

}
