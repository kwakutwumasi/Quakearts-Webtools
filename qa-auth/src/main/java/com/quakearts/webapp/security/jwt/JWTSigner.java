package com.quakearts.webapp.security.jwt;

import javax.security.auth.login.LoginException;

public interface JWTSigner extends JWTBase {
	String sign(JWTHeader header, JWTClaims claims) throws LoginException;
}
