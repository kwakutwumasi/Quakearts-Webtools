package com.quakearts.workflowapp.jbpm.tag.listener.beans;

public class ProcessInstanceReport {
	private String initiatedBy;
	private String initiatedByUsername;
	private String processName;
	
	public String getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(String initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public String getInitiatedByUsername() {
		return initiatedByUsername;
	}

	public void setInitiatedByUsername(String initiatedByUsername) {
		this.initiatedByUsername = initiatedByUsername;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
