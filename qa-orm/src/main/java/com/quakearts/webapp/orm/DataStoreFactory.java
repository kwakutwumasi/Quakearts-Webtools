package com.quakearts.webapp.orm;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.quakearts.webapp.orm.exception.DataStoreException;

public abstract class DataStoreFactory {
	private static DataStoreFactory instance;

	public static DataStoreFactory getInstance() {
		if(instance == null){
			Iterator<DataStoreFactory> dataStoreFactoryIterator = ServiceLoader.load(DataStoreFactory.class).iterator();
			
			while (dataStoreFactoryIterator.hasNext()) {//a run
				dataStoreFactoryIterator.next();
			}
		}
		
		return instance;
	}

	protected static void setInstance(DataStoreFactory instance) {
		if(DataStoreFactory.instance == null)
			DataStoreFactory.instance = instance;
		else
			throw new DataStoreException("A factory has already been set: "
					+ DataStoreFactory.instance.getClass().getName()
					+ ". Cannot replace it with: "
					+ instance.getClass().getName());
	}

	public abstract DataStore getDataStore();
	
	public abstract DataStore getDataStore(String storename);
}
