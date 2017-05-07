package com.quakearts.syshub.core;

import java.util.Map;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public interface AgentConfigurationModule {
    /** Get the {@link AgentConfiguration} used to configure the formatter
     * @return
     */
    AgentConfiguration getAgentConfiguration();
    /**Set the {@link AgentConfiguration} used to configure this formatter
     * @param agentConfiguration
     */
    void setAgentConfiguration(AgentConfiguration agentConfiguration);
    /**Method to setup {@link AgentConfigurationModule}. 
     * Implementations may retrieve configuration information
     * from the {@link AgentConfigurationParameter} passed in.
     * @param props a {@link Map} object containing configuration information
     * @throws ConfigurationException
     */
    public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameterMap) 
    		throws ConfigurationException;

}
