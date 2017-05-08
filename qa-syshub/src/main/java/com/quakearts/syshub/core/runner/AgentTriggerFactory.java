/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.syshub.core.runner;

import java.util.Map;

import javax.enterprise.inject.spi.CDI;

import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;

public class AgentTriggerFactory {

	private AgentTriggerFactory() {
	}
	
	private static final AgentTriggerFactory instance = new AgentTriggerFactory();
	
	public static AgentTriggerFactory getInstance() {
		return instance;
	}
	
	public AgentTrigger createAgentTrigger(String className, RunAgentListener listener, 
			Map<String, AgentConfigurationParameter> parameters) throws ConfigurationException{
		try {
			Class<?> triggerClass = Class.forName(className);
			
			Object instanceObj = CDI.current().select(triggerClass).get();
			
			if(!(instanceObj instanceof AgentTrigger)){
				throw new ConfigurationException("Unable to use class "+className+" as a trigger");
			}
			
			((AgentTrigger) instanceObj).setupWithConfigurationParameters(parameters);
			
			return (AgentTrigger) instanceObj;
		} catch (ClassNotFoundException | IllegalArgumentException e) {
			throw new ConfigurationException("Error starting trigger: "+e.getMessage(), e);
		}
	}
}
