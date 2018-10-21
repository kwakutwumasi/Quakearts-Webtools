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
package com.quakearts.webapp.security.auth;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.util.HashPassword;
import com.quakearts.webapp.security.util.UtilityMethods;

import java.util.Iterator;

public class DatabaseLoginModule implements LoginModule {
	private static final Logger log = Logger.getLogger(DatabaseLoginModule.class.getName());
	public static final String LOAD_PROFILE_ONLY = "loadProfileOnly";
	public static final String USE_FIRST_PASS = "useFirstPass";
	public static final String LOCKOUT_TIME = "lockout_time";
	public static final String MAX_TRY_ATTEMPTS = "max_try_attempts";
	public static final String DATABASE_USEHASH = "database.usehash";
	public static final String HASH_ITERATIONS = "hash_iterations";
	public static final String SALT_VALUE = "salt_value";
	public static final String USERNAME_AS_SALT = "username_as_salt";
	public static final String RESULT_ORIENTATION_POTRAIT = "result_orientation_potrait";
	public static final String DATABASE_DEFAULTROLES = "database.defaultroles";
	public static final String HASH_ALGORITHM = "hash_algorithm";
	public static final String DATABASE_ROLESCOLUMNS = "database.rolescolumns";
	public static final String DATABASE_ROLESQUERY = "database.rolesquery";
	public static final String DATABASE_AUTHENTICATIONQUERY = "database.authenticationquery";
	public static final String DATABASE_JNDINAME = "database.jndiname";
	public static final String DATABASE_ROLENAME = "database.rolename";

	private Subject subject;
	private Group rolesgrp;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private boolean loginOk;
	private String username;
	private char[] passwordChars;
	private HashMap<String, String> userprof = new HashMap<>();
	private AttemptChecker checker;

	private boolean useFirstPass;
	private boolean loadProfileOnly;
	private boolean useHash;
	private String rolesgrpname;
	private String dsjndiname;
	private String authenticationquery;
	private String rolesquery;
	private boolean resOrientPort;
	private String[] defaultroles;
	private String hashalgorithm;
	private String rolescolumns;
	private String salt;
	private int iterations;
	private boolean usernameAsSalt;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;

		configureAuthenticationQuery(options);		
		configureHashing(options);
		setCommonOptions(options);
		configureAttemptChecker(options);
	}

	private void configureAuthenticationQuery(Map<String, ?> options) {
		rolesgrpname = (String) options.get(DATABASE_ROLENAME);
		dsjndiname = (String) options.get(DATABASE_JNDINAME);
		authenticationquery = (String) options.get(DATABASE_AUTHENTICATIONQUERY);
		rolesquery = (String) options.get(DATABASE_ROLESQUERY);
		rolescolumns = (String) options.get(DATABASE_ROLESCOLUMNS);

		resOrientPort = Boolean.parseBoolean((String) options
				.get(RESULT_ORIENTATION_POTRAIT));
	}

	private void configureHashing(Map<String, ?> options) {
		if(options.containsKey(DATABASE_USEHASH)) {
			useHash = Boolean.parseBoolean((String) options
					.get(DATABASE_USEHASH));
			hashalgorithm = (String) options.get(HASH_ALGORITHM);
			salt = (String) options.get(SALT_VALUE);
			if (salt == null)
				salt = System.getProperty("com.quakearts."+SALT_VALUE, HashPassword.DEFAULT_SALT);
	
			usernameAsSalt = Boolean.parseBoolean((String) options
					.get(USERNAME_AS_SALT));
	
			Object iterationsValue = options.get(HASH_ITERATIONS);
			if (iterationsValue != null)
				try {
					iterations = Integer.parseInt(iterationsValue.toString());
				} catch (Exception e) {
					log.log(Level.SEVERE,"Invalid value for hash_interations. Using default", e);
				}
		}
	}

	private void setCommonOptions(Map<String, ?> options) {
		if (rolesgrpname == null)
			rolesgrpname = "Roles";

		String defaultrolesStr = (String) options.get(DATABASE_DEFAULTROLES);
		if(defaultrolesStr != null){
        	defaultroles = defaultrolesStr.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }

		useFirstPass = Boolean.parseBoolean((String) options.get(USE_FIRST_PASS));
		loadProfileOnly = Boolean.parseBoolean((String) options.get(LOAD_PROFILE_ONLY));
	}

	private void configureAttemptChecker(Map<String, ?> options) {
		String maxAttemptsStr = (String) options.get(MAX_TRY_ATTEMPTS);
		String lockoutTimeStr = (String) options.get(LOCKOUT_TIME);

		if (maxAttemptsStr == null && lockoutTimeStr == null) {
			checker = AttemptChecker.getChecker(dsjndiname);
		} else {
			int maxAttempts;
			int lockoutTime;
			try {
				maxAttempts = Integer.parseInt(maxAttemptsStr);
			} catch (Exception e) {
				maxAttempts = 4;
			}

			try {
				lockoutTime = Integer.parseInt(lockoutTimeStr);
			} catch (Exception e) {
				lockoutTime = 3600000;
			}
			AttemptChecker.createChecker(dsjndiname, maxAttempts, lockoutTime);
			checker = AttemptChecker.getChecker(dsjndiname);
		}
	}

	public boolean login() throws LoginException {
		try {
			if (useFirstPass && sharedState != null) {
				loadUsernameAndPassword();
			}

			if (!useFirstPass || username == null || passwordChars == null) {
				processCallbacks();
			}
			
			runPreAuthenticationChecks();
			processAuthentication();
			performFinalActions();
			return loginOk;
		} catch (LoginOperationException e) {
			log.log(Level.SEVERE, "Login processing failed due to an internal error", e);
			return false;
		} finally {
			passwordChars = null;
		}
	}

	private void loadUsernameAndPassword() {
		Object loginPrincipal = sharedState.get("javax.security.auth.login.name");
		Object passwordVal = sharedState.get("javax.security.auth.login.password");
		username = (loginPrincipal instanceof Principal) ? ((Principal) loginPrincipal).getName() : null;
		passwordChars = (passwordVal instanceof char[]) ? (char[]) passwordVal: null;
	}

	private void processCallbacks() throws LoginOperationException {
		NameCallback name = new NameCallback("Enter your username","annonymous");
		PasswordCallback pass = new PasswordCallback("Enter your passwordChars:", false);
		Callback[] callbacks = new Callback[2];
		callbacks[0] = name;
		callbacks[1] = pass;

		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginOperationException("Callback could not be processed", e);
		}

		username = (name.getName() == null ? name.getDefaultName(): name.getName()).trim();
		passwordChars = pass.getPassword();
	}

	private void processAuthentication() throws LoginException, LoginOperationException {
		InitialContext icx = UtilityMethods.getInitialContext();
		DataSource ds = getDataSource(icx);
		try(Connection con = ds.getConnection()){
			if (!loadProfileOnly) {
				String password = preparePassword();
				authenticateSubject(con, password);
			}
	
			if (rolesquery != null) {
				loadRoles(con);
			}
		} catch (SQLException e) {
			throw new LoginOperationException("Unable to process query", e);
		}
	}

	private DataSource getDataSource(InitialContext icx) throws LoginOperationException {
		DataSource ds;
		try {
			ds = (DataSource) icx.lookup(dsjndiname);
		} catch (NamingException e) {
			throw new LoginOperationException("Unable to get data source", e);
		}
		return ds;
	}

	private void runPreAuthenticationChecks() throws LoginException {
		if (username == null || passwordChars == null)
			throw new LoginException("Username/Password is null.");
		if (checker.isLocked(username))
			throw new LoginException("Account is locked out.");
		checker.incrementAttempts(username);
	}

	private String preparePassword() throws LoginOperationException {
		String passwordhash = new String(passwordChars);
		if(useHash){
			passwordhash = new HashPassword(passwordhash, hashalgorithm, 
					iterations, salt +(usernameAsSalt?username:"")).toString();
			
			if (passwordhash.isEmpty())
				throw new LoginOperationException("Error generating password hash.");
		}
		
		return passwordhash;
	}

	private void authenticateSubject(Connection con, String password)
			throws SQLException, LoginException {
		try(PreparedStatement stmt = con.prepareStatement(authenticationquery)){
			stmt.setString(1, username);
			stmt.setString(2, password);
			checkUsernamAndPassword(stmt);
		}
	}

	private void checkUsernamAndPassword(PreparedStatement stmt) throws SQLException, LoginException {
		try(ResultSet rs = stmt.executeQuery()){
			if (!rs.next()) {
				throw new LoginException("Invalid Password.");
			}
		}
	}

	private void loadRoles(Connection con) throws SQLException {
		try(PreparedStatement stmt = con.prepareStatement(rolesquery)){
			stmt.setString(1, username);
			executeAndProcessResultSet(stmt);
		}
	}

	private void executeAndProcessResultSet(PreparedStatement stmt) throws SQLException {
		try(ResultSet rs = stmt.executeQuery()){
			if (!resOrientPort) {
				handleResultsLandscape(rs);
			} else {
				handleResultsPotrait(rs);
			}
		}
	}

	private void handleResultsLandscape(ResultSet rs) throws SQLException {
		if (rs.next()) {
			String[] roles = rolescolumns.split(",");
			for (String role : roles) {
				userprof.put(role, rs.getString(role));
			}
		}
	}

	private void handleResultsPotrait(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int i = 1;
			do {
				userprof.put("role" + (i++), rs.getString(rolescolumns));
			} while (rs.next());
		}
	}

	@SuppressWarnings("unchecked")
	private void performFinalActions() {
		loginOk = true;
		checker.reset(username);
		if (sharedState != null) {
			UserPrincipal shareduser = new UserPrincipal(username);
			Map<String, Object> sharedStateObj = ((Map<String, Object>)sharedState);
			sharedStateObj.put("javax.security.auth.login.name", shareduser);
			sharedStateObj.put("javax.security.auth.login.password", passwordChars);
			sharedStateObj.put("com.quakearts.LoginOk", loginOk);					
		}
	}

	public boolean commit() {
		if (loginOk) {
			Set<Principal> principalset = subject.getPrincipals();

			if (useFirstPass) {
				findRolesGroup(principalset);
			}
			
			createRolesGroup(principalset);

			loadRoles();
			loadDefaultRoles();

			addPrincipalSetToSubject(principalset);

			userprof = null;
			return true;
		} else {
			return false;
		}
	}

	private void findRolesGroup(Set<Principal> principalset) {
		for (Iterator<Principal> i = principalset.iterator(); i.hasNext();) {
			Object obj = i.next();
			if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
				rolesgrp = (Group) obj;
				break;
			}
		}
	}

	private void createRolesGroup(Set<Principal> principalset) {
		if (rolesgrp == null) {
			rolesgrp = new DirectoryRoles(rolesgrpname);
			principalset.add(rolesgrp);
		}
	}

	private void loadRoles() {
		userprof.entrySet().forEach(entry ->{
			OtherPrincipal principal;
			principal = new OtherPrincipal(entry.getValue() != null ? entry.getValue() : "",
					entry.getKey());
			rolesgrp.addMember(principal);
		});
	}

	private void loadDefaultRoles() {
		OtherPrincipal principal;
		if (defaultroles != null) {
			int count = 1;
			for (String role : defaultroles) {
				principal = new OtherPrincipal(role, "default"+(count++));
				rolesgrp.addMember(principal);
			}
		}
	}

	private void addPrincipalSetToSubject(Set<Principal> principalset) {
		rolesgrp.addMember(new UserPrincipal(username));
		Enumeration<? extends Principal> members = rolesgrp.members();
		while (members.hasMoreElements()) {
			Principal type = members.nextElement();
			principalset.add(type);				
		}
	}

	public boolean abort() {
		useHash = false;
		hashalgorithm = null;
		defaultroles = null;
		salt = null;
		usernameAsSalt = false;
		rolesgrpname = null;
		subject = null;
		rolesgrp = null;
		callbackHandler = null;
		sharedState = null;
		loginOk = false;
		checker = null;
		username = null;
		passwordChars = null;
		userprof = new HashMap<>();
		return true;
	}

	public boolean logout() {
		return abort();
	}

}
