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
import com.quakearts.syshub.model.ProcessingLog;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class ProcessingLogFinder extends AbstractSysHubFinder {

	public List<ProcessingLog> findObjects(CriteriaMap criteria){
		return getDataStore().find(ProcessingLog.class).using(criteria)
				.thenList();
	}
	
	public ProcessingLog getById(long id){
		return getDataStore().get(ProcessingLog.class,id);
	}
	
	public List<ProcessingLog> filterByText(String searchString){
		return getDataStore().find(ProcessingLog.class).using(createCriteria()
					.requireAnyOfTheFollowing()
					.property("agentConfiguration.agentName").mustBeLike(searchString)
					.property("agentModule.moduleName").mustBeLike(searchString)
					.property("agentModule.moduleClassName").mustBeLike(searchString)
					.property("mid").mustBeLike(searchString)
					.property("recipient").mustBeLike(searchString)
					.property("statusMessage").mustBeLike(searchString)
					.finish()).thenList();	
		}
}
