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

import java.net.URI;
import java.net.URL;
import java.security.URIParameter;
import java.util.Map;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;

import com.quakearts.webapp.security.auth.callback.TokenCallback;
import com.quakearts.webapp.security.rest.cache.AuthenticationCacheService;
import com.quakearts.webapp.security.rest.cache.AuthenticationCacheServiceFactory;
import com.quakearts.webapp.security.rest.context.LoginContext;

/**Base class for JAAS dependent authentication systems. 
 * It provides a mechanism for loading a JAAS configuration. This class
 * loads the configuration from a file named login.config loaded from the 
 * root of the classpath.
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class JAASAuthenticatorBase {
	private Configuration jaasConfig;
	private static final Logger log = Logger.getLogger(JAASAuthenticatorBase.class.getName());
	private AuthenticationCacheService cacheService;
	
	protected JAASAuthenticatorBase() {
		cacheService = AuthenticationCacheServiceFactory.findService();
	}
	
	protected void init(String username, String password, String remoteHost, int remotePort,
			Map<String, String> requestHeaders, String host, int port, String application, String applicationContext) {
		SecurityContext.getSecurityContext().init(username, password, remoteHost, remotePort, requestHeaders, host,
				port, application, applicationContext);
	}
	
	protected void init(byte[] credentials, String remoteHost, int remotePort,
			Map<String, String> requestHeaders, String host, int port, String application, String applicationContext){
		SecurityContext.getSecurityContext().init(credentials, remoteHost, remotePort, requestHeaders, host,
				port, application, applicationContext);
	}
	
	protected void authenticateViaUsernameAndPassword(String contextName) 
			throws LoginException {

		final SecurityContext context = SecurityContext.getSecurityContext();
		
		if(tryAuthenticateFromCache(context.getIdentity(), context.getCredentials(), contextName))
			return;
			
		authenticate((c)-> {
			for(Callback cb:c){
				if(cb instanceof NameCallback){
					((NameCallback)cb).setName(context.getIdentity());
				} else if(cb instanceof PasswordCallback){
					((PasswordCallback) cb).setPassword(context.getCredentials().toCharArray());
				}
			}
		}, contextName);
		
		trySaveSubject(context.getIdentity(), context.getCredentials(), context.getSubject(), contextName);
		context.clearSensitiveData();
	}
	
	protected void authenticateViaByteCredentials(String contextName) throws LoginException{
		final SecurityContext context = SecurityContext.getSecurityContext();

		authenticate((c)->{
			for(Callback cb:c){
				if(cb instanceof TokenCallback){
					((TokenCallback)cb).setTokenData(context.getCredentialData());
				}
			}			
		}, contextName);
		context.clearSensitiveData();
	}
	
	protected void authenticate(CallbackHandler handler, String contextName) 
			throws LoginException {
		if(jaasConfig == null){
			try {
				URL resource = Thread.currentThread().getContextClassLoader().
                        getResource("login.config");
                URI uri = resource.toURI();
                jaasConfig = Configuration.getInstance("JavaLoginConfig", 
                		new URIParameter(uri));
			} catch(Exception e){
				log.severe("Initialization Error");
				throw new LoginException("Initialization Error");
			}
		}
		
		LoginContext context = new LoginContext(contextName, null, handler, jaasConfig);
		context.login();
		context.getSubject().setReadOnly();
		SecurityContext.getSecurityContext().setSubject(context.getSubject());
	}
	
	private boolean tryAuthenticateFromCache(String identity, String authenticationData, String contextName) {
		Subject subject = null;
		if(cacheService!=null) {
			subject = cacheService.retrieveSubject(cacheService.getKey(identity, authenticationData, contextName));
			if(subject!=null)
				SecurityContext.getSecurityContext().setSubject(subject);
		}
		
		return subject != null;
	}
	
	private void trySaveSubject(String identity, String authenticationData, Subject subject, String contextName) {
		if(cacheService != null) {
			cacheService.saveSubject(cacheService.getKey(identity, authenticationData, contextName), subject);
		}
	}
	
}
