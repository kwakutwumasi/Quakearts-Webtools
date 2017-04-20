package com.quakearts.test;

import javax.inject.Inject;

public class TestProductImpl implements TestProduct {

	public static boolean done = false;
	
	@Override
	public String myStatus() {
		done = true;
		return "I have been produced!";
	}
	
	@Inject
	private TestInject inject;
	
	@Override
	public TestInject getTestInject() {
		return inject;
	}
	
	public void setTestInject(TestInject inject) {
		this.inject = inject;
	}
}
