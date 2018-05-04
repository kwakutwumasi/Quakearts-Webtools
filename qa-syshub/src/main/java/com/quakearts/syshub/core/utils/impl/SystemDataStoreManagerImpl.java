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

import javax.inject.Singleton;

import com.quakearts.syshub.core.utils.SystemDataStoreManager;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

@Singleton
public class SystemDataStoreManagerImpl implements SystemDataStoreManager {	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.SystemDataStoreManager#getSystemDataStore()
	 */
	@Override
	public DataStore getDataStore() {
		return DataStoreFactory.getInstance().getDataStore("system");
	}
}
