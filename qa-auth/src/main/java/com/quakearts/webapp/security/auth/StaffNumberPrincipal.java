package com.quakearts.webapp.security.auth;

public class StaffNumberPrincipal extends DirectoryPrincipal {
    public StaffNumberPrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "employeenumber";
    }
}
