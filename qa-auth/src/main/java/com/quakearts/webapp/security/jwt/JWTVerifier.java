package com.quakearts.webapp.security.jwt;

import javax.security.auth.login.LoginException;

public interface JWTVerifier extends JWTBase {
	void verify(byte[] token) throws LoginException;
}
