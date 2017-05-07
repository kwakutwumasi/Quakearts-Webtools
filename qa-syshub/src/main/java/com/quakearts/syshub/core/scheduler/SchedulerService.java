package com.quakearts.syshub.core.scheduler;

import com.quakearts.syshub.exception.ConfigurationException;

public interface SchedulerService {
	void scheduleTask(Runnable runnable, Schedule schedule) throws ConfigurationException;
	boolean isRunning(Runnable runnable);
	boolean isShutDown(Runnable runnable);
	boolean shutdown(Runnable runnable);
}
