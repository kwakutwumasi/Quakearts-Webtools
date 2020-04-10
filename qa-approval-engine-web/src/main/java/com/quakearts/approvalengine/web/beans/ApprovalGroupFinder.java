package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.ApprovalGroup;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;
@Singleton
public class ApprovalGroupFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<ApprovalGroup> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(ApprovalGroup.class).using(criteria).thenList();
	}

	public ApprovalGroup getById(long id){
		return dataStoreFactory.getDataStore().get(ApprovalGroup.class, id);
	}

	public List<ApprovalGroup> filterByText(String searchString){
		return dataStoreFactory.getDataStore().find(ApprovalGroup.class)
												.using(createCriteria()
													.property("name").mustBeLike(searchString)
												.finish())
											.thenList();
	}
}
