package com.quakearts.syshub.webapp.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;

public class AgentConfigurationModuleMappingDropdownHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3727623071541337598L;

	private transient AgentConfigurationModuleMappingFinder finder = new AgentConfigurationModuleMappingFinder();
	
	private List<AgentConfigurationModuleMapping> foundItems;
	public List<AgentConfigurationModuleMapping> getFoundItems() {
		if(foundItems == null)
			foundItems = finder.getAll();
    		return foundItems;
    }
    	
	private AgentConfigurationModuleMapping agentConfigurationModuleMapping;
	
	public AgentConfigurationModuleMapping getAgentConfigurationModuleMapping(){
		if(agentConfigurationModuleMapping == null){
			agentConfigurationModuleMapping = new AgentConfigurationModuleMapping();
			addToFoundItemsList(agentConfigurationModuleMapping);
		}
		
		return agentConfigurationModuleMapping;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(AgentConfigurationModuleMapping agentConfigurationModuleMapping) {
		if(foundItems==null)
			foundItems = new ArrayList<>();
		
		foundItems.add(agentConfigurationModuleMapping);
	}
	
}
