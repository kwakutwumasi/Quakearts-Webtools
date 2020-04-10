package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.ApprovalRules;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;
@Singleton
public class ApprovalRulesFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<ApprovalRules> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(ApprovalRules.class).using(criteria).thenList();
	}

	public ApprovalRules getById(int id){
		return dataStoreFactory.getDataStore().get(ApprovalRules.class, id);
	}

	public List<ApprovalRules> filterByText(String searchString){
		return dataStoreFactory.getDataStore().find(ApprovalRules.class)
												.using(createCriteria()
													.property("name").mustBeLike(searchString)
												.finish())
											.thenList();
	}
}
