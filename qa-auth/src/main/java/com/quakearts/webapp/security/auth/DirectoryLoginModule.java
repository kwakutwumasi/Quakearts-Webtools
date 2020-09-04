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
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.ldap.LDAPConnection;
import com.quakearts.webapp.security.ldap.LDAPConnectionFactory;
import com.quakearts.webapp.security.ldap.LDAPObject;
import com.quakearts.webapp.security.ldap.impl.DefaultLDAPConnectionFactory;
import com.quakearts.webapp.security.util.HashPassword;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectoryLoginModule implements LoginModule{
    private static final String INVALID_PASSWORD = "Invalid password";
	private static final Logger log = Logger.getLogger(DirectoryLoginModule.class.getName());
	public static final String USE_FIRST_PASSPARAMETER = "use_first_pass";
	public static final String LDAP_SEARCH_ACCPARAMETER = "ldap.search.acc";
	public static final String LDAP_SEARCH_DNPARAMETER = "ldap.search.dn";
	public static final String LOCKOUT_TIMEPARAMETER = "lockout_time";
	public static final String MAX_TRY_ATTEMPTSPARAMETER = "max_try_attempts";
	public static final String DIRECTORY_ATTRIBUTESPARAMETER = "directory.attributes";
	public static final String DIRECTORY_DEFAULTROLESPARAMETER = "directory.defaultroles";
	public static final String LDAP_CREDENTIAL_PARAMETER = "ldap.password.param";
	public static final String LDAP_ALLOW_ANONYMOUSBINDPARAMETER = "ldap.allow.anonymousbind";
	public static final String LDAP_USEHASH = "ldap.use.hash";
	public static final String LDAP_FILTERPARAMETER = "ldap.filter";
	public static final String LDAP_SEARCH_BASE_DNPARAMETER = "ldap.search.baseDN";
	public static final String LDAP_COMPARE_USEPARAMETER = "ldap.compare.use";
	public static final String LDAP_SSL_USEPARAMETER = "ldap.ssl.use";
	public static final String LDAP_KEYSTOREPARAMETER = "ldap.keystore";
	public static final String LDAP_PORTPARAMETER = "ldap.port";
	public static final String LDAP_SERVERPARAMETER = "ldap.server";
	public static final String HASH_ITERATIONS = "hash_iterations";
	public static final String SALT_VALUE = "salt_value";
	public static final String USERNAME_AS_SALT = "username_as_salt";
	public static final String HASH_ALGORITHM = "hash_algorithm";

	private Subject subject;
    private CallbackHandler callbackHandler;
	private Map<String, ?> sharedState;
	private String username;
	private char[] password;
    private boolean loginOk=false;
    private LDAPObject userprof;
    private AttemptChecker checker;

    private int ldapPort;
    private String ldapHost;
	private String searchuser;
	private String searchpass;
	private String searchbasedn;
	private String filterParam;
	private String passwordParam;
	private boolean useFirstPass;
	private boolean usecompare;
	private boolean allowEmptyPassword;
    /* attribute variable must be a list of exactly 9 directory attributes corresponding to the following (in strict order)
     * (firstname), (surname), (email address), (unit), (department), (branch), (position), (grade), (staff number)
     * ex "givenname","sn","mail","costcenter","ou","sitelocation","title","employeetype","employeenumber"
     */
    private String[] attributes;
    private String[] defaultroles;
	private boolean usessl;
	private boolean usehash;
	private String hashalgorithm;
	private int iterations;
	private String salt;
	boolean usernameAsSalt;
	private LDAPConnectionFactory<? extends Exception> lDAPConnectionFactory;
	private static final LDAPConnectionFactory<? extends Exception> DEFAULT 
		= new DefaultLDAPConnectionFactory();
	
	public DirectoryLoginModule() {
		lDAPConnectionFactory = DEFAULT;
	}
	
	public DirectoryLoginModule setLdapConnectionFactoryAs(
			LDAPConnectionFactory<? extends Exception> lDAPConnectionFactory) {
		this.lDAPConnectionFactory = lDAPConnectionFactory;
		return this;
	}
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, 
			Map<String,?> sharedState, Map<String,?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        
        configureConnectionSettings(options);
        configureAuthentication(options);
        configureAttemptChecker(options);
        setCommonOptions(options);
    }

	private void configureConnectionSettings(Map<String,?> options) {
		String ldapPortString = null;
        ldapHost = (String) options.get(LDAP_SERVERPARAMETER);        	        	
        ldapPortString = (String) options.get(LDAP_PORTPARAMETER);
        String keyStorePath;
        keyStorePath = (String) options.get(LDAP_KEYSTOREPARAMETER);
    	usessl = Boolean.parseBoolean((String)options.get(LDAP_SSL_USEPARAMETER));
        if(usessl && System.getProperty("javax.net.ssl.trustStore")==null 
        		&& keyStorePath !=null){
           System.setProperty("javax.net.ssl.trustStore", keyStorePath);
        }
        int defaultPort = usessl?636:389;
        ldapPort = ldapPortString == null? (defaultPort):Integer.parseInt(ldapPortString);
	}

	private void configureAuthentication(Map<String,?> options) {
		usecompare = Boolean.parseBoolean((String) options.get(LDAP_COMPARE_USEPARAMETER));
		usehash = Boolean.parseBoolean((String) options.get(LDAP_USEHASH));
		if(usecompare && usehash) {
			configureHashing(options);
		}
		
        searchbasedn = (String) options.get(LDAP_SEARCH_BASE_DNPARAMETER);
    	searchuser = (String) options.get(LDAP_SEARCH_DNPARAMETER);
    	searchpass = (String) options.get(LDAP_SEARCH_ACCPARAMETER);
		
    	if(searchpass == null) {
    		searchpass = "";
    	}
    	
		if(searchuser==null) {
		    searchuser="";
			searchpass="";
		}
				
        passwordParam = (String) options.get(LDAP_CREDENTIAL_PARAMETER);
        filterParam = (String) options.get(LDAP_FILTERPARAMETER);
        allowEmptyPassword = Boolean.parseBoolean((String) options.get(LDAP_ALLOW_ANONYMOUSBINDPARAMETER));
        String attributesString = (String) options.get(DIRECTORY_ATTRIBUTESPARAMETER);
        if(attributesString!=null)
        	attributes = attributesString.split(";");
        else
        	attributes = new String[0];
	}

	private void configureHashing(Map<String, ?> options) {
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

	private void configureAttemptChecker(Map<String,?> options) {
		String maxAttemptsString = (String) options.get(MAX_TRY_ATTEMPTSPARAMETER);
        String lockoutTimeString = (String) options.get(LOCKOUT_TIMEPARAMETER);
        if(maxAttemptsString == null && lockoutTimeString == null){
        	checker = AttemptChecker.getChecker(ldapHost);
        } else {
	        int maxAttempts;
	        int lockoutTime;
	        try {
				maxAttempts = Integer.parseInt(maxAttemptsString);
			} catch (Exception e) {
				maxAttempts = 4;
			}
			
			try {
				lockoutTime = Integer.parseInt(lockoutTimeString);
			} catch (Exception e) {
				lockoutTime = 3_600_000;
			}
        	AttemptChecker.createChecker(ldapHost, maxAttempts, lockoutTime);
        	checker = AttemptChecker.getChecker(ldapHost);
        }
	}

	private void setCommonOptions(Map<String,?> options) {
        String defaultrolesString = (String) options.get(DIRECTORY_DEFAULTROLESPARAMETER);
        
        if(defaultrolesString != null){
        	defaultroles = defaultrolesString.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }               
        useFirstPass = Boolean.parseBoolean((String)options.get(USE_FIRST_PASSPARAMETER));
	}

	public boolean login() throws LoginException {        
        LDAPConnection<? extends Exception> conn = null;
        try {
	        if(useFirstPass && sharedState != null){
	            loadUsernameAndPassword();
	        }
	        
	        if(!useFirstPass || username==null || password==null){
				processCallbacks();
	        }
		        
	        runPreAuthenticationChecks();
	        conn = lDAPConnectionFactory.createConnection(usessl);
            attemptLogin(conn);
    		performFinalActions();
        } catch (LoginOperationException e) {
            handleLoginOperationException(e);
        } finally {
            closeAndCleanUp(conn);
        }
        return loginOk;
    }

	private void loadUsernameAndPassword() {
		Object loginPrincipal = sharedState.get("javax.security.auth.login.name");
		Object loginPassword = sharedState.get("javax.security.auth.login.password");
		username = (loginPrincipal instanceof Principal) ? ((Principal) loginPrincipal).getName() : null;
		password = (loginPassword instanceof char[]) ?(char[]) loginPassword:null;
	}

	private void processCallbacks() throws LoginOperationException {
		NameCallback name = new NameCallback("Enter your username.","annonymous");
		PasswordCallback pass = new PasswordCallback("Enter your password:",false);           
		Callback[] callbacks = new Callback[2];
		callbacks[0] = name;
		callbacks[1] = pass;

		try {
		    callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginOperationException("Callback could not be processed", e);
		}
		
		username = name.getName()==null? name.getDefaultName():name.getName();		
		password = pass.getPassword();	
	}

	private void runPreAuthenticationChecks() throws LoginException {
		if(username == null || password == null)
            throw new LoginException("Login/Password is null.");
        
        if(!allowEmptyPassword && password.length == 0)
            throw new LoginException("Password is empty.");
                
        if(checker.isLocked(username))
        	throw new LoginException("Account has been locked out.");
	}

	private void attemptLogin(LDAPConnection<? extends Exception> conn) 
			throws LoginOperationException, LoginException {
		username = username.trim();
		try {
			conn.connect(ldapHost, ldapPort);
			conn.bind(searchuser, searchpass.getBytes());
			userprof = conn.search(searchbasedn, filterParam+"="+username, attributes, false);
			String loginDN = null;
			loginDN = userprof.getDN();

			if (usecompare) {
				attemptCompare(conn, loginDN);
			} else {
				attemptBind(conn, loginDN);
			}			
		} catch (LoginException e) {
			throw e;
		} catch (Exception e) {
			throw new LoginOperationException("Unable to process login", e);
		}
	}

	private void attemptCompare(LDAPConnection<? extends Exception> conn, String loginDN) 
			throws LoginOperationException, LoginException {
		String ldapPassword;
		if(usehash){
			ldapPassword = new HashPassword(new String(password), hashalgorithm, 
					iterations, salt +(usernameAsSalt?username:"")).toString();
		} else {
			ldapPassword = new String(password);
		}

		try {
			if (!conn.compare(loginDN, new String[]{passwordParam, ldapPassword})) 
				throw new LoginException(INVALID_PASSWORD);
		} catch (LoginException e) {
			throw e;
		} catch (Exception e) {
			throw new LoginException(INVALID_PASSWORD);
		}
	}

	private void attemptBind(LDAPConnection<? extends Exception> conn, String loginDN) 
			throws LoginException, LoginOperationException {
		try {
			conn.disconnect();
		} catch (Exception e) {
			throw new LoginOperationException(e.getMessage());
		}
		byte[] ldapPassword = new String(password).getBytes();
		try {
			conn.bind(loginDN, ldapPassword);
		} catch (Exception e) {
			throw new LoginException(INVALID_PASSWORD);
		}
	}

	@SuppressWarnings("unchecked")
	private void performFinalActions() {
		if (sharedState != null){
			UserPrincipal shareduser = new UserPrincipal(username);
			Map<String, Object> sharedStateObj = (Map<String, Object>) sharedState;
			sharedStateObj.put("javax.security.auth.login.name", shareduser);
			sharedStateObj.put("javax.security.auth.login.password", new String(password).toCharArray());            
		}

		checker.reset(username);
		loginOk = true;
	}

	private void handleLoginOperationException(LoginOperationException ex) {
		log.log(Level.SEVERE, "Login processing failed due to an internal error", ex);
	}

	private void closeAndCleanUp(LDAPConnection<? extends Exception> conn) {
		if(conn!=null)
			try {
			    conn.disconnect();
			} catch (Exception e) {
			    log.log(Level.SEVERE, "Error disconnecting from LDAP server. ", e);
			}
		password = null;
	}

	public boolean commit() {
        if(loginOk){
        	
            if(attributes.length >= 3) {
	            addRoles();	            
            }
          
            addDefaultRoles();            
            userprof = null;
        }
        return loginOk;
    }

	private void addRoles() {
        StringBuilder buf = new StringBuilder();
		String[] attribute;
		attribute = userprof.getAttributeEntries().get(0);
		buf.append(attribute[1]);
		buf.append(" ");
		attribute = userprof.getAttributeEntries().get(1);
		buf.append(attribute[1]);
		
		NamePrincipal name = new NamePrincipal(buf.toString().trim());
		
		attribute = userprof.getAttributeEntries().get(2);
		EmailPrincipal email = new EmailPrincipal(attribute[1]);
		
		subject.getPrincipals().add(name);
		subject.getPrincipals().add(email);
		for(int index=3;index<attributes.length;index++) {
			attribute = userprof.getAttributeEntries().get(index);
			subject.getPrincipals().add(new OtherPrincipal(attribute[1], attribute[0]));
		}
	}

	private void addDefaultRoles() {
		subject.getPrincipals().add(new UserPrincipal(username));
		if(defaultroles!=null) {
			for(int i=0;i<defaultroles.length;i++){
				subject.getPrincipals().add(new OtherPrincipal(defaultroles[i]));
			}
		}
	}

	public boolean abort() {
    	subject = null;
        callbackHandler = null;
    	sharedState = null;
    	username = null;
    	password = null;
        checker = null;
        loginOk = false;
        userprof = null;
        return true;
    }

	public boolean logout() {
        return abort();
    }

}
