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
package com.quakearts.webapp.security.rest.context;

import java.util.Map;
import java.util.HashMap;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**A copy of {@link javax.security.auth.login.LoginContext LoginContext} that is ~70%
 * faster.
 * @author kwakutwumasi-afriyie
 *
 */
public class LoginContext {

	enum ModuleMethod {
		LOGIN_METHOD, COMMIT_METHOD,ABORT_METHOD,LOGOUT_METHOD;
	}
	private static final String OTHER = "other";
	private Subject subject = null;
	private boolean subjectProvided = false;
	private boolean loginSucceeded = false;
	private CallbackHandler callbackHandler;
	private Map<String, ?> state = new HashMap<>();

	private Configuration config;
	private ModuleHandle[] moduleStack;
	private ClassLoader contextClassLoader = null;

	private LoginException firstError = null;
	private LoginException firstRequiredError = null;
	private boolean success = false;

	private void init(String name) throws LoginException {
		if (config == null) {
			config = Configuration.getConfiguration();
		}

		AppConfigurationEntry[] entries = config.getAppConfigurationEntry(name);
		if (entries == null) {
			entries = config.getAppConfigurationEntry(OTHER);
			if (entries == null) {
				throw new LoginException("No LoginModules configured for name " + name);
			}
		}
		
		moduleStack = new ModuleHandle[entries.length];
		for (int i = 0; i < entries.length; i++) {
			moduleStack[i] = new ModuleHandle(entries[i], null);
		}

		contextClassLoader = Thread.currentThread().getContextClassLoader();
		if (contextClassLoader == null) {
			contextClassLoader = ClassLoader.getSystemClassLoader();
		}					
	}
	
    /**
     * Instantiate a new {@code LoginContext} object with a name,
     * a {@code Subject} to be authenticated,
     * a {@code CallbackHandler} object, and a login
     * {@code Configuration}.
     *
     * <p>
     *
     * @param name the name used as the index into the caller-specified
     *          {@code Configuration}. <p>
     *
     * @param subject the {@code Subject} to authenticate,
     *          or {@code null}. <p>
     *
     * @param callbackHandler the {@code CallbackHandler} object used by
     *          LoginModules to communicate with the user, or {@code null}.
     *          <p>
     *
     * @param config the {@code Configuration} that lists the
     *          login modules to be called to perform the authentication,
     *          or {@code null}.
     *
     * @exception LoginException if the caller-specified {@code name}
     *          does not appear in the {@code Configuration}
     *          and there is no {@code Configuration} entry
     *          for "<i>other</i>".
     *          <p>
     * @exception RestSecurityException if a SecurityManager is set,
     *          <i>config</i> is {@code null},
     *          and either the caller does not have
     *          AuthPermission("createLoginContext.<i>name</i>"),
     *          or if a configuration entry for <i>name</i> does not exist and
     *          the caller does not additionally have
     *          AuthPermission("createLoginContext.other")
     */
    public LoginContext(String name, Subject subject,
                        CallbackHandler callbackHandler,
                        Configuration config) throws LoginException {
        this.config = config;
        init(name);
        if (subject != null) {
            this.subject = subject;
            subjectProvided = true;
        }
        if (callbackHandler == null) {
            throw new LoginException("Parameter callbackHandler is required");
        } else {
            this.callbackHandler = callbackHandler;
        }
    }
    
	/**
	 * Perform the authentication.
	 *
	 * <p>
	 * This method invokes the {@code login} method for each LoginModule
	 * configured for the <i>name</i> specified to the {@code LoginContext}
	 * constructor, as determined by the login {@code Configuration}. Each
	 * {@code LoginModule} then performs its respective type of authentication
	 * (username/password, smart card pin verification, etc.).
	 *
	 * <p>
	 * This method completes a 2-phase authentication process by calling each
	 * configured LoginModule's {@code commit} method if the overall
	 * authentication succeeded (the relevant REQUIRED, REQUISITE, SUFFICIENT,
	 * and OPTIONAL LoginModules succeeded), or by calling each configured
	 * LoginModule's {@code abort} method if the overall authentication failed.
	 * If authentication succeeded, each successful LoginModule's {@code commit}
	 * method associates the relevant Principals and Credentials with the
	 * {@code Subject}. If authentication failed, each LoginModule's
	 * {@code abort} method removes/destroys any previously stored state.
	 *
	 * <p>
	 * If the {@code commit} phase of the authentication process fails, then the
	 * overall authentication fails and this method invokes the {@code abort}
	 * method for each configured {@code LoginModule}.
	 *
	 * <p>
	 * If the {@code abort} phase fails for any reason, then this method
	 * propagates the original exception thrown either during the {@code login}
	 * phase or the {@code commit} phase. In either case, the overall
	 * authentication fails.
	 *
	 * <p>
	 * In the case where multiple LoginModules fail, this method propagates the
	 * exception raised by the first {@code LoginModule} which failed.
	 *
	 * <p>
	 * Note that if this method enters the {@code abort} phase (either the
	 * {@code login} or {@code commit} phase failed), this method invokes all
	 * LoginModules configured for the application regardless of their
	 * respective {@code Configuration} flag parameters. Essentially this means
	 * that {@code Requisite} and {@code Sufficient} semantics are ignored
	 * during the {@code abort} phase. This guarantees that proper cleanup and
	 * state restoration can take place.
	 *
	 * <p>
	 *
	 * @exception LoginException
	 *                if the authentication fails.
	 */
	public void login() throws LoginException {

		loginSucceeded = false;

		if (subject == null) {
			subject = new Subject();
		}

		try {
			invoke(ModuleMethod.LOGIN_METHOD);
			invoke(ModuleMethod.COMMIT_METHOD);
			loginSucceeded = true;
		} catch (LoginException le) {
			try {
				invoke(ModuleMethod.ABORT_METHOD);
			} catch (LoginException le2) {
				throw le;
			}
			throw le;
		}
	}

	/**
	 * Logout the {@code Subject}.
	 *
	 * <p>
	 * This method invokes the {@code logout} method for each
	 * {@code LoginModule} configured for this {@code LoginContext}. Each
	 * {@code LoginModule} performs its respective logout procedure which may
	 * include removing/destroying {@code Principal} and {@code Credential}
	 * information from the {@code Subject} and state cleanup.
	 *
	 * <p>
	 * Note that this method invokes all LoginModules configured for the
	 * application regardless of their respective {@code Configuration} flag
	 * parameters. Essentially this means that {@code Requisite} and
	 * {@code Sufficient} semantics are ignored for this method. This guarantees
	 * that proper cleanup and state restoration can take place.
	 *
	 * <p>
	 *
	 * @exception LoginException
	 *                if the logout fails.
	 */
	public void logout() throws LoginException {
		if (subject == null) {
			throw new LoginException("Null subject. Logout called before login");
		}

		invoke(ModuleMethod.LOGOUT_METHOD);
	}

	/**
	 * Return the authenticated Subject.
	 *
	 * <p>
	 *
	 * @return the authenticated Subject. If the caller specified a Subject to
	 *         this LoginContext's constructor, this method returns the
	 *         caller-specified Subject. If a Subject was not specified and
	 *         authentication succeeds, this method returns the Subject
	 *         instantiated and used for authentication by this LoginContext. If
	 *         a Subject was not specified, and authentication fails or has not
	 *         been attempted, this method returns null.
	 */
	public Subject getSubject() {
		if (!loginSucceeded && !subjectProvided)
			return null;
		return subject;
	}

	private void clearState() {
		firstError = null;
		firstRequiredError = null;
		success = false;
	}

	private void throwException(LoginException originalError, LoginException le) throws LoginException {
		clearState();
		if(originalError != null) 
			throw originalError;

		throw le;
	}

	private void invoke(ModuleMethod methodName) throws LoginException {
		for (ModuleHandle moduleHandle: moduleStack) {
			try {
				initializeModule(moduleHandle);
				boolean status = executeModuleMethod(methodName, moduleHandle);
				if (status) {
					if (methodName !=ModuleMethod.ABORT_METHOD 
							&& methodName != ModuleMethod.LOGOUT_METHOD
							&& moduleHandle.entry
									.getControlFlag() == AppConfigurationEntry.LoginModuleControlFlag.SUFFICIENT
							&& firstRequiredError == null) {
						clearState();
						return;
					}
					success = true;
				}
			} catch (InstantiationException ie) {
				throwException(null, new LoginException("Unable to instantiate LoginModule " + ie.getMessage()));
			} catch (ClassNotFoundException cnfe) {
				throwException(null, new LoginException("Unable to find LoginModule class " + cnfe.getMessage()));
			} catch (IllegalAccessException iae) {
				throwException(null, new LoginException("Unable to access LoginModule " + iae.getMessage()));
			} catch (Exception ite) {
				LoginException le = getLoginException(ite);
				setFirstErrorParameters(methodName, moduleHandle, le);
			}
		}

		handleException();
	}

	private boolean executeModuleMethod(ModuleMethod methodName, ModuleHandle moduleHandle) throws LoginException {
		boolean status = false;
		switch (methodName) {
		case LOGIN_METHOD:
			status = moduleHandle.module.login();
			break;
		case COMMIT_METHOD:
			status = moduleHandle.module.commit();
			break;
		case LOGOUT_METHOD:
			status = moduleHandle.module.logout();
			break;
		case ABORT_METHOD:
			status = moduleHandle.module.abort();
			break;
		}
		return status;
	}

	private void initializeModule(ModuleHandle moduleHandle)
			throws ClassNotFoundException, LoginException, IllegalAccessException, InstantiationException {
		if (moduleHandle.module == null) {
			Class<?> c = Class.forName(moduleHandle.entry.getLoginModuleName(), true, contextClassLoader);

			if(!LoginModule.class.isAssignableFrom(c)){
				moduleHandle.module = new LoginModuleWrapper(c);
			} else {
				moduleHandle.module =(LoginModule) c.newInstance();
			}
			moduleHandle.module.initialize(subject, callbackHandler, state, moduleHandle.entry.getOptions());
		}
	}

	private LoginException getLoginException(Exception ite) {
		LoginException le;

		if (ite.getCause() instanceof LoginException) {

			le = (LoginException) ite.getCause();

		} else if (ite.getCause() instanceof SecurityException) {
			le = new LoginException("Security Exception");
			le.initCause(new SecurityException());
		} else {
			if(ite.getCause()!=null){
				java.io.StringWriter sw = new java.io.StringWriter();
				ite.getCause().printStackTrace(new java.io.PrintWriter(sw));
				sw.flush();
				le = new LoginException(sw.toString());
			} else {
				le = new LoginException("Exception of type " 
						+ ite.getClass().getName() 
						+ " was thrown. Message is " 
						+ ite.getMessage());
			}
		}
		return le;
	}

	private void setFirstErrorParameters(ModuleMethod methodName, ModuleHandle moduleHandle, LoginException le)
			throws LoginException {
		if (moduleHandle.entry.getControlFlag() == AppConfigurationEntry.LoginModuleControlFlag.REQUISITE) {
			if (methodName == ModuleMethod.ABORT_METHOD 
					|| methodName == ModuleMethod.LOGOUT_METHOD) {
				if (firstRequiredError == null)
					firstRequiredError = le;
			} else {
				throwException(firstRequiredError, le);
			}
		} else if (moduleHandle.entry
				.getControlFlag() == AppConfigurationEntry.LoginModuleControlFlag.REQUIRED) {
			if (firstRequiredError == null)
				firstRequiredError = le;
		} else {
			if (firstError == null)
				firstError = le;
		}
	}

	private void handleException() throws LoginException {
		if (firstRequiredError != null) {
			throwException(firstRequiredError, null);
		} else if (!success && firstError != null) {
			throwException(firstError, null);
		} else if (!success) {
			throwException(new LoginException("Login Failure: All modules ignored"), null);
		} else {
			clearState();
		}
	}

	private static class ModuleHandle {
		AppConfigurationEntry entry;
		LoginModule module;

		ModuleHandle(AppConfigurationEntry newEntry, LoginModule newModule) {
			this.entry = newEntry;
			this.module = newModule;
		}
	}
}
