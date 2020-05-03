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
import com.quakearts.syshub.model.AgentConfiguration;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class AgentConfigurationFinder extends AbstractSysHubFinder {	

	public List<AgentConfiguration> findObjects(CriteriaMap criteria){
		return getDataStore().find(AgentConfiguration.class).using(criteria).thenList();
	}
	
	public AgentConfiguration getById(int id){
		return getDataStore().get(AgentConfiguration.class,id);
	}
	
	public List<AgentConfiguration> filterByText(String searchString){
		return getDataStore().find(AgentConfiguration.class).using(createCriteria()
					.property("agentName").mustBeLike("%"+searchString+"%")
					.finish()).thenList();
	}

	public List<AgentConfiguration> findByName(String searchString){
		return getDataStore().find(AgentConfiguration.class).using(createCriteria()
				.property("agentName").mustBeEqualTo(searchString)
				.finish()).thenList();
	}

	public List<AgentConfiguration> getAll() {
		return getDataStore().list(AgentConfiguration.class);
	}
}
