package com.quakearts.webapp.hibernate.facelets;

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
