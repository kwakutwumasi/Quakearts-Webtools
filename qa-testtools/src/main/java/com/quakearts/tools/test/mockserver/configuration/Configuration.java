package com.quakearts.tools.test.mockserver.configuration;

public interface Configuration {
	static enum MockingMode {
		RECORD,
		MOCK
	}
	
	String getURLToRecord();
	MockingMode getMockingMode();
	int getPort();
	String getIPInterface();
	boolean useTLS();
	String getKeyStore();
	String getKeyStorePassword();
	String getKeyStoreType();
	int getReadTimeout();
	int getConnectTimeout();
}
