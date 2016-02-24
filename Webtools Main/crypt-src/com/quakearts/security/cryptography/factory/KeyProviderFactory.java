package com.quakearts.security.cryptography.factory;

import com.quakearts.security.cryptography.provider.KeyProvider;

public class KeyProviderFactory {
	private KeyProviderFactory(){
	}
	
	public static KeyProvider createKeyProvider(String keyProviderClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> fileformatterclass = Class.forName(keyProviderClass);
        Object obj = fileformatterclass.newInstance();
        if(obj instanceof KeyProvider){
        	return (KeyProvider)obj;
        }else
        	throw new ClassNotFoundException(keyProviderClass+" is not a valid "+KeyProvider.class.getName());
	}
}
