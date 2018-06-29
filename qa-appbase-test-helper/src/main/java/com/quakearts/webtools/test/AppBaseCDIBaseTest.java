package com.quakearts.webtools.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public abstract class AppBaseCDIBaseTest extends AppBaseTest {
	@BeforeClass
	public static void startCDI() {
		createServices();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().initiateJNDIServices();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().initiateJavaTransactionManager();
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().initiateContextDependency();
	}

	@AfterClass
	public static void shutDownCDI() {
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().shutDownContextDependency();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().shutdownJavaTransactionManager();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().shutdownJNDIService();
	}
}
