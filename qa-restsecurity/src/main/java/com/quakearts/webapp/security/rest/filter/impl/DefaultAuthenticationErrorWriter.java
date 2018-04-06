package com.quakearts.webapp.security.rest.filter.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.filter.AuthenticationErrorWriter;

public class DefaultAuthenticationErrorWriter implements AuthenticationErrorWriter {

	@Override
	public void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException {
		httpResponse.setContentType("text/plain");
		httpResponse.sendError(code, message);
	}

}
