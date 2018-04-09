/*******************************************************************************
* Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.orm;

import com.quakearts.webapp.orm.exception.DataStoreException;

/**A functional interface for special data store functions
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface DataStoreConnection {
	/**Return a special connection object used to interface with the data store
	 * @param expectedConnection the class of the expected connectiob
	 * @return an instance of the expected connection, if any
	 * @throws DataStoreException
	 */
	<Connection> Connection getConnection(Class<Connection> expectedConnection) throws DataStoreException;
}
