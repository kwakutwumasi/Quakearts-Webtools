package com.quakearts.syshub.core.runner.impl;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;

public class LoopedAgentRunner implements Runnable, AgentRunner {
	private ProcessingAgent agent;
	private boolean running = true, inErrorState = false;
	
	public LoopedAgentRunner(ProcessingAgent agent) {
		this.agent = agent;
		restart();
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
		return running;
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
	public boolean restart() {
		new Thread(this).start();
		return true;
	}

	@Override
	public boolean shutdown() {
		setRunning(false);
		return true;
	}
}
