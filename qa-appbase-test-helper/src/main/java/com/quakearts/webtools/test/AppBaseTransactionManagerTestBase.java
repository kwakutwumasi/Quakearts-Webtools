package com.quakearts.webtools.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

/**Base for tests that require transaction manager services
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class AppBaseTransactionManagerTestBase extends AppBaseTest {
	@BeforeClass
	public static void startTransactionManager() {
		createServices();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().initiateJNDIServices();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().initiateJavaTransactionManager();
	}

	@AfterClass
	public static void shutDownTransactionManager() {
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi().shutdownJavaTransactionManager();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().shutdownJNDIService();
	}
}
