package com.quakearts.test;

public class TestInjectImpl implements TestInject {

	public static boolean done = false;
	
	@Override
	public String sayHello() {
		done=true;
		return "Hello, World";
	}

}
