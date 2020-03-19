package com.quakearts.webapp.orm.cdi.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.cdi.test.runner.CDIAndDataSourceRunner;

@RunWith(CDIAndDataSourceRunner.class)
public class DataStoreFactoryTest extends TestBase {

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;
	
	@Test @Transactional(TransactionType.SINGLETON)
	public void testDataStoreFactoryHandle() {
		DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
		assertTrue(dataStore instanceof HibernateSessionDataStore);
	}

}
