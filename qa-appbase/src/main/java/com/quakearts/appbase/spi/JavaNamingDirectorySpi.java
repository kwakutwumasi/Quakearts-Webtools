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
package com.quakearts.appbase.spi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.quakearts.appbase.exception.ConfigurationException;

/**The SPI for JNDI implementations. Implementers should use this method to bootstrap the JNDI implementation
 * @author kwakutwumasi-afriyie
 *
 */
public interface JavaNamingDirectorySpi {
	
	/**An {@linkplain InitialContext} that can be used to access the Directory
	 * @return
	 */
	InitialContext getInitialContext();
	/** Create a {@linkplain Context} for discovering instances
	 * @param name the name of the root of the {@linkplain Context}
	 * @return the created {@linkplain Context}
	 * @throws NamingException if the {@linkplain Context} cannot be created
	 */
	Context createContext(String name) throws NamingException;
	/**Start the JNDI implementation
	 * @throws ConfigurationException if there is an error initializing the instance
	 */
	void initiateJNDIServices();
	/**Shutdown the JNDI implementation, cleaning up and releasing held resources.
	 * A successful call to {@link #initiateJNDIServices()} should be possible after this method exits
	 * @throws ConfigurationException if there is an error shutting down the instance
	 */
	void shutdownJNDIService();
}
