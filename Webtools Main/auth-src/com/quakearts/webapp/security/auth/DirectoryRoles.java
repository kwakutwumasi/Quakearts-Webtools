package com.quakearts.webapp.security.auth;

import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class DirectoryRoles implements Group{
    private ArrayList<Principal> userroles = new ArrayList<Principal>();
    private String name;
    public DirectoryRoles(String groupname) {
        name = groupname ==null? "Roles" :groupname;
    }

    @Override
    public boolean addMember(Principal roles) {
    	if(!userroles.contains(roles))
    		return userroles.add(roles);
    	else
    		return false;
    }

    @Override
    public boolean removeMember(Principal roles) {
        return userroles.remove(roles);
    }

    @Override
    public boolean isMember(Principal member) {
        return userroles.contains(member);
    }

    @Override
    public Enumeration<? extends Principal> members() {
        return Collections.enumeration(userroles);
    }

    @Override
   	public String getName() {
        return name;
    }

	@Override
	public int hashCode() {
		int code=0;
		
		for(Principal principal:userroles)
			if(Integer.MAX_VALUE - code > principal.hashCode())
				code+=principal.hashCode();
		
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(this == obj)
			return true;
				
		if(!(obj instanceof DirectoryRoles))
			return false;
				
		if(this.userroles.size() != ((DirectoryRoles)obj).userroles.size())
			return false;
		
		boolean match = true;
		for(Principal principal:userroles)
			match = match && ((DirectoryRoles)obj).userroles.contains(principal);
		
		return match;
	}
    
}
