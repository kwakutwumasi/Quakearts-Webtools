package test;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.callback.TokenCallback;

public class TestLoginModule implements LoginModule {

	Subject subject;
	CallbackHandler callbackHandler;
	
	public static boolean addDenyRole;
	public static boolean passwordFetched, usernameFetched, tokenFetched;
	public static boolean throwLoginException;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	@Override
	public boolean login() throws LoginException {
		NameCallback callback1 = new NameCallback("test");
		PasswordCallback callback2 = new PasswordCallback("test", false);
		TokenCallback callback3 = new TokenCallback();
		Callback[] callbacks = {callback1,
					callback2,
					callback3};

		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
		}
		usernameFetched = callback1.getName()!=null;
		passwordFetched = callback2.getPassword()!=null;
		tokenFetched = callback3.getTokenData()!=null;	
		
		if(throwLoginException) {
			throwLoginException = false;
			throw new LoginException("Throwing..");
		}
		
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		subject.getPrincipals().add(new OtherPrincipal("TestRole"));
		subject.getPrincipals().add(new UserPrincipal("testuser"));
		
		if(addDenyRole) {
			OtherPrincipal otherPrincipal = new OtherPrincipal("TestDenyRole");
			subject.getPrincipals().add(otherPrincipal);
		}
		
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
