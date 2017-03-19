package com.quakearts.webapp.hibernate;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class HibernateSessionDataStoreFactory extends DataStoreFactory {
	
	public HibernateSessionDataStoreFactory() {
		if(getInstance()==null)
			setInstance(this);
	}
	
	@Override
	public DataStore getDataStore(String domain) {
		return new HibernateSessionDataStore(domain);
	}

}
