package com.quakearts.syshub.webapp.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.quakearts.syshub.core.CloseableIterator;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.event.ParameterEventBroadcaster;
import com.quakearts.syshub.core.event.ParameterEventListener;
import com.quakearts.syshub.core.impl.ResultImpl;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.core.utils.MapRowBuilder;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter.ParameterType;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

@ConfigurationProperties({
	@ConfigurationProperty(type=ParameterType.CLASS, value="test.class", friendlyName="Test Class", assignableType=DataSpooler.class),
	@ConfigurationProperty(type=ParameterType.CRONCONFIGURATION, value="test.cronconfiguration",description="Test Description"),
	@ConfigurationProperty(type=ParameterType.EMAIL, value="test.email", global=true),
	@ConfigurationProperty(type=ParameterType.ENDPOINTADDRESS, value="test.endpointaddress", required=true)
})
public class TestDataSpooler extends RandomErrorThrower implements DataSpooler, ParameterEventListener {

	private AgentConfiguration agentConfiguration;
	private Map<String, AgentConfigurationParameter> parameters;
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
		if(parameters.containsKey("throw.error"))
			throw new ConfigurationException("Throwing error for test");
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
	public CloseableIterator prepare() throws ProcessingException {
		throwErrorOrNot();
		return new CloseableIterator() {
			
			private int produceCount;
			
			@Override
			public Result<Object> next() {
				if(produceCount<2) {
					produceCount++;
					Result<Object> result = new ResultImpl();
					result.addRow(MapRowBuilder.create()
							.row()
							.addColumn("agent.name", "Test")
							.thenBuild())
						.addMetaDataResult(MapRowBuilder.create()
								.row()
								.addColumn("agent.name", "Test 2")
								.thenBuild());
					List<Map<String, Object>> rowsList = Arrays.asList(MapRowBuilder.create()
							.row()
							.addColumn("agent.module.name", "Test")
							.thenBuild());
					result.addAllRows(rowsList);
					result.addAllMetaData(rowsList);
					result.addProperty("test", "value");
					
					return result;
				}
				return null;
			}
			
			@Override
			public boolean hasNext() {
				if(agentConfiguration.getType() == RunType.LOOPED)
					try {
						Thread.sleep(Math.abs((getRandom().nextInt()%10)) * 1000);
					} catch (InterruptedException e) {
					}; //Prevent loop from generating too much test data too quickly
					
				return produceCount < 2;
			}
			
			@Override
			public void close() throws IOException {
			}
		};
	}

	@Override
	public void updateData(Result<?> result, Message<?> message) throws ProcessingException {
		throwErrorOrNot();
	}

	@Override
	public void close() {
	}
	
	@Override
	public void shutdown() {
		parameterEventBroadcaster.unregisterListener(this);
	}
}
