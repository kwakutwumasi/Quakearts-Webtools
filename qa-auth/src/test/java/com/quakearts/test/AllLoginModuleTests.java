package com.quakearts.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestDatabaseLoginModule.class, TestDatabaseServerLoginModule.class, TestDefaultLDAPConnection.class, TestDirectoryLoginModule.class, TestHashPassword.class,
		TestJWTLoginModule.class, TestJsonInternal.class, TestKeystoreOperations.class, TestLoadProfileLoginModule.class, TestSigners.class })
public class AllLoginModuleTests {}
