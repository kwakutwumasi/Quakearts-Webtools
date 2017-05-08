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
package com.quakearts.webapp.orm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.quakearts.webapp.orm.exception.DataStoreException;

@WebListener
public class DataStoreFactoryContextListener implements ServletContextListener {

	private static final String DATASTOREFACTORY = "com.quakearts.webapp.orm.FACTORY";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String className = sce.getServletContext().getInitParameter(DATASTOREFACTORY);
		if(className==null)
			throw new DataStoreException("Unable to find the datastore factory");
		
		try {
			Class<?> dataStoreFactoryClass = Class.forName(className);
			
			dataStoreFactoryClass.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new DataStoreException("Unable to load the datastore factory class named "+className
					+". Exception of type " + e.getClass().getName() 
					+ " was thrown. Message is " + e.getMessage());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
