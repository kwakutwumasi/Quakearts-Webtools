package com.quakearts.webapp.orm.cdi.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DataStoreFactoryTest.class, DataStoreTest.class, TransactionalDataStoreTest.class })
public class AllORMCDITests {}
