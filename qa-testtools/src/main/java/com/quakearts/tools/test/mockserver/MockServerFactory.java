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

import com.quakearts.tools.test.mockserver.impl.MockServerImpl;

/**Factory for creating {@linkplain MockServer} instances
 * @author kwakutwumasi-afriyie
 *
 */
public class MockServerFactory {
	protected static MockServerFactory instance;	
	private MockServerFactory() {
	}
	
	/**Get the static factory instance
	 * @return
	 */
	public static MockServerFactory getInstance() {
		if(instance == null)
			instance = new MockServerFactory();
		
		return instance;
	}
	
	/**Create a mocking server
	 * @return
	 */
	public MockServer getMockServer() {
		return new MockServerImpl();
	}
}
