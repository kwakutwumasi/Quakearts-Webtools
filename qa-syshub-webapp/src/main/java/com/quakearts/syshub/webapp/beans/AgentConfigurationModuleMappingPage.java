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
import com.quakearts.syshub.model.AgentConfigurationModuleMapping;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

@Named("agentConfigurationModuleMappingPage")
@ViewScoped
public class AgentConfigurationModuleMappingPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2397338870949261420L;

	private static Logger log = Logger.getLogger(AgentConfigurationModuleMappingPage.class.getName());

	private AgentConfigurationModuleMapping agentConfigurationModuleMapping;
	
	@Inject @Named("webappmain")
	private WebApplicationMain webappmain;
	private transient AgentConfigurationModuleMappingFinder finder = new AgentConfigurationModuleMappingFinder();
			
	public WebApplicationMain getWebappmain(){			
		return webappmain;
	}
		
	public AgentConfigurationModuleMapping getAgentConfigurationModuleMapping() {
		if(agentConfigurationModuleMapping==null){    			
			agentConfigurationModuleMapping = new AgentConfigurationModuleMapping();
		}
		
		return agentConfigurationModuleMapping;
	}
    	
	public void setAgentConfigurationModuleMapping(AgentConfigurationModuleMapping agentConfigurationModuleMapping) {
		this.agentConfigurationModuleMapping = agentConfigurationModuleMapping;
		if(agentConfigurationModuleMapping!=null){
			AgentConfiguration agentConfiguration = agentConfigurationModuleMapping.getAgentConfiguration();
			if(agentConfiguration!=null){
				getAgentConfigurationDropdownHelper().addToFoundItemsList(agentConfiguration);
			}
			AgentModule agentModule = agentConfigurationModuleMapping.getAgentModule();
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
	
	private List<AgentConfigurationModuleMapping> agentConfigurationModuleMappingList;
	
	public List<AgentConfigurationModuleMapping> getAgentConfigurationModuleMappingList(){
		return agentConfigurationModuleMappingList;
	}
	
	public void findAgentConfigurationModuleMapping(ActionEvent event){
		CriteriaMapBuilder criteriaMapBuilder = CriteriaMapBuilder.createCriteria();
		if(agentConfigurationModuleMapping.getAcid() != 0){
			criteriaMapBuilder.property("acid").mustBeEqualTo(agentConfigurationModuleMapping.getAcid());
		}
		
		if(agentConfigurationModuleMapping.getAgentConfiguration() != null){
			criteriaMapBuilder.property("agentConfiguration").mustBeEqualTo(agentConfigurationModuleMapping.getAgentConfiguration());
		}
		
		if(agentConfigurationModuleMapping.getAgentModule() != null){
			criteriaMapBuilder.property("agentModule").mustBeEqualTo(agentConfigurationModuleMapping.getAgentModule());
		}
		
		if(agentConfigurationModuleMapping.getAmid() != 0){
			criteriaMapBuilder.property("amid").mustBeEqualTo(agentConfigurationModuleMapping.getAmid());
		}
    		
		try {
			agentConfigurationModuleMappingList = finder.findObjects(criteriaMapBuilder.finish());
		} catch (DataStoreException e) {
			addError("Search error", "An error occured while searching for Agent Configuration Module Mapping", FacesContext.getCurrentInstance());
			log.severe("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles searching for AgentConfigurationModuleMapping");
		}		
	}
    	
	public void removeAgentConfigurationModuleMapping(ActionEvent event){
		if(agentConfigurationModuleMapping!=null && agentConfigurationModuleMappingList!=null){
			agentConfigurationModuleMappingList.remove(agentConfigurationModuleMapping);
		}
	}
	
	public boolean isInCreateOrEditMode(){
		return FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("create.xhtml") || "edit".equals(webappmain.getMode());
	}
}
