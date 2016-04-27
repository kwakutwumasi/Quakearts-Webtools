package com.quakearts.webapp.security.auth;

public class NamePrincipal extends DirectoryPrincipal{
    public NamePrincipal(String value) {
        super(value);
    }

    public String getAttribute() {
        return "name";
    }
}
