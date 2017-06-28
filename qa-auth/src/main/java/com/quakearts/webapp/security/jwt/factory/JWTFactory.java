package com.quakearts.webapp.security.jwt.factory;

import java.util.Map;
import java.util.Map.Entry;
import com.quakearts.webapp.security.jwt.JWTBase;
import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.JWTSigner;
import com.quakearts.webapp.security.jwt.JWTVerifier;
import com.quakearts.webapp.security.jwt.exception.JWTException;
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
	
	public JWTSigner getSigner(String algorithm, Map<String, ?> options) throws JWTException{
		JWTBase signerObject = createFromParameters(algorithm, options);
		if(signerObject instanceof JWTSigner)
			return (JWTSigner) signerObject;
		else
			throw new JWTException("Unable to obtain JWTSigner. Factory did not produce a valid "+JWTSigner.class.getName());
	}
	
	public JWTVerifier getVerifier(String algorithm, Map<String, ?> options) throws JWTException{
		Object verifierObject = createFromParameters(algorithm, options);
		if(verifierObject instanceof JWTVerifier)
			return (JWTVerifier) verifierObject;
		else
			throw new JWTException("Unable to obtain JWTSigner. Factory did not produce a valid "+JWTSigner.class.getName());
	}
	
	private JWTBase createFromParameters(String algorithm, Map<String, ?> options) throws JWTException {
		if(algorithm == null || algorithm.length()<2)
			throw new JWTException("Invalid algorithm: "+algorithm);
		
		if(options == null)
			throw new JWTException("Invalid algorithm: "+algorithm);			
		
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
			throw new JWTException("Invalid algorithm: "+algorithm);
		}
		
		try {
			signer.setAlgorithim(algorithm);
			for(Entry<String, ?> entry:options.entrySet()){
				if(entry.getValue()!=null)
					signer.setParameter(entry.getKey(), entry.getValue());
			}
			
			return signer;			
		} catch (Exception e) {
			throw new JWTException(e.getMessage());
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

	public JWTClaims createJWTClaimsFromMap(Map<String, String> map) throws JWTException{
		JWTClaims claims = new JWTClaimsImpl();
		
		for(Entry<String, String> entry:map.entrySet()){
			if(entry.getKey().equals(JWTClaims.SUB))
				claims.setSubject(entry.getValue());
			else if(entry.getKey().equals(JWTClaims.AUD))
				claims.setAudience(entry.getValue());
			else if(entry.getKey().equals(JWTClaims.EXP))
				try {
					claims.setExpiry(Long.parseLong(entry.getValue()));
				} catch (NumberFormatException e) {
					throw new JWTException("Claim '"+JWTClaims.EXP+"' must be a valid integer");
				}
			else if(entry.getKey().equals(JWTClaims.IAT))
				try {
					claims.setIssuedAt(Long.parseLong(entry.getValue()));
				} catch (NumberFormatException e) {
					throw new JWTException("Claim '"+JWTClaims.IAT+"' must be a valid integer");
				}
			else if(entry.getKey().equals(JWTClaims.ISS))
				claims.setIssuer(entry.getValue());
			else if(entry.getKey().equals(JWTClaims.NBF))
				try {
					claims.setNotBefore(Long.parseLong(entry.getValue()));
				} catch (NumberFormatException e) {
					throw new JWTException("Claim '"+JWTClaims.NBF+"' must be a valid integer");
				}
		}
		
		return claims;
	}
	
	public JWTClaims createEmptyClaims() {
		return new JWTClaimsImpl();
	}
	
	public JWTHeader createEmptyClaimsHeader(){
		return new JWTHeaderImpl();
	}
}
