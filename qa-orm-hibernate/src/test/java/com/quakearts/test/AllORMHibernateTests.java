package com.quakearts.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OrmDataStoreTest.class, OrmStringConcatTest.class })
public class AllORMHibernateTests {

}
