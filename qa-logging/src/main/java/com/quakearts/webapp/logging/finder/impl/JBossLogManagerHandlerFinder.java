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
package com.quakearts.webapp.logging.finder.impl;

import java.util.Enumeration;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;

import org.jboss.logmanager.LogManager;

import com.quakearts.webapp.logging.WebListenerRegistrar;
import com.quakearts.webapp.logging.finder.RegistrarFinder;

public class JBossLogManagerHandlerFinder implements RegistrarFinder {

	@Override
	public WebListenerRegistrar locateWebListenerRegistrar(ServletConfig config)
			throws Exception {
		Logger logger = LogManager.getLogManager().getLogger("");
		WebListenerRegistrar registrar = findFromHandlers(logger.getHandlers());
		if(registrar==null){
			Enumeration<String> names=LogManager.getLogManager().getLoggerNames();
			while (names.hasMoreElements()) {
				logger= LogManager.getLogManager().getLogger((String) names.nextElement());
				registrar = findFromHandlers(logger.getHandlers());
				if(registrar!=null)
					return registrar;
			}
			throw new IllegalStateException("Cannot find log handler of type "+WebListenerRegistrar.class.getName());
		}else
			return registrar;
	}
	
	private WebListenerRegistrar findFromHandlers(Handler[] handlers){
		for(Handler handler:handlers){
			if(handler instanceof WebListenerRegistrar)
				return (WebListenerRegistrar) handler;
		}
		return null;
	}
}
