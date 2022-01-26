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
package com.quakearts.syshub.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.utils.CacheManager;
import com.quakearts.syshub.core.utils.Serializer;
import com.quakearts.syshub.core.utils.SystemDataStoreManager;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.model.ResultExceptionLog;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;
import com.quakearts.syshub.model.ProcessingLog.LogType;

import org.infinispan.Cache;

@Singleton
public class LoggerImpl implements MessageLogger, ResultExceptionLogger {
	private static final String MESSAGE_DUMP = "Message dump:\n{}";
	@Inject
	private CacheManager cacheManager;
	@Inject
	private Serializer serializer;
	@Inject
	private SystemDataStoreManager storeManager;

	private Cache<String, ProcessingLog> saveLogCache;
	private Cache<String, ProcessingLog> updateLogCache;
	private Comparator<ProcessingLog> logComparator = (n1,n2)-> n1.getLogDt().compareTo(n2.getLogDt());
	private final Logger log = LoggerFactory.getLogger(LoggerImpl.class);
	private final Timer logTimer = new Timer(true);
	private Cache<String, ResultExceptionLog> resultExceptionLogCache;
	private boolean shutdown;
		
	LoggerImpl() {
		logTimer.schedule(new TimerTask() {	
			@Override
			public void run() {
				if(shutdown) {
					log.trace("Scheduler has been shutdown");
					return;
				}
				
				log.trace("Scheduler Pushing logs...");
				getInstance().pushLogsToDB();
				log.trace("Scheduler Pushed logs.");
			}
		}, 0, 500);
		
		Runtime.getRuntime().addShutdownHook(new Thread(()-> {
				shutdown = true;
				logTimer.cancel();
				for(String key:getSaveLogCache().keySet()){
					getSaveLogCache().evict(key);
				}
				
				for(String key:getUpdateLogCache().keySet()){
					getUpdateLogCache().evict(key);
				}

				for(String key:getResultExceptionLog().keySet()){
					getResultExceptionLog().evict(key);
				}
			}));
    }
    
	private static LoggerImpl instance;
	
	public static LoggerImpl getInstance() {
		if(instance == null){
			instance = CDI.current().select(LoggerImpl.class).get();
		}
		return instance;
	}

	@Transactional(TransactionType.SINGLETON)
	public synchronized void pushLogsToDB() {
		if(shutdown)
			return;
		
		List<ProcessingLog> sortList = new ArrayList<>(getSaveLogCache().values());
		sortList.sort(logComparator);
		
		Queue<ProcessingLog> logQueue = new LinkedList<>(sortList);
		log.trace("Pushing {} new logs to database....", logQueue.size());
		while (!logQueue.isEmpty() ) {
			ProcessingLog notificationLog = logQueue.poll();
			try {
				storeManager.getDataStore().save(notificationLog);
			} catch (Exception e) {
				log.error("Unable to persist notification log.");
				log.error(MESSAGE_DUMP, dump(notificationLog));
			} finally {
				getSaveLogCache().remove(notificationLog.getMid());
			}
		}
		log.trace("Pushed new logs to database. {} logs remaining", logQueue.size());
	
		if(shutdown)
			return;
		
		sortList = new ArrayList<>(getUpdateLogCache().values());
		sortList.sort(logComparator);
		
		logQueue = new LinkedList<>(sortList);
		
		log.trace("Pushing {} log updates to database....", logQueue.size());
		while(!logQueue.isEmpty()){
			ProcessingLog notificationLog = logQueue.poll();
			try {
				storeManager.getDataStore().update(notificationLog);
			} catch (Exception e) {
				log.error("Unable to persist notification log.");
				log.error(MESSAGE_DUMP, dump(notificationLog));
			} finally {
				getUpdateLogCache().remove(notificationLog.getMid());
			}
		}
		log.trace("Pushed log updates to database. {} logs remaining", logQueue.size());
		
		if(shutdown)
			return;
		
		List<ResultExceptionLog> sortExceptionLogList = new ArrayList<>(getResultExceptionLog().values());
		sortExceptionLogList.sort((r1,r2)-> r1.getExceptionDt().compareTo(r2.getExceptionDt()));
		
		Queue<ResultExceptionLog> exceptionQueue = new LinkedList<>(sortExceptionLogList);
		log.trace("Pushing {} exceptions to database....", exceptionQueue.size());
		while (!exceptionQueue.isEmpty()) {
			ResultExceptionLog exceptionLog = exceptionQueue.poll();
			try {
				storeManager.getDataStore().save(exceptionLog);
			} catch (Exception e) {
				log.error("Unable to persist result exception log.");
				log.error(MESSAGE_DUMP, dump(exceptionLog));
			} finally {
				getResultExceptionLog().remove(exceptionLog.hashCodeAsString());
			}
		}
		log.trace("Pushed exceptions to database. {} logs remaining", logQueue.size());
	}
	
	private String dump(Serializable log) {
		byte[] logData = serializer.toByteArray(log);
		return Base64.getEncoder().encodeToString(logData);
	}

	@Override
	public void logResultException(AgentConfiguration agentConfiguration, AgentModule agentModule,
			Exception e, Result<?> result) {
		ResultExceptionLog exceptionLog = new ResultExceptionLog();
		exceptionLog.setAgentConfiguration(agentConfiguration);
		exceptionLog.setAgentModule(agentModule);
		exceptionLog.setExceptionType(e.getClass().getName());
		exceptionLog.setExceptionData(serializer.toByteArray(e));
		exceptionLog.setResultData(serializer.toByteArray(result));
		getResultExceptionLog().put(exceptionLog.hashCodeAsString(), exceptionLog);
	}
	
	@Override
	public List<ResultExceptionLog> getUnpersistedResultExceptionLogs() {
		ArrayList<ResultExceptionLog> unpersistedLogs = 
				new ArrayList<>(getResultExceptionLog().values());
		return Collections.unmodifiableList(unpersistedLogs);
	}
	
	@Override
	public ResultExceptionLog getResultExceptionLogByID(long relID) {
		return storeManager.getDataStore().get(ResultExceptionLog.class, relID);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#getUnpersistedLogs()
	 */
	@Override
	public List<ProcessingLog> getUnpersistedProcessingLogs(){
		ArrayList<ProcessingLog> unpersistedLogs = 
				new ArrayList<>(getSaveLogCache().values());
		unpersistedLogs.addAll(getUpdateLogCache().values());
		return Collections.unmodifiableList(unpersistedLogs);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#log(com.quakearts.notification.core.Message, java.lang.String, boolean)
	 */
	@Override
	public void logMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String response, boolean isError){
		saveLog(agentConfiguration, agentModule, mssg, LogType.INFO, response,isError);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#store(com.quakearts.notification.core.Message, java.lang.String, boolean)
	 */
	@Override
	public void storeMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String details){
		saveLog(agentConfiguration, agentModule, mssg, LogType.STORED, details, false);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#queue(com.quakearts.notification.core.Message, java.lang.String)
	 */
	@Override
	public void queueMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg,String reason){
		saveLog(agentConfiguration, agentModule, mssg, LogType.QUEUED, reason, false);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#getLogByID(long)
	 */
	@Override
	@Transactional(TransactionType.SINGLETON)
	public ProcessingLog getProcessingLogByID(long logID){
		return storeManager.getDataStore().get(ProcessingLog.class, Long.valueOf(logID));
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#findMessageLogByMid(java.lang.String)
	 */
	@Override
	@Transactional(TransactionType.SINGLETON)
	public ProcessingLog getProcessingLogByMid(String mid){
		
		ProcessingLog foundLog = getUpdateLogCache().get(mid);
		if(foundLog!=null){
			return foundLog;
		}

		foundLog = getSaveLogCache().get(mid);
		if(foundLog!=null){
			return foundLog;
		}

		Optional<ProcessingLog> optionalLog = storeManager.getDataStore().find(ProcessingLog.class)
				.filterBy("mid").withAValueEqualTo(mid).thenGetFirst();
		
		if(optionalLog.isPresent()){
			return optionalLog.get();
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#updateLog(com.quakearts.notification.model.NotificationLog)
	 */
	@Override
	public void updateLog(ProcessingLog notificationLog){
		getUpdateLogCache().put(notificationLog.getMid(), notificationLog);
	}

	private void saveLog(AgentConfiguration agentConfiguration, AgentModule agentModule, 
			Message<?> mssg, LogType type, String statusMessage, boolean isError){
		if(log.isTraceEnabled())
			log.trace("Saving message to queue. Type: {}. statusMessage: {}", type, statusMessage);
		
		ProcessingLog notelog = new ProcessingLog();
		
		notelog.setStatusMessage(statusMessage);
		notelog.setMid(mssg.getId());

		AgentConfiguration toSaveAgentConfiguration = new AgentConfiguration();
		toSaveAgentConfiguration.setId(agentConfiguration.getId());
		notelog.setAgentConfiguration(toSaveAgentConfiguration);

		AgentModule toSaveAgentModule = new AgentModule();

		toSaveAgentModule.setId(agentModule.getId());
		notelog.setAgentModule(toSaveAgentModule);

		String[] receipients = mssg.getRecipients(); 
		notelog.setRecipient(Arrays.toString(receipients));
		notelog.setType(type);
		notelog.setMessageData(serializer.toByteArray(mssg));
		notelog.setRetries(0);
		notelog.setError(isError);
		notelog.setLogDt(new Date());
		
		OrmStringConcatUtil.trimStrings(notelog);
		
		getSaveLogCache().put(notelog.getMid(), notelog);
	}

	/**
	 * @return the log_cache
	 */
	private Cache<String, ProcessingLog> getSaveLogCache() {
		if(saveLogCache==null){
			saveLogCache = cacheManager.getCache(ProcessingLog.class,".NEW");
		}
		return saveLogCache;
	}

	private Cache<String, ProcessingLog> getUpdateLogCache() {
		if(updateLogCache==null){
			updateLogCache = cacheManager.getCache(ProcessingLog.class,".UPDATE");
		}
		return updateLogCache;
	}
	
	private Cache<String, ResultExceptionLog> getResultExceptionLog(){
		if(resultExceptionLogCache == null){
			resultExceptionLogCache = cacheManager.getCache(ResultExceptionLog.class, null);
		}
		
		return resultExceptionLogCache;
	}
}
