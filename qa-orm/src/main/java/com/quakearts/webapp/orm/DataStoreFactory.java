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

import java.util.Iterator;
import java.util.ServiceLoader;

import com.quakearts.webapp.orm.exception.DataStoreException;

/**Factory method for obtaining instances of an interface to the persistent store
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class DataStoreFactory {
	private static DataStoreFactory instance;

	/**Get the singleton factory instance
	 * @return The current {@link DataStoreFactory}. Only one will exist per classloader
	 */
	public static DataStoreFactory getInstance() {
		if(instance == null){
			Iterator<DataStoreFactory> dataStoreFactoryIterator = 
					ServiceLoader.load(DataStoreFactory.class, DataStoreFactory.class.getClassLoader()).iterator();
			
			while (dataStoreFactoryIterator.hasNext()) {//a run
				dataStoreFactoryIterator.next();
			}
		}
		
		return instance;
	}

	/**Use this method to set this {@link DataStoreFactory} as the default.
	 * If an instance has already been set, a {@link DataStoreException} will be thrown
	 * @param instance 
	 */
	protected static void setInstance(DataStoreFactory instance) {
		if(DataStoreFactory.instance == null)
			DataStoreFactory.instance = instance;
		else
			throw new DataStoreException("A factory has already been set: "
					+ DataStoreFactory.instance.getClass().getName()
					+ ". Cannot replace it with: "
					+ instance.getClass().getName());
	}

	/** Get an interface to the persistent store
	 * @return the interface
	 */
	public abstract DataStore getDataStore();
	/** Get an interface to the persistent store with a name equal to the passed in storename
	 * @param storename the name of the specific store to load
	 * @return the interface
	 */
	public abstract DataStore getDataStore(String storename);
}
