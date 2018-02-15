package com.quakearts.webapp.orm;

import com.quakearts.webapp.orm.exception.DataStoreException;

@FunctionalInterface
public interface DataStoreConnection {
	<Connection> Connection getConnection(Class<Connection> expectedConnection) throws DataStoreException;
}
