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
import com.quakearts.approvalengine.model.ApprovalRules;
import com.quakearts.approvalengine.model.ApprovalGroup;

@Named("rulesPage")
@ViewScoped
public class ApprovalRulesPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4010050558189618436L;

	private static Logger log = Logger.getLogger(ApprovalRulesPage.class.getName());

	private ApprovalRules rules;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalRulesFinder finder;
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public ApprovalRules getRules() {
		if(rules==null){
			if(hasParameter("rules")){
				setRules(finder.getById(getParameterInt("rules")));
				approvalapp.setMode("read");
			} else {
	    		setRules(new ApprovalRules());
			}
		}
		
		return rules;
	}
    	
	public void setRules(ApprovalRules rules) {
		this.rules = rules;
		if(rules!=null) {
			ApprovalGroup group = rules.getGroup();
			if(group!=null){
				getApprovalGroupDropdownHelper().addToFoundItemsList(group);
			}
		}
	}
	
	@Inject
	private ApprovalGroupDropdownHelper approvalGroupDropdownHelper;

	public ApprovalGroupDropdownHelper getApprovalGroupDropdownHelper(){
		return approvalGroupDropdownHelper;
	}
	
	private List<ApprovalRules> rulesList;
	
	public List<ApprovalRules> getRulesList(){
		return rulesList;
	}
	
	public void findRules(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(rules.isActive()){
			criteriaBuilder.property("active").mustBeEqualTo(rules.isActive());
		}

		if(rules.getGroup() != null){
			criteriaBuilder.property("group").mustBeEqualTo(rules.getGroup());
		}

		if(rules.getName() != null && ! rules.getName().trim().isEmpty()){
			criteriaBuilder.property("name").mustBeLike(rules.getName());
		}

		if(rules.getPriority() != 0){
			criteriaBuilder.property("priority").mustBeEqualTo(rules.getPriority());
		}

		try {
			rulesList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval Rules", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for ApprovalRules");
		}		
	}
    	
	public void removeRules(ActionEvent event){
		if(rules!=null && rulesList!=null){
			rulesList.remove(rules);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
}
