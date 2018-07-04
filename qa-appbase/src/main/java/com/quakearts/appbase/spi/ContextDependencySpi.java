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

import javax.enterprise.inject.spi.BeanManager;

import com.quakearts.appbase.exception.ConfigurationException;

/**The SPI for CDI implementations. Implementers should use this method to bootstrap the CDI implementation
 * @author kwakutwumasi-afriyie
 *
 */
public interface ContextDependencySpi {
	/**Start the CDI implementation
	 * @throws ConfigurationException if there is an error initializing the instance
	 */
	void initiateContextDependency();
	/**Shutdown the CDI implementation, cleaning up and releasing held resources.
	 * A successful call to {@link #initiateContextDependency()} should be possible after this method exits
	 * @throws ConfigurationException if there is an error shutting down the instance
	 */
	void shutDownContextDependency();
	/**Used to get the main class specified when the application starts. See {@link com.quakearts.appbase.Main Main}
	 * @param mainSingletonClass
	 * @return an instance of the main class
	 */
	<T> T getMainSingleton(Class<T> mainSingletonClass);
	/**Obtain the {@link BeanManager} implementation
	 * @return the {@link BeanManager}
	 */
	BeanManager getBeanManager();
}
