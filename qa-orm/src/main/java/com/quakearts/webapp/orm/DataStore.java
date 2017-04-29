package com.quakearts.webapp.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.QueryOrder;

public interface DataStore {
	void save(Object object) throws DataStoreException;
	<T> T get(Class<T> clazz, Serializable id) throws DataStoreException;
	void update(Object object) throws DataStoreException;
	void delete(Object object) throws DataStoreException;
	<T> List<T> list(Class<T> clazz, Map<String, Serializable> parameters, QueryOrder... orders) throws DataStoreException;
	void saveOrUpdate(Object object) throws DataStoreException;
	<T> T refresh(T object) throws DataStoreException;
	void flushBuffers();
	String getConfigurationProperty(String propertyName);
}