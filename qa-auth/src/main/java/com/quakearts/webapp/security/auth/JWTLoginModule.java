package com.quakearts.webapp.security.auth;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.security.auth.callback.TokenCallback;
import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.JWTSigner;
import com.quakearts.webapp.security.jwt.JWTVerifier;
import com.quakearts.webapp.security.jwt.factory.JWTFactory;

public class JWTLoginModule implements LoginModule {

	public static final String ACTIVATEAFTERPARAMETER = "activate.after";
	public static final String ACTIVATEAFTERPERIODPARAMETER = "activate.after.period";
	public static final String ADDITIONALCLAIMSPARAMETER = "additional.claims";
	public static final String GRACEPERIODPARAMETER = "grace.period";
	public static final String VALIDITY_PERIODPARAMETER = "validity.period";
	public static final String VALIDITYPARAMETER = "validity";
	public static final String ROLESGROUPNAMEPARAMETER = "rolesgroupname";
	public static final String ISSUERPARAMETER = "issuer";
	public static final String AUDIENCEPARAMETER = "audience";
	public static final String ALGORITHMPARAMETER = "algorithm";
	private boolean loginOk;
	private Map<String, Object> sharedState;
	private Subject subject;
	private CallbackHandler callbackHandler;
	private AuthenticationMode authenticationMode;
	private String algorithm;
	private String rolesgrpname = "Roles";
	private String additionalClaims;
	private Map<String, ?> options;
	private static final Logger log = Logger.getLogger(JWTLoginModule.class.getName());
	private String issuer = JWTLoginModule.class.getName();
	private String audience = JWTLoginModule.class.getName();
	public static long DEFAULTVALIDITY = 54000;
	private long activateAfter = 0;
	private int gracePeriod = 10;
	private JWTVerifier verifier;
	private String username;
	
	
	private enum AuthenticationMode {
		VERIFY, GENERATE
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = (Map<String, Object>) sharedState;
		this.options = options;

		if (options.containsKey(ALGORITHMPARAMETER))
			algorithm = options.get(ALGORITHMPARAMETER).toString();
		else
			algorithm = "HS256";

		if (options.containsKey(AUDIENCEPARAMETER))
			audience = options.get(AUDIENCEPARAMETER).toString();

		if (options.containsKey(ISSUERPARAMETER))
			issuer = options.get(ISSUERPARAMETER).toString();

		if (options.containsKey(ROLESGROUPNAMEPARAMETER))
			rolesgrpname = options.get(ROLESGROUPNAMEPARAMETER).toString();

		try {
			if(options.containsKey(GRACEPERIODPARAMETER))
				gracePeriod = Integer.parseInt(options.get(GRACEPERIODPARAMETER).toString());
		} catch (Exception e) {
			log.severe("Invalid parameter: " + GRACEPERIODPARAMETER + "; " + e.getMessage());
		}
		
		if(options.containsKey(ADDITIONALCLAIMSPARAMETER)){
			additionalClaims = options.get(ADDITIONALCLAIMSPARAMETER).toString();
		}
			
		try {
			if (options.containsKey(VALIDITYPARAMETER)){
			DEFAULTVALIDITY = Long.parseLong(options.get(VALIDITYPARAMETER).toString()) * 1000;
			}
		} catch (Exception e) {
			log.severe("Invalid parameter: " + VALIDITYPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(VALIDITY_PERIODPARAMETER)) {
				DEFAULTVALIDITY = parseDuration(options.get(VALIDITY_PERIODPARAMETER).toString(), options);
			}
		} catch (Exception e) {
			log.severe("Invalid parameter: " + VALIDITY_PERIODPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(ACTIVATEAFTERPARAMETER)) {
				activateAfter = Long.parseLong(options.get(ACTIVATEAFTERPARAMETER).toString());
			}
		} catch (Exception e) {
			log.severe("Invalid parameter: " + ACTIVATEAFTERPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(ACTIVATEAFTERPERIODPARAMETER)) {
				activateAfter = parseDuration(options.get(ACTIVATEAFTERPERIODPARAMETER).toString(), options);
			}
		} catch (Exception e) {
			log.severe("Invalid parameter: " + ACTIVATEAFTERPERIODPARAMETER + "; " + e.getMessage());
		}

		loginOk = false;
		authenticationMode = null;
	}

	public static long parseDuration(String parameter, Map<String, ?> options) {
		String durationString = options.get(parameter).toString();
		String[] durationStringParts = durationString.split("[\\s]+", 2);

		if (durationStringParts.length == 2 
				&& durationStringParts[0] != null
				&& !durationStringParts[0].trim().isEmpty()
				&& durationStringParts[1] != null
				&& !durationStringParts[1].trim().isEmpty()) {
			int periodAmount = Integer.parseInt(durationStringParts[0].trim());
			String prefix = durationStringParts[1].trim().substring(0, 1).toUpperCase();
			Duration duration = Duration
					.parse("P" + (prefix.equals("H") || prefix.equals("M") || prefix.equals("S") ? "T" : "")
							+ periodAmount + prefix);

			return duration.getSeconds() * 1000;
		}
		return 0;
	}

	@Override
	public boolean login() throws LoginException {
		if (sharedState != null) {
			log.fine("Using first pass....");
			Object usernameObject = sharedState.get("javax.security.auth.login.name");
			if(usernameObject != null)
				username = usernameObject.toString();
				
			Object loginOkObject = sharedState.get("com.quakearts.LoginOk");
			loginOk = (loginOkObject == null ? false : (Boolean) (loginOkObject));
			if(loginOk)
				authenticationMode = AuthenticationMode.GENERATE;
		}

		if (!loginOk) {
			try {
				TokenCallback headerCallback = new TokenCallback();
				PasswordCallback passwordCallback = new PasswordCallback("Enter your password", false);
				callbackHandler.handle(new Callback[] { headerCallback, passwordCallback });

				if (headerCallback.getTokenData() != null || passwordCallback.getPassword() != null) {
					authenticationMode = AuthenticationMode.VERIFY;

					byte[] token = headerCallback.getTokenData() != null ? headerCallback.getTokenData()
							: new String(passwordCallback.getPassword()).getBytes();

					verifier = JWTFactory.getInstance().getVerifier(algorithm, options);
					verifier.verify(token);

					loginOk = true;
					JWTClaims claims = verifier.getClaims();
					
					if(!issuer.equals(claims.getIssuer())){
						loginOk = false;
						return loginOk;
					}
					
					if(!audience.equals(claims.getIssuer())){
						loginOk = false;
					}

					long currentTimeInSeconds = System.currentTimeMillis() / 1000;
					if (claims.getExpiry() > 0 && claims.getExpiry() < currentTimeInSeconds + gracePeriod) {
						loginOk = false;
						return loginOk;
					}
					
					if (claims.getNotBefore() > 0 && claims.getNotBefore() > currentTimeInSeconds) {
						loginOk = false;
						return loginOk;
					}
					log.fine("Storing state....");
					UserPrincipal shareduser = new UserPrincipal(claims.getSubject());
					sharedState.put("javax.security.auth.login.name",
							shareduser);
					sharedState.put("com.quakearts.LoginOk", loginOk);
				}
			} catch (IOException | UnsupportedCallbackException e) {
				return loginOk;
			}
		}

		return loginOk;
	}

	@Override
	public boolean commit() throws LoginException {
		if (loginOk) {
			Set<Principal> principalset = subject.getPrincipals();

			Group rolesgrp = null;
			log.fine("Fetching already existing roles group...");
			for (Iterator<?> i = principalset.iterator(); i.hasNext();) {
				Object obj = i.next();
				if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
					rolesgrp = (Group) obj;
					log.fine("Found existing roles group: " + rolesgrp.getName());
					break;
				}
			}

			if (rolesgrp == null) {
				log.fine("Creating roles group...");
				rolesgrp = new DirectoryRoles(rolesgrpname);
				principalset.add(rolesgrp);
			}

			if (authenticationMode == AuthenticationMode.GENERATE) {
				JWTClaims claims = JWTFactory.getInstance().createEmptyClaims();
				claims.setAudience(audience);
				claims.setIssuer(issuer);

				if(DEFAULTVALIDITY > 0)
					claims.setExpiry((claims.getIssuedAt())+DEFAULTVALIDITY);
				
				if(activateAfter>0){
					claims.setNotBefore(claims.getIssuedAt()+activateAfter);
				}
				
				if(username!=null)
					claims.setSubject(username);
				
				if(additionalClaims != null 
						&& !additionalClaims.trim().isEmpty()){
					String[] additionalClaimsParts = additionalClaims.split(";");
					for(String additionalClaimsPart:additionalClaimsParts){
						String[] claimParts = additionalClaimsPart.split(":",2);
						if(claimParts.length == 2
								&& claimParts[0] != null
								&& !claimParts[0].trim().isEmpty()
								&& claimParts[1] != null
								&& !claimParts[1].trim().isEmpty()) {
							claims.addPrivateClaim(claimParts[0].toLowerCase(), claimParts[1]);
						}
					}
				}
				
				JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
				
				JWTSigner signer = JWTFactory.getInstance().getSigner(algorithm, options);
				JWTPrincipal principal = new JWTPrincipal(signer.sign(header, claims));
				
				rolesgrp.addMember(principal);
				principalset.add(principal);				
			} else if (authenticationMode == AuthenticationMode.VERIFY) {
				JWTClaims claims = verifier.getClaims();
				for(JWTClaims.Claim claim:claims){
					if(!JWTClaims.REGISTEREDNAMESLIST.contains(claim.getName().trim())){
						OtherPrincipal principal = new OtherPrincipal(claim.getValue(), claim.getName());
						rolesgrp.addMember(principal);
						principalset.add(principal);
					}
					
					if(claim.getName().equals(JWTClaims.SUB)){
						UserPrincipal principal = new UserPrincipal(claim.getValue());
						rolesgrp.addMember(principal);
						principalset.add(principal);
					}
				}
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