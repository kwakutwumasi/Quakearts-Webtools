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

import java.util.List;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.syshub.model.MaxID;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class MaxIDFinder extends AbstractSysHubFinder {

	public List<MaxID> findObjects(CriteriaMap criteria){
		return getDataStore().find(MaxID.class).using(criteria).thenList();
	}
	
	public MaxID getById(int id){
		return getDataStore().get(MaxID.class,id);
	}
	
	public List<MaxID> filterByText(String searchString){
		return getDataStore().find(MaxID.class).using(createCriteria()
					.property("maxIDName").mustBeLike(searchString)
					.finish())
				.thenList();	
	}
}
