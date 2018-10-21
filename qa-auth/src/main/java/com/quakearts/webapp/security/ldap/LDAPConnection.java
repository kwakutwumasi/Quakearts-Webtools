package com.quakearts.webapp.security.ldap;

public interface LDAPConnection<T extends Throwable> {
	void connect(String host, int port) throws T;
	void bind(String dn, byte[] passwd) throws T;
	LDAPObject search(String base, String filter, String[] attrs, boolean typesOnly) throws T;
	boolean compare(String dn, String[] attributeValuePair) throws T;
	void disconnect() throws T;
}
