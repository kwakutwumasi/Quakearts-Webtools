package com.quakearts.syshub.webapp.test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.impl.MessageByteImpl;
import com.quakearts.syshub.core.metadata.annotations.Autoconfigured;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.log.MessageLogger;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.webapp.helpers.viewupdate.UpdateViewEvent;

@Autoconfigured
public class TestMessenger2 extends RandomErrorThrower implements Messenger {

	@Inject
	private Event<UpdateViewEvent> updateViewEvent;
	
	private AgentConfiguration agentConfiguration;
	private Map<String, AgentConfigurationParameter> parameters;
	private AgentModule agentModule;
	private AtomicInteger integer = new AtomicInteger(0);
	private static final Logger log = LoggerFactory.getLogger(TestMessenger2.class);
	
	@Inject
	private MessageLogger messageLogger;
	
	public int getSendCount(){
		return integer.get();
	}
	
	public Map<String, AgentConfigurationParameter> getParameters() {
		return parameters;
	}
	
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
		this.parameters = parameters;
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
		throwErrorOrNot();
		if(!(mssg instanceof MessageByteImpl))
			throw new ProcessingException("Wrong message type: "+mssg.getClass().getName());

		StringBuilder outputBuilder = new StringBuilder("\nSending message....")
				.append(String.format("%1$-12s: %2$s", "ID", mssg.getId()))
		.append(String.format("%1$-12s: %2$s", "DATA", new String(mssg.getMessageData(byte[].class))))
		.append(String.format("%1$-12s: %2$s", "PROPERTY", mssg.getProperty("size")))
		.append(String.format("%1$-12s: %2$s", "RECIPIENTS", Arrays.toString(mssg.getRecipients())))
		.append(String.format("%1$-12s: %2$s", "SECURE", mssg.isSecure()))
		.append(String.format("%1$-12s: %2$d", "LENGTH", mssg.length()));

		integer.incrementAndGet();
		log.trace(outputBuilder.toString());
		updateViewEvent.fireAsync(new UpdateViewEvent("TestMessenger2#sendMessage()", agentConfiguration, agentModule));
		mssg.setMessageStatus("complete");
		messageLogger.logMessage(agentConfiguration, agentModule, mssg, "Test Logging 2", false);
	}

	@Override
	public boolean confirmDelivery(ProcessingLog log) {
		return true;
	}

	@Override
	public boolean isCompatibleWith(MessageFormatter formatter) {
		return formatter instanceof TestFormatter2;
	}

	@Override
	public void close() {
	}

}
