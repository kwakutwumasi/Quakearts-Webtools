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
package com.quakearts.syshub.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.core.impl.ResultImpl;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.core.runner.AgentTrigger;
import com.quakearts.syshub.core.runner.RunAgentListener;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;

@ConfigurationProperties({
		@ConfigurationProperty("test.parameter.1"), @ConfigurationProperty(value = "test.parameter.2", type = ParameterType.BINARY),
		@ConfigurationProperty(value = "test.parameter..parameter.3", type = ParameterType.BOOLEAN),
		@ConfigurationProperty(value = "test.parameter.4", type = ParameterType.CLASS),
		@ConfigurationProperty(value = "test.parameter.5", type = ParameterType.CRONCONFIGURATION),
		@ConfigurationProperty(value = "test.parameter.6", type = ParameterType.EMAIL),
		@ConfigurationProperty(value = "test.parameter.7", type = ParameterType.ENDPOINTADDRESS),
		@ConfigurationProperty(value = "test.parameter.8", type = ParameterType.FILE),
		@ConfigurationProperty(value = "test.parameter.9", type = ParameterType.JNDINAME),
		@ConfigurationProperty(value = "test.parameter.10", type = ParameterType.NUMERIC),
		@ConfigurationProperty(value = "test.parameter.11", type = ParameterType.STRING), 
	})
public class TestAgent implements DataSpooler, MessageFormatter, Messenger, AgentTrigger {

	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;
	private AtomicInteger test = new AtomicInteger(0);
	private RunAgentListener listener;
	private boolean running;

	@Override
	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	@Override
	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	@Override
	public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters)
			throws ConfigurationException {
	}

	@Override
	public AgentModule getAgentModule() {
		return agentModule;
	}

	@Override
	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
	}

	@Override
	public void validate() throws ConfigurationException {
	}

	@Override
	public void sendMessage(Message<?> mssg) throws ProcessingException {
		System.out.println(mssg.toString());
	}

	@Override
	public boolean confirmDelivery(ProcessingLog log) {
		return true;
	}

	@Override
	public boolean isCompatibleWith(MessageFormatter formatter) {
		return formatter.getClass() == this.getClass();
	}

	@Override
	public Message<?> formatdata(Result rlt) throws ProcessingException {
		MessageStringImpl messageStringImpl = new MessageStringImpl();
		return messageStringImpl.appendBody(rlt.getDataResults().get(0).get("test").toString());
	}

	@Override
	public void prepare() throws ProcessingException {
		test.incrementAndGet();
	}

	@Override
	public boolean hasMoreData() {
		return test.get() == 1;
	}

	@Override
	public Result getData() throws ProcessingException {
		test.decrementAndGet();
		Map<String, Object> row = new HashMap<>();
		row.put("Test", "Hello World");
		return new ResultImpl().addRow(row);
	}

	@Override
	public void updateData(Result result, Message<?> message) throws ProcessingException {
		System.out.println("Updating: "+result);
	}

	@Override
	public void close() {
	}

	@Override
	public void runTrigger() {
		running = true;
		while(running){
			try {
				listener.runAgent();
			} catch (ProcessingException e) {
				running = false;
			}
		}
	}

	@Override
	public boolean shutdown() {
		running = false;
		return running;
	}

	@Override
	public boolean isShutDown() {
		return running;
	}

	@Override
	public void registerRunAgentListener(RunAgentListener listener) {
		this.listener = listener;
	}

	@Override
	public TriggerState getState() {
		return TriggerState.Connected;
	}
}
