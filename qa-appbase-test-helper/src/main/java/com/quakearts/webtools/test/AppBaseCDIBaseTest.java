package com.quakearts.webtools.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;

/**Base class for tests that require CDI services in qa-appbase
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class AppBaseCDIBaseTest extends AppBaseTest {
	@BeforeClass
	public static void startCDI() {
		createServices();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().initiateJNDIServices();
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().initiateContextDependency();
	}

	@AfterClass
	public static void shutDownCDI() {
		ContextDependencySpiFactory.getInstance()
			.getContextDependencySpi().shutDownContextDependency();
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi().shutdownJNDIService();
	}
}
