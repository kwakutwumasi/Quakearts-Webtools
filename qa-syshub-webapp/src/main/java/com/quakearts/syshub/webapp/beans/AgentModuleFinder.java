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
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.AgentModule.ModuleType;

import static com.quakearts.webapp.orm.query.helper.ParameterMapBuilder.createParameters;

public class AgentModuleFinder extends AbstractSysHubFinder {
	public AgentModuleFinder(){
		super();
	}

	public List<AgentModule> findObjects(Map<String, Serializable> parameters,QueryOrder...queryOrders){
		return getDataStore().list(AgentModule.class, parameters, queryOrders);
	}
	public AgentModule getById(int id){
		return getDataStore().get(AgentModule.class,id);
	}
	public List<AgentModule> filterByText(String searchString){
		return getDataStore().list(AgentModule.class, createParameters().disjoin()
					.addVariableString("agentClassName", searchString)
					.addVariableString("mappedModuleName", searchString)
					.addVariableString("moduleName", searchString)
					.build());	
	}

	public List<AgentModule> filterMessageFormattersByText(String searchString) {
		return getDataStore().list(AgentModule.class, createParameters().disjoin()
				.addVariableString("agentClassName", searchString)
				.addVariableString("mappedModuleName", searchString)
				.addVariableString("moduleName", searchString)
				.add("moduleType", ModuleType.FORMATTER)
				.build());
	}
}
