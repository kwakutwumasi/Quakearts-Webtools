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
package com.quakearts.syshub.agent.event;

import java.io.Serializable;

import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;

/**CDI event for informing observers of events in 
 * {@link com.quakearts.syshub.agent.ProcessingAgent}'s operations
 * @author kwakutwumasi-afriyie
 *
 */
public class ProcessingEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1909395729976964965L;

	private Throwable exception;
	private AgentConfiguration agentConfiguration;
	private AgentModule agentModule;
	private Object dataObject;
	
	public ProcessingEvent(Throwable exception, AgentConfiguration agentConfiguration, AgentModule agentModule) {
		this.exception = exception;
		this.agentConfiguration = agentConfiguration;
		this.agentModule = agentModule;
	}
	
	public ProcessingEvent(Throwable exception, AgentConfiguration agentConfiguration, AgentModule agentModule,
			Object dataObject) {
		this.exception = exception;
		this.agentConfiguration = agentConfiguration;
		this.agentModule = agentModule;
		this.dataObject = dataObject;
	}

	public Throwable getException() {
		return exception;
	}
	
	public AgentConfiguration getAgentConfiguration() {
		return agentConfiguration;
	}
	
	public AgentModule getAgentModule() {
		return agentModule;
	}
	
	public Object getDataObject() {
		return dataObject;
	}
}
