package com.quakearts.webapp.security.jwt;

public interface JWTBase {
	JWTBase setAlgorithim(String algorithm);
	JWTBase setParameter(String name, Object parameter);
}
