/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
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
import javax.persistence.UniqueConstraint;

/**
 * @author QuakesHome
 *
 */
@Entity
@Table(name = "agent_configuration", uniqueConstraints = @UniqueConstraint(columnNames = {"agentName"}))
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
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private Set<AgentModule> agentModules = new HashSet<>();
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private Set<AgentConfigurationParameter> parameters = new HashSet<>();
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
	private Set<ProcessingLog> processingLogs = new HashSet<>();
	@OneToMany(mappedBy="agentConfiguration", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private Set<AgentConfigurationModuleMapping> agentConfigurationModuleMappings = new HashSet<>();
	
	public enum RunType{
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

	public Set<AgentConfigurationModuleMapping> getAgentConfigurationModuleMappings() {
		return agentConfigurationModuleMappings;
	}

	public void setAgentConfigurationModuleMappings(Set<AgentConfigurationModuleMapping> agentConfigurationModuleMappings) {
		this.agentConfigurationModuleMappings = agentConfigurationModuleMappings;
	}
	
	@Transient
	private Map<Integer, Map<String, AgentConfigurationParameter>> moduleConfigurationMaps;
	@Transient
	private Map<String, AgentConfigurationParameter> agentConfigurationMap;

	private void createConfigurationMaps() {
		agentConfigurationMap = new HashMap<>();
		moduleConfigurationMaps = new HashMap<>();
		if(!getParameters().isEmpty()){
			loadParameters();			
			setEntries();			
			createMaps();
		}
	}

	protected void loadParameters() {
		for(AgentConfigurationParameter agentConfigurationParameter: getParameters()) {
			if(agentConfigurationParameter.getAgentModule()!=null){
				Map<String, AgentConfigurationParameter> agentModuleConfigurationMap = 
						moduleConfigurationMaps.get(agentConfigurationParameter.getAgentModule().getId());
				
				if(agentModuleConfigurationMap == null){
					agentModuleConfigurationMap = new HashMap<>();
					moduleConfigurationMaps.put(agentConfigurationParameter.getAgentModule().getId(), agentModuleConfigurationMap);
				}
				
				agentModuleConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);
				if(agentConfigurationParameter.isGlobal())
					agentConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);

			} else {
				agentConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);
			}
		}
	}

	protected void setEntries() {
		for(Entry<Integer, Map<String, AgentConfigurationParameter>> entry:moduleConfigurationMaps.entrySet()){
			entry.getValue().putAll(agentConfigurationMap);
			entry.setValue(Collections.unmodifiableMap(entry.getValue()));
		}
	}

	protected void createMaps() {
		for(AgentConfigurationModuleMapping configurationModuleMapping:getAgentConfigurationModuleMappings()) {
			moduleConfigurationMaps.put(configurationModuleMapping.getAgentModule().getId()
					, configurationModuleMapping.getAgentModule().getModuleConfigurationMap());
		}

		agentConfigurationMap = Collections.unmodifiableMap(agentConfigurationMap);
		moduleConfigurationMaps = Collections.unmodifiableMap(moduleConfigurationMaps);
	}
	
	@Transient
	public AgentConfigurationParameter getAgentConfigurationParameter(String name) {		
		return getAgentConfigurationMap().get(name);
	}

	public Map<String, AgentConfigurationParameter> getAgentConfigurationMap() {
		if(agentConfigurationMap==null)
			createConfigurationMaps();

		return agentConfigurationMap;
	}
	
	@Transient
	public Map<String, AgentConfigurationParameter> getAgentModuleConfigurationMap(AgentModule agentModule) {
		if(moduleConfigurationMaps==null)
			createConfigurationMaps();
		
		Map<String, AgentConfigurationParameter> agentModuleConfigurationMap = moduleConfigurationMaps.get(agentModule.getId());
		if(agentModuleConfigurationMap!=null) {
			return agentModuleConfigurationMap;
		} else {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+id>0?id:0);
	}
}
