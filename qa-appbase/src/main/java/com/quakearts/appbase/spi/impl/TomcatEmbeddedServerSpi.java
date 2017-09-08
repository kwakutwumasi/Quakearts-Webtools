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

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.scan.StandardJarScanner;
import com.quakearts.appbase.Main;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import static com.quakearts.appbase.internal.properties.AppBasePropertiesLoader.get;


public class TomcatEmbeddedServerSpi implements EmbeddedWebServerSpi {
	
	@Override
	public void initiateEmbeddedWebServer() {
		
        File webserversDirLocation = new File("webservers");
        
        if(!webserversDirLocation.exists()){
        	webserversDirLocation.mkdir();
        	return;
        } else if(!webserversDirLocation.isDirectory()){
        	throw new ConfigurationException(webserversDirLocation.getAbsolutePath()+ " is not a directory");
        } else {
        	AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
        	for(File webappDirLocation:webserversDirLocation.listFiles()){
        		if(webappDirLocation.isFile()){
        			launchInstance(webappDirLocation, loader);
        		}
        	}
        }
	}
	
	private void launchInstance(File webappDirLocation, AppBasePropertiesLoader loader) throws ConfigurationException {
        if(!webappDirLocation.exists()){
        	throw new ConfigurationException("Missing webapp: /"+webappDirLocation);
        }
        
        Map<String, Serializable> serverConfiguration = null;
        File configurationFile = new File(webappDirLocation, "META-INF"+File.separator+"server.config.json");
        
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        
        if(configurationFile.exists() && configurationFile.isFile()){
        	serverConfiguration = loader.loadParametersFromFile(configurationFile);
        } else if(!configurationFile.isFile()) {
        	throw new ConfigurationException("Invalid file in /"+webappDirLocation+". META-INF/server.config.json is not a file.");
        }
        
        final Tomcat tomcat = new Tomcat();

        if(serverConfiguration != null){
			if (serverConfiguration.containsKey("port")) {
				tomcat.setPort(get("port", Integer.class, serverConfiguration));
			}
			
			if(serverConfiguration.containsKey("protocol")){
				Connector connector = new Connector(get("connectorProtocol", String.class, serverConfiguration));
				tomcat.setConnector(connector);
			}
			
			if (serverConfiguration.containsKey("usessl") && get("usessl", Boolean.class, serverConfiguration)) {
				SSLHostConfig sslHostConfig = new SSLHostConfig();
			
				try {
					setPropertiesIfAvailable(sslHostConfig, "ssl", serverConfiguration);
				} catch (ConfigurationException e) {
					throw new ConfigurationException("Invalid ssl property in /"+webappDirLocation.getName()+":"+e.getMessage());
				} catch (IntrospectionException e) {
					throw new ConfigurationException("Unable to instrospect "+SSLHostConfig.class+": "+e.getMessage());
				}
				
				tomcat.getConnector().addSslHostConfig(sslHostConfig);
			}

			try{
				setPropertiesIfAvailable(tomcat.getConnector(), "connector", serverConfiguration);
			} catch (ConfigurationException e) {
				throw new ConfigurationException("Invalid connector property in /"+webappDirLocation.getName()+":"+e.getMessage(), e);
			} catch (IntrospectionException e) {
				throw new ConfigurationException("Unable to instrospect "+SSLHostConfig.class+": "+e.getMessage(), e);
			}
	    }
        
        
        for(File webappFolder:webappDirLocation.listFiles()){
        	if(!webappFolder.isDirectory())
        		continue;
        	
	        StandardContext ctx;
			try {
				ctx = (StandardContext) tomcat.addWebapp("/"+webappFolder.getName(), webappFolder.getAbsolutePath());
			} catch (ServletException e) {
				throw new ConfigurationException("ServletException adding webapp "+webappFolder.getAbsolutePath(), e);
			}
			
			if(ctx.getJarScanner() instanceof StandardJarScanner)
				((StandardJarScanner)ctx.getJarScanner()).setScanClassPath(false);
	        
			Main.log.debug("Configured web application with base directory: " +webappFolder.getAbsolutePath());
	
	        // Declare an alternative location for your "WEB-INF/classes" dir
	        // Servlet 3.0 annotation will work
	        File additionWebInfClasses = new File(webappFolder,"WEB-INF/classes");
	        
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
	        
	        File contextFile = new File(webappFolder,"META-INF/context.xml");
	        if(contextFile.exists()){
	        	try {
					ctx.setConfigFile(contextFile.toURI().toURL());
				} catch (MalformedURLException e) {
		        	throw new ConfigurationException(contextFile.getAbsolutePath()+ ": "+e.getMessage(), e);
				}
	        }
        }
        
        try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new ConfigurationException("Unable to start embedded server", e);
		}
        
        new Thread(()->{
            tomcat.getServer().await();
        }).start();
	}

	private void setPropertiesIfAvailable(Object target, String prefix, Map<String, Serializable> properties) throws IntrospectionException{
		BeanInfo info = Introspector.getBeanInfo(target.getClass());
		
		for(PropertyDescriptor propertyDescriptor: info.getPropertyDescriptors()){
			String propertyName = (prefix!=null?prefix+".":"")+propertyDescriptor.getName();
			try {
				if(propertyDescriptor.getWriteMethod()!=null 
						&& properties.containsKey(propertyName)){
					Method setMethod = propertyDescriptor.getWriteMethod();
					setMethod.invoke(target, get(propertyName,propertyDescriptor.getPropertyType(), properties));
				}
			} catch (ClassCastException e) {
				throw new ConfigurationException(propertyName+" cannot be cast to "+propertyDescriptor.getPropertyType());
			} catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException e) {
				throw new ConfigurationException(propertyName+" cannot be written to "+target.getClass().getName()+": "+e.getMessage());
			}
		}
	}
	
	@Override
	public void shutdownEmbeddedWebServer() {
	}
	
}
