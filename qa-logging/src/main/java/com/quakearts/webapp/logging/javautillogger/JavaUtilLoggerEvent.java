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

import java.util.logging.Level;
import org.jboss.logmanager.ExtLogRecord;

import com.quakearts.webapp.logging.abstraction.WebListenerLevel;
import com.quakearts.webapp.logging.abstraction.WebListenerLoggingEvent;

public class JavaUtilLoggerEvent extends WebListenerLoggingEvent {

	public JavaUtilLoggerEvent(ExtLogRecord logRecord) {
		super(getLevel(logRecord.getLevel()), logRecord.getLoggerName(), logRecord.getMessage(), logRecord.getThreadName(), logRecord.getMillis());
	}

	private static WebListenerLevel getLevel(Level level){
		if(level==null)
			return WebListenerLevel.INFO;
		else if(level.getName().equalsIgnoreCase("TRACE"))
			return WebListenerLevel.TRACE;
		else if(level.getName().equalsIgnoreCase("WARN"))
			return WebListenerLevel.WARN;
		else if(level.getName().equalsIgnoreCase("FATAL"))
			return WebListenerLevel.FATAL;
		else if(level.getName().equalsIgnoreCase("DEBUG"))
			return WebListenerLevel.DEBUG;
		else if(level.getName().equalsIgnoreCase("ERROR"))
			return WebListenerLevel.ERROR;
		else
			return WebListenerLevel.INFO;
	}
}
