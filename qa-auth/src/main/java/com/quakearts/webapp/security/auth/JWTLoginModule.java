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

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import com.quakearts.webapp.security.auth.callback.TokenCallback;

public class JWTLoginModule implements LoginModule {

	private boolean loginOk;
	private Map<String, ?> sharedState;
	private Subject subject;
	private CallbackHandler callbackHandler;
	private AuthenticationMode authenticationMode;
	//private String algorithm;
	private String rolesgrpname;
    private static final Logger log = Logger.getLogger(JWTLoginModule.class.getName());
	private static final RsaJsonWebKey KEY = generateKey();
	private String issuer = "QuakeArts.JWTLoginModule."+JWTLoginModule.class;
	private String username;
	
	private static RsaJsonWebKey generateKey() {
		try {
			RsaJsonWebKey key = RsaJwkGenerator.generateJwk(2048);
			key.setKeyId(UUID.randomUUID().toString());
			return key;
		} catch (JoseException e) {
			throw new RuntimeException(e);
		}
	}
	
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
            Object loginDN_val = sharedState.get("javax.security.auth.login.name");
//            Object changePasswordObject = sharedState.get("com.quakearts.ChangePassword");
            username = (loginDN_val!=null && loginDN_val instanceof Principal)?((Principal) loginDN_val).getName():null;
            Object loginOkObject = sharedState.get("com.quakearts.LoginOk");
            loginOk = (loginOkObject==null?false:(Boolean)(loginOkObject));
            authenticationMode = AuthenticationMode.GENERATE;
        }
        try {
        	TokenCallback callback = new TokenCallback();
			callbackHandler.handle(new Callback[]{callback});
			
			if(callback.getTokenData() != null){
				String token = new String(callback.getTokenData());
				
				JwtConsumer jwtConsumer = new JwtConsumerBuilder()
			            .setRequireExpirationTime() // the JWT must have an expiration time
			            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
			            .setRequireSubject() // the JWT must have a subject claim
			            .setExpectedIssuer(issuer) // whom the JWT needs to have been issued by
			            .setVerificationKey(KEY.getKey()) // verify the signature with the public key
			            .setJwsAlgorithmConstraints( // only allow the expected signature algorithm(s) in the given context
			                    new AlgorithmConstraints(ConstraintType.WHITELIST, // which is only RS256 here
			                            AlgorithmIdentifiers.RSA_USING_SHA256))
			            .build(); // create the JwtConsumer instance
				
				try {
					 jwtConsumer.processToClaims(token);
				} catch (Throwable e) {
					return false;
				}
				
				loginOk = true;
	            authenticationMode = AuthenticationMode.GENERATE;
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
				JwtClaims claims = new JwtClaims();
			    claims.setIssuer(issuer);  // who creates the token and signs it
			    claims.setExpirationTimeMinutesInTheFuture(60); // time when the token will expire (10 minutes from now)
			    claims.setGeneratedJwtId(); // a unique identifier for the token
			    claims.setIssuedAtToNow();  // when the token was issued/created (now)
			    claims.setSubject(username); // the subject/principal is whom the token is about
			    List<String> groups = Arrays.asList("Client", "Practitioner");
			    claims.setStringListClaim("groups", groups); // multi-valued claims work too and will end up as a JSON array

			    // A JWT is a JWS and/or a JWE with JSON claims as the payload.
			    // In this example it is a JWS so we create a JsonWebSignature object.
			    JsonWebSignature jws = new JsonWebSignature();

			    // The payload of the JWS is JSON content of the JWT Claims
			    jws.setPayload(claims.toJson());

			    // The JWT is signed using the private key
			    jws.setKey(KEY.getPrivateKey());

			    // Set the Key ID (kid) header because it's just the polite thing to do.
			    // We only have one key in this example but a using a Key ID helps
			    // facilitate a smooth key rollover process
			    jws.setKeyIdHeaderValue(KEY.getKeyId());

			    // Set the signature algorithm on the JWT/JWS that will integrity protect the claims
			    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

			    // Sign the JWS and produce the compact serialization or the complete JWT/JWS
			    // representation, which is a string consisting of three dot ('.') separated
			    // base64url-encoded parts in the form Header.Payload.Signature
			    // If you wanted to encrypt it, you can simply set this jwt as the payload
			    // of a JsonWebEncryption object and set the cty (Content Type) header to "jwt".
				JWTPrincipal jwtPrincipal;
				try {
					jwtPrincipal = new JWTPrincipal(jws.getCompactSerialization());
				} catch (JoseException e) {
					return false;
				}
				principalset.add(jwtPrincipal);
			} else if(authenticationMode == AuthenticationMode.VERIFY){
				principalset.add(new OtherPrincipal("Client"));
				principalset.add(new OtherPrincipal("Practitioner"));
			}
		}
		return loginOk;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
