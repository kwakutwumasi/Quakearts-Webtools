package com.quakearts.webapp.logging.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;

import com.quakearts.webapp.logging.abstraction.WebListenerLevel;
import com.quakearts.webapp.logging.abstraction.WebListenerLoggingEvent;

public class Log4jLoggingEvent extends WebListenerLoggingEvent {
	public Log4jLoggingEvent(LoggingEvent event){
		super(getLevel(event.getLevel()), event.getLoggerName(), event.getRenderedMessage(), event.getThreadName(), event.timeStamp);
	}
	
	private static WebListenerLevel getLevel(Level level){
		if(level==null)
			return WebListenerLevel.INFO;
		else if(level == Level.TRACE)
			return WebListenerLevel.TRACE;
		else if(level == Level.WARN)
			return WebListenerLevel.WARN;
		else if(level== Level.FATAL)
			return WebListenerLevel.FATAL;
		else if(level == Level.DEBUG)
			return WebListenerLevel.DEBUG;
		else if(level == Level.ERROR)
			return WebListenerLevel.ERROR;
		else
			return WebListenerLevel.INFO;
	}
}
