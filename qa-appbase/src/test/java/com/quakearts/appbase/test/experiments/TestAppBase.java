package com.quakearts.appbase.test.experiments;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestAppBase {
	
	@Inject
	TestInject inject;
	
	public void init(){
		inject.sayHello();
		inject.testTransaction();
	}	
}
