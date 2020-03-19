package com.quakearts.webapp.orm.cdi.test.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;

@Singleton
public class TestBean {

	@Inject @DataStoreHandle
	private DataStore dataStore;
	
	@Inject @DataStoreHandle("domain")
	private DataStore dataStoreTestDomain;

	public TestObject getFromDataStore(int id){
		return dataStore.get(TestObject.class, id);
	}
	
	public TestObject getFromDataStoreTestDomain(int id){
		return dataStoreTestDomain.get(TestObject.class, id);
	}

}
