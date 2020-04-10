package com.quakearts.approvalengine.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import com.quakearts.approvalengine.model.ApprovalProcessRules;

@Dependent
public class ApprovalProcessRulesDropdownHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4878829017672166883L;

	@Inject
	private transient ApprovalProcessRulesFinder finder;
	
	private List<ApprovalProcessRules> foundItems;
	
	public List<ApprovalProcessRules> getFoundItems() {
		if(foundItems == null)
			foundItems = finder.getAll();
    	return foundItems;
    }
    	
	private ApprovalProcessRules approvalProcessRules;
	
	public ApprovalProcessRules getApprovalProcessRules(){
		if(approvalProcessRules == null){
			approvalProcessRules = new ApprovalProcessRules();
			addToFoundItemsList(approvalProcessRules);
		}
		
		return approvalProcessRules;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ApprovalProcessRules processRules) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(processRules);
	}
}
