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

import com.quakearts.syshub.core.Message;
import com.quakearts.syshub.model.AgentConfiguration;
import com.quakearts.syshub.model.AgentModule;
import com.quakearts.syshub.model.ProcessingLog;

/**Interface for classes that provide message logging
 * @author kwakutwumasi-afriyie
 *
 */
public interface MessageLogger {

	/**Get a list of {@linkplain ProcessingLog}s that have yet to be persisted
	 * @return the {@linkplain List} of {@linkplain ProcessingLog}s
	 */
	List<ProcessingLog> getUnpersistedProcessingLogs();
	/**Retrieve a {@linkplain ProcessingLog} using the log ID
	 * @param logID the ID of the log
	 * @return the {@linkplain ProcessingLog}
	 */
	ProcessingLog getProcessingLogByID(long logID);
	/**Retrieve a {@linkplain ProcessingLog} using the message ID
	 * @param mid the ID of the message
	 * @return the {@linkplain ProcessingLog}
	 */
	ProcessingLog getProcessingLogByMid(String mid);
	/**Update a {@linkplain ProcessingLog}
	 * @param notificationLog
	 * @throws Exception
	 */
	void updateLog(ProcessingLog notificationLog) throws Exception;
	/**Log the {@linkplain Message}. Messages that are logged are considered complete
	 * @param agentConfiguration the {@linkplain AgentConfiguration} used to create the module
	 * @param agentModule the {@linkplain AgentModule} used to send the message
	 * @param mssg the message
	 * @param details details of the message processing result
	 * @param isError indictates that the log represents a processing error
	 */
	void logMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String details, boolean isError);
	/**Store the {@linkplain Message}. Messages that are stored are considered incomplete
	 * @param agentConfiguration the {@linkplain AgentConfiguration} used to create the module
	 * @param agentModule the {@linkplain AgentModule} used to send the message
	 * @param mssg the message
	 * @param details details of the message processing result
	 */
	void storeMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String details);
	/**queue the {@linkplain Message}. Messages that are queued are resent after an interval
	 * @param agentConfiguration the {@linkplain AgentConfiguration} used to create the module
	 * @param agentModule the {@linkplain AgentModule} used to send the message
	 * @param mssg the message
	 * @param details details of the message processing result
	 */
	void queueMessage(AgentConfiguration agentConfiguration, AgentModule agentModule, Message<?> mssg, String reason);

}
