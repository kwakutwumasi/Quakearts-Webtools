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

import com.quakearts.appbase.Main;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.AgentScheduler;
import com.quakearts.syshub.core.runner.ScheduledStateReporter;
import com.quakearts.syshub.core.runner.Statistic;
import com.quakearts.syshub.core.scheduler.Schedule;
import com.quakearts.syshub.core.scheduler.SchedulerService;
import com.quakearts.syshub.core.scheduler.SchedulerServiceFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;

@Vetoed
public class ScheduledAgentRunner implements Runnable, AgentRunner, ScheduledStateReporter, AgentScheduler {

	private ProcessingAgent agent;
	private boolean inErrorState;
	private SchedulerServiceFactory schedulerServiceFactory = SchedulerServiceFactory.getInstance();
	private SchedulerService schedulerService = schedulerServiceFactory.getSchedulerService();
	private Schedule schedule;
	private SchedulerState state = SchedulerState.None;
	
	public ScheduledAgentRunner(ProcessingAgent agent, 
			Map<String, AgentConfigurationParameter> parameters)
			throws ConfigurationException {
		this.agent = agent;
		this.schedule = schedulerServiceFactory.createScheduleFromParameter(parameters);
		start();
	}
		
	@Override
	public void run() {
		try {
			state = SchedulerState.Running;
			agent.processData();
		} catch (ProcessingException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
			+ ". Exception occured whiles processing data.");
			inErrorState = true;
		} finally {
			state = SchedulerState.Sleeping;
		}
	}

	@Override
	public boolean isRunning() {
		return schedulerService.isRunning(this);
	}

	@Override
	public boolean isShutDown() {
		return schedulerService.isShutDown(this) 
				|| agent.isShutdown();
	}

	@Override
	public boolean isInErrorState() {
		return inErrorState;
	}

	@Override
	public boolean isInRestartRequiredMode() {
		return false;
	}

	@Override
	public ProcessingAgent getProcessingAgent() {
		return agent;
	}

	@Override
	public RunType getRunType() {
		return RunType.SCHEDULED;
	}

	@Override
	public boolean start() {
		if(!isRunning()) {
			agent.reset();
			try {
				schedulerService.scheduleTask(this, schedule);
			} catch (ConfigurationException e) {
				Main.log.error("Exception starting schedule", e);
				inErrorState = true;
				return false;
			}
			state = SchedulerState.Started;
		}
		return true;
	}

	@Override
	public boolean shutdown() {
		if(schedulerService.shutdown(this)) {
			agent.shutdown();
			state = SchedulerState.Stopped;
			return true;
		}
		return isShutDown();
	}

	@Override
	public String toString() {
		return agent.getName();
	}
	
	@Override
	public SchedulerState getState() {
		return state;
	}
	
	@Override
	public List<Statistic> getStatistics() {
		return schedulerService.getStatistics(this);
	}
}
