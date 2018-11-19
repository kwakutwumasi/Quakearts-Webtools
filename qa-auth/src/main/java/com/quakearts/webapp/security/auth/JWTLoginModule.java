package com.quakearts.webapp.security.auth;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
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
import com.quakearts.webapp.security.jwt.exception.JWTException;
import com.quakearts.webapp.security.jwt.factory.JWTFactory;
import static com.quakearts.webapp.security.jwt.RegisteredNames.*;
import static com.quakearts.webapp.security.util.UtilityMethods.*;

public class JWTLoginModule implements LoginModule {

	private static final List<String> REGISTEREDNAMESLIST = Arrays.asList(NBF,EXP,IAT,ISS,AUD,SUB);
	private static final Logger log = Logger.getLogger(JWTLoginModule.class.getName());
	private static final String INVALID_PARAMETER = "Invalid parameter: ";
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
	private static final long DEFAULTVALIDITY = 900;

	private boolean loginOk;
	private Map<String, ?> sharedState;
	private Subject subject;
	private CallbackHandler callbackHandler;
	private AuthenticationMode authenticationMode;
	private enum AuthenticationMode {
		VERIFY, GENERATE
	}

	private Map<String, ?> options;
	private String algorithm = "HS256";
	private String rolesgrpname = "Roles";
	private String additionalClaims;
	private String issuer = JWTLoginModule.class.getName();
	private String audience = JWTLoginModule.class.getName();
	private long validity = DEFAULTVALIDITY;
	private long activateAfter = 0;
	private int gracePeriod = 10;

	private JWTVerifier verifier;

	private String username;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, 
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
		this.sharedState = sharedState;
		this.options = options;

		setAlgorithm(options);
		configureAudienceAndIssuer(options);
		setCommonOptions(options);	
		loadValidityOptions(options);
		
		loginOk = false;
		authenticationMode = null;
	}

	private void setAlgorithm(Map<String, ?> options) {
		if (options.containsKey(ALGORITHMPARAMETER))
			algorithm = options.get(ALGORITHMPARAMETER).toString();
	}

	private void configureAudienceAndIssuer(Map<String, ?> options) {
		if (options.containsKey(AUDIENCEPARAMETER))
			audience = options.get(AUDIENCEPARAMETER).toString();

		if (options.containsKey(ISSUERPARAMETER))
			issuer = options.get(ISSUERPARAMETER).toString();
	}

	private void setCommonOptions(Map<String, ?> options) {
		if (options.containsKey(ROLESGROUPNAMEPARAMETER))
			rolesgrpname = options.get(ROLESGROUPNAMEPARAMETER).toString();

		if(options.containsKey(ADDITIONALCLAIMSPARAMETER)){
			additionalClaims = options.get(ADDITIONALCLAIMSPARAMETER).toString();
		}
	}

	private void loadValidityOptions(Map<String, ?> options) {
		try {
			if(options.containsKey(GRACEPERIODPARAMETER))
				gracePeriod = Integer.parseInt(options.get(GRACEPERIODPARAMETER).toString());
		} catch (Exception e) {
			log.severe(INVALID_PARAMETER + GRACEPERIODPARAMETER + "; " + e.getMessage());
		}
		
		try {
			if (options.containsKey(VALIDITYPARAMETER)){
				validity = Long.parseLong(options.get(VALIDITYPARAMETER).toString()) * 1000;
			}
		} catch (Exception e) {
			log.severe(INVALID_PARAMETER + VALIDITYPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(VALIDITY_PERIODPARAMETER)) {
				validity = parseDuration(options.get(VALIDITY_PERIODPARAMETER).toString());
			}
		} catch (Exception e) {
			log.severe(INVALID_PARAMETER + VALIDITY_PERIODPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(ACTIVATEAFTERPARAMETER)) {
				activateAfter = Long.parseLong(options.get(ACTIVATEAFTERPARAMETER).toString());
			}
		} catch (Exception e) {
			log.severe(INVALID_PARAMETER + ACTIVATEAFTERPARAMETER + "; " + e.getMessage());
		}

		try {
			if (options.containsKey(ACTIVATEAFTERPERIODPARAMETER)) {
				activateAfter = parseDuration(options.get(ACTIVATEAFTERPERIODPARAMETER).toString());
			}
		} catch (Exception e) {
			log.severe(INVALID_PARAMETER + ACTIVATEAFTERPERIODPARAMETER + "; " + e.getMessage());
		}

	}

	@Override
	public boolean login() throws LoginException {
		if (sharedState != null) {
			loadAuthenticationState();
		}

		if(loginOk) {
			authenticationMode = AuthenticationMode.GENERATE;
		} else {
			authenticationMode = AuthenticationMode.VERIFY;
			try {
				verifyToken();
			} catch (LoginOperationException e) {
				loginOk = false;
				log.log(Level.SEVERE, e.getMessage(), e);
				return loginOk;
			}
			
		}

		return loginOk;
	}

	private void verifyToken() throws LoginException, LoginOperationException {
		byte[] token;
		try {
			token = handleCallbacks();
		} catch (IOException | UnsupportedCallbackException e) {
			throw new LoginOperationException("Unable to process call backs");
		}				
		verify(token);
		JWTClaims claims = validateToken();
		performCompletionActions(claims);
	}

	private void loadAuthenticationState() {
		Object loginPrincipal = sharedState.get("javax.security.auth.login.name");
		username = (loginPrincipal instanceof Principal) ? ((Principal) loginPrincipal)
				.getName() : null;
			
		Object loginOkObject = sharedState.get("com.quakearts.LoginOk");
		if(loginOkObject != null ) 
			loginOk = (Boolean) (loginOkObject);	
	}

	private byte[] handleCallbacks() throws IOException, 
		UnsupportedCallbackException, LoginException {
		TokenCallback headerCallback = new TokenCallback();
		PasswordCallback passwordCallback = new PasswordCallback("Enter your password", false);
		callbackHandler.handle(new Callback[] { headerCallback, passwordCallback });
		if (headerCallback.getTokenData() == null  && passwordCallback.getPassword() == null) {
			throw new LoginException("Credentials are missing");
		}
		return headerCallback.getTokenData() != null ? headerCallback.getTokenData()
				: new String(passwordCallback.getPassword()).getBytes();
	}

	private void verify(byte[] token) throws LoginException {
		try {
			verifier = JWTFactory.getInstance().getVerifier(algorithm, options);
			verifier.verify(token);
		} catch (JWTException e) {
			throw new LoginException(e.getMessage());
		}
	}

	private JWTClaims validateToken() throws LoginException {
		JWTClaims claims = verifier.getClaims();
		
		if(!issuer.equals(claims.getIssuer())){
			throw new LoginException("Issuer is not recognized");
		}
		
		if(!audience.equals(claims.getAudience())){
			throw new LoginException("Audience is not recognized");
		}

		long currentTimeInSeconds = System.currentTimeMillis() / 1000;
		if (claims.getExpiry() > 0 && claims.getExpiry() < currentTimeInSeconds + gracePeriod) {
			throw new LoginException("Token has expired");
		}

		if(claims.getNotBefore() > 0 && claims.getNotBefore() > currentTimeInSeconds) {
			throw new LoginException("Token is not active");
		}
		
		return claims;
	}

	@SuppressWarnings("unchecked")
	private void performCompletionActions(JWTClaims claims) {
		UserPrincipal shareduser = new UserPrincipal(claims.getSubject());
		loginOk = true;
		if(sharedState!=null) {
			Map<String, Object> sharedStateObj = (Map<String, Object>) sharedState;
			sharedStateObj.put("javax.security.auth.login.name", shareduser);
			sharedStateObj.put("com.quakearts.LoginOk", loginOk);
		}
	}

	@Override
	public boolean commit() throws LoginException {
		if (loginOk) {
			Set<Principal> principalset = subject.getPrincipals();

			Group rolesgrp = null;
			rolesgrp = findRolesGroup(principalset, rolesgrp);
			if (rolesgrp == null) {
				rolesgrp = createRolesGroup(principalset);
			}

			if (authenticationMode == AuthenticationMode.GENERATE) {
				List<String[]> foundRoles = loadPrincipalsFromSubject(rolesgrp);
				JWTPrincipal principal = new JWTPrincipal(generateJWTToken(foundRoles));
				rolesgrp.addMember(principal);
				principalset.add(principal);				
			} else {
				loadPrincipalsFromToken(principalset, rolesgrp);
			}
		}
		return loginOk;
	}

	private Group findRolesGroup(Set<Principal> principalset, Group rolesgrp) {
		for (Iterator<?> i = principalset.iterator(); i.hasNext();) {
			Object obj = i.next();
			if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
				rolesgrp = (Group) obj;
				break;
			}
		}
		return rolesgrp;
	}

	private Group createRolesGroup(Set<Principal> principalset) {
		Group rolesgrp;
		rolesgrp = new DirectoryRoles(rolesgrpname);
		principalset.add(rolesgrp);
		return rolesgrp;
	}

	private List<String[]> loadPrincipalsFromSubject(Group rolesgrp) {
		List<String[]> foundRoles = new ArrayList<>();
		Principal principal;
		Enumeration<? extends Principal> roles = rolesgrp.members();
		while(roles.hasMoreElements()) {
			principal = roles.nextElement();
			
			if(principal instanceof OtherPrincipal) {
				OtherPrincipal otherPrincipal = (OtherPrincipal) principal;
				foundRoles.add(new String[] {otherPrincipal.getAttribute(),otherPrincipal.getName()});
			} else if(principal instanceof NamePrincipal) {
				NamePrincipal namePrincipal = (NamePrincipal) principal;
				foundRoles.add(new String[] {namePrincipal.getAttribute(),namePrincipal.getName()});		
			} else if(principal instanceof EmailPrincipal) {
				EmailPrincipal emailPrincipal = (EmailPrincipal) principal;
				foundRoles.add(new String[] {emailPrincipal.getAttribute(),emailPrincipal.getName()});
			} else if(principal instanceof UserPrincipal) {
				UserPrincipal userPrincipal = (UserPrincipal) principal;
				foundRoles.add(new String[] {userPrincipal.getAttribute(),userPrincipal.getName()});
			} else {
				foundRoles.add(new String[] {principal.getName(), principal.getName()});							
			}
		}
		return foundRoles;
	}

	private void loadPrincipalsFromToken(Set<Principal> principalset, Group rolesgrp) {
		JWTClaims claims = verifier.getClaims();
		for(JWTClaims.Claim claim:claims){
			if(!REGISTEREDNAMESLIST.contains(claim.getName().trim())){
				OtherPrincipal principal = new OtherPrincipal(claim.getValue(), claim.getName());
				rolesgrp.addMember(principal);
				principalset.add(principal);
			}
			
			if(claim.getName().equals(SUB)){
				UserPrincipal principal = new UserPrincipal(claim.getValue());
				rolesgrp.addMember(principal);
				principalset.add(principal);
			}
		}
	}

	public String generateJWTToken(List<String[]> foundRoles) throws LoginException{
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();		
		JWTClaims claims = JWTFactory.getInstance().createEmptyClaims();
		setValidationAttributes(claims);		
		populateClaims(claims, foundRoles);		
		return signToken(claims, header);
	}

	private void setValidationAttributes(JWTClaims claims) {
		claims.setAudience(audience);
		claims.setIssuer(issuer);
		claims.setExpiry((claims.getIssuedAt())+validity);
		
		if(activateAfter>0){
			claims.setNotBefore(claims.getIssuedAt()+activateAfter);
		}
	}

	private void populateClaims(JWTClaims claims, List<String[]> foundRoles) {
		if(username!=null)
			claims.setSubject(username);
		
		addAdditionalClaims(claims);	
		addFoundRoles(claims, foundRoles);
	}

	private void addAdditionalClaims(JWTClaims claims) {
		if(additionalClaims != null && !additionalClaims.trim().isEmpty()){
			String[] additionalClaimsParts = additionalClaims.split(";");
			for(String additionalClaimsPart:additionalClaimsParts){
				addAdditionalClaim(claims, additionalClaimsPart);
			}
		}
	}

	private void addAdditionalClaim(JWTClaims claims, String additionalClaimsPart) {
		String[] claimParts = additionalClaimsPart.split(":",2);
		if(claimParts.length == 2
				&& !claimParts[0].trim().isEmpty()
				&& !claimParts[1].trim().isEmpty()) {
			claims.addPrivateClaim(claimParts[0].toLowerCase(), claimParts[1]);
		}
	}

	private void addFoundRoles(JWTClaims claims, List<String[]> foundRoles) {
		for(String[] role:foundRoles) {
			claims.addPrivateClaim(role[0].toLowerCase(), role[1]);		
		}
	}
	
	private String signToken(JWTClaims claims, JWTHeader header) throws LoginException {
		JWTSigner signer;
		try {
			signer = JWTFactory.getInstance().getSigner(algorithm, options);
			return signer.sign(header, claims);
		} catch (JWTException e) {
			throw new LoginException(e.getMessage());
		}
	}
	
	@Override
	public boolean abort() throws LoginException {
		loginOk = false;
		sharedState = null;
		subject = null;
		callbackHandler = null;
		authenticationMode = null;
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return abort();
	}

}