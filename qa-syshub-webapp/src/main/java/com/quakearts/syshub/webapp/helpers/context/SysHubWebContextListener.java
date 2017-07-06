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
package com.quakearts.syshub.webapp.helpers.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.quakearts.classannotationscanner.ClasspathScanner;

@WebListener
public class SysHubWebContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String packages = sce.getServletContext().getInitParameter("com.quakearts.syshub.MODULES");
		
		ClasspathScanner scanner;
		if(packages != null){
			scanner = new ClasspathScanner(packages.split(","));
		} else {
			scanner = new ClasspathScanner();
		}
		
		scanner.addAnnotationListener(new ConfigurationPropertyAnnotationScanner()).scan();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
