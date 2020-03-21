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
import com.quakearts.syshub.model.AgentConfigurationParameter;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class AgentConfigurationParameterFinder extends AbstractSysHubFinder {

	public List<AgentConfigurationParameter> findObjects(CriteriaMap criteria){
		return getDataStore().find(AgentConfigurationParameter.class)
				.using(criteria).thenList();
	}
	
	public AgentConfigurationParameter getById(int id){
		return getDataStore().get(AgentConfigurationParameter.class,id);
	}
	
	public List<AgentConfigurationParameter> filterByText(String searchString){
		return getDataStore().find(AgentConfigurationParameter.class).using(createCriteria()
					.requireAnyOfTheFollowing()
					.property("name").mustBeLike(searchString)
					.property("stringValue").mustBeLike(searchString)
					.finish()).thenList();
	}
}
