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
import static com.quakearts.webapp.security.jwt.RegisteredNames.*;

public class JWTFactory {
	private static final String INVALID_ALGORITHM = "Invalid algorithm: ";

	private JWTFactory() {
	}
	
	private static JWTFactory instance = new JWTFactory();
	
	public static JWTFactory getInstance() {
		return instance;
	}
	
	public JWTSigner getSigner(String algorithm, Map<String, ?> options) throws JWTException{
		JWTBase signerObject = createFromParameters(algorithm, options);
		return (JWTSigner) signerObject;
	}
	
	public JWTVerifier getVerifier(String algorithm, Map<String, ?> options) throws JWTException{
		Object verifierObject = createFromParameters(algorithm, options);
		return (JWTVerifier) verifierObject;
	}
	
	private JWTBase createFromParameters(String algorithm, Map<String, ?> options) throws JWTException {
		if(algorithm == null || algorithm.trim().length()<2)
			throw new JWTException(INVALID_ALGORITHM+algorithm);
		
		if(options == null)
			throw new JWTException(INVALID_ALGORITHM+algorithm);			
		
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
			throw new JWTException(INVALID_ALGORITHM+algorithm);
		}
		
		try {
			signer.setAlgorithim(algorithm);
			for(Entry<String, ?> entry:options.entrySet()){
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

	public JWTClaims createJWTClaimsFromMap(Map<String, ?> map) throws JWTException{
		JWTClaims claims = new JWTClaimsImpl();
		
		for(Entry<String, ?> entry:map.entrySet()){
			switch(entry.getKey()) {
			case SUB:
				claims.setSubject(entry.getValue().toString());
				break;
			case AUD:
				claims.setAudience(entry.getValue().toString());
				break;
			case EXP:
				claims.setExpiry((Long)entry.getValue());
			break;
			case IAT:
				claims.setIssuedAt((Long)entry.getValue());
			break;
			case ISS:
				claims.setIssuer(entry.getValue().toString());
			break;
			case NBF:
				claims.setNotBefore((Long)entry.getValue());
			break;
			default:
				claims.addPrivateClaim(entry.getKey(), entry.getValue().toString());
				break;
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
