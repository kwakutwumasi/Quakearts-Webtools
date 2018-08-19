package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.tomcat.websocket.pojo.PojoEndpointServer;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.apache.tomcat.websocket.server.WsSessionListener;
import org.jboss.weld.module.web.servlet.WeldInitialListener;
import org.jboss.weld.module.web.servlet.WeldTerminalListener;
import org.junit.Rule;
import org.junit.Test;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.beans.WebAppListener;
import com.quakearts.appbase.spi.beans.WebAppListener.Priority;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;
import com.quakearts.appbase.spi.impl.tomcat.AppBaseCDIInstanceManager;
import com.quakearts.appbase.test.experiments.TestInjectImpl;
import com.quakearts.appbase.test.experiments.TestSubInjectDecorator;
import com.quakearts.appbase.test.experiments.TestSubInjectImpl;
import com.quakearts.appbase.test.helpers.HideResourceClassLoader;
import com.quakearts.appbase.test.helpers.TestListener1;
import com.quakearts.appbase.test.helpers.TestServlet;
import com.quakearts.appbase.test.helpers.rules.ClassLoaderRule;
import com.quakearts.appbase.test.helpers.rules.SystemEnvironmentRule;

public class TestTomcatEmbeddedServerSpiImpl {

	@Test
	public void testAddingListeners() throws Exception {
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
		TomcatEmbeddedServerSpiImpl serverSpi =(TomcatEmbeddedServerSpiImpl) EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi();
		
		serverSpi.addDefaultListener(new WebAppListener(WeldTerminalListener.class, Priority.ANY));
		serverSpi.addDefaultListener(new WebAppListener(WsSessionListener.class, Priority.ANY));
		serverSpi.addDefaultListener(new WebAppListener(WeldInitialListener.class, Priority.FIRST));
		serverSpi.addDefaultListener(new WebAppListener(WsContextListener.class, Priority.LAST));
		
		assertThat(serverSpi.getFirstWebAppListener(), is(new WebAppListener(WeldInitialListener.class, Priority.FIRST)));
		assertThat(serverSpi.getLastWebAppListener(), is(new WebAppListener(WsContextListener.class, Priority.LAST)));
		assertThat(serverSpi.getWebAppListeners().size(), is(2));
		assertThat(serverSpi.getWebAppListeners().contains(new WebAppListener(WeldTerminalListener.class, Priority.ANY)), is(true));
		assertThat(serverSpi.getWebAppListeners().contains(new WebAppListener(WsSessionListener.class, Priority.ANY)), is(true));
		
		try {
			serverSpi.addDefaultListener(new WebAppListener(WeldInitialListener.class, Priority.FIRST));
			fail("Did not reject double registration");
		} catch (ConfigurationException e) {
		}
		
		try {
			serverSpi.addDefaultListener(new WebAppListener(WsContextListener.class, Priority.LAST));
			fail("Did not reject double registration");
		} catch (ConfigurationException e) {
		}

		try {
			serverSpi.addDefaultListener(new WebAppListener(String.class, Priority.ANY));
			fail("Did not reject invalid listener");
		} catch (ConfigurationException e) {
		}

		try {
			serverSpi.addDefaultListener(null);
			fail("Did not reject null listener");
		} catch (ConfigurationException e) {
		}
		
		TestAppBaseMainStartup.clearInstanceVariables();
	}
	
	@Test
	public void testTomcatEmbeddedServerSpiImpl() throws Exception {
		TestInjectImpl.reset();
		TestSubInjectImpl.reset();
		TestSubInjectDecorator.reset();
		
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			EmbeddedWebServerSpi serverSpi = EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi();
	
			serverSpi.addDefaultListener(new WebAppListener(TestListener1.class, Priority.ANY));
			
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
			
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test").openConnection();
				
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(200));
				assertThat(connection.getHeaderField("Server"), is("Test Server"));
				assertThat(connection.getHeaderField("X-Powered-By") != null, is(true));
				assertThat(TestServlet.transactionLoaded(), is(true));
				assertThat(TestInjectImpl.saidHello(), is(true));
				assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));		
				assertThat(TestInjectImpl.transactionWorked(), is(true));
				assertThat(TestSubInjectImpl.hasDoneSomething(), is(true));
				assertThat(TestSubInjectDecorator.decoratedSubInject(), is(true));
			} catch (IOException e) {
				fail("Unable to connect:" +e.getMessage());
			}
	
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/testRoot").openConnection();
				
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(200));
			} catch (IOException e) {
				fail("Unable to connect:" +e.getMessage());
			}
			
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8190, "/testssl/test").openConnection();
				
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(200));
			} catch (IOException e) {
				fail("Unable to connect:" +e.getMessage());
			}
	
			try {
				TrustManager[] manager = new TrustManager[] {
						new X509TrustManager() {
							
							@Override
							public X509Certificate[] getAcceptedIssuers() {
								return null;
							}
							
							@Override
							public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
							}
							
							@Override
							public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
							}
						}
				};
				
				TestInjectImpl.reset();
				SSLContext context = SSLContext.getInstance("TLS");
				context.init(null, manager, new SecureRandom());
				
				HttpsURLConnection connection = (HttpsURLConnection) new URL("https", "localhost", 8543, "/testssl/test").openConnection();
				
				connection.setSSLSocketFactory(context.getSocketFactory());
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     			
				connection.setHostnameVerifier((string, sslContext)->{
					return true;
				});
				
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(200));
				assertThat(TestInjectImpl.saidHello(), is(true));
				assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));		
				assertThat(TestInjectImpl.transactionWorked(), is(true));
				assertThat(TestSubInjectImpl.hasDoneSomething(), is(true));
				assertThat(TestSubInjectDecorator.decoratedSubInject(), is(true));
			} catch (NoSuchAlgorithmException | IOException | KeyManagementException e) {
				fail("Unable to connect:" +e.getMessage());
			}
	
			try {
				HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test-secured").openConnection();
	
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(401));			
	
				connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test-secured").openConnection();
				
				connection.addRequestProperty("Authorization","Basic "+Base64.getEncoder().encodeToString("test:test".getBytes()));
				
				connection.connect();
				
				assertThat(connection.getResponseCode(), is(200));			
			} catch (IOException e) {
				fail("Unable to connect:" +e.getMessage());
			}
			
			AppBaseCDIInstanceManager manager = new AppBaseCDIInstanceManager(
					ContextDependencySpiFactory.getInstance().getContextDependencySpi().getBeanManager());
			
			TestInjectImpl.reset();
			
			TestInjectImpl impl = new TestInjectImpl();
			manager.newInstance(impl);
			impl.sayHello();

			assertThat(TestInjectImpl.saidHello(), is(true));
			assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));		
			
			PojoEndpointServer endpointServer = new PojoEndpointServer();
			manager.newInstance(endpointServer);
			
			serverSpi.shutdownEmbeddedWebServer();
			serverSpi.shutdownEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			TestAppBaseMainStartup.clearInstanceVariables();
		}
	}
	
	private HideResourceClassLoader classLoader = new HideResourceClassLoader();
	
	@Rule
	public ClassLoaderRule classLoaderRule = new ClassLoaderRule()
		.replaceClassLoaderWith(classLoader);
	
	@Rule
	public SystemEnvironmentRule systemEnvironmentRule = new SystemEnvironmentRule(); 
	
	@Test
	public void testTomcatEmbeddedServerSpiImplFromClassPath() throws Exception {
		classLoader.setResourcePattern(null);
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		File testWebappLocation = new File("webserverfromclasspath");
		deleteFile(testWebappLocation);
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			EmbeddedWebServerSpi serverSpi = new TomcatEmbeddedServerSpiImpl("webserverfromclasspath");
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
			
			assertThat(testWebappLocation.isDirectory(), is(true));
			File testFile = new File(testWebappLocation, "default-server"
					+File.separator
					+"webapps"
					+File.separator
					+"webapp"
					+File.separator
					+"META-INF"
					+File.separator
					+"webapp.config.json");
			assertThat(testFile.isFile(), is(true));
			
			testFile = new File(testWebappLocation, "default-server"
					+File.separator
					+"webapps"
					+File.separator
					+"webapp"
					+File.separator
					+"WEB-INF"
					+File.separator
					+"web.xml");
			assertThat(testFile.isFile(), is(true));
			
			testFile = new File(testWebappLocation, "default-server"
					+File.separator
					+"conf"
					+File.separator
					+"server.config.json");
			assertThat(testFile.isFile(), is(true));
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8280, "/test/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(404));
			assertThat(connection.getHeaderField("Server"), is("Test Server"));
			assertThat(connection.getHeaderField("X-Powered-By") != null, is(true));
			
			serverSpi.shutdownEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			
			TestAppBaseMainStartup.clearInstanceVariables();
			
			deleteFile(testWebappLocation);
		}
	}
	
	@Test
	public void testTomcatEmbeddedServerSpiImplFromAppConfig() throws Exception {
		classLoader.setResourcePattern("(.*main-server\\.config\\.json)|(.*main-web\\.xml)");
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		File testWebappLocation = new File("webserverfromappconfig");
		deleteFile(testWebappLocation);
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			EmbeddedWebServerSpi serverSpi = new TomcatEmbeddedServerSpiImpl("webserverfromappconfig");
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
			
			File testFile = new File(testWebappLocation, "default-server"
					+File.separator
					+"webapps"
					+File.separator
					+"webapp"
					+File.separator
					+"WEB-INF"
					+File.separator
					+"web.xml");
			assertThat(testFile.isFile(), is(true));
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8380, "/test/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(404));
			assertThat(connection.getHeaderField("Server"), is("Test Server"));
			assertThat(connection.getHeaderField("X-Powered-By") != null, is(true));
			
			serverSpi.shutdownEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			
			TestAppBaseMainStartup.clearInstanceVariables();
			
			deleteFile(testWebappLocation);
		}
	}
	
	@Test
	public void testTomcatEmbeddedServerSpiImplFromEnvironmentVariables() throws Exception {
		classLoader.setResourcePattern("(.*main-server\\.config\\.json)|(.*app\\.config\\.json)");
		
		systemEnvironmentRule.set("web.port").as(":8480")
							.set("web.connectorProtocol").as("HTTP/1.1")
							.set("web.connector.xpoweredBy").as(":true")
							.set("web.connectorProtocol.server").as("Test Server");
		
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();
	
		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		File testWebappLocation = new File("webserverfromenvironment");
		deleteFile(testWebappLocation);
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			EmbeddedWebServerSpi serverSpi = new TomcatEmbeddedServerSpiImpl("webserverfromenvironment");
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8480, "/test/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(404));
			assertThat(connection.getHeaderField("Server"), is("Test Server"));
			assertThat(connection.getHeaderField("X-Powered-By") != null, is(true));
			
			serverSpi.shutdownEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			
			TestAppBaseMainStartup.clearInstanceVariables();
			
			deleteFile(testWebappLocation);
			systemEnvironmentRule.reset();
		}
	}

	@Test
	public void testTomcatEmbeddedServerSpiImplDefault() throws Exception {
		classLoader.setResourcePattern("(.*main-server\\.config\\.json)|(.*app\\.config\\.json)");
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		File testWebappLocation = new File("defaultwebserver");
		deleteFile(testWebappLocation);
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			EmbeddedWebServerSpi serverSpi = new TomcatEmbeddedServerSpiImpl("defaultwebserver");
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8080, "/test/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(404));			
			serverSpi.shutdownEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			
			TestAppBaseMainStartup.clearInstanceVariables();
			
			deleteFile(testWebappLocation);
		}
	}
	
	@Test
	public void testInitiateWithoutClassPathFiles() throws Exception {
		classLoader.setResourcePattern("(.*main-server\\.config\\.json)|(.*main-web\\.xml)|(.*main-webapp\\.config\\.json)");
		TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("nowebservers");
		serverSpi.initiateEmbeddedWebServer();
		
		assertThat(serverSpi.getTomcatInstances().isEmpty(), is(true));
		serverSpi.shutdownEmbeddedWebServer();
	}
	
	@Test(expected=ConfigurationException.class)
	public void testTomcatEmbeddedServerSpiImplWithInvalidWebserverFolder() throws Exception {
		TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("pom.xml");
		serverSpi.initiateEmbeddedWebServer();
	}
	
	@Test(expected=ConfigurationException.class)
	public void testTomcatEmbeddedServerSpiImplWithInvalidWebserverFolderContents() throws Exception {
		TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("invalidwebserver");
		serverSpi.initiateEmbeddedWebServer();
	}
	
	@Test(expected=ConfigurationException.class)
	public void testTomcatEmbeddedServerSpiImplWithInvalidWebappsFolderContents() throws Exception {
		TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("testinvalidwebappsdir");
		serverSpi.initiateEmbeddedWebServer();
	}

	@Test(expected=ConfigurationException.class)
	public void testTomcatEmbeddedServerSpiImplWithInvalidServerConfig() throws Exception {
		TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("testinvalidserverconfig");
		serverSpi.initiateEmbeddedWebServer();
	}

	@Test(expected=ConfigurationException.class)
	public void testTomcatEmbeddedServerSpiImplWithInvalidWebInfs() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance()
		.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
		.initiateJNDIServices();

		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance()
			.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
			TomcatEmbeddedServerSpiImpl serverSpi = new TomcatEmbeddedServerSpiImpl("testinvalidwebinfs");
			dependencySpi.initiateContextDependency();
			serverSpi.initiateEmbeddedWebServer();
		} finally {
			ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();
			
			JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
			JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
			
			TestAppBaseMainStartup.clearInstanceVariables();
			
		}
	}
	
	private void deleteFile(File file) {
		if(file.exists()) {
			if(file.isDirectory()) {
				for(File subFile:file.listFiles()) {
					deleteFile(subFile);
				}
			}
			file.delete();
		}
	}
}
