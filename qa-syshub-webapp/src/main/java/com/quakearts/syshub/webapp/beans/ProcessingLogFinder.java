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
import com.quakearts.syshub.model.ProcessingLog;
import static com.quakearts.webapp.orm.query.helper.ParameterMapBuilder.createParameters;

public class ProcessingLogFinder extends AbstractSysHubFinder {
	public ProcessingLogFinder(){
		super();
	}

	public List<ProcessingLog> findObjects(Map<String, Serializable> parameters,QueryOrder...queryOrders){
		return getDataStore().list(ProcessingLog.class, parameters, queryOrders);
	}
	public ProcessingLog getById(long id){
		return getDataStore().get(ProcessingLog.class,id);
	}
	public List<ProcessingLog> filterByText(String searchString){
		return getDataStore().list(ProcessingLog.class, createParameters()
					.disjoin()
					.addVariableString("agentConfiguration.agentName", searchString)
					.addVariableString("agentModule.moduleName", searchString)
					.addVariableString("agentModule.agentClassName", searchString)
					.addVariableString("mid", searchString)
					.addVariableString("recipient", searchString)
					.addVariableString("statusMessage", searchString)
					.build());	}
}
