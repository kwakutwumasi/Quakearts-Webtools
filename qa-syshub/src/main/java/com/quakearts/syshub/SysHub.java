package com.quakearts.syshub;

import java.util.Collection;

import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;

public interface SysHub {

	Collection<AgentRunner> listAgentRunners();

	void deployAgent(AgentConfiguration agentConfiguration) throws ConfigurationException;

	void undeployAgent(AgentConfiguration agentConfiguration);

	boolean isDeployed(AgentConfiguration agentConfiguration);

}