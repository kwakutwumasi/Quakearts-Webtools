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

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class SystemDataStoreUtils {
	private SystemDataStoreUtils() {
	}

	private static final SystemDataStoreUtils instance = new SystemDataStoreUtils();
	
	public static SystemDataStoreUtils getInstance() {
		return instance;
	}
	
	public DataStore getSystemDataStore() {
		return DataStoreFactory.getInstance().getDataStore("system");
	}
}
