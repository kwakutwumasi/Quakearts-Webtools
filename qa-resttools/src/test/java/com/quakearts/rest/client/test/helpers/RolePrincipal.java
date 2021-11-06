package com.quakearts.rest.client.test.helpers;

import java.security.Principal;

public class RolePrincipal implements Principal {

	@Override
	public String getName() {
		return "Authenticated";
	}

}
