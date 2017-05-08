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
package com.quakearts.webapp.logging.javautillogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import org.jboss.logmanager.ExtHandler;
import org.jboss.logmanager.ExtLogRecord;

import com.quakearts.webapp.logging.WebListener;
import com.quakearts.webapp.logging.WebListenerRegistrar;

public class WebLogHandler extends ExtHandler implements WebListenerRegistrar {

	private List<WebListener> listeners = new ArrayList<WebListener>();
	private final ConcurrentLinkedQueue<ExtLogRecord> records = new ConcurrentLinkedQueue<ExtLogRecord>();
	private final Timer timer = new Timer(true);

	private Semaphore token = new Semaphore(1);

	public WebLogHandler(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					token.acquire();
					ExtLogRecord record;
					while((record = records.poll())!=null)
						for(WebListener listener:listeners){
							listener.append(new JavaUtilLoggerEvent(record));
						}
				} catch (Exception e) {
				} finally {
					token.release();
				}
			}
		}, 0, 1);
	}
	
	@Override
	public void registerListener(WebListener listener) {
		try {
			token.acquire();
			listeners.add(listener);
		} catch (InterruptedException e) {
		} finally {
			token.release();
		}
	}

	@Override
	protected void doPublish(ExtLogRecord record) {
		records.add(record);
	}


	@Override
	public boolean unregisterListener(WebListener listener) {
		try {
			token.acquire();
			return listeners.remove(listener);
		} catch (InterruptedException e) {
			return false;
		} finally {
			token.release();
		}
	}

	@Override
	public void close() throws SecurityException {
		try {
			token.acquire();
			listeners.clear();
			timer.cancel();
		} catch (InterruptedException e) {
		}finally{
			token.release();
		}
	}

	@Override
	public void flush() {
	}

}
