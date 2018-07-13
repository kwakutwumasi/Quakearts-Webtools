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

/**This interface is implemented by services that provide scheduling for agent runners
 * @author kwakutwumasi-afriyie
 *
 */
public interface SchedulerService {
	/**Schedule the agent runner runnable
	 * @param runnable The runnable of the agent runner
	 * @param schedule the schedule to run the agent runner on
	 * @throws ConfigurationException if there was an error scheduling the agent runner
	 */
	void scheduleTask(Runnable runnable, Schedule schedule) throws ConfigurationException;
	/**Getter for the running state of the scheduler
	 * @param runnable the {@linkplain Runnable} to check
	 * @return true if this {@linkplain Runnable} is running
	 */
	boolean isRunning(Runnable runnable);
	/**Getter for the shutdown state of the scheduler
	 * @param runnable the {@linkplain Runnable} to check
	 * @return true if this {@linkplain Runnable} has been shutdown
	 */
	boolean isShutDown(Runnable runnable);
	/**Shutdown the scheduler for this {@linkplain Runnable}
	 * @param runnable the {@linkplain Runnable} to check
	 * @return true if this {@linkplain Runnable} has been shutdown
	 */
	boolean shutdown(Runnable runnable);
	/**
	 * @return Statistics that can be displayed in the application monitor
	 */
	List<Statistic> getStatistics(Runnable runnable);
}
