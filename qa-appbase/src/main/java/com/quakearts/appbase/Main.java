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
package com.quakearts.appbase;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import javax.enterprise.inject.Vetoed;
import javax.net.ServerSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.internal.properties.impl.AppBasePropertiesLoaderImpl;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.DataSourceProviderSpi;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.JavaNamingDirectorySpi;
import com.quakearts.appbase.spi.JavaTransactionManagerSpi;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

/**This is the applications startup class. It reads in the name of the main class to be called after initializing all the 
 * JEE modules (JNDI, JTA, CDI, JCA, Servlet Container, in that order) and executes its <code> public void init() { }</code> method.
 * The main class is not required to be annotated with {@linkplain javax.inject.Singleton}, however this helps ensure that
 * any bean that contains a dependency on the Main class will get the instance started by this class.
 * This class prints out usage documentation if it's not called with any parameters, or if the parameters are invalid
 * below is the printout: <br /> <br />
 * <code> Usage: appbase mainclass [filename] [-dontwaitinmain] </code><br />
 * <br />
 * <code>mainclass: the CDI managed bean that serves as the application starting point</code> <br />
 * <code>filename (optional): the file name of the properties file of the app configuration</code> <br />
 * <code>-dontwaitinmain (optional): flag to indicate that the main application thread should shutdown upon completion. 
 * This setting enables an external shutdown hook which can be called to shutdown the service</code> <br />
 * <code> </code> <br /><br />
 * 
 * webapp signifies the name of the application as called from the command line of the operating system.
 * It may be different, and may even be the full call to the java command line executable
 * 
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class Main {

	private static final String APP_CONFIG_JSON = "app.config.json";
	private static final String USAGE = "Usage: webapp mainclass [filename] [-dontwaitinmain]\n"
			+ "\n"
			+ "mainclass: the CDI managed bean that serves as the application starting point\n"
			+ "filename (optional): the file name of the properties file of the app configuration\n"
			+ "-dontwaitinmain (optional): flag to indicate that the main application thread should shutdown upon completion\n"
			+ "\t\t\tThis setting enables an external shutdown hook which can be called to shutdown the service.";
	private static final String DONT_WAIT_IN_MAIN = "-dontwaitinmain";
	public static final Logger log = LoggerFactory.getLogger(Main.class);
	private static Main instance;
	private static AppBasePropertiesLoaderImpl defualtAppBasePropertiesLoader;
	private ContextDependencySpi contextDependencySpi;
	private EmbeddedWebServerSpi embeddedWebServerSpi;
	private JavaTransactionManagerSpi javaTransactionManagerSpi;
	private JavaNamingDirectorySpi javaNamingDirectorySpi;
	private DataSourceProviderSpi dataSourceProviderSpi;
	
	private ConfigurationPropertyMap appConfiguration = new ConfigurationPropertyMap();
	
	public static Main getInstance() {
		if(instance == null)
			instance = new Main();
		
		return instance;
	}
	
	public static AppBasePropertiesLoader getAppBasePropertiesLoader() {
		if(defualtAppBasePropertiesLoader == null) {
			defualtAppBasePropertiesLoader = new AppBasePropertiesLoaderImpl();
		}
		
		return defualtAppBasePropertiesLoader;
	}
		
	public ConfigurationPropertyMap getAppConfiguration() {
		return appConfiguration;
	}
	
	private Main() {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(APP_CONFIG_JSON);
		if(is != null)
			try {
				appConfiguration = getAppBasePropertiesLoader().loadParametersFromReader(APP_CONFIG_JSON, new InputStreamReader(is));
			} catch (IOException e) {
				throw new ConfigurationException("Unable to load app.config.json", e);
			}
	}
	
	void startUp(String mainClassName, Properties props){
		javaNamingDirectorySpi = JavaNamingDirectorySpiFactory
				.getInstance()
				.createJavaNamingDirectorySpi(props.getProperty("jndi.spi.class"));
		javaTransactionManagerSpi = JavaTransactionManagerSpiFactory
				.getInstance()
				.createJavaTransactionManagerSpi(props.getProperty("java.tm.spi.class"));
		contextDependencySpi = ContextDependencySpiFactory
				.getInstance()
				.createContextDependencySpi(props.getProperty("cdi.spi.class"));
		dataSourceProviderSpi = DataSourceProviderSpiFactory.getInstance()
				.createDataSourceProviderSpi(props.getProperty("datasource.spi.class"));
		embeddedWebServerSpi = EmbeddedWebServerSpiFactory
				.getInstance().createEmbeddedWebServerSpi(props.getProperty("embedded.ws.spi.class"));

		javaNamingDirectorySpi.initiateJNDIServices();
		Main.log.info("JNDI Naming provider service started");
		javaTransactionManagerSpi.initiateJavaTransactionManager();
		Main.log.info("Java Transaction Manager service started");
		contextDependencySpi.initiateContextDependency();
		Main.log.info("Context Dependency service started");
		dataSourceProviderSpi.initiateDataSourceSpi();
		Main.log.info("DataSource provider service started");
		embeddedWebServerSpi.initiateEmbeddedWebServer();
		Main.log.info("Embedded Web Server service started");

		new Thread(()->{
			try {
				Class<?> mainClass = Class.forName(mainClassName);
				Object mainInstance = contextDependencySpi.getMainSingleton(mainClass);
				Method initMethod = mainClass.getMethod("init");
				initMethod.invoke(mainInstance);
			} catch (ClassNotFoundException | NoSuchMethodException 
					| SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				throw new ConfigurationException("Cannot load mainclass "+mainClassName, e);
			}
		}).start();
	}
	
	public void shutDown(){
		embeddedWebServerSpi.shutdownEmbeddedWebServer();
		dataSourceProviderSpi.shutDownDataSourceProvider();
		contextDependencySpi.shutDownContextDependency();
		javaTransactionManagerSpi.shutdownJavaTransactionManager();
		javaNamingDirectorySpi.shutdownJNDIService();
	}

	public static void main(String[] args) {
		if(instance != null)
			return;
		
		Properties props = new Properties();
		String propertiesFilename, mainClassName;
		mainClassName = null;
		propertiesFilename = "default.configuration";
		if(args.length>3 || args.length<=0) {
			log.error(USAGE);
			return;
		} 
		
		mainClassName = args[0];
		
		boolean waitInMain = true;
		
		if(args.length == 1){
			propertiesFilename = "default.configuration";
		} else if(args.length == 2) {
			if(!args[1].equals(DONT_WAIT_IN_MAIN))
				propertiesFilename = args[1];
			else
				waitInMain = false;
		} else if(args.length == 3) {
			if(!args[2].equals(DONT_WAIT_IN_MAIN) 
					&& !args[1].equals(DONT_WAIT_IN_MAIN)) {
				log.error(USAGE);
				return;
			}

			if(!args[1].equals(DONT_WAIT_IN_MAIN)){
				propertiesFilename = args[1];
			} else {
				propertiesFilename = args[2];
			} 

			waitInMain = false;
		}
		
		try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFilename)){
			if(is!=null){
				props.load(is);
			} else {
				log.error("Unable to load file "+propertiesFilename);
				return;
			}
		} catch(IOException e){
			log.error("Unable to load file "+propertiesFilename+": "+e.getMessage());
			e.printStackTrace();
			return;
		}
		
		getInstance();
		instance.startUp(mainClassName, props);
		Runtime.getRuntime().addShutdownHook(new Thread(()->{
			try {
				instance.shutDown();
			} catch (Exception e) {
			}
		}));
				
		if(waitInMain){
			int port = Integer.parseInt(props.getProperty("shutdown.port", "9999"));
			log.info("Listening for shutdown command on port "+port);

			ServerSocket shutdownSocket;
			
			try {
				shutdownSocket = ServerSocketFactory.getDefault().createServerSocket(port);
			} catch (IOException e) {
				log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles getting shutdown hook");
				return;
			}
			
			while(true){
				Socket commandSocket;
				try {
					commandSocket = shutdownSocket.accept();
					if(commandSocket.getInetAddress().isLoopbackAddress()){
						int b = commandSocket.getInputStream().read();
						if(b==0xFA) {
							Main.log.info("Shutdown called....");
							instance.shutDown();
							Main.log.info("Shutdown complete");
							break;
						}
					}
				} catch (IOException e) {
					log.error("Could not understand message from shutdown command:"+e.getMessage());
				}
			}
		}
	}
}
