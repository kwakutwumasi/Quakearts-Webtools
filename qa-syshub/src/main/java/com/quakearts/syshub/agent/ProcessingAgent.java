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
package com.quakearts.syshub.agent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.utils.SerializationUtil;
import com.quakearts.syshub.core.utils.SystemDataStoreUtils;
import com.quakearts.syshub.exception.FatalException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.log.MessageLogger;
import com.quakearts.syshub.log.MessageLogging;
import com.quakearts.syshub.log.ResultExceptionLogger;
import com.quakearts.syshub.log.ResultExceptionLogging;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.model.ResultExceptionLog;
import com.quakearts.webapp.orm.exception.DataStoreException;

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
public class ProcessingAgent {
	private static final Logger log = LoggerFactory.getLogger(ProcessingAgent.class);
	@Inject @MessageLogging
	private MessageLogger messageLogger;
	@Inject @ResultExceptionLogging
	private ResultExceptionLogger resultExceptionLogger;
	private AgentConfiguration agentConfiguration;
	private List<DataSpooler> dataSpoolers;
	private Map<Messenger, MessageFormatter> mapper;
	private ThreadPoolExecutor executor;
	private String name;
	private Date lastRunTime, startTime;
	private int agentWorkersCreated;
	private int maxDataSpoolerWorkers = 5, dataSpoolerWorkersCreated;
	private int maxFormatterMessengerWorkers = 5, formatterMessengerWorkersCreated;
	private BlockingQueue<AgentWorker> agentWorkers;
	private BlockingQueue<AgentDataSpoolerWorker> agentDataSpoolerWorkers = new ArrayBlockingQueue<>(maxDataSpoolerWorkers);
	private BlockingQueue<AgentFormatterMessengerWorker> agentFormatterMessengerWorkers = new ArrayBlockingQueue<>(maxFormatterMessengerWorkers);
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
			throw new ProcessingException("Incomplete setup: "+(agentConfiguration == null?"missing agentConfiguration":"missing name"));
		
		if(dataSpoolers == null)
			throw new ProcessingException("Incomplete setup: missing Data Spoolers");			
		
		if(mapper == null)
			throw new ProcessingException("Incomplete setup: missing Message Formatter/Messenger Pairs");			
		
		log.trace("Initiating...");
		lastRunTime = new Date();
		
		if(dataSpoolers.size() == 1){
			getAnAgentWorker(dataSpoolers.get(0)).startProcess();
		} else {
			for(DataSpooler spooler:dataSpoolers){
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

	public void reprocessProcessingLog(ProcessingLog processingLog) throws ClassNotFoundException, IOException, ProcessingException {
		if(mapper == null || mapper.isEmpty())
			return;
		
		if(processingLog == null)
			return;

		if(processingLog.getAgentModule() == null)
			return;
		
		for(Messenger messenger:mapper.keySet()){
			if(messenger.getAgentModule().getId() == processingLog.getAgentModule().getId()){
				try {
					processingLog.getMessageData();
				} catch (DataStoreException e) {
					processingLog = SystemDataStoreUtils.getInstance().getSystemDataStore().refresh(processingLog);
				}
				
				log.trace("Reprocessing message for ProcessingLog logID "+processingLog.getLogID());
				Message<?> message = (Message<?>) SerializationUtil.getInstance().toObject(processingLog.getMessageData());
				
				messenger.sendMessage(message);
			}
		}
	}
	
	public void resendResultExceptionLog(ResultExceptionLog exceptionLog) throws ClassNotFoundException, IOException, ProcessingException {
		if(mapper == null || mapper.isEmpty())
			return;
		
		if(exceptionLog == null)
			return;
		
		if(exceptionLog.getExceptionData() == null || exceptionLog.getExceptionData().length <= 0)
			return;
		
		log.trace("Starting resendResultExceptionLog() for ResultExceptionLog id "+exceptionLog.getId()+"...");
		log.trace("Transaction started...");
		Result rlt =(Result) serializationUtil.toObject(exceptionLog.getResultData());
		log.trace("Result loaded...");
		
		for(Messenger messenger:mapper.keySet()){
			MessageFormatter messageFormatter = mapper.get(messenger);
			Message<?> message = messageFormatter.formatdata(rlt);
			messenger.sendMessage(message);
		}
		SystemDataStoreUtils.getInstance().getSystemDataStore().delete(exceptionLog);
		log.trace("Sending complete.");
	}
	
	private synchronized AgentWorker getAnAgentWorker(DataSpooler dataSpooler){
		AgentWorker worker;
		if(agentWorkersCreated < dataSpoolers.size()){
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
		if(dataSpoolerWorkersCreated < maxDataSpoolerWorkers){
			log.trace("No sub worker found in sub worker queue. Creating one...");
			++dataSpoolerWorkersCreated;
			worker = new AgentDataSpoolerWorker(dataSpooler, result);
			if(log.isTraceEnabled())
				log.trace("Added new agent sub worker. Hashcode:"+worker.hashCode());
			
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
		if(formatterMessengerWorkersCreated < maxFormatterMessengerWorkers){
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
			} catch (ProcessingException e) {
				if(resultExceptionLogger != null){
					resultExceptionLogger.logResultException(agentConfiguration, dataSpooler.getAgentModule(), e, result);
					log.error( "Exception " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()
							+". Exception occured whiles attempting to format data for sending", e);
				}
				return;
			} 
			
			try {
				messenger.sendMessage(message);
			} catch (ProcessingException e) {
				log.error( "Exception " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles attempting to send message", e);
				if(messageLogger!=null)
					messageLogger.logMessage(messenger.getAgentConfiguration(), messenger.getAgentModule(), 
							message, "Exception " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()
							+". Exception occured whiles attempting to send message."
							+ " Message will be logged as an error", true);
			}			
			
			try {
				dataSpooler.updateData(result, message);
			} catch (ProcessingException e) {
				log.error( "Exception " + e.getClass().getName()
						+ " was thrown. Message is " + e.getMessage()
						+". Exception occured whiles attempting to format data for sending", e);
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
	 * @return the number of agent workers created. Equivalent to the number of {@link DataSpooler} added
	 */
	public int getAgentWorkersCreated() {
		return agentWorkersCreated;
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
		if(corePoolSize<=0)
			return;
		
		if(corePoolSize < dataSpoolers.size())
			corePoolSize += dataSpoolers.size();
		
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
		if(maximumPoolSize <= 0 || corePoolSize > maximumPoolSize)
			return;
		
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
		if(queueSize <= 0)
			return;
		
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
		if(keepAliveTime <= 0)
			return;
		
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
		return dataSpoolers;
	}

	public void setDataSpoolers(List<DataSpooler> dataSpoolers) {
		if(dataSpoolers == null || dataSpoolers.size() <= 0)
			return;
		
		this.dataSpoolers = Collections.unmodifiableList(dataSpoolers);
		agentWorkers = new ArrayBlockingQueue<>(dataSpoolers.size());
	}
	
	/**
	 * @return a {@link Map} of {@link MessageFormatter}/{@link Messenger} pairs
	 */
	public Map<Messenger, MessageFormatter> getMessengerFormatterMapper() {
		return mapper;
	}
	
	public void setMessengerFormatterMapper(Map<Messenger, MessageFormatter> mapper) {
		if(mapper == null || mapper.size() <= 0)
			return;
		
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
