package com.quakearts.workflowapp.jbpm.util.process;

import java.io.Serializable;

public class ProcessFile implements Serializable {
    private static final long serialVersionUID = 1946413334334046539L;
    private String filename = null;
    private String ticket = null;
    private String description = null;

    public ProcessFile() {
    }

    public ProcessFile(String filename, String ticket, String description) {
        this.filename = filename;
        this.ticket = ticket;
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return filename;
    }
}
