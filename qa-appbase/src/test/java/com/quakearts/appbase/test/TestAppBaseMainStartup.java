package com.quakearts.appbase.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.Is.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.Shutdown;
import com.quakearts.appbase.spi.factory.ContextDependencySpiFactory;
import com.quakearts.appbase.spi.factory.DataSourceProviderSpiFactory;
import com.quakearts.appbase.spi.factory.EmbeddedWebServerSpiFactory;
import com.quakearts.appbase.spi.factory.JavaNamingDirectorySpiFactory;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;
import com.quakearts.appbase.test.helpers.MainNonDefaultTestHelper;
import com.quakearts.appbase.test.helpers.MainTestHelper;

public class TestAppBaseMainStartup {

	@Rule
	public Timeout timeout = new Timeout(30, TimeUnit.SECONDS);
	
	@Test
	public void testMainInstantiation() throws Exception {
		clearInstanceVariables();
		MainTestHelper.resetChecks();
		MainNonDefaultTestHelper.resetChecks();
		
		Main.main(new String[0]);
		Main.main(new String[] {MainNonDefaultTestHelper.class.getName(),"no.configuration"});

		assertThat(MainTestHelper.isContextDependencyInjectionStarted(), is(false));
		assertThat(MainTestHelper.isDatasourceStarted(), is(false));
		assertThat(MainTestHelper.isEmbeddedWebServerStarted(), is(false));
		assertThat(MainTestHelper.isJndiServicesStarted(), is(false));
		assertThat(MainTestHelper.isJavaTransactionManagerInitiated(), is(false));
		assertThat(MainTestHelper.isMainSingletonClassMatches(), is(false));
		assertThat(MainTestHelper.isMainSingletonIniated(), is(false));

		assertThat(MainNonDefaultTestHelper.isContextDependencyInjectionStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isDatasourceStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isEmbeddedWebServerStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isJndiServicesStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isJavaTransactionManagerInitiated(), is(false));
		assertThat(MainNonDefaultTestHelper.isMainSingletonClassMatches(), is(false));
		assertThat(MainNonDefaultTestHelper.isMainSingletonIniated(), is(false));

		clearInstanceVariables();
		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		Main.main(new String[] {MainTestHelper.class.getName(),"-dontwaitinmain"});
		Main.main(new String[] {MainTestHelper.class.getName(),"-dontwaitinmain"});
		Thread.sleep(100);
		
		assertThat(MainTestHelper.isContextDependencyInjectionStarted(), is(true));
		assertThat(MainTestHelper.isDatasourceStarted(), is(true));
		assertThat(MainTestHelper.isEmbeddedWebServerStarted(), is(true));
		assertThat(MainTestHelper.isJndiServicesStarted(), is(true));
		assertThat(MainTestHelper.isJavaTransactionManagerInitiated(), is(true));
		assertThat(MainTestHelper.isMainSingletonClassMatches(), is(true));
		assertThat(MainTestHelper.isMainSingletonIniated(), is(true));

		assertThat(MainNonDefaultTestHelper.isContextDependencyInjectionStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isDatasourceStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isEmbeddedWebServerStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isJndiServicesStarted(), is(false));
		assertThat(MainNonDefaultTestHelper.isJavaTransactionManagerInitiated(), is(false));
		assertThat(MainNonDefaultTestHelper.isMainSingletonClassMatches(), is(false));
		assertThat(MainNonDefaultTestHelper.isMainSingletonIniated(), is(false));

		clearInstanceVariables();
		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		Main.main(new String[] {MainNonDefaultTestHelper.class.getName(),"test.configuration","-dontwaitinmain"});
		Thread.sleep(100);
		
		assertThat(MainTestHelper.isContextDependencyInjectionStarted(), is(false));
		assertThat(MainTestHelper.isDatasourceStarted(), is(false));
		assertThat(MainTestHelper.isEmbeddedWebServerStarted(), is(false));
		assertThat(MainTestHelper.isJndiServicesStarted(), is(false));
		assertThat(MainTestHelper.isJavaTransactionManagerInitiated(), is(false));
		assertThat(MainTestHelper.isMainSingletonClassMatches(), is(false));
		assertThat(MainTestHelper.isMainSingletonIniated(), is(false));

		assertThat(MainNonDefaultTestHelper.isContextDependencyInjectionStarted(), is(true));
		assertThat(MainNonDefaultTestHelper.isDatasourceStarted(), is(true));
		assertThat(MainNonDefaultTestHelper.isEmbeddedWebServerStarted(), is(true));
		assertThat(MainNonDefaultTestHelper.isJndiServicesStarted(), is(true));
		assertThat(MainNonDefaultTestHelper.isJavaTransactionManagerInitiated(), is(true));
		assertThat(MainNonDefaultTestHelper.isMainSingletonClassMatches(), is(true));
		assertThat(MainNonDefaultTestHelper.isMainSingletonIniated(), is(true));		

		clearInstanceVariables();
		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		Thread mainTestHelperThread = new Thread(()->{
			try {
				clearInstanceVariables();
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
			Main.main(new String[] {MainTestHelper.class.getName()});
		});
		mainTestHelperThread.start();
		Thread.sleep(100);
		
		if(MainTestHelper.isMainSingletonIniated() && !mainTestHelperThread.isAlive())
			fail("Main execution for MainTestHelper did not wait in main method");

		Shutdown.main(new String[0]);
		Thread.sleep(100);
		if(mainTestHelperThread.isAlive())
			fail("MainTestHelper did not shutdown as expected");

		Thread mainNonDefaultTestHelperThread = new Thread(()->{
			try {
				clearInstanceVariables();
			} catch (IllegalArgumentException | IllegalAccessException e) {
			}
			Main.main(new String[] {MainNonDefaultTestHelper.class.getName(),"test.configuration"});
		});
		
		mainNonDefaultTestHelperThread.start();
		Thread.sleep(100);
		if(MainNonDefaultTestHelper.isMainSingletonIniated() && !mainNonDefaultTestHelperThread.isAlive())
			fail("Main execution for MainNonDefaultTestHelper did not wait in main method");
		
		Shutdown.main(new String[] {"10000"});
		Thread.sleep(100);
		if(mainNonDefaultTestHelperThread.isAlive())
			fail("MainNonDefaultTestHelper did not shutdown as expected");
		
		clearInstanceVariables();
	}

	@Test
	public void testMainUsage() throws Exception {
		Main.main(new String[] {MainTestHelper.class.getName(),"test","value"});
		clearInstanceVariables();
	}
	
	public static void clearInstanceVariables() throws IllegalArgumentException, IllegalAccessException {
		clearInstanceVariable(ContextDependencySpiFactory.getInstance(), "dependencySpi");
		clearInstanceVariable(DataSourceProviderSpiFactory.getInstance(), "dataSourceProviderSpi");
		clearInstanceVariable(EmbeddedWebServerSpiFactory.getInstance(), "webServerSpi");
		clearInstanceVariable(JavaNamingDirectorySpiFactory.getInstance(), "javaNamingDirectorySpi");
		clearInstanceVariable(JavaTransactionManagerSpiFactory.getInstance(), "transactionManagerSpi");
		clearMainInstance();
	}
	
	private static void clearInstanceVariable(Object object, String fieldName) throws IllegalArgumentException, IllegalAccessException {
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for(Field field: declaredFields)
        {
            if(fieldName.equals(field.getName())){
	            field.setAccessible(true);
	            field.set(object, null);
            }
        }
     }
	
	private static void clearMainInstance() throws IllegalArgumentException, IllegalAccessException {
        Field[] declaredFields = Main.class.getDeclaredFields();
        for(Field field: declaredFields)
        {
            if("instance".equals(field.getName())){
	            field.setAccessible(true);
	            field.set(null, null);
            }
        }
	}
}
