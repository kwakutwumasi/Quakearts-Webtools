package com.quakearts.webapp.security.rest.cache;

import javax.security.auth.Subject;

public interface AuthenticationCacheService {
	void saveSubject(String key, Subject subject);
	Subject retrieveSubject(String key);
	void invalidateSubject(String key);
	String getKey(String identity, String authenticationData, String contextName);
}
