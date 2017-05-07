package com.quakearts.syshub.log;

import java.util.List;

import com.quakearts.syshub.model.ResultExceptionLog;

public interface ResultExceptionLogger {
	List<ResultExceptionLog> getUnpersistedResultExceptionLogs();
	void log(ResultExceptionLog log);
}
