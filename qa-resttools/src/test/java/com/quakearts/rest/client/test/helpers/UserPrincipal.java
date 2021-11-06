package com.quakearts.rest.client.test.helpers;

import java.security.Principal;

public class UserPrincipal implements Principal {

	@Override
	public String getName() {
		return "TestUser";
	}

}
