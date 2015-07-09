package com.quakearts.webapp.security.auth;

import java.security.Principal;

public class OtherPrincipal implements Principal{
    private String name;
    private String attribute = "other";
    
    public OtherPrincipal(String name) {
        this.name = name;
    }

    public OtherPrincipal(String name,String attribute) {
        this.name = name;
        this.attribute=attribute;
    }

    public String getAttribute() {
        return attribute;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
