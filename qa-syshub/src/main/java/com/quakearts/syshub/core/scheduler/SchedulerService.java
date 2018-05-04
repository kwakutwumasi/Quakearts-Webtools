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
package com.quakearts.syshub.core.scheduler;

import java.util.List;

import com.quakearts.syshub.core.runner.Statistic;
import com.quakearts.syshub.exception.ConfigurationException;

public interface SchedulerService {
	void scheduleTask(Runnable runnable, Schedule schedule) throws ConfigurationException;
	boolean isRunning(Runnable runnable);
	boolean isShutDown(Runnable runnable);
	boolean shutdown(Runnable runnable);
	List<Statistic> getStatistics(Runnable runnable);
}
