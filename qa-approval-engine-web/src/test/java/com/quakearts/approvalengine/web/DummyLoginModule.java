package com.quakearts.approvalengine.web;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;

public class DummyLoginModule implements LoginModule {

	private Subject subject;
	private CallbackHandler callbackHandler;
	private String roles;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[]{ new NameCallback("Get", "Read.AE")};
		try {
			callbackHandler.handle(callbacks);
			roles = ((NameCallback)callbacks[0]).getName();
		} catch (IOException | UnsupportedCallbackException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		String[] rolesArray = roles.split(";");
		Set<Principal> principals = subject.getPrincipals();
		for(String role:rolesArray) {
			principals.add(new OtherPrincipal(role));
		}
		principals.add(new UserPrincipal("testuse"));
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return logout();
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
