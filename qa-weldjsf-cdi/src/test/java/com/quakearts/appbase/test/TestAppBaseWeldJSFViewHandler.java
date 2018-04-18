/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.appbase.test;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import com.quakearts.webtools.faces.viewhandler.AppBaseWeldJSFViewHandler;

public class TestAppBaseWeldJSFViewHandler {

	@Test
	public void test() throws Exception {
		AppBaseWeldJSFViewHandler handler = new AppBaseWeldJSFViewHandler(null);
		assertThat(handler.appendExistingQueryString("/test", "parameter1=value1"),
				is("/test?parameter1=value1"));
		assertThat(handler.appendExistingQueryString("/test?parameter1=value1", "parameter2=value2"),
				is("/test?parameter1=value1&parameter2=value2"));
		assertThat(handler.appendExistingQueryString("/test?", "parameter1=value1&parameter2=value2"),
				is("/test?parameter1=value1&parameter2=value2"));
		assertThat(handler.appendExistingQueryString("/", "parameter1=value1&parameter2=value2"),
				is("/?parameter1=value1&parameter2=value2"));
		
		assertThat(handler.appendConversationParameter("/test", "cid", "2"),
				is("/test?cid=2"));
		assertThat(handler.appendConversationParameter("/test?parameter1=value1", "cid", "2"),
				is("/test?parameter1=value1&cid=2"));
		assertThat(handler.appendConversationParameter("/test?", "cid", "2"),
				is("/test?cid=2"));
		assertThat(handler.appendConversationParameter("/test?cid=2", "cid", "2"),
				is("/test?cid=2"));
		assertThat(handler.appendConversationParameter("/test?cid=3", "cid", "2"),
				is("/test?cid=3"));
	}

}
