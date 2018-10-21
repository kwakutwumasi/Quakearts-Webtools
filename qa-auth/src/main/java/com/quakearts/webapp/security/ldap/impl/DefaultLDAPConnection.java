package com.quakearts.webapp.security.ldap.impl;

import java.util.ArrayList;
import java.util.List;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPJSSESecureSocketFactory;
import com.novell.ldap.LDAPSearchResults;
import com.quakearts.webapp.security.ldap.LDAPObject;

public class DefaultLDAPConnection 
	implements com.quakearts.webapp.security.ldap.LDAPConnection<LDAPException> {

	private LDAPConnection conn;
	
	public DefaultLDAPConnection(boolean usessl) {
		conn = usessl? new LDAPConnection(new LDAPJSSESecureSocketFactory())
	        	: new LDAPConnection();
	}

	@Override
	public void connect(String host, int port) throws LDAPException {
		conn.connect(host, port);
	}

	@Override
	public void bind(String dn, byte[] password) throws LDAPException {
		conn.bind(LDAPConnection.LDAP_V3, dn, password);
	}

	@Override
	public LDAPObject search(String base, String filter, String[] attributes, boolean typesOnly)
			throws LDAPException {
		
		LDAPSearchResults result = conn.search(base, LDAPConnection.SCOPE_SUB, filter, attributes, false);
		LDAPEntry userprof = result.next();
		List<String[]> results = new ArrayList<>();
		for(String attribute:attributes) {
			LDAPAttribute ldapAttribute;
			ldapAttribute = userprof.getAttribute(attribute);
			String attributeValue = ldapAttribute.getStringValue();
			results.add(new String[]{attribute, attributeValue==null?"NONE":attributeValue});
		}
		
		return new LDAPObject().withDNAs(userprof.getDN())
				.withAttributeEntriesAs(results);
	}

	@Override
	public boolean compare(String dn, String[] attributeValuePair) throws LDAPException {
		LDAPAttribute attribute = new LDAPAttribute(attributeValuePair[0]);
		attribute.addValue(attributeValuePair[1].getBytes());

		return conn.compare(dn, attribute);
	}

	@Override
	public void disconnect() throws LDAPException {
		conn.disconnect();
	}

}
