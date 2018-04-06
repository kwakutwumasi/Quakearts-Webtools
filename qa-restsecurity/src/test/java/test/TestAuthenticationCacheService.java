package test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.Subject;

import com.quakearts.webapp.security.rest.cache.AuthenticationCacheService;
import com.quakearts.webapp.security.util.HashPassword;

public class TestAuthenticationCacheService implements AuthenticationCacheService {

	private static final Map<String, Subject> map = new ConcurrentHashMap<>();
	private static boolean cacheHit, cacheLoaded;
	
	public static void reset() {
		cacheHit = false;
		cacheLoaded = false;
	}
	
	public static boolean cacheHit() {
		return cacheHit;
	}
	
	public static boolean cacheLoaded() {
		return cacheLoaded;
	}
	
	@Override
	public void saveSubject(String key, Subject subject) {
		cacheLoaded = true;
		map.put(key, subject);
	}

	@Override
	public Subject retrieveSubject(String key) {
		cacheHit = map.containsKey(key);
		return map.get(key);
	}

	@Override
	public void invalidateSubject(String key) {
		map.remove(key);
	}

	@Override
	public String getKey(String identity, String authenticationData, String contextName) {
		return new HashPassword(identity, "SHA-256", 23, contextName).toString();
	}
}
