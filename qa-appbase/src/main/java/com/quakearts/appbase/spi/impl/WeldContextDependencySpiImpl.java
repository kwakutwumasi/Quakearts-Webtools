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

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.weld.bootstrap.api.helpers.RegistrySingletonProvider;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.module.web.servlet.WeldInitialListener;
import org.jboss.weld.module.web.servlet.WeldTerminalListener;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;
import com.quakearts.appbase.spi.beans.WebAppListener;
import com.quakearts.appbase.spi.beans.WebAppListener.Priority;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

@Vetoed
public class WeldContextDependencySpiImpl implements ContextDependencySpi {

	private Weld weld;
	private WeldContainer container;
	private static final String JAVA_COMP = "java:comp";
	private boolean unbindJavaComp = false;

	@Override
	public void initiateContextDependency() {
        weld = new Weld().containerId(RegistrySingletonProvider.STATIC_INSTANCE);
        container = weld.initialize();
        EmbeddedWebServerSpi serverSpi = EmbeddedWebServerSpiFactory
        		.getInstance().getEmbeddedWebServerSpi();
        
        serverSpi.addDefaultListener(new WebAppListener(WeldInitialListener.class, Priority.FIRST));
        serverSpi.addDefaultListener(new WebAppListener(WeldTerminalListener.class, Priority.LAST));
        
        JavaNamingDirectorySpi spi = JavaNamingDirectorySpiFactory.getInstance()
        		.getJavaNamingDirectorySpi();
        InitialContext context = spi.getInitialContext();
    	try {
            if(!javaCompIsBound(context)){
            	bindJavaComp(spi);
            }
			context.bind(JAVA_COMP+"/BeanManager", container.getBeanManager());
		} catch (NamingException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}
	}

	private void bindJavaComp(JavaNamingDirectorySpi spi) throws NamingException {
		unbindJavaComp = true;
		spi.createContext(JAVA_COMP);
	}

	private boolean javaCompIsBound(InitialContext context) {
		try {
			context.lookup(JAVA_COMP);
			return true;
		} catch (NamingException e) {
			return false;
		}
	}

	@Override
	public void shutDownContextDependency() {
		if (weld == null) {
			throw new ConfigurationException("Call to shutdown Weld without existing instance");
		}
		try {
			weld.shutdown();
		} catch (Exception e) {
			// Suppress error
		}
		JavaNamingDirectorySpi spi = JavaNamingDirectorySpiFactory.getInstance()
        		.getJavaNamingDirectorySpi();
        InitialContext context = spi.getInitialContext();
    	try {
            if(unbindJavaComp){
    			context.unbind(JAVA_COMP);
    			unbindJavaComp = false;
            }
			context.unbind(JAVA_COMP+"/BeanManager");
		} catch (NamingException e) {
			//Do nothing
		}
	}
	
	@Override
	public <T> T getMainSingleton(Class<T> mainClass) {
		return container.select(mainClass).get();
	}
	
	@Override
	public BeanManager getBeanManager() {
		if(container!=null)
			return container.getBeanManager();
		
		return null;
	}
	
}
