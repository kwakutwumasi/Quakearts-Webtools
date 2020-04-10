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
import com.quakearts.approvalengine.model.ApprovalGroup;

@Named("groupPage")
@ViewScoped
public class ApprovalGroupPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2694326638443238173L;

	private static Logger log = Logger.getLogger(ApprovalGroupPage.class.getName());

	private ApprovalGroup group;
	
	@Inject @Named("approvalapp")
	private ApprovalApplication approvalapp;
	@Inject
	private transient ApprovalGroupFinder finder;
			
	public ApprovalApplication getApprovalapp(){
		if(approvalapp == null)
			approvalapp = new ApprovalApplication();
			
		return approvalapp;
	}
	
	public ApprovalGroup getGroup() {
		if(group==null){
			if(hasParameter("group")){
				setGroup(finder.getById(getParameterLong("group")));
				approvalapp.setMode("read");
			} else {
	    		setGroup(new ApprovalGroup());
			}
		}
		
		return group;
	}
    	
	public void setGroup(ApprovalGroup group) {
		this.group = group;
	}
	
	private List<ApprovalGroup> groupList;
	
	public List<ApprovalGroup> getGroupList(){
		return groupList;
	}
	
	public void findGroup(ActionEvent event){
		CriteriaMapBuilder criteriaBuilder = CriteriaMapBuilder.createCriteria();
		if(group.getName() != null && ! group.getName().trim().isEmpty()){
			criteriaBuilder.property("name").mustBeLike(group.getName());
		}

		if(group.isValid()){
			criteriaBuilder.property("valid").mustBeEqualTo(group.isValid());
		}

		try {
			groupList = finder.findObjects(criteriaBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Approval Group", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for ApprovalGroup");
		}		
	}
    	
	public void removeGroup(ActionEvent event){
		if(group!=null && groupList!=null){
			groupList.remove(group);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(approvalapp.getMode());
	}
}
