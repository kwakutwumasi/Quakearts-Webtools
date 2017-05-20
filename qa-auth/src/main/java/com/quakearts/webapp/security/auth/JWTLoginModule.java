package com.quakearts.webapp.security.auth;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.security.auth.callback.TokenCallback;

public class JWTLoginModule implements LoginModule {

	private boolean loginOk;
	private Map<String, ?> sharedState;
	private Subject subject;
	private CallbackHandler callbackHandler;
	private AuthenticationMode authenticationMode;
	private String algorithm;
	private String rolesgrpname;
    private static final Logger log = Logger.getLogger(JWTLoginModule.class.getName());
	private String issuer = "QuakeArts.JWTLoginModule."+JWTLoginModule.class;
	
	/*
 +--------------+-------------------------------+--------------------+
   | "alg" Param  | Digital Signature or MAC      | Implementation     |
   | Value        | Algorithm                     | Requirements       |
   +--------------+-------------------------------+--------------------+
   | HS256        | HMAC using SHA-256            | Required           |
   | HS384        | HMAC using SHA-384            | Optional           |
   | HS512        | HMAC using SHA-512            | Optional           |
   | RS256        | RSASSA-PKCS1-v1_5 using       | Recommended        |
   |              | SHA-256                       |                    |
   | RS384        | RSASSA-PKCS1-v1_5 using       | Optional           |
   |              | SHA-384                       |                    |
   | RS512        | RSASSA-PKCS1-v1_5 using       | Optional           |
   |              | SHA-512                       |                    |
   | ES256        | ECDSA using P-256 and SHA-256 | Recommended+       |
   | ES384        | ECDSA using P-384 and SHA-384 | Optional           |
   | ES512        | ECDSA using P-521 and SHA-512 | Optional           |
   | PS256        | RSASSA-PSS using SHA-256 and  | Optional           |
   |              | MGF1 with SHA-256             |                    |
   | PS384        | RSASSA-PSS using SHA-384 and  | Optional           |
   |              | MGF1 with SHA-384             |                    |
   | PS512        | RSASSA-PSS using SHA-512 and  | Optional           |
   |              | MGF1 with SHA-512             |                    |
   | none         | No digital signature or MAC   | Optional           |
   |              | performed                     |                    |
   +--------------+-------------------------------+--------------------+	 
	 */
	
	private enum AuthenticationMode {
		VERIFY,
		GENERATE
	}
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		//TODO Options
		
		if(options.containsKey("rolesgroupname"))
			rolesgrpname = options.get("rolesgroupname").toString();
		else
			rolesgrpname = "Roles";
		
		if(options.containsKey("issuer")){
			issuer = options.get("issuer").toString();
		}
		
		loginOk = false;
	}

	@Override
	public boolean login() throws LoginException {
        if(sharedState != null){
            log.fine("Using first pass....");
            Object loginOkObject = sharedState.get("com.quakearts.LoginOk");
            loginOk = (loginOkObject==null?false:(Boolean)(loginOkObject));
            authenticationMode = AuthenticationMode.GENERATE;
        }
        try {
        	TokenCallback callback = new TokenCallback();
			callbackHandler.handle(new Callback[]{callback});
			
			if(callback.getTokenData() != null){
				
				loginOk = true;
	            authenticationMode = AuthenticationMode.VERIFY;
			}
		} catch (IOException | UnsupportedCallbackException e) {
			return loginOk;
		}
				
		return loginOk;
	}

	@Override
	public boolean commit() throws LoginException {
		if(loginOk){
            Set<Principal> principalset = subject.getPrincipals();
            
            Group rolesgrp = null;
            log.fine("Fetching already existing roles group...");
			for (Iterator<?> i = principalset.iterator(); i.hasNext();) {
				Object obj = i.next();
				if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
					rolesgrp = (Group) obj;
                    log.fine("Found existing roles group: "+rolesgrp.getName());
					break;
				}
			}				

            if(rolesgrp == null){
                log.fine("Creating roles group...");
                rolesgrp = new DirectoryRoles(rolesgrpname);
                principalset.add(rolesgrp);
            }

			if(authenticationMode == AuthenticationMode.GENERATE){
//				JWTPrincipal jwtPrincipal;
//				jwtPrincipal = new JWTPrincipal();
//				principalset.add(jwtPrincipal);
			} else if(authenticationMode == AuthenticationMode.VERIFY){
				
			}
		}
		return loginOk;
	}

	@Override
	public boolean abort() throws LoginException {
		return loginOk;
	}

	@Override
	public boolean logout() throws LoginException {
		return loginOk;
	}

}
