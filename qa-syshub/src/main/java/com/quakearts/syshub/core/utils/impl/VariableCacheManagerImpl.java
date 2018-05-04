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
package com.quakearts.syshub.core.utils.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.syshub.core.utils.Serializer;
import com.quakearts.syshub.core.utils.SystemDataStoreManager;
import com.quakearts.syshub.core.utils.VariableCacheManager;
import com.quakearts.syshub.model.VariableCache;
import com.quakearts.webapp.orm.DataStore;

@Singleton
public class VariableCacheManagerImpl implements VariableCacheManager {
	VariableCacheManagerImpl() {
	}
	
	@Inject
	private Serializer serializer;
	@Inject
	private SystemDataStoreManager storeManager;
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.VariableCacheManager#storeVariable(java.lang.String, java.io.Serializable)
	 */
	@Override
	@Transactional(TransactionType.SINGLETON)
	public void storeVariable(String key, Serializable obj) throws Exception{
		try{
			VariableCache cache = new VariableCache();
			cache.setAppKey(key);
			cache.setAppData(serializer.toByteArray(obj));
			
			storeManager.getDataStore().save(cache);
		} catch(Exception e){
			throw new Exception(e);
		}		
	}

	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.VariableCacheManager#updateVariable(java.lang.String, java.io.Serializable)
	 */
	@Override
	@Transactional(TransactionType.SINGLETON)
	public void updateVariable(String key, Serializable obj) throws Exception{
		try{
			DataStore systemDataStore = storeManager.getDataStore();
			VariableCache cache = (VariableCache) systemDataStore.get(VariableCache.class, key);
			if(cache !=null){
				cache.setAppData(serializer.toByteArray(obj));
				systemDataStore.update(cache);
			}else{
				storeVariable(key, obj);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.VariableCacheManager#getVariable(java.lang.String, boolean)
	 */
	@Override
	@Transactional(TransactionType.SINGLETON)
	public Object getVariable(String key,boolean remove) throws Exception{
		Object obj;		
		try {
			Object cacheObj = storeManager.getDataStore().get(VariableCache.class, key);
			if(cacheObj instanceof VariableCache){
				obj = serializer
						.toObject(((VariableCache)cacheObj).getAppData());
				if(remove)
					storeManager.getDataStore().delete(cacheObj);
			} else
				obj=null;
		} catch(Exception e){
			throw new Exception(e);
		}
		return obj;
	}
	
}
