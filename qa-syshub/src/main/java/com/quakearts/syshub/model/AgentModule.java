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
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="agent_module", schema="dbo")
public class AgentModule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832241426782359322L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false, length=100)
	private String moduleClassName;
	@ManyToOne(fetch=FetchType.EAGER)
	private AgentConfiguration agentConfiguration;
	@Column(length=100)
	private String moduleName;
	@Column(length=100)
	private String mappedModuleName;
	@Column(nullable=false)
	private ModuleType moduleType;
	@OneToMany(mappedBy="agentModule", fetch=FetchType.LAZY, cascade = {CascadeType.ALL})
	private Set<AgentConfigurationParameter> parameters = new HashSet<>();
	@OneToMany(mappedBy="agentModule", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private Set<AgentConfigurationModuleMapping> agentConfigurationModuleMappings = new HashSet<>();
	
	public static enum ModuleType {
		DATASPOOLER,
		FORMATTER,
		MESSENGER
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModuleClassName() {
		return moduleClassName;
	}

	public void setModuleClassName(String agentClassName) {
		this.moduleClassName = agentClassName;
	}

	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMappedModuleName() {
		return mappedModuleName;
	}

	public void setMappedModuleName(String mappedModuleName) {
		this.mappedModuleName = mappedModuleName;
	}

	public ModuleType getModuleType() {
		return moduleType;
	}

	public void setModuleType(ModuleType moduleType) {
		this.moduleType = moduleType;
	}

	public Set<AgentConfigurationParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Set<AgentConfigurationParameter> agentConfigurationParameters) {
		this.parameters = agentConfigurationParameters;
	}

	public Set<AgentConfigurationModuleMapping> getAgentConfigurationModuleMappings() {
		return agentConfigurationModuleMappings;
	}

	public void setAgentConfigurationModuleMappings(Set<AgentConfigurationModuleMapping> agentConfigurationModuleMappings) {
		this.agentConfigurationModuleMappings = agentConfigurationModuleMappings;
	}

	public AgentModule cloneById() {
		AgentModule clone = new AgentModule();
		clone.id = id;
		clone.moduleName = moduleName;
		return clone;
	}

	@Transient
	private Map<String, AgentConfigurationParameter> moduleConfigurationMap;
	
	public Map<String, AgentConfigurationParameter> getModuleConfigurationMap() {
		if(moduleConfigurationMap == null){
			moduleConfigurationMap = new HashMap<>();
			if(parameters.size()>0){
				for(AgentConfigurationParameter agentConfigurationParameter : parameters){
					moduleConfigurationMap.put(agentConfigurationParameter.getName(), agentConfigurationParameter);
				}
				
				moduleConfigurationMap = Collections.unmodifiableMap(moduleConfigurationMap);
			}
		}
		
		return moduleConfigurationMap;
	}
	
	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(int)(id>0?id:0));
	}

}
