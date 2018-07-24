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
package com.quakearts.syshub.core.runner;

import com.quakearts.syshub.exception.ProcessingException;

/**Interface implemented by classes that run 
 * {@link com.quakearts.syshub.agent.ProcessingAgent ProcessingAgent}s
 * @author kwakutwumasi-afriyie
 *
 */
public interface RunAgentListener {
	void runAgent() throws ProcessingException;
}
