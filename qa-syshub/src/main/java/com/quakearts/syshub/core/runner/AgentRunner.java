package com.quakearts.syshub.core.runner;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.model.AgentConfiguration.RunType;

public interface AgentRunner {
	boolean isRunning();
	boolean isShutDown();
	boolean isInErrorState();
	boolean isInRestartRequiredMode();
	ProcessingAgent getProcessingAgent();
	RunType getRunType();
	boolean restart();
	boolean shutdown();
}
