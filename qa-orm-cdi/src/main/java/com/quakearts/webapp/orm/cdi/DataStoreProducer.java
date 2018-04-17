package com.quakearts.webapp.orm.cdi;

import javax.enterprise.inject.Produces;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;

public class DataStoreProducer {
	@Produces
	public @DataStoreHandle DataStore getDataStoreHandle() {
		return new CDIDataStore();
	}
}
