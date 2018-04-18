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

import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.store.exception.HttpMessageStoreException;
import com.quakearts.tools.test.mockserver.store.fi.HttpMessageStoreQuery;

public interface HttpMessageStore extends Iterable<HttpRequest>{
	void storeRequest(HttpRequest httpRequest) throws HttpMessageStoreException;
	HttpRequest findRequestIdentifiedBy(String id) throws HttpMessageStoreException;
	IntermediateResult findRequestsMatching(HttpMessageStoreQuery query) throws HttpMessageStoreException;
	
	public interface IntermediateResult {
		HttpRequest[] thenList();
		HttpRequest thenFetchOne();
	}
	
}
