package com.quakearts.webtools.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public abstract class AppDatasourceBeanTestBase extends AppBaseTest {
	@BeforeClass
	public static void startDatasource() {
		createServices();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().initiateJNDIServices();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().initiateJavaTransactionManager();
		DataSourceProviderSpiFactory.getInstance()
			.getDataSourceProviderSpi().initiateDataSourceSpi();
	}

	@AfterClass
	public static void shutDownDatasource() {
		DataSourceProviderSpiFactory.getInstance()
			.getDataSourceProviderSpi().shutDownDataSourceProvider();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().shutdownJavaTransactionManager();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().shutdownJNDIService();
	}
}
