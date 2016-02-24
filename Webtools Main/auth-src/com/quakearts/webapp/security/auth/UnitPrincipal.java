package com.quakearts.webapp.security.auth;

public class UnitPrincipal extends DirectoryPrincipal{
    public UnitPrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "businessCategory";
    }
}
