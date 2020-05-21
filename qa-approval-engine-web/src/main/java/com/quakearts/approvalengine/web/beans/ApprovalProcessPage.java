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
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.Approval;
import com.quakearts.approvalengine.model.ApprovalGroup;

@Named("processPage")
@ViewScoped
public class ApprovalProcessPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5650031601025364808L;

	private static Logger log = Logger.getLogger(ApprovalProcessPage.class.getName());

	private ApprovalProcess process;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalProcessFinder finder;
			
	private Range startDateRange = new Range();
	private Range completeDateRange = new Range();
			
	public Range getStartDateRange() {
		return startDateRange;
	}
	
	public Range getCompleteDateRange() {
		return completeDateRange;
	}
	
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public ApprovalProcess getProcess() {
		if(process==null){
			if(hasParameter("process")){
				setProcess(finder.getById(getParameterLong("process")));
				approvalapp.setMode("read");
			} else {
	    		setProcess(new ApprovalProcess());
			}
		}
		
		return process;
	}
    	
	public void setProcess(ApprovalProcess process) {
		this.process = process;
		if(process!=null) {
			ApprovalGroup group = process.getGroup();
			if(group!=null){
				getApprovalGroupDropdownHelper().addToFoundItemsList(group);
			}
			approvals = null;
		}
	}
	
	@Inject
	private ApprovalGroupDropdownHelper approvalGroupDropdownHelper;

	public ApprovalGroupDropdownHelper getApprovalGroupDropdownHelper(){
		return approvalGroupDropdownHelper;
	}
	
	private List<ApprovalProcess> processList;
	
	public List<ApprovalProcess> getProcessList(){
		return processList;
	}
	
	public void findProcess(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(process.isApproved()){
			criteriaBuilder.property("approved").mustBeEqualTo(process.isApproved());
		}

		if(process.isComplete()){
			criteriaBuilder.property("complete").mustBeEqualTo(process.isComplete());
		}

		if(process.getCompleteDate() != null){
			criteriaBuilder.property("completeDate").mustBeEqualTo(process.getCompleteDate());
		}

		if(process.getGroup() != null){
			criteriaBuilder.property("group").mustBeEqualTo(process.getGroup());
		}

		if(process.getStartDate() != null){
			criteriaBuilder.property("startDate").mustBeEqualTo(process.getStartDate());
		}

		if(process.isValid()){
			criteriaBuilder.property("valid").mustBeEqualTo(process.isValid());
		}

		try {
			processList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval Process", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for ApprovalProcess");
		}		
	}
    	
	public void removeProcess(ActionEvent event){
		if(process!=null && processList!=null){
			processList.remove(process);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
	
	public boolean isInSearchMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("list.xhtml") 
				&& (approvalapp.getMode() == null || approvalapp.getMode().trim().isEmpty());
	}
	
	private List<Approval> approvals;
	
	public List<Approval> getApprovals() {
		if(approvals == null && process != null) {
			approvals = finder.findApprovals(process);
		}
		return approvals;
	}
}
