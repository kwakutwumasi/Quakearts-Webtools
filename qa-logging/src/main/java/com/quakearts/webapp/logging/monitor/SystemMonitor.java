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
package com.quakearts.webapp.logging.monitor;

import javax.servlet.ServletConfig;

public interface SystemMonitor {
	public enum State{
		UP,
		DOWN,
		QUERYERROR;
	}
	
	public enum Type{
		WEBAPP,
		WEBSERVICE,
		SERVERSERVICE;
	}
	
	public State getState(MonitorTarget target);
	public void setProperties(ServletConfig config);
}
