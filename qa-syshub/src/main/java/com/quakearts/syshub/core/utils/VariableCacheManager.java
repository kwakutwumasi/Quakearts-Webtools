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
package com.quakearts.syshub.core.utils;

import java.io.Serializable;

/**Interface for classes that provide persistent storage 
 * @author kwakutwumasi-afriyie
 *
 */
public interface VariableCacheManager {
	/**Save a variable to persistent storage
	 * @param key the key to use for retrieval
	 * @param obj the object to save
	 * @throws Exception if the manager is unable to store the variable
	 */
	void storeVariable(String key, Serializable obj);
	/**Update a variable in persistent storage
	 * @param key the key to use for retrieval
	 * @param obj the object to update
	 * @throws Exception if the manager is unable to update the variable
	 */
	void updateVariable(String key, Serializable obj);
	/**Retrieve the variable stored with the key
	 * @param key the key to use for retrieval
	 * @param remove indicates that the variable should be removed after retrieval
	 * @return the object saved by this key if any
	 * @throws Exception if the manager is unable to retrieve the variable
	 */
	Object getVariable(String key, boolean remove);
}