package com.quakearts.syshub.core.utils;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class SystemDataStoreUtils {
	private SystemDataStoreUtils() {
	}

	private static final SystemDataStoreUtils instance = new SystemDataStoreUtils();
	
	public static SystemDataStoreUtils getInstance() {
		return instance;
	}
	
	public DataStore getSystemDataStore() {
		return DataStoreFactory.getInstance().getDataStore("system");
	}
}
