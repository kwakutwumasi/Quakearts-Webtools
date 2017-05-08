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
import com.quakearts.syshub.model.ProcessingLog;

public interface MessageLogger {

	List<ProcessingLog> getUnpersistedLogs();

	void log(Message<?> mssg, String response, boolean isError);

	void store(Message<?> mssg, boolean isError);

	void store(Message<?> mssg, String details, boolean isError);

	void queue(Message<?> mssg, String reason);

	List<ProcessingLog> findMessagesByDetails(String messageDetails, Byte type, String errorStatus, String source)
			throws Exception;

	ProcessingLog getLogByID(long logID);

	ProcessingLog findMessageLogByMid(String mid);

	void updateLog(ProcessingLog notificationLog) throws Exception;

}
