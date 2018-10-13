package com.quakearts.webtools.test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllServicesTest.class, CDIRunnerTest.class, DataSourceRunnerTest.class,
		EmbeddedWebserverRunnerTest.class, TransactionManagerRunnerTest.class })
public class AllTestHelperTests {}
