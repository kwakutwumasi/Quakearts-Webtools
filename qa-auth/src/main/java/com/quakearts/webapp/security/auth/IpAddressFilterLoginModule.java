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

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.jacc.PolicyContext;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

import com.quakearts.webapp.security.auth.util.AttemptChecker;

public class IpAddressFilterLoginModule implements LoginModule {
	public static final String USE_FIRST_PASSPARAMETER = "use_first_pass";
	public static final String LOCKOUT_TIMEPARAMETER = "lockout_time";
	public static final String MAX_TRY_ATTEMPTSPARAMETER = "max_try_attempts";
	public static final String ROLENAMEPARAMETER = "rolename";
	public static final String DEFAULTROLESPARAMETER = "defaultroles";
	public static final String ALLOW_ANONYMOUSPARAMETER = "allow.anonymous";
	public static final String PROPERTIES_FILEPARAMETER = "properties.file";
	public static final String DEFAULT_AUTH_GRPPARAMETER = "com.quakearts.webapp.security.auth";
    private Group rolesgrp;
    private Subject subject;
    private CallbackHandler callbackHandler;
    @SuppressWarnings("rawtypes")
	private Map sharedState;
    private boolean loginOk=false, usefirstPass;
	private boolean allowEmptyPassword;
    private AttemptChecker checker;
    private static final Logger log = Logger.getLogger(IpAddressFilterLoginModule.class.getName());
    private String[] defaultroles;
    private String rolesStr,propertiesFile,ipAddress,rolesgrpname;
    private Properties properties = new Properties();
    		
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
        log.fine("Initializing....");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;

        propertiesFile  = (String) options.get(PROPERTIES_FILEPARAMETER);
        allowEmptyPassword = Boolean.parseBoolean((String) options.get(ALLOW_ANONYMOUSPARAMETER));
        String defaultroles_str = (String) options.get(DEFAULTROLESPARAMETER);
        rolesgrpname = (String) options.get(ROLENAMEPARAMETER);

        String maxAttempts_str = (String) options.get(MAX_TRY_ATTEMPTSPARAMETER);
        String lockoutTime_str = (String) options.get(LOCKOUT_TIMEPARAMETER);
        
        if(maxAttempts_str == null && lockoutTime_str == null){
        	checker = AttemptChecker.getChecker(DEFAULT_AUTH_GRPPARAMETER);
        }else{
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
        	AttemptChecker.createChecker(DEFAULT_AUTH_GRPPARAMETER, maxAttempts, lockoutTime);
        	checker = AttemptChecker.getChecker(DEFAULT_AUTH_GRPPARAMETER);
        }

        if(defaultroles_str != null){
        	defaultroles = defaultroles_str.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }

        usefirstPass = Boolean.parseBoolean((String)options.get(USE_FIRST_PASSPARAMETER));
        
        log.fine("Initialization complete.\n"+
                "\t\tIpAddressFilterLoginModule options:\n"+
                "\t\tallowEmptyPassword: "+allowEmptyPassword+"\n"+
                "\t\tdefaultroles_str: "+defaultroles_str+"\n"+
                "\t\tmaxAttempts_str: "+maxAttempts_str+"\n"+
                "\t\tlockoutTime_str: "+lockoutTime_str+"\n"+
                "\t\tuse_first_pass: "+usefirstPass+"\n"+
                "\t\trolesgrpname: "+rolesgrpname);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[2];
        String username=null;
        byte[] password = null;

        if(usefirstPass){
            if(sharedState != null){
                log.fine("Using first pass....");
                Object loginDN_val = sharedState.get("javax.security.auth.login.name");
                Object password_val = sharedState.get("javax.security.auth.login.password");
                username = (loginDN_val!=null && loginDN_val instanceof Principal)?((Principal) loginDN_val).getName():null;
                password = (password_val!=null && password_val instanceof char[])?new String((char[]) password_val).getBytes():null;
            }
        }
        
        if(!usefirstPass || username==null || password==null){
            NameCallback name = new NameCallback("Enter your username.");
            PasswordCallback pass = new PasswordCallback("Enter your password:",false);           
            callbacks[0] = name;
            callbacks[1] = pass;

            try {
                log.fine("Handling callback....");
                callbackHandler.handle(callbacks);
            } catch (UnsupportedCallbackException e) {
                throw new LoginException("Callback is not supported");
            } catch (IOException e) {
                throw new LoginException("IOException during call back");
            }
            
            username = name.getName()==null? name.getDefaultName():name.getName();
            password = (new String(pass.getPassword())).getBytes();
            
            if (sharedState != null){
                log.fine("Storing state....");
                UserPrincipal shareduser = new UserPrincipal(username);
                sharedState.put("javax.security.auth.login.name", shareduser);
                char[] sharedpass = new String(password).toCharArray();
                sharedState.put("javax.security.auth.login.password", sharedpass);
            }
        }
        
        try {
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile);
			properties.load(in);
			in.close();

			HttpServletRequest request = (HttpServletRequest) PolicyContext.getContext("javax.servlet.http.HttpServletRequest");
			ipAddress = request.getRemoteAddr();
			log.fine("Attempted access from "+ipAddress);
		} catch (Exception e) {
			log.severe("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage());
			return false;
		}

		rolesStr = properties.getProperty(ipAddress);
		if(rolesStr == null || ipAddress == null){
			throw new LoginException("Unauthorized IP");
		}
        
        if(checker.isLocked(ipAddress))
        	throw new LoginException("Account has been locked out.");
        loginOk=true;
        
		return loginOk;
	}

	@Override
	public boolean commit() throws LoginException {	
		String[] roles = rolesStr.split(";,");
		
		Set<Principal> principalset = subject.getPrincipals();            
        if(usefirstPass){
    		log.fine("Fetching already existing roles group...");
            for(Iterator<?> i=principalset.iterator();i.hasNext();){
                Object obj = i.next();
                if(obj instanceof DirectoryRoles){
                    rolesgrp = (DirectoryRoles) obj;
                    log.fine("Found existing roles group: "+rolesgrp.getName());
                    break;
                }
            }
    	}
    	if(rolesgrp==null){
    		rolesgrp = new DirectoryRoles(rolesgrpname);
        	principalset.add(rolesgrp);
    	}
    	for(String role:roles)
    		rolesgrp.addMember(new OtherPrincipal(role));
    	
    	for(String role:defaultroles)
    		rolesgrp.addMember(new OtherPrincipal(role));
		
		Enumeration<? extends Principal> members = rolesgrp.members();
		while (members.hasMoreElements()) {
			Principal type = members.nextElement();
			principalset.add(type);				
		}
    	
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		if(loginOk){
            @SuppressWarnings("rawtypes")
			Set princ = subject.getPrincipals();
            princ.remove(rolesgrp);
            return true;
        }else
            return false;
	}

	@Override
	public boolean logout() throws LoginException {
        if(loginOk){
            @SuppressWarnings("rawtypes")
			Set princ = subject.getPrincipals();
            princ.remove(rolesgrp);
            return true;
        }else
            return false;
	}

}
