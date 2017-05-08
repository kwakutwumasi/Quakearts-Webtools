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
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import javax.naming.InitialContext;
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
	private Subject subject;
	private Group rolesgrp;
	private CallbackHandler callbackHandler;
	@SuppressWarnings("rawtypes")
	private Map sharedState;
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
	private boolean loginOk = false, use_first_pass = false,
			authenticate_only = false;
	private String rolesgrpname, dsjndiname, rolesquery,
			rolescolumns,case_sensitivity;
	private String[] defaultroles = null;
	private HashMap<String, String> userprof = new HashMap<String, String>();
	private static final Logger log = Logger
			.getLogger(DatabaseServerLoginModule.class.getName());
	private boolean resOrientPort;
	private AttemptChecker checker;

	public DatabaseServerLoginModule() {
	}

	@SuppressWarnings("rawtypes")
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
		log.fine("Initializing....");
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;
		rolesgrpname = (String) options.get("database.rolename");
		dsjndiname = (String) options.get("database.jndiname");
		rolesquery = (String) options.get("database.rolesquery");
		rolescolumns = (String) options.get("database.rolescolumns");
		case_sensitivity = (String) options.get("database.case_sensitivity");
		
		String defaultroles_str = (String) options.get("database.defaultroles");

		resOrientPort = Boolean.parseBoolean((String) options
				.get("result_orientation_potrait"));

		if (rolesgrpname == null)
			rolesgrpname = new String("Roles");

        if(defaultroles_str != null){
        	defaultroles = defaultroles_str.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }

		use_first_pass = Boolean.parseBoolean((String) options
				.get("use_first_pass"));

		authenticate_only = Boolean.parseBoolean((String) options
				.get("authenticate_only"));

		String maxAttempts_str = (String) options.get("max_try_attempts");
		String lockoutTime_str = (String) options.get("lockout_time");

		if (maxAttempts_str == null && lockoutTime_str == null) {
			checker = AttemptChecker.getChecker(dsjndiname);
		} else {
			int maxAttempts, lockoutTime;
			try {
				maxAttempts = Integer.parseInt(maxAttempts_str);
			} catch (Exception e) {
				maxAttempts = 4;
			}

			try {
				lockoutTime = Integer.parseInt(lockoutTime_str);
			} catch (Exception e) {
				lockoutTime = 3600000;
			}
			AttemptChecker.createChecker(dsjndiname, maxAttempts, lockoutTime);
			checker = AttemptChecker.getChecker(dsjndiname);
		}

		log.fine("Initialization complete.\n"
				+ "\t\tDatabaseLoginModule options:\n"
				+ "\t\tdatabase.jndiname: "
				+ dsjndiname
				+ "\n"
				+ "\t\tdatabase.authenticationquery: "
				+ "\t\tdatabase.rolesquery: "
				+ rolesquery
				+ "\n"
				+ "\t\tdatabase.rolename: "
				+ rolesgrpname
				+ "\n"
				+ "\t\tdatabase.hashalgorithm: "
				+ "\t\tresult_orientation_potrait: "
				+ resOrientPort
				+ "\n"
				+ "\n"
				+ "\t\tuse_first_pass: "
				+ use_first_pass
				+ "\n" + "\t\tauthenticate_only: " + authenticate_only + "\n");
	}

	@SuppressWarnings("unchecked")
	public boolean login() throws LoginException {
		log.fine("Starting authentication......");
		String username = null;
		char[] password = null;
		InitialContext icx;
		DataSource ds;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Callback[] callbacks = new Callback[2];
			icx = UtilityMethods.getInitialContext();
			ds = (DataSource) icx.lookup(dsjndiname);

			if (use_first_pass) {
				if (sharedState != null) {
					log.fine("Using first pass....");
					Object loginDN_val = sharedState
							.get("javax.security.auth.login.name");
					Object password_val = sharedState
							.get("javax.security.auth.login.password");
					username = (loginDN_val != null && loginDN_val instanceof Principal) ? ((Principal) loginDN_val)
							.getName() : null;
					password = (password_val instanceof char[]) ?(char[]) password_val: null;
				}
			}

			if (!use_first_pass || username == null || password == null) {
				log.fine("Authenticating....");
				NameCallback name = new NameCallback("Enter your username");
				PasswordCallback pass = new PasswordCallback(
						"Enter your password:", false);
				callbacks[0] = name;
				callbacks[1] = pass;

				try {
					log.fine("Handling callback....");
					callbackHandler.handle(callbacks);
				} catch (UnsupportedCallbackException e) {
					throw new DirectoryLoginException(
							"Callback is not supported", e);
				} catch (IOException e) {
					throw new DirectoryLoginException(
							"IOException during call back", e);
				}

				username = (name.getName() == null ? name.getDefaultName()
						: name.getName()).trim();
				password = pass.getPassword();

				userprof.put("*username*", username);

				if (sharedState != null) {
					log.fine("Storing state....");
					UserPrincipal shareduser = new UserPrincipal(username);
					sharedState.put("javax.security.auth.login.name",
							shareduser);
					char[] sharedpass = new String(password).toCharArray();
					sharedState.put("javax.security.auth.login.password",
							sharedpass);
				}
			}
			if (username == null || password == null)
				throw new LoginException("Login/Password is null.");

			if (checker.isLocked(username))
				throw new LoginException("Account is lockedout.");
			
			checker.incrementAttempts(username);
			
			if (log.isLoggable(Level.FINE))
				log.fine("Verifying subject " + username);

			try {
				if(case_sensitivity!=null && !case_sensitivity.equalsIgnoreCase("none")){
					if(case_sensitivity.equalsIgnoreCase("upper")){
						con = ds.getConnection(username.toUpperCase(), new String(password).toUpperCase());
					} else if(case_sensitivity.equalsIgnoreCase("lower")){
						con = ds.getConnection(username.toLowerCase(), new String(password).toLowerCase());
					}
				} else {
					con = ds.getConnection(username, new String(password));
				}
			} catch (Exception e) {
				throw new LoginException("Username/password is invalid");
			}

			log.fine("Subject " + username + " verified.");
			checker.reset(username);
			if (sharedState != null) {
				sharedState.put("com.quakearts.LoginOk",Boolean.TRUE);					
			}
			
			if (!authenticate_only) {
				log.fine("Getting roles for " + username + "...");
				if (rolesquery != null) {
					con.close();
					con = ds.getConnection();
					stmt = con.prepareStatement(rolesquery);
					stmt.setString(1, username);
					rs = stmt.executeQuery();
	
					if (!resOrientPort) {
						if (rs.next()) {
							log.fine("Got profile. Loading....");
							String[] roles = rolescolumns.split(",");
							for (String role : roles) {
								userprof.put(role, rs.getString(role));
							}
						} else {
							throw new LoginException(
									"No profile found for " + username);
						}
					} else {
						if (rs.next()) {
							log.fine("Got profile. Loading....");
							int i = 1;
							do {
								userprof.put("" + (i++), rs.getString(rolescolumns));
							} while (rs.next());
							log.fine("Got roles: " + userprof);
						} else {
							throw new DirectoryLoginException(
									"No profile found for " + username);
						}
					}
					rs.close();
					stmt.close();
				}
			}

			password = null;
			loginOk = true;
			log.fine("Login is successful.");
			return loginOk;
		} catch (LoginException e) {
			throw e;
		} catch (Exception e) {
			password = null;
			log.log(Level.SEVERE, "Login failed.", e);
			return false;
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean commit() {
		if (loginOk) {
			log.fine("Comitting...");
			StringBuffer buffer = new StringBuffer();
			Set<Principal> principalset = subject.getPrincipals();

			if (use_first_pass) {
				log.fine("Fetching already existing roles group...");
				for (Iterator i = principalset.iterator(); i.hasNext();) {
					Object obj = i.next();
					if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
						rolesgrp = (Group) obj;
                        log.fine("Found existing roles group: "+rolesgrp.getName());
						break;
					}
				}				
			}
			if (rolesgrp == null) {
				log.fine("Creating roles group...");
				rolesgrp = new DirectoryRoles(rolesgrpname);
			}

			rolesgrp.addMember(new UserPrincipal(userprof.get("*username*")));

			String name;
			OtherPrincipal principal;
			for (String key : userprof.keySet()) {
				name = userprof.get(key);
				principal = new OtherPrincipal(name != null ? name : "", key);
				rolesgrp.addMember(principal);
				if (log.isLoggable(Level.FINE))
					buffer.append("OtherPrincipal Attribute: " + key + " - Name:" + name + "\n\t\t");
			}

			if (defaultroles != null)
				for (String role : defaultroles) {
					principal = new OtherPrincipal(role, "default");
					rolesgrp.addMember(principal);
					if (log.isLoggable(Level.FINE))
						buffer.append("OtherPrincipal Attribute: default - Name:" + role + "\n\t\t");
				}

			Enumeration<? extends Principal> members = rolesgrp.members();
			while (members.hasMoreElements()) {
				Principal type = members.nextElement();
				principalset.add(type);				
			}

			principalset.add(rolesgrp);
			if (log.isLoggable(Level.FINE))
				log.fine("Added \n\t\t" + buffer + "\n\t\tto rolesgrp "
						+ rolesgrp.getName());
			userprof = null;
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean abort() {
		if (loginOk) {
			userprof = null;
			Set princ = subject.getPrincipals();
			princ.remove(rolesgrp);
			return true;
		} else
			return false;
	}

	@SuppressWarnings("rawtypes")
	public boolean logout() {
		if (loginOk) {
			userprof = null;
			Set princ = subject.getPrincipals();
			princ.remove(rolesgrp);
			return true;
		} else
			return false;
	}

}
