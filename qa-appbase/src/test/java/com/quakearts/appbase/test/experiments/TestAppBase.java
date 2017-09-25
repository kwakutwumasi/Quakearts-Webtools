package com.quakearts.appbase.test.experiments;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;

@Singleton
public class TestAppBase {
	
	public void init(){
		TestInject inject = CDI.current().select(TestInject.class).get();		
		inject.sayHello();
	}	
}
