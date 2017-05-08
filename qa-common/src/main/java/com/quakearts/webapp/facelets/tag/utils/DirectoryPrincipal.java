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
package com.quakearts.webapp.facelets.tag.utils;

public enum DirectoryPrincipal {
	USER,
	NAME,
	GRADE,
	POSITION,
	DEPARTMENT,
	UNIT,
	BRANCH,
	EMAIL;
	
	public static String toString(DirectoryPrincipal principal){
	switch (principal) {
		case USER:
			return "QUAKEARTS.user";
		case NAME:
			return "QUAKEARTS.name";
		case GRADE:
			return "QUAKEARTS.grade";
		case POSITION:
			return "QUAKEARTS.post";
		case DEPARTMENT:
			return "QUAKEARTS.dept";
		case UNIT:
			return "QUAKEARTS.unit";
		case BRANCH:
			return "QUAKEARTS.bran";
		case EMAIL:
			return "QUAKEARTS.mail";
		default:
			return "";
		}	
	}
	
	public String toString(){
		return toString(this);
	}
}
