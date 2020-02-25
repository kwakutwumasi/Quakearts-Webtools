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
@Table(name="transaction_log")
public class TransactionLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3952142593580586557L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(length=100, nullable=false)
	private String username;
	@Column(length=100, nullable=false)
	private String action;
	@ManyToOne(fetch=FetchType.LAZY)
	private ProcessingLog processingLog;
	@Column(nullable=false)
	private Date tranDt;

	public TransactionLog() {
	}

	public TransactionLog(String username, String action, ProcessingLog processingLog, Date tranDt) {
		this.username = username;
		this.action = action;
		this.processingLog = processingLog;
		this.tranDt = tranDt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ProcessingLog getProcessingLog() {
		return processingLog;
	}

	public void setProcessingLog(ProcessingLog processingLog) {
		this.processingLog = processingLog;
	}

	public Date getTranDt() {
		return this.tranDt;
	}

	public void setTranDt(Date tranDt) {
		this.tranDt = tranDt;
	}

	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(int)(id>0?id:0));
	}
}
