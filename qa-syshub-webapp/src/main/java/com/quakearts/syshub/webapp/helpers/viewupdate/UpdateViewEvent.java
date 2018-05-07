package com.quakearts.syshub.webapp.helpers.viewupdate;

import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

public class UpdateViewEvent {
	private String source, agentName, moduleName;
	
	public UpdateViewEvent(String source, AgentConfiguration agentConfiguration, AgentModule agentModule) {
		this.source = source;
		this.agentName = agentConfiguration!=null?agentConfiguration.getAgentName():null;
		this.moduleName = agentModule!=null?agentModule.getModuleName():null;
	}

	public String getSource() {
		return source;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getModuleName() {
		return moduleName;
	}
	
}
