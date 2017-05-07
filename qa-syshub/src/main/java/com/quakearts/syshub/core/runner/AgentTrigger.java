package com.quakearts.syshub.core.runner;

import java.util.Map;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public interface AgentTrigger {
	void run();
	boolean shutdown();
    public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters) 
    		throws ConfigurationException;
	boolean isShutDown();
	boolean validate();
	void registerRunAgentListener(RunAgentListener listener);
}
