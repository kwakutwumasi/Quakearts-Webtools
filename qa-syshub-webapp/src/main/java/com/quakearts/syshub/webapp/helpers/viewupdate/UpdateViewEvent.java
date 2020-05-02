package com.quakearts.syshub.webapp.helpers.viewupdate;

import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

public class UpdateViewEvent {
	private String updateData;
	private String agentName;
	private String moduleName;
	
	public UpdateViewEvent(String updateData, AgentConfiguration agentConfiguration, AgentModule agentModule) {
		this.updateData = updateData;
		this.agentName = agentConfiguration!=null?agentConfiguration.getAgentName():null;
		this.moduleName = agentModule!=null?agentModule.getModuleName():null;
	}

	public String getUpdateData() {
		return updateData;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getModuleName() {
		return moduleName;
	}
	
}
