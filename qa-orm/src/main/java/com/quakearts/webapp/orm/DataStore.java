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
import com.quakearts.webapp.orm.query.ListBuilder;
import com.quakearts.webapp.orm.query.QueryOrder;

/** An abstraction of object storage technologies. Created to hide the complexities of the many object-database
 * techonolgies available. One API to interface with any data store.
 * @author kwakutwumasi-afriyie
 *
 */
public interface DataStore {
	/**Save and object to the persistent data store
	 * @param object the object to store
	 * @throws DataStoreException
	 */
	void save(Object object) throws DataStoreException;
	/**Retrieve a single object identified by the id
	 * @param clazz The type of object to return
	 * @param id The id of the object in the persistent data store
	 * @return an instance of the class
	 * @throws DataStoreException
	 */
	<Entity> Entity get(Class<Entity> clazz, Serializable id) throws DataStoreException;
	/**Update the object in the persistent data store
	 * @param object The object to update
	 * @throws DataStoreException
	 */
	void update(Object object) throws DataStoreException;
	/**Delete the object in the persistent data store
	 * @param object The object to delete
	 * @throws DataStoreException
	 */
	void delete(Object object) throws DataStoreException;
	/** A low level method for listing objects using complex selection criteria
	 * @param clazz the kind of entities to return
	 * @param parameters a special map containing the parameters, 
	 * 	their values, and the kind of selection criteria to use
	 * @param orders A list of values used to order the returned list.
	 * @return A {@link List} of object instances of the class
	 * @throws DataStoreException
	 */
	<Entity> List<Entity> list(Class<Entity> clazz, Map<String, Serializable> parameters, QueryOrder... orders) throws DataStoreException;
	/**Returns all objects of the specified class in the persistent store
	 * @param clazz
	 * @return
	 */
	default <Entity> List<Entity> list(Class<Entity> clazz){
		return list(clazz, null);
	}
	/**A fluid API for defining the selection criteria
	 * @param clazz
	 * @return A {@link ListBuilder} used to define the selection criteria
	 */
	default <Entity> ListBuilder<Entity> find(Class<Entity> clazz){
		return new ListBuilder<>(this, clazz);
	}
	/**A useful method for saving objects that may or may not already exist in the persistent store
	 * @param object to save or update
	 * @throws DataStoreException
	 */
	void saveOrUpdate(Object object) throws DataStoreException;
	
	/**Sometimes an object may be out of sync with the persistent store.
	 * This method returns a freshly synchronized instance of the object. Replace the 
	 * object with the returned object
	 * @param object The freshly synchronized object
	 * @return
	 * @throws DataStoreException
	 */
	<Entity> Entity refresh(Entity object) throws DataStoreException;
	/**Some persistent stores hold objects in a buffer and only commit them to the store 
	 * when it is most efficient to do so. This method forces items held in the buffer 
	 * into the persistent store
	 * @throws DataStoreException
	 */
	void flushBuffers() throws DataStoreException;
	/**Some data stores require special operations beyond the scope of this API
	 * These operations can be executed by special {@link DataStoreFunction}s
	 * @param function
	 * @throws DataStoreException
	 */
	void executeFunction(DataStoreFunction function) throws DataStoreException;
	/**Fetch a property used to configure the data store
	 * @param propertyName
	 * @return The property, if any.
	 */
	String getConfigurationProperty(String propertyName);
}
