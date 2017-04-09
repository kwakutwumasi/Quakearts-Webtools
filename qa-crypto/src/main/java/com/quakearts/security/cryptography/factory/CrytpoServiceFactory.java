package com.quakearts.security.cryptography.factory;

import java.security.Key;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class CrytpoServiceFactory {
	private CrytpoServiceFactory() {
	}

	private static final Logger log = LoggerFactory.getLogger(CrytpoServiceFactory.class);
	
	private static final CrytpoServiceFactory instance = new CrytpoServiceFactory();
	
	public static CrytpoServiceFactory getInstance() {
		return instance;
	}
	
	public CryptoResource getCryptoResourc(String instance, String keyProviderClass, Map<Object, Object> properties, String name) throws Exception{
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
}
