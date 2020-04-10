package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.ApprovalRule;
@Singleton
public class ApprovalRuleFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<ApprovalRule> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(ApprovalRule.class).using(criteria).thenList();
	}

	public ApprovalRule getById(int id){
		return dataStoreFactory.getDataStore().get(ApprovalRule.class, id);
	}

	public List<ApprovalRule> getAll(){
		return dataStoreFactory.getDataStore().list(ApprovalRule.class);
	}
}
