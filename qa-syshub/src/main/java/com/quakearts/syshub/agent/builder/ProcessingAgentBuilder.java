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

/**Builder class for {@linkplain ProcessingAgent}s
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class ProcessingAgentBuilder {

	private static final String CANNOT_SET_MESSAGE = "Cannot set ProcessingAgent properties when creating an AgentModule";
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
	
	/**Default constructor
	 * 
	 */
	public ProcessingAgentBuilder() {
		configuration = new AgentConfiguration();
	}
	
	/**Constructor using a populated {@linkplain AgentConfiguration}
	 * @param configuration
	 */
	public ProcessingAgentBuilder(AgentConfiguration configuration) {
		this.configuration = configuration;
	}
	
	/**Getter for {@linkplain AgentConfiguration}
	 * @return the {@linkplain AgentConfiguration}
	 */
	public AgentConfiguration getConfiguration() {
		return configuration;
	}
	
	/**Terminal method in the builder fluid API. Assembles the {@linkplain ProcessingAgent}
	 * using provided paramters
	 * @return the configured {@linkplain ProcessingAgent}
	 * @throws ConfigurationException if an error occurs during assembly
	 */
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
		
		configureModules(agentModules, messageFormatters, messengerFormatterPairs, dataSpoolers);
		
		if(messengerFormatterPairs.isEmpty())
			throw new ConfigurationException("No Messenger/MessageFormatter pairs to send data");

		agent.setMessengerFormatterMapper(messengerFormatterPairs);
		
		if(dataSpoolers.isEmpty())
			throw new ConfigurationException("No data spooler to spool data.");

		agent.setDataSpoolers(dataSpoolers);
		
		setCommonParameters(agent);		
		return agent;
	}

	private void configureModules(List<AgentModule> agentModules, Map<String, MessageFormatter> messageFormatters,
			Map<Messenger, MessageFormatter> messengerFormatterPairs, List<DataSpooler> dataSpoolers)
			throws ConfigurationException {
		for (AgentModule agentModuleInternal : agentModules){
			Map<String, AgentConfigurationParameter> parameters = configuration.getAgentModuleConfigurationMap(agentModuleInternal);
			
			if(parameters == null)
				parameters = configuration.getAgentConfigurationMap();
			
			switch (agentModuleInternal.getModuleType()) {
			case DATASPOOLER:
				configureDataSpooler(dataSpoolers, agentModuleInternal, parameters);
				break;
			case FORMATTER:
				configureFormatter(messageFormatters, agentModuleInternal, parameters);
				break;
			case MESSENGER:
				configureMessenger(messageFormatters, messengerFormatterPairs, agentModuleInternal, parameters);
				break;
			}
		}
	}

	private void configureDataSpooler(List<DataSpooler> dataSpoolers, AgentModule agentModuleInternal,
			Map<String, AgentConfigurationParameter> parameters) throws ConfigurationException {
		dataSpoolers.add(DataSpoolerFactory.getFactory().getInstance(parameters, 
				agentModuleInternal));
	}

	private void configureFormatter(Map<String, MessageFormatter> messageFormatters, AgentModule agentModuleInternal,
			Map<String, AgentConfigurationParameter> parameters) throws ConfigurationException {
		messageFormatters.put(agentModuleInternal.getModuleName() != null 
		&& !agentModuleInternal.getModuleName().trim().isEmpty()?
				agentModuleInternal.getModuleName():agentModuleInternal.getModuleClassName(), 
				MessageFormatterFactory.getFactory().getInstance(parameters, 
						agentModuleInternal));
	}

	private void configureMessenger(Map<String, MessageFormatter> messageFormatters,
			Map<Messenger, MessageFormatter> messengerFormatterPairs, AgentModule agentModuleInternal,
			Map<String, AgentConfigurationParameter> parameters) throws ConfigurationException {
		Messenger messenger = MessengerFactory.getFactory().getInstance(parameters,
				agentModuleInternal);
		if(agentModuleInternal.getMappedModuleName() != null && !agentModuleInternal.getMappedModuleName().trim().isEmpty()) {
			MessageFormatter formatter = messageFormatters.get(agentModuleInternal.getMappedModuleName());
			if(formatter == null)
				throw new ConfigurationException("No formatter module named "+agentModuleInternal.getMappedModuleName()
					+" to send data for messenger module "+(agentModuleInternal.getModuleName()!=null?
							agentModuleInternal.getModuleName():agentModuleInternal.getModuleClassName()));
			
			messengerFormatterPairs.put(messenger, formatter);
		} else {
			for(MessageFormatter formatter: messageFormatters.values()){
				if(messenger.isCompatibleWith(formatter)){
					messengerFormatterPairs.put(messenger, formatter);
					break;
				}
			}
		}
	}

	private void setCommonParameters(ProcessingAgent agent) throws ConfigurationException {
		if (configuration.getAgentConfigurationParameter(MAX_DATA_SPOOLER_WORKERS) != null) {
			try {
				agent.setMaxDataSpoolerWorkers(configuration.getAgentConfigurationParameter(MAX_DATA_SPOOLER_WORKERS)
						.getNumericValue().intValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: maxDataSpoolerWorkers.");
			}
		}
		
		if (configuration.getAgentConfigurationParameter(MAX_FORMATTER_WORKERS) != null) {
			try {
				agent.setMaxFormatterMessengerWorkers(configuration.getAgentConfigurationParameter(MAX_FORMATTER_WORKERS)
						.getNumericValue().intValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: maxFormatterWorkers.");
			}
		}

        if(configuration.getAgentConfigurationParameter(IS_RESEND_CAPABLE)!=null){
	        try {
	        	agent.setResendCapable(configuration.getAgentConfigurationParameter(IS_RESEND_CAPABLE).getBooleanValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: queueSize.");
			}
        }
		
        setThreadPoolParameters(agent);        
	}

	private void setThreadPoolParameters(ProcessingAgent agent) throws ConfigurationException {
		if(configuration.getAgentConfigurationParameter(MAXIMUM_POOL_SIZE)!=null){
	        try {
	        	agent.setMaximumPoolSize(configuration.getAgentConfigurationParameter(MAXIMUM_POOL_SIZE).getNumericValue().intValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: maximumPoolSize");
			}
        }

        if(configuration.getAgentConfigurationParameter(CORE_POOL_SIZE)!=null){
	        try {
				agent.setCorePoolSize(configuration.getAgentConfigurationParameter(CORE_POOL_SIZE).getNumericValue().intValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: corePoolSize.");
			}
        }

        if(configuration.getAgentConfigurationParameter(KEEP_ALIVE_TIME)!=null){
	        try {
	        	agent.setKeepAliveTime(configuration.getAgentConfigurationParameter(KEEP_ALIVE_TIME).getNumericValue().longValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: keepAliveTime.");
			}
        }

        if(configuration.getAgentConfigurationParameter(QUEUE_SIZE)!=null){
	        try {
	        	agent.setQueueSize(configuration.getAgentConfigurationParameter(QUEUE_SIZE).getNumericValue().intValue());
			} catch (Exception e) {
				throw new ConfigurationException("Invalid property: queueSize.");
			}
        }
	}
	
	/**Set the name of the {@linkplain ProcessingAgent}
	 * @param name the name to set
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder name(String name){
		configuration.setAgentName(name);
		return this;
	}
	
	/**Create a dataspooler
	 * @param dataSpoolerClass the class of the implementation of {@linkplain DataSpooler} to use
	 * @param moduleName the name to give to the {@linkplain AgentModule}
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder dataSpooler(Class<? extends DataSpooler> dataSpoolerClass, String moduleName){
		return createAgentModule(ModuleType.DATASPOOLER, dataSpoolerClass.getName(), moduleName, null);
	}

	/**Create a message formatter
	 * @param messageFormatterClass the class of the implementation of {@linkplain MessageFormatter} to use
	 * @param moduleName the name to give to the {@linkplain AgentModule}
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder messageFormatter(Class<? extends MessageFormatter> messageFormatterClass, String moduleName){
		return createAgentModule(ModuleType.FORMATTER, messageFormatterClass.getName(), moduleName, null);
	}

	/**Create a messenger
	 * @param messengerClass the class of the implementation of {@linkplain Messenger} to use
	 * @param moduleName the name to give to the {@linkplain AgentModule}
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder messenger(Class<? extends Messenger> messengerClass, String moduleName){
		return createAgentModule(ModuleType.MESSENGER, messengerClass.getName(), moduleName, null);
	}

	/**Create a messenger
	 * @param messengerClass the class of the implementation of {@linkplain Messenger} to use
	 * @param moduleName the name to give to the {@linkplain AgentModule}
	 * @param mappedModuleName the module name of a {@linkplain MessageFormatter} to link to this messenger
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder messenger(Class<? extends Messenger> messengerClass, String moduleName, String mappedModuleName){
		return createAgentModule(ModuleType.MESSENGER, messengerClass.getName(), moduleName, mappedModuleName);
	}

	/**Create an agent module using the supplied parameters
	 * @param type the {@linkplain ModuleType}
	 * @param className the class name of the {@linkplain DataSpooler}, {@linkplain MessageFormatter}
	 * or {@linkplain Messenger} implementation to attach to the module
	 * @param moduleName the name to give the module
	 * @param mappedModuleName the module name of a {@linkplain MessageFormatter} to link to this messenger. 
	 * Applies only to modules of type {@linkplain ModuleType} MESSENGER
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
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

	/**Add an {@linkplain AgentConfigurationParameter} to the {@linkplain ProcessingAgent}.
	 * If any preceding call was to any of the module creating methods ({@link #dataSpooler(Class, String)},
	 * {@link #messageFormatter(Class, String)}, {@link #messenger(Class, String)}, {@link #messenger(Class, String, String)},
	 * or {@link #createAgentModule(ModuleType, String, String, String)}, the parameter is added to its {@linkplain AgentModule}
	 * @param parameter the {@linkplain AgentConfigurationParameter}
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
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
	
	/**Creates an {@linkplain AgentConfigurationParameter} for the {@linkplain ProcessingAgent} using the
	 * supplied parameters. Calls through to {@link #addConfigurationParameter(AgentConfigurationParameter)}
	 * @param name the parameter name
	 * @param value the string value
	 * @param type the string {@linkplain ParameterType}
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
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
	
	/**Creates an {@linkplain AgentConfigurationParameter} for the {@linkplain ProcessingAgent} using the
	 * supplied parameters. 
	 * @param name the parameter name
	 * @param value the byte array to save
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder addBinaryParameter(String name, byte[] value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setBase64String(value);

		return addConfigurationParameter(parameter);
	}
	
	/**Creates an {@linkplain AgentConfigurationParameter} for the {@linkplain ProcessingAgent} using the
	 * supplied parameters. Calls through to {@link #addConfigurationParameter(AgentConfigurationParameter)}.
	 * Defaults the {@linkplain ParameterType} to STRING.
	 * @param name the parameter name
	 * @param value the string value
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder addStringParameter(String name, String value){		
		return addStringParameter(name, value, ParameterType.STRING);
	}

	/**Creates an {@linkplain AgentConfigurationParameter} for the {@linkplain ProcessingAgent} using the
	 * supplied parameters. 
	 * @param name the parameter name
	 * @param value the double value to save
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder addNumericParameter(String name, double value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setNumericValue(value);
		parameter.setParameterType(ParameterType.NUMERIC);
				
		return addConfigurationParameter(parameter);
	}
	
	/**Creates an {@linkplain AgentConfigurationParameter} for the {@linkplain ProcessingAgent} using the
	 * supplied parameters. 
	 * @param name the parameter name
	 * @param value the boolean value to save
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder addBooleanParameter(String name, boolean value){
		AgentConfigurationParameter parameter = new AgentConfigurationParameter();
		parameter.setName(name);
		parameter.setBooleanValue(value);
		parameter.setParameterType(ParameterType.BOOLEAN);
				
		return addConfigurationParameter(parameter);
	}

	/**Map an existing {@linkplain AgentModule} to this {@linkplain ProcessingAgent}
	 * @param module the {@linkplain AgentModule} to map
	 * @return this {@linkplain ProcessingAgentBuilder} for method chaining
	 */
	public ProcessingAgentBuilder map(AgentModule module) {
		AgentConfigurationModuleMapping configurationModuleMapping = new AgentConfigurationModuleMapping();
		configurationModuleMapping.setAgentConfiguration(configuration);
		configurationModuleMapping.setAgentModule(module);
		configuration.getAgentConfigurationModuleMappings().add(configurationModuleMapping);
		return this;
	}

	/**Set the maxDataSpoolerWorkers property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder maxDataSpoolerWorkers(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(MAX_DATA_SPOOLER_WORKERS, amount);		
	}
	
	/**Set the maxFormatterWorkers property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder maxFormatterWorkers(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(MAX_FORMATTER_WORKERS, amount);
	}
	
	/**Set the queueSize property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder queueSize(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(QUEUE_SIZE, amount);
	}
	
	/**Set the keepAliveTime property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder keepAliveTime(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(KEEP_ALIVE_TIME, amount);
	}
	
	
	/**Set the corePoolSize property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder corePoolSize(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(CORE_POOL_SIZE, amount);
	}
	
	/**Set the maximumPoolSize property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder maximumPoolSize(double amount) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addNumericParameter(MAXIMUM_POOL_SIZE, amount);
	}
	
	/**Set the resendCapable property on the processing agent
	 * @param amount
	 * @return
	 * @throws IllegalStateException if an agent module is being created.
	 */
	public ProcessingAgentBuilder resendCapable(boolean activate) {
		if(agentModule!=null)
			throw new IllegalStateException(CANNOT_SET_MESSAGE);
		
		return addBooleanParameter(IS_RESEND_CAPABLE, activate);
	}
}
