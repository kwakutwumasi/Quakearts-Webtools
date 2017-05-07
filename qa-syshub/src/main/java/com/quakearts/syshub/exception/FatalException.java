package com.quakearts.syshub.exception;

public class FatalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public FatalException(Exception e) {
        super(e);
    }
    public FatalException(String msg) {
        super(msg);
    }
    public FatalException(String msg, Exception e) {
        super(msg,e);
    }

}
