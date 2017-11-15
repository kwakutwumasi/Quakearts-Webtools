package com.quakearts.appbase.test.helpers;

import java.security.Principal;

public class TestPrincipal implements Principal {

	@Override
	public String getName() {
		return "Test";
	}

}
