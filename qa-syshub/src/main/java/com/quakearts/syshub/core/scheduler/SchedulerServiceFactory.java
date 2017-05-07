package com.quakearts.syshub.core.scheduler;

import java.util.Map;

import com.quakearts.syshub.core.scheduler.impl.QuartzScheduleServiceFactory;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.FatalException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public abstract class SchedulerServiceFactory {
	private static SchedulerServiceFactory instance;
	
	public static SchedulerServiceFactory getInstance() {
		if(instance == null)
			instance = new QuartzScheduleServiceFactory();
		
		return instance;
	}

	public static void setInstance(SchedulerServiceFactory instance) {
		if(SchedulerServiceFactory.instance == null)
			SchedulerServiceFactory.instance = instance;
		else
			throw new FatalException("Multiple scheduler services loaded.");
	}

	public abstract SchedulerService getSchedulerService();
	public abstract Schedule createScheduleFromParameter(Map<String, AgentConfigurationParameter> parameters) 
			throws ConfigurationException;
	public abstract Schedule createScheduleFromString(String schedule) throws ConfigurationException;

}
