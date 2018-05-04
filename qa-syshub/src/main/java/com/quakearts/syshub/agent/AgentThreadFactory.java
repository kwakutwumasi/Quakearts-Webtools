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
package com.quakearts.syshub.agent;

import java.util.concurrent.ThreadFactory;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class AgentThreadFactory implements ThreadFactory {
	
	private String agentName;
	
	public AgentThreadFactory(String agentName) {
		this.agentName = agentName;
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r);
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.setName(agentName);
		return thread;
	}
}
