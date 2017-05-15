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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="result_exception_log", schema="dbo")
public class ResultExceptionLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7363234346810491168L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private AgentConfiguration agentConfiguration;
	@Column(length=4096, nullable=false)
	private byte[] resultData;
	@Column(length=100, nullable=false)
	private String exceptionType;
	@Column(length=4096, nullable=false)
	private byte[] exceptionData;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private AgentModule agentModule;
	@Column(nullable=false)
	private Date exceptionDt = new Date();

	public ResultExceptionLog() {
	}

	public ResultExceptionLog(AgentConfiguration agentConfiguration, byte[] resultData, String exceptionType,
			byte[] exceptionData, AgentModule agentModule, Date exceptionDt) {
		this.agentConfiguration = agentConfiguration;
		this.resultData = resultData;
		this.exceptionType = exceptionType;
		this.exceptionData = exceptionData;
		this.agentModule = agentModule;
		this.exceptionDt = exceptionDt;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	public byte[] getResultData() {
		return this.resultData;
	}

	public void setResultData(byte[] resultData) {
		this.resultData = resultData;
	}

	public String getExceptionType() {
		return this.exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public byte[] getExceptionData() {
		return this.exceptionData;
	}

	public void setExceptionData(byte[] exceptionData) {
		this.exceptionData = exceptionData;
	}

	public AgentModule getAgentModule() {
		return agentModule;
	}

	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
	}

	public Date getExceptionDt() {
		return exceptionDt;
	}

	public void setExceptionDt(Date exceptionDt) {
		this.exceptionDt = exceptionDt;
	}
	
	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(int)(id>0?id:0));
	}
}
