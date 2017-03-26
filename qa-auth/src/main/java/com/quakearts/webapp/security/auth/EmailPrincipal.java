package com.quakearts.webapp.security.auth;

public class EmailPrincipal extends DirectoryPrincipal {
    public EmailPrincipal(String username){
        super(username);
    }

    public String getAttribute() {
        return "mail";
    }
}
