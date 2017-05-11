package com.quakearts.test;

import javax.inject.Inject;

public class TestInjectImpl implements TestInject {
	@Inject
	TestAppBase appBase;
	
	@Override
	public void sayHello(){
		if(appBase!=null){
			System.out.println("Hello, World! I have AppBase (hashCode()) = "+appBase.hashCode());
		} else {
			System.out.println("Good bye!");
		}
	}
}
