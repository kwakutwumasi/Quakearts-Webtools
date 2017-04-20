package com.quakearts.webapp.security.rest;

import java.security.Principal;

public class RestUserPrincipal implements Principal {

	private String name;
	
	public RestUserPrincipal(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
