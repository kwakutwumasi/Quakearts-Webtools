/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.QueryOrder;

public interface DataStore {
	void save(Object object) throws DataStoreException;
	<Entity> Entity get(Class<Entity> clazz, Serializable id) throws DataStoreException;
	void update(Object object) throws DataStoreException;
	void delete(Object object) throws DataStoreException;
	<Entity> List<Entity> list(Class<Entity> clazz, Map<String, Serializable> parameters, QueryOrder... orders) throws DataStoreException;
	void saveOrUpdate(Object object) throws DataStoreException;
	<Entity> Entity refresh(Entity object) throws DataStoreException;
	void flushBuffers() throws DataStoreException;
	void executeFunction(DataStoreFunction function) throws DataStoreException;
	String getConfigurationProperty(String propertyName);
}
