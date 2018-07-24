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
package com.quakearts.tools.test.mockserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.quakearts.tools.test.mockserver.configuration.Configuration;
import com.quakearts.tools.test.mockserver.exception.ConfigurationException;
import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.fi.DefaultAction;
import com.quakearts.tools.test.mockserver.model.MockAction;

/**Interface for MockServer.
 * <br />
 * MockServer provides an HTTP server for mocking HTTP calls to live services.
 * MockServers can be created by defining request and response mappings or by 
 * using recorded response request sessions. MockServer is inherently stateless.
 * However custom actions can be specified to enforce stateful behavior.
 * <br />
 * MockServer provides a fluid API for configuration
 * @author kwakutwumasi-afriyie
 *
 */
public interface MockServer {
	/**Initialize the mock server
	 * @throws MockServerRuntimeException
	 */
	void start() throws MockServerRuntimeException;
	/**Stop a running mock server
	 * @throws MockServerRuntimeException
	 */
	void stop() throws MockServerRuntimeException;
	/**Configure the mock server using the passed in configuration object
	 * @param configuration {@linkplain Configuration} to use
	 * @return this object for method chaining
	 * @throws ConfigurationException if there is an error configuring the server
	 */
	MockServer configure(Configuration configuration) throws ConfigurationException;
	/**Configure the mock server using the configuration loaded from the named file
	 * @param fileName the name of the file containing the configuration
	 * @return this object for method chaining
	 * @throws ConfigurationException if there is an error configuring the server
	 * @throws IOException if there is an error reading the file
	 */
	MockServer configureFromFile(String fileName) throws IOException, ConfigurationException;
	/**Configure the mock server using the configuration loaded from the file
	 * @param file the {@linkplain File} containing the configuration
	 * @return this object for method chaining
	 * @throws ConfigurationException if there is an error configuring the server
	 * @throws IOException if there is an error reading the file
	 */
	MockServer configureFromFile(File file) throws IOException, ConfigurationException;
	/**Configure the mock server using the configuration loaded from the input source
	 * @param inputStream the {@linkplain InputStream} the configuration source
	 * @return this object for method chaining
	 * @throws ConfigurationException if there is an error configuring the server
	 * @throws IOException if there is an error reading the file
	 */
	MockServer configureFromStream(InputStream inputStream) throws IOException, ConfigurationException;
	/**Add an action to be executed every time the mock server processes a request
	 * @param actions
	 * @return this object for method chaining
	 */
	MockServer addDefaultActions(DefaultAction... actions);
	/**Add an action to execute when the mock server processes a client
	 * @param mockActions
	 * @return this object for method chaining
	 */
	MockServer add(MockAction... mockActions);
}
