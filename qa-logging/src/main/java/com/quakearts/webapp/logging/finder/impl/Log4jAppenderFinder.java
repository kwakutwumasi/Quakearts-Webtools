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
package com.quakearts.webapp.logging.finder.impl;

import javax.servlet.ServletConfig;

import org.apache.log4j.Logger;

import com.quakearts.webapp.logging.WebListenerRegistrar;
import com.quakearts.webapp.logging.finder.RegistrarFinder;

public class Log4jAppenderFinder implements RegistrarFinder {

	@Override
	public WebListenerRegistrar locateWebListenerRegistrar(ServletConfig config) throws Exception {
		String appenderName = config.getInitParameter("appender.name");
		String loggerName =  config.getInitParameter("logger.name");
		
		if(appenderName == null){
			appenderName = "WEB";
		}
		
		Object appenderObj;
		Logger logger;
		if(loggerName!=null)
			logger = Logger.getLogger(loggerName);
		else
			logger = Logger.getRootLogger();
		
		if(logger!=null)
			appenderObj = logger.getAppender(appenderName);
		else
			throw new IllegalStateException("Cannot locate "+(loggerName==null?"root logger":"logger named "+loggerName));
		
		if(appenderObj instanceof WebListenerRegistrar){
			return (WebListenerRegistrar) appenderObj;
		} else {
			throw new IllegalArgumentException("Cannot locate appender named "+appenderName+"."+(appenderObj==null?"":" Found "+appenderObj.getClass().getName()));
		}
	}
}
