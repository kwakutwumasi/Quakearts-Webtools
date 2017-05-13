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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String agentClassName;
	@ManyToOne(fetch=FetchType.EAGER)
	private AgentConfiguration agentConfiguration;
	@Column(length=100)
	private String moduleName;
	@Column(length=100)
	private String mappedModuleName;
	@Column(nullable=false)
	private ModuleType moduleType;
	@OneToMany(mappedBy="agentModule")
	private Set<AgentConfigurationParameter> parameters = new HashSet<>();
	
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

	public String getAgentClassName() {
		return agentClassName;
	}

	public void setAgentClassName(String agentClassName) {
		this.agentClassName = agentClassName;
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

	public AgentModule cloneById() {
		AgentModule clone = new AgentModule();
		clone.id = id;
		return clone;
	}

}
