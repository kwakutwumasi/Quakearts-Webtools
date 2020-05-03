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
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;

import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class AgentModuleFinder extends AbstractSysHubFinder {

	public List<AgentModule> findObjects(CriteriaMap criteria){
		return getDataStore().find(AgentModule.class).using(criteria).thenList();
	}
	
	public AgentModule getById(int id){
		return getDataStore().get(AgentModule.class,id);
	}
	
	public List<AgentModule> filterByText(String searchString){
		searchString = "%"+searchString+"%";
		return getDataStore().find(AgentModule.class).using(createCriteria().
				requireAnyOfTheFollowing()
					.property("agentClassName").mustBeLike(searchString)
					.property("mappedModuleName").mustBeLike(searchString)
					.property("moduleName").mustBeLike(searchString)
				.finish()).thenList();
	}

	public List<AgentModule> filterMessageFormattersByText(String searchString) {
		return getDataStore().find(AgentModule.class).using(createCriteria().
				requireAnyOfTheFollowing()
				.property("agentClassName").mustBeLike(searchString)
				.property("mappedModuleName").mustBeLike(searchString)
				.property("moduleName").mustBeLike(searchString)
				.property("moduleType").mustBeEqualTo(ModuleType.FORMATTER)
			.finish()).thenList();
	}

	public List<AgentModule> getAll() {
		return getDataStore().list(AgentModule.class);
	}
}
