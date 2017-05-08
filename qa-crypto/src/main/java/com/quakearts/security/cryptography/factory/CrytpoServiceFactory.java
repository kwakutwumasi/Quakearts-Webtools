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
package com.quakearts.security.cryptography.factory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.Key;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class CrytpoServiceFactory {
	private static final String CRYPTO_PROPERTIES = ".crypto.properties";

	private CrytpoServiceFactory() {
	}

	private static final Logger log = LoggerFactory.getLogger(CrytpoServiceFactory.class);
	
	private static final CrytpoServiceFactory instance = new CrytpoServiceFactory();
	
	public static CrytpoServiceFactory getInstance() {
		return instance;
	}
	
	public CryptoResource getCryptoResource(String instance, String keyProviderClass, Map<Object, Object> properties, String name) throws Exception{
		KeyProvider provider;
		Key key;
		
    	provider = KeyProviderFactory.createKeyProvider(keyProviderClass);
    	
    	if(properties == null){
    		log.warn("Parameter 'properties' is null. Key Provider may not startup properly.");
    	} else {
    		provider.setProperties(properties);
    	}
    	
    	key = provider.getKey();
    	return new CryptoResource(key, instance, name);
	}
	
	private static final Map<String, CryptoResource> localResources = new ConcurrentHashMap<>();
	
	public CryptoResource getCryptoResource(String name) throws Exception {
		CryptoResource resource = localResources.get(name);
		if(resource==null){
			Properties properties = new Properties();
		
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(name+CRYPTO_PROPERTIES);
			if(in==null)
				throw new FileNotFoundException("Unable to find "+name+CRYPTO_PROPERTIES);
		
			properties.load(in);
		
			String instance = properties.getProperty("crypto.instance");
			String keyProviderClass = properties.getProperty("crypto.key.provider.class");
			
			resource = getCryptoResource(instance, keyProviderClass, properties, name);
			
			localResources.put(name, resource);
		}
		
		return resource;
	}
}
