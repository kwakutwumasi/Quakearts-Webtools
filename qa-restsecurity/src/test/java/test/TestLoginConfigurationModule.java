package test;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class TestLoginConfigurationModule implements LoginModule {

	String failOnUsername;
	CallbackHandler callbackHandler;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		failOnUsername = (String) options.get("failOnUsername");
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		NameCallback callback;
		try {
			callback = new NameCallback("Test");
			callbackHandler.handle(new Callback[] {callback});
		} catch (IOException | UnsupportedCallbackException e) {
			return false;
		}
		
		if(callback.getName().contains(failOnUsername))
			throw new LoginException();
		
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		return true;
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