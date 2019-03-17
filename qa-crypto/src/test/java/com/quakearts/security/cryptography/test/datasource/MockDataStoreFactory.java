package com.quakearts.security.cryptography.test.datasource;

import java.util.HashMap;
import java.util.Map;

import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class MockDataStoreFactory extends DataStoreFactory {

	private static Map<String, String> map = new HashMap<>();
	
	public MockDataStoreFactory() {
		try {
			setInstance(this);
		} catch (DataStoreException e) {}
	}
	
	@Override
	public DataStore getDataStore() {
		return MockingProxyBuilder.createMockingInvocationHandlerFor(DataStore.class)
				.mock("getConfigurationProperty")
				.with(arguments->map.get(arguments.get(0)))
				.thenBuild();
	}

	@Override
	public DataStore getDataStore(String storename) {
		if(storename.equals("teststore"))
			return getDataStore();
		throw new DataStoreException("invalid store name");
	}

	public static Map<String, String> getMap() {
		return map;
	}
}
