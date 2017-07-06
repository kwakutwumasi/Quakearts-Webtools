package com.quakearts.syshub.webapp.beans;

import java.util.List;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import com.quakearts.webapp.facelets.base.BaseBean;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

@ManagedBean(name="agentConfigurationParameterPage")
@ViewScoped
public class AgentConfigurationParameterPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2244897577206167783L;

	private static Logger log = Logger.getLogger(AgentConfigurationParameterPage.class.getName());

	private AgentConfigurationParameter agentConfigurationParameter;
	private WebApplicationMain webappmain;
	private transient AgentConfigurationParameterFinder finder = new AgentConfigurationParameterFinder();
		
	public AgentConfigurationParameterPage(){
		webappmain = new WebApplicationMain();
	}
	
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
		ParameterMapBuilder parameterBuilder = new ParameterMapBuilder();
		if(agentConfigurationParameter.getAgentConfiguration() != null){
			parameterBuilder.add("agentConfiguration", agentConfigurationParameter.getAgentConfiguration());
		}
		if(agentConfigurationParameter.getAgentModule() != null){
			parameterBuilder.add("agentModule", agentConfigurationParameter.getAgentModule());
		}
		if(agentConfigurationParameter.getBooleanValue()){
			parameterBuilder.add("booleanValue", agentConfigurationParameter.getBooleanValue());
		}
		if(agentConfigurationParameter.getName() != null && ! agentConfigurationParameter.getName().trim().isEmpty()){
			parameterBuilder.addVariableString("name", agentConfigurationParameter.getName());
		}
		if(agentConfigurationParameter.getNumericValue() != null){
			parameterBuilder.add("numericValue", agentConfigurationParameter.getNumericValue());
		}
		if(agentConfigurationParameter.getParameterType() != null){
			parameterBuilder.add("parameterType", agentConfigurationParameter.getParameterType());
		}
		if(agentConfigurationParameter.getStringValue() != null && ! agentConfigurationParameter.getStringValue().trim().isEmpty()){
			parameterBuilder.addVariableString("stringValue", agentConfigurationParameter.getStringValue());
		}
    		
		try {
			agentConfigurationParameterList = finder.findObjects(parameterBuilder.build());
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
