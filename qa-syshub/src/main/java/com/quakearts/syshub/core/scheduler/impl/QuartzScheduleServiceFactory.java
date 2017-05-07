package com.quakearts.syshub.core.scheduler.impl;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
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
			public <T> T getSchedule() {
				return (T) TriggerBuilder
						.newTrigger()
						.withSchedule(CronScheduleBuilder
								.cronSchedule(schedule));
			}
		};
	}
}
