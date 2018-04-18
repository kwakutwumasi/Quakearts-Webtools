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
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public abstract class TestResourceParent {

	private static boolean optionsWorked;

	public static void reset() {
		optionsWorked = false;
	}
	
	@OPTIONS
	@Produces(MediaType.TEXT_PLAIN)
	@ValidateOnExecution(type=ExecutableType.NONE)
	public @Size(min=10) String options() {
		optionsWorked = true;
		return "OK";
	}
	
	public static boolean optionsWorked() {
		return optionsWorked;
	}
}
