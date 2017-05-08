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
package com.quakearts.webapp.logging.log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.quakearts.webapp.logging.WebListener;
import com.quakearts.webapp.logging.WebListenerRegistrar;

public class WebLogAppender extends AppenderSkeleton implements WebListenerRegistrar {

	private final List<WebListener> listeners = new ArrayList<WebListener>();
	private final Semaphore token = new Semaphore(1);
	private final ConcurrentLinkedQueue<LoggingEvent> events = new ConcurrentLinkedQueue<LoggingEvent>();
	private final Timer timer = new Timer(true);
	
	public WebLogAppender(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					token.acquire();
					LoggingEvent event;
					while((event = events.poll())!=null)
						for(WebListener listener:listeners){
							listener.append(new Log4jLoggingEvent(event));
						}
				} catch (Exception e) {
				} finally {
					token.release();
				}
			}
		}, 0, 1);
	}

	@Override
	protected void append(LoggingEvent event) {
		events.add(event);
	}

	@Override
	public void close() {
		try {
			token.acquire();
			listeners.clear();
			events.clear();
		} catch (Exception e) {
		} finally {
			token.release();
		}
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.webapp.logging.WebListenerRegistrar#registerListener(com.quakearts.webapp.logging.WebListener)
	 */
	@Override
	public void registerListener(WebListener listener){
		try {
			token.acquire();
			listeners.add(listener);			
		} catch (Exception e) {
		} finally {
			token.release();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.webapp.logging.WebListenerRegistrar#unregisterListener(com.quakearts.webapp.logging.WebListener)
	 */
	@Override
	public boolean unregisterListener(WebListener listener){
		boolean result = false;
		try {
			token.acquire();
			result = listeners.remove(listener);
		} catch (Exception e) {
		} finally {
			token.release();
		}
		return result;
	}
}
