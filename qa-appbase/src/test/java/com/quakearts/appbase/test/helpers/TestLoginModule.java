package com.quakearts.appbase.test.helpers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class TestLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	private boolean loginOk;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		PasswordCallback passwordCallback = new PasswordCallback("Password", false);
		NameCallback nameCallback = new NameCallback("Name");
		try {
			callbackHandler.handle(new Callback[] {
				passwordCallback,nameCallback 
			});
		} catch (IOException | UnsupportedCallbackException e) {
		}

		if("test".equals(nameCallback.getName()) && "test".equals(new String(passwordCallback.getPassword())))
			loginOk = true;
		
		return loginOk;
	}

	@Override
	public boolean commit() throws LoginException {
		if(loginOk)
			subject.getPrincipals().addAll(Arrays.asList(new TestPrincipal(), new TestUserPrincipal()));
		
		return loginOk;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
