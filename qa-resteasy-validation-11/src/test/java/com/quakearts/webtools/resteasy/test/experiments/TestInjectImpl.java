package com.quakearts.webtools.resteasy.test.experiments;

import com.quakearts.webtools.resteasy.validation.test.helpers.TestParameter;

public class TestInjectImpl implements TestInject {	
	private static boolean processedTestParameter;
	
	public static void reset() {
		processedTestParameter = false;
	}

	@Override
	public void processTestParameter(TestParameter parameter){
		processedTestParameter = true;
	}
	
	public static boolean processedTestParameter() {
		return processedTestParameter;
	}
}
