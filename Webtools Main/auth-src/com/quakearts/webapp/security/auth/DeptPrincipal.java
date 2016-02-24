package com.quakearts.webapp.security.auth;

public class DeptPrincipal extends DirectoryPrincipal{
    public DeptPrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "department";
    }
}
