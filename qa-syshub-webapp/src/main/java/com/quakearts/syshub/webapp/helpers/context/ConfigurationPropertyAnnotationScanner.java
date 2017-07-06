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
package com.quakearts.syshub.webapp.helpers.context;

import com.quakearts.classannotationscanner.listener.ClassAnnotationScanningListener;
import com.quakearts.syshub.core.DataSpooler;
import com.quakearts.syshub.core.MessageFormatter;
import com.quakearts.syshub.core.Messenger;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperties;
import com.quakearts.syshub.core.metadata.annotations.ConfigurationProperty;
import com.quakearts.syshub.core.runner.AgentTrigger;
import com.quakearts.syshub.webapp.helpers.parameter.AgentModuleDatabase;

public class ConfigurationPropertyAnnotationScanner implements ClassAnnotationScanningListener {

	@Override
	public String[] getAnnotationsToListenFor() {
		return new String[]{ConfigurationProperty.class.getName(), ConfigurationProperties.class.getName()};
	}

	@Override
	public void handle(String className, String annotation) {
		try {
			Class<?> moduleClass = Class.forName(className);
			if(DataSpooler.class.isAssignableFrom(moduleClass)){
				AgentModuleDatabase.getInstance().getDataSpoolers().add(className);
			} 
			
			if(MessageFormatter.class.isAssignableFrom(moduleClass)){
				AgentModuleDatabase.getInstance().getMessageFormatters().add(className);
			} 
			
			if(Messenger.class.isAssignableFrom(moduleClass)){
				AgentModuleDatabase.getInstance().getMessengers().add(className);
			} 
			
			if(AgentTrigger.class.isAssignableFrom(moduleClass)){
				AgentModuleDatabase.getInstance().getAgentTriggers().add(className);
			}
		} catch (ClassNotFoundException e) {
		}
	}
}
