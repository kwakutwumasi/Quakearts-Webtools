package com.quakearts.approvalengine.web.beans;

import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.approvalengine.model.ApprovalProcessRules;
import com.quakearts.approvalengine.model.ApprovalProcess;
import com.quakearts.approvalengine.model.ApprovalRules;

@Named("processRulesPage")
@ViewScoped
public class ApprovalProcessRulesPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9032739875036424678L;

	private static Logger log = Logger.getLogger(ApprovalProcessRulesPage.class.getName());

	private ApprovalProcessRules processRules;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalProcessRulesFinder finder;
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public ApprovalProcessRules getProcessRules() {
		if(processRules==null){    		setProcessRules(new ApprovalProcessRules());
		}
		
		return processRules;
	}
    	
	public void setProcessRules(ApprovalProcessRules processRules) {
		this.processRules = processRules;
		if(processRules!=null) {
			ApprovalProcess approvalProcess = processRules.getApprovalProcess();
			if(approvalProcess!=null){
				getApprovalProcessDropdownHelper().addToFoundItemsList(approvalProcess);
			}

			ApprovalRules approvalRules = processRules.getApprovalRules();
			if(approvalRules!=null){
				getApprovalRulesDropdownHelper().addToFoundItemsList(approvalRules);
			}
		}
	}
	
	@Inject
	private ApprovalProcessDropdownHelper approvalProcessDropdownHelper;

	public ApprovalProcessDropdownHelper getApprovalProcessDropdownHelper(){
		return approvalProcessDropdownHelper;
	}
	
	@Inject
	private ApprovalRulesDropdownHelper approvalRulesDropdownHelper;

	public ApprovalRulesDropdownHelper getApprovalRulesDropdownHelper(){
		return approvalRulesDropdownHelper;
	}
	
	private List<ApprovalProcessRules> processRulesList;
	
	public List<ApprovalProcessRules> getProcessRulesList(){
		return processRulesList;
	}
	
	public void findProcessRules(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(processRules.isActive()){
			criteriaBuilder.property("active").mustBeEqualTo(processRules.isActive());
		}

		if(processRules.getApprovalProcess() != null){
			criteriaBuilder.property("approvalProcess").mustBeEqualTo(processRules.getApprovalProcess());
		}

		if(processRules.getApprovalProcessId() != 0){
			criteriaBuilder.property("approvalProcessId").mustBeEqualTo(processRules.getApprovalProcessId());
		}

		if(processRules.getApprovalRules() != null){
			criteriaBuilder.property("approvalRules").mustBeEqualTo(processRules.getApprovalRules());
		}

		if(processRules.getApprovalRulesId() != 0){
			criteriaBuilder.property("approvalRulesId").mustBeEqualTo(processRules.getApprovalRulesId());
		}

		try {
			processRulesList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval Process Rules", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for ApprovalProcessRules");
		}		
	}
    	
	public void removeProcessRules(ActionEvent event){
		if(processRules!=null && processRulesList!=null){
			processRulesList.remove(processRules);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
}
