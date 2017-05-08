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
package com.quakearts.syshub;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant.TransactionType;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.agent.builder.ProcessingAgentBuilder;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.impl.LoopedAgentRunner;
import com.quakearts.syshub.core.runner.impl.ScheduledAgentRunner;
import com.quakearts.syshub.core.runner.impl.TriggeredAgentRunner;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

public class SysHubMain {
	
	private static Map<String, AgentRunner> agentRunners = new ConcurrentHashMap<>();
	
	@TransactionParticipant(TransactionType.SINGLETON)
	public void init() {
		List<AgentConfiguration> agentConfigurations = DataStoreFactory.getInstance()
				.getDataStore()
				.list(AgentConfiguration.class, new ParameterMapBuilder().add("active", Boolean.TRUE).build());
		
		for(AgentConfiguration agentConfiguration : agentConfigurations) {
			try {
				deployAgent(agentConfiguration);
			} catch (ConfigurationException e) {
				Main.log.error("Exception of type " + e.getClass().getName() 
						+ " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles deploying "+agentConfiguration.getAgentName());
			}
		}
	}
	
	public void deployAgent(AgentConfiguration agentConfiguration) throws ConfigurationException{
		ProcessingAgentBuilder builder = new ProcessingAgentBuilder(agentConfiguration);
		ProcessingAgent agent = builder.build();
		AgentRunner agentRunner = null;
		switch (agentConfiguration.getType()) {
			case LOOPED:
				agentRunner = new LoopedAgentRunner(agent);
				break;
			case SCHEDULED:
				agentRunner = new ScheduledAgentRunner(agent, agentConfiguration.getAgentConfigurationMap());
				break;
			case TRIGGERED:
				agentRunner = new TriggeredAgentRunner(agent, agentConfiguration.getAgentConfigurationMap());
				break;
		}
		
		if(agentRunner == null)
			throw new ConfigurationException("Unable to find type runner for " + agentConfiguration.getType());
		
		agentRunners.put(agent.getName(), agentRunner);
	}
}
