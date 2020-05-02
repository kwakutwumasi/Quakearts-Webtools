package com.quakearts.security.cryptography.test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.io.File;
import java.security.Key;
import java.util.Properties;

import javax.security.auth.Destroyable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.provider.impl.KeystoreKeyProvider;

public class KeystoreKeyProviderTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	Properties getKeyProperties() {
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("key.pass","password");
		properties.setProperty("store.pass","password");
		properties.setProperty("key.alias","testkey");
		
		return properties;
	}
	
	@Test
	public void testGetKeyFromFile() throws Exception {
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = getKeyProperties();
		properties.setProperty("store.file","src"+File.separator
				+"test"+File.separator+"resources"+File.separator
				+"test.keystore");
		keyProvider.setProperties(properties);
		
		Key key = keyProvider.getKey();
		assertNotNull(key);
		assertThat(key instanceof Destroyable, is(true));
	}

	@Test
	public void testGetKeyFromClassPath() throws Exception {
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = getKeyProperties();
		properties.setProperty("store.file","test.keystore");
		keyProvider.setProperties(properties);
		
		assertNotNull(keyProvider.getKey());
	}

	@Test
	public void testGetKeyWithoutKeyStoreType() throws Exception {
		expectedException.expect(CryptoResourceRuntimeException.class);
		expectedException.expectMessage(is("keyStoreType is null"));
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}

	@Test
	public void testGetKeyWithoutKeyfile() throws Exception {
		expectedException.expect(CryptoResourceRuntimeException.class);
		expectedException.expectMessage(is("keyfile is null"));
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}

	@Test
	public void testGetKeyWithoutKeyAlias() throws Exception {
		expectedException.expect(CryptoResourceRuntimeException.class);
		expectedException.expectMessage(is("keyAlias is null"));
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("store.file","src"+File.separator
				+"test"+File.separator+"resources"+File.separator
				+"test.keystore");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}

	@Test
	public void testGetKeyWithoutKeyPass() throws Exception {
		expectedException.expect(CryptoResourceRuntimeException.class);
		expectedException.expectMessage(is("keyPass is null"));
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("store.file","src"+File.separator
				+"test"+File.separator+"resources"+File.separator
				+"test.keystore");
		properties.setProperty("key.alias","testkey");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}

	@Test
	public void testGetKeyWithoutStorePass() throws Exception {
		expectedException.expect(CryptoResourceRuntimeException.class);
		expectedException.expectMessage(is("storePass is null"));
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("store.file","src"+File.separator
				+"test"+File.separator+"resources"+File.separator
				+"test.keystore");
		properties.setProperty("key.pass","password");
		properties.setProperty("key.alias","testkey");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}
	
	@Test
	public void testGetKeyWithInvalidKeyStoreType() throws Exception {
		expectedException.expect(KeyProviderException.class);
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","INVALID");
		properties.setProperty("store.file","src"+File.separator
				+"test"+File.separator+"resources"+File.separator
				+"test.keystore");
		properties.setProperty("key.pass","password");
		properties.setProperty("store.pass","password");
		properties.setProperty("key.alias","testkey");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}
	
	@Test
	public void testGetKeyWithInvalidKeyStoreFile() throws Exception {
		expectedException.expect(KeyProviderException.class);
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("store.file","invalid.keystore");
		properties.setProperty("key.pass","password");
		properties.setProperty("store.pass","password");
		properties.setProperty("key.alias","testkey");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}
	
	@Test
	public void testGetKeyWithInvalidKey() throws Exception {
		expectedException.expect(KeyProviderException.class);
		KeystoreKeyProvider keyProvider = new KeystoreKeyProvider();
		Properties properties = new Properties();
		properties.setProperty("key.storeType","JCEKS");
		properties.setProperty("store.file","invalid.keystore");
		properties.setProperty("key.pass","password1");
		properties.setProperty("store.pass","password1");
		properties.setProperty("key.alias","invalid");
		keyProvider.setProperties(properties);
		
		keyProvider.getKey();
	}

}
