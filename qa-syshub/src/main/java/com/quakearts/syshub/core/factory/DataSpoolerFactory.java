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
package com.quakearts.syshub.core.factory;

import com.quakearts.appbase.Main;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.utils.SysHubUtils;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

import java.util.Map;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.CDI;
import javax.naming.NamingException;

/**Factory method for {@linkplain DataSpooler}
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class DataSpoolerFactory {

	private static final String SPOOLER = "spooler:/";

	private DataSpoolerFactory() {
	}

	private static DataSpoolerFactory factory = new DataSpoolerFactory();

	public static DataSpoolerFactory getFactory() {
		return factory;
	}

	/**Create the {@linkplain DataSpooler} implemented by the named class
	 * @param instancename the name of the class implementing {@linkplain DataSpooler}
	 * @return the created {@linkplain DataSpooler}
	 * @throws ConfigurationException if there is an error creating the {@linkplain DataSpooler}
	 */
	@SuppressWarnings("rawtypes")
	public DataSpooler getInstance(String instancename) throws ConfigurationException {
		Class dataSpoolerClass;
		try {
			dataSpoolerClass = Class.forName(instancename);
		} catch (ClassNotFoundException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}

		if (!DataSpooler.class.isAssignableFrom(dataSpoolerClass))
			throw new ConfigurationException(instancename + " does not implement " + DataSpooler.class.getName());

		@SuppressWarnings("unchecked")
		DataSpooler dataSpooler = (DataSpooler) CDI.current().select(dataSpoolerClass).get();
		return dataSpooler;
	}

	/**Create the {@linkplain DataSpooler} described by the {@linkplain AgentModule}
	 * @param parameters the {@linkplain Map} of {@linkplain AgentConfigurationParameter}s 
	 * to use in configuring the {@linkplain DataSpooler}. This method also registers the created in module in JNDI,
	 * if creating the module afresh. Note that if a module with the same name has already been created, 
	 * it will be returned; no new module will be created
	 * @param module the {@linkplain AgentModule} describing the {@linkplain DataSpooler}
	 * @return the created {@linkplain DataSpooler}
	 * @throws ConfigurationException if there is an error creating the {@linkplain DataSpooler}
	 */
	public DataSpooler getInstance(Map<String, AgentConfigurationParameter> parameters, AgentModule module)
			throws ConfigurationException {
		DataSpooler dataSpooler;
		if (module.getModuleName() != null && !module.getModuleName().trim().isEmpty()) {
			try {
				dataSpooler = (DataSpooler) SysHubUtils.getInitialContext().lookup(SPOOLER + module.getModuleName());
				if (dataSpooler != null)
					return dataSpooler;
			} catch (NamingException e) {
			} catch (ClassCastException e) {
				throw new ConfigurationException(
						module.getModuleName() + " bound to context does not implement " + DataSpooler.class.getName());
			}
		}

		dataSpooler = getInstance(module.getModuleClassName());
		if (parameters != null)
			dataSpooler.setupWithConfigurationParameters(parameters);
		dataSpooler.setAgentConfiguration(module.getAgentConfiguration());
		dataSpooler.setAgentModule(module);
		dataSpooler.validate();

		if (module.getModuleName() != null && !module.getModuleName().trim().isEmpty()) {
			try {
				SysHubUtils.getInitialContext().bind(SPOOLER + module.getModuleName(), dataSpooler);
			} catch (NamingException e) {
				throw new ConfigurationException("Unable to bind " + module.getModuleName() + " to context.");
			}
		}
		
		return dataSpooler;
	}

	/**Remove the registered {@linkplain DataSpooler}
	 * @param module the {@linkplain AgentModule} describing the {@linkplain DataSpooler}
	 */
	public void removeInstance(AgentModule module) {
		try {
			DataSpooler dataSpooler = (DataSpooler) SysHubUtils.getInitialContext()
					.lookup(SPOOLER + module.getModuleName());
			dataSpooler.close();
		} catch (Throwable e) {
			Main.log.error("Unable to stop Agent Module "+module.getModuleName(), e);
		}

		try {
			SysHubUtils.getInitialContext().unbind(SPOOLER + module.getModuleName());
		} catch (Throwable e) {
			Main.log.error("Unable to unbind Agent Module "+module.getModuleName(), e);
		}
	}

}
