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
package com.quakearts.security.cryptography.permission;

import java.security.Permission;

public final class CryptographyOperationPermission extends Permission {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3809203656901925002L;

	private String actions;
	private String[] actionsArray;
	
	public CryptographyOperationPermission(String name, String actions) {
		super(name);
		this.actions = actions;
		actionsArray = actions.split(",");
	}

	@Override
	public boolean implies(Permission permission) {
		if(!(permission instanceof CryptographyOperationPermission))
			return false;
		
		if(!permission.getName().equals(this.getName()))
			return false;
		
		if(actions.equals("all"))
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
		
		if(!(obj instanceof CryptographyOperationPermission))
			return false;
		
		CryptographyOperationPermission comp = (CryptographyOperationPermission) obj;
		
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
