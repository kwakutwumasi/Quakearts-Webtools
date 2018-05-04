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

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.model.AgentConfiguration.RunType;

/**The handle for the system to access/run processing agents
 * Implementation note:
 * The methods here must all return without an error and must to the best of ones ability
 * return with the runner in a meaningful state i.e the runner should not hang indefinitely (infinite loops,
 * locked states) and any call to shutdown or restart the agent must work
 * @author kwakutwumasi-afriyie
 *
 */
public interface AgentRunner {
	/**
	 * @return true iff this agent is running (is not in an error state), false if not (not started/in an error state)
	 */
	boolean isRunning();
	/**
	 * @return true iff this agent has shutdown (no more data processing can occur)
	 */
	boolean isShutDown();
	/**
	 * @return true iff the agent is in an erroneous state
	 */
	boolean isInErrorState();
	/**
	 * @return true iff the agent is in an error state that requires manual restart
	 */
	boolean isInRestartRequiredMode();
	/**
	 * @return The {@linkplain ProcessingAgent} that this runner is responsible for
	 */
	ProcessingAgent getProcessingAgent();
	/**
	 * @return The {@linkplain RunType} of this agent runner
	 */
	RunType getRunType();
	/**Start this agent runner
	 * @return true iff the restart was successful
	 */
	boolean start();	
	/**
	 * @return true iff the shutdown was successful
	 */
	boolean shutdown();
}
