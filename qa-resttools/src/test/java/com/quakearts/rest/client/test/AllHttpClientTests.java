package com.quakearts.rest.client.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ErrorTest0.class, RegressionTest0.class, RegressionTest1.class, RegressionTest2.class,
		RegressionTest3.class, TestHttpClient.class })
public class AllHttpClientTests {}
