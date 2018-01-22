package com.quakearts.appbase.test.experiments;

public class TestSubInjectImpl implements TestSubInject {

	public static boolean didSomething;
	
	@Override
	public void doSomething() {
		didSomething = true;
	}

}
