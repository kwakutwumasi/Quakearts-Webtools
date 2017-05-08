/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core.utils;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;

import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.syshub.model.VariableCache;
import com.quakearts.webapp.orm.DataStore;

@ApplicationScoped
public class VariableCacheUtil {
	VariableCacheUtil() {
	}
	
	private SerializationUtil serializationUtil = SerializationUtil.getInstance();
	private SystemDataStoreUtils systemDataStoreUtils = SystemDataStoreUtils.getInstance();
	
	private static VariableCacheUtil instance;
	
	public static VariableCacheUtil getInstance() {
		if(instance == null){
			instance = CDI.current().select(VariableCacheUtil.class).get();
		}
		return instance;
	}
	
	@TransactionParticipant
	public void storeVariable(String key, Serializable obj) throws Exception{
		try{
			VariableCache cache = new VariableCache();
			cache.setAppKey(key);
			cache.setAppData(serializationUtil.toByteArray(obj));
			
			systemDataStoreUtils.getSystemDataStore().save(cache);
		} catch(Exception e){
			throw new Exception(e);
		}		
	}

	@TransactionParticipant
	public void updateVariable(String key, Serializable obj) throws Exception{
		try{
			DataStore systemDataStore = systemDataStoreUtils.getSystemDataStore();
			VariableCache cache = (VariableCache) systemDataStore.get(VariableCache.class, key);
			if(cache !=null){
				cache.setAppData(serializationUtil.toByteArray(obj));
				systemDataStore.update(cache);
			}else{
				storeVariable(key, obj);
			}
		}catch(Exception e){
			throw new Exception(e);
		}
	}

	
	@TransactionParticipant
	public Object getVariable(String key,boolean remove) throws Exception{
		Object obj;		
		try {
			Object cacheObj = systemDataStoreUtils.getSystemDataStore().get(VariableCache.class, key);
			if(cacheObj instanceof VariableCache){
				obj = serializationUtil
						.toObject(((VariableCache)cacheObj).getAppData());
				if(remove)
					systemDataStoreUtils.getSystemDataStore().delete(cacheObj);
			} else
				obj=null;
		} catch(Exception e){
			throw new Exception(e);
		}
		return obj;
	}
	
}
