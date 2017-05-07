package com.quakearts.syshub.exception;

public class ConfigurationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7293767477390702563L;
	public ConfigurationException(Exception e){
        super(e);
    }
    public ConfigurationException(String msg, Exception e){
        super(msg, e);
    }
    public ConfigurationException(String msg){
        super(msg);
    }
}
