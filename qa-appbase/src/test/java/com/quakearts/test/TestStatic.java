package com.quakearts.test;

import javax.enterprise.inject.spi.CDI;

public class TestStatic {	
	private static TestStatic instance;
	
	TestStatic() {
	}
	
	public static TestStatic getInstance() {
		if(instance==null)
			instance = CDI.current().select(TestStatic.class).get();
		
		return instance;
	}
	
	@TestingInterceptor
	public void intercepted(){
	}
}
