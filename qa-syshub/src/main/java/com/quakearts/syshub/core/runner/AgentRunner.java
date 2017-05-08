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
package com.quakearts.syshub.core.runner;

import com.quakearts.syshub.agent.ProcessingAgent;
import com.quakearts.syshub.model.AgentConfiguration.RunType;

public interface AgentRunner {
	boolean isRunning();
	boolean isShutDown();
	boolean isInErrorState();
	boolean isInRestartRequiredMode();
	ProcessingAgent getProcessingAgent();
	RunType getRunType();
	boolean restart();
	boolean shutdown();
}
