package com.quakearts.webapp.security.auth;

public class GradePrincipal extends DirectoryPrincipal{
    public GradePrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "employeetype";
    }
}
