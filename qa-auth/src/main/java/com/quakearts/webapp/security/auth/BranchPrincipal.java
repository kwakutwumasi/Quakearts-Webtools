package com.quakearts.webapp.security.auth;

public class BranchPrincipal extends DirectoryPrincipal{
    public BranchPrincipal(String value) {
            super(value);
    }

    public String getAttribute() {
        return "physicalDeliveryOfficeName";
    }
}
