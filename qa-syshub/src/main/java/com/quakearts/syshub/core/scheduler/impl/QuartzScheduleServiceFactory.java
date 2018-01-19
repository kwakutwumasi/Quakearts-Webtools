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
package com.quakearts.syshub.core.scheduler.impl;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import com.quakearts.syshub.core.scheduler.Schedule;
import com.quakearts.syshub.core.scheduler.SchedulerService;
import com.quakearts.syshub.core.scheduler.SchedulerServiceFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public class QuartzScheduleServiceFactory extends SchedulerServiceFactory {

	private static final QuartzSchedulerService instance = new QuartzSchedulerService();
	
	@Override
	public SchedulerService getSchedulerService() {
		return instance;
	}

	@Override
	public Schedule createScheduleFromParameter(Map<String, AgentConfigurationParameter> parameters) 
			throws ConfigurationException {
		AgentConfigurationParameter parameter = parameters.get("schedule.cron");
		if(parameter == null)
			throw new ConfigurationException("Missing paramter. schedule.cron not found");
			
		String cronExpression = parameter.getStringValue();
		if(cronExpression==null)
			throw new ConfigurationException("Invalid paramter "+parameter.getName()+". Must be a valid string");
		
		return createScheduleFromString(cronExpression);
	}

	@Override
	public Schedule createScheduleFromString(String schedule) {
		return new Schedule() {
			
			@SuppressWarnings("unchecked")
			@Override
			public CronTrigger getSchedule() {
				return TriggerBuilder
						.newTrigger()
						.withSchedule(CronScheduleBuilder
								.cronSchedule(schedule))
						.build();
			}
		};
	}
}
