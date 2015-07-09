package com.quakearts.workflowapp.jbpm.audit;

public class AuditReportException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4726905626429184181L;
	public AuditReportException(String message) {
        super(message);
    }
    public AuditReportException(String message, Throwable throwable) {
        super(message,throwable);
    }
    public AuditReportException(Throwable throwable) {
        super(throwable);
    }
    public AuditReportException() {
        super();
    }

}
