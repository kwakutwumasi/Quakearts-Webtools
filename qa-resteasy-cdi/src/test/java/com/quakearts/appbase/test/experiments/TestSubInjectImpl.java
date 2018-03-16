package com.quakearts.appbase.test.experiments;

public class TestSubInjectImpl implements TestSubInject {

	private static boolean didSomething;
	
	@Override
	public void doSomething() {
		didSomething = true;
	}

	public static boolean hasDoneSomething() {
		return didSomething;
	}
	
	public static void reset() {
		didSomething = false;
	}
}
