package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.Approval;
@Singleton
public class ApprovalFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<Approval> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(Approval.class).using(criteria).thenList();
	}

	public Approval getById(long id){
		return dataStoreFactory.getDataStore().get(Approval.class, id);
	}

	public List<Approval> getAll(){
		return dataStoreFactory.getDataStore().list(Approval.class);
	}
}
