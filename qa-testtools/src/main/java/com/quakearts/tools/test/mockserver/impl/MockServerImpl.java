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
package com.quakearts.tools.test.mockserver.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.net.SSLHostConfig;

import com.quakearts.tools.test.mockserver.MockServer;
import com.quakearts.tools.test.mockserver.configuration.Configuration;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.fi.DefaultAction;
import com.quakearts.tools.test.mockserver.model.MockAction;

public class MockServerImpl implements MockServer {

	Configuration configuration;
	private Set<MockAction> actionsSet = Collections.newSetFromMap(new ConcurrentHashMap<MockAction, Boolean>());
	private Set<DefaultAction> defaultActions = Collections.newSetFromMap(new ConcurrentHashMap<DefaultAction, Boolean>());
	private Tomcat tomcat;
	private boolean started;
	
	@Override
	public synchronized void start() {
		if(!started) {
			if(configuration.getMockingMode() == MockingMode.MOCK && actionsSet.isEmpty()) {
				throw new MockServerRuntimeException("Invalid Setup. No MockAction have been configured for the mock server");
			}

			tomcat = new Tomcat();
			
			File file = new File("tomcat-test");
			File webappsFile = new File(file,"webapps");
			if(!file.exists()) {
				file.mkdir();
				webappsFile.mkdir();
			}
			
			if(!webappsFile.exists()) {
				webappsFile.mkdir();
			}
						
			try {
				tomcat.setBaseDir(file.getCanonicalPath());
			} catch (IOException e) {
				throw new MockServerRuntimeException("Unable to start embedded server", e);
			}
			tomcat.getConnector();
			
			if(configuration.getPort()>0)
				tomcat.getConnector().setPort(configuration.getPort());
			
			if(configuration.getIPInterface()!=null)
				tomcat.setHostname(configuration.getIPInterface());
			
			if(configuration.useTLS()) {
				SSLHostConfig hostConfig = new SSLHostConfig();
				hostConfig.setProtocols("TLSv1.2");
				hostConfig.setCertificateKeystoreType(configuration.getKeyStoreType());
				hostConfig.setCertificateKeystorePassword(configuration.getKeyStorePassword());
				hostConfig.setCertificateKeystoreFile(configuration.getKeyStore());
				
				tomcat.getConnector().addSslHostConfig(hostConfig);
				tomcat.getConnector().setScheme("https");
				tomcat.getConnector().setProperty("SSLEnabled", "true");
				tomcat.getConnector().setSecure(true);				
			}
			
			Context context = tomcat.addContext("", "");
			
			MockServerServlet servlet;
			servlet = new MockServerServlet(actionsSet, defaultActions, configuration);
			
			tomcat.addServlet("", MockServerServlet.class.getSimpleName(), servlet);
			context.addServletMappingDecoded("/*", MockServerServlet.class.getSimpleName());
						
			try {
				tomcat.start();
			} catch (LifecycleException e) {
				throw new MockServerRuntimeException("Unable to start embedded server", e);
			}
	        
	        new Thread(()->{
	            tomcat.getServer().await();
	        }).start();
	        	        
			started = true;
		}
	}

	@Override
	public synchronized void stop() {
		if(started) {
			try {
				tomcat.stop();
			} catch (LifecycleException e) {
			}
			started = false;
		}
	}

	@Override
	public MockServer configure(Configuration configuration) {
		this.configuration = configuration;
		return this;
	}

	@Override
	public MockServer configureFromFile(String fileName) throws IOException {
		return configureFromFile(new File(fileName));
	}

	@Override
	public MockServer configureFromFile(File file) throws IOException {
		return configureFromStream(new FileInputStream(file));
	}

	@Override
	public MockServer configureFromStream(InputStream inputStream) throws IOException {
		configuration = ConfigurationBuilder.newConfiguration().fromStream(inputStream);
		return this;
	}

	@Override
	public MockServer addDefaultActions(DefaultAction... actionsArray) {
		for(DefaultAction action:actionsArray)
			defaultActions.add(action);
		return this;
	}

	@Override
	public MockServer add(MockAction... mockActions) {
		for(MockAction action:mockActions)
			actionsSet.add(action);
		
		return this;
	}

}
