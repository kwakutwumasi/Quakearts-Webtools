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

/**Interface for classes that provide result logging
 * @author kwakutwumasi-afriyie
 *
 */public interface ResultExceptionLogger {
		/**Get a list of {@linkplain ResultExceptionLog}s that have yet to be persisted
		 * @return the {@linkplain List} of {@linkplain ResultExceptionLog}s
		 */
	List<ResultExceptionLog> getUnpersistedResultExceptionLogs();
	/**Retrieve a {@linkplain ResultExceptionLog} using the result log ID
	 * @param relID the ID of the result log
	 * @return the {@linkplain ResultExceptionLog}
	 */
	ResultExceptionLog getResultExceptionLogByID(long relID);
	/**Log the {@linkplain Result}.
	 * @param agentConfiguration the {@linkplain AgentConfiguration} used to create the module
	 * @param agentModule the {@linkplain AgentModule} used to process the {@linkplain Result}
	 * @param e the Exception that caused the processing to fail
	 * @param result the {@linkplain Result} that caused the error
	 */
	void logResultException(AgentConfiguration agentConfiguration, AgentModule agentModule, Exception e, Result<?> result);
}
