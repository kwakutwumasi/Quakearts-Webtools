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

import com.quakearts.appbase.Main;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.scheduler.Schedule;
import com.quakearts.syshub.core.scheduler.SchedulerService;
import com.quakearts.syshub.core.scheduler.SchedulerServiceFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration.RunType;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public class ScheduledAgentRunner implements Runnable, AgentRunner {

	private ProcessingAgent agent;
	private boolean inErrorState;
	private SchedulerService schedulerService = SchedulerServiceFactory.getInstance().getSchedulerService();
	private Schedule schedule;
	
	public ScheduledAgentRunner(ProcessingAgent agent, 
			Map<String, AgentConfigurationParameter> parameters)
			throws ConfigurationException {
		this.agent = agent;
		schedule = SchedulerServiceFactory.getInstance().createScheduleFromParameter(parameters);
		instantiate();
	}
	
	private void instantiate() throws ConfigurationException {
		schedulerService.scheduleTask(this, schedule);
	}
	
	@Override
	public void run() {
		try {
			agent.processData();
		} catch (ProcessingException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
			+ ". Exception occured whiles processing data.");
			inErrorState = true;
		}
	}

	@Override
	public boolean isRunning() {
		return schedulerService.isRunning(this);
	}

	@Override
	public boolean isShutDown() {
		return schedulerService.isShutDown(this);
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
	public boolean restart() {
		try {
			if(!isRunning())
				instantiate();

			return true;
		} catch (ConfigurationException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles restarting scheduled job");
			return false;
		}
	}

	@Override
	public boolean shutdown() {
		return schedulerService.shutdown(this);
	}

	@Override
	public String toString() {
		return agent.getName();
	}
}
