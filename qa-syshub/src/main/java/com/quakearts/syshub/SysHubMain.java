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

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.agent.builder.ProcessingAgentBuilder;
import com.quakearts.syshub.core.factory.DataSpoolerFactory;
import com.quakearts.syshub.core.factory.MessageFormatterFactory;
import com.quakearts.syshub.core.factory.MessengerFactory;
import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.core.runner.impl.LoopedAgentRunner;
import com.quakearts.syshub.core.runner.impl.ScheduledAgentRunner;
import com.quakearts.syshub.core.runner.impl.TriggeredAgentRunner;
import com.quakearts.syshub.core.utils.SystemDataStoreUtils;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

@Singleton
public class SysHubMain implements SysHub {
	
	private static Map<String, AgentRunner> agentRunners = new ConcurrentHashMap<>();
		
	private static boolean hasRun = false;
	
	private static SysHub instance;
	
	public static SysHub getInstance() {
		return instance;
	}
	
	@Transactional(TransactionType.SINGLETON)
	public void init() {
		if(!hasRun){//Run only once per application
			List<AgentConfiguration> agentConfigurations = SystemDataStoreUtils
					.getInstance()
					.getSystemDataStore()
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
			instance = this;
			hasRun = true;
			
			Runtime.getRuntime().addShutdownHook(new Thread(()->{
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException e) {
				}
			}));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.SysHub#listAgentRunners()
	 */
	@Override
	public Collection<AgentRunner> listAgentRunners(){
		return agentRunners.values();
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.SysHub#deployAgent(com.quakearts.syshub.model.AgentConfiguration)
	 */
	@Override
	public void deployAgent(AgentConfiguration agentConfiguration) throws ConfigurationException{
		ProcessingAgentBuilder builder = new ProcessingAgentBuilder(agentConfiguration);
		ProcessingAgent agent; 
		AgentRunner agentRunner = null;
		try {
			agent = builder.build();
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
		} catch (ConfigurationException e) {
			undeployAgent(agentConfiguration);
			throw e;
		}
		
		if(agentRunner == null)
			throw new ConfigurationException("Unable to find type runner for " + agentConfiguration.getType());
		
		agentRunners.put(agent.getName(), agentRunner);
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.SysHub#undeployAgent(com.quakearts.syshub.model.AgentConfiguration)
	 */
	@Override
	public void undeployAgent(AgentConfiguration agentConfiguration){
		if(agentConfiguration == null)
			return;
			
		AgentRunner agentRunner = agentRunners.get(agentConfiguration.getAgentName());
		if(agentRunner != null){
			agentRunner.shutdown();
			agentRunners.remove(agentConfiguration.getAgentName());
		}
		
		for(AgentModule module:agentConfiguration.getAgentModules()){
			try {				
				switch (module.getModuleType()) {
				case DATASPOOLER:
					DataSpoolerFactory.getFactory().unbindFromJNDI(module);
					break;
				case FORMATTER:
					MessageFormatterFactory.getFactory().unbindFromJNDI(module);
					break;
				case MESSENGER:
					MessengerFactory.getFactory().unbindFromJNDI(module);
					break;
				default:
					break;
				}
			} catch (Throwable e) {
				Main.log.error("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles undeploying "+module.getModuleName()+" for agent "+agentConfiguration.getAgentName());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.SysHub#isDeployed(com.quakearts.syshub.model.AgentConfiguration)
	 */
	@Override
	public boolean isDeployed(AgentConfiguration agentConfiguration){
		if(agentConfiguration.getAgentName() != null)
			return agentRunners.containsKey(agentConfiguration.getAgentName());
		else
			return false;
	}
	
	@Override
	public AgentRunner fetchAgentRunner(AgentConfiguration agentConfiguration) {
		return agentRunners.get(agentConfiguration.getAgentName());
	}
}
