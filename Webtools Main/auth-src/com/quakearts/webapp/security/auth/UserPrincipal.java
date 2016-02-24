package com.quakearts.webapp.security.auth;

public class UserPrincipal extends DirectoryPrincipal{
    public UserPrincipal(String username) {
        super(username);
    }

    public String getAttribute() {
        return "sAMAccountName";
    }
}
