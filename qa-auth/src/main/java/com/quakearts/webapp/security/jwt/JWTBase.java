package com.quakearts.webapp.security.jwt;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface JWTBase {
	JWTBase setParameter(String name, Object parameter);
	JWTBase setSecretKey(String secret);
	JWTBase setPublicKey(PublicKey key);
	JWTBase setPrivateKey(PrivateKey key);
}
