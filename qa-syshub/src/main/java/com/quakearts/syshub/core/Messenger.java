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
import com.quakearts.syshub.exception.ConfigurationException;
import com.quakearts.syshub.exception.ProcessingException;
import com.quakearts.syshub.model.ProcessingLog;

import java.util.Properties;

/**This is the interface implemented by instances that process messages.
 * Typically a messenger will send the processed data over a transport channel.
 * This is the terminal operation in the message processing chain.
 * @author kwaku.twumasi@quakearts.com
 *
 */
public interface Messenger extends AgentConfigurationModule, AgentModuleValidator  {
    /**Method to send messages. Implementations should extract message and transport according to 
     * message transport protocol/specifications. Message may be plain text or a serialized object
     * for transport protocols requiring specialised objects.
     * 
	 * Implementation note: Message sending actions must not wait indefinitely, or else 
	 * thread starvation may occur as a result of the processing thread never terminating.
	 * Always have a timeout in order to release the thread to process other requests
     * @param mssg The message to be sent
     * @throws ProcessingException
     */
    void sendMessage(Message<?> mssg) throws ProcessingException;
    /**Method to setup messenger. Implementations may retrieve data source configuration information
     * from the properties passed in.
     * @param props a {@link Properties} object containing configuration information
     * @throws ConfigurationException
     */
    boolean confirmDelivery(ProcessingLog log);
    /**Checks compatibility with the MessageFormatter. This is to ensure the messenger is able to send messages
     * of the specified type
     * @param formatter
     * @return True if the formatter generates messages this messenger can handle
     */
    boolean isCompatibleWith(MessageFormatter formatter);
    /**Method to release resources. This method is called upon system shutdown
     * Any resource held by this messenger should be released.
     *  For graceful shutdown this method should block until all current messages are
     *  sent. 
     */    
    void close();
    
    default boolean isResendCapable() {
    	return false;
    }
    
}
