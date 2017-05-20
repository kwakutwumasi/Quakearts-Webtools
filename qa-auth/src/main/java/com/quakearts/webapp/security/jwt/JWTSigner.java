package com.quakearts.webapp.security.jwt;

import javax.security.auth.login.LoginException;

import com.quakearts.webapp.security.jwt.internal.json.JsonObject;

public interface JWTSigner {
	String sign(JsonObject header, JsonObject claims) throws LoginException;
}
