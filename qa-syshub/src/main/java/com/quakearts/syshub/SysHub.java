package com.quakearts.syshub;

import java.util.Collection;

import com.quakearts.syshub.core.runner.AgentRunner;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfiguration;

public interface SysHub {

	/**Lists all {@link AgentRunner}'s deployed on the system
	 * @return the list of agent runners
	 */
	Collection<AgentRunner> listAgentRunners();

	/**Deploy an {@link AgentRunner} identified by the {@link AgentConfiguration} 
	 * @param agentConfiguration the {@link AgentConfiguration} entry for the {@link AgentRunner}
	 * @throws ConfigurationException If there was an error in the configuration parameters
	 */
	void deployAgent(AgentConfiguration agentConfiguration) throws ConfigurationException;

	/**Undeploy an {@link AgentRunner} identified by the {@link AgentConfiguration} 
	 * @param agentConfiguration the {@link AgentConfiguration} entry for the {@link AgentRunner}
	 */
	void undeployAgent(AgentConfiguration agentConfiguration);

	/**Checks if the {@link AgentRunner} identified by the {@link AgentConfiguration} has been deployed
	 * @param agentConfiguration the {@link AgentConfiguration} entry for the {@link AgentRunner}
	 * @return true if the {@link AgentRunner} identified in the {@link AgentConfiguration} has been deployed
	 */
	boolean isDeployed(AgentConfiguration agentConfiguration);

	/**Retrieve the {@link AgentRunner} identified by the {@link AgentConfiguration} 
	 * @param agentConfiguration the {@link AgentConfiguration} entry for the {@link AgentRunner}
	 * @return
	 */
	AgentRunner fetchAgentRunner(AgentConfiguration agentConfiguration);
}