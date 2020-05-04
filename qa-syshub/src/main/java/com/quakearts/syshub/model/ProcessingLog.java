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

import java.io.Serializable;

// Generated Jul 18, 2011 3:16:30 PM by Hibernate Tools 3.3.0.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="processing_log")
public class ProcessingLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2529467718157449441L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long logID;
	@Column(nullable=false, length=50)
	private String mid;
	@Column(length=100)
	private String recipient;
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true)
	private LogType type;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private AgentConfiguration agentConfiguration;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private AgentModule agentModule;
	@Column(nullable=false, length=200)
	private String statusMessage;
	@Column(length=5120, nullable=false)
	private byte[] messageData;
	@Column(nullable=false)
	private long retries;
	@Column(nullable=false)
	private boolean error;
	@Column(nullable=false)
	private Date logDt;
	@OneToMany(mappedBy="processingLog", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	private Set<TransactionLog> transactionLogs = new HashSet<>();

	public enum LogType {
		INFO,
		QUEUED,
		STORED,
		RESENT
	}
	
	public ProcessingLog() {
	}

	public ProcessingLog(String mid, LogType type, AgentConfiguration agentConfiguration, AgentModule agentModule,
			String statusMessage, byte[] messageData, long retries, boolean error, Date logDt) {
		this.mid = mid;
		this.type = type;
		this.agentConfiguration = agentConfiguration;
		this.agentModule = agentModule;
		this.statusMessage = statusMessage;
		this.messageData = messageData;
		this.retries = retries;
		this.error = error;
		this.logDt = logDt;
	}


	public long getLogID() {
		return this.logID;
	}

	public void setLogID(long logID) {
		this.logID = logID;
	}

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public LogType getType() {
		return this.type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	public AgentModule getAgentModule() {
		return agentModule;
	}

	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
	}

	public String getStatusMessage() {
		return this.statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public byte[] getMessageData() {
		return this.messageData;
	}

	public void setMessageData(byte[] messageData) {
		this.messageData = messageData;
	}

	public long getRetries() {
		return this.retries;
	}

	public void setRetries(long retries) {
		this.retries = retries;
	}

	public boolean isError() {
		return this.error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Date getLogDt() {
		return this.logDt;
	}

	public void setLogDt(Date logDt) {
		this.logDt = logDt;
	}

	public Set<TransactionLog> getTransactionLogs() {
		return transactionLogs;
	}

	public void setTransactionLogs(Set<TransactionLog> transactionLogs) {
		this.transactionLogs = transactionLogs;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(mid);
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
		ProcessingLog other = (ProcessingLog) obj;
		return Objects.equals(mid, other.mid);
	}

	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(int)(logID>0?logID:0));
	}
}
