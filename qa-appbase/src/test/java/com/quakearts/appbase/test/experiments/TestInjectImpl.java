package com.quakearts.appbase.test.experiments;

import javax.inject.Inject;

public class TestInjectImpl implements TestInject {
	@Inject
	TestAppBase appBase;
	
	private static boolean saidHello;
	private static boolean appBaseloaded;
	
	@Override
	public void sayHello(){
		saidHello = true;
		if(appBase!=null){
			appBaseloaded = true;
		}
	}
	
	public static boolean saidHello() {
		return saidHello;
	}
	
	public static boolean appBaseloaded() {
		return appBaseloaded;
	}
}
