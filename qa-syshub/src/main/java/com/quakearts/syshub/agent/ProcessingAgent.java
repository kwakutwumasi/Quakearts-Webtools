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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.syshub.agent.event.ProcessingEvent;
import com.quakearts.syshub.core.CloseableIterator;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.utils.Serializer;
import com.quakearts.syshub.exception.FatalException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.model.ResultExceptionLog;

/**This class is the root class for data processing. Once configured with a number of spoolers and message formatter/messenger pairs,
 * the agent is ready to run. A call to {@link #processData()} will start the process chain.
 * The agent uses a thread pool executor to run the data processing in parallel. Each step of the processing is invoked
 * on its own thread. The {@link ThreadPoolExecutor} is configured to run on calling thread if there are no workers to process 
 * the tasks.
 * Data process follows these steps:
 * <br />
 * 1. Call {@link DataSpooler#prepare()} to initialize the {@link DataSpooler} and return a {@linkplain CloseableIterator}
 * <br />
 * 2. Call {@link CloseableIterator#hasNext()} to check if data is available for processing
 * <br />
 * 3. Call {@link CloseableIterator#next()} to pull a {@link Result} instance with all the information necessary to create a single 
 * {@link Message}
 * <br />
 * 4. Call {@link MessageFormatter#formatdata(Result)} with the {@link Result} instance to create a {@link Message} for processing
 * <br />
 * 5. Call {@link Messenger#sendMessage(Message)} to process the message
 * <br />
 * 6. Call {@link DataSpooler#updateData(Result, Message)} to update the {@link DataSpooler} of the result of processing
 * <br />
 * @author kwaku.twumasi@quakearts.com
 *
 */
public class ProcessingAgent {
	private static final String INCOMPLETE_SETUP_MESSAGE = "Incomplete setup: missing Message Formatter/Messenger Pairs";
	private static final Logger log = LoggerFactory.getLogger(ProcessingAgent.class);
    @Inject
	private Serializer serializer;
	@Inject
	private Event<ProcessingEvent> processingEventTrigger;	
	private AgentConfiguration agentConfiguration;
	private List<DataSpooler> dataSpoolers;
	private Map<Messenger, MessageFormatter> mapper;
	private ThreadPoolExecutor executor;
	private String name;
	private Date lastRunTime;
	private Date startTime;
	private int agentWorkersCreated;
	private int maxDataSpoolerWorkers = 5;
	private int dataSpoolerWorkersCreated;
	private int maxFormatterMessengerWorkers = 5;
	private int formatterMessengerWorkersCreated;
	private BlockingQueue<AgentWorker> agentWorkers;
	private BlockingQueue<AgentDataSpoolerWorker> agentDataSpoolerWorkers = new ArrayBlockingQueue<>(maxDataSpoolerWorkers);
	private BlockingQueue<AgentFormatterMessengerWorker> agentFormatterMessengerWorkers = new ArrayBlockingQueue<>(maxFormatterMessengerWorkers);
	private int corePoolSize = 5;
	private int maximumPoolSize = 5;
	private int queueSize = 10;
    private long keepAliveTime = 60;

	/** Default constructor. Looks for a file named notificationagent.config at the root of the current directory
	 */
	public ProcessingAgent() {
		startTime = new Date();
	}
	
	public void processData() throws ProcessingException {
		if(agentConfiguration == null 
				|| name == null || name.trim().isEmpty())
			throw new ProcessingException("Incomplete setup: "+(agentConfiguration == null?"missing agentConfiguration":"missing name"));
		
		if(dataSpoolers == null || dataSpoolers.isEmpty())
			throw new ProcessingException("Incomplete setup: missing Data Spoolers");			
		
		if(mapper == null || mapper.isEmpty())
			throw new ProcessingException(INCOMPLETE_SETUP_MESSAGE);			
		
		lastRunTime = new Date();
		
		long executiontime = 0;
		if(log.isTraceEnabled())
			executiontime = System.nanoTime();
		
		if(dataSpoolers.size() == 1){
			getAnAgentWorker(dataSpoolers.get(0)).startProcess();
		} else {
			for(DataSpooler spooler:dataSpoolers){
				getExecutor().execute(()->getAnAgentWorker(spooler).startProcess());
			}		
		}
		if(log.isTraceEnabled()) {
			log.trace("Processing {} took {} ns", agentConfiguration.getAgentName(), (System.nanoTime()-executiontime));
		}
	}

	public void shutdown(){
		if(executor!=null){
			executor.shutdown();
		}
	}
	
	public boolean isShutdown() {
		if(executor!=null){
			return executor.isShutdown();
		}
		return true;
	}
	
	public void reset() {
		executor = null;
	}

	public void reprocessProcessingLog(ProcessingLog processingLog) throws ClassNotFoundException, IOException, ProcessingException {		
		if(mapper == null || mapper.isEmpty())
			throw new ProcessingException(INCOMPLETE_SETUP_MESSAGE);			
		
		if(processingLog == null)
			throw new ProcessingException("Invalid log passed in. Parameter was null");			

		if(processingLog.getAgentModule() == null)
			throw new ProcessingException("Invalid log passed in. getAgentModule() was null");			
		
		if(processingLog.getMessageData()==null)
			throw new ProcessingException("Invalid log passed in. getMessageData() was null");			
		
		for(Messenger messenger:mapper.keySet()){
			if(messenger.isResendCapable() && messenger.getAgentModule().getModuleName()
					.equals(processingLog.getAgentModule().getModuleName())){					
				Message<?> message = (Message<?>) serializer.toObject(processingLog.getMessageData());
				
				messenger.sendMessage(message);
				return;
			}
		}
		
		throw new ProcessingException("Processing log with ID "+processingLog.getLogID()+" cannot be resent using any of the messenger modules available.");
	}
	
	public void reprocessResultExceptionLog(ResultExceptionLog exceptionLog) throws ClassNotFoundException, IOException, ProcessingException {
		if(mapper == null || mapper.isEmpty())
			throw new ProcessingException(INCOMPLETE_SETUP_MESSAGE);			
		
		if(exceptionLog == null)
			throw new ProcessingException("Invalid log passed in. Parameter was null");			
		
		if(exceptionLog.getExceptionData() == null)
			throw new ProcessingException("Invalid log passed in. getExceptionData() was null");			

		if(exceptionLog.getResultData() == null)
			throw new ProcessingException("Invalid log passed in. getResultData() was null");			

		Result<?> rlt =(Result<?>) serializer.toObject(exceptionLog.getResultData());		
		for(Entry<Messenger,MessageFormatter> messengerPair:mapper.entrySet()){
			Message<?> message = messengerPair.getValue().formatdata(rlt);
			messengerPair.getKey().sendMessage(message);
		}
	}
	
	private synchronized AgentWorker getAnAgentWorker(DataSpooler dataSpooler){
		AgentWorker worker;
		if(agentWorkersCreated < dataSpoolers.size()){
			worker = new AgentWorker(dataSpooler);			
			agentWorkersCreated++;
		} else {
			try {
				worker = agentWorkers.take();
				worker.dataSpooler = dataSpooler;
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
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
			long executiontime = 0;
			if(log.isTraceEnabled()) 
				executiontime = System.nanoTime();
			
			try {
				runDataExtraction();
			} catch (ProcessingException e) {
				processingEventTrigger.fireAsync(new ProcessingEvent(e, agentConfiguration, dataSpooler.getAgentModule()));
				log.error( "Exception {} was thrown whiles fetching data. Message is {}", e.getClass().getName(), e.getMessage());
			} finally {
				if(log.isTraceEnabled()) {
					log.trace("DataSpooler {} took {} ns", dataSpooler.getAgentModule().getModuleName(), (System.nanoTime()-executiontime));
				}
				dataSpooler = null;
				try {
					agentWorkers.put(this);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		private void runDataExtraction() throws ProcessingException {
			try(CloseableIterator dataIterator = dataSpooler.prepare()){
				while(dataIterator.hasNext()){
					Result<?> result = dataIterator.next();	
					final DataSpooler dataSpoolerInternal = this.dataSpooler;
					getExecutor().execute(()->getAnAgentDataSpoolerWorker(dataSpoolerInternal, result).processData());
				}
			} catch (IOException e) {
				processingEventTrigger.fireAsync(new ProcessingEvent(e, agentConfiguration, dataSpooler.getAgentModule()));
				log.error( "Exception {} was thrown while closing CloseableIterator. Message is {}", e.getClass().getName(), e.getMessage());
			}
		}
		
		private synchronized AgentDataSpoolerWorker getAnAgentDataSpoolerWorker(DataSpooler dataSpooler, Result<?> result){
			AgentDataSpoolerWorker worker;
			if(dataSpoolerWorkersCreated < maxDataSpoolerWorkers){
				++dataSpoolerWorkersCreated;
				worker = new AgentDataSpoolerWorker(dataSpooler, result);
			} else {
				try {
					worker = agentDataSpoolerWorkers.take();
					worker.dataSpooler = dataSpooler;
					worker.result = result;
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					throw new FatalException(e);
				}
			}
			return worker;
		}
	}

	private class AgentDataSpoolerWorker {
		private DataSpooler dataSpooler;
		private Result<?> result;

		
		public AgentDataSpoolerWorker(DataSpooler dataSpooler, Result<?> result) {
			this.dataSpooler = dataSpooler;
			this.result = result;
		}
	
		public void processData() {
			long executiontime = 0;
			if(log.isTraceEnabled()) {
				executiontime = System.nanoTime();
			}
			
			try {
				if(!result.hasResults()){
					return;
				}
				
				if(mapper.size() == 1){
					Messenger messenger = mapper.keySet().iterator().next();
					MessageFormatter formatter = mapper.get(messenger);
					if(log.isTraceEnabled()) {
						log.trace("MessageFormatter and Messenger preparation for DataSpooler {} took  {} ns", 
								dataSpooler.getAgentModule().getModuleName(), (System.nanoTime()-executiontime));
					}
					getAnAgentFormatterMessengerWorker(dataSpooler, 
							formatter, messenger, result).formatDataAndSend();
				} else {
					for(Entry<Messenger,MessageFormatter> messengerPair:mapper.entrySet()){
						final DataSpooler dataSpoolerInternal = this.dataSpooler;
						getExecutor().execute(()->
							getAnAgentFormatterMessengerWorker(dataSpoolerInternal, 
									messengerPair.getValue(), messengerPair.getKey(), result).formatDataAndSend());
					}
					if(log.isTraceEnabled()) {
						log.trace("MessageFormatter and Messenger preparation for DataSpooler "
								+dataSpooler.getAgentModule().getModuleName()
								+" took "+(System.nanoTime()-executiontime)+"ns");
					}					
				}
			} finally {			
				dataSpooler = null;
				try {
					agentDataSpoolerWorkers.put(this);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
		private synchronized AgentFormatterMessengerWorker getAnAgentFormatterMessengerWorker(DataSpooler dataSpooler,
				MessageFormatter messageFormatter, Messenger messenger,
				Result<?> result){

			AgentFormatterMessengerWorker worker;
			if(formatterMessengerWorkersCreated < maxFormatterMessengerWorkers){
				worker = new AgentFormatterMessengerWorker(dataSpooler, messageFormatter, messenger, result);
				formatterMessengerWorkersCreated++;
			} else {
				try {
					worker = agentFormatterMessengerWorkers.take();
					worker.dataSpooler = dataSpooler;
					worker.messageFormatter = messageFormatter;
					worker.messenger = messenger;
					worker.result = result;
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					throw new FatalException(e);
				}
			}
			return worker;
		}
	}

	private class AgentFormatterMessengerWorker {
		DataSpooler dataSpooler;
		MessageFormatter messageFormatter;
		Messenger messenger;
		Result<?> result;
			
		public AgentFormatterMessengerWorker(DataSpooler dataSpooler,
				MessageFormatter messageFormatter, Messenger messenger,
				Result<?> result) {
			this.dataSpooler = dataSpooler;
			this.messageFormatter = messageFormatter;
			this.messenger = messenger;
			this.result = result;
		}
	
		public void formatDataAndSend() {
			long executiontime = 0;
			if(log.isTraceEnabled()) {
				executiontime = System.nanoTime();
			}
			try {
				Message<?> message;
				try {
					message = messageFormatter.formatdata(result);
				} catch (ProcessingException e) {
					processingEventTrigger.fireAsync(new ProcessingEvent(e, agentConfiguration, messageFormatter.getAgentModule(), result));
					return;
				} 
				
				try {
					messenger.sendMessage(message);
				} catch (ProcessingException e) {
					processingEventTrigger.fireAsync(new ProcessingEvent(e, agentConfiguration, messenger.getAgentModule(), message));
					log.error( "Exception " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()+". Exception occured whiles attempting to send message", e);
					return;
				}			
				
				try {
					dataSpooler.updateData(result, message);
				} catch (ProcessingException e) {
					processingEventTrigger.fireAsync(new ProcessingEvent(e, agentConfiguration, dataSpooler.getAgentModule()));
					log.error( "Exception " + e.getClass().getName()
							+ " was thrown. Message is " + e.getMessage()
							+". Exception occured whiles attempting to format data for sending", e);
				}
			} finally {
				if(log.isTraceEnabled()) {
					log.trace("Message formatting and sending using MessageFormatter "
							+messageFormatter.getAgentModule().getModuleName()
							+" and Messenger "+messenger.getAgentModule().getModuleName()
							+" for DataSpooler "+dataSpooler.getAgentModule().getModuleName()
							+" took "+(System.nanoTime()-executiontime)+"ns");
					
				}
				result = null;
				dataSpooler = null;
				messageFormatter = null;
				messenger = null;
				try {
					agentFormatterMessengerWorkers.put(this);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
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
		if(executor==null) {
			this.maxDataSpoolerWorkers = maxDataSpoolerWorkers;
			agentDataSpoolerWorkers = new ArrayBlockingQueue<>(maxDataSpoolerWorkers);
		}
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
		if(executor==null) {
			this.maxFormatterMessengerWorkers = maxFormatterMessengerWorkers;
			agentFormatterMessengerWorkers = new ArrayBlockingQueue<>(maxFormatterMessengerWorkers);
		}
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
		if(corePoolSize<=0 
				|| corePoolSize > maximumPoolSize)
			return;
		
		if(corePoolSize < dataSpoolers.size()) {
			this.corePoolSize = dataSpoolers.size();
			return;
		}
		
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
	 * @return A list of configured {@link DataSpooler}s
	 */
	public List<DataSpooler> getDataSpoolers() {
		return dataSpoolers;
	}

	public void setDataSpoolers(List<DataSpooler> dataSpoolers) {
		if(dataSpoolers == null || dataSpoolers.isEmpty())
			return;
		
		this.dataSpoolers = Collections.unmodifiableList(dataSpoolers);
		agentWorkers = new ArrayBlockingQueue<>(dataSpoolers.size());
	}
	
	/**
	 * @return a {@link Map} of {@link MessageFormatter}/{@link Messenger} pairs
	 */
	public Map<Messenger, MessageFormatter> getMessengerFormatterMapper() {
		return Collections.unmodifiableMap(mapper);
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
