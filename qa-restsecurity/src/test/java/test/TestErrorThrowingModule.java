package test;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.jboss.weld.exceptions.NullInstanceException;

public class TestErrorThrowingModule implements LoginModule {

	public static boolean throwInInitialize,
		throwInLogin, throwInCommit, throwInAbort,
		throwInLogout, abortRan;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		if(throwInInitialize) {
			throwInInitialize = false;
			throw new RuntimeException(new LoginException("Error"));
		}
	}

	@Override
	public boolean login() throws LoginException {
		if(throwInLogin) {
			throwInLogin = false;
			throw new RuntimeException(new SecurityException("Error"));
		}
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		if(throwInCommit) {
			throwInCommit = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		abortRan = true;
		if(throwInAbort) {
			throwInAbort = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		if(throwInLogout) {
			throwInLogout = false;
			throw new RuntimeException(new NullInstanceException("Error"));
		}
		return true;
	}

}
