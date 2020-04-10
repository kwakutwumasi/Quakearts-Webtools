package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.FacesContext;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.approvalengine.model.ApprovalGroup;

@Dependent
public class ApprovalGroupDropdownHelper extends BaseBean {
	
	/** 
	 * 
	 */
	private static final long serialVersionUID = -9140955941146915907L;

	@Inject
	private transient ApprovalGroupFinder finder;
	
	private List<ApprovalGroup> foundItems;
	
	public List<ApprovalGroup> getFoundItems() {
    	return foundItems;
    }
    	
	private ApprovalGroup approvalGroup;
	
	public ApprovalGroup getApprovalGroup(){
		if(approvalGroup == null){
			approvalGroup = new ApprovalGroup();
			addToFoundItemsList(approvalGroup);
		}
		
		return approvalGroup;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ApprovalGroup group) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(group);
	}

	private String searchText;
	
	public String getSearchText() {
		return searchText;
	}
	
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
	public void filterItems(AjaxBehaviorEvent event){
		if(searchText != null && !searchText.trim().isEmpty()){
			foundItems = finder.filterByText(searchText+"%");
			
			if(foundItems.isEmpty()) {
				addMessage("Not found","The search string returned no results",
					FacesContext.getCurrentInstance(), event.getComponent().getClientId());
			}
		}
	}
}
