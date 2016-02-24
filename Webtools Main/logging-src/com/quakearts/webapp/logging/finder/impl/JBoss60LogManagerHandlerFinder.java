package com.quakearts.webapp.logging.finder.impl;

import java.util.logging.Handler;

import org.apache.log4j.Appender;
import org.jboss.logmanager.log4j.handlers.Log4jAppenderHandler;

import com.quakearts.webapp.logging.WebListenerRegistrar;

public class JBoss60LogManagerHandlerFinder extends AbstractHandlerFinder {
	
	protected WebListenerRegistrar findFromHandlers(Handler[] handlers){
		for(Handler handler:handlers){
			if(handler instanceof Log4jAppenderHandler){
				Appender appender= ((Log4jAppenderHandler)handler).getAppender();
				if(appender instanceof WebListenerRegistrar)
					return (WebListenerRegistrar) appender;
			}
			if(handler instanceof WebListenerRegistrar)
				return (WebListenerRegistrar) handler;
		}
		return null;
	}
}
