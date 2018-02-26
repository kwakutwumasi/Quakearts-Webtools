package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.HttpResponse;

@FunctionalInterface
public interface HttpResponseAction {
	HttpResponse perfomResponseAction(HttpRequest request, HttpResponse response);
}
