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
package com.quakearts.syshub.webapp.helpers.parameter;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.naming.NamingException;

import org.quartz.CronExpression;

import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.core.utils.SysHubUtils;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.webapp.beans.AgentConfigurationFinder;
import com.quakearts.syshub.webapp.beans.SysHubDataStoreUser;

public class AgentConfigurationParameterHelper implements SysHubDataStoreUser {
	private List<ParameterMetaData> parameterMetaDataList = new ArrayList<>();
	private static final String EMAILPATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
			+ "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
			+ "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")"
			+ "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+"
			+ "[a-z0-9]"
			+ "(?:[a-z0-9-]*[a-z0-9])?"
			+ "|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
			+ "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:"
			+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
			+ "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	public AgentConfigurationParameterHelper(Class<?> configurableClass, AgentModule module,
			AgentConfiguration agentConfiguration) {		
		ConfigurationProperty classProperty = configurableClass.getAnnotation(ConfigurationProperty.class);
		if(classProperty != null){
			parameterMetaDataList.add(new ParameterMetaData(classProperty, module, agentConfiguration));
		}
		if(classProperty == null){
			ConfigurationProperties properties = configurableClass.getAnnotation(ConfigurationProperties.class);
			
			if(properties!=null)
				for(ConfigurationProperty property:properties.value()){
					parameterMetaDataList.add(new ParameterMetaData(property, module, agentConfiguration));
				}
		}
	}
	
	public static final class ParameterMetaData {
		private static final String PARAMETER = "Parameter ";
		private static final String INVALID_DATA = "Invalid Data";
		ConfigurationProperty property;
		AgentConfigurationParameter agentConfigurationParameter;
		AgentModule module;
		AgentConfiguration agentConfiguration;
		AgentConfigurationFinder finder = new AgentConfigurationFinder();
		
		public ParameterMetaData(ConfigurationProperty property, AgentModule module,
				AgentConfiguration agentConfiguration) {
			this.property = property;
			this.module = module;
			this.agentConfiguration = agentConfiguration;
			
			Map<String, AgentConfigurationParameter> parameters;
			if(module != null)
				parameters = module.getModuleConfigurationMap();
			else
				parameters = agentConfiguration.getAgentConfigurationMap();
			
			if(parameters != null && parameters.containsKey(property.value())) {
				agentConfigurationParameter = parameters.get(property.value());
			} else {
				agentConfigurationParameter = new AgentConfigurationParameter();
				agentConfigurationParameter.setName(property.value());
				agentConfigurationParameter.setParameterType(property.type());
				agentConfigurationParameter.setGlobal(property.global());
			}
		}

		public AgentConfigurationParameter getAgentConfigurationParameter() {
			return agentConfigurationParameter;
		}

		public boolean isRequired() {
			return property.required();
		}

		public ParameterType getType() {
			return property.type();
		}
		
		public String[] getOptionList() {
			return property.optionList();
		}

		public String getPattern() {
			return property.pattern();
		}

		public String getDescription() {
			return property.description();
		}

		public String getFriendlyName() {
			return property.friendlyName() != null 
					&& !property.friendlyName().trim().isEmpty()?
							property.friendlyName():property.value();
		}

		public Class<?> getAssignableType() {
			return property.assignableType();
		}
		
		public boolean isGlobal(){
			return property.global();
		}
		
		public void parameterChanged(AjaxBehaviorEvent event) {
			if(agentConfigurationParameter.getAgentConfiguration()==null){
				if(module != null){
					agentConfigurationParameter.setAgentModule(module);
					module.getParameters().add(agentConfigurationParameter);
				} 
				
				if(agentConfiguration != null){
					agentConfigurationParameter.setAgentConfiguration(agentConfiguration);
					if(module == null)
						agentConfiguration.getParameters().add(agentConfigurationParameter);
				}
			}
			
			if(agentConfigurationParameter.getId() != 0){
				finder.getDataStore().update(agentConfigurationParameter);
			}
		}
		
		public void removeParameter(AjaxBehaviorEvent event){
			if(agentConfigurationParameter.getId() != 0){
				finder.getDataStore().delete(agentConfigurationParameter);
				if(module != null)
					module.getParameters().remove(agentConfigurationParameter);
				else
					agentConfiguration.getParameters().remove(agentConfigurationParameter);

				agentConfigurationParameter = new AgentConfigurationParameter(property.value(), property.type());
			}
		}
		
		public void validateParameter(FacesContext context, UIComponent component, Object value) {
			if(isRequired() && (value == null 
					|| value.toString().trim().isEmpty())){
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
					+(getFriendlyName()==null?property.value():getFriendlyName())+" is required."));
			}
			
			if(value == null)
				return;
			
			if(getPattern() != null && !getPattern().trim().isEmpty() 
					&& !value.toString().matches(getPattern()))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" does not match required pattern: "+getPattern()));
							
			switch (getType()) {
			case CLASS:
				validateClass(value);
				break;
			case CRONCONFIGURATION:
				validateCRONPattern(value);
				break;
			case EMAIL:
				validateEmail(value);
				break;
			case ENDPOINTADDRESS:
				validateEndpointURL(value);
				break;
			case FILE:
				validateFile(value);
				break;
			case JNDINAME:
				validateJNDIName(value);
				break;
			default:
				break;
			}
		}

		private void validateClass(Object value) {
			Class<?> loadedClass;
			try {
				loadedClass = Class.forName(value.toString());
			} catch (ClassNotFoundException e) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())+" is not valid class name"));
			}
			
			if(!property.assignableType().isAssignableFrom(loadedClass))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())+" is not a "
						+property.assignableType().getName()));
		}

		private void validateCRONPattern(Object value) {
			if(!CronExpression.isValidExpression(value.toString()))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" is not a valid Cron expression"));
		}

		private void validateEmail(Object value) {
			String email = value.toString().trim();
			if(email.contains(";") || email.contains(",")){
				validateMultipleEmails(email);
			} else {
				validateSingleEmail(email);
			}
		}

		private void validateMultipleEmails(String email) {
			String[] emails = email.split("[;,]");
			for(String emailToCheck:emails){
				validateSingleEmail(emailToCheck);
			}
		}

		private void validateSingleEmail(String email) {
			if(!email.matches(EMAILPATTERN))
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" is not a valid email"));
		}

		private void validateEndpointURL(Object value) {
			try {
				new URL(value.toString());
			} catch (MalformedURLException e) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" is not a valid url: "+e.getMessage()));
			}
		}

		private void validateFile(Object value) {
			if(!new File(value.toString()).exists())
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" is not a file that exits"));
		}

		private void validateJNDIName(Object value) {
			try {
				SysHubUtils.getInitialContext().lookup(value.toString());
			} catch (NamingException e) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, INVALID_DATA,PARAMETER
						+(getFriendlyName()==null?property.value():getFriendlyName())
						+" is not bound."));
			}
		}
	}
	
	public List<ParameterMetaData> getParameterMetaDataList() {
		return parameterMetaDataList;
	}
}
