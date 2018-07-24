package ${package};

import java.util.Map;
import com.clydestone.tools.fileconverter.FileConverterModule;
import com.quakearts.syshub.core.CloseableIterator;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

@ConfigurationProperties({
	@ConfigurationProperty(value="spooler.property1", friendlyName="Property 1"}
})
public class ${dataspoolerclass} implements DataSpooler {
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
	public CloseableIterator prepare() throws ProcessingException {
		//TODO
	}
	
	@Override
	public void updateData(Result<?> result, Message<?> message) throws ProcessingException {
		//TODO
	}
	
	@Override
	public void close() {
		//TODO
	}
}