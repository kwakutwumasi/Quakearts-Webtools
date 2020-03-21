package com.quakearts.syshub.webapp.beans;

import java.util.List;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;

public class AgentConfigurationModuleMappingFinder {	

	public List<AgentConfigurationModuleMapping> findObjects(CriteriaMap criteria){
		return DataStoreFactory.getInstance().getDataStore().find(AgentConfigurationModuleMapping.class)
				.using(criteria).thenList();
	}
	
	public List<AgentConfigurationModuleMapping> getAll(){
		return DataStoreFactory.getInstance().getDataStore().list(AgentConfigurationModuleMapping.class);
	}
}
