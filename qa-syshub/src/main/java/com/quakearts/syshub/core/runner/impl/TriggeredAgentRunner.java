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
package com.quakearts.syshub.core.runner.impl;

import java.util.Map;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.AgentTrigger;
import com.quakearts.syshub.core.runner.AgentTriggerFactory;
import com.quakearts.syshub.core.runner.RunAgentListener;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public class TriggeredAgentRunner implements Runnable, AgentRunner, RunAgentListener {

	private ProcessingAgent agent;
	private AgentTrigger trigger;
	private String triggerClassName;
	private boolean isRunning,
	isInErrorState;
	
	public TriggeredAgentRunner(ProcessingAgent agent, Map<String, AgentConfigurationParameter> parameters) 
			throws ConfigurationException {
		this.agent = agent;
		
		if(!parameters.containsKey("trigger.class")){
			throw new ConfigurationException("Missing parameter. trigger.class not found");
		}
		
		this.triggerClassName = parameters.get("trigger.class").getStringValue();
		
		if(triggerClassName == null)
			throw new ConfigurationException("Missing parameter. trigger.class not found");
		
		trigger = AgentTriggerFactory.getInstance().createAgentTrigger(triggerClassName, this, parameters);
		trigger.setupWithConfigurationParameters(parameters);
		restart();
	}

	@Override
	public void run() {
		if(trigger != null && !isRunning){
			isRunning = true;
			trigger.run();
			isRunning = false;
		}
	}
	
	@Override
	public void runAgent() throws ProcessingException {
		agent.processData();
	}
	
	@Override
	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public boolean isShutDown() {
		return trigger == null 
				|| trigger.isShutDown();
	}

	@Override
	public boolean isInErrorState() {
		return isInErrorState;
	}

	@Override
	public boolean isInRestartRequiredMode() {
		return trigger != null 
				&& trigger.isShutDown() 
				&& isInErrorState;
	}

	@Override
	public ProcessingAgent getProcessingAgent() {
		return agent;
	}

	@Override
	public RunType getRunType() {
		return RunType.TRIGGERED;
	}

	@Override
	public boolean restart() {
		if(!isRunning)
			new Thread(this).start();
		return true;
	}

	@Override
	public boolean shutdown() {
		return trigger != null 
				&& trigger.shutdown();
	}

}
