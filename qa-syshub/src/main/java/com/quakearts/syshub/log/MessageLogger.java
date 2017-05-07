package com.quakearts.syshub.log;

import java.util.List;

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.model.ProcessingLog;

public interface MessageLogger {

	List<ProcessingLog> getUnpersistedLogs();

	void log(Message<?> mssg, String response, boolean isError);

	void store(Message<?> mssg, boolean isError);

	void store(Message<?> mssg, String details, boolean isError);

	void queue(Message<?> mssg, String reason);

	List<ProcessingLog> findMessagesByDetails(String messageDetails, Byte type, String errorStatus, String source)
			throws Exception;

	ProcessingLog getLogByID(long logID);

	ProcessingLog findMessageLogByMid(String mid);

	void updateLog(ProcessingLog notificationLog) throws Exception;

}