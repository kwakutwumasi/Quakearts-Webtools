package com.quakearts.approvalengine.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import com.quakearts.approvalengine.model.ApprovalRule;

@Dependent
public class ApprovalRuleDropdownHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9047284294694176809L;

	@Inject
	private transient ApprovalRuleFinder finder;
	
	private List<ApprovalRule> foundItems;
	
	public List<ApprovalRule> getFoundItems() {
		if(foundItems == null)
			foundItems = finder.getAll();
    	return foundItems;
    }
    	
	private ApprovalRule approvalRule;
	
	public ApprovalRule getApprovalRule(){
		if(approvalRule == null){
			approvalRule = new ApprovalRule();
			addToFoundItemsList(approvalRule);
		}
		
		return approvalRule;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(ApprovalRule rule) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(rule);
	}
}
