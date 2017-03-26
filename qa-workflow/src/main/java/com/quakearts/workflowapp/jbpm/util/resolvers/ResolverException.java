package com.quakearts.workflowapp.jbpm.util.resolvers;

public class ResolverException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3423586982010313842L;
	ResolverException(String message) {
        super(message);
    }
    ResolverException(String message,Throwable cause) {
        super(message,cause);
    }
    ResolverException(Throwable cause) {
        super(cause);
    }
}
