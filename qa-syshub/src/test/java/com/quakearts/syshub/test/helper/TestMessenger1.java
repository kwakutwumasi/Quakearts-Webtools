package com.quakearts.syshub.test.helper;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;

public class TestMessenger1 extends ShutdownMonitor implements Messenger {

	private AgentConfiguration agentConfiguration;
	private Map<String, AgentConfigurationParameter> parameters;
	private AgentModule agentModule;
	private AtomicInteger integer = new AtomicInteger(0);
	private static final Logger log = LoggerFactory.getLogger(TestMessenger1.class);
	
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
		if(!(mssg instanceof MessageStringImpl))
			throw new ProcessingException("Wrong message type: "+mssg.getClass().getName());
		
		StringBuilder outputBuilder = new StringBuilder("\nSending message....")
				.append(String.format("%1$-12s: %2$s", "ID", mssg.getId()))
				.append(String.format("%1$-12s: %2$s", "DATA", mssg.getMessageData(String.class)))
				.append(String.format("%1$-12s: %2$s", "PROPERTY", mssg.getProperty("size")))
				.append(String.format("%1$-12s: %2$s", "RECIPIENTS", Arrays.toString(mssg.getRecipients())))
				.append(String.format("%1$-12s: %2$s", "SECURE", mssg.isSecure()))
				.append(String.format("%1$-12s: %2$d", "LENGTH", mssg.length()))
				.append(mssg.toString()).append("\n");

		integer.incrementAndGet();
		log.trace(outputBuilder.toString());
		mssg.setMessageStatus("complete");
	}

	@Override
	public boolean confirmDelivery(ProcessingLog log) {
		return true;
	}

	@Override
	public boolean isCompatibleWith(MessageFormatter formatter) {
		return formatter instanceof TestFormatter1;
	}

	@Override
	public void close() {
	}
	
	@Override
	public boolean isResendCapable() {
		return true;
	}

}
