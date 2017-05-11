package com.quakearts.test;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Singleton;

import com.quakearts.appbase.Main;

@Singleton
public class TestAppBase {

	public void init(){
		System.out.println("Started. I am (hashCode()) = "+hashCode());
		
		TestInject inject = CDI.current().select(TestInject.class).get();		
		inject.sayHello();
	}
	
	public static void main(String[] args) {
		Main.main(new String[]{"com.quakearts.test.TestAppBase"});
	}
}
