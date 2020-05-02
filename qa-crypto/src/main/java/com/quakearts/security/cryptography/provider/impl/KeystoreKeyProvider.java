/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.security.cryptography.provider.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Map;
import java.util.Properties;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class KeystoreKeyProvider implements KeyProvider {

	private String keyStoreFile;
	private String keyStoreType;
	private String storePass;
	private String keyPass;
	private String keyAlias;
			
	@Override
	public Key getKey() throws KeyProviderException {			
		if(keyStoreType == null)
			throw new CryptoResourceRuntimeException("keyStoreType is null");
		if(keyStoreFile == null)
			throw new CryptoResourceRuntimeException("keyfile is null");
		if(keyAlias == null)
			throw new CryptoResourceRuntimeException("keyAlias is null");
		if(keyPass == null)
			throw new CryptoResourceRuntimeException("keyPass is null");
		if(storePass == null)
			throw new CryptoResourceRuntimeException("storePass is null");
		
		KeyStore store;
		try {
			store = KeyStore.getInstance(keyStoreType);
		} catch (KeyStoreException e) {
			throw new KeyProviderException(e);
		}
		
		File file = new File(keyStoreFile);
		Key key;
		try(InputStream stream = file.exists()? new FileInputStream(file): Thread.currentThread().getContextClassLoader().getResourceAsStream(keyStoreFile)){
			if(stream == null)
				throw new FileNotFoundException("The keystore file "+file+" does not exists");
		
			store.load(stream, storePass.toCharArray());
			key = store.getKey(keyAlias, keyPass.toCharArray());
		} catch (IOException | GeneralSecurityException e) {
			throw new KeyProviderException(e);
		} 
		return key;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setProperties(Map properties) {
		Properties keyProperties = new Properties();
		keyProperties.putAll(properties);
		
		keyStoreType = keyProperties.getProperty("key.storeType");
		keyPass = keyProperties.getProperty("key.pass");
		storePass = keyProperties.getProperty("store.pass");
		keyStoreFile = keyProperties.getProperty("store.file");
		keyAlias = keyProperties.getProperty("key.alias");
	}

}
