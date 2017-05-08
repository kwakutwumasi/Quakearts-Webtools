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
/**	Version 3
 *  This is the main class of the Notification Server
 *  It performs the notifications. It uses a multithreaded approach to spool, generate and send messages
 *  It also has a daemon that processes result exceptions. Message exceptions are expected to be handled by the messengers.
 *  The agent takes a configuration file (java.util.Property readable file, xml and key value format) and builds the agent
 *  Required properties:
 *  ntagent.name - the name of the agent. Must be unique on the server
 *  ntagent.dataspooler - Two options: the fully qualified class name of a dataspooler instance to be created or a 
 *  					  comma separated list of configuration files for dataspoolers. Each file must define
 *  					  a property called dataspooler.class that holds the fully qualified class name of the 
 *  					  dataspooler instance  					  
 *  ntagent.messageformatter - Two options: the fully qualified class name of a messageformatter instance to be created or a 
 *  					  comma separated list of configuration files for messageformatters. Each file must define
 *  					  a property called messageformatter.class that holds the fully qualified class name of the 
 *  					  dataspooler instance
 *  ntagent.messenger - Two options: the JNDI name of a MessengerService instance to be created and mapped to each of
 *  					message formatters or a property of the format [filename prefix].ntagent.messenger where [filename prefix]
 *  					is the filename prefix of the message formatter that should be mapped to the messenger
 *  Optional parameters are
 *  ntagent.maximumPoolSize - Used to configure the maximum pool size of the ThreadpoolExecutor
 *  ntagent.corePoolSize - Used to configure the core pool size of the ThreadpoolExecutor
 *  ntagent.keepAliveTime - Used to configure the keep alive time of the ThreadpoolExecutor
 *  ntagent.queueSize - Used to configure the queue size of the ThreadpoolExecutor
 *  see documentation for @java.util.concurrent.ThreadPoolExecutor for more information on how to configure this system.
 */

package com.quakearts.syshub.agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.utils.SerializationUtil;
import com.quakearts.syshub.exception.FatalException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.log.MessageLogger;
import com.quakearts.syshub.log.MessageLogging;
import com.quakearts.syshub.log.ResultExceptionLogger;
import com.quakearts.syshub.log.ResultExceptionLogging;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.ResultExceptionLog;

/**This class is the root class for data processing. Once configured with a number of spoolers and message formatter/messenger pairs,
 * the agent is ready to run. A call to {@link #processData()} will start the process chain.
 * The agent uses a thread pool executor to run the data processing in parallel. Each step of the processing is invoked
 * on its own thread. The {@link ThreadPoolExecutor} is configured to run on calling thread if there are no workers to process 
 * the tasks.
 * Data process follows these steps:
 * <br>
 * 1. Call {@link DataSpooler#prepare()} to initialiaze the {@link DataSpooler}
 * <br>
 * 2. Call {@link DataSpooler#hasMoreData()} to check if data is available for processing
 * <br>
 * 3. Call {@link DataSpooler#getData()} to pull a {@link Result} instance with all the information necessary to create a single 
 * {@link Message}
 * <br>
 * 4. Call {@link MessageFormatter#formatdata(Result)} with the {@link Result} instance to create a {@link Message} for processing
 * <br>
 * 5. Call {@link Messenger#sendMessage(Message)} to process the message
 * <br>
 * 6. Call {@link DataSpooler#updateData(Result, Message)} to update the {@link DataSpooler} of the result of processing
 * <br>
 * @author kwaku.twumasi@quakearts.com
 *
 */
@ApplicationScoped
public class ProcessingAgent {
	private static final Logger log = LoggerFactory.getLogger(ProcessingAgent.class);
	@Inject @MessageLogging
	private MessageLogger messageLogger;
	@Inject @ResultExceptionLogging
	private ResultExceptionLogger resultExceptionLogger;
	private AgentConfiguration agentConfiguration;
	private List<DataSpooler> dataspoolers;
	private Map<Messenger, MessageFormatter> mapper = new HashMap<>();
	private ThreadPoolExecutor executor;
	private String name;
	private Date lastRunTime, startTime;
	private int agentWorkersCreated;
	private int maxDataSpoolerWorkers = 5, dataSpoolerWorkersCreated;
	private int maxFormatterMessengerWorkers = 5, formatterMessengerWorkersCreated;
	private BlockingQueue<AgentWorker> agentWorkers;
	private BlockingQueue<AgentDataSpoolerWorker> agentDataSpoolerWorkers = new ArrayBlockingQueue<>(maxDataSpoolerWorkers);
	private BlockingQueue<AgentFormatterMessengerWorker> agentFormatterMessengerWorkers;
	private int corePoolSize = 5, maximumPoolSize = 5, queueSize = 10;
    private long keepAliveTime = 60;
	private SerializationUtil serializationUtil = SerializationUtil.getInstance();
	

	/** Default constructor. Looks for a file named notificationagent.config at the root of the current directory
	 */
	public ProcessingAgent() {
		startTime = new Date();
	}
	
	public void processData() throws ProcessingException {
		if(agentConfiguration == null 
				|| name == null)
			throw new ProcessingException("Incomplete setup: "+(agentConfiguration==null?"missing agentConfiguration":"missing name"));
		
		log.trace("Initiating...");
		lastRunTime = new Date();
		
		if(dataspoolers.size() == 1){
			getAnAgentWorker(dataspoolers.get(0)).startProcess();
		} else {
			for(DataSpooler spooler:dataspoolers){
				getExecutor().execute(()->{getAnAgentWorker(spooler).startProcess();});
				log.trace("Launched dataspooler "+spooler.getClass().getName());
			}		
		}
	}

	public void shutdown(){
		if(executor!=null){
			executor.shutdown();
			executor = null;
		}
	}

	public void resendResultExceptionLog(ResultExceptionLog exceptionLog){
		if(mapper.isEmpty())
			return;
		
		if(exceptionLog == null)
			return;
		
		if(exceptionLog.getExceptionData() == null || exceptionLog.getExceptionData().length <= 0)
			return;
		
		log.trace("Starting resendResultExceptionLog() for id "+exceptionLog.getId()+"...");
		log.trace("Transaction started...");
		try {
			Result rlt =(Result) serializationUtil.toObject(exceptionLog.getResultData());
			log.trace("Result loaded...");
			
			for(Messenger messenger:mapper.keySet()){
				MessageFormatter messageFormatter = mapper.get(messenger);
				Message<?> message = messageFormatter.formatdata(rlt);
				messenger.sendMessage(message);
			}
			log.trace("Sending complete.");
		} catch (ProcessingException | ClassNotFoundException | IOException e) {
			log.error( "Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles attempting to resend result in exception log with id "+exceptionLog.getId());
		}		
	}
	
	private synchronized AgentWorker getAnAgentWorker(DataSpooler dataSpooler){
		AgentWorker worker;
		if(agentWorkersCreated <= dataspoolers.size()){
			log.trace("No sub worker found in sub worker queue. Creating one...");
			worker = new AgentWorker(dataSpooler);
			if(log.isTraceEnabled())
				log.trace("Added new agent sub worker. Hashcode:"+worker.hashCode());
			
			agentWorkersCreated++;
		} else {
			try {
				worker = agentWorkers.take();
				worker.dataSpooler = dataSpooler;
				if(log.isTraceEnabled())
					log.trace("Using an existing sub worker. Hashcode:"+worker.hashCode());
			} catch (InterruptedException e) {
				throw new FatalException(e);
			}
		}
		return worker;
	}

	private class AgentWorker {
		DataSpooler dataSpooler;
	
		public AgentWorker(DataSpooler dataSpooler) {
			this.dataSpooler = dataSpooler;
		}
	
		public void startProcess() {
			List<Future<?>> agentDataSpoolerTrackers = new ArrayList<>();
			try {
				log.trace("Preparing dataspooler...");
				dataSpooler.prepare();				

				log.trace("Fetching data...");
				while(dataSpooler.hasMoreData()){
					
					Result result = dataSpooler.getData();

					log.trace("Dataspooler has data. Initiating sub workers...");
					agentDataSpoolerTrackers.add(
						getExecutor()
							.submit(()->{
								getAnAgentDataSpoolerWorker(dataSpooler, result).processData();
							}
					));
				}
			} catch (ProcessingException e) {
				log.error( "Exception " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage());
				return;
			} finally {
				for(Future<?> tracker: agentDataSpoolerTrackers){
					try {
						tracker.get();//Wait for execution to complete
					} catch (InterruptedException | ExecutionException e) {
						//won't happen
					}
				}
				dataSpooler.close();
				dataSpooler = null;
				try {
					agentWorkers.put(this);
					if(log.isTraceEnabled())
						log.trace("Added super worker hashcode:"+this.hashCode()+" to super worker queue");
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private synchronized AgentDataSpoolerWorker getAnAgentDataSpoolerWorker(DataSpooler dataSpooler, Result result){
		AgentDataSpoolerWorker worker;
		if(dataSpoolerWorkersCreated <= maxDataSpoolerWorkers){
			log.trace("No sub worker found in sub worker queue. Creating one...");
			worker = new AgentDataSpoolerWorker(dataSpooler, result);
			if(log.isTraceEnabled())
				log.trace("Added new agent sub worker. Hashcode:"+worker.hashCode());
			
			dataSpoolerWorkersCreated++;
		} else {
			try {
				worker = agentDataSpoolerWorkers.take();
				worker.dataSpooler = dataSpooler;
				if(log.isTraceEnabled())
					log.trace("Using an existing sub worker. Hashcode:"+worker.hashCode());
			} catch (InterruptedException e) {
				throw new FatalException(e);
			}
		}
		return worker;
	}

	private class AgentDataSpoolerWorker {
		private DataSpooler dataSpooler;
		private Result result;

		
		public AgentDataSpoolerWorker(DataSpooler dataSpooler, Result result) {
			this.dataSpooler = dataSpooler;
			this.result = result;
		}
	
		public void processData() {
			try {
				if(result.getDataResults().size()==0){
					return;
				}
				
				if(mapper.size() == 1){
					Messenger messenger = mapper.keySet().iterator().next();
					MessageFormatter formatter = mapper.values().iterator().next();
					
					getAnAgentFormatterMessengerWorker(dataSpooler, 
							formatter, messenger, result).formatDataAndSend();
				} else {
					for(Messenger messenger:mapper.keySet()){
						getExecutor().execute(()->{
							getAnAgentFormatterMessengerWorker(dataSpooler, 
									mapper.get(messenger), messenger, result).formatDataAndSend();
						});
					}					
				}
			} finally {	
				dataSpooler = null;
				try {
					agentDataSpoolerWorkers.put(this);
					if(log.isTraceEnabled())
						log.trace("Added sub worker hashcode:"+this.hashCode()+" to sub worker queue");
				} catch (InterruptedException e) {
				}
			}
		}
	}

	private synchronized AgentFormatterMessengerWorker getAnAgentFormatterMessengerWorker(DataSpooler dataSpooler,
			MessageFormatter messageFormatter, Messenger messenger,
			Result result){
		AgentFormatterMessengerWorker worker;
		if(formatterMessengerWorkersCreated <= maxFormatterMessengerWorkers){
			log.trace("No base worker found in base worker queue. Creating one...");
			worker = new AgentFormatterMessengerWorker(dataSpooler, messageFormatter, messenger, result);
			if(log.isTraceEnabled())
				log.trace("Added new agent base worker. Hashcode:"+worker.hashCode());
			
			formatterMessengerWorkersCreated++;
		} else {
			try {
				worker = agentFormatterMessengerWorkers.take();
				worker.dataSpooler = dataSpooler;
				worker.messageFormatter = messageFormatter;
				worker.messenger = messenger;
				worker.result = result;
				if(log.isTraceEnabled())
					log.trace("Using an existing base worker. Hashcode:"+worker.hashCode());
			} catch (InterruptedException e) {
				throw new FatalException(e);
			}
		}
		return worker;
	}

	private class AgentFormatterMessengerWorker {
		DataSpooler dataSpooler;
		MessageFormatter messageFormatter;
		Messenger messenger;
		Result result;
			
		public AgentFormatterMessengerWorker(DataSpooler dataSpooler,
				MessageFormatter messageFormatter, Messenger messenger,
				Result result) {
			this.dataSpooler = dataSpooler;
			this.messageFormatter = messageFormatter;
			this.messenger = messenger;
			this.result = result;
		}
	
		public void formatDataAndSend() {
			Message<?> message;
			try {
				message = messageFormatter.formatdata(result);
				messenger.sendMessage(message);
				dataSpooler.updateData(result, message);				
			} catch (ProcessingException e) {
				if(resultExceptionLogger != null){
					ResultExceptionLog exceptionLog = new ResultExceptionLog();
					exceptionLog.setAgentConfiguration(agentConfiguration);
					exceptionLog.setExceptionType(e.getClass().getName());
					exceptionLog.setExceptionData(serializationUtil.toByteArray(e));
					exceptionLog.setResultData(serializationUtil.toByteArray(result));
					exceptionLog.setSpoolerType(dataSpooler.getClass().getName());
					
					resultExceptionLogger.log(exceptionLog);
					log.error( "Exception " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles attempting to format data for sending", e);
				}
				return;
			} finally {
				result = null;
				dataSpooler = null;
				messageFormatter = null;
				messenger = null;
				
				try {
					agentFormatterMessengerWorkers.put(this);
					if(log.isTraceEnabled())
						log.trace("Added base worker hashcode:"+this.hashCode()+" to base worker queue");
				} catch (InterruptedException e) {
				}
			}			
		}
	}
	
	/**
	 * @return the configured maximum AgentDataSpoolerWorkers for this object
	 */
	public int getMaxDataSpoolerWorkers() {
		return maxDataSpoolerWorkers;
	}

	/**
	 * @param maxDataSpoolerWorkers the maximum number of AgentDataSpoolerWorkers to create
	 */
	public void setMaxDataSpoolerWorkers(int maxDataSpoolerWorkers) {
		this.maxDataSpoolerWorkers = maxDataSpoolerWorkers;
		agentDataSpoolerWorkers = new ArrayBlockingQueue<>(maxDataSpoolerWorkers);
	}

	/**
	 * @return the number of AgentDataSpoolerWorkers created
	 */
	public int getDataSpoolerWorkersCreated() {
		return dataSpoolerWorkersCreated;
	}
	
	/**
	 * @return the configured maximum AgentFormatterWorkers for this object
	 */
	public int getMaxFormatterMessengerWorkers() {
		return maxFormatterMessengerWorkers;
	}

	/**
	 * @param maxFormatterMessengerWorkers the maximum number of AgentFormatterWorkers to create
	 */
	public void setMaxFormatterMessengerWorkers(int maxFormatterMessengerWorkers) {
		this.maxFormatterMessengerWorkers = maxFormatterMessengerWorkers;
		agentFormatterMessengerWorkers = new ArrayBlockingQueue<>(maxFormatterMessengerWorkers);
	}

	/**
	 * @return the number of AgentFormatterWorkers created
	 */
	public int getFormatterMessengerWorkersCreated() {
		return formatterMessengerWorkersCreated;
	}
		
	/**
	 * @return {@link ThreadPoolExecutor}'s core pool size
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * @param corePoolSize The core pool size for {@link ThreadPoolExecutor}
	 */
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/**
	 * @return {@link ThreadPoolExecutor}'s maximum pool size
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * @param maximumPoolSize The maximumPoolSize for {@link ThreadPoolExecutor}
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	/**
	 * @return The queue size for {@link ThreadPoolExecutor}
	 */
	public int getQueueSize() {
		return queueSize;
	}

	/**
	 * @param queueSize The queue size for {@link ThreadPoolExecutor}
	 */
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	/**
	 * @param The keep alive time in seconds for {@link ThreadPoolExecutor}
	 */
	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * @param keepAliveTime The keep alive time in seconds for {@link ThreadPoolExecutor}
	 */
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	/**
	 * @return The {@link AgentConfiguration} that was used to create this agent
	 */
	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	/**
	 * @param agentConfiguration The {@link AgentConfiguration} that was used to create this agent
	 */
	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	/**
	 * @return A list of configured {@link DataSpooler}
	 */
	public List<DataSpooler> getDataSpoolers() {
		return dataspoolers;
	}

	public void setDataSpoolers(List<DataSpooler> dataspoolers) {
		this.dataspoolers = Collections.unmodifiableList(dataspoolers);
		agentWorkers = new ArrayBlockingQueue<>(dataspoolers.size());
	}
	
	/**
	 * @return a {@link Map} of {@link MessageFormatter}/{@link Messenger} pairs
	 */
	public Map<Messenger, MessageFormatter> getMessengerFormatterMapper() {
		return mapper;
	}
	
	public void setMessengerFormatterMapper(Map<Messenger, MessageFormatter> mapper) {
		this.mapper = Collections.unmodifiableMap(mapper);
	}

	/** The reference name of this processing agent
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The reference name of this processing agent
	 */
	public void setName(String name) {
		this.name = name;
	}

	private ThreadPoolExecutor getExecutor() {
		if(executor == null){
			executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
					keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(queueSize),
					new AgentThreadFactory(name==null?this.toString():name),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}
		
		return executor;
	}

	/**Returns the approximate number of threads that are actively executing tasks.
	 * 
	 * @return the number of threads
	 */
	public int getActiveCount(){
		return getExecutor().getActiveCount();
	}
	
	/**Returns the approximate total number of tasks that have completed execution. 
	 * Because the states of tasks and threads may change dynamically during computation, 
	 * the returned value is only an approximation, but one that does not ever decrease 
	 * across successive calls.
	 * 
	 * @return the number of threads
	 */
	public long getCompletedTaskCount(){
		return getExecutor().getCompletedTaskCount();
	}
			
	/**Returns the largest number of threads that have ever simultaneously been in the pool.
	 * 
	 * @return the number of threads
	 */
	public int getLargestPoolSize(){
		return getExecutor().getLargestPoolSize();
	}
	
	/**Returns the current number of threads in the pool.
	 * 
	 * @return the number of threads
	 */
	public int getPoolSize(){
		return getExecutor().getPoolSize();
	}
	
	/**Returns the approximate total number of tasks that have ever been scheduled for execution. 
	 * Because the states of tasks and threads may change dynamically during computation, the 
	 * returned value is only an approximation.
	 * 
	 * @return the number of tasks
	 */
	public long getTaskCount(){
		return getExecutor().getTaskCount();
	}

	/**
	 * @return The date/time of the last run
	 */
	public Date getLastRunTime() {
		return lastRunTime;
	}

	/**
	 * @return The agents' initialization time
	 */
	public Date getStartTime() {
		return startTime;
	}
}
