package com.quakearts.syshub.webapp.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;

public class AgentConfigurationModuleMappingFinder {	

	public List<AgentConfigurationModuleMapping> findObjects(Map<String, Serializable> parameters,QueryOrder...queryOrders){
		return DataStoreFactory.getInstance().getDataStore().list(AgentConfigurationModuleMapping.class, parameters, queryOrders);
	}
	public List<AgentConfigurationModuleMapping> getAll(){
		return DataStoreFactory.getInstance().getDataStore().list(AgentConfigurationModuleMapping.class, null);
	}
}
