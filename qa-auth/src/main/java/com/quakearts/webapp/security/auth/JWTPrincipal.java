package com.quakearts.webapp.security.auth;

import java.security.Principal;

public class JWTPrincipal implements Principal{

	private String token;
	
	public JWTPrincipal(String token) {
		this.token = token;
	}

	@Override
	public String getName() {
		return token;
	}

}
