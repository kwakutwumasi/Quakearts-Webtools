package com.quakearts.webapp.security.ldap;

import java.util.ArrayList;
import java.util.List;

public class LDAPObject {
	private List<String[]> attributeEntries;
	private String dn;

	public List<String[]> getAttributeEntries() {
		return attributeEntries;
	}

	public void setAttributeEntries(List<String[]> attributeEntries) {
		this.attributeEntries = attributeEntries;
	}
	
	public LDAPObject withAttributeEntriesAs(List<String[]> attributeEntries) {
		setAttributeEntries(attributeEntries);
		return this;
	}
	
	public LDAPObject addAttributeEntry(String attribute, String value) {
		if(attributeEntries == null)
			attributeEntries = new ArrayList<>();
		
		attributeEntries.add(new String[] {attribute, value});
		return this;
	}

	public String getDN() {
		return dn;
	}

	public void setDN(String dn) {
		this.dn = dn;
	}
	
	public LDAPObject withDNAs(String dn) {
		setDN(dn);
		return this;
	}

}
