package com.quakearts.tools.test.mockserver.store.fi;

import com.quakearts.tools.test.mockserver.model.HttpRequest;

@FunctionalInterface
public interface HttpMessageStoreQuery {
	boolean matches(HttpRequest request);
}