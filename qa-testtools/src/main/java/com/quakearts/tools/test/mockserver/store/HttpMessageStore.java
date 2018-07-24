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
package com.quakearts.tools.test.mockserver.store;

import com.quakearts.tools.test.mockserver.model.HttpMessage;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.store.exception.HttpMessageStoreException;
import com.quakearts.tools.test.mockserver.store.fi.HttpMessageStoreQuery;

/**Interface implemented by classes that provide storage for HTTP messages
 * @author kwakutwumasi-afriyie
 *
 */
public interface HttpMessageStore extends Iterable<HttpRequest>{
	/**Store the {@linkplain HttpRequest}
	 * @param httpRequest the {@linkplain HttpRequest}
	 * @throws HttpMessageStoreException if the message cannot be stored
	 */
	void storeRequest(HttpRequest httpRequest) throws HttpMessageStoreException;
	/**Finf the request with the specified ID
	 * @param id the ID of the message
	 * @return the {@linkplain HttpMessage}, if any
	 * @throws HttpMessageStoreException if the message cannot be retrieved
	 */
	HttpRequest findRequestIdentifiedBy(String id) throws HttpMessageStoreException;
	/**Fluid API for chaining {@link HttpMessageStoreQuery HttpMessageStoreQueries}
	 * @param query the {@link HttpMessageStoreQuery} to use
	 * @return an intermediate method for method chaining
	 * @throws HttpMessageStoreException if there was an error searching the HTTP store
	 */
	IntermediateResult findRequestsMatching(HttpMessageStoreQuery query) throws HttpMessageStoreException;
	
	/**Interface implemented by classes that act as the terminator
	 * to the {@linkplain HttpMessageStore#findRequestsMatching(HttpMessageStoreQuery)} method 
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public interface IntermediateResult {
		/**Find and return a list of {@link HttpRequest}s that match the criteria
		 * @return the list of {@link HttpRequest}s, if any
		 */
		HttpRequest[] thenList();
		/**Find and return a single {@link HttpRequest}s that matches the criteria
		 * @return the {@link HttpRequest}, if any
		 */
		HttpRequest thenFetchOne();
	}
	
}
