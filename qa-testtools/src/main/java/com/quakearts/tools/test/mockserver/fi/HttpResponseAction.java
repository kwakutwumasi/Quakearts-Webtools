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
import com.quakearts.tools.test.mockserver.model.HttpResponse;

/**Functional interface for performing custom actions before sending an HTTP response
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface HttpResponseAction {
	/**Execute the response action
	 * @param request the {@linkplain HttpRequest} that was received
	 * @param response the {@linkplain HttpResponse} the default response stored with the message
	 * @return the {@link HttpResponse} to send
	 */
	HttpResponse perfomResponseAction(HttpRequest request, HttpResponse response);
}
