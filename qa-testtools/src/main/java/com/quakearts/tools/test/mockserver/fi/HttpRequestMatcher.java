package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.model.HttpRequest;

@FunctionalInterface
public interface HttpRequestMatcher {
	boolean canMatch(HttpRequest request, HttpRequest requestToMatch);
}
