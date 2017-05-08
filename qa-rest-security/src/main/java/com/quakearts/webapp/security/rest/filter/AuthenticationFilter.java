/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
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
import com.quakearts.webapp.security.rest.requestwrapper.AuthenticationServletRequestWrapper;
import com.quakearts.webapp.security.rest.util.Base64;

public class AuthenticationFilter extends JAASAuthenticatorBase implements Filter {

	private static final String BASIC = "Basic";
	private static final String BEARER = "Bearer";
	private boolean requireAuthorization;
	private String contextName;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		requireAuthorization = Boolean.parseBoolean(filterConfig.getInitParameter("requireAuthorization"));
		contextName = filterConfig.getInitParameter("contextName");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest 
				&& proceedWithAuthentication((HttpServletRequest) request)){
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
						
			if(httpRequest.getHeader("Authorization")!=null){
				String authorization = httpRequest.getHeader("Authorization");
				String type;
				if(authorization.indexOf(BEARER)!=-1){
					type = BEARER;
				} else if(authorization.indexOf(BASIC)!=-1){
					type = BASIC;
				} else {
					sendError(401,"{\"message\":\"Authorization parameter not understood:"+authorization+"\"}", httpResponse);
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
							contextName, httpRequest.getContextPath());
					try {
						authenticateViaByteCredentials(contextName);
					} catch (LoginException e) {
						sendError(403,"{\"message\":\"Authorization failed\"}", httpResponse);
						return;
					}
				} else {
					String[] authData; 
					try {						
						authData = Base64.decode(authenticationToken).split(":",2);
					} catch (IOException e) {
						sendError(401, "{\"message\":\"Auth data was not understood. Must be a valid Base64 encoded character string\"}", httpResponse);
						return;
					}
					
					if(authData.length!=2){
						sendError(401, "{\"message\":\"Auth data was not understood. Must be of the form username:password\"}", httpResponse);
					}
						
					init(authData[0], authData[1], httpRequest.getRemoteAddr(), httpRequest.getRemotePort(),
							buildHeaderMap(httpRequest), httpRequest.getLocalAddr(), httpRequest.getLocalPort(),
							contextName, httpRequest.getContextPath());
					
					try {
						authenticateViaUsernameAndPassword(contextName);
					} catch (LoginException e) {
						sendError(403,"{\"message\":\"Authorization failed\"}", httpResponse);
						return;
					}
				}
			} else if(httpRequest.getParameterMap().containsKey("j_username")
					&& httpRequest.getParameterMap().containsKey("j_password")) {
				String username = httpRequest.getParameter("j_username");
				String password = httpRequest.getParameter("j_password");
				
				init(username, password, httpRequest.getRemoteAddr(), httpRequest.getRemotePort(),
						buildHeaderMap(httpRequest), httpRequest.getLocalAddr(), httpRequest.getLocalPort(),
						contextName, httpRequest.getContextPath());

				try {
					authenticateViaUsernameAndPassword(contextName);
				} catch (LoginException e) {
					sendError(403,"{\"message\":\"Authorization failed\"}", httpResponse);
					return;
				}
			} else if(requireAuthorization){
				sendError(401, "{\"message\":\"Missing identity and credential parameters. Authentication required\"}", httpResponse);
				return;
			}
			
			chain.doFilter( new AuthenticationServletRequestWrapper(httpRequest), httpResponse);
		} else {
			chain.doFilter( request, response);			
		}
		
	}

	private void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException {
		httpResponse.setContentType("application/json");
		httpResponse.sendError(401,"{\"message\":\"Missing identity and credential parameters. Authentication required\"}");
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

	protected boolean proceedWithAuthentication(HttpServletRequest request){
		return true;
	}
}
