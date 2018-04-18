/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.webtools.resteasy.validation.test.helpers;

import javax.validation.constraints.Size;

public class TestParameter {
	@Size(min=5)
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public TestParameter setContent(String content) {
		this.content = content;
		return this;
	}
}
