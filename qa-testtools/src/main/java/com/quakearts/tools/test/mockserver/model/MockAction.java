package com.quakearts.tools.test.mockserver.model;

import com.quakearts.tools.test.mockserver.exception.MockServerProcessingException;

public interface MockAction {
	boolean requestMatches(HttpRequest httpRequest);
	HttpResponse executeAction() throws MockServerProcessingException;
}
