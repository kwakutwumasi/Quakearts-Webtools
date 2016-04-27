package com.quakearts.security.cryptography.provider.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.quakearts.security.cryptography.provider.KeyProvider;

public class KeystoreKeyProvider implements KeyProvider {

	String keyStoreFile,keyStoreType,storePass,keyPass,keyAlias;
	private static final Logger log = Logger.getLogger(KeystoreKeyProvider.class.getName());
	
	public KeystoreKeyProvider(){
	}
	
	@Override
	public Key getKey() throws Exception {
		Exception exception = null;
			
		if(keyStoreType == null)
			throw new NullPointerException("keyStoreType is null");
		if(keyStoreFile == null)
			throw new NullPointerException("keyfile is null");
		if(keyAlias == null)
			throw new NullPointerException("keyAlias is null");
		if(keyPass == null)
			throw new NullPointerException("keyPass is null");
		if(storePass == null)
			throw new NullPointerException("storePass is null");
		
		KeyStore store = KeyStore.getInstance(keyStoreType);
		
		InputStream stream;

		File file = new File(keyStoreFile);
		if(file.exists()){
			stream = new FileInputStream(file);
		} else {
			stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStoreFile);
		}
		
		if(stream == null)
			throw new FileNotFoundException("The keystore file "+file+" does not exists");
		
		Key key = null;
		try {
			store.load(stream, storePass.toCharArray());
			key = store.getKey(keyAlias, keyPass.toCharArray());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage(),e);
			exception = e;
		} finally {
			stream.close();
			if(exception !=null)
				throw exception;
		}
		return key;
	}

	@Override
	public void setProperties(Properties properties) {
		keyStoreType = properties.getProperty("key.storeType");
		keyPass = properties.getProperty("key.pass");
		storePass = properties.getProperty("store.pass");
		keyStoreFile = properties.getProperty("store.file");
		keyAlias = properties.getProperty("key.alias");
		if(log.isLoggable(Level.FINE)){
			log.fine("Properties: "+
		"\nkey.storeType: " +keyStoreType+
		"\nstore.file: " +keyStoreFile+
		"\nkey.alias: "+keyAlias);
		}
	}

}
