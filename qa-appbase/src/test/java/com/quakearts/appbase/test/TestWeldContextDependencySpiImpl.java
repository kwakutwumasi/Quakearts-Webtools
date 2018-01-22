package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;
import com.quakearts.appbase.test.experiments.TestAppBase;
import com.quakearts.appbase.test.experiments.TestInjectImpl;

public class TestWeldContextDependencySpiImpl {

	@Test
	public void testDependencyInjection() throws Exception {
		JavaNamingDirectorySpiFactory.getInstance()
			.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
			.initiateJNDIServices();
	
		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance().getContextDependencySpi();
		dependencySpi.initiateContextDependency();
		assertThat(dependencySpi.getBeanManager() != null, is(true));
		
		TestAppBase appBase = dependencySpi.getMainSingleton(TestAppBase.class);
		
		appBase.init();
		
		assertThat(TestInjectImpl.saidHello(), is(true));
		
		assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));
		
		assertThat(TestInjectImpl.transactionWorked(), is(true));

		dependencySpi.shutDownContextDependency();
		dependencySpi.shutDownContextDependency();
		JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.shutdownJavaTransactionManager();
		
		JavaNamingDirectorySpiFactory.getInstance()
			.getJavaNamingDirectorySpi()
			.shutdownJNDIService();
		new TestAppBaseMainStartup().clearInstanceVariables();
	}

}
