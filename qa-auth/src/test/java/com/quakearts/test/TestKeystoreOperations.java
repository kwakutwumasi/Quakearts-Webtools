package com.quakearts.test;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import org.junit.Test;

import com.quakearts.webapp.security.jwt.keyprovider.impl.ECKeystoreKeyPairProvider;
import com.quakearts.webapp.security.jwt.keyprovider.impl.ECKeystoreKeyPairProvider.ECDSAKeySize;
import com.quakearts.webapp.security.jwt.keyprovider.impl.RSAKeystoreKeyPairProvider;

public class TestKeystoreOperations {

	@Test
	public void testKeystoreECKeyLengths() throws Exception {
		try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.keystore")){
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			char[] password = new char[]{'t','e','s','t','1','2'};
			keyStore.load(in, password);
			
			assertThat(getKeyLength("testeckey", keyStore, password), is(256));
			assertThat(getKeyLength("testeckey384", keyStore, password), is(384));
			assertThat(getKeyLength("testeckey512", keyStore, password), is(521));
		} finally {
		}
	}
	
	private int getKeyLength(String name, KeyStore keyStore, char[] password) throws NoSuchAlgorithmException, UnrecoverableEntryException, KeyStoreException{
		PrivateKeyEntry entry = (PrivateKeyEntry) 
				keyStore.getEntry(name, new KeyStore.PasswordProtection(password));

		ECPublicKey ecPublicKey = (ECPublicKey) entry.getCertificate().getPublicKey();
		return ecPublicKey.getParams().getOrder().bitLength();
	}
	
	@Test
	public void testRSAKeystoreKeyPairProvider() throws Exception {
		RSAKeystoreKeyPairProvider provider = new RSAKeystoreKeyPairProvider();
		provider.setFile("test.keystore");
		provider.setPassword("test12".toCharArray());
		provider.setStoreType("JCEKS");
		
		KeyPair keyPair = provider.getKeyPair("testrsakey");
		
		assertThat(keyPair != null, is(true));
		assertThat(keyPair.getPrivate() != null, is(true));
		assertThat(keyPair.getPublic() != null, is(true));
		
		assertThat(RSAPrivateKey.class.isAssignableFrom(keyPair.getPrivate().getClass()), is(true));
		assertThat(RSAPublicKey.class.isAssignableFrom(keyPair.getPublic().getClass()), is(true));
		
		assertThat(((RSAPublicKey)keyPair.getPublic()).getModulus().bitLength(), is(2048));
	}
		
	@Test
	public void testECKeystoreKeyPairProvider() throws Exception {
		ECKeystoreKeyPairProvider provider = new ECKeystoreKeyPairProvider(ECDSAKeySize.ES256KEY);

		provider.setFile("test.keystore");
		provider.setPassword("test12".toCharArray());
		provider.setStoreType("JCEKS");
		
		KeyPair keyPair = provider.getKeyPair("testeckey");
		
		assertThat(keyPair != null, is(true));
		assertThat(keyPair.getPrivate() != null, is(true));
		assertThat(keyPair.getPublic() != null, is(true));
		
		assertThat(ECPrivateKey.class.isAssignableFrom(keyPair.getPrivate().getClass()), is(true));
		assertThat(ECPublicKey.class.isAssignableFrom(keyPair.getPublic().getClass()), is(true));
		
		assertThat(((ECPublicKey)keyPair.getPublic()).getParams().getOrder().bitLength(), is(256));
		
		provider = new ECKeystoreKeyPairProvider(ECDSAKeySize.ES384KEY);

		provider.setFile("test.keystore");
		provider.setPassword("test12".toCharArray());
		provider.setStoreType("JCEKS");
		
		keyPair = provider.getKeyPair("testeckey384");
		
		assertThat(keyPair != null, is(true));
		assertThat(keyPair.getPrivate() != null, is(true));
		assertThat(keyPair.getPublic() != null, is(true));
		
		assertThat(ECPrivateKey.class.isAssignableFrom(keyPair.getPrivate().getClass()), is(true));
		assertThat(ECPublicKey.class.isAssignableFrom(keyPair.getPublic().getClass()), is(true));
		
		assertThat(((ECPublicKey)keyPair.getPublic()).getParams().getOrder().bitLength(), is(384));
		
		provider = new ECKeystoreKeyPairProvider(ECDSAKeySize.ES512KEY);

		provider.setFile("test.keystore");
		provider.setPassword("test12".toCharArray());
		provider.setStoreType("JCEKS");
		
		keyPair = provider.getKeyPair("testeckey512");
		
		assertThat(keyPair != null, is(true));
		assertThat(keyPair.getPrivate() != null, is(true));
		assertThat(keyPair.getPublic() != null, is(true));
		
		assertThat(ECPrivateKey.class.isAssignableFrom(keyPair.getPrivate().getClass()), is(true));
		assertThat(ECPublicKey.class.isAssignableFrom(keyPair.getPublic().getClass()), is(true));
		
		assertThat(((ECPublicKey)keyPair.getPublic()).getParams().getOrder().bitLength(), is(521));
		
		try {
			provider = new ECKeystoreKeyPairProvider(ECDSAKeySize.ES256KEY);

			provider.setFile("test.keystore");
			provider.setPassword("test12".toCharArray());
			provider.setStoreType("JCEKS");
			
			keyPair = provider.getKeyPair("testeckey384");
			fail("No exception thrown");
		} catch (Exception e) {
		}
	}
}
