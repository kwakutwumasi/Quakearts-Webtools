package test;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.weld.exceptions.NullInstanceException;

public class TestWrappedErrorThrowingModule {

	public static boolean throwInInitialize,
		throwInLogin, throwInCommit, throwInAbort,
		throwInLogout;
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		if(throwInInitialize) {
			throwInInitialize = false;
			throw new RuntimeException(new LoginException("Error"));
		}
	}

	public boolean login() throws LoginException {
		if(throwInLogin) {
			throwInLogin = false;
			throw new RuntimeException(new SecurityException("Error"));
		}
		return true;
	}

	public boolean commit() throws LoginException {
		if(throwInCommit) {
			throwInCommit = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		
		return true;
	}

	public boolean abort() throws LoginException {
		if(throwInAbort) {
			throwInAbort = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		return true;
	}

	public boolean logout() throws LoginException {
		if(throwInLogout) {
			throwInLogout = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		return true;
	}

}
