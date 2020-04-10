package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.FacesContext;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.approvalengine.model.Approver;

@Dependent
public class ApproverDropdownHelper extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4314254657254704560L;

	@Inject
	private transient ApproverFinder finder;
	
	private List<Approver> foundItems;
	
	public List<Approver> getFoundItems() {
    	return foundItems;
    }
    	
	private Approver approver;
	
	public Approver getApprover(){
		if(approver == null){
			approver = new Approver();
			addToFoundItemsList(approver);
		}
		
		return approver;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(Approver approver) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(approver);
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
