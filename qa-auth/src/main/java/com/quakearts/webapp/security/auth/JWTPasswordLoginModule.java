package com.quakearts.webapp.security.auth;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

public class JWTPasswordLoginModule extends JWTLoginModule {

	@Override
	protected byte[] handleCallbacks() throws IOException, 
		UnsupportedCallbackException, LoginException {
		PasswordCallback passwordCallback = new PasswordCallback("Enter your password", false);
		callbackHandler.handle(new Callback[] { passwordCallback });
		if (passwordCallback.getPassword() == null) {
			throw new LoginException("Credentials are missing");
		}
		return new String(passwordCallback.getPassword()).getBytes();
	}
}