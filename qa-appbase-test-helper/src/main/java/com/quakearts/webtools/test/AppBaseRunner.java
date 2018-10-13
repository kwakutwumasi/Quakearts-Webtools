package com.quakearts.webtools.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosBeanDatasourceProviderSpiImpl;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;

/**Base for all tests runners
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class AppBaseRunner extends BlockJUnit4ClassRunner {
	public AppBaseRunner(Class<?> klass) throws InitializationError {
		super(klass);
		createServices();
		startServices();

		Runtime.getRuntime().addShutdownHook(new Thread(this::shutdownServices));
	}
		
	protected abstract void createServices();
	protected abstract void startServices();
	protected abstract void shutdownServices();

	protected void createJavaNamingDirectory() {
		if(JavaNamingDirectorySpiFactory.getInstance().getJavaNamingDirectorySpi()==null) {
			JavaNamingDirectorySpiFactory.getInstance()
				.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName());
		}
	}
	
	protected void createJavaTransactionManager() {
		createJavaNamingDirectory();
		if(JavaTransactionManagerSpiFactory.getInstance().getJavaTransactionManagerSpi()==null) {
			JavaTransactionManagerSpiFactory.getInstance()
				.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName());
		}
	}
	
	protected void createDataSourceProvider() {
		createJavaTransactionManager();
		if(DataSourceProviderSpiFactory.getInstance().getDataSourceProviderSpi()==null) {		
			DataSourceProviderSpiFactory.getInstance()
				.createDataSourceProviderSpi(AtomikosBeanDatasourceProviderSpiImpl.class.getName());
		}
	}
	
	protected void createContextDependency() {
		createJNCDEWS();
	}
	
	protected void createEmbeddedWebServer() {
		createJNCDEWS();
	}
	
	private void createJNCDEWS() {
		createJavaNamingDirectory();
		internalCreateContextDependency();
		internalCreateEmbeddedWebServer();
	}
	
	private void internalCreateContextDependency() {
		if(ContextDependencySpiFactory.getInstance().getContextDependencySpi()==null) {
			ContextDependencySpiFactory.getInstance()
				.createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		}		
	}
		
	private void internalCreateEmbeddedWebServer() {
		if(EmbeddedWebServerSpiFactory.getInstance().getEmbeddedWebServerSpi()==null) {
			EmbeddedWebServerSpiFactory.getInstance()
					.createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
		}
	}
	
	private static final Map<String, Boolean> executed = new ConcurrentHashMap<>();
	protected void startJavaNamingDirectory() {
		if(!executed.containsKey("initiateJNDIServices")) {
			JavaNamingDirectorySpiFactory.getInstance()
				.getJavaNamingDirectorySpi().initiateJNDIServices();
			executed.put("initiateJNDIServices", Boolean.TRUE);
		}
	}

	protected void startJavaTransactionManager() {
		startJavaNamingDirectory();
		if(!executed.containsKey("initiateJavaTransactionManager")) {
			JavaTransactionManagerSpiFactory.getInstance()
				.getJavaTransactionManagerSpi()
				.initiateJavaTransactionManager();
			executed.put("initiateJavaTransactionManager", Boolean.TRUE);
		}
	}

	protected void startDataSourceProvider() {
		startJavaTransactionManager();
		if(!executed.containsKey("initiateDataSourceSpi")) {
			DataSourceProviderSpiFactory.getInstance()
				.getDataSourceProviderSpi()
				.initiateDataSourceSpi();
			executed.put("initiateDataSourceSpi", Boolean.TRUE);
		}
	}

	protected void startContextDependency() {
		startJavaNamingDirectory();
		if(!executed.containsKey("initiateContextDependency")) {
			ContextDependencySpiFactory.getInstance()
				.getContextDependencySpi()
				.initiateContextDependency();
			executed.put("initiateContextDependency", Boolean.TRUE);
		}
	}

	protected void startEmbeddedWebServer() {
		startContextDependency();
		if(!executed.containsKey("initiateEmbeddedWebServer")) {
			EmbeddedWebServerSpiFactory.getInstance()
				.getEmbeddedWebServerSpi()
				.initiateEmbeddedWebServer();
			executed.put("initiateEmbeddedWebServer", Boolean.TRUE);
		}
	}
	
	protected void shutdownJavaNamingDirectory() {
		if(!executed.containsKey("shutdownJNDIService")) {
			JavaNamingDirectorySpiFactory.getInstance()
				.getJavaNamingDirectorySpi()
				.shutdownJNDIService();		
			executed.put("shutdownJNDIService", Boolean.TRUE);
		}
	}
	
	protected void shutdownJavaTransactionManager() {
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		shutdownJavaNamingDirectory();
	}
	
	protected void shutdownDataSourceProvider() {
		DataSourceProviderSpiFactory.getInstance()
			.getDataSourceProviderSpi()
			.shutDownDataSourceProvider();
		shutdownJavaTransactionManager();
	}
	
	protected void shutdownContextDependency() {
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi()
			.shutDownContextDependency();	
		shutdownJavaNamingDirectory();
	}
	
	protected void shutdownEmbeddedWebServer() {
		EmbeddedWebServerSpiFactory.getInstance()
			.getEmbeddedWebServerSpi()
			.shutdownEmbeddedWebServer();
		shutdownContextDependency();
	}
}
