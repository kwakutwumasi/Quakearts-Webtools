package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.Approver;

@Named("approvalPage")
@ViewScoped
public class ApprovalPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4555141199221724091L;

	private static Logger log = Logger.getLogger(ApprovalPage.class.getName());

	private Approval approval;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalFinder finder;
	
	private Range approvalDateRange = new Range();
	
	public Range getApprovalDateRange() {
		return approvalDateRange;
	}
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public Approval getApproval() {
		if(approval==null){
			if(hasParameter("approval")){
				setApproval(finder.getById(getParameterLong("approval")));
				approvalapp.setMode("read");
			} else {
	    		setApproval(new Approval());
			}
		}
		
		return approval;
	}
    	
	public void setApproval(Approval approval) {
		this.approval = approval;
		if(approval!=null) {
			ApprovalProcess approvalProcess = approval.getApprovalProcess();
			if(approvalProcess!=null){
				getApprovalProcessDropdownHelper().addToFoundItemsList(approvalProcess);
			}

			Approver approver = approval.getApprover();
			if(approver!=null){
				getApproverDropdownHelper().addToFoundItemsList(approver);
			}
		}
	}
	
	@Inject
	private ApprovalProcessDropdownHelper approvalProcessDropdownHelper;

	public ApprovalProcessDropdownHelper getApprovalProcessDropdownHelper(){
		return approvalProcessDropdownHelper;
	}
	
	@Inject
	private ApproverDropdownHelper approverDropdownHelper;

	public ApproverDropdownHelper getApproverDropdownHelper(){
		return approverDropdownHelper;
	}
	
	private List<Approval> approvalList;
	
	public List<Approval> getApprovalList(){
		return approvalList;
	}
	
	public void findApproval(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(approval.getAction() != null){
			criteriaBuilder.property("action").mustBeEqualTo(approval.getAction());
		}

		if(!approvalDateRange.isEmpty()){
			criteriaBuilder.property("approvalDate").mustBeEqualTo(approvalDateRange);
		}

		if(approval.getApprovalProcess() != null){
			criteriaBuilder.property("approvalProcess").mustBeEqualTo(approval.getApprovalProcess());
		}

		if(approval.getApprover() != null){
			criteriaBuilder.property("approver").mustBeEqualTo(approval.getApprover());
		}

		if(approval.isValid()){
			criteriaBuilder.property("valid").mustBeEqualTo(approval.isValid());
		}

		try {
			approvalList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for Approval");
		}		
	}
    	
	public void removeApproval(ActionEvent event){
		if(approval!=null && approvalList!=null){
			approvalList.remove(approval);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
	
	public boolean isInSearchMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("list.xhtml") 
				&& approvalapp.getMode() == null || approvalapp.getMode().trim().isEmpty();
	}
}
