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
