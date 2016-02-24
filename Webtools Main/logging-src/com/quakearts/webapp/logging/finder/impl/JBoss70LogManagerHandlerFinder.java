package com.quakearts.webapp.logging.finder.impl;

import java.util.logging.Handler;
import com.quakearts.webapp.logging.WebListenerRegistrar;

public class JBoss70LogManagerHandlerFinder extends AbstractHandlerFinder {
	
	protected WebListenerRegistrar findFromHandlers(Handler[] handlers){
		for(Handler handler:handlers){
			if(handler instanceof WebListenerRegistrar)
				return (WebListenerRegistrar) handler;
		}
		return null;
	}
}
