package com.quakearts.appbase.test.experiments;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class TestSubInjectDecorator implements TestSubInject {

	@Inject
	@Delegate
	@Any
	private TestSubInject inject;
	
	private static boolean decorated;
	
	@Override
	public void doSomething() {
		decorated = inject instanceof TestSubInjectImpl;
		inject.doSomething();
	}

	public static boolean decoratedSubInject() {
		return decorated;
	}
	
	public static void reset() {
		decorated = true;
	}
}
