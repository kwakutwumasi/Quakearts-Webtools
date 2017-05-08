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
package com.quakearts.webapp.hibernate;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;

public class HibernateSessionDataStoreFactory extends DataStoreFactory {
	
	public HibernateSessionDataStoreFactory() {
		setInstance(this);
	}
	
	@Override
	public DataStore getDataStore() {
		return new HibernateSessionDataStore();
	}
	
	@Override
	public DataStore getDataStore(String domain) {
		return new HibernateSessionDataStore(domain);
	}

}
