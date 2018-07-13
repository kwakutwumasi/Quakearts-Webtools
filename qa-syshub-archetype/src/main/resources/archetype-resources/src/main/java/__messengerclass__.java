package ${package};

import java.util.Map;

import com.clydestone.tools.fileconverter.module.GelmaltoInputFileFormatter;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;

@ConfigurationProperties({
	@ConfigurationProperty(value="messenger.property1", friendlyName="Property 1"}
})
public class ${messengerclass} implements Messenger {
	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;

	@Override
	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
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
	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	@Override
	public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters) {
		//TODO
	}
	
	@Override
	public void validate() throws ConfigurationException {
		//TODO
	}
	
	@Override
	public void sendMessage(Message<?> mssg) throws ProcessingException {
		//TODO
	}
	
	@Override
	public boolean confirmDelivery(ProcessingLog log) {
		//TODO
		return false;
	}

	@Override
	public boolean isCompatibleWith(MessageFormatter formatter) {
		//TODO
		return false;
	}

	@Override
	public void close() {
		//TODO
	}
}