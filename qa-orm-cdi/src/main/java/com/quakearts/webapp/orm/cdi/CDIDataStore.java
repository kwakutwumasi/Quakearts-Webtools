/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webapp.orm.cdi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.DataStoreFunction;
import com.quakearts.webapp.orm.query.QueryOrder;

/**A lazy initialized {@linkplain DataStore}. This is to enable CDI injection of the resource
 * Note: the DataStore instance is not thread safe, and must be acauired and disposed of 
 * within the context of a single thread. Re use may result in unwanted errors.
 * @author kwakutwumasi-afriyie
 *
 */
public class CDIDataStore implements DataStore {

	private ThreadLocal<DataStore> wrappedDataStore =  ThreadLocal
			.withInitial(DataStoreFactory.getInstance()::getDataStore);
		
	@Override
	public void save(Object object) {
		wrappedDataStore.get().save(object);
	}

	@Override
	public <Entity> Entity get(Class<Entity> clazz, Serializable id) {
		return wrappedDataStore.get().get(clazz, id);
	}

	@Override
	public void update(Object object) {
		wrappedDataStore.get().update(object);
	}

	@Override
	public void delete(Object object) {
		wrappedDataStore.get().delete(object);
	}

	@Override
	public <Entity> List<Entity> list(Class<Entity> clazz, Map<String, Serializable> parameters, QueryOrder... orders) {
		return wrappedDataStore.get().list(clazz, parameters, orders);
	}

	@Override
	public void saveOrUpdate(Object object) {
		wrappedDataStore.get().saveOrUpdate(object);
	}

	@Override
	public <Entity> Entity refresh(Entity object) {
		return wrappedDataStore.get().refresh(object);
	}

	@Override
	public void flushBuffers() {
		wrappedDataStore.get().flushBuffers();
	}

	@Override
	public void executeFunction(DataStoreFunction function) {
		wrappedDataStore.get().executeFunction(function);
	}

	@Override
	public String getConfigurationProperty(String propertyName) {
		return wrappedDataStore.get().getConfigurationProperty(propertyName);
	}

	@Override
	public void clearBuffers() {
		wrappedDataStore.get().clearBuffers();
	}
}
