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
package com.quakearts.tools.test.mockserver.store.fi;

import com.quakearts.tools.test.mockserver.model.HttpRequest;

/**Functional interface for querying the HTTP message store
 * @author kwakutwumasi-afriyie
 *
 */
@FunctionalInterface
public interface HttpMessageStoreQuery {
	/**Determine if the request matches the required properties
	 * @param request the {@linkplain HttpRequest} to match
	 * @return true if the {@linkplain HttpRequest} matches the required properties 
	 */
	boolean matches(HttpRequest request);
}