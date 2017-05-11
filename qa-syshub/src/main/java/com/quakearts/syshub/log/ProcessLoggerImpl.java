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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant.TransactionType;
import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.core.utils.CacheManagerUtil;
import com.quakearts.syshub.core.utils.SerializationUtil;
import com.quakearts.syshub.core.utils.SystemDataStoreUtils;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;
import com.quakearts.syshub.model.ResultExceptionLog;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;
import com.quakearts.webapp.orm.stringconcat.OrmStringConcatUtil;
import com.quakearts.syshub.model.ProcessingLog.LogType;

import org.infinispan.Cache;

@ApplicationScoped
public class ProcessLoggerImpl implements MessageLogger, ResultExceptionLogger {
	private Cache<String, ProcessingLog> save_log_cache;
	private Cache<String, ProcessingLog> update_log_cache;
	private Comparator<ProcessingLog> logComparator = (n1,n2)-> n1.getLogDt().compareTo(n2.getLogDt());
	private final Logger log = LoggerFactory.getLogger(ProcessLoggerImpl.class);
	private final Timer log_timer = new Timer(true);
	private static Cache<String, ResultExceptionLog> result_exception_log_cache;
	private SystemDataStoreUtils systemDataStoreUtils = SystemDataStoreUtils.getInstance();
		
	ProcessLoggerImpl() {
		log_timer.schedule(new TimerTask() {	
			@Override
			public void run() {
				try {
					pushLogsToDB();
				} catch (Exception e) {
					log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
							+ ". Exception occured whiles pushing logs to database.");
				}
			}
		}, 0, 5000);
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				log_timer.cancel();
				for(String key:save_log_cache.keySet()){
					save_log_cache.evict(key);
				}
			}
		}));
    }
    
	private static ProcessLoggerImpl instance;
	
	public static ProcessLoggerImpl getInstance() {
		if(instance == null){
			instance = CDI.current().select(ProcessLoggerImpl.class).get();
		}
		return instance;
	}

	@TransactionParticipant(TransactionType.SINGLETON)
	public void pushLogsToDB() throws Exception {
		if(getSaveLogCache()==null)
			return;
		
		List<ProcessingLog> sortList = new ArrayList<>(getSaveLogCache().values());
		sortList.sort(logComparator);
		
		Queue<ProcessingLog> logQueue = new LinkedList<>(sortList);
		
		while ( !logQueue.isEmpty() ) {
			ProcessingLog notificationLog = logQueue.poll();
			try {
				saveLogToDB(notificationLog);
			} catch (Exception e) {
				log.error("Unable to persist notification log.");
				log.error("Message dump: ["+notificationLog.toString()+"]");
			} finally {
				getSaveLogCache().remove(notificationLog.getMid());
			}
		}
	
		sortList = new ArrayList<>(getUpdateLogCache().values());
		sortList.sort(logComparator);
		
		logQueue = new LinkedList<>(sortList);
		
		while(!logQueue.isEmpty()){
			ProcessingLog notificationLog = logQueue.poll();
			try {
				updateLogToDB(notificationLog);
			} catch (Exception e) {
				log.error("Unable to persist notification log.");
				log.error("Message dump: ["+notificationLog.toString()+"]");
			} finally {
				getSaveLogCache().remove(notificationLog.getMid());
			}
		}
		
		List<ResultExceptionLog> sortExceptionLogList = new ArrayList<>(getResultExceptionLog().values());
		sortExceptionLogList.sort((r1,r2)-> r1.getExceptionDt().compareTo(r2.getExceptionDt()));
		
		Queue<ResultExceptionLog> exceptionQueue = new LinkedList<>(sortExceptionLogList);
		while (!exceptionQueue.isEmpty()) {
			ResultExceptionLog exceptionLog = exceptionQueue.poll();
			try {
				saveExceptionLogToDB(exceptionLog);
			} catch (Exception e) {
				log.error("Unable to persist notification log.");
				log.error("Message dump: ["+exceptionLog.toString()+"]");
			} finally {
				getResultExceptionLog().remove(exceptionLog.toString());
			}
		}
	}

	@Override
	public void logResultException(AgentConfiguration agentConfiguration, AgentModule agentModule,
			Exception e, Result result) {
		ResultExceptionLog log = new ResultExceptionLog();
		log.setAgentConfiguration(agentConfiguration);
		log.setAgentModule(agentModule);
		log.setExceptionType(e.getClass().getName());
		log.setExceptionData(SerializationUtil.getInstance().toByteArray(e));
		log.setResultData(SerializationUtil.getInstance().toByteArray(result));
		getResultExceptionLog().put(log.toString(), log);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#getUnpersistedLogs()
	 */
	@Override
	public List<ProcessingLog> getUnpersistedLogs(){
		ArrayList<ProcessingLog> unpersistedLogs = 
				new ArrayList<ProcessingLog>(getSaveLogCache().values());
		unpersistedLogs.addAll(getUpdateLogCache().values());
		return Collections.unmodifiableList(unpersistedLogs);
	}
	
	@Override
	public List<ResultExceptionLog> getUnpersistedResultExceptionLogs() {
		ArrayList<ResultExceptionLog> unpersistedLogs = 
				new ArrayList<ResultExceptionLog>(getResultExceptionLog().values());
		return Collections.unmodifiableList(unpersistedLogs);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#log(com.quakearts.notification.core.Message, java.lang.String, boolean)
	 */
	@Override
	public void log(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String response, boolean isError){
		saveLog(agentConfiguration, agentModule, mssg, LogType.INFO, response,isError);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#store(com.quakearts.notification.core.Message, boolean)
	 */
	@Override
	public void store(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, boolean isError){
		saveLog(agentConfiguration, agentModule, mssg, LogType.STORED, "",isError);
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#store(com.quakearts.notification.core.Message, java.lang.String, boolean)
	 */
	@Override
	public void store(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg,String details, boolean isError){
		saveLog(agentConfiguration, agentModule, mssg, LogType.STORED, details,isError);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#queue(com.quakearts.notification.core.Message, java.lang.String)
	 */
	@Override
	public void queue(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg,String reason){
		saveLog(agentConfiguration, agentModule, mssg, LogType.QUEUED, reason, false);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#findMessagesByDetails(java.lang.String, java.lang.Byte, java.lang.String, java.lang.String)
	 */
	@TransactionParticipant
	@Override
	public List<ProcessingLog> findMessagesByDetails(String messageDetails, Byte type, 
			String errorStatus, String source) throws Exception {
		List<ProcessingLog> list;
		try {	
			DataStore systemDataStore = systemDataStoreUtils.getSystemDataStore();
			ParameterMapBuilder parameters = new ParameterMapBuilder();
			parameters.addVariableString("message", messageDetails);
			if(type!=null)
				parameters.add("type", type);
			if(errorStatus!=null){
				parameters.add("error", Boolean.valueOf(errorStatus));
			}
			if(source!=null)
				parameters.add("source", source);
			
			list = systemDataStore.list(ProcessingLog.class,parameters.build());
			return list; 
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#getLogByID(long)
	 */
	@TransactionParticipant
	@Override
	public ProcessingLog getLogByID(long logID){
		return systemDataStoreUtils.getSystemDataStore().get(ProcessingLog.class, Long.valueOf(logID));
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#findMessageLogByMid(java.lang.String)
	 */
	@TransactionParticipant
	@Override
	public ProcessingLog findMessageLogByMid(String mid){
		
		ProcessingLog founndlog = getSaveLogCache().get(mid);
		if(founndlog!=null){
			return founndlog;
		}

		List<ProcessingLog> list = systemDataStoreUtils.getSystemDataStore().list(ProcessingLog.class, ParameterMapBuilder
				.createParameters().add("mid",mid).build());
		
		if(list.size()>0){
			Object obj = list.get(list.size()-1);
			return ((ProcessingLog)obj);
		} else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.notification.log.MessageLogger#updateLog(com.quakearts.notification.model.NotificationLog)
	 */
	@Override
	public void updateLog(ProcessingLog notificationLog) throws Exception{
		getUpdateLogCache().put(notificationLog.getMid(), notificationLog);
	}

	private void saveLog(AgentConfiguration agentConfiguration, AgentModule agentModule, 
			Message<?> mssg, LogType type, String statusMessage, boolean isError){
		if(log.isTraceEnabled())
			log.trace("Saving message to queue. Type: "+type+". statusMessage: "+statusMessage);
		
		ProcessingLog notelog = new ProcessingLog();
		
		notelog.setStatusMessage(statusMessage);
		notelog.setMid(mssg.getId());
		notelog.setAgentConfiguration(agentConfiguration);
		notelog.setAgentModule(agentModule);
		String[] receipients = mssg.getRecipients(); 
		notelog.setRecipient(Arrays.toString(receipients));
		notelog.setType(type);
		notelog.setMessageData(SerializationUtil.getInstance().toByteArray(mssg));
		notelog.setRetries(0);
		notelog.setError(isError);
		notelog.setLogDt(new Date());
		
		OrmStringConcatUtil.trimStrings(notelog);
		
		getSaveLogCache().put(notelog.getMid(), notelog);
	}

	private void saveLogToDB(ProcessingLog notificationLog) throws Exception{
		DataStore dataStore = systemDataStoreUtils.getSystemDataStore();
		ProcessingLog oldLog=findMessageLogByMid(notificationLog.getMid());
		if(oldLog==null)
			dataStore.save(notificationLog);
		else{
			notificationLog = (ProcessingLog) dataStore.refresh(notificationLog);
			notificationLog.setRetries(notificationLog.getRetries()+1);
			
			dataStore.update(notificationLog);
		}
		
		log.trace("Saved message. ID is "+notificationLog.getLogID());
	}
	
	private void saveExceptionLogToDB(ResultExceptionLog exceptionLog) throws Exception{
		systemDataStoreUtils.getSystemDataStore().save(exceptionLog);
	}
	
	private void updateLogToDB(ProcessingLog notificationLog) throws Exception{
		systemDataStoreUtils.getSystemDataStore().update(notificationLog);
	}

	/**
	 * @return the log_cache
	 */
	private Cache<String, ProcessingLog> getSaveLogCache() {
		if(save_log_cache==null){
			save_log_cache = CacheManagerUtil.getInstance().getCache(ProcessingLog.class);
		}
		return save_log_cache;
	}

	private Cache<String, ProcessingLog> getUpdateLogCache() {
		if(update_log_cache==null){
			update_log_cache = CacheManagerUtil.getInstance().getCache(ProcessingLog.class);
		}
		return update_log_cache;
	}
	
	private Cache<String, ResultExceptionLog> getResultExceptionLog(){
		if(result_exception_log_cache == null){
			result_exception_log_cache = CacheManagerUtil.getInstance().getCache(ResultExceptionLog.class);
		}
		
		return result_exception_log_cache;
	}
}
