/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
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
