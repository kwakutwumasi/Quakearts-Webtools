package com.quakearts.webapp.security.auth;

import java.security.Principal;

public abstract class DirectoryPrincipal implements Principal{
    String value;
    public DirectoryPrincipal(String value) {
        this.value = value==null?new String():value;
    }

    public String getName() {
        return value;
    }
    
    public boolean equals(Object o){
        if(o == null)
            return false;
        
        if(this == o){
            return true;
        }
        if(o instanceof Principal){
            return ((Principal) o).getName().equals(value) && o.getClass().equals(this.getClass());
        }
        else
            return false;
    }
    
    public abstract String getAttribute();
}
