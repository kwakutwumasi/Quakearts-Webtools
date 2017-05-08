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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.internal.json.Json;
import com.quakearts.appbase.internal.json.JsonArray;
import com.quakearts.appbase.internal.json.JsonValue;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.factory.DataSpoolerFactory;
import com.quakearts.syshub.core.factory.MessageFormatterFactory;
import com.quakearts.syshub.core.factory.MessengerFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;

public class ProcessingAgentBuilder {

	private static final String QUEUE_SIZE = "queueSize";
	private static final String KEEP_ALIVE_TIME = "keepAliveTime";
	private static final String CORE_POOL_SIZE = "corePoolSize";
	private static final String MAX_FORMATTER_WORKERS = "maxFormatterWorkers";
	private static final String MAX_DATA_SPOOLER_WORKERS = "maxDataSpoolerWorkers";
	private static final String MAXIMUM_POOL_SIZE = "maximumPoolSize";
	private AgentConfiguration configuration;
	private AgentModule agentModule;
	private static final Logger log = LoggerFactory.getLogger(ProcessingAgentBuilder.class);
	private static final List<String> globalNameRegistry = new ArrayList<>();
	
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
		registerName(configuration.getAgentName());		
		agent.setName(configuration.getAgentName());
		agent.setAgentConfiguration(configuration);
		
		TreeSet<AgentModule> agentModules = new TreeSet<>((a1,a2)->a1.getModuleType().compareTo(a2.getModuleType()));
		agentModules.addAll(configuration.getAgentModules());
		
		Map<String, MessageFormatter> messageFormatters = new HashMap<>();
		Map<Messenger, MessageFormatter> messengerFormatterPairs = new HashMap<>();
		List<DataSpooler> dataSpoolers = new ArrayList<>();
		
		for (AgentModule agentModule : configuration.getAgentModules()){
			switch (agentModule.getModuleType()) {
			case DATASPOOLER:
				dataSpoolers.add(DataSpoolerFactory.getFactory().getInstance(agentModule.getAgentClassName(),
						configuration.getModuleConfigurationParameters(agentModule), agentModule));
				break;
			case FORMATTER:
				messageFormatters.put(agentModule.getModuleName() != null && !agentModule.getModuleName().trim().isEmpty()?
						agentModule.getModuleName():agentModule.getAgentClassName(), MessageFormatterFactory.getFactory().getInstance(agentModule.getAgentClassName(),
						configuration.getModuleConfigurationParameters(agentModule), agentModule));
				break;
			case MESSENGER:
				Messenger messenger = MessengerFactory.getFactory().getInstance(agentModule.getAgentClassName(),
						configuration.getModuleConfigurationParameters(agentModule), agentModule);
				if(agentModule.getMappedModuleName()!=null) {
					MessageFormatter formatter = messageFormatters.get(agentModule.getMappedModuleName());
					if(formatter == null)
						throw new ConfigurationException("No messenger module named "+agentModule.getMappedModuleName()
							+" to send data for formatter module "+(agentModule.getModuleName()!=null?
									agentModule.getModuleName():agentModule.getAgentClassName()));
					
					messengerFormatterPairs.put(messenger, formatter);
				} else {
					for(MessageFormatter formatter: messageFormatters.values()){
						if(messenger.isCompatibleWith(formatter)){
							messengerFormatterPairs.put(messenger, formatter);
						}
						break;
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
		
		log.trace("Setup complete....");
		if(log.isTraceEnabled()){
			log.trace("Setup summary:"
					+ "\nAgent name: "+agent.getName()
					+ "\nDataspoolers:"+agent.getDataSpoolers().size()
					+ "\nMessageFormatter\\Messenger pairs:"
					+agent.getMessengerFormatterMapper().size()
					+"\nThreadPoolExecutor parameters:" +
					"core pool size="+agent.getCorePoolSize()+
					";maximum pool size="+agent.getMaximumPoolSize()+
					";keep alive time="+agent.getKeepAliveTime()+
					";queue size="+agent.getQueueSize());
		}
		
		return agent;
	}

	/**
	 * Utility method to register agent names. This is to enforce uniqueness across the VM
	 */
	private static synchronized void registerName(String name) throws ConfigurationException{
		if(globalNameRegistry.contains(name))
			throw new ConfigurationException("Name "+name+" not a unique agent name. Name already exists.");
		else
			globalNameRegistry.add(name);		
	}
	
	public ProcessingAgentBuilder fromFile(String fileName) throws ConfigurationException {
		JsonValue jsonObject;
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(in!=null){
			try (InputStream stream = in) {
				 jsonObject = Json.parse(new InputStreamReader(stream));
			} catch (IOException e) {
				throw new ConfigurationException("Unable to load file "+fileName, e);
			}
		} else {
			try(InputStream stream = new FileInputStream(fileName);) {
				jsonObject = Json.parse(new InputStreamReader(stream));
			} catch (IOException e) {
				throw new ConfigurationException("Unable to load file "+fileName, e);
			}
		}
		
		try {
			name(jsonObject.asObject().get("name").asString());
			JsonArray commonParameters = jsonObject.asObject().get("parameters").asArray();
			commonParameters.forEach((p)->{
				processParameter(p);
			});
			
			JsonArray array = jsonObject.asObject().get("modules").asArray();
			array.forEach((o)->{
				processModule(o);
			});
			
		} catch (UnsupportedOperationException e) {
			throw new ConfigurationException("Unable to read file "+fileName+" file is not in the proper structure.", e);
		}
		
		return this;		
	}
	
	private void processModule(JsonValue o) {
		JsonValue classNameValue = o.asObject().get("classname");
		
		if(classNameValue==null){
			throw new UnsupportedOperationException("Parameter classname is required for all agent modules.");
		}

		String className = classNameValue.asString();
		JsonValue moduleNameValue = o.asObject().get("modulename");
		JsonValue typeValue = o.asObject().get("type");
		if(typeValue==null){
			throw new UnsupportedOperationException("Parameter type is required for agent modules. Missing for "+moduleNameValue!=null?moduleNameValue.asString():className);
		}
		
		ModuleType type;
		try {
			type = ModuleType.valueOf(typeValue.asString());
		} catch (IllegalArgumentException e) {
			throw new UnsupportedOperationException("ModuleType: "+typeValue.asString()+" is not valid");
		}
		
		switch (type) {
		case DATASPOOLER:
			dataSpooler(className, moduleNameValue!=null? moduleNameValue.asString():null);
			break;
		case FORMATTER:
			messageFormatter(className, moduleNameValue!=null? moduleNameValue.asString():null);
			break;
		case MESSENGER:
			JsonValue mappedModuleNameValue = o.asObject().get("mappedmodulename");
			messenger(className, moduleNameValue != null ? moduleNameValue.asString() : null,
					mappedModuleNameValue != null ? mappedModuleNameValue.asString() : null);
			break;
		default:
			break;
		}
		
		JsonArray commonParameters = o.asObject().get("parameters").asArray();
		commonParameters.forEach((p)->{
			processParameter(p);
		});
	}

	private void processParameter(JsonValue p){
		String name = p.asObject().get("name").asString();
		JsonValue value = p.asObject().get("value");
		if(value.isBoolean()){
			addBooleanParameter(name, value.asBoolean());
		} else if(value.isNumber()){
			addNumericParameter(name, value.asDouble());
		} else {
			JsonValue typeValue = p.asObject().get("value");
			if(typeValue != null){
				try {
					addStringParameter(name, value.asString(), ParameterType.valueOf(typeValue.asString()));
				} catch (IllegalArgumentException e) {
					throw new UnsupportedOperationException("PrameterType: "+typeValue.asString()+" is not valid");
				}
			} else {
				addStringParameter(name, value.asString());
			}
		}
	}
	
	public ProcessingAgentBuilder name(String name){
		configuration.setAgentName(name);
		return this;
	}
	
	public ProcessingAgentBuilder dataSpooler(String className){
		return createAgentModule(ModuleType.DATASPOOLER, className, null, null);
	}

	public ProcessingAgentBuilder dataSpooler(String className, String moduleName){
		return createAgentModule(ModuleType.DATASPOOLER, className, moduleName, null);
	}

	public ProcessingAgentBuilder messageFormatter(String className){
		return createAgentModule(ModuleType.FORMATTER, className, null, null);
	}

	public ProcessingAgentBuilder messageFormatter(String className, String moduleName){
		return createAgentModule(ModuleType.FORMATTER, className, moduleName, null);
	}

	public ProcessingAgentBuilder messenger(String className){
		return createAgentModule(ModuleType.MESSENGER, className, null, null);
	}

	public ProcessingAgentBuilder messenger(String className, String moduleName){
		return createAgentModule(ModuleType.MESSENGER, className, moduleName, null);
	}

	public ProcessingAgentBuilder messenger(String className, String moduleName, String mappedModuleName){
		return createAgentModule(ModuleType.MESSENGER, className, moduleName, mappedModuleName);
	}

	private ProcessingAgentBuilder createAgentModule(ModuleType type, String className, String moduleName, String mappedModuleName) {
		agentModule = new AgentModule();
		agentModule.setAgentClassName(className);
		agentModule.setModuleName(moduleName);
		agentModule.setModuleType(type);
		agentModule.setAgentConfiguration(configuration);
		if(type == ModuleType.MESSENGER)
			agentModule.setMappedModuleName(mappedModuleName);
		configuration.getAgentModules().add(agentModule);
		return this;
	}

	public ProcessingAgentBuilder addConfigurationParameter(AgentConfigurationParameter parameter){
		if(agentModule!=null)
			parameter.setAgentModule(agentModule);

		parameter.setAgentConfiguration(configuration);		
		configuration.getParameters().add(parameter);
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
		return this;
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

}
