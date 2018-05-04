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
package com.quakearts.syshub.log;

import java.util.List;

import com.quakearts.syshub.core.Result;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ResultExceptionLog;

public interface ResultExceptionLogger {
	List<ResultExceptionLog> getUnpersistedResultExceptionLogs();
	ResultExceptionLog getResultExceptionLogByID(long relID);
	void logResultException(AgentConfiguration agentConfiguration, AgentModule agentModule, Exception e, Result<?> result);
}
