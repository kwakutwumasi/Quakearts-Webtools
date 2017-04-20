package com.quakearts.webapp.security.rest;

import java.security.Permission;

public class SecurityContextPermission extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6644924808697969568L;
	private String actions;

	public SecurityContextPermission(String name, String actions) {
		super(name);
		this.actions = actions;
	}

	@Override
	public boolean implies(Permission permission) {
		return permission instanceof SecurityContextPermission 
				&& getActions().equals(permission.getActions())
				&& getName().equals(permission.getName());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		
		if(obj==this)
			return true;
		
		if(!(obj instanceof SecurityContextPermission))
			return false;
		
		SecurityContextPermission comp = (SecurityContextPermission) obj;
		
		return this.actions.equals(comp.actions) && this.getName().equals(comp.getName());
	}

	@Override
	public int hashCode() {
		return actions.hashCode()*5+getName().hashCode()*7;
	}

	@Override
	public String getActions() {
		return actions;
	}

}
