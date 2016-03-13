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
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.util.HashPassword;
import com.quakearts.webapp.security.util.UtilityMethods;

import java.util.Iterator;

public class DatabaseLoginModule implements LoginModule {
	private Subject subject;
	private Group rolesgrp;
	private CallbackHandler callbackHandler;
	@SuppressWarnings("rawtypes")
	private Map sharedState;
	@SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
	private boolean loginOk = false, use_first_pass = false,
			load_profile_only = false, require_password_change = false,
			change_password = false;
	private String rolesgrpname, dsjndiname, authenticationquery, rolesquery,
			hashalgorithm, rolescolumns, salt, changePasswordRole,
			defaultPassword;
	private String[] defaultroles = null;
	private HashMap<String, String> userprof = new HashMap<String, String>();
	private static final Logger log = Logger
			.getLogger(DatabaseLoginModule.class.getName());
	private int iterations = 0;
	private boolean resOrientPort;
	private AttemptChecker checker;

	public DatabaseLoginModule() {
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
		authenticationquery = (String) options
				.get("database.authenticationquery");
		rolesquery = (String) options.get("database.rolesquery");
		rolescolumns = (String) options.get("database.rolescolumns");
		hashalgorithm = (String) options.get("database.hashalgorithm");
		String defaultroles_str = (String) options.get("database.defaultroles");

		resOrientPort = Boolean.parseBoolean((String) options
				.get("result_orientation_potrait"));
		require_password_change = Boolean.parseBoolean((String) options
				.get("require_password_change"));

		if (require_password_change) {
			changePasswordRole = (String) options.get("changePasswordRole");
			defaultPassword = (String) options.get("defaultPassword");
		}

		require_password_change = require_password_change
				&& (changePasswordRole != null) && (defaultPassword != null);

		salt = (String) options.get("salt_value");
		if (salt == null)
			salt = HashPassword.DEFAULT_SALT;

		Object iterationsValue = options.get("hash_iterations");
		if (iterationsValue != null)
			try {
				iterations = Integer.parseInt(iterationsValue.toString());
			} catch (Exception e) {
				log.log(Level.SEVERE,"Invalid value for hash_interations. Using default",
						e);
				iterations = 10;
			}

		if (rolesgrpname == null)
			rolesgrpname = new String("Roles");

        if(defaultroles_str != null){
        	defaultroles = defaultroles_str.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }

		use_first_pass = Boolean.parseBoolean((String) options
				.get("use_first_pass"));

		load_profile_only = Boolean.parseBoolean((String) options
				.get("load_profile_only"));

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
				+ authenticationquery
				+ "\n"
				+ "\t\tdatabase.rolesquery: "
				+ rolesquery
				+ "\n"
				+ "\t\tdatabase.rolename: "
				+ rolesgrpname
				+ "\n"
				+ "\t\tdatabase.hashalgorithm: "
				+ hashalgorithm
				+ "\n"
				+ "\t\tresult_orientation_potrait: "
				+ resOrientPort
				+ "\n"
				+ "\t\tsalt_value: "
				+ salt
				+ "\n"
				+ "\t\thash_iterations: "
				+ iterations
				+ "\n"
				+ "\t\tuse_first_pass: "
				+ use_first_pass
				+ "\n" + "\t\tload_profile_only: " + load_profile_only + "\n");
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
			con = ds.getConnection();

			if (use_first_pass) {
				if (sharedState != null) {
					log.fine("Using first pass....");
					Object loginDN_val = sharedState
							.get("javax.security.auth.login.name");
					Object password_val = sharedState
							.get("javax.security.auth.login.password");
					username = (loginDN_val != null && loginDN_val instanceof Principal) ? ((Principal) loginDN_val)
							.getName() : null;
					password = (char[]) ((password_val instanceof char[]) ? password_val : null);
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

				username = name.getName() == null ? name.getDefaultName()
						: name.getName();
				password = pass.getPassword();

				userprof.put("*username*", username);

				if (sharedState != null) {
					log.fine("Storing state....");
					UserPrincipal shareduser = new UserPrincipal(username);
					sharedState.put("javax.security.auth.login.name",
							shareduser);
					char[] sharedpass = password;
					sharedState.put("javax.security.auth.login.password",
							sharedpass);
				}
			}
			
			if (!load_profile_only) {
				if (username == null || password == null)
					throw new LoginException("Login/Password is null.");
				String passwordhash;
				HashPassword hash = new HashPassword(new String(password),
						hashalgorithm, iterations, salt);

				passwordhash = hash.toString();

				if (passwordhash == null)
					throw new DirectoryLoginException("Error logging in.");

				if (checker.isLocked(username))
					throw new DirectoryLoginException("Account is lockedout.");
				
				checker.incrementAttempts(username);
				
				if (log.isLoggable(Level.FINE))
					log.fine("Verifying subject " + username);
				stmt = con.prepareStatement(authenticationquery);
				stmt.setString(1, username);
				stmt.setString(2, passwordhash);
				rs = stmt.executeQuery();
				if (!rs.next()) {
					rs.close();
					stmt.close();
					throw new LoginException("Invalid Password.");
				}

				if (require_password_change) {
					if (passwordhash.equals(defaultPassword)) {
						change_password = true;
						sharedState.put("com.zenithbank.ChangePassword",
								Boolean.TRUE);
					}
				}

				rs.close();
				stmt.close();
				log.fine("Subject " + username + " verified.");
				checker.reset(username);
				if (sharedState != null) {
					sharedState.put("com.zenithbank.LoginOk",Boolean.TRUE);					
				}
			}
			log.fine("Getting roles for " + username + "...");
			if (rolesquery != null) {
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
						throw new DirectoryLoginException(
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

			password = null;
			loginOk = true;
			log.fine("Login is successful.");
			return loginOk;
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
				principalset.add(rolesgrp);
			}

			rolesgrp.addMember(new UserPrincipal(userprof.get("*username*")));

			String name;
			OtherPrincipal principal;
			if (change_password) {
				principal = new OtherPrincipal(changePasswordRole,
						"changePassword");
				rolesgrp.addMember(principal);
			} else {
				for (String key : userprof.keySet()) {
					name = userprof.get(key);
					principal = new OtherPrincipal(name != null ? name : "",
							key);
					rolesgrp.addMember(principal);
					if (log.isLoggable(Level.FINE))
						buffer.append("OtherPrincipal Attribute: " + key
								+ " - Name:" + name + "\n\t\t");
				}

				if (defaultroles != null)
					for (String role : defaultroles) {
						principal = new OtherPrincipal(role, "default");
						rolesgrp.addMember(principal);
						if (log.isLoggable(Level.FINE))
							buffer.append("OtherPrincipal Attribute: default - Name:"
									+ role + "\n\t\t");
					}
			}

			Enumeration<? extends Principal> members = rolesgrp.members();
			while (members.hasMoreElements()) {
				Principal type = members.nextElement();
				principalset.add(type);				
			}

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