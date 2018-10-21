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
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.util.UtilityMethods;

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

import java.util.Iterator;

public class DatabaseServerLoginModule implements LoginModule {
	public static final String LOCKOUT_TIME = "lockout_time";
	public static final String MAX_TRY_ATTEMPTS = "max_try_attempts";
	public static final String AUTHENTICATE_ONLY = "authenticateOnly";
	public static final String USE_FIRST_PASS = "useFirstPass";
	public static final String DATABASE_DEFAULTROLES = "database.defaultroles";
	public static final String RESULT_ORIENTATION_POTRAIT = "result_orientation_potrait";
	public static final String DATABASE_CASE_SENSITIVITY = "database.case_sensitivity";
	public static final String DATABASE_ROLESCOLUMNS = "database.rolescolumns";
	public static final String DATABASE_ROLESQUERY = "database.rolesquery";
	public static final String DATABASE_JNDINAME = "database.jndiname";
	public static final String DATABASE_ROLENAME = "database.rolename";
	
	private static final Logger log = Logger
			.getLogger(DatabaseServerLoginModule.class.getName());
	
	private Subject subject;
	private Group rolesgrp;
	private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private String username;
	private char[] passwordChars;
	private boolean loginOk = false;
	private HashMap<String, String> userprof = new HashMap<>();
	private AttemptChecker checker;
	
	private boolean useFirstPass = false;
	private boolean authenticateOnly = false;
	private String rolesgrpname;
	private String dsjndiname;
	private String rolesquery;
	private String rolescolumns;
	private String caseSensitivity;
	private String[] defaultroles = null;
	private boolean resOrientPort;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		
		configureAuthentication(options);
		setCommonOptions(options);
		configureAttemptChecker(options);
	}

	private void configureAuthentication(Map<String, ?> options) {
		rolesgrpname = (String) options.get(DATABASE_ROLENAME);
		dsjndiname = (String) options.get(DATABASE_JNDINAME);
		rolesquery = (String) options.get(DATABASE_ROLESQUERY);
		rolescolumns = (String) options.get(DATABASE_ROLESCOLUMNS);
		caseSensitivity = (String) options.get(DATABASE_CASE_SENSITIVITY);
		resOrientPort = Boolean.parseBoolean((String) options
				.get(RESULT_ORIENTATION_POTRAIT));
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
		authenticateOnly = Boolean.parseBoolean((String) options.get(AUTHENTICATE_ONLY));
	}

	private void configureAttemptChecker(Map<String, ?> options) {
		String maxAttemptsStr= (String) options.get(MAX_TRY_ATTEMPTS);
		String lockoutTimeStr= (String) options.get(LOCKOUT_TIME);

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
		username = null;
		passwordChars = null;

		try {
			Callback[] callbacks = new Callback[2];

			if (useFirstPass && sharedState != null) {
				loadUsernameAndPassword();
			}

			if (!useFirstPass || username == null || passwordChars == null) {
				processCallbacks(callbacks);
			}
			
			runPreChecks();
			
			DataSource ds = getDataSource();		
			authenticateSubject(ds);
			
			if (!authenticateOnly && rolesquery != null) {
				loadProfile(ds);
			}

			performFinalActions();
			return loginOk;
		} catch (LoginException e) {
			throw e;
		} catch (LoginOperationException e) {
			passwordChars = null;
			log.log(Level.SEVERE, "Login failed.", e);
			return false;
		} finally {
			passwordChars = null;
		}
	}

	private DataSource getDataSource() throws LoginOperationException {
		InitialContext icx = UtilityMethods.getInitialContext();
		DataSource ds;
		try {
			ds = (DataSource) icx.lookup(dsjndiname);
		} catch (NamingException e) {
			throw new LoginOperationException("There was an exception accessing the data source", e);
		}
		return ds;
	}

	private void loadUsernameAndPassword() {
		log.fine("Using first pass....");
		Object loginPrincipal = sharedState
				.get("javax.security.auth.login.name");
		Object passwordVal = sharedState
				.get("javax.security.auth.login.password");
		username = (loginPrincipal instanceof Principal) ? ((Principal) loginPrincipal)
				.getName() : null;
		passwordChars = (passwordVal instanceof char[]) ?(char[]) passwordVal: null;
	}

	private void processCallbacks(Callback[] callbacks) throws LoginOperationException {
		log.fine("Authenticating....");
		NameCallback name = new NameCallback("Enter your username","annonymous");
		PasswordCallback pass = new PasswordCallback(
				"Enter your password:", false);
		callbacks[0] = name;
		callbacks[1] = pass;

		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginOperationException("Callback could not be processed", e);
		}

		username = (name.getName() == null ? name.getDefaultName()
				: name.getName()).trim();
		passwordChars = pass.getPassword();
	}

	private void runPreChecks() throws LoginException {
		if (username == null || passwordChars == null)
			throw new LoginException("Login/Password is null.");
		if (checker.isLocked(username))
			throw new LoginException("Account is locked out.");
		checker.incrementAttempts(username);
	}

	private void authenticateSubject(DataSource ds) throws LoginException {
		String passwordStr = prepareCredentials(); 
		try(Connection con = ds.getConnection(username, passwordStr)) {
			//
		} catch (SQLException e) {
			throw new LoginException("Username/password is invalid");
		}
	}

	private String prepareCredentials() {
		String passwordStr = new String(passwordChars);
		if(caseSensitivity!=null && !caseSensitivity.equalsIgnoreCase("none")){
			if(caseSensitivity.equalsIgnoreCase("upper")){
				username = username.toUpperCase();
				passwordStr = passwordStr.toUpperCase();
			} else if(caseSensitivity.equalsIgnoreCase("lower")){
				username = username.toLowerCase();
				passwordStr = passwordStr.toLowerCase();
			}
		}
		
		return passwordStr;
	}

	@SuppressWarnings("unchecked")
	private void performFinalActions() {
		checker.reset(username);
		loginOk = true;
		if (sharedState != null) {
			UserPrincipal shareduser = new UserPrincipal(username);
			Map<String, Object> sharedStateObj = (Map<String, Object>) sharedState;
			sharedStateObj.put("javax.security.auth.login.name",
					shareduser);
			sharedStateObj.put("javax.security.auth.login.password",
					passwordChars);			
			sharedStateObj.put("com.quakearts.LoginOk", loginOk);					
		}
	}

	private void loadProfile(DataSource ds) throws LoginOperationException {
		try(Connection con = ds.getConnection()){
			prepareAndLoadStatement(con);
		} catch (SQLException e) {
			throw new LoginOperationException("There was an error connecting to the databse", e);
		}
	}

	private void prepareAndLoadStatement(Connection con) throws SQLException {
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
				handleResultsPortrait(rs);
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

	private void handleResultsPortrait(ResultSet rs) throws SQLException {
		if (rs.next()) {
			int i = 1;
			do {
				userprof.put("role" + (i++), rs.getString(rolescolumns));
			} while (rs.next());
		}
	}

	public boolean commit() {
		if (loginOk) {
			Set<Principal> principalset = subject.getPrincipals();

			if (useFirstPass) {
				findRolesGroup(principalset);				
			}
			if (rolesgrp == null) {
				createRolesGroup(principalset);
			}

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
		        log.fine("Found existing roles group: "+rolesgrp.getName());
				break;
			}
		}
	}

	private void createRolesGroup(Set<Principal> principalset) {
		rolesgrp = new DirectoryRoles(rolesgrpname);
		principalset.add(rolesgrp);
	}

	private void loadRoles() {
		rolesgrp.addMember(new UserPrincipal(username));
		userprof.entrySet().forEach(entry->{
			OtherPrincipal principal;
			principal = new OtherPrincipal(entry.getValue() != null ? entry.getValue() : "", entry.getKey());
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
		Enumeration<? extends Principal> members = rolesgrp.members();
		while (members.hasMoreElements()) {
			Principal type = members.nextElement();
			principalset.add(type);				
		}
	}
	
	public boolean abort() {
		sharedState = null;
		passwordChars = null;
		username = null;
		userprof = new HashMap<>();
		loginOk = false;
		subject = null;
		rolesgrp = null;
		checker = null;
		callbackHandler = null;
		return true;
	}

	public boolean logout() {
		return abort();
	}

}
