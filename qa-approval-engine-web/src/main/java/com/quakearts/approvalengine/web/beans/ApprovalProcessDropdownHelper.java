package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.webapp.facelets.base.BaseBean;

@Dependent
public class ApprovalProcessDropdownHelper extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7872035025144584456L;

	@Inject
	private transient ApprovalProcessFinder finder;
	
	private List<ApprovalProcess> foundItems;
	
	public List<ApprovalProcess> getFoundItems() {
    	return foundItems;
    }
    	
	private ApprovalProcess approvalProcess;
	
	public ApprovalProcess getApprovalProcess(){
		if(approvalProcess == null){
			approvalProcess = new ApprovalProcess();
			addToFoundItemsList(approvalProcess);
		}
		
		return approvalProcess;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ApprovalProcess process) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(process);
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
