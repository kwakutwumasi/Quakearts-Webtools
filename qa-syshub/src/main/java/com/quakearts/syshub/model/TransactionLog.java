package com.quakearts.syshub.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="transaction_log", schema="dbo")
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
	@ManyToOne(fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
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

}
