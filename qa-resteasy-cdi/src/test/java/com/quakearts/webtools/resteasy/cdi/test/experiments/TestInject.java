/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.cdi.test.experiments;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.quakearts.webtools.resteasy.cdi.test.helpers.TestParameter;

public interface TestInject {
	void sayHello();
	void testTransaction();
	TestParameter pullFromInputStream(InputStream in) throws IOException;
	void pushToOutputStream(TestParameter parameter, OutputStream out) throws IOException;
}
