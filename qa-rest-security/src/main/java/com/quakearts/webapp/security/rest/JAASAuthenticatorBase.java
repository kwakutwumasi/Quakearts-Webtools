package com.quakearts.webapp.security.rest;

import java.net.URI;
import java.net.URL;
import java.security.URIParameter;
import java.util.Map;
import java.util.logging.Logger;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;

import com.quakearts.webapp.security.rest.callback.TokenCallback;
import com.quakearts.webapp.security.rest.context.LoginContext;


public abstract class JAASAuthenticatorBase {
	private static Configuration jaasConfig;
	private static final Logger log = Logger.getLogger(JAASAuthenticatorBase.class.getName());
	
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
		
		authenticate((c)-> {
			for(Callback cb:c){
				if(cb instanceof NameCallback){
					((NameCallback)cb).setName(context.getIdentity());
				} else if(cb instanceof PasswordCallback){
					((PasswordCallback) cb).setPassword(context.getCredentials().toCharArray());
				}
			}
			context.clearSensitiveData();
		}, contextName);
	}
	
	protected void authenticateViaByteCredentials(String contextName) throws LoginException{
		final SecurityContext context = SecurityContext.getSecurityContext();

		authenticate((c)->{
			for(Callback cb:c){
				if(cb instanceof TokenCallback){
					((TokenCallback)cb).setTokenData(context.getCredentialData());
				}
			}
			
			context.clearSensitiveData();
		}, contextName);
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
		SecurityContext.getSecurityContext().setSubject(context.getSubject());
	}
}
