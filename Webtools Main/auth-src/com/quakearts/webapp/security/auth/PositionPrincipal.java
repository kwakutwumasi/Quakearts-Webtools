package com.quakearts.webapp.security.auth;

public class PositionPrincipal extends DirectoryPrincipal{
    public PositionPrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "title";
    }
}
