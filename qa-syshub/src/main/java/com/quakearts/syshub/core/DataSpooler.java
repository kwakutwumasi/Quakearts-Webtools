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
package com.quakearts.syshub.core;

import com.quakearts.syshub.core.metadata.AgentModuleValidator;
import com.quakearts.syshub.exception.ProcessingException;

/**This is the interface implemented by instances that pull data from data sources.
 * @author kwaku.twumasi@quakearts.com
 *
 */
public interface DataSpooler extends AgentConfigurationModule, AgentModuleValidator {
	/** Main method call for dataspooler. Implementations should 
	 * return an {@link Iterator} for data retrieval. The data may not have as yet
	 * been fetched.
	 * 
	 * Implementation note: Data spooling actions must not wait indefinitely, or else 
	 * thread starvation may occur as a result of the processing thread never terminating.
	 * Always have a timeout in order to release the thread to process other requests
	 * @throws ProcessingException
	 */
	CloseableIterator prepare() throws ProcessingException;	
	/**Method to mark data as being successfully retrieved and transformed into a message.
	 * Called after message creation. Implementations should provide a mechanism for data
	 * to be marked to prevent re-generation of messages
	 * @param result {@link Result} object from which message has been generated
     * @param messages The messages that were generated and sent. useful for spoolers that also need data 
     * from the messaging destination. Message properties can hold this data
	 * @throws ProcessingException
	 */
	void updateData(Result<?> result, Message<?> message) throws ProcessingException;
    
	/**Method to clean up and release data source resources. This method will be called after hasMoreData()
     * returns false. Implementations should ensure that no more data retrieval can occur before
     * this method returns. 
     */
    void close();
}
