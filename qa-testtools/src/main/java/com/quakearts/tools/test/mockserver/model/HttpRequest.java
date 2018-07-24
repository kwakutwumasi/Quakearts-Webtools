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

/**Implemented by classes that represent an HTTP request
 * @author kwakutwumasi-afriyie
 *
 */
public interface HttpRequest extends HttpMessage {
	/**Get the HTTP method sent
	 * @return the HTTP method
	 */
	String getMethod();
	/**Get the resource represented by this request 
	 * @return the resource 
	 */
	String getResource();
	/**Get the {@linkplain HttpResponse} attached to this request
	 * @return the response
	 */
	HttpResponse getResponse();
	/**Get the URI parameters of the HTTP request
	 * @param name the URI parameter name
	 * @return the URI parameters
	 */
	List<String> getURIParameterValue(String name);
	/**Checks the presence of a URI parameter
	 * @param name the URI parameter name
	 * @return true if the parameter is present
	 */
	boolean hasParameter(String name);
	/**The ID used to store this HTTP message
	 * @return the ID
	 */
	String getId();
}