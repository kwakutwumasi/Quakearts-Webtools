package com.quakearts.workflowapp.jbpm.tag.listener.beans;

import java.util.Date;
import java.util.List;

import org.jbpm.taskmgmt.exe.PooledActor;

public class TaskInstanceReport {
	private String processName;
	private String initiator;
	private String actorFullName;
	private Date initiationDate;
	private List<PooledActor> pooledActors;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getActorFullName() {
		return actorFullName;
	}

	public void setActorFullName(String actorFullName) {
		this.actorFullName = actorFullName;
	}

	public Date getInitiationDate() {
		return initiationDate;
	}

	public void setInitiationDate(Date initiationDate) {
		this.initiationDate = initiationDate;
	}

	public List<PooledActor> getPooledActors() {
		return pooledActors;
	}

	public void setPooledActors(List<PooledActor> pooledActors) {
		this.pooledActors = pooledActors;
	}

}
