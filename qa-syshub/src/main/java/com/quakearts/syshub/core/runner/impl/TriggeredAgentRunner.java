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

import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Vetoed;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.TriggeredStateReporter;
import com.quakearts.syshub.core.runner.AgentTrigger.TriggerState;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.AgentTrigger;
import com.quakearts.syshub.core.runner.AgentTriggerFactory;
import com.quakearts.syshub.core.runner.RunAgentListener;
import com.quakearts.syshub.core.runner.Statistic;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;

/**Agent runner that runs when triggered
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class TriggeredAgentRunner implements Runnable, AgentRunner, RunAgentListener, TriggeredStateReporter {

	private ProcessingAgent agent;
	private AgentTrigger trigger;
	private String triggerClassName;
	private boolean enteredRunTrigger,
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
		start();
	}

	@Override
	public void run() {
		if(trigger != null){
			enteredRunTrigger = true;
			try {
				trigger.runTrigger();
			} catch (ProcessingException e) {
				isInErrorState = true;
			}
			enteredRunTrigger = false;
		}
	}
	
	@Override
	public void runAgent() throws ProcessingException {
		agent.processData();
	}
	
	@Override
	public boolean isRunning() {
		return enteredRunTrigger && !isShutDown();
	}

	public boolean hasEnteredRunTrigger() {
		return enteredRunTrigger;
	}
	
	@Override
	public boolean isShutDown() {
		return trigger == null 
				|| trigger.isShutDown() 
				|| agent.isShutdown();
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
	public boolean start() {
		if(!enteredRunTrigger) {
			agent.reset();
			new Thread(this).start();
		}
		return true;
	}

	@Override
	public boolean shutdown() {
		if(trigger != null 
				&& trigger.shutdown()) {
			agent.shutdown();
			return true;
		}
		
		return isShutDown();
	}

	@Override
	public String toString() {
		return agent.getName();
	}
	
	@Override
	public TriggerState getState() {
		return trigger.getState();
	}
	
	@Override
	public List<Statistic> getStatistics() {
		return trigger.getStatistics();
	}
}
