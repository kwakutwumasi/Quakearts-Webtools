package com.quakearts.webtools.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;


/**Base class for tests that require the full qa-appbase services
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class AppBaseFullBaseTest extends AppBaseTest{

	@BeforeClass
	public static void startFullServer() {
		createServices();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().initiateJNDIServices();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().initiateJavaTransactionManager();
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().initiateContextDependency();
		DataSourceProviderSpiFactory.getInstance()
			.getDataSourceProviderSpi().initiateDataSourceSpi();
		EmbeddedWebServerSpiFactory.getInstance()
			.getEmbeddedWebServerSpi().initiateEmbeddedWebServer();
	}

	@AfterClass
	public static void shutDownFullServer() {
		EmbeddedWebServerSpiFactory.getInstance()
			.getEmbeddedWebServerSpi().shutdownEmbeddedWebServer();
		DataSourceProviderSpiFactory.getInstance()
			.getDataSourceProviderSpi().shutDownDataSourceProvider();
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().shutDownContextDependency();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().shutdownJavaTransactionManager();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().shutdownJNDIService();
	}
}
