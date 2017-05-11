/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core;

import java.util.Map;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

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
    public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters) 
    		throws ConfigurationException;

    /**Get the {@link AgentModule} used to configure this agent
     * @return the module
     */
    AgentModule getAgentModule();
    
    /**Get the {@link AgentModule} used to configure this agent
     * @param agentModule the module
     */
    void setAgentModule(AgentModule agentModule);
}
