package com.quakearts.approvalengine.web.beans;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.enterprise.context.Dependent;
import com.quakearts.approvalengine.model.Approval;

@Dependent
public class ApprovalDropdownHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2503213390925373332L;

	@Inject
	private transient ApprovalFinder finder;
	
	private List<Approval> foundItems;
	
	public List<Approval> getFoundItems() {
		if(foundItems == null)
			foundItems = finder.getAll();
    	return foundItems;
    }
    	
	private Approval approval;
	
	public Approval getApproval(){
		if(approval == null){
			approval = new Approval();
			addToFoundItemsList(approval);
		}
		
		return approval;
	}
	
	private boolean inCreateMode;
	
	public boolean isInCreateMode() {
		return inCreateMode;
	}

	public void setInCreateMode(boolean inCreateMode) {
		this.inCreateMode = inCreateMode;
	}

	public void addToFoundItemsList(Approval approval) {
		if(foundItems == null)
			foundItems = new ArrayList<>();
		
		foundItems.add(approval);
	}
}
