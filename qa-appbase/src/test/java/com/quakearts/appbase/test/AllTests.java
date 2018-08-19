package com.quakearts.appbase.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAppBasePropertiesLoader.class, TestAtomikosBeanDatasourceSpiImpl.class,
		TestAtomikosJavaTransactionManagerSpiImpl.class, TestAppBaseMainStartup.class, TestTomcatEmbeddedServerSpiImpl.class,
		TestWeldContextDependencySpiImpl.class, TestFactoryClasses.class,
		TestAppBaseVirtualDirectoryResourceSet.class,
		TestAppBaseWebappClassLoader.class,
		TestClasspathResourcesSpiImpl.class,
		TestInternalJson.class})
public class AllTests {
}
