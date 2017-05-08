/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.logging.parser.impl;

import javax.servlet.ServletConfig;

import com.quakearts.webapp.logging.parser.LogPrinter;

public class DefaultPrinter implements LogPrinter {

	@Override
	public void setupParser(ServletConfig config) {
	}

	@Override
	public String format(String log, String contentType, String filter)
			throws UnsupportedOperationException, IllegalArgumentException {
		return log;
	}

	@Override
	public byte[] format(byte[] log, String contentType,String filter)
			throws UnsupportedOperationException, IllegalArgumentException {
		return log;
	}

}
