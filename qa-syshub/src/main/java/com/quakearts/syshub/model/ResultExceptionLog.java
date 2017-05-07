package com.quakearts.syshub.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private AgentConfiguration agentConfiguration;
	@Column(length=4096, nullable=false)
	private byte[] resultData;
	@Column(length=100, nullable=false)
	private String exceptionType;
	@Column(length=4096, nullable=false)
	private byte[] exceptionData;
	@Column(length=100, nullable=false)
	private String spoolerType;
	@Column(nullable=false)
	private Date exceptionDt = new Date();

	public ResultExceptionLog() {
	}

	public ResultExceptionLog(AgentConfiguration agentConfiguration, byte[] resultData,
			String exceptionType, byte[] exceptionData, String spoolerType) {
		this.agentConfiguration = agentConfiguration;
		this.resultData = resultData;
		this.exceptionType = exceptionType;
		this.exceptionData = exceptionData;
		this.spoolerType = spoolerType;
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

	public String getSpoolerType() {
		return this.spoolerType;
	}

	public void setSpoolerType(String spoolerType) {
		this.spoolerType = spoolerType;
	}

	public Date getExceptionDt() {
		return exceptionDt;
	}

	public void setExceptionDt(Date exceptionDt) {
		this.exceptionDt = exceptionDt;
	}
}
