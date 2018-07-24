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
package com.quakearts.syshub.core.metadata;

import com.quakearts.syshub.exception.ConfigurationException;

/**Interface for all modules that require validation after assembly
 * @author kwakutwumasi-afriyie
 *
 */
public interface AgentModuleValidator {
	/**Validate that the module has been configured properly
	 * @throws ConfigurationException if there was an error configuring the module
	 */
	void validate() throws ConfigurationException;
}
