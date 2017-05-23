package com.quakearts.webapp.security.jwt.factory;

import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.login.LoginException;

import com.quakearts.webapp.security.jwt.JWTBase;
import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.JWTSigner;
import com.quakearts.webapp.security.jwt.JWTVerifier;
import com.quakearts.webapp.security.jwt.impl.ESSigner;
import com.quakearts.webapp.security.jwt.impl.HSSigner;
import com.quakearts.webapp.security.jwt.impl.JWTClaimsImpl;
import com.quakearts.webapp.security.jwt.impl.JWTHeaderImpl;
import com.quakearts.webapp.security.jwt.impl.RSSigner;

public class JWTFactory {
	private JWTFactory() {
	}
	
	private static JWTFactory instance = new JWTFactory();
	
	public static JWTFactory getInstance() {
		return instance;
	}
	
	public JWTSigner getSigner(String algorithm, Map<String, ?> options) throws LoginException{
		Object signerObject = createFromParameters(algorithm, options);
		if(signerObject instanceof JWTSigner)
			return (JWTSigner) signerObject;
		else
			throw new LoginException("Unable to obtain JWTSigner. Factory did not produce a valid "+JWTSigner.class.getName());
	}
	
	public JWTVerifier getVerifier(String algorithm, Map<String, ?> options) throws LoginException{
		Object verifierObject = createFromParameters(algorithm, options);
		if(verifierObject instanceof JWTVerifier)
			return (JWTVerifier) verifierObject;
		else
			throw new LoginException("Unable to obtain JWTSigner. Factory did not produce a valid "+JWTSigner.class.getName());
	}
	
	private Object createFromParameters(String algorithm, Map<String, ?> options) throws LoginException {
		if(algorithm == null || algorithm.length()<2)
			throw new LoginException("Invalid algorithm: "+algorithm);
		
		if(options == null)
			throw new LoginException("Invalid algorithm: "+algorithm);			
		
		JWTBase signer = null;
		
		String prefix = algorithm.substring(0,2);
		switch (prefix) {
		case "HS":
			signer = new HSSigner();
			break;
		case "RS":
			signer = new RSSigner();
			break;
		case "ES":
			signer = new ESSigner();
			break;
		default:
			throw new LoginException("Invalid algorithm: "+algorithm);
		}
		
		try {
			signer.setAlgorithim(algorithm);
			for(Entry<String, ?> entry:options.entrySet()){
				if(entry.getValue()!=null)
					signer.setParameter(entry.getKey(), entry.getValue());
			}
			
			return signer;			
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}
	}

	public JWTHeader createJWTHeaderFromBytes(byte[] headerBytes){
		JWTHeader header = new JWTHeaderImpl();
		header.unCompact(new String(headerBytes));
		return header;
	}
	
	public JWTClaims createJWTClaimsFromBytes(byte[] claimsBytes){
		JWTClaims claims = new JWTClaimsImpl();
		claims.unCompact(new String(claimsBytes));
		
		return claims;
	}

	public JWTClaims createEmptyClaims() {
		return new JWTClaimsImpl();
	}
	
	public JWTHeader createEmptyClaimsHeader(){
		return new JWTHeaderImpl();
	}
}
