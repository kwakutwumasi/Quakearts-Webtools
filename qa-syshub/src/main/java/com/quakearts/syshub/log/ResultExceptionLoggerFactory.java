package com.quakearts.syshub.log;

import javax.enterprise.inject.Produces;

public class ResultExceptionLoggerFactory {

	@Produces 
	public @ResultExceptionLogging ResultExceptionLogger getResultExceptionLogger(){
		return ProcessLoggerImpl.getInstance();
	}
}
