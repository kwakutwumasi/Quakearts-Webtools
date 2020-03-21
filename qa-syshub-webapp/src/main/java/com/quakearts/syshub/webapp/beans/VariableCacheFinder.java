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
import com.quakearts.syshub.model.VariableCache;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class VariableCacheFinder extends AbstractSysHubFinder {	

	public List<VariableCache> findObjects(CriteriaMap criteria){
		return getDataStore().find(VariableCache.class).using(criteria)
				.thenList();
	}
	
	public VariableCache getById(String id){
		return getDataStore().get(VariableCache.class,id);
	}
	
	public List<VariableCache> filterByText(String searchString){
		return getDataStore().find(VariableCache.class).using(createCriteria()
					.property("appKey").mustBeLike(searchString)
					.finish())
				.thenList();	
	}
}
