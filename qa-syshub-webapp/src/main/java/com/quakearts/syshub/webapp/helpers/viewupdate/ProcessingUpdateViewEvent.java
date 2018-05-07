package com.quakearts.syshub.webapp.helpers.viewupdate;

import com.quakearts.syshub.agent.event.ProcessingEvent;

public class ProcessingUpdateViewEvent {
	private String exception, agentName, moduleName;

	public ProcessingUpdateViewEvent(ProcessingEvent event) {
		exception = event.getException()!=null?
					event.getException().getMessage():null;
		agentName = event.getAgentConfiguration()!=null? 
					event.getAgentConfiguration().getAgentName():null;
		moduleName = event.getAgentModule()!=null? 
					event.getAgentModule().getModuleName():null;
	}

	public String getException() {
		return exception;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getModuleName() {
		return moduleName;
	}
}
