package com.quakearts.webapp.security.ldap.impl;

import com.novell.ldap.LDAPException;
import com.quakearts.webapp.security.ldap.LDAPConnection;
import com.quakearts.webapp.security.ldap.LDAPConnectionFactory;

public class DefaultLDAPConnectionFactory implements LDAPConnectionFactory<LDAPException> {

	@Override
	public LDAPConnection<LDAPException> createConnection(boolean usessl) {
		return new DefaultLDAPConnection(usessl);
	}

}
