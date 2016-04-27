package com.quakearts.webapp.security.auth.jbpm;

import org.jbpm.svc.Service;
import org.jbpm.svc.ServiceFactory;

public class SubjectAuthenticationFactory implements ServiceFactory {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2295338873911760959L;

	public SubjectAuthenticationFactory() {
    }

    public Service openService() {
        return new SubjectAuthenticationService();
    }

    public void close() {
    }
}
