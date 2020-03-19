package com.quakearts.webapp.orm.cdi.test.datastore;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.test.model.TestObject;

public class TestDataStoreFactory extends DataStoreFactory {

	private static DataStoreFactory oldInstance;
	
	public static void replaceCurrentFactory() throws Exception {
		Field field = DataStoreFactory.class.getDeclaredField("instance");
		field.setAccessible(true);
		oldInstance = (DataStoreFactory) field.get(null);
		field.set(null, new TestDataStoreFactory());
	}
	
	public static void restoreOldFactory() throws Exception {
		Field field = DataStoreFactory.class.getDeclaredField("instance");
		field.setAccessible(true);
		field.set(null, oldInstance);
	}
	
	@Override
	public DataStore getDataStore() {
		TestObject testObject = new TestObject();
		testObject.setName("test");
		return MockingProxyBuilder.createMockingInvocationHandlerFor(DataStore.class)
				.mock("get").with(args->{
					assertTrue(args.get(0) == TestObject.class);
					testObject.setId(args.get(1));
					return testObject;
				})
				.thenBuild();
	}

	@Override
	public DataStore getDataStore(String storename) {
		TestObject testObject = new TestObject();
		testObject.setName("test-"+storename);
		return MockingProxyBuilder.createMockingInvocationHandlerFor(DataStore.class)
				.mock("get").with((args)->{ 
					assertTrue(args.get(0) == TestObject.class);
					testObject.setId(args.get(1));
					return testObject;
				})
				.thenBuild();
	}

}
