/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.webapp.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.faces.event.AjaxBehaviorEvent;
import com.quakearts.syshub.model.AgentModule;

public class AgentModuleDropdownHelper implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -659723910740869649L;

	private transient AgentModuleFinder finder = new AgentModuleFinder();
	
	private List<AgentModule> foundItems;
	public List<AgentModule> getFoundItems() {
    	return foundItems;
    }
    	
	private AgentModule agentModule;
	
	public AgentModule getAgentModule(){
		if(agentModule == null){
			agentModule = new AgentModule();
			addToFoundItemsList(agentModule);
		}
		
		return agentModule;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(AgentModule agentModule) {
		if(foundItems==null)
			foundItems = new ArrayList<>();
		
		foundItems.add(agentModule);
	}
	
	private String searchText;
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public void filterItems(AjaxBehaviorEvent event){
		if(searchText !=null && !searchText.trim().isEmpty()){
			foundItems = finder.filterByText(searchText);
		}
	}
	
	public void filterMessageFormatterItems(AjaxBehaviorEvent event){
		if(searchText !=null && !searchText.trim().isEmpty()){
			foundItems = finder.filterMessageFormattersByText(searchText);
		}
	}
}
