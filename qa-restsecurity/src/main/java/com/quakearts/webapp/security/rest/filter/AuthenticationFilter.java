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
package com.quakearts.webapp.security.rest.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.LoginException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.quakearts.webapp.security.rest.JAASAuthenticatorBase;
import com.quakearts.webapp.security.rest.SecurityContext;
import com.quakearts.webapp.security.rest.exception.SecurityException;
import com.quakearts.webapp.security.rest.filter.impl.DefaultAuthenticationErrorWriter;
import com.quakearts.webapp.security.rest.requestwrapper.AuthenticationServletRequestWrapper;
import com.quakearts.webapp.security.rest.util.Base64;

public class AuthenticationFilter extends JAASAuthenticatorBase implements Filter {

	private static final String BASIC = "Basic";
	private static final String BEARER = "Bearer";
	private boolean requireAuthorization;
	private String contextName;
	private AuthenticationErrorWriter errorWriter;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		requireAuthorization = Boolean.parseBoolean(filterConfig.getInitParameter("requireAuthorization"));
		contextName = filterConfig.getInitParameter("contextName");
		String errorWriterClass;
		if((errorWriterClass = filterConfig.getInitParameter("errorWriterClass"))!=null) {
			try {
				errorWriter = Class.forName(errorWriterClass)
						.asSubclass(AuthenticationErrorWriter.class)
						.newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				throw new ServletException("Unable to initiate filter", e);
			}
		} else {
			errorWriter = new DefaultAuthenticationErrorWriter();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
						
		try {
			if(httpRequest.getHeader("Authorization")!=null){
				String authorization = httpRequest.getHeader("Authorization");
				String type;
				if(authorization.indexOf(BEARER)!=-1){
					type = BEARER;
				} else if(authorization.indexOf(BASIC)!=-1){
					type = BASIC;
				} else {
					sendError(403,"Authorization parameter not understood:"+authorization, httpResponse);
					return;
				}
				
				String authenticationToken = authorization.substring(authorization.indexOf(type+":")+7);
				authenticationToken = authenticationToken.trim();
				
				if(type == BEARER){
					byte[] authData; 
					if(httpRequest.getCharacterEncoding()!=null)
						authData = authenticationToken.getBytes(httpRequest.getCharacterEncoding());
					else
						authData = authenticationToken.getBytes();
					
					init(authData, httpRequest.getRemoteAddr(), httpRequest.getRemotePort(),
							buildHeaderMap(httpRequest), httpRequest.getLocalAddr(), httpRequest.getLocalPort(),
							httpRequest.getContextPath(), contextName);
					try {
						authenticateViaByteCredentials(contextName);
					} catch (LoginException e) {
						sendError(403,"Authorization failed", httpResponse);
						return;
					}
				} else {
					String[] authData; 
					try {						
						authData = Base64.decode(authenticationToken).split(":",2);
					} catch (IOException e) {
						sendError(403, "Auth data was not understood. Must be a valid Base64 encoded character string", httpResponse);
						return;
					}
					
					if(authData.length!=2){
						sendError(403, "Auth data was not understood. Must be of the form username:password", httpResponse);
						return;
					}
						
					init(authData[0], authData[1], httpRequest.getRemoteAddr(), httpRequest.getRemotePort(),
							buildHeaderMap(httpRequest), httpRequest.getLocalAddr(), httpRequest.getLocalPort(),
							httpRequest.getContextPath(), contextName);
					
					try {
						authenticateViaUsernameAndPassword(contextName);
					} catch (LoginException e) {
						sendError(403,"Authorization failed", httpResponse);
						return;
					}
				}
			} else if(httpRequest.getParameterMap().containsKey("qa_username")
					&& httpRequest.getParameterMap().containsKey("qa_password")) {
				String username = httpRequest.getParameter("qa_username");
				String password = httpRequest.getParameter("qa_password");
				
				init(username, password, httpRequest.getRemoteAddr(), httpRequest.getRemotePort(),
						buildHeaderMap(httpRequest), httpRequest.getLocalAddr(), httpRequest.getLocalPort(),
						httpRequest.getContextPath(), contextName);

				try {
					authenticateViaUsernameAndPassword(contextName);
				} catch (LoginException e) {
					sendError(403,"Authorization failed}", httpResponse);
					return;
				}
			} else if(requireAuthorization){
				sendError(401, "Missing identity/credential parameters. Authorization is required", httpResponse);
				return;
			} else {
				chain.doFilter( request, response);
				return;
			}
			chain.doFilter( new AuthenticationServletRequestWrapper(httpRequest), httpResponse);
		} catch (SecurityException e) {
			sendError(403, e.getMessage(), httpResponse);
		} finally {
			SecurityContext.getSecurityContext().release();				
		}
	}
	
	private void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException {
		errorWriter.sendError(code, message, httpResponse);
	}
	
	private Map<String, String> buildHeaderMap(HttpServletRequest httpRequest) {
		Map<String, String> headers = new HashMap<>();
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String header = (String) headerNames.nextElement();
			headers.put(header, httpRequest.getHeader(header));
		}
		
		return headers;
	}

	@Override
	public void destroy() {
	}

}
