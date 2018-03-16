package com.quakearts.appbase.test.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.appbase.test.experiments.TestInject;

@Named("testNamed")
public class TestNamed {
	
	private static boolean beanAccessed;
	
	@Inject
	private TestInject testInject;
	
	public String getResponse() {
		testInject.sayHello();
		testInject.testTransaction();
		return "OK";
	}
	
	public static boolean beanHasBeenAccessed() {
		return beanAccessed;
	}
}
