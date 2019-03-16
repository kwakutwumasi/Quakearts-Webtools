package com.quakearts.security.cryptography.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({BitManipulationUtilitiesTest.class, CryptographyOperationPermissionTest.class, 
	CryptoResourceTest.class, CryptoServiceProducerTest.class, JPAConverterTests.class,
	KeystoreKeyProviderTest.class })
public class AllCryptoTests {}
