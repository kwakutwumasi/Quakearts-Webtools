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

public interface MockServer {
	void start() throws MockServerRuntimeException;
	void stop() throws MockServerRuntimeException;
	MockServer configure(Configuration configuration) throws ConfigurationException;
	MockServer configureFromFile(String fileName) throws IOException, ConfigurationException;
	MockServer configureFromFile(File file) throws IOException, ConfigurationException;
	MockServer configureFromStream(InputStream inputStream) throws IOException, ConfigurationException;
	MockServer addDefaultActions(DefaultAction... actions);
	MockServer add(MockAction... mockActions);
}
