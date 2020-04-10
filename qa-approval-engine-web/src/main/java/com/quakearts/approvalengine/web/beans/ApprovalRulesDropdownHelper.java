package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.context.FacesContext;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.approvalengine.model.ApprovalRules;

@Dependent
public class ApprovalRulesDropdownHelper extends BaseBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -887976755080189631L;

	@Inject
	private transient ApprovalRulesFinder finder;
	
	private List<ApprovalRules> foundItems;
	
	public List<ApprovalRules> getFoundItems() {
    	return foundItems;
    }
    	
	private ApprovalRules approvalRules;
	
	public ApprovalRules getApprovalRules(){
		if(approvalRules == null){
			approvalRules = new ApprovalRules();
			addToFoundItemsList(approvalRules);
		}
		
		return approvalRules;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ApprovalRules rules) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(rules);
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
