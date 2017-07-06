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
package com.quakearts.syshub.webapp.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.syshub.model.MaxID;
import static com.quakearts.webapp.orm.query.helper.ParameterMapBuilder.createParameters;

public class MaxIDFinder extends AbstractSysHubFinder {
	public MaxIDFinder(){
		super();
	}

	public List<MaxID> findObjects(Map<String, Serializable> parameters,QueryOrder...queryOrders){
		return getDataStore().list(MaxID.class, parameters, queryOrders);
	}
	public MaxID getById(int id){
		return getDataStore().get(MaxID.class,id);
	}
	public List<MaxID> filterByText(String searchString){
		return getDataStore().list(MaxID.class, createParameters()
					.addVariableString("maxIDName", searchString)
					.build());	}
}
