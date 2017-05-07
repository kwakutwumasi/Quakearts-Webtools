package com.quakearts.webapp.hibernate;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class HibernateSessionDataStoreFactory extends DataStoreFactory {
	
	public HibernateSessionDataStoreFactory() {
		setInstance(this);
	}
	
	@Override
	public DataStore getDataStore() {
		return new HibernateSessionDataStore();
	}
	
	@Override
	public DataStore getDataStore(String domain) {
		return new HibernateSessionDataStore(domain);
	}

}
