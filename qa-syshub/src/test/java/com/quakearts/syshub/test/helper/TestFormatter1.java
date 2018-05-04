package com.quakearts.syshub.test.helper;

import java.util.Map;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

public class TestFormatter1 implements MessageFormatter {
	
	private Map<String, AgentConfigurationParameter> parameters;
	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;
	
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
	public Message<?> formatdata(Result<?> rlt) throws ProcessingException {
		Message<String> message = new MessageStringImpl();
		message.addProperty("size", rlt.getDataResultSize());
		rlt.getMetaDataResults().forEach((entry)->{
			if(entry.get("agent.name")!=null)
				message.addRecipient(entry.get("agent.name").toString());			
		});
		
		rlt.getDataResults().forEach((entry)->{
			try {
				if(entry.get("agent.module.name")!=null)
					message.appendBody("***********\n"+entry.get("agent.module.name").toString())
						.appendBody("|").appendBody(rlt.hashCode()+"");
			} catch (ProcessingException e) {
			}			
		});
		
		message.appendFooter("***********\n"+rlt.getProperties().getProperty("test"));
		message.appendHeader("Has results: ")
			.appendHeader(rlt.hasResults()+"")
			.appendHeader("\nHas meta data results: ")
			.appendHeader(rlt.hasMetaDataResults()+"")
			.appendHeader("\nIterator Hash Code: ")
			.appendHeader(rlt.iterator().hashCode()+"")
			.appendHeader("\nResult Hash Code: ")
			.appendHeader(rlt.hashCode()+"");
		return message;
	}

	@Override
	public void close() {
	}

}
