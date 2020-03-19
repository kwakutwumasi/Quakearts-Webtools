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

import java.lang.annotation.Annotation;
import java.util.Iterator;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreHandle;
import com.quakearts.webapp.orm.cdi.annotation.RequiresTransaction;

/**Produce a {@link DataStore} instance
 * @author kwaku
 *
 */
@Singleton
public class DataStoreProducer {
	/**Produce a {@link DataStore} instance. 
	 *  If the instance is dependent on a {@link javax.transaction.Transaction Transaction},
	 *  add the {@link RequiresTransaction} annotation to inject an instance configured to synchronize with
	 *  the transaction. The instance is reset when the transaction is completed (commited or rolled back)
	 * @param injectionPoint to fine tune the injected instance
	 * @return the DataStore instance
	 */
	@Produces
	public @DataStoreHandle @RequiresTransaction DataStore getDataStore(InjectionPoint injectionPoint) {
		InjectionInfo injectionInfo = getInfo(injectionPoint);
		if(injectionInfo.isTransactional) {
			return new TransactionalDataStore(injectionInfo.domain);
		} else {
			return "".equals(injectionInfo.domain)? 
					DataStoreFactory.getInstance().getDataStore() : DataStoreFactory.getInstance().getDataStore(injectionInfo.domain);
		}
	}

	private InjectionInfo getInfo(InjectionPoint injectionPoint) {
		String domain = null;
		boolean isTransactional = false;
		Iterator<Annotation> iterator = injectionPoint.getQualifiers().iterator();
		
		while (iterator.hasNext()) {
			Annotation annotation = iterator.next();
			if(annotation instanceof DataStoreHandle){
				domain = ((DataStoreHandle)annotation).value();
			} else if(annotation instanceof RequiresTransaction) {
				isTransactional = true;
			}
		}
		return new InjectionInfo(domain, isTransactional);
	}
	
	private class InjectionInfo {
		String domain;
		boolean isTransactional;
		InjectionInfo(String domain, boolean isTransactional) {
			this.domain = domain;
			this.isTransactional = isTransactional;
		}
	}
}
