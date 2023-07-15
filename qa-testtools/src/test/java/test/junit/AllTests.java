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
package test.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@SelectClasses({ BeanGeneratorTest.class, CollectionFactoryTest.class, PrimitivesTest.class,
	TestConfiguration.class, TestHttpMessageBuilder.class, TestHttpMessageStore.class,
	TestMockActionBuilder.class, TestMockingProxy.class, TestMockServer.class})

@Suite()
public class AllTests {}
