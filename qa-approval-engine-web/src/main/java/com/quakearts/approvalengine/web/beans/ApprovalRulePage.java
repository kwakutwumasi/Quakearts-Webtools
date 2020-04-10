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
import com.quakearts.approvalengine.model.ApprovalRule;
import com.quakearts.approvalengine.model.ApprovalRules;

@Named("rulePage")
@ViewScoped
public class ApprovalRulePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4144907656646991116L;

	private static Logger log = Logger.getLogger(ApprovalRulePage.class.getName());

	private ApprovalRule rule;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalRuleFinder finder;
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public ApprovalRule getRule() {
		if(rule==null){
			if(hasParameter("rule")){
				setRule(finder.getById(getParameterInt("rule")));
				approvalapp.setMode("read");
			} else {
	    		setRule(new ApprovalRule());
			}
		}
		
		return rule;
	}
    	
	public void setRule(ApprovalRule rule) {
		this.rule = rule;
		if(rule!=null) {
			ApprovalRules rules = rule.getRules();
			if(rules!=null){
				getApprovalRulesDropdownHelper().addToFoundItemsList(rules);
			}
		}
	}
	
	@Inject
	private ApprovalRulesDropdownHelper approvalRulesDropdownHelper;

	public ApprovalRulesDropdownHelper getApprovalRulesDropdownHelper(){
		return approvalRulesDropdownHelper;
	}
	
	private List<ApprovalRule> ruleList;
	
	public List<ApprovalRule> getRuleList(){
		return ruleList;
	}
	
	public void findRule(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(rule.isActive()){
			criteriaBuilder.property("active").mustBeEqualTo(rule.isActive());
		}

		if(rule.getCount() != 0){
			criteriaBuilder.property("count").mustBeEqualTo(rule.getCount());
		}

		if(rule.getLevel() != 0){
			criteriaBuilder.property("level").mustBeEqualTo(rule.getLevel());
		}

		if(rule.getRules() != null){
			criteriaBuilder.property("rules").mustBeEqualTo(rule.getRules());
		}

		try {
			ruleList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval Rule", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for ApprovalRule");
		}		
	}
    	
	public void removeRule(ActionEvent event){
		if(rule!=null && ruleList!=null){
			ruleList.remove(rule);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
}
