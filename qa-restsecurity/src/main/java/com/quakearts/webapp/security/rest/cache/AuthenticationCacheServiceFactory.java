package com.quakearts.webapp.security.rest.cache;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.quakearts.webapp.security.rest.exception.AuthenticationCacheServiceException;

public class AuthenticationCacheServiceFactory {

	private AuthenticationCacheServiceFactory() {
	}
	
	public static AuthenticationCacheService findService() {
		ServiceLoader<AuthenticationCacheService> loader = ServiceLoader.load(AuthenticationCacheService.class);
		Iterator<AuthenticationCacheService> iterator = loader.iterator();
		AuthenticationCacheService cacheService = null; 
		if(iterator.hasNext()) {
			cacheService = iterator.next();
		}
		
		if(iterator.hasNext())
			throw new AuthenticationCacheServiceException("Multiple services registered as "
					+AuthenticationCacheService.class.getName()
					+" were found on the classpath. Please remove reduntant services");

		return cacheService;
	}
	
}
