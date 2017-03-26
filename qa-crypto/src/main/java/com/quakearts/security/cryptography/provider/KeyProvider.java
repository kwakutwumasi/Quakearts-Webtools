package com.quakearts.security.cryptography.provider;

import java.security.Key;
import java.util.Properties;

public interface KeyProvider {
	public Key getKey() throws Exception;
	public void setProperties(Properties props);
}
