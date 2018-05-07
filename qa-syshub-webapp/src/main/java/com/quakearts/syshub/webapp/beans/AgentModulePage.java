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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;
import com.quakearts.syshub.webapp.helpers.parameter.AgentConfigurationParameterHelper;
import com.quakearts.syshub.webapp.helpers.parameter.AgentModuleDatabase;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.model.AgentConfiguration;

@ManagedBean(name = "agentModulePage")
@ViewScoped
public class AgentModulePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 903163592767371710L;

	private static Logger log = Logger.getLogger(AgentModulePage.class.getName());

	private AgentModule agentModule;
	private WebApplicationMain webappmain;
	private transient AgentModuleFinder finder = new AgentModuleFinder();

	public AgentModulePage() {
		webappmain = new WebApplicationMain();
	}

	public WebApplicationMain getWebappmain() {
		return webappmain;
	}

	public AgentModule getAgentModule() {
		if (agentModule == null) {
			if (hasParameter("agentModule")) {
				webappmain.setMode("read");
				setAgentModule(finder.getById(getParameterInt("agentModule")));
			} else {
				agentModule = new AgentModule();
			}
		}

		return agentModule;
	}
	
	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
		if (agentModule != null) {
			AgentConfiguration agentConfiguration = agentModule.getAgentConfiguration();
			if (agentConfiguration != null) {
				getAgentConfigurationDropdownHelper().addToFoundItemsList(agentConfiguration);
			}
			if (agentModule.getModuleClassName() != null){
				foundClassNames = new ArrayList<>();
				foundClassNames.add(agentModule.getModuleClassName());
				populateModuleParameters(null);
			}
		} else {
			if(configurationHelper != null)
				configurationHelper = null;
			if(foundClassNames != null)
				foundClassNames = null;
		}
	}

	public void validateAgentName(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {

		if(value == null)
			return;
		
		if(agentModule == null 
				|| agentModule.getAgentConfiguration() == null 
				|| agentModule.getAgentConfiguration().getId()<= 0)
			throw new ValidatorException(new FacesMessage("Invalid Selection", "Select agent configuration to continue"));
		
		List<AgentModule> sameNamedModules = finder.findObjects(new ParameterMapBuilder()
				.add("agentConfiguration", agentModule.getAgentConfiguration())
				.add("moduleName", value.toString()).build());
		
		if(!sameNamedModules.isEmpty())
			throw new ValidatorException(new FacesMessage("Invalid Data", "A module already exists with the name "+value));
	}
	
	public void moduleNameChanged(AjaxBehaviorEvent event) {
		if(agentModule != null && agentModule.getId() != 0 
				&& agentModule.getModuleType() == ModuleType.FORMATTER){
			DataStore store = finder.getDataStore();
			AgentModule oldAgentModule = finder.getById(agentModule.getId());//Reload
			
			List<AgentModule> mappedModules = finder.findObjects(new ParameterMapBuilder().add("agentConfiguration", agentModule.getAgentConfiguration())
					.add("mappedModuleName", oldAgentModule.getModuleName()).build());

			if(!mappedModules.isEmpty()){
				oldAgentModule.setModuleName(agentModule.getModuleName());
				agentModule = oldAgentModule;
				store.save(agentModule);
				store.clearBuffers();
				
				for(AgentModule agentModule : mappedModules){
					agentModule.setMappedModuleName(agentModule.getModuleName());
					store.save(agentModule);
				}
			}
		}
	}
	
	public List<String> foundClassNames;
	
	public List<String> getFoundClassNames() {
		return foundClassNames;
	}
	
	public String classNameFilter;
	
	public String getClassNameFilter() {
		return classNameFilter;
	}

	public void setClassNameFilter(String classNameFilter) {
		this.classNameFilter = classNameFilter;
	}

	public void filterAgentClasses(AjaxBehaviorEvent event) {
		if(classNameFilter == null || classNameFilter.trim().isEmpty()){
			return;
		}
		
		List<String> stringsToFilter = null;
		if(getAgentModule() != null && getAgentModule().getModuleType() != null){
			switch (getAgentModule().getModuleType()) {
			case DATASPOOLER:
				stringsToFilter = AgentModuleDatabase.getInstance().getDataSpoolers();
				break;
			case FORMATTER:
				stringsToFilter = AgentModuleDatabase.getInstance().getMessageFormatters();
				break;
			case MESSENGER:
				stringsToFilter = AgentModuleDatabase.getInstance().getMessengers();
				break;
			default:
				return;
			}
		}
				
		if(stringsToFilter != null){
			if("***".equals(classNameFilter.trim()))
				foundClassNames = stringsToFilter;
			else
				foundClassNames = stringsToFilter.stream().filter((c)-> c.contains(classNameFilter)).collect(Collectors.toList());
		}
	}
	
	private AgentConfigurationParameterHelper configurationHelper;
	
	public AgentConfigurationParameterHelper getConfigurationHelper() {
		return configurationHelper;
	}
	
	public void populateModuleParameters(AjaxBehaviorEvent event) {
		configurationHelper = null;
		
		if(agentModule == null)
			return;
		
		if(agentModule.getModuleClassName() == null || agentModule.getModuleClassName().trim().isEmpty())
			return;
		
		if(agentModule.getAgentConfiguration() == null || agentModule.getAgentConfiguration().getId()<= 0)
			return;
				
		Class<?> moduleClass;
		try {
			moduleClass = Class.forName(agentModule.getModuleClassName());
			configurationHelper = new AgentConfigurationParameterHelper(moduleClass, agentModule, agentModule.getAgentConfiguration());
		} catch (ClassNotFoundException e) {
			addError("Invalid Data", "Agent Class not found: "+getAgentModule().getModuleClassName(), FacesContext.getCurrentInstance());
		}
	}
	
	public void validateAgentClass(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		if (getAgentModule().getModuleType() == null)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Module Type",
					"Select module type to continue"));

		if (value == null)
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data", "Agent Class Name cannot be null"));

		Class<?> moduleClass;
		try {
			moduleClass = Class.forName(value.toString());
		} catch (ClassNotFoundException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data",
					"Agent Class named " + value + " not found"));
		}

		switch (getAgentModule().getModuleType()) {
		case DATASPOOLER:
			if (!DataSpooler.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data",
						"Agent Class is not a " + DataSpooler.class.getName()));

			break;
		case FORMATTER:
			if (!MessageFormatter.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data",
						"Agent Class is not a " + MessageFormatter.class.getName()));

			break;
		case MESSENGER:
			if (!Messenger.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data",
						"Agent Class is not a " + Messenger.class.getName()));

			break;
		default:
			break;
		}
	}

	private AgentConfigurationDropdownHelper agentConfigurationDropdownHelper;

	public AgentConfigurationDropdownHelper getAgentConfigurationDropdownHelper() {
		if (agentConfigurationDropdownHelper == null)
			agentConfigurationDropdownHelper = new AgentConfigurationDropdownHelper();

		return agentConfigurationDropdownHelper;
	}

	private List<AgentModule> agentModuleList;

	public List<AgentModule> getAgentModuleList() {
		return agentModuleList;
	}

	public void findAgentModule(ActionEvent event) {
		ParameterMapBuilder parameterBuilder = new ParameterMapBuilder();
		if (agentModule.getModuleClassName() != null && !agentModule.getModuleClassName().trim().isEmpty()) {
			parameterBuilder.addVariableString("agentClassName", agentModule.getModuleClassName());
		}
		if (agentModule.getAgentConfiguration() != null) {
			parameterBuilder.add("agentConfiguration", agentModule.getAgentConfiguration());
		}
		if (agentModule.getMappedModuleName() != null && !agentModule.getMappedModuleName().trim().isEmpty()) {
			parameterBuilder.addVariableString("mappedModuleName", agentModule.getMappedModuleName());
		}
		if (agentModule.getModuleName() != null && !agentModule.getModuleName().trim().isEmpty()) {
			parameterBuilder.addVariableString("moduleName", agentModule.getModuleName());
		}
		if (agentModule.getModuleType() != null) {
			parameterBuilder.add("moduleType", agentModule.getModuleType());
		}

		try {
			agentModuleList = finder.findObjects(parameterBuilder.build());
			
			agentModuleList.forEach((agentModule)->{
				agentModule.getModuleConfigurationMap();
			});
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Agent Module",
					FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for AgentModule");
		}
	}

	public void removeAgentModule(ActionEvent event) {
		if (agentModule != null && agentModuleList != null) {
			agentModuleList.remove(agentModule);
		}
	}

	public boolean isInCreateOrEditMode() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml")
				|| "edit".equals(webappmain.getMode());
	}
}
