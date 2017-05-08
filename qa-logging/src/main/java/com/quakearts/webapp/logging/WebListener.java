/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import com.quakearts.webapp.logging.abstraction.WebListenerLevel;
import com.quakearts.webapp.logging.abstraction.WebListenerLoggingEvent;

public class WebListener {

	private int bufferSize=300;
	private WebListenerLevel threshold = WebListenerLevel.INFO;
	private ConcurrentLinkedQueue<WebListenerLoggingEvent> eventBuffer = new ConcurrentLinkedQueue<WebListenerLoggingEvent>();
	private final Semaphore token = new Semaphore(1);
	private WebListenerRegistrar appender;
	
	public WebListener(WebListenerRegistrar appender){
		this.appender = appender;
		appender.registerListener(this);
	}
	
	public void append(WebListenerLoggingEvent event) {
		try {
			token.acquire();
			if(event.getLevel().isGreaterOrEqual(threshold)){
				if(eventBuffer.size() == bufferSize){
					eventBuffer.poll();
				}
				eventBuffer.add(event);
			}			
		} catch (Exception e) {
		} finally {
			token.release();
		}
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public int getBufferSize() {
		return bufferSize;
	}
	
	public List<WebListenerLoggingEvent> getEvents(){
		List<WebListenerLoggingEvent> latestEvents = new ArrayList<WebListenerLoggingEvent>();
		WebListenerLoggingEvent loggingEvent;
		while((loggingEvent = eventBuffer.poll())!=null){
			latestEvents.add(loggingEvent);
		}
		return latestEvents;
	}
	
	public void setThreshold(WebListenerLevel threshold) {
		try{
			token.acquire();
			this.threshold = threshold;
		} catch (Exception e) {
		} finally {
			token.release();
		}
	}

	public WebListenerLevel getThreshold() {
		return threshold;
	}
	
	public void close(){
		try {
			token.acquire();
			this.appender.unregisterListener(this);
			eventBuffer.clear();
		} catch (Exception e) {
		} finally {
			token.release();
		}
	}
	
}
