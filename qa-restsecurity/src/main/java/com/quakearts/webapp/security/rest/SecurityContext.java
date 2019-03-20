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
package com.quakearts.webapp.security.rest;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.security.auth.Subject;

import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.rest.exception.SecurityContextException;
import com.quakearts.webapp.security.rest.util.PluginService;


/**This class holds information related to an authenticated user, such as the roles and credentials.
 * @author kwakutwumasi-afriyie
 *
 */
public final class SecurityContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439562144323581177L;
	
	private static class DefaultContextStorageService implements SecurityContextStorageService {
		private static final ThreadLocal<SecurityContext> securityContext = new InheritableThreadLocal<>();
		@Override
		public void storeContext(SecurityContext context) {
			securityContext.set(context);
		}

		@Override
		public SecurityContext retrieveContext() {
			return securityContext.get();
		}
		
		@Override
		public void removeContext() {
			securityContext.remove();
		}
	}
	
	private static final SecurityContextStorageService storageService = createSecurityContextStorageService();
	private static final SecurityContextPermission readCredentialsPermission = new SecurityContextPermission("SecurityContext", "read");
	
	SecurityContext() {
	}

	private static SecurityContextStorageService createSecurityContextStorageService() {
		SecurityContextStorageService service = PluginService.loadPlugin(SecurityContextStorageService.class);
		if(service!=null)
			return service;
		
		return new DefaultContextStorageService();
	}

	public static SecurityContext getCurrentSecurityContext() {
		if(storageService.retrieveContext()==null) {
			storageService.storeContext(new SecurityContext());
		}
		
		return storageService.retrieveContext();
	}
	
	private String identity;
	private String credentials;
	private byte[] credentialData;
	private String remoteHost;
	private int remotePort;
	private Map<String, String> requestHeaders;
	private String host;
	private int port;
	private String application;
	private String applicationContext;
	private String pathInfo;
	private Subject subject;

	void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getIdentity() {
		if(identity == null && subject != null){
			try {
				identity = subject.getPrincipals(UserPrincipal.class).iterator().next().getName();
			} catch (NullPointerException e) {
				//DO nothing
			}
		}
		return identity;
	}
	
	public Principal getUserPrincipal(){
		try {
			return subject==null?null:
				subject.getPrincipals(UserPrincipal.class).iterator().next();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	String getCredentials() {
		SecurityManager sm = System.getSecurityManager();
		if(sm!=null){
			sm.checkPermission(readCredentialsPermission);
		}
		
		return credentials;
	}

	void init(byte[] credentialData, String remoteHost,
			int remotePort, Map<String, String> requestHeaders, String host, int port, String application, String pathInfo,
			String applicationContext) {
		this.credentialData = credentialData;
		initCommonFields(remoteHost, remotePort, requestHeaders, host, port, application, pathInfo, applicationContext);
	}

	void init(String identity, String credentials, String remoteHost,
			int remotePort, Map<String, String> requestHeaders, String host, int port, String application, String pathInfo,
			String applicationContext) {
		if(identity == null)
			throw new SecurityContextException("Parameter 'identity' is required");

		this.identity = identity;
		this.credentials = credentials;
		initCommonFields(remoteHost, remotePort, requestHeaders, host, port, application, pathInfo, applicationContext);
	}

	private void initCommonFields(String remoteHost, int remotePort, Map<String, String> requestHeaders, String host,
			int port, String application, String pathInfo, String applicationContext) {
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.requestHeaders = requestHeaders;
		this.host = host;
		this.port = port;
		this.application = application;
		this.pathInfo = pathInfo;
		this.applicationContext = applicationContext;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getApplication() {
		return application;
	}

	public String getApplicationContext() {
		return applicationContext;
	}

	public String getPathInfo() {
		return pathInfo;
	}
	
	byte[] getCredentialData() {
		SecurityManager sm = System.getSecurityManager();
		if(sm!=null){
			sm.checkPermission(readCredentialsPermission);
		}

		return credentialData;
	}
	
	void clearSensitiveData(){
		credentialData = null;
		credentials = null;
	}
	
	public boolean isAuthenicated(){
		return subject!=null;
	}
	
	public void release() {
		storageService.removeContext();
	}
}
