package com.quakearts.test;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public abstract class LoginModuleTest {
	
	protected MapBuilder newMap() {
		return new MapBuilder();
	}
	
	protected class MapBuilder{
		Map<String, Object> map = new HashMap<>();
		
		MapBuilder add(String name, Object object) {
			map.put(name, object);
			return this;
		}
		
		Map<String, ?> thenBuild(){
			return map;
		}
	}
	
	protected void runModule() throws LoginException {
		getModule().login();
		getModule().commit();
		getModule().logout();
	}
	
	protected abstract LoginModule getModule();
}
