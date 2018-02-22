package com.quakearts.tools.test.mockserver.configuration;

public interface Configuration {
	static enum MockingMode {
		RECORD,
		MOCK
	}
	
	String getURLToMock();
	MockingMode getMockingMode();
	int getPort();
	String getIPInterface();
	boolean useTLS();
	String getKeyStore();
	String getKeyPassword();
	String getKeyStoreType();
	boolean verifyClientCertificate();
	int getReadTimeout();
	int getConnectTimeout();
}
