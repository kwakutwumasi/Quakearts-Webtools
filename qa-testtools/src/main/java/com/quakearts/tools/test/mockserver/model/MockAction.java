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

import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;

/**Implemented by classes that handle mock server actions
 * @author kwakutwumasi-afriyie
 *
 */
public interface MockAction {
	/**Determine if the request matches the stored HTTP request
	 * @param httpRequest the {@linkplain HttpRequest} to match
	 * @return true if the request matches
	 */
	boolean requestMatches(HttpRequest httpRequest);
	/**Execute the specified action using the HTTP request
	 * @param httpRequest the {@linkplain HttpRequest}
	 * @return the {@linkplain HttpResponse} to return
	 * @throws MockServerProcessingException if there was a problem executing the action
	 */
	HttpResponse executeAction(HttpRequest httpRequest) throws MockServerProcessingException;
}
