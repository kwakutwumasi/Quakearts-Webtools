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
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.SysHub;
import com.quakearts.syshub.SysHubMain;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.webapp.helpers.parameter.AgentConfigurationParameterHelper;
import com.quakearts.syshub.webapp.helpers.parameter.AgentModuleDatabase;

@ManagedBean(name="agentConfigurationPage")
@ViewScoped
public class AgentConfigurationPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3139201520089427229L;

	private static Logger log = Logger.getLogger(AgentConfigurationPage.class.getName());

	private SysHub sysHub = SysHubMain.getInstance();
	
	private AgentConfiguration agentConfiguration;
	private WebApplicationMain webappmain;
	private transient AgentConfigurationFinder finder = new AgentConfigurationFinder();
	private AgentConfigurationParameterWrapper queueSize, 
			keepAliveTime, 
			corePoolSize, 
			maxFormatterWorkers, 
			maxDataSpoolerWorkers, 
			maximumPoolSize,
			schedulerCron,
			triggerClass,
			isResendCapable;
	
	public AgentConfigurationPage(){
		webappmain = new WebApplicationMain();
		setupParameters();
	}
	
	private void setupParameters() {
		queueSize = new AgentConfigurationParameterWrapper("queueSize", ParameterType.NUMERIC);
		keepAliveTime = new AgentConfigurationParameterWrapper("keepAliveTime", ParameterType.NUMERIC); 
		corePoolSize= new AgentConfigurationParameterWrapper("corePoolSize", ParameterType.NUMERIC);
		maxFormatterWorkers = new AgentConfigurationParameterWrapper("maxFormatterWorkers", ParameterType.NUMERIC); 
		maxDataSpoolerWorkers = new AgentConfigurationParameterWrapper("maxDataSpoolerWorkers", ParameterType.NUMERIC); 
		maximumPoolSize = new AgentConfigurationParameterWrapper("maximumPoolSize", ParameterType.NUMERIC);
		isResendCapable = new AgentConfigurationParameterWrapper("isResendCapable", ParameterType.NUMERIC);
		schedulerCron = new AgentConfigurationParameterWrapper("scheduler.cron", ParameterType.CRONCONFIGURATION);
		triggerClass = new AgentConfigurationParameterWrapper("trigger.class", ParameterType.CLASS); 
	}
	
	public class AgentConfigurationParameterWrapper {
		private AgentConfigurationParameter configurationParameter;
		private String name;
		private ParameterType type;
		
		public AgentConfigurationParameterWrapper(String name, ParameterType type) {
			this.name = name;
			this.type = type;
			configurationParameter = new AgentConfigurationParameter(name, type);
		}
		
		public AgentConfigurationParameter getConfigurationParameter() {
			return configurationParameter;
		}
		
		public void parameterChanged(AjaxBehaviorEvent event){
			if(agentConfiguration == null)
				return;
			
			if(configurationParameter.getAgentConfiguration()==null){
				configurationParameter.setAgentConfiguration(agentConfiguration);
				agentConfiguration.getParameters().add(configurationParameter);
			}
			
			if(configurationParameter.getId() != 0){
				finder.getDataStore().update(configurationParameter);
			}
		}
		
		public void removeParameter(AjaxBehaviorEvent event){
			if(configurationParameter.getId() != 0){
				finder.getDataStore().delete(configurationParameter);
				configurationParameter = new AgentConfigurationParameter(name, type);
			}
		}
	}
	
	public WebApplicationMain getWebappmain(){
		return webappmain;
	}
	
	public AgentConfiguration getAgentConfiguration() {
		if(agentConfiguration==null){
			if(hasParameter("agentConfiguration")){
				setAgentConfiguration(finder.getById(getParameterInt("agentConfiguration")));
				webappmain.setMode("read");
			} else {
    			agentConfiguration = new AgentConfiguration();
    			if(webappmain.isInSearchMode())
    				agentConfiguration.setType(null);
			}
		}
		
		return agentConfiguration;
	}
    	
	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
		if(agentConfiguration!=null){
			try {
				agentConfiguration.getAgentConfigurationMap();
			} catch (Exception e) {
				agentConfiguration = finder.getDataStore()
						.refresh(agentConfiguration);
				this.agentConfiguration = agentConfiguration;
			}
			
			AgentConfigurationParameter parameter;

			parameter = agentConfiguration.getAgentConfigurationParameter("queueSize");
			if (parameter != null) {
				queueSize.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("keepAliveTime");
			if (parameter != null) {
				keepAliveTime.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("corePoolSize");
			if (parameter != null) {
				corePoolSize.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("maxFormatterWorkers");
			if (parameter != null) {
				maxFormatterWorkers.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("maxDataSpoolerWorkers");
			if (parameter != null) {
				maxDataSpoolerWorkers.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("maximumPoolSize");
			if (parameter != null) {
				maximumPoolSize.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("isResendCapable");
			if (parameter != null) {
				isResendCapable.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("scheduler.cron");
			if (parameter != null) {
				schedulerCron.configurationParameter = parameter;
			}
			
			parameter = agentConfiguration.getAgentConfigurationParameter("trigger.class");
			if (parameter != null) {
				triggerClass.configurationParameter = parameter;
				foundClassNames = new ArrayList<>();
				foundClassNames.add(parameter.getStringValue());
				populateTriggeredAgentRunnerParameters();
			}
		} else {
			setupParameters();
		}
	}
	
	private List<AgentConfiguration> agentConfigurationList;
	
	public List<AgentConfiguration> getAgentConfigurationList(){
		return agentConfigurationList;
	}
	
	public void findAgentConfiguration(ActionEvent event){
		ParameterMapBuilder parameterBuilder = new ParameterMapBuilder();
		if(agentConfiguration.isActive()){
			parameterBuilder.add("active", agentConfiguration.isActive());
		}
		if(agentConfiguration.getAgentName() != null && ! agentConfiguration.getAgentName().trim().isEmpty()){
			parameterBuilder.addVariableString("agentName", agentConfiguration.getAgentName());
		}
		if(agentConfiguration.getType() != null){
			parameterBuilder.add("type", agentConfiguration.getType());
		}
    		
		try {
			agentConfigurationList = finder.findObjects(parameterBuilder.build());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Agent Configuration", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for AgentConfiguration");
		}		
	}
    	
	public void removeAgentConfiguration(ActionEvent event){
		if(agentConfiguration!=null && agentConfigurationList!=null){
			agentConfigurationList.remove(agentConfiguration);
		}
	}
	
	public boolean isDeployed(){
		if(sysHub != null && agentConfiguration != null){
			return sysHub.isDeployed(agentConfiguration);
		}
		
		return false;
	}
	
	public boolean isDeployed(AgentConfiguration agentConfiguration){
		if(sysHub != null){
			return sysHub.isDeployed(agentConfiguration);
		}
		
		return false;
	}
	
	public void deployThisAgent(AjaxBehaviorEvent event) {
		if(agentConfiguration==null || agentConfiguration.getId()==0){
			addError("Invalid Operation", "Save the agent before deployment", FacesContext.getCurrentInstance());
			return;
		}

		if(!agentConfiguration.isActive()){
			addError("Invalid Operation", "Activate the agent before deployment", FacesContext.getCurrentInstance());
			return;
		}

		if(sysHub == null){
			addError("System Error", "Missing reference to SysHub interface", FacesContext.getCurrentInstance());
			return;
		}

		try {
			agentConfiguration.getAgentConfigurationMap(); //Test for lazy loaded parameter access
			agentConfiguration.getAgentModules().size();
		} catch (Throwable e) {
			setAgentConfiguration(finder.getDataStore().refresh(agentConfiguration));
		}
		
		if(sysHub.isDeployed(agentConfiguration)){
			addMessage("No Action Taken", "This agent has already been deployed", FacesContext.getCurrentInstance());
			return;
		}
		
		try {
			sysHub.deployAgent(agentConfiguration);
			addMessage("Success", "Agent has been deployed", FacesContext.getCurrentInstance());
		} catch (ConfigurationException e) {
			addError("Error", "Agent could not be deployed: "+e.getMessage(), 
					FacesContext.getCurrentInstance());
		}
	}
	
	public void unDeployThisAgent(AjaxBehaviorEvent event){
		if(agentConfiguration == null || agentConfiguration.getId()==0){
			addError("Invalid Operation", "Save the agent before deployment", FacesContext.getCurrentInstance());
			return;
		}
		
		if(sysHub == null){
			addError("System Error", "Missing reference to SysHub interface", FacesContext.getCurrentInstance());
			return;
		}

		try {
			agentConfiguration.getAgentModules().size();
		} catch (Exception e) {
			setAgentConfiguration(finder.getDataStore().refresh(agentConfiguration));
		}
		
		if(!sysHub.isDeployed(agentConfiguration)){
			addMessage("No Action Taken", "This agent has already been deployed", FacesContext.getCurrentInstance());
			return;
		}

		sysHub.undeployAgent(agentConfiguration);
		addMessage("Undeployed", "This agent has been undeployed", FacesContext.getCurrentInstance());
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(webappmain.getMode());
	}

	public AgentConfigurationParameterWrapper getQueueSize() {
		return queueSize;
	}

	public AgentConfigurationParameterWrapper getKeepAliveTime() {
		return keepAliveTime;
	}

	public AgentConfigurationParameterWrapper getCorePoolSize() {
		return corePoolSize;
	}

	public AgentConfigurationParameterWrapper getMaxFormatterWorkers() {
		return maxFormatterWorkers;
	}

	public AgentConfigurationParameterWrapper getMaxDataSpoolerWorkers() {
		return maxDataSpoolerWorkers;
	}

	public AgentConfigurationParameterWrapper getMaximumPoolSize() {
		return maximumPoolSize;
	}
	
	public AgentConfigurationParameterWrapper getIsResendCapable() {
		return isResendCapable;
	}
	
	public AgentConfigurationParameterWrapper getTriggerClass() {
		return triggerClass;
	}
	
	public AgentConfigurationParameterWrapper getSchedulerCron() {
		return schedulerCron;
	}
	
	private AgentConfigurationParameterHelper configurationHelper;
	
	public AgentConfigurationParameterHelper getConfigurationHelper() {
		return configurationHelper;
	}

	public void populateTriggeredAgentRunnerParameters(AjaxBehaviorEvent event) {
		if(agentConfiguration == null)
			return;
		
		if (agentConfiguration.getType() == RunType.TRIGGERED 
				&& triggerClass.configurationParameter.getStringValue() != null
				&& !triggerClass.configurationParameter.getStringValue().trim().isEmpty()) {
			triggerClass.parameterChanged(event);
			populateTriggeredAgentRunnerParameters();
		}
	}

	private void populateTriggeredAgentRunnerParameters() {
		try {
			agentConfiguration.getAgentConfigurationMap();
		} catch (Exception e) {
			agentConfiguration = finder.getDataStore().refresh(agentConfiguration);
		}

		try {
			Class<?> clazz = Class.forName(triggerClass.configurationParameter.getStringValue());
			configurationHelper = new AgentConfigurationParameterHelper(clazz, null, agentConfiguration);
		} catch (ClassNotFoundException e) {
			addError("Invalid Data", "Trigger1 Class not found:" + triggerClass.configurationParameter.getStringValue(),
					FacesContext.getCurrentInstance());
		}
	}

	public void clearTriggeredAgentRunnerParameters(AjaxBehaviorEvent event) {
		if (triggerClass.configurationParameter.getId() !=0 ) {
			finder.getDataStore().delete(triggerClass.configurationParameter);
			triggerClass = new AgentConfigurationParameterWrapper("trigger.class", ParameterType.CLASS);
			configurationHelper = null;
		}
	}

	private byte[] data;
	
	private String fileName;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	private String classNameFilter;
		
	public String getClassNameFilter() {
		return classNameFilter;
	}

	public void setClassNameFilter(String classNameFilter) {
		this.classNameFilter = classNameFilter;
	}

	private List<String> foundClassNames;
	
	public List<String> getFoundClassNames() {
		return foundClassNames;
	}
	
	public void filterAgentTriggerClasses(AjaxBehaviorEvent event) {
		if(classNameFilter == null || classNameFilter.trim().isEmpty()){
			return;
		}
		
		if("***".equals(classNameFilter.trim()))
			foundClassNames = AgentModuleDatabase.getInstance().getAgentTriggers();
		else
			foundClassNames = AgentModuleDatabase.getInstance().getAgentTriggers()
				.stream().filter((c)-> c.contains(classNameFilter)).collect(Collectors.toList());
	}
		
	public void validateAgentName(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		if(value == null)
			return;
		
		List<AgentConfiguration> agentConfigurations = finder.findByName(value.toString());
		
		if(!agentConfigurations.isEmpty()){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Data", "An agent with the name "+value+" already exits"));
		}
	}	
}
