/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.agent.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.factory.DataSpoolerFactory;
import com.quakearts.syshub.core.factory.MessageFormatterFactory;
import com.quakearts.syshub.core.factory.MessengerFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;

@Vetoed
public class ProcessingAgentBuilder {

	private static final String QUEUE_SIZE = "queueSize";
	private static final String KEEP_ALIVE_TIME = "keepAliveTime";
	private static final String CORE_POOL_SIZE = "corePoolSize";
	private static final String MAX_FORMATTER_WORKERS = "maxFormatterWorkers";
	private static final String MAX_DATA_SPOOLER_WORKERS = "maxDataSpoolerWorkers";
	private static final String MAXIMUM_POOL_SIZE = "maximumPoolSize";
	private static final String IS_RESEND_CAPABLE = "isResendCapable";
	private AgentConfiguration configuration;
	private AgentModule agentModule;
	private static final Logger log = LoggerFactory.getLogger(ProcessingAgentBuilder.class);
	
	public ProcessingAgentBuilder() {
		configuration = new AgentConfiguration();
	}
	
	public ProcessingAgentBuilder(AgentConfiguration configuration) {
		this.configuration = configuration;
	}
	
	public AgentConfiguration getConfiguration() {
		return configuration;
	}
	
	public ProcessingAgent build() throws ConfigurationException {
		log.trace("Setting up....");
		
		ProcessingAgent agent = CDI.current().select(ProcessingAgent.class).get();
		
		if(configuration.getAgentName()==null||configuration.getAgentName().trim().isEmpty())
			throw new ConfigurationException("Configuration Agent Name is required");
		
		agent.setName(configuration.getAgentName());
		agent.setAgentConfiguration(configuration);
		
		List<AgentModule> agentModules = new ArrayList<>(configuration.getAgentModules());
		
		for(AgentConfigurationModuleMapping configurationModuleMapping:
				configuration.getAgentConfigurationModuleMappings()) {
			agentModules.add(configurationModuleMapping.getAgentModule());
		}
		
		Collections.sort(agentModules, (a1,a2)->a1.getModuleType().compareTo(a2.getModuleType()));
		
		Map<String, MessageFormatter> messageFormatters = new HashMap<>();
		Map<Messenger, MessageFormatter> messengerFormatterPairs = new HashMap<>();
		List<DataSpooler> dataSpoolers = new ArrayList<>();
		
		for (AgentModule agentModule : agentModules){
			Map<String, AgentConfigurationParameter> parameters = configuration.getAgentModuleConfigurationMap(agentModule);
			
			if(parameters == null)
				parameters = configuration.getAgentConfigurationMap();
			
			switch (agentModule.getModuleType()) {
			case DATASPOOLER:
				dataSpoolers.add(DataSpoolerFactory.getFactory().getInstance(parameters, 
						agentModule));
				break;
			case FORMATTER:
				messageFormatters.put(agentModule.getModuleName() != null 
				&& !agentModule.getModuleName().trim().isEmpty()?
						agentModule.getModuleName():agentModule.getModuleClassName(), 
						MessageFormatterFactory.getFactory().getInstance(parameters, 
								agentModule));
				break;
			case MESSENGER:
				Messenger messenger = MessengerFactory.getFactory().getInstance(parameters,
						agentModule);
				if(agentModule.getMappedModuleName() != null && !agentModule.getMappedModuleName().trim().isEmpty()) {
					MessageFormatter formatter = messageFormatters.get(agentModule.getMappedModuleName());
					if(formatter == null)
						throw new ConfigurationException("No formatter module named "+agentModule.getMappedModuleName()
							+" to send data for messenger module "+(agentModule.getModuleName()!=null?
									agentModule.getModuleName():agentModule.getModuleClassName()));
					
					messengerFormatterPairs.put(messenger, formatter);
				} else {
					for(MessageFormatter formatter: messageFormatters.values()){
						if(messenger.isCompatibleWith(formatter)){
							messengerFormatterPairs.put(messenger, formatter);
							break;
						}
					}
				}
				break;
			}
		}
		
		if(messengerFormatterPairs.isEmpty())
			throw new ConfigurationException("No Messenger/MessageFormatter pairs to send data");

		agent.setMessengerFormatterMapper(messengerFormatterPairs);
		
		if(dataSpoolers.isEmpty())
			throw new ConfigurationException("No data spooler to spool data.");

		agent.setDataSpoolers(dataSpoolers);
		
		if (configuration.getAgentConfigurationParameter(MAX_DATA_SPOOLER_WORKERS) != null) {
			try {
				agent.setMaxDataSpoolerWorkers(configuration.getAgentConfigurationParameter(MAX_DATA_SPOOLER_WORKERS)
						.getNumericValue().intValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: maxDataSpoolerWorkers.");
			}
		}
		
		if (configuration.getAgentConfigurationParameter(MAX_FORMATTER_WORKERS) != null) {
			try {
				agent.setMaxFormatterMessengerWorkers(configuration.getAgentConfigurationParameter(MAX_FORMATTER_WORKERS)
						.getNumericValue().intValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: maxFormatterWorkers.");
			}
		}

        if(configuration.getAgentConfigurationParameter(MAXIMUM_POOL_SIZE)!=null){
	        try {
	        	agent.setMaximumPoolSize(configuration.getAgentConfigurationParameter(MAXIMUM_POOL_SIZE).getNumericValue().intValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: maximumPoolSize");
			}
        }

        if(configuration.getAgentConfigurationParameter(CORE_POOL_SIZE)!=null){
	        try {
				agent.setCorePoolSize(configuration.getAgentConfigurationParameter(CORE_POOL_SIZE).getNumericValue().intValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: corePoolSize.");
			}
        }

        if(configuration.getAgentConfigurationParameter(KEEP_ALIVE_TIME)!=null){
	        try {
	        	agent.setKeepAliveTime(configuration.getAgentConfigurationParameter(KEEP_ALIVE_TIME).getNumericValue().longValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: keepAliveTime.");
			}
        }

        if(configuration.getAgentConfigurationParameter(QUEUE_SIZE)!=null){
	        try {
	        	agent.setQueueSize(configuration.getAgentConfigurationParameter(QUEUE_SIZE).getNumericValue().intValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: queueSize.");
			}
        }
        
        if(configuration.getAgentConfigurationParameter(IS_RESEND_CAPABLE)!=null){
	        try {
	        	agent.setResendCapable(configuration.getAgentConfigurationParameter(IS_RESEND_CAPABLE).getBooleanValue());
			} catch (NullPointerException e) {
				throw new ConfigurationException("Invalid property: queueSize.");
			}
        }
		
		return agent;
	}
	
	public ProcessingAgentBuilder name(String name){
		configuration.setAgentName(name);
		return this;
	}
	
	public ProcessingAgentBuilder dataSpooler(Class<? extends DataSpooler> dataSpoolerClass, String moduleName){
		return createAgentModule(ModuleType.DATASPOOLER, dataSpoolerClass.getName(), moduleName, null);
	}

	public ProcessingAgentBuilder messageFormatter(Class<? extends MessageFormatter> messageFormatterClass, String moduleName){
		return createAgentModule(ModuleType.FORMATTER, messageFormatterClass.getName(), moduleName, null);
	}

	public ProcessingAgentBuilder messenger(Class<? extends Messenger> messengerClass, String moduleName){
		return createAgentModule(ModuleType.MESSENGER, messengerClass.getName(), moduleName, null);
	}

	public ProcessingAgentBuilder messenger(Class<? extends Messenger> messengerClass, String moduleName, String mappedModuleName){
		return createAgentModule(ModuleType.MESSENGER, messengerClass.getName(), moduleName, mappedModuleName);
	}

	private ProcessingAgentBuilder createAgentModule(ModuleType type, String className, String moduleName, String mappedModuleName) {
		agentModule = new AgentModule();
		agentModule.setModuleClassName(className);
		agentModule.setModuleName(moduleName);
		agentModule.setModuleType(type);
		agentModule.setAgentConfiguration(configuration);
		if(type == ModuleType.MESSENGER 
				&& mappedModuleName!=null 
				&& !mappedModuleName.isEmpty())
			agentModule.setMappedModuleName(mappedModuleName);
		configuration.getAgentModules().add(agentModule);
		return this;
	}

	public ProcessingAgentBuilder addConfigurationParameter(AgentConfigurationParameter parameter){
		parameter.setAgentConfiguration(configuration);		
		if(agentModule!=null) {
			parameter.setAgentModule(agentModule);
			agentModule.getParameters().add(parameter);
		} else {
			configuration.getParameters().add(parameter);
		}
		return this;
	}
	
	public ProcessingAgentBuilder addStringParameter(String name, String value, ParameterType type){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setStringValue(value);
		if(type!=null && type != ParameterType.BOOLEAN 
				&& type != ParameterType.NUMERIC
				&& type != ParameterType.BINARY)
			parameter.setParameterType(type);
		else
			parameter.setParameterType(ParameterType.STRING);
				
		return addConfigurationParameter(parameter);
	}
	
	public ProcessingAgentBuilder addBinaryParameter(String name, byte[] value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setBase64String(value);

		return addConfigurationParameter(parameter);
	}
	
	public ProcessingAgentBuilder addStringParameter(String name, String value){		
		return addStringParameter(name, value, ParameterType.STRING);
	}

	public ProcessingAgentBuilder addNumericParameter(String name, double value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setNumericValue(value);
		parameter.setParameterType(ParameterType.NUMERIC);
				
		return addConfigurationParameter(parameter);
	}
	
	public ProcessingAgentBuilder addBooleanParameter(String name, boolean value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setBooleanValue(value);
		parameter.setParameterType(ParameterType.BOOLEAN);
				
		return addConfigurationParameter(parameter);
	}

	public ProcessingAgentBuilder map(AgentModule module) {
		AgentConfigurationModuleMapping configurationModuleMapping = new AgentConfigurationModuleMapping();
		configurationModuleMapping.setAgentConfiguration(configuration);
		configurationModuleMapping.setAgentModule(module);
		configuration.getAgentConfigurationModuleMappings().add(configurationModuleMapping);
		return this;
	}
}
