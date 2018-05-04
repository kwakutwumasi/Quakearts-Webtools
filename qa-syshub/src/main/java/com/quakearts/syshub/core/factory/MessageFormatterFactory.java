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
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.utils.SysHubUtils;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

import java.util.Map;

import javax.enterprise.inject.Vetoed;
import javax.enterprise.inject.spi.CDI;
import javax.naming.NamingException;

@Vetoed
public class MessageFormatterFactory {

	private static final String FORMATTER = "formatter:/";

	private MessageFormatterFactory() {
	}

	private static MessageFormatterFactory factory = new MessageFormatterFactory();

	public static MessageFormatterFactory getFactory() {
		return factory;
	}

	@SuppressWarnings("rawtypes")
	public MessageFormatter getInstance(String instancename) throws ConfigurationException {
		Class mssgFormatterClass;
		try {
			mssgFormatterClass = Class.forName(instancename);
		} catch (ClassNotFoundException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}

		if (!MessageFormatter.class.isAssignableFrom(mssgFormatterClass))
			throw new ConfigurationException(instancename + " does not implement " + MessageFormatter.class.getName());

		@SuppressWarnings("unchecked")
		MessageFormatter messageFormatter = (MessageFormatter) CDI.current().select(mssgFormatterClass).get();
		return messageFormatter;
	}

	public MessageFormatter getInstance(Map<String, AgentConfigurationParameter> parameters, AgentModule module)
			throws ConfigurationException {
		MessageFormatter messageFormatter;
		if (module.getModuleName() != null && !module.getModuleName().trim().isEmpty()) {
			try {
				messageFormatter = (MessageFormatter) SysHubUtils.getInitialContext()
						.lookup(FORMATTER + module.getModuleName());
				if (messageFormatter != null)
					return messageFormatter;
			} catch (NamingException e) {
			} catch (ClassCastException e) {
				throw new ConfigurationException(module.getModuleName() + " bound to context does not implement "
						+ MessageFormatter.class.getName());
			}
		}

		messageFormatter = getInstance(module.getModuleClassName());
		if (parameters != null)
			messageFormatter.setupWithConfigurationParameters(parameters);
		
		messageFormatter.setAgentConfiguration(module.getAgentConfiguration().cloneById());
		messageFormatter.setAgentModule(module.cloneById());
		messageFormatter.validate();

		if (module.getModuleName() != null && !module.getModuleName().trim().isEmpty()) {
			try {
				SysHubUtils.getInitialContext().bind(FORMATTER + module.getModuleName(), messageFormatter);
			} catch (NamingException e) {
				throw new ConfigurationException("Unable to bind " + module.getModuleName() + " to context.");
			}
		}
		
		return messageFormatter;
	}

	public void removeInstance(AgentModule module) {
		try {
			MessageFormatter formatter = (MessageFormatter) SysHubUtils.getInitialContext()
					.lookup(FORMATTER + module.getModuleName());
			formatter.close();
		} catch (Throwable e) {
			Main.log.error("Unable to stop Agent Module "+module.getModuleName(), e);
		}
		
		try {
			SysHubUtils.getInitialContext().unbind(FORMATTER + module.getModuleName());
		} catch (Throwable e) {
			Main.log.error("Unable to unbind Agent Module "+module.getModuleName(), e);
		}
	}

}
