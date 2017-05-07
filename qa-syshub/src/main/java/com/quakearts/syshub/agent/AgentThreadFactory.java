package com.quakearts.syshub.agent;

import java.util.concurrent.ThreadFactory;

public class AgentThreadFactory implements ThreadFactory {
	
	private String agentName;
	
	public AgentThreadFactory(String agentName) {
		this.agentName = agentName;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setName(agentName);
		return thread;
	}
}