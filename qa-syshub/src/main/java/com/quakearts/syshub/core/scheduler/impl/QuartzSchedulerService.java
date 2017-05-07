package com.quakearts.syshub.core.scheduler.impl;

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
import org.quartz.impl.StdSchedulerFactory;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.core.scheduler.Schedule;
import com.quakearts.syshub.core.scheduler.SchedulerService;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.FatalException;

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
		return isRunning(runnable);
	}

	@Override
	public boolean shutdown(Runnable runnable) {
		try {
			scheduler.deleteJob(new JobKey(runnable.toString(), GROUP));
			return true;
		} catch (SchedulerException e) {
			Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
			+ ". Exception occured whiles shutting down job for " + runnable.toString());
			return false;
		}
	}
}
