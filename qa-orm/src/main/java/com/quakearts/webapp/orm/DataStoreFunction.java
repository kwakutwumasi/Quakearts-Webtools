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

/**A functional interface for accessing persistent store specific interfaces to run
 * store specific code
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface DataStoreFunction {
	/**This method once called will be passed a connection object that wraps the 
	 * persistent store's interface
	 * @param con The interface wrapper
	 * @throws DataStoreException
	 */
	public void execute(DataStoreConnection con);
}
