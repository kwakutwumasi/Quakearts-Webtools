package com.quakearts.syshub.exception;

@SuppressWarnings("serial")
public class ProcessingException extends Exception{
    public ProcessingException(Exception e) {
        super(e);
    }
    public ProcessingException(String msg) {
        super(msg);
    }
    public ProcessingException(String msg, Exception e) {
        super(msg,e);
    }
}
