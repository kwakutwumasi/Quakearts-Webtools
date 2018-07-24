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
package com.quakearts.syshub.core.runner;

import java.util.List;
import java.util.Map;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

/**This interface is implemented by classes that are used to trigger 
 * the {@link com.quakearts.syshub.agent.ProcessingAgent ProcessingAgent}
 * @author kwakutwumasi-afriyie
 *
 */
public interface AgentTrigger {
	/**This method is called by the trigger agent runner to initiate the trigger.
	 * Implementation note: this method should not return whiles the trigger is in 
	 * a valid state i.e. as long as the trigger can be executed
	 * @throws ProcessingException
	 */
	void runTrigger() throws ProcessingException;
	/**The shutdown method for this trigger agent
	 * @return
	 */
	boolean shutdown();
    /**Instantiate this trigger with the passed in parameters
     * @param parameters the parameters
     * @throws ConfigurationException
     */
    public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters) 
    		throws ConfigurationException;
	/**
	 * @return true iff the agent trigger is no longer available for executions
	 */
	boolean isShutDown();
	/**Register the handle for the agent runner to call the {@link com.quakearts.syshub.agent.ProcessingAgent#processData() processData()}
	 * method
	 * @param listener The handle for the agent to run
	 */
	void registerRunAgentListener(RunAgentListener listener);
	/**
	 * @return The current state of this agent trigger
	 */
	TriggerState getState();
	/**
	 * @return Statistics that can be displayed in the application monitor
	 */
	public List<Statistic> getStatistics();
	
	/**Enumeration for the agent trigger states
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static enum TriggerState {
		/**The trigger has no state
		 */
		None,
		/**The trigger is dormant
		 */
		Dormant,
		/**The trigger is preparing
		 */
		Preparing,
		/**The trigger is ready
		 */
		Ready,
		/**The trigger is connected
		 */
		Connected,
		/**The trigger is disconnected
		 */
		Disconnected,
		/**The trigger is in an error state
		 */
		Error
	}
}
