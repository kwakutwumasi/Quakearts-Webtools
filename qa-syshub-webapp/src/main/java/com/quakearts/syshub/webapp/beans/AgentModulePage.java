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
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
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
import com.quakearts.syshub.model.AgentConfigurationParameter;

@Named("agentModulePage")
@ViewScoped
public class AgentModulePage extends BaseBean {

	private static final String AGENT_CLASS_IS_NOT_A = "Agent Class is not a ";

	private static final String INVALID_DATA = "Invalid Data";

	private static final String AGENT_CONFIGURATION = "agentConfiguration";

	/**
	 * 
	 */
	private static final long serialVersionUID = 903163592767371710L;

	private static Logger log = Logger.getLogger(AgentModulePage.class.getName());

	private AgentModule agentModule;
	@Inject @Named("webappmain")
	private WebApplicationMain webappmain;
	private transient AgentModuleFinder finder = new AgentModuleFinder();

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

	public void validateAgentName(FacesContext context, UIComponent component, Object value) {

		if(value == null || webappmain.isInSearchMode())
			return;
		
		if(agentModule == null 
				|| agentModule.getAgentConfiguration() == null 
				|| agentModule.getAgentConfiguration().getId()<= 0)
			throw new ValidatorException(new FacesMessage("Invalid Selection", "Select agent configuration to continue"));
		
		List<AgentModule> sameNamedModules = finder.findObjects(CriteriaMapBuilder.createCriteria()
				.property(AGENT_CONFIGURATION).mustBeEqualTo(agentModule.getAgentConfiguration())
				.property("moduleName").mustBeEqualTo(value.toString()).finish());
		
		if(!sameNamedModules.isEmpty())
			throw new ValidatorException(new FacesMessage(INVALID_DATA, "A module already exists with the name "+value));
	}
	
	public void moduleNameChanged(AjaxBehaviorEvent event) {
		if(agentModule != null && agentModule.getId() != 0 
				&& agentModule.getModuleType() == ModuleType.FORMATTER){
			DataStore store = finder.getDataStore();
			AgentModule oldAgentModule = finder.getById(agentModule.getId());//Reload
			
			List<AgentModule> mappedModules = finder.findObjects(CriteriaMapBuilder.createCriteria()
					.property(AGENT_CONFIGURATION).mustBeEqualTo(agentModule.getAgentConfiguration())
					.property("mappedModuleName").mustBeEqualTo(oldAgentModule.getModuleName())
					.finish());

			if(!mappedModules.isEmpty()){
				oldAgentModule.setModuleName(agentModule.getModuleName());
				agentModule = oldAgentModule;
				store.save(agentModule);
				store.clearBuffers();
				
				for(AgentModule agentModuleItem : mappedModules){
					agentModuleItem.setMappedModuleName(agentModuleItem.getModuleName());
					store.save(agentModuleItem);
				}
			}
		}
	}
	
	private transient List<String> foundClassNames;
	
	public List<String> getFoundClassNames() {
		return foundClassNames;
	}
	
	private String classNameFilter;
	
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
				foundClassNames = stringsToFilter.stream().filter(c-> c.contains(classNameFilter)).collect(Collectors.toList());
		}
	}
	
	private transient AgentConfigurationParameterHelper configurationHelper;
	
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
			addError(INVALID_DATA, "Agent Class not found: "+getAgentModule().getModuleClassName(), FacesContext.getCurrentInstance());
		}
	}
	
	public void validateAgentClass(FacesContext context, UIComponent component, Object value) {
		if (getAgentModule().getModuleType() == null)
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Missing Module Type",
					"Select module type to continue"));

		if (value == null)
			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA, "Agent Class Name cannot be null"));

		Class<?> moduleClass;
		try {
			moduleClass = Class.forName(value.toString());
		} catch (ClassNotFoundException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,
					"Agent Class named " + value + " not found"));
		}

		switch (getAgentModule().getModuleType()) {
		case DATASPOOLER:
			if (!DataSpooler.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,
						AGENT_CLASS_IS_NOT_A + DataSpooler.class.getName()));

			break;
		case FORMATTER:
			if (!MessageFormatter.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,
						AGENT_CLASS_IS_NOT_A + MessageFormatter.class.getName()));

			break;
		case MESSENGER:
			if (!Messenger.class.isAssignableFrom(moduleClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,
						AGENT_CLASS_IS_NOT_A + Messenger.class.getName()));

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
		if(webappmain.isInSearchMode() && agentModuleList == null) {
			agentModuleList = finder.getAll();
		}
		return agentModuleList;
	}

	public void findAgentModule(ActionEvent event) {
		CriteriaMapBuilder criteriaMapBuilder = CriteriaMapBuilder.createCriteria();
		if (agentModule.getModuleClassName() != null && !agentModule.getModuleClassName().trim().isEmpty()) {
			criteriaMapBuilder.property("agentClassName").mustBeLike(agentModule.getModuleClassName());
		}
		
		if (agentModule.getAgentConfiguration() != null) {
			criteriaMapBuilder.property(AGENT_CONFIGURATION).mustBeEqualTo(agentModule.getAgentConfiguration());
		}
		
		if (agentModule.getMappedModuleName() != null && !agentModule.getMappedModuleName().trim().isEmpty()) {
			criteriaMapBuilder.property("mappedModuleName").mustBeLike(agentModule.getMappedModuleName());
		}
		
		if (agentModule.getModuleName() != null && !agentModule.getModuleName().trim().isEmpty()) {
			criteriaMapBuilder.property("moduleName").mustBeLike(agentModule.getModuleName());
		}
		
		if (agentModule.getModuleType() != null) {
			criteriaMapBuilder.property("moduleType").mustBeEqualTo(agentModule.getModuleType());
		}

		try {
			agentModuleList = finder.findObjects(criteriaMapBuilder.finish());
			
			agentModuleList.forEach(this::lazyLoadAgentModuleConfigurationMap);
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Agent Module",
					FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for AgentModule");
		}
	}

	protected Map<String, AgentConfigurationParameter> lazyLoadAgentModuleConfigurationMap(AgentModule agentModule) {
		return agentModule.getModuleConfigurationMap();
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
