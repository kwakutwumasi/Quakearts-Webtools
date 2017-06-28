package com.quakearts.webapp.security.jwt;

import com.quakearts.webapp.security.jwt.exception.JWTException;

public interface JWTSigner extends JWTBase {
	String sign(JWTHeader header, JWTClaims claims) throws JWTException;
}
