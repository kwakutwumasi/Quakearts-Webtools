package com.quakearts.webapp.security.rest.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface AuthenticationErrorWriter {
	void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException;
}
