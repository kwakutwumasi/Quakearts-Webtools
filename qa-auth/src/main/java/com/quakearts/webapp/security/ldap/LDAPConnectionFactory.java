package com.quakearts.webapp.security.ldap;

public interface LDAPConnectionFactory<T extends Exception> {
	LDAPConnection<T> createConnection(boolean usessl);
}
