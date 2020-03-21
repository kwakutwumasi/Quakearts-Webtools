package com.quakearts.syshub.webapp.beans;

import java.util.List;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

@Named("agentConfigurationParameterPage")
@ViewScoped
public class AgentConfigurationParameterPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2244897577206167783L;

	private static Logger log = Logger.getLogger(AgentConfigurationParameterPage.class.getName());

	private AgentConfigurationParameter agentConfigurationParameter;
	
	@Inject @Named("webappmain")
	private WebApplicationMain webappmain;
	private transient AgentConfigurationParameterFinder finder = new AgentConfigurationParameterFinder();
			
	public WebApplicationMain getWebappmain(){
		return webappmain;
	}
	
	public AgentConfigurationParameter getAgentConfigurationParameter() {
		if(agentConfigurationParameter==null){
			if(hasParameter("agentConfigurationParameter")){
				setAgentConfigurationParameter(finder.getById(getParameterInt("agentConfigurationParameter")));
				webappmain.setMode("read");
			} else {
    			agentConfigurationParameter = new AgentConfigurationParameter();
			}
		}
		
		return agentConfigurationParameter;
	}
    	
	public void setAgentConfigurationParameter(AgentConfigurationParameter agentConfigurationParameter) {
		this.agentConfigurationParameter = agentConfigurationParameter;
		if(agentConfigurationParameter!=null){
			AgentConfiguration agentConfiguration = agentConfigurationParameter.getAgentConfiguration();
			if(agentConfiguration!=null){
				getAgentConfigurationDropdownHelper().addToFoundItemsList(agentConfiguration);
			}
			AgentModule agentModule = agentConfigurationParameter.getAgentModule();
			if(agentModule!=null){
				getAgentModuleDropdownHelper().addToFoundItemsList(agentModule);
			}
		}
	}
	
	private AgentConfigurationDropdownHelper agentConfigurationDropdownHelper;

	public AgentConfigurationDropdownHelper getAgentConfigurationDropdownHelper(){
		if(agentConfigurationDropdownHelper == null)
			agentConfigurationDropdownHelper = new AgentConfigurationDropdownHelper();
			
		return agentConfigurationDropdownHelper;
	}
	private AgentModuleDropdownHelper agentModuleDropdownHelper;

	public AgentModuleDropdownHelper getAgentModuleDropdownHelper(){
		if(agentModuleDropdownHelper == null)
			agentModuleDropdownHelper = new AgentModuleDropdownHelper();
			
		return agentModuleDropdownHelper;
	}
	
	private List<AgentConfigurationParameter> agentConfigurationParameterList;
	
	public List<AgentConfigurationParameter> getAgentConfigurationParameterList(){
		return agentConfigurationParameterList;
	}
	
	public void findAgentConfigurationParameter(ActionEvent event){
		CriteriaMapBuilder criteriaMapBuilder = CriteriaMapBuilder.createCriteria();
		if(agentConfigurationParameter.getAgentConfiguration() != null){
			criteriaMapBuilder.property("agentConfiguration").mustBeEqualTo(agentConfigurationParameter.getAgentConfiguration());
		}
		
		if(agentConfigurationParameter.getAgentModule() != null){
			criteriaMapBuilder.property("agentModule").mustBeEqualTo(agentConfigurationParameter.getAgentModule());
		}
		
		if(Boolean.TRUE.equals(agentConfigurationParameter.getBooleanValue())){
			criteriaMapBuilder.property("booleanValue").mustBeEqualTo(agentConfigurationParameter.getBooleanValue());
		}
		
		if(agentConfigurationParameter.getName() != null && ! agentConfigurationParameter.getName().trim().isEmpty()){
			criteriaMapBuilder.property("name").mustBeLike(agentConfigurationParameter.getName());
		}
		
		if(agentConfigurationParameter.getNumericValue() != null){
			criteriaMapBuilder.property("numericValue").mustBeEqualTo(agentConfigurationParameter.getNumericValue());
		}
		
		if(agentConfigurationParameter.getParameterType() != null){
			criteriaMapBuilder.property("parameterType").mustBeEqualTo(agentConfigurationParameter.getParameterType());
		}
		
		if(agentConfigurationParameter.getStringValue() != null && ! agentConfigurationParameter.getStringValue().trim().isEmpty()){
			criteriaMapBuilder.property("stringValue").mustBeLike(agentConfigurationParameter.getStringValue());
		}
    		
		try {
			agentConfigurationParameterList = finder.findObjects(criteriaMapBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Agent Configuration Parameter", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for AgentConfigurationParameter");
		}		
	}
    	
	public void removeAgentConfigurationParameter(ActionEvent event){
		if(agentConfigurationParameter!=null && agentConfigurationParameterList!=null){
			agentConfigurationParameterList.remove(agentConfigurationParameter);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(webappmain.getMode());
	}
}
