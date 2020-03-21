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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;
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
import com.quakearts.syshub.core.utils.SystemDataStoreManager;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

@Singleton
public class SysHubMain implements SysHub {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9017418510878915431L;
	@Inject
	private transient SystemDataStoreManager storeManager;
	private static Map<String, AgentRunner> agentRunners = new ConcurrentHashMap<>();
	private static boolean hasRun = false;
	@Transactional(TransactionType.SINGLETON)
	public void init() {
		if(!hasRun()){//Run only once per application
			List<AgentConfiguration> agentConfigurations = storeManager
					.getDataStore()
					.find(AgentConfiguration.class)
					.filterBy("active")
					.withAValueEqualTo(Boolean.TRUE)
					.thenList();
			
			for(AgentConfiguration agentConfiguration : agentConfigurations) {
				try {
					deployAgent(agentConfiguration);
				} catch (ConfigurationException e) {
					Main.log.error("Exception of type " + e.getClass().getName() 
							+ " was thrown. Message is " + e.getMessage()
							+ ". Exception occured whiles deploying "+agentConfiguration.getAgentName());
				}
			}

			setRun(true);
			
			Runtime.getRuntime().addShutdownHook(new Thread(()->{
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException e) {
					//Do nothing
				}
			}));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.quakearts.syshub.SysHub#listAgentRunners()
	 */
	@Override
	public Collection<AgentRunner> listAgentRunners(){
		return Collections.unmodifiableCollection(agentRunners.values());
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
			switch (module.getModuleType()) {
			case DATASPOOLER:
				DataSpoolerFactory.getFactory().removeInstance(module);
				break;
			case FORMATTER:
				MessageFormatterFactory.getFactory().removeInstance(module);
				break;
			case MESSENGER:
				MessengerFactory.getFactory().removeInstance(module);
				break;
			default:
				break;
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

	private static boolean hasRun() {
		return hasRun;
	}

	private static void setRun(boolean hasRun) {
		SysHubMain.hasRun = hasRun;
	}
}
