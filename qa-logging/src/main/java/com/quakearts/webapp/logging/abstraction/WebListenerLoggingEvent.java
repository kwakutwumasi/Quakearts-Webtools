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
package com.quakearts.webapp.logging.abstraction;

public class WebListenerLoggingEvent {

	private WebListenerLevel level;
	private String loggerName;
	private String renderedMessage;
	private String threadName;
	private long timeStamp;
	
	public WebListenerLoggingEvent(WebListenerLevel level, String loggerName,
			String renderedMessage, String threadName, long timeStamp) {
		this.level = level;
		this.loggerName = loggerName;
		this.renderedMessage = renderedMessage;
		this.threadName = threadName;
		this.timeStamp = timeStamp;
	}

	public WebListenerLevel getLevel() {
		return level;
	}

	public String getLoggerName() {
		return loggerName;
	}

	public String getRenderedMessage() {
		return renderedMessage;
	}

	public String getThreadName() {
		return threadName;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

}
