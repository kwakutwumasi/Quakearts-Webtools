package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.Approver;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;
@Singleton
public class ApproverFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<Approver> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(Approver.class).using(criteria).thenList();
	}

	public Approver getById(long id){
		return dataStoreFactory.getDataStore().get(Approver.class, id);
	}

	public List<Approver> filterByText(String searchString){
		return dataStoreFactory.getDataStore().find(Approver.class)
												.using(createCriteria()
													.property("externalId").mustBeLike(searchString)
												.finish())
											.thenList();
	}
}
