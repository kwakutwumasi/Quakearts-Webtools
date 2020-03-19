package com.quakearts.webapp.orm.cdi.test;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import javax.enterprise.inject.spi.CDI;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webapp.orm.cdi.test.datastore.TestDataStoreFactory;
import com.quakearts.webapp.orm.cdi.test.model.TestBean;
import com.quakearts.webapp.orm.cdi.test.model.TestObject;
import com.quakearts.webtools.test.CDIRunner;

@RunWith(CDIRunner.class)
public class DataStoreTest {
	
	@BeforeClass
	public static void changeDataStore() throws Exception{
		TestDataStoreFactory.replaceCurrentFactory();
	}

	@AfterClass
	public static void restoreDataStore() throws Exception{
		TestDataStoreFactory.restoreOldFactory();
	}

	@Test
	public void test() {
		TestBean bean = CDI.current().select(TestBean.class).get();
		
		TestObject testObject1 = bean.getFromDataStore(1);
		assertThat(testObject1, is(notNullValue()));
		assertThat(testObject1.getId(), is(1));
		assertThat(testObject1.getName(), is("test"));
		
		TestObject testObject2 = bean.getFromDataStoreTestDomain(2);
		assertThat(testObject2, is(notNullValue()));
		assertThat(testObject2.getId(), is(2));
		assertThat(testObject2.getName(), is("test-domain"));
	}

}
