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
import com.quakearts.syshub.model.ResultExceptionLog;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class ResultExceptionLogFinder extends AbstractSysHubFinder {	
	public List<ResultExceptionLog> findObjects(CriteriaMap criteria){
		return getDataStore().find(ResultExceptionLog.class).using(criteria)
				.thenList();
	}
	
	public ResultExceptionLog getById(long id){
		return getDataStore().get(ResultExceptionLog.class,id);
	}
	
	public List<ResultExceptionLog> filterByText(String searchString){
		return getDataStore().find(ResultExceptionLog.class).using(createCriteria().
				requireAnyOfTheFollowing()
					.property("exceptionType").mustBeLike(searchString)
					.property("spoolerType").mustBeLike(searchString)
					.finish()).thenList();	
	}
}
