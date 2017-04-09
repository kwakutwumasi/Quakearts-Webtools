package com.quakearts.security.cryptography.permission;

import java.security.Permission;

public final class CrytographyOperationPermission extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809203656901925002L;

	private String actions;
	private String[] actionsArray;
	
	public CrytographyOperationPermission(String name, String actions) {
		super(name);
		this.actions = actions;
		actionsArray = actions.split(",");
	}

	@Override
	public boolean implies(Permission permission) {
		if(!(permission instanceof CrytographyOperationPermission))
			return false;
		
		if(!permission.getName().equals(this.getName()))
			return false;
		
		if(permission.getActions().equals("all"))
			return true;
		
		for(String action:actionsArray){
			if(!permission.getActions().contains(action))
				return false;
		}
		
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)
			return false;
		
		if(obj==this)
			return true;
		
		if(!(obj instanceof CrytographyOperationPermission))
			return false;
		
		CrytographyOperationPermission comp = (CrytographyOperationPermission) obj;
		
		return this.actions.equals(comp.actions) && this.getName().equals(comp.getName());
	}

	@Override
	public int hashCode() {
		return this.actions.hashCode()*5+this.getName().hashCode()*7;
	}

	@Override
	public String getActions() {
		return actions;
	}

}
