package com.quakearts.approvalengine.web.beans;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.cdi.annotation.DataStoreFactoryHandle;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.approvalengine.model.ApprovalProcess;
@Singleton
public class ApprovalProcessFinder {	

	@Inject @DataStoreFactoryHandle
	private DataStoreFactory dataStoreFactory;

	public List<ApprovalProcess> findObjects(CriteriaMap criteria){
		return dataStoreFactory.getDataStore().find(ApprovalProcess.class).using(criteria).thenList();
	}

	public ApprovalProcess getById(long id){
		return dataStoreFactory.getDataStore().get(ApprovalProcess.class, id);
	}

	public List<ApprovalProcess> filterByText(String suggestion) {
		if(suggestion.matches("[\\d]+:.+")){
			String[] parts = suggestion.split(":", 2);
			Long id = new Long(parts[0]);
			String groupSuggestion = parts[1];
			return dataStoreFactory.getDataStore().find(ApprovalProcess.class)
					.filterBy("id").withAValueEqualTo(id)
					.filterBy("group.name").withAValueLike(groupSuggestion+"%")
					.thenList();
		} else {
			return dataStoreFactory.getDataStore().find(ApprovalProcess.class)
					.filterBy("group.name").withAValueLike(suggestion+"%")
					.thenList();
		}
	}
}
