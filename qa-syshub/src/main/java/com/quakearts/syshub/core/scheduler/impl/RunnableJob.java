package com.quakearts.syshub.core.scheduler.impl;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RunnableJob implements Job {

	public static final String RUNNABLE = "com.quakearts.syshub.RUNNABLE";
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 Runnable runnable = (Runnable) context.get(RUNNABLE);
		 new Thread(runnable).start();
	}

}
