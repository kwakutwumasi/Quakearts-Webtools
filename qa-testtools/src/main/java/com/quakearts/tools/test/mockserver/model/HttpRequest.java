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
package com.quakearts.tools.test.mockserver.model;

import java.util.List;

public interface HttpRequest extends HttpMessage {
	String getMethod();
	String getResource();
	HttpResponse getResponse();
	List<String> getURIParameterValue(String name);
	boolean hasParameter(String name);
	byte[] getContentBytes();
	String getId();
}