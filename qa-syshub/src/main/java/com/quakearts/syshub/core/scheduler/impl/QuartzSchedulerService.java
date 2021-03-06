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

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Vetoed;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.core.runner.Statistic;
import com.quakearts.syshub.core.scheduler.Schedule;
import com.quakearts.syshub.core.scheduler.SchedulerService;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.FatalException;

@Vetoed
public class QuartzSchedulerService implements SchedulerService, JobListener {

	private static final String GROUP = "SysHub Schedule Group";
	public Scheduler scheduler;
	
	public QuartzSchedulerService() {
		SchedulerFactory factory = new StdSchedulerFactory();
		try {
			scheduler = factory.getScheduler();
			scheduler.getListenerManager().addJobListener(this);
			scheduler.start();
			
			Runtime.getRuntime().addShutdownHook(new Thread(()->{
				try {
					scheduler.shutdown();
				} catch (SchedulerException e) {
					Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
							+ ". Exception occured whiles shutting down scheduler.");
				}
			}));
		} catch (SchedulerException e) {
			throw new FatalException("Unable to instantiate scheduler: "+e.getMessage(), e);
		}
	}
	
	@Override
	public void scheduleTask(Runnable runnable, Schedule schedule) throws ConfigurationException {
		JobDataMap newJobDataMap = new JobDataMap();
		newJobDataMap.put(RunnableJob.RUNNABLE, runnable);
		
		JobDetail job = JobBuilder.newJob(RunnableJob.class)
				.withIdentity(runnable.toString(), GROUP)
				.usingJobData(newJobDataMap).build();
		
		try {
			scheduler.scheduleJob(job, schedule.getSchedule());
		} catch (SchedulerException e) {
			throw new ConfigurationException("Cannot schedule "+runnable.toString(), e);
		}
	}

	@Override
	public String getName() {
		return "SysHub Quartz Scheduler Service";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		Main.log.debug("About to execute: "+context.getJobDetail().getDescription());
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		Main.log.debug("Executed: "+context.getJobDetail().getDescription());
		if(jobException != null)
			Main.log.error("Exception of type " + jobException.getClass().getName() + " was thrown. Message is " + jobException.getMessage()
					+ ". Exception occured whiles running job for "+context.getJobDetail().getDescription());
	}

	@Override
	public boolean isRunning(Runnable runnable) {
		try {
			return scheduler.checkExists(new JobKey(runnable.toString(), GROUP));
		} catch (SchedulerException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
			+ ". Exception occured whiles checking job for " + runnable.toString());
			return false;
		}
	}

	@Override
	public boolean isShutDown(Runnable runnable) {
		return !isRunning(runnable);
	}

	@Override
	public boolean shutdown(Runnable runnable) {
		try {
			return scheduler.deleteJob(new JobKey(runnable.toString(), GROUP));
		} catch (SchedulerException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
			+ ". Exception occured whiles shutting down job for " + runnable.toString());
			return false;
		}
	}
	
	@Override
	public List<Statistic> getStatistics(Runnable runnable) {
		List<Statistic> statistics = new ArrayList<>();
		try {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(new JobKey(runnable.toString(), GROUP));
			if(!triggers.isEmpty()) {
				Trigger trigger = triggers.get(0);
				statistics.add(new Statistic("Started",trigger.getStartTime()));

				if(trigger.getEndTime()!=null)
					statistics.add(new Statistic("Ending",trigger.getEndTime()));

				if(trigger.getPreviousFireTime()!=null)
					statistics.add(new Statistic("Last Run",trigger.getPreviousFireTime()));
				
				statistics.add(new Statistic("Next Run",trigger.getNextFireTime()));
			}
		} catch (SchedulerException e) {
			statistics.add(new Statistic("Error","Unable to get statistics"));
		}
		return statistics;
	}
}
