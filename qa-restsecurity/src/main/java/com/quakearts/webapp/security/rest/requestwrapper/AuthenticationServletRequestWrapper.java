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
package com.quakearts.webapp.security.rest.requestwrapper;

import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.SecurityContext;
import com.quakearts.webapp.security.rest.filter.AuthenticationFilter;

/**Wrapper for the {@link javax.servlet.http.HttpServletRequest HttpServletRequest}. Redirects calls
 * to {@linkplain #getRemoteUser()},{@linkplain #isUserInRole(String)}, {@linkplain #getUserPrincipal()},
 * and {@linkplain #authenticate(HttpServletResponse)} to the {@linkplain SecurityContext}
 * @author kwakutwumasi-afriyie
 *
 */
public final class AuthenticationServletRequestWrapper extends HttpServletRequestWrapper {

	private static final String PASSWORD = "j_password";
	private static final String AUTHORIZATION_HEADER = "authorization";
	private static final String QUAKEARTS_AUTHENTICATION = "com.quakearts.security.AUTHENTICATION";

	public AuthenticationServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getAuthType() {
		if(SecurityContext.getCurrentSecurityContext().isAuthenicated())
			return QUAKEARTS_AUTHENTICATION;
		else 
			return null;
	}

	@Override
	public String getHeader(String name) {
		if(AUTHORIZATION_HEADER.equals(name.toLowerCase()))
			return null;
		
		return super.getHeader(name);
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		if(AUTHORIZATION_HEADER.equals(name.toLowerCase()))
			return null;

		return super.getHeaders(name);
	}

	@Override
	public boolean isUserInRole(String role) {
		Subject subject = SecurityContext.getCurrentSecurityContext().getSubject();
		if(subject==null)
			return false;
		
		for(Principal principal:subject.getPrincipals()){
			if(principal.getName().equals(role))
				return true;
		}
		
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		return SecurityContext.getCurrentSecurityContext()
				.getUserPrincipal();
	}
	
	@Override
	public String getRemoteUser() {
		Principal principal = getUserPrincipal();
		if(principal!=null)
			return principal.getName();
		
		return null;
	}

	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		if(SecurityContext.getCurrentSecurityContext().isAuthenicated())
			return true;
		
		response.sendError(401);
		return false;
	}

	@Override
	public void login(String username, String password) throws ServletException {
		throw new ServletException("login() method is not supported. Use "+AuthenticationFilter.class.getName());
	}

	@Override
	public void logout() throws ServletException {
	}

	@Override
	public String getParameter(String name) {
		if(PASSWORD.equals(name))
			return null;
		
		return super.getParameter(name);
	}

	@Override
	public String[] getParameterValues(String name) {
		if(PASSWORD.equals(name))
			return null;

		return super.getParameterValues(name);
	}

}
