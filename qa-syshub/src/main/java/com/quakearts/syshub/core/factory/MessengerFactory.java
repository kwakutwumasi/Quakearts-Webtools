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

import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.utils.SysHubUtils;
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.model.AgentConfigurationParameter;
import com.quakearts.syshub.model.AgentModule;

import java.util.Map;

import javax.enterprise.inject.spi.CDI;
import javax.naming.NamingException;

public class MessengerFactory {
	
	private static final String MESSENGER = "messenger:/";

	private MessengerFactory() {
	}

	private static MessengerFactory factory = new MessengerFactory();

	public static MessengerFactory getFactory() {
		return factory;
	}
	
    @SuppressWarnings("rawtypes")
	public Messenger getInstance(String instancename) throws ConfigurationException {
        Class messengerClass;
        try {
            messengerClass = Class.forName(instancename);
		} catch (ClassNotFoundException e) {
			throw new ConfigurationException(e.getMessage(), e);
		}
    
        if(!Messenger.class.isAssignableFrom(messengerClass))
        	throw new ConfigurationException(instancename+" does not implement "+Messenger.class.getName());
        
        @SuppressWarnings("unchecked")
		Messenger messenger = (Messenger) CDI.current().select(messengerClass).get();
        return messenger;
    }

    public Messenger getInstance(Map<String, AgentConfigurationParameter> parameters,
    		AgentModule module) throws ConfigurationException {
    	Messenger messenger;
    	if(module.getModuleName() != null && !module.getModuleName().trim().isEmpty()){
        	try {
				messenger =  (Messenger) SysHubUtils.getInitialContext().lookup(MESSENGER+module.getModuleName());
				if(messenger!=null)
					return messenger;
			} catch (NamingException e) {
			} catch (ClassCastException e) {
	            throw new ConfigurationException(module.getModuleName()+" bound to context does not implement "+Messenger.class.getName());
			}
        }    	

    	messenger = getInstance(module.getModuleClassName());
        messenger.setupWithConfigurationParameters(parameters);
        messenger.setAgentConfiguration(module.getAgentConfiguration().cloneById());

        if(module.getModuleName() != null && !module.getModuleName().trim().isEmpty()){
        	try {
				SysHubUtils.getInitialContext().bind(MESSENGER+module.getModuleName(), this);
			} catch (NamingException e) {
	            throw new ConfigurationException("Unable to bind "+module.getModuleName()+" to context.");
			}
        }

        return messenger;
    }
}
