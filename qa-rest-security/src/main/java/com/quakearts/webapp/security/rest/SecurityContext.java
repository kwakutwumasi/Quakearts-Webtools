package com.quakearts.webapp.security.rest;

import java.io.Serializable;
import java.util.Map;

import javax.security.auth.Subject;

import com.quakearts.webapp.security.rest.exception.SecurityContextException;

public final class SecurityContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2439562144323581177L;
	private static final ThreadLocal<SecurityContext> securityContext = new InheritableThreadLocal<SecurityContext>(){
		protected SecurityContext initialValue() {
			return new SecurityContext();
		};
	};
	
	private static final SecurityContextPermission READCREDENTIALSPERMISSION = new SecurityContextPermission("SecurityContext", "read");
	
	private SecurityContext() {
	}

	public static SecurityContext getSecurityContext() {
		return securityContext.get();
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
	private Subject subject;

	void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Subject getSubject() {
		return subject;
	}

	public String getIdentity() {
		return identity;
	}

	String getCredentials() {
		SecurityManager sm = System.getSecurityManager();
		if(sm!=null){
			sm.checkPermission(READCREDENTIALSPERMISSION);
		}
		
		return credentials;
	}

	void init(byte[] credentialData, String remoteHost,
			int remotePort, Map<String, String> requestHeaders, String host, int port, String application,
			String applicationContext) {
		if(this.identity == null)
			throw new SecurityContextException("Parameter 'identity' is required");
		
		if(this.identity != null){
			this.credentialData = credentialData;
			this.remoteHost = remoteHost;
			this.remotePort = remotePort;
			this.requestHeaders = requestHeaders;
			this.host = host;
			this.port = port;
			this.application = application;
			this.applicationContext = applicationContext;
		}
	}
	
	void init(String identity, String credentials, String remoteHost,
			int remotePort, Map<String, String> requestHeaders, String host, int port, String application,
			String applicationContext) {
		if(this.identity == null)
			throw new SecurityContextException("Parameter 'identity' is required");

		if(this.identity != null){
			this.identity = identity;
			this.credentials = credentials;
			this.remoteHost = remoteHost;
			this.remotePort = remotePort;
			this.requestHeaders = requestHeaders;
			this.host = host;
			this.port = port;
			this.application = application;
			this.applicationContext = applicationContext;
		}
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

	byte[] getCredentialData() {
		SecurityManager sm = System.getSecurityManager();
		if(sm!=null){
			sm.checkPermission(READCREDENTIALSPERMISSION);
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
}
