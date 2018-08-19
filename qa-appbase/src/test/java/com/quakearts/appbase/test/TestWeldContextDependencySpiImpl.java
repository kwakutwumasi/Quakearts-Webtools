package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.enterprise.inject.spi.CDI;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import com.quakearts.appbase.spi.ContextDependencySpi;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.spi.impl.AtomikosJavaTransactionManagerSpiImpl;
import com.quakearts.appbase.spi.impl.JavaNamingDirectorySpiImpl;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;
import com.quakearts.appbase.spi.impl.WeldContextDependencySpiImpl;
import com.quakearts.appbase.test.experiments.TestAppBase;
import com.quakearts.appbase.test.experiments.TestInjectImpl;
import com.quakearts.appbase.test.experiments.TestSubInjectDecorator;
import com.quakearts.appbase.test.experiments.TestSubInjectImpl;
import com.quakearts.appbase.test.experiments.TransactionTestHarness;

public class TestWeldContextDependencySpiImpl {

	@Test
	public void testDependencyInjection() throws Exception {
		TestSubInjectImpl.reset();
		TestInjectImpl.reset();
		TestSubInjectDecorator.reset();
		JavaNamingDirectorySpiFactory.getInstance()
			.createJavaNamingDirectorySpi(JavaNamingDirectorySpiImpl.class.getName())
			.initiateJNDIServices();
	
		JavaTransactionManagerSpiFactory.getInstance()
			.createJavaTransactionManagerSpi(AtomikosJavaTransactionManagerSpiImpl.class.getName())
			.initiateJavaTransactionManager();
		
		EmbeddedWebServerSpiFactory.getInstance().createEmbeddedWebServerSpi(TomcatEmbeddedServerSpiImpl.class.getName());
		ContextDependencySpiFactory.getInstance().createContextDependencySpi(WeldContextDependencySpiImpl.class.getName());
		try {
			ContextDependencySpi dependencySpi = ContextDependencySpiFactory.getInstance().getContextDependencySpi();
			dependencySpi.initiateContextDependency();
			assertThat(dependencySpi.getBeanManager() != null, is(true));
			
			TestAppBase appBase = dependencySpi.getMainSingleton(TestAppBase.class);
			
			appBase.init();
			
			assertThat(TestInjectImpl.saidHello(), is(true));
			assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));		
			assertThat(TestInjectImpl.transactionWorked(), is(true));
			assertThat(TestSubInjectImpl.hasDoneSomething(), is(true));
			assertThat(TestSubInjectDecorator.decoratedSubInject(), is(true));
			
			TransactionTestHarness testHarness = CDI.current().select(TransactionTestHarness.class).get();
			
			ExecutorService executor = Executors.newFixedThreadPool(4);
			
			Future<Boolean> runTransactionTestExpected = executor.submit(()->{
				testHarness.runTransactionTest();
				return true;
			});
			Future<Boolean> runErrorStateActiveExpected = executor.submit(()->{
				JavaTransactionManagerSpiFactory.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction().begin();
				try {
					testHarness.runErrorStateActive();					
				} catch (Exception e) {
					return true;
				} finally {
					JavaTransactionManagerSpiFactory.getInstance()
					.getJavaTransactionManagerSpi()
					.getUserTransaction().commit();					
				}
				return false;
			});
			Future<Boolean> runErrorStateNotActiveEndExpected = executor.submit(()->{
				try {
					testHarness.runErrorStateNotActiveEnd();					
				} catch (Exception e) {
					return true;
				}
				return false;
			});
			Future<Boolean> runErrorStateNotActiveJoinExpected = executor.submit(()->{
				try {
					testHarness.runErrorStateNotActiveJoin();					
				} catch (Exception e) {
					return true;
				}
				return false;
			});
			
			assertThat(runTransactionTestExpected.get(), is(true));
			assertThat(runErrorStateActiveExpected.get(), is(true));
			assertThat(runErrorStateNotActiveEndExpected.get(), is(true));
			assertThat(runErrorStateNotActiveJoinExpected.get(), is(true));

			dependencySpi.shutDownContextDependency();
			dependencySpi.shutDownContextDependency();
		} finally {
			JavaTransactionManagerSpiFactory.getInstance()
				.getJavaTransactionManagerSpi()
				.shutdownJavaTransactionManager();
			
			JavaNamingDirectorySpiFactory.getInstance()
				.getJavaNamingDirectorySpi()
				.shutdownJNDIService();
			TestAppBaseMainStartup.clearInstanceVariables();
		}
	}

}
