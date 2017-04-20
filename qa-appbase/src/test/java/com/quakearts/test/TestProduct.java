package com.quakearts.test;

import javax.inject.Inject;

public interface TestProduct {
	String myStatus();
	@Inject
	TestInject getTestInject();
}
