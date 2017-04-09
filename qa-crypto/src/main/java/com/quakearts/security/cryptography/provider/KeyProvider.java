package com.quakearts.security.cryptography.provider;

import java.security.Key;
import java.util.Map;

public interface KeyProvider {
	public Key getKey() throws Exception;
	public void setProperties(Map<Object, Object> props);
}
