package com.quakearts.webapp.security.rest;

public interface SecurityContextStorageService {
	void storeContext(SecurityContext context);
	SecurityContext retrieveContext();
	void removeContext();
}
