package com.quakearts.webapp.security.auth;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.acl.Group;
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
import org.apache.log4j.Logger;

import com.quakearts.webapp.security.auth.util.AttemptChecker;

public class IpAddressFilterLoginModule implements LoginModule {
	public static final String DEFAULT_AUTH_GRP = "com.quakearts.webapp.security.auth";
    private Group rolesgrp;
    private Subject subject;
    private CallbackHandler callbackHandler;
    @SuppressWarnings("rawtypes")
	private Map sharedState;
    private boolean loginOk=false, use_first_pass;
	private boolean allowEmptyPassword;
    private AttemptChecker checker;
    private static final Logger log = Logger.getLogger(IpAddressFilterLoginModule.class);
    private String[] defaultroles;
    private String rolesStr,propertiesFile,ipAddress,rolesgrpname;
    private Properties properties = new Properties();
    		
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
        log.trace("Initializing....");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;

        propertiesFile  = (String) options.get("properties.file");
        allowEmptyPassword = Boolean.parseBoolean((String) options.get("allow.anonymous"));
        String defaultroles_str = (String) options.get("defaultroles");
        rolesgrpname = (String) options.get("rolename");

        String maxAttempts_str = (String) options.get("max_try_attempts");
        String lockoutTime_str = (String) options.get("lockout_time");
        
        if(maxAttempts_str == null && lockoutTime_str == null){
        	checker = AttemptChecker.getChecker(DEFAULT_AUTH_GRP);
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
        	AttemptChecker.createChecker(DEFAULT_AUTH_GRP, maxAttempts, lockoutTime);
        	checker = AttemptChecker.getChecker(DEFAULT_AUTH_GRP);
        }

        if(defaultroles_str != null){
        	defaultroles = defaultroles_str.split(";");
        	for(int i=0;i<defaultroles.length;i++)
        		defaultroles[i] = defaultroles[i].trim();
        }

        use_first_pass = Boolean.parseBoolean((String)options.get("use_first_pass"));
        
        log.trace("Initialization complete.\n"+
                "\t\tIpAddressFilterLoginModule options:\n"+
                "\t\tallowEmptyPassword: "+allowEmptyPassword+"\n"+
                "\t\tdefaultroles_str: "+defaultroles_str+"\n"+
                "\t\tmaxAttempts_str: "+maxAttempts_str+"\n"+
                "\t\tlockoutTime_str: "+lockoutTime_str+"\n"+
                "\t\tuse_first_pass: "+use_first_pass+"\n"+
                "\t\trolesgrpname: "+rolesgrpname);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[2];
        String username=null;
        byte[] password = null;

        if(use_first_pass){
            if(sharedState != null){
                log.trace("Using first pass....");
                Object loginDN_val = sharedState.get("javax.security.auth.login.name");
                Object password_val = sharedState.get("javax.security.auth.login.password");
                username = (loginDN_val!=null && loginDN_val instanceof Principal)?((Principal) loginDN_val).getName():null;
                password = (password_val!=null && password_val instanceof char[])?new String((char[]) password_val).getBytes():null;
            }
        }
        
        if(!use_first_pass || username==null || password==null){
            NameCallback name = new NameCallback("Enter your username.");
            PasswordCallback pass = new PasswordCallback("Enter your password:",false);           
            callbacks[0] = name;
            callbacks[1] = pass;

            try {
                log.trace("Handling callback....");
                callbackHandler.handle(callbacks);
            } catch (UnsupportedCallbackException e) {
                throw new LoginException("Callback is not supported");
            } catch (IOException e) {
                throw new LoginException("IOException during call back");
            }
            
            username = name.getName()==null? name.getDefaultName():name.getName();
            password = (new String(pass.getPassword())).getBytes();
            
            if (sharedState != null){
                log.trace("Storing state....");
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
			log.trace("Attempted access from "+ipAddress);
		} catch (Exception e) {
			throw new LoginException("Exception " + e.getClass().getName() + ". Message is "
					+ e.getMessage());
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
        if(use_first_pass){
    		log.trace("Fetching already existing roles group...");
            for(Iterator<?> i=principalset.iterator();i.hasNext();){
                Object obj = i.next();
                if(obj instanceof DirectoryRoles){
                    rolesgrp = (DirectoryRoles) obj;
                    log.trace("Found existing roles group: "+rolesgrp.getName());
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
