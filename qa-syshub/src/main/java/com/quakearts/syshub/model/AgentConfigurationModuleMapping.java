package com.quakearts.syshub.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author kwakutwumasi-afriyie
 *
 */
@Entity
@Table(name = "agent_configuration_module_mapping")
public class AgentConfigurationModuleMapping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4161366216938531552L;
	@Id
	private int acid;
	@Id
	private int amid;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acid", insertable = false, updatable = false, nullable = false)
	private AgentConfiguration agentConfiguration;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "amid", insertable = false, updatable = false, nullable = false)
	private AgentModule agentModule;

	public int getAcid() {
		return acid;
	}

	public void setAcid(int acid) {
		this.acid = acid;
	}

	public int getAmid() {
		return amid;
	}

	public void setAmid(int amid) {
		this.amid = amid;
	}

	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
		if(agentConfiguration != null)
			this.acid = agentConfiguration.getId();
	}

	public AgentModule getAgentModule() {
		return agentModule;
	}

	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
		if(agentModule != null)
			this.amid = agentModule.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(acid, amid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AgentConfigurationModuleMapping other = (AgentConfigurationModuleMapping) obj;
		return acid == other.acid && amid == other.amid;
	}

}
