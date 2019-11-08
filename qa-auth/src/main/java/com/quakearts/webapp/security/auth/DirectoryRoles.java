/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.security.auth;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class DirectoryRoles implements Group{
    private Set<Principal> userroles = new HashSet<>();
    private String name;
    public DirectoryRoles(String groupname) {
        name = groupname ==null? "Roles" :groupname;
    }

    @Override
    public boolean addMember(Principal roles) {
    	return userroles.add(roles);
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
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
		result = prime * result + userroles.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		
		if(this == obj)
			return true;
				
		if(!(obj instanceof DirectoryRoles))
			return false;
				
		DirectoryRoles other = (DirectoryRoles)obj;
		if(this.userroles.size() != other.userroles.size())
			return false;
		
		if(!name.equals(other.name)){
			return false;
		}
		
		boolean match = true;
		for(Principal principal:userroles){
			match = other.userroles.contains(principal) && match;
			if(!match)
				break;
		}
		
		return match;
	}    
}
