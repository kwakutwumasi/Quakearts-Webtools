package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.ApprovalProcessRules;
@Singleton
public class ApprovalProcessRulesFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<ApprovalProcessRules> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(ApprovalProcessRules.class).using(criteria).thenList();
	}

	public List<ApprovalProcessRules> getAll(){
		return dataStoreFactory.getDataStore().list(ApprovalProcessRules.class);
	}
}
