package com.quakearts.appbase.test;

import java.util.Map;
import org.junit.Test;
import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.test.helpers.MainTestHelper;
import com.quakearts.appbase.test.helpers.TestBean;
import com.quakearts.appbase.test.helpers.TestPrivateConstructor;

public class TestFactoryClasses {

	@Test(expected = ConfigurationException.class)
	public void testJavaNamingDirectorySpiFactoryClassNotFound() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi("notExistant");
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaNamingDirectorySpiFactoryInstantiationException() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(Map.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaNamingDirectorySpiFactoryIllegalAccessException() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance()
				.createJavaNamingDirectorySpi(TestPrivateConstructor.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaNamingDirectorySpiFactoryClassCastException() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(TestBean.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaNamingDirectorySpiFactoryCreateTwice() throws Exception {
		try {
			JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(MainTestHelper.class.getName());
			JavaNamingDirectorySpiFactory.getInstance().createJavaNamingDirectorySpi(MainTestHelper.class.getName());
		} finally {
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaTransactionManagerSpiFactoryClassNotFound() throws Exception {
		JavaTransactionManagerSpiFactory.getInstance().createJavaTransactionManagerSpi("notExistant");
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaTransactionManagerSpiFactoryInstantiationException() throws Exception {
		JavaTransactionManagerSpiFactory.getInstance().createJavaTransactionManagerSpi(Map.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaTransactionManagerSpiFactoryIllegalAccessException() throws Exception {
		JavaTransactionManagerSpiFactory.getInstance()
				.createJavaTransactionManagerSpi(TestPrivateConstructor.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaTransactionManagerSpiFactoryClassCastException() throws Exception {
		JavaTransactionManagerSpiFactory.getInstance().createJavaTransactionManagerSpi(TestBean.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testJavaTransactionManagerSpiFactoryCreateTwice() throws Exception {
		try {
			JavaTransactionManagerSpiFactory.getInstance()
					.createJavaTransactionManagerSpi(MainTestHelper.class.getName());
			JavaTransactionManagerSpiFactory.getInstance()
					.createJavaTransactionManagerSpi(MainTestHelper.class.getName());
			new TestAppBaseMainStartup().clearInstanceVariables();
		} finally {
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}

	@Test(expected = ConfigurationException.class)
	public void testEmbeddedWebServerSpiFactoryClassNotFound() throws Exception {
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi("notExistant");
	}

	@Test(expected = ConfigurationException.class)
	public void testEmbeddedWebServerSpiFactoryInstantiationException() throws Exception {
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(Map.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testEmbeddedWebServerSpiFactoryIllegalAccessException() throws Exception {
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TestPrivateConstructor.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testEmbeddedWebServerSpiFactoryClassCastException() throws Exception {
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TestBean.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testEmbeddedWebServerSpiFactoryCreateTwice() throws Exception {
		try {
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(MainTestHelper.class.getName());
			EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(MainTestHelper.class.getName());
			new TestAppBaseMainStartup().clearInstanceVariables();
		} finally {
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}

	@Test(expected = ConfigurationException.class)
	public void testDataSourceSpiFactoryClassNotFound() throws Exception {
		DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi("notExistant");
	}

	@Test(expected = ConfigurationException.class)
	public void testDataSourceSpiFactoryInstantiationException() throws Exception {
		DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(Map.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testDataSourceSpiFactoryIllegalAccessException() throws Exception {
		DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(TestPrivateConstructor.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testDataSourceSpiFactoryClassCastException() throws Exception {
		DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(TestBean.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testDataSourceProviderSpiFactoryCreateTwice() throws Exception {
		try {
			DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(MainTestHelper.class.getName());
			DataSourceProviderSpiFactory.getInstance().createDataSourceProviderSpi(MainTestHelper.class.getName());
			new TestAppBaseMainStartup().clearInstanceVariables();
		} finally {
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}

	@Test(expected = ConfigurationException.class)
	public void testContextDependencySpiFactoryClassNotFound() throws Exception {
		ContextDependencySpiFactory.getInstance().createContextDependencySpi("notExistant");
	}

	@Test(expected = ConfigurationException.class)
	public void testContextDependencySpiFactoryInstantiationException() throws Exception {
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(Map.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testContextDependencySpiFactoryIllegalAccessException() throws Exception {
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(TestPrivateConstructor.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testContextDependencySpiFactoryClassCastException() throws Exception {
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(TestBean.class.getName());
	}

	@Test(expected = ConfigurationException.class)
	public void testContextDependencySpiFactoryCreateTwice() throws Exception {
		try {
			ContextDependencySpiFactory.getInstance().createContextDependencySpi(MainTestHelper.class.getName());
			ContextDependencySpiFactory.getInstance().createContextDependencySpi(MainTestHelper.class.getName());
		} finally {
			new TestAppBaseMainStartup().clearInstanceVariables();
		}
	}
}
