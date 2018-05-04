package com.quakearts.syshub.test.helper;

import java.io.IOException;
import java.util.Map;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.CloseableIterator;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.core.impl.ResultImpl;
import com.quakearts.syshub.core.utils.MapRowBuilder;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;

public class ErrorThrowingModule implements DataSpooler, Messenger, MessageFormatter {

	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;
	private ExceptionLocation throwException;
	
	public ErrorThrowingModule(ExceptionLocation throwException, ProcessingAgent agent) {
		this.throwException = throwException;
		agent.setAgentConfiguration(getAgentConfiguration());
		agent.setName(getAgentConfiguration().getAgentName());
	}

	public static enum ExceptionLocation {
		FORMATDATA, SENDMESSAGE, PREPARE, CLOSE, UPDATEDATA, NONE
	}
	
	@Override
	public AgentConfiguration getAgentConfiguration() {
		if(agentConfiguration == null) {
			agentConfiguration = new AgentConfiguration();
			agentConfiguration.setAgentName("ErrorThrowingModule");
			agentConfiguration.setId(-1);
		}
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
		if(agentModule == null) {
			agentModule = new AgentModule();
			agentModule.setModuleName("ErrorThrowingModule");
		}
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
	public Message<?> formatdata(Result<?> rlt) throws ProcessingException {
		if(throwException == ExceptionLocation.FORMATDATA)
			throw new ProcessingException("Test Exception");
		
		return new MessageStringImpl();
	}

	@Override
	public void sendMessage(Message<?> mssg) throws ProcessingException {
		if(throwException == ExceptionLocation.SENDMESSAGE)
			throw new ProcessingException("Test Exception");
	}

	@Override
	public boolean confirmDelivery(ProcessingLog log) {
		return true;
	}

	@Override
	public boolean isCompatibleWith(MessageFormatter formatter) {
		return false;
	}

	@Override
	public CloseableIterator prepare() throws ProcessingException {
		if(throwException == ExceptionLocation.PREPARE)
			throw new ProcessingException("Test Exception");

		return new CloseableIterator() {
			private int produced;
			@Override
			public Result<Object> next() {
				if(throwException == ExceptionLocation.NONE)
					return new ResultImpl();
				
				++produced;
				Result<Object> result = new ResultImpl();
				result.addRow(MapRowBuilder.create()
						.row()
						.addColumn("test", "value")
						.thenBuild());
				return result;
			}
			
			@Override
			public boolean hasNext() {
				return produced==0;
			}
			
			@Override
			public void close() throws IOException {
				if(throwException == ExceptionLocation.CLOSE)
					throw new IOException("Test Exception");
				
			}
		};
	}

	@Override
	public void updateData(Result<?> result, Message<?> message) throws ProcessingException {
		if(throwException == ExceptionLocation.UPDATEDATA)
			throw new ProcessingException("Test Exception");
	}

	@Override
	public void close() {
	}

}
