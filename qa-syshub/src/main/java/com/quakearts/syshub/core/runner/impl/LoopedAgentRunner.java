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

import javax.enterprise.inject.Vetoed;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;

@Vetoed
public class LoopedAgentRunner implements Runnable, AgentRunner {
	private ProcessingAgent agent;
	private boolean running, inErrorState;
	
	public LoopedAgentRunner(ProcessingAgent agent) {
		this.agent = agent;
		start();
	}

	@Override
	public void run() {
		while(running){
			try {
				agent.processData();
			} catch (ProcessingException e) {
				Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles processing data.");
				inErrorState = true;
				setRunning(false);
			}
		}
	}

	private synchronized void setRunning(boolean running){
		this.running = running;
	}
	
	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isShutDown() {
		return !running || agent.isShutdown();
	}

	@Override
	public boolean isInErrorState() {
		return inErrorState;
	}

	@Override
	public boolean isInRestartRequiredMode() {
		return inErrorState;
	}

	@Override
	public ProcessingAgent getProcessingAgent() {
		return agent;
	}

	@Override
	public RunType getRunType() {
		return RunType.LOOPED;
	}

	@Override
	public boolean start() {
		if(!running) {
			agent.reset();
			setRunning(true);
			new Thread(this).start();
		}
		return true;
	}

	@Override
	public boolean shutdown() {
		if(running) {
			setRunning(false);
			agent.shutdown();
		}
		return isShutDown();
	}

	@Override
	public String toString() {
		return agent.getName();
	}
}
