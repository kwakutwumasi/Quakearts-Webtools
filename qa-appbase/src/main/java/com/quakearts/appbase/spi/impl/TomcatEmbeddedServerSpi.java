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
package com.quakearts.appbase.spi.impl;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;

public class TomcatEmbeddedServerSpi implements EmbeddedWebServerSpi {
	
	@Override
	public void initiateEmbeddedWebServer() {
        File webappDirLocation = new File("webapp");
        
        if(!webappDirLocation.exists()){
        	webappDirLocation.mkdir();
        } else if(!webappDirLocation.isDirectory()){
        	throw new ConfigurationException(webappDirLocation.getAbsolutePath()+ " is not a directory");
        }
        
        final Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx;
		try {
			ctx = (StandardContext) tomcat.addWebapp("/", webappDirLocation.getAbsolutePath());
		} catch (ServletException e) {
			throw new ConfigurationException("ServletException adding webapp "+webappDirLocation.getAbsolutePath(), e);
		}
		if(ctx.getJarScanner() instanceof StandardJarScanner)
			((StandardJarScanner)ctx.getJarScanner()).setScanClassPath(false);
        
		Main.log.debug("Configured web application with base directory: " +webappDirLocation.getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File(webappDirLocation,"WEB-INF/classes");
        
        if(!additionWebInfClasses.exists()){
        	additionWebInfClasses.mkdirs();
        } else if(!additionWebInfClasses.isDirectory()){
        	throw new ConfigurationException(additionWebInfClasses.getAbsolutePath()+ " is not a directory");
        }
        
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);
        Main.log.debug("Configured additional WEB-INF/classes with directory: " +additionWebInfClasses.getAbsolutePath());
                
        try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new ConfigurationException("Unable to start embedded server", e);
		}
        
        new Thread(()->{
            tomcat.getServer().await();
        }).start();
	}

	@Override
	public void shutdownEmbeddedWebServer() {
	}

}
