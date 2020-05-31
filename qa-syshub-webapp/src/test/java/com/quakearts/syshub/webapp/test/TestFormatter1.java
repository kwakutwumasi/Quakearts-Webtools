package com.quakearts.syshub.webapp.test;

import java.util.Map;

import javax.inject.Inject;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.event.ParameterEventBroadcaster;
import com.quakearts.syshub.core.event.ParameterEventListener;
import com.quakearts.syshub.core.impl.MessageStringImpl;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;

@ConfigurationProperties({
	@ConfigurationProperty(type=ParameterType.FILE, value="test.file"),
	@ConfigurationProperty(type=ParameterType.JNDINAME, value="test.jndiname")
})
public class TestFormatter1 extends RandomErrorThrower implements MessageFormatter, ParameterEventListener {
	
	private Map<String, AgentConfigurationParameter> parameters;
	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;
	@Inject
	private ParameterEventBroadcaster parameterEventBroadcaster;
	
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
		parameterEventBroadcaster.registerListener(this);
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
		throwErrorOrNot();
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

	@Override
	public void shutdown() {
		parameterEventBroadcaster.unregisterListener(this);
	}

}
