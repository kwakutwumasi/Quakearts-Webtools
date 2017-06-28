package com.quakearts.webapp.security.jwt;

import com.quakearts.webapp.security.jwt.exception.JWTException;

public interface JWTVerifier extends JWTBase {
	void verify(byte[] token) throws JWTException;
	void verify(String token) throws JWTException;
	JWTHeader getHeader();
	JWTClaims getClaims();
}
