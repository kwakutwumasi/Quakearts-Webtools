package com.quakearts.webapp.orm;

import com.quakearts.webapp.orm.exception.DataStoreException;

@FunctionalInterface
public interface DataStoreFunction {
	public void execute(DataStoreConnection con) throws DataStoreException;
}
