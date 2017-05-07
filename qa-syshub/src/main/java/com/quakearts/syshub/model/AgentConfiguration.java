package com.quakearts.syshub.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author QuakesHome
 *
 */
@Entity
@Table(name = "agent_configuration", schema = "dbo")
public class AgentConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7117694041115125087L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100, nullable = false, unique = true)
	private String agentName;
	private RunType type = RunType.LOOPED;
	private boolean active;
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<AgentModule> agentModules = new HashSet<>();
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<AgentConfigurationParameter> parameters = new HashSet<>();
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.EAGER, cascade={CascadeType.REMOVE})
	private Set<ProcessingLog> processingLogs = new HashSet<>();
	
	public static enum RunType{
		SCHEDULED,
		LOOPED,
		TRIGGERED
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public RunType getType() {
		return type;
	}

	public void setType(RunType type) {
		this.type = type;
	}

	public Set<AgentModule> getAgentModules() {
		return agentModules;
	}

	public void setAgentModules(Set<AgentModule> agentModules) {
		this.agentModules = agentModules;
	}
	
	public Set<AgentConfigurationParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Set<AgentConfigurationParameter> parameters) {
		this.parameters = parameters;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public AgentConfiguration cloneById(){
		AgentConfiguration agentConfiguration = new AgentConfiguration();
		agentConfiguration.id = id;
		
		return agentConfiguration;
	}
	
	@Transient
	private Map<Integer, Map<String, AgentConfigurationParameter>> moduleConfigurationMaps;
	@Transient
	private Map<String, AgentConfigurationParameter> agentConfigurationMap;

	private void createConfigurationMap() {
		if(parameters.size()>0){
			agentConfigurationMap = new HashMap<>();
			moduleConfigurationMaps = new HashMap<>();
			for(AgentConfigurationParameter agentConfigurationParameter: parameters) {
				if(agentConfigurationParameter.getAgentModule()!=null){
					Map<String, AgentConfigurationParameter> agentModuleConfigurationMap = 
							moduleConfigurationMaps.get(agentConfigurationParameter.getAgentModule().getId());
					
					if(agentModuleConfigurationMap == null){
						agentModuleConfigurationMap = new HashMap<>();
						moduleConfigurationMaps.put(agentConfigurationParameter.getAgentModule().getId(), agentModuleConfigurationMap);
					}
					
					agentModuleConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);
				} else {
					agentConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);
				}
			}
			
			agentConfigurationMap = Collections.unmodifiableMap(agentConfigurationMap);
			moduleConfigurationMaps = Collections.unmodifiableMap(moduleConfigurationMaps);
		}
	}
	
	@Transient
	public AgentConfigurationParameter getAgentConfigurationParameter(String name) {		
		return getAgentConfigurationMap().get(name);
	}

	public Map<String, AgentConfigurationParameter> getAgentConfigurationMap() {
		if(agentConfigurationMap==null)
			createConfigurationMap();

		return agentConfigurationMap;
	}
	
	@Transient
	public Map<String, AgentConfigurationParameter> getModuleConfigurationParameters(AgentModule agentModule) {
		if(moduleConfigurationMaps==null)
			createConfigurationMap();
		
		Map<String, AgentConfigurationParameter> moduleConfigurationMap = moduleConfigurationMaps.get(agentModule.getId());
		if(moduleConfigurationMap!=null) {
			moduleConfigurationMap.putAll(agentConfigurationMap);
			return moduleConfigurationMap;
		} else {
			return null;
		}
	}
}
