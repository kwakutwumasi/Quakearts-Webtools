package com.quakearts.syshub.test.helper;

import com.quakearts.syshub.model.AgentModule;

public abstract class ShutdownMonitor {

	private boolean shutdown;
	
	public abstract AgentModule getAgentModule();
	
	public boolean isShutdown() {
		return shutdown;
	}
	
	public void shutdown(){
		shutdown = true;
	}

}
