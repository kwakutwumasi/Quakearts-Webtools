package com.quakearts.test.hibernate;

import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;

public class TestAppBaseHibernate {
	public void init(){
		DataStore store = new HibernateSessionDataStore();
		
		TestModel model = new TestModel();
		model.setId(1);
		model.setTestBoolean(true);
		model.setTestDouble(80d);
		model.setTestName("Test");
		
		store.save(model);
	}
}
