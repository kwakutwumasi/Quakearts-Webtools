package com.quakearts.syshub.log;

import javax.enterprise.inject.Produces;

public class MessageLoggerFactory {

	@Produces 
	public @MessageLogging MessageLogger getMessageLogger(){
		return ProcessLoggerImpl.getInstance();
	}
}
