package com.quakearts.tools.test.mockserver.fi;

import com.quakearts.tools.test.mockserver.model.HttpResponse;

@FunctionalInterface
public interface HttpResponseAction {
	HttpResponse perfomResponseAction(HttpResponse response);
}
