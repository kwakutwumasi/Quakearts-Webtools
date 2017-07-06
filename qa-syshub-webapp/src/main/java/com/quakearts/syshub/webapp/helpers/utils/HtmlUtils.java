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
package com.quakearts.syshub.webapp.helpers.utils;

import java.io.IOException;
import java.io.StringWriter;

public class HtmlUtils {
	private HtmlUtils() {
	}
	
	public static String escape(String value){
		try {
			StringWriter stringWriter = new StringWriter();
			com.sun.faces.util.HtmlUtils.writeText(stringWriter, true, true, new char[1028], value.toCharArray());
			return stringWriter.toString();
		} catch (IOException e) {
			return "";
		}
	}
}
