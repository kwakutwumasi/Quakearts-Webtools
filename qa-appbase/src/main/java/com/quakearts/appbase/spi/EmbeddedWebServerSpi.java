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

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.spi.beans.WebAppListener;

/**The SPI for Servlet Container implementations. Implementers should use this method to bootstrap the Servlet Container implementation
 * @author kwakutwumasi-afriyie
 *
 */
public interface EmbeddedWebServerSpi {
	/**Start the Servlet Container implementation
	 * @throws ConfigurationException if there is an error initializing the instance
	 */
	void initiateEmbeddedWebServer();
	/**Shutdown the Servlet Container implementation, cleaning up and releasing held resources.
	 * A successful call to {@link #initiateEmbeddedWebServer()} should be possible after this method exits
	 * @throws ConfigurationException if there is an error shutting down the instance
	 */
	void shutdownEmbeddedWebServer();
	/**Add a {@linkplain WebAppListener} to the Servlet Container. This listener will be attached to every
	 * application launched by the container. This method should be used by other SPI's that want to add
	 * web application listeners to Servlet applications in order to intercept and modify their behaviour as 
	 * required by the SPI. The Embdedded Web Server is guarunteed to be the last service started, as such
	 * SPI's are assured that calling this method during initialization will add the listener.
	 * Using this method after the Embedded Web Server is initialized may have no effect at all
	 * @param listener a {@linkplain WebAppListener}
	 * @throws ConfigurationException if the specified listener cannot be added.
	 */
	void addDefaultListener(WebAppListener listener) throws ConfigurationException;
}
