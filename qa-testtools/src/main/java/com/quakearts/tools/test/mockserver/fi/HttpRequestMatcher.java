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
package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.model.HttpRequest;

/**Functional interface for matching a saved HTTP request 
 * template with an incoming request
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface HttpRequestMatcher {
	/**Return a boolean to indicate whether the HTTP request matches stored HTTP request
	 * @param request the stored request
	 * @param incomingRequest the incoming request
	 * @return true if the request matches
	 */
	boolean canMatch(HttpRequest request, HttpRequest incomingRequest);
}
