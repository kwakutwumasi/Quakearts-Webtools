package com.quakearts.syshub.test.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.syshub.core.runner.AgentTrigger;
import com.quakearts.syshub.core.runner.RunAgentListener;
import com.quakearts.syshub.core.runner.Statistic;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

@Singleton
public class TestAgentTrigger2 implements AgentTrigger {

	private int runcount;
	private Map<String, AgentConfigurationParameter> parameters;
	private boolean shutdown;
	private RunAgentListener listener;
	@Inject
	private Trigger2 trigger;
	
	@Override
	public synchronized void runTrigger() {
		runcount++;
		while (!shutdown) {
			trigger.prepare();
			if(!shutdown)
				try {
					listener.runAgent();
				} catch (ProcessingException e) {
				}
		}
	}

	public Map<String, AgentConfigurationParameter> getParameters() {
		return parameters;
	}
		
	@Override
	public boolean shutdown() {
		shutdown =true;
		trigger.fire();
		return shutdown;
	}

	@Override
	public void setupWithConfigurationParameters(Map<String, AgentConfigurationParameter> parameters)
			throws ConfigurationException {
		this.parameters = parameters;
	}

	@Override
	public boolean isShutDown() {
		return shutdown;
	}

	@Override
	public void registerRunAgentListener(RunAgentListener listener) {
		this.listener = listener;
	}

	@Override
	public TriggerState getState() {
		return shutdown?TriggerState.Disconnected:TriggerState.Connected;
	}

	@Override
	public List<Statistic> getStatistics() {
		return Arrays.asList(new Statistic("Run Count", runcount));
	}

}
