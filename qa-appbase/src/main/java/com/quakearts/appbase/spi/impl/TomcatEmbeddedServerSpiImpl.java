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

import java.beans.IntrospectionException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.inject.Vetoed;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.coyote.http2.Http2Protocol;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.scan.StandardJarScanner;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.classpath.ClassPathSupport;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.internal.properties.impl.AppBasePropertiesLoaderImpl;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.beans.WebAppListener;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseCDIInstanceManager;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseContextConfig;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseVirtualDirectoryResourceSet;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseWebAppLoader;

/**An implementation of the {@linkplain EmbeddedWebServerSpi} that starts up Tomcat Embedded.
 * The <code>webservers/</code> folder at the root of the application is traversed. Each folder within the <code>webservers/</code> folder
 * represents a single web server instance. Each folder within the <code>webservers/</code> folder should contain a 
 * folder named <code>conf/</code> that contains a file named <code>server.config.json</code>. The contents of the file must be a valid
 * JSON object. If the file is empty then empty curly brackets should be used: <code>{}</code>.
 * <br />
 * For users that need only one web server there are several options:
 * <br />* A JSON file named main-server.config.json can be placed at the root of the classpath. 
 * <br />* Users who prefer environment variables can set variables named after the properties and prefixed by 'web.'
 * (case sensitive environments) or 'WEB_' (case insensitive environments). For case insensitive environments, 
 * CamelCase properties should be converted to underscore variants. Ex a property named camelCase should be stored as CAMEL_CASE. (See 
 * {@linkplain AppBasePropertiesLoaderImpl#loadParametersFromEnvironment(String)}).
 * <br />* For users who wish to use a single configuration file the properties can be stored in the 'app.config.json' at the root of the classpath under 
 * the JSON property "webserver".
 * <br /><br />
 * The properties of the JSON object are described below:
 * <br />
 * <code>port</code>: a JSON integer value. The IP port to listen on for connections
 * <br />
 * <code>useapr</code>: a JSON boolean value. Indicates whether to use the Apache Runtime Library. See Tomcat documentation for more information
 * <br />
 * <code>hostname</code>: a JSON string value. The hostname to bind the listener to
 * <br />
 * <code>connectorProtocol</code>: a JSON string value. The FQDN of the Tomcat Connector to use. See Tomcat documentation for more information
 * <br />
 * <code>connectorProtocol.xxx</code>: property xxx will be passed to the <code>public void setProperty(String,String)</code> 
 * method of the Tomcat Connector object.
 *  See the documentation or code of the Connector implementation for possible property values
 * <br />
 * <code>connector.xxx</code>: property xxx will be set on the Tomcat Connector object. 
 * See the documentation or code of the Connector implementation for possible property values
 * <br />
 * <code>usessl</code>: a JSON boolean value. Indicates whether TLS/SSL is used
 * <br />
 * <code>protocols</code>: a JSON string value. A comma separated list of TLS/SSL protocols to use
 * <br />
 * <code>sslHostConfig.xxx</code>: property xxx will be set on the Tomcat SslHostConfig object. 
 * See the documentation or code of the SslHostConfig implementation for possible property values
 * <br />
 * <code>usehttp2</code>: a JSON boolean value. Indicates whether HTTP2 protocol should be used to upgrade the connection
 * <br />
 * <code>connector.http2.xxx</code>: property xxx will be set on the Tomcat Http2Protocol object. 
 * See the documentation or code of the Http2Protocol implementation for possible property values
 * <br />
 * <code>addunsecured</code>: a JSON boolean value. Indicates whether to add an unsecured port. This is only meaningful if usessl is true
 * <br />
 * <code>unsecuredPort</code>: a JSON integer value. The IP port to listen on for unsecured connections
 * <br />
 * <code>unsecuredConnectorProtocol</code>: a JSON string value. The FQDN of the Tomcat Connector to use. See Tomcat documentation for more information
 * <br />
 * <code>unsecuredConnectorProtocol.xxx</code>: property xxx will be passed to the <code>public void setProperty(String,String)</code> 
 * method of the Tomcat Connector object.
 *  See the documentation or code of the Connector implementation for possible property values
 * <br />
 * <code>unsecuredConnectorProtocol</code>:property xxx will be passed to the <code>public void setProperty(String,String)</code> 
 * method of the Tomcat Connector object.
 *  See the documentation or code of the Connector implementation for possible property values
 * <br />
 * <code>unsecuredConnector.xxx</code>: property xxx will be set on the Tomcat Connector object. 
 * See the documentation or code of the Connector implementation for possible property values
 *<br /><br />
 * Each web server folder in the <code>webservers/</code> folder should contain a folder named <code>webapps/</code>, which contains the exploded directories of
 * .war applications.
 *<br /><br />
 * Each web application can contain a webapp.config.json JSON file in its <code>META-INF/</code> folder that lists the libraries and classpath 
 * folders that should be exposed to the {@linkplain AppBaseWebAppLoader} custom loader. The custom loader is required to prevent
 * the Tomcat container from scanning and auto configuring web modules not required by a web application.
 * <br />
 * Alternatively a JSON file named main-webapp.config.json can be stored at the root of the classpath
 * <br />
 * The file contains the following JSON properties:
 * <br /><br />
 * <code>webapp.libraries</code>: a JSON object with one property named 'set' that points to a JSON array of string values of the names of the libraries to scan. 
 * ex. <code>{
		"set": ["tomcat-embed-el-8.5.9.jar","tomcat-embed-websocket-8.5.9.jar"]
	}</code>
 * <br />
 * <code>webapp.directories</code>: a JSON object with one property named 'set' that points to a JSON array of string values of the names of the directories to scan. 
 * ex. <code>{
		"set": ["etc","classes"]
	}</code>
 * @author kwakutwumasi-afriyie
 */
@Vetoed
public class TomcatEmbeddedServerSpiImpl implements EmbeddedWebServerSpi {
	
	private List<Tomcat> tomcatInstances = new ArrayList<>();
	private AppBasePropertiesLoader propertiesLoader = Main.getAppBasePropertiesLoader();
	private Set<WebAppListener> webAppListeners = new HashSet<>();
	private WebAppListener firstListener, lastListener;
	private String rootLocation = "webservers";
	
	public TomcatEmbeddedServerSpiImpl() {
	}
	
	public TomcatEmbeddedServerSpiImpl(String rootLocation) {
		this.rootLocation = rootLocation;
	}

	public WebAppListener getFirstWebAppListener() {
		return firstListener;
	}
	
	public WebAppListener getLastWebAppListener() {
		return lastListener;
	}
	
	public Set<WebAppListener> getWebAppListeners() {
		return webAppListeners;
	}
	
	@Override
	public void addDefaultListener(WebAppListener listener) throws ConfigurationException {
		if(listener == null)
			throw new ConfigurationException("Listener is required");
		
		checkType(listener.getListenerClass());
		
		switch (listener.getPriority()) {
		case ANY:
			webAppListeners.add(listener);
			break;
		case FIRST:
			if(firstListener != null)
				throw new ConfigurationException("A Listener with first priority has already been registered");
			else
				firstListener = listener;
			break;
		case LAST:
			if(lastListener != null)
				throw new ConfigurationException("A Listener with last priority has already been registered");
			else
				lastListener = listener;
			break;
		}
	}
	
	private void checkType(Class<?> listener) throws ConfigurationException {
		if(!ServletContextListener.class.isAssignableFrom(listener)
				&& !ServletRequestListener.class.isAssignableFrom(listener)
				&& !HttpSessionListener.class.isAssignableFrom(listener))
			throw new ConfigurationException("Listener is not a registrable type. The listener must implement one of "
					+ServletContextListener.class.getName()+", "
					+ServletRequestListener.class.getName()+", or "
					+HttpSessionListener.class.getName());
	}

	@Override
	public void initiateEmbeddedWebServer() {
        File webserversDirLocation = new File(rootLocation);
        
        if(!webserversDirLocation.exists()){
        	if(!createWebserverFromClasspath(webserversDirLocation))
        		return;
        		
        } else if(!webserversDirLocation.isDirectory()){
        	throw new ConfigurationException(webserversDirLocation.getAbsolutePath()+ " is not a directory");
        }
        
    	for(File webserverLocation : webserversDirLocation.listFiles()){
    		if(webserverLocation.isDirectory()){
    			launchInstance(webserverLocation);
    		}
    	}
	}
	
	private boolean createWebserverFromClasspath(File webserversDirLocation) {
		ClassLoader loader = Thread.currentThread()
				.getContextClassLoader();
		InputStream webappconfigin = loader
				.getResourceAsStream("main-webapp.config.json"),
				webxmlin = loader
					.getResourceAsStream("main-web.xml"),
				serverconfigin = loader
					.getResourceAsStream("main-server.config.json");
		if(webappconfigin!=null || webxmlin!=null) {
			createWebserver(webserversDirLocation, webappconfigin, webxmlin, serverconfigin);
			return true;
		}
		
		return false;
	}

	private void createWebserver(File webserversDirLocation, InputStream webappconfigin,
			InputStream webxmlin, InputStream serverconfigin) {
		webserversDirLocation.mkdir();
		File webappDirLocation = new File(webserversDirLocation, "default-server"
				+File.separator
				+"webapps"
				+File.separator
				+"webapp");
		webappDirLocation.mkdirs();
		
		if(webappconfigin != null) {
			writeToFile(webappconfigin, webappDirLocation, "META-INF", "webapp.config.json");
		}
		
		if(webxmlin == null) {
			webxmlin = getGenericWebXML();
		}
		
		writeToFile(webxmlin, webappDirLocation, "WEB-INF", "web.xml");
		
		if(serverconfigin != null) {
			File webappServerDirLocation = new File(webserversDirLocation, "default-server");
			writeToFile(serverconfigin, webappServerDirLocation,"conf", "server.config.json");
		}
	}
	
	private InputStream getGenericWebXML() {
		return getClass().getResourceAsStream("generic-web.xml.tpl");
	}

	private void writeToFile(InputStream configin, File webappDirLocation,
			String fileLocation, String fileName) {
		try(InputStream in = configin;){
			File folder = new File(webappDirLocation, fileLocation);
			folder.mkdir();
			
			File file = new File(folder, fileName);
			try(FileOutputStream stream = new FileOutputStream(file);){
				int read;
				while ((read = in.read())!=-1) {
					stream.write(read);
				}
			}
		} catch (IOException e) {
			throw new ConfigurationException(webappDirLocation.getAbsolutePath()
					+" "
					+fileLocation
					+File.separator
					+fileName
					+" cannot be created.");
		}
	}

	private void launchInstance(File webserverLocation) throws ConfigurationException {
        File webappDirLocation = new File(webserverLocation,"webapps");
        if(webappDirLocation.exists() && webappDirLocation.isDirectory()) {
	        final Tomcat tomcat = new Tomcat();
	        
	        tomcat.setBaseDir(webserverLocation.getPath());
	        tomcat.getHost().setConfigClass(AppBaseContextConfig.class.getName());        
	        File configurationFile = new File(webserverLocation, "conf"+File.separator+"server.config.json");
	        ConfigurationPropertyMap webAppConfigurationMap;
	        if(configurationFile.exists() && configurationFile.isFile()){
	        	webAppConfigurationMap = propertiesLoader.loadParametersFromFile(configurationFile);
		    } else if(configurationFile.exists() && !configurationFile.isFile()) {
		    	throw new ConfigurationException("Invalid file in /"+webserverLocation+". conf/server.config.json is not a file.");
	        } else {
	        	webAppConfigurationMap = Main.getInstance().getAppConfiguration().getSubConfigurationPropertyMap("web");
	        	if(webAppConfigurationMap == null)
	        		webAppConfigurationMap = propertiesLoader.loadParametersFromEnvironment("web");
	        }

			configureWebServer(webAppConfigurationMap, tomcat, webserverLocation);

	        for(File webappFolder : webappDirLocation.listFiles()) {
	        	configureWepapp(webappFolder, tomcat);
	        }
	        
	        try {
				tomcat.start();
			} catch (LifecycleException e) {
				throw new ConfigurationException("Unable to start embedded server", e);
			}
	        
	        new Thread(()->{
	            tomcat.getServer().await();
	            Main.log.info("Tomcat instance is shutting down");
	        }).start();
	        tomcatInstances.add(tomcat);
		} else if(webappDirLocation.exists() && webappDirLocation.isFile()) {
        		throw new ConfigurationException("Invalid file in /"+webserverLocation+". webapps is not a directory.");
		}
	}
	
	private void configureWebServer(ConfigurationPropertyMap serverConfiguration, Tomcat tomcat, File webserverLocation) {
		if(serverConfiguration.containsKey("useapr") 
				&& serverConfiguration.getBoolean("useapr")) {
			tomcat.getServer().addLifecycleListener(new AprLifecycleListener());
		}
		
		if(serverConfiguration.containsKey("hostname"))
			tomcat.setHostname(serverConfiguration.getString("hostname"));
	
		if (serverConfiguration.containsKey("connectorProtocol")){
			Connector connector = new Connector(serverConfiguration.getString("connectorProtocol"));
	        
			if (serverConfiguration.containsKey("port")) {
	        	connector.setPort(serverConfiguration.getInt("port"));
	        }
	        
			tomcat.setConnector(connector);
		} else {
		    //The port that we should run on can be set into an environment variable
		    //Look for that variable and default to 8080 if it isn't there.
		    if (serverConfiguration.containsKey("port")) {
				tomcat.getConnector().setPort(serverConfiguration.getInt("port"));
			}
		}
		
		try {
			serverConfiguration.populateBean(tomcat.getConnector(), "connector");				
		} catch (ConfigurationException e) {
			throw new ConfigurationException("Invalid connector property in configuration for "+webserverLocation.getPath()+":"+e.getMessage(), e);
		} catch (IntrospectionException e) {
			throw new ConfigurationException("Unable to instrospect "+Connector.class+": "+e.getMessage(), e);
		}
		
		getProperties("connectorProtocol.", serverConfiguration).keySet().stream().forEach((key)->{
			String propertyName = key.substring(key.indexOf(".")+1);
			String value = serverConfiguration.getString(key);
			tomcat.getConnector().setProperty(propertyName, value);
		});
	
		if (serverConfiguration.containsKey("usessl") 
				&& serverConfiguration.getBoolean("usessl")) {
			SSLHostConfig sslHostConfig = new SSLHostConfig();
			
			String protocols = serverConfiguration.getString("protocols");
			if(protocols!=null) {
				sslHostConfig.setProtocols(protocols);
			}
			
			try {
				serverConfiguration.populateBean(sslHostConfig, "sslHostConfig");
			} catch (ConfigurationException e) {
				throw new ConfigurationException("Invalid ssl property in configuration for "+webserverLocation.getPath()+":"+e.getMessage());
			} catch (IntrospectionException e) {
				throw new ConfigurationException("Unable to instrospect "+SSLHostConfig.class+": "+e.getMessage());
			}
			
			tomcat.getConnector().addSslHostConfig(sslHostConfig);
			tomcat.getConnector().setScheme("https");
			tomcat.getConnector().setProperty("SSLEnabled", "true");
			tomcat.getConnector().setSecure(true);
			
			if(serverConfiguration.containsKey("usehttp2")
					&& serverConfiguration.getBoolean("usehttp2")) {
				//Only do this for the main connector. No need to use it for the second connector (i.e. when the
				//main connector is TLS and the secondary connector is unsecured, to allow for redirection
				Http2Protocol http2Protocol = new Http2Protocol();
				try {
					serverConfiguration.populateBean(http2Protocol, "connector.http2");
				} catch (ConfigurationException e) {
					throw new ConfigurationException("Invalid ssl property in configuration for "+webserverLocation.getPath()+":"+e.getMessage());
				} catch (IntrospectionException e) {
					throw new ConfigurationException("Unable to instrospect "+SSLHostConfig.class+": "+e.getMessage());
				}
				
				tomcat.getConnector().addUpgradeProtocol(http2Protocol);
			}
			
			if(serverConfiguration.containsKey("addunsecured") 
					&& serverConfiguration.getBoolean("addunsecured")) {
				Connector connector;
				if (serverConfiguration.containsKey("unsecuredConnectorProtocol")){
					connector = new Connector(serverConfiguration.getString("unsecuredConnectorProtocol"));
				} else {
					connector = new Connector();
				}
				
				if (serverConfiguration.containsKey("unsecuredPort")) {
					connector.setPort(serverConfiguration.getInt("unsecuredPort"));
				} else {
					throw new ConfigurationException("Missing property inconfiguration for "+webserverLocation.getPath()+": unsecuredPort is required");
				}
				
				try {
					serverConfiguration.populateBean(connector, "unsecuredConnector");				
				} catch (ConfigurationException e) {
					throw new ConfigurationException("Invalid unsecured connector property in /"+webserverLocation.getName()+":"+e.getMessage(), e);
				} catch (IntrospectionException e) {
					throw new ConfigurationException("Unable to instrospect "+Connector.class+": "+e.getMessage(), e);
				}
				
				getProperties("unsecuredConnectorProtocol.", serverConfiguration).keySet().stream().forEach((key)->{
					String propertyName = key.substring(key.indexOf(".")+1);
					String value = serverConfiguration.getString(key);
					connector.setProperty(propertyName, value);
				});
				
				connector.setRedirectPort(tomcat.getConnector().getPort());
				tomcat.getService().addConnector(connector);
			}
		}
	}

	private void configureWepapp(File webappFolder, Tomcat tomcat) {
		if(!webappFolder.isDirectory())
			throw new ConfigurationException("Invalid web application. Must be a folder. If it is a zipped war file, explode it in the webapps directory");
	
	    StandardContext ctx;
		try {
			String contextName = webappFolder.getName().equals("webapp")?"":"/"+webappFolder.getName();
			if(contextName.endsWith(".war"))
				contextName = contextName.substring(0, contextName.indexOf(".war"));
			
			ctx = (StandardContext) tomcat.addWebapp(contextName, webappFolder.getAbsolutePath());
		} catch (ServletException e) {
			throw new ConfigurationException("ServletException adding webapp "+webappFolder.getAbsolutePath(), e);
		}	        		
		
		//Prevent the scanning of the main app classpath to improve performance and prevent errors
		((StandardJarScanner)ctx.getJarScanner()).setScanClassPath(false);
	    
		Main.log.debug("Configured web application with base directory: " +webappFolder.getAbsolutePath());
	
	    File webInfClasses = new File(webappFolder,"WEB-INF/classes");
	    
	    if(!webInfClasses.exists()){ //Create one to avoid problems
	    		webInfClasses.mkdirs();
		    Main.log.debug("Created WEB-INF/classes");
	    } else if(!webInfClasses.isDirectory()){ //Someone is playing games....
	    		throw new ConfigurationException(webInfClasses.getAbsolutePath()+ " is not a directory");
	    } else {
	    		for(String file:webInfClasses.list()) {//Check to see if there are class files and warn of no CDI injection
	    			if(file.endsWith(".class"))
					    Main.log.warn("Class file resource "+file+" is not visible to the CDI container. Injection of beans will not be available.");
	    		}
	    }
	    
	    File webInfLibs = new File(webappFolder,"WEB-INF/lib");
	    if(webInfLibs.exists()){
	    		for(String file:webInfLibs.list()) {//Check to see if there are jar files and warn of no CDI injection
	    		if(file.endsWith(".jar"))
	    			Main.log.warn("Jar file resource "+file+" is not visible to the CDI container. Injection of beans will not be available.");
	    		}
	    }
	   
		File webappConfigurationFile = new File(webappFolder, "META-INF" + File.separator + "webapp.config.json");
		if (webappConfigurationFile.exists() && webappConfigurationFile.isFile()) {
			final ConfigurationPropertyMap webappConfiguration = propertiesLoader
					.loadParametersFromFile(webappConfigurationFile);
			WebResourceRoot resources = new StandardRoot(ctx);
			ctx.setResources(resources);
			if (webappConfiguration.containsKey("webapp.libraries")) {
				@SuppressWarnings("unchecked")
				Set<String> libraries = webappConfiguration.get("webapp.libraries", Set.class);
				if(libraries.size()>0) {
					String repoBase = webappConfiguration.getString("webapp.libraries.base");
					if(repoBase == null)
						repoBase = "repo";
					
					AppBaseVirtualDirectoryResourceSet resourceSet = new AppBaseVirtualDirectoryResourceSet(resources, 
							"/WEB-INF/lib", repoBase,"/");
					for (String library : libraries) {
						resourceSet.addUrl(library, ClassPathSupport.getClasspathResources().getLibraryPath(library));
					}
					resources.addJarResources(resourceSet);
				}
			}
			
			if(webappConfiguration.containsKey("webapp.directories")) {
				@SuppressWarnings("unchecked")
				Set<String> directories = webappConfiguration.get("webapp.directories", Set.class);
				if(directories.size()>0) {
					for(String directory:directories) {
						File file = new File(directory);
						if(file.exists() && file.isDirectory()) {
							if(!file.isAbsolute())
								file = file.getAbsoluteFile();
							
							resources.addPostResources(new DirResourceSet(resources, "/WEB-INF/classes", file.getAbsolutePath(), "/"));
						} else {
							throw new ConfigurationException("Unable to locate directory "+directory);
						}
					}
				}
			}
			
	        if(webappConfiguration.containsKey("webapp.scifilter")) {
	        	ctx.setContainerSciFilter(webappConfiguration.getString("webapp.scifilter"));
	        }			        
	    }
		
		if(firstListener != null) {
			ctx.addApplicationListener(firstListener.getListenerClass().getName());
		} 
		
		if(!webAppListeners.isEmpty()) {
			for(WebAppListener webAppListener:webAppListeners)
				ctx.addApplicationListener(webAppListener.getListenerClass().getName());
		}
		
		if(lastListener != null) {
			ctx.addApplicationListener(lastListener.getListenerClass().getName());				
		}
		
		ctx.addContainerListener((event)->{
			synchronized (this) {
				if(event.getType().equals("addApplicationListener") 
						&& lastListener != null //It is supposed to be always last
						&& !event.getData().equals(lastListener.getListenerClass().getName())) { //this action itself will raise an event...ignore that event
					ctx.removeApplicationListener(lastListener.getListenerClass().getName());	//remove from current position			
					ctx.addApplicationEventListener(lastListener.getListenerClass().getName()); //add to the end
				}
			}
		});
				        
	    AppBaseWebAppLoader webappLoader = new AppBaseWebAppLoader(ctx);
	    ctx.setLoader(webappLoader);
		ctx.setInstanceManager(new AppBaseCDIInstanceManager(
				ContextDependencySpiFactory.getInstance()
				.getContextDependencySpi().getBeanManager()));
	}

	private Map<String, Serializable> getProperties(String prefix, Map<String, Serializable> properties){
		Map<String, Serializable> filteredMap = new ConcurrentHashMap<>();
		
		properties.keySet().stream().parallel().filter((key)->{ return key.startsWith(prefix);}).forEach((key)->{
			filteredMap.put(key, properties.get(key));
		});
		
		return filteredMap;
	}
	
	public List<Tomcat> getTomcatInstances() {
		return tomcatInstances;
	}
	
	@Override
	public void shutdownEmbeddedWebServer() {
		if(tomcatInstances.isEmpty())
			return;
		
		tomcatInstances.stream().forEach((tomcat)-> {
			try {
				tomcat.stop();
			} catch (LifecycleException e) {
				Main.log.error("Error stopping tomcat instance: "+e.getMessage(), e);
			}
		});
	}
	
}
