package com.quakearts.webapp.security.auth;

public class DirectoryLoginException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8025568717701561834L;

	public DirectoryLoginException() {
        super();
    }
    
    public DirectoryLoginException(String ex) {
        super(ex);
    }
    
    public DirectoryLoginException(String ex, Throwable thr) {
        super(ex, thr);
    }
}
