package com.quakearts.test.hibernate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant.TransactionType;
import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;

public class TestHibernateMainBean {
		
	@TransactionParticipant(TransactionType.SINGLETON)
	public void init(){
		DataStore store = new HibernateSessionDataStore();
		
		TestModel model = new TestModel();
		model.setId(1);
		model.setTestBoolean(true);
		model.setTestDouble(80d);
		model.setTestName("Test");
		
		store.save(model);
		store.flushBuffers();
		
		assertThat(model.id, is(1));
	}


}
