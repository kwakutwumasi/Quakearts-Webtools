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
import com.quakearts.approvalengine.model.Approver;
import com.quakearts.approvalengine.model.ApprovalGroup;

@Named("approverPage")
@ViewScoped
public class ApproverPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6830079680066405665L;

	private static Logger log = Logger.getLogger(ApproverPage.class.getName());

	private Approver approver;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApproverFinder finder;
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public Approver getApprover() {
		if(approver==null){
			if(hasParameter("approver")){
				setApprover(finder.getById(getParameterLong("approver")));
				approvalapp.setMode("read");
			} else {
	    		setApprover(new Approver());
			}
		}
		
		return approver;
	}
    	
	public void setApprover(Approver approver) {
		this.approver = approver;
		if(approver!=null) {
			ApprovalGroup group = approver.getGroup();
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
	
	private List<Approver> approverList;
	
	public List<Approver> getApproverList(){
		return approverList;
	}
	
	public void findApprover(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(approver.getExternalId() != null && ! approver.getExternalId().trim().isEmpty()){
			criteriaBuilder.property("externalId").mustBeLike(approver.getExternalId());
		}

		if(approver.getGroup() != null){
			criteriaBuilder.property("group").mustBeEqualTo(approver.getGroup());
		}

		if(approver.getLevel() != 0){
			criteriaBuilder.property("level").mustBeEqualTo(approver.getLevel());
		}

		if(approver.isValid()){
			criteriaBuilder.property("valid").mustBeEqualTo(approver.isValid());
		}

		try {
			approverList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approver", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for Approver");
		}		
	}
    	
	public void removeApprover(ActionEvent event){
		if(approver!=null && approverList!=null){
			approverList.remove(approver);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
}
