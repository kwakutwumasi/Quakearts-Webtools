package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import com.quakearts.appbase.Main;
import com.quakearts.appbase.test.helpers.MainNonDefaultTestHelper;
import com.quakearts.appbase.test.helpers.MainTestHelper;

public class TestMain {

	@Test
	public void test() throws Exception {
		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();
		
		Main.main(new String[0]);		
		
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

		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		Main.main(new String[] {MainTestHelper.class.getName(),"-dontwaitinmain"});
		Thread.sleep(1000);
		
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

		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		Main.main(new String[] {MainNonDefaultTestHelper.class.getName(),"test.configuration","-dontwaitinmain"});
		Thread.sleep(1000);
		
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

		MainTestHelper.resetChecks();		
		MainNonDefaultTestHelper.resetChecks();

		new Thread(()->{
			Main.main(new String[] {MainTestHelper.class.getName()});
			didNotWaitDefault = true;
		}).start();
				
		new Thread(()->{
			Main.main(new String[] {MainNonDefaultTestHelper.class.getName(),"test.configuration"});
			didNotWaitNonDefault = true;
		}).start();
		
		Thread.sleep(1000);
		
		for(int i=0;i<4;i++) {
			Thread.sleep(1000);
			if(MainTestHelper.isMainSingletonIniated() && didNotWaitDefault)
				fail("Did not wait in main method for default configuration");

			if(MainNonDefaultTestHelper.isMainSingletonIniated() && didNotWaitNonDefault)
				fail("Did not wait in main method for default configuration");
			
			if(MainNonDefaultTestHelper.isMainSingletonIniated() && MainTestHelper.isMainSingletonIniated()) {
				return;
			}
		}
		
		fail("Main classes did not start as intended");
	}

	static boolean didNotWaitDefault, didNotWaitNonDefault;

}
