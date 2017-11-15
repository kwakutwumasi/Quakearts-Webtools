package com.quakearts.appbase.test.helpers;

import java.security.Principal;

public class TestUserPrincipal implements Principal {

	@Override
	public String getName() {
		return "TestUser";
	}

}
