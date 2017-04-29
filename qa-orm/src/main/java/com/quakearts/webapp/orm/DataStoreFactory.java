package com.quakearts.webapp.orm;

import com.quakearts.webapp.orm.exception.DataStoreException;

public abstract class DataStoreFactory {
	private static DataStoreFactory instance;

	public static DataStoreFactory getInstance() {
		return instance;
	}

	protected static void setInstance(DataStoreFactory instance) {
		if(DataStoreFactory.instance == null)
			DataStoreFactory.instance = instance;
		else
			throw new DataStoreException("A factory has already been set.");
	}

	public abstract DataStore getDataStore();
	
	public abstract DataStore getDataStore(String storename);
}
