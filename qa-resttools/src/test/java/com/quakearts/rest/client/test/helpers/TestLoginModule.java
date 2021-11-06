package com.quakearts.rest.client.test.helpers;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

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

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[] {
			new PasswordCallback("Test", false),
			new NameCallback("Test")
		};
		
		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			return false;
		}
		
		assert ((PasswordCallback)callbacks[0]).getPassword() != null;
		assert ((NameCallback)callbacks[1]).getName() != null;
		
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		Set<Principal> principals = subject.getPrincipals();
		principals.add(new UserPrincipal());
		principals.add(new RolePrincipal());
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return abort();
	}

}
