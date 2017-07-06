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
package com.quakearts.syshub.webapp.helpers.parameter;

import java.util.ArrayList;
import java.util.List;

public class AgentModuleDatabase {
	private AgentModuleDatabase() {
	}

	private static AgentModuleDatabase instance = new AgentModuleDatabase();

	public static AgentModuleDatabase getInstance() {
		return instance;
	}

	private List<String> dataSpoolers = new ArrayList<>();
	private List<String> messageFormatters = new ArrayList<>();
	private List<String> messengers = new ArrayList<>();
	private List<String> agentTriggers = new ArrayList<>();

	public List<String> getDataSpoolers() {
		return dataSpoolers;
	}

	public List<String> getMessageFormatters() {
		return messageFormatters;
	}

	public List<String> getMessengers() {
		return messengers;
	}

	public List<String> getAgentTriggers() {
		return agentTriggers;
	}

}
