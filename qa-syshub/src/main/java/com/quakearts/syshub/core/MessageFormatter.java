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

import com.quakearts.syshub.core.impl.ResultImpl;
import com.quakearts.syshub.core.metadata.AgentModuleValidator;
import com.quakearts.syshub.exception.ProcessingException;

/**
 * @author Kwaku Twumasi-Afriyie
 *
 */
public interface MessageFormatter extends AgentConfigurationModule, AgentModuleValidator {
    /**Method for generate messages from the Result objects passed in.
     * @param rlt {@link ResultImpl} object for generating messages
     * @return An array or Message objects
     * @throws ProcessingException
     */
    Message<?> formatdata(Result rlt) throws ProcessingException;
    /**Method to release and resources the formatter may be holding
     * 
     */
    void close();
}
