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
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.quakearts.syshub.exception.ConfigurationException;

@Entity
@Table(name="agent_configuration_parameters")
public class AgentConfigurationParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916156534982774751L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne(optional=false, fetch=FetchType.EAGER)
	private AgentConfiguration agentConfiguration;
	@ManyToOne(fetch=FetchType.EAGER)
	private AgentModule agentModule;
	@Column(length=100, nullable=false)
	private String name;
	@Column(nullable=false)
	private ParameterType parameterType;
	private Double numericValue;
	private Boolean booleanValue;
	@Column(length=1024)
	private String stringValue;
	@Column(nullable=false, name="isGlobal")
	private boolean global;
		
	public static enum ParameterType {
		/**Numeric parameter, like integer, double or float values
		 * 
		 */
		NUMERIC,
		/**Boolean true or false
		 * 
		 */
		BOOLEAN,
		/**Ordinary one line string
		 * 
		 */
		STRING,
		/**A class or interface
		 * 
		 */
		CLASS,
		/**An array of bytes
		 * 
		 */
		BINARY,
		/**A local file
		 * 
		 */
		FILE,
		/**A JNDI resource
		 * 
		 */
		JNDINAME,
		/**An email address
		 * 
		 */
		EMAIL,
		/**An IP or domain name
		 * 
		 */
		ENDPOINTADDRESS,
		/**A CRON string
		 * 
		 */
		CRONCONFIGURATION,
		/**A passowrd
		 * 
		 */
		PASSWORD,
		/**A list of options
		 * 
		 */
		LIST,
		/**A multi line string
		 * 
		 */
		LONGSTRING
	}

	public AgentConfigurationParameter() {
	}
	
	public AgentConfigurationParameter(String name) {
		this.name = name;
	}
	
	public AgentConfigurationParameter(String name, ParameterType parameterType) {
		this.name = name;
		this.parameterType = parameterType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}

	public void setAgentConfiguration(AgentConfiguration agentConfiguration) {
		this.agentConfiguration = agentConfiguration;
	}

	public AgentModule getAgentModule() {
		return agentModule;
	}

	public void setAgentModule(AgentModule agentModule) {
		this.agentModule = agentModule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ParameterType getParameterType() {
		return parameterType;
	}

	public void setParameterType(ParameterType parameterType) {
		this.parameterType = parameterType;
	}

	public Double getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Double numericValue) {
		this.numericValue = numericValue;
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public boolean isGlobal() {
		return global;
	}

	public void setGlobal(boolean global) {
		this.global = global;
	}

	@Transient
	public void setBase64String(byte[] value){
		this.stringValue = Base64.getEncoder().encodeToString(value);
		this.parameterType = ParameterType.BINARY;
	}
	
	@Transient
	public byte[] getBase64Bytes() throws ConfigurationException {
		if(stringValue != null)
			try {
				return Base64.getDecoder().decode(stringValue);
			} catch (IllegalArgumentException e) {
				throw new ConfigurationException("Exception of type " 
						+ e.getClass().getName() 
						+ " was thrown. Message is " 
						+ e.getMessage()
						+ ". Exception occured whiles getting parameter "
						+ name
						+" for agent "
						+agentConfiguration.getAgentName(), e);
			}
		else
			return null;
	}
	
	@Override
	public String toString() {
		return Integer.toHexString(this.hashCode()+(int)(id>0?id:0));
	}
}
