/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.tag.utils;

import java.io.Serializable;
import java.security.Principal;
import java.security.acl.Group;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import com.quakearts.webapp.security.auth.DeptPrincipal;
import com.quakearts.webapp.security.auth.EmailPrincipal;
import com.quakearts.webapp.security.auth.GradePrincipal;
import com.quakearts.webapp.security.auth.NamePrincipal;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.PositionPrincipal;
import com.quakearts.webapp.security.auth.UnitPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;

public class SubjectHelper implements Serializable {
    private static final long serialVersionUID = 4894523893065329890L;
	private final Map<String, Principal> map = new HashMap<String, Principal>();
	public static final String KEY = "QUAKEARTS.subject.helper", ROLESNAME="Roles";
	
	public SubjectHelper() throws PolicyContextException{
		this(ROLESNAME);
	}
	
	public SubjectHelper(Subject subject) {
		setupSubject(subject,ROLESNAME);
	}
	
	public SubjectHelper(Subject subject, String rolesname) {
		setupSubject(subject,rolesname);
	}
	
	public SubjectHelper(String rolesName) throws PolicyContextException{
		Subject subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		if (subject==null)
			throw new NullPointerException("Subject could not be obtained. Security must be enabled.");
		
		setupSubject(subject,rolesName);
	}
	
	@SuppressWarnings("rawtypes")
	public Map getSubjectMap(){
		return Collections.unmodifiableMap(map);
	}
	
	public Principal getPrincipal(String key){
		return map.get(key);
	}
	
	public Principal getPrincipal(DirectoryPrincipal principal){
		return map.get(principal.toString());
	}
	
	@SuppressWarnings("rawtypes")
	private void setupSubject(Subject subject, String rolesName) {
		Set principals = subject.getPrincipals(Group.class);
		for(Iterator i = principals.iterator();i.hasNext();){
			Group roles = (Group) i.next();
			if(roles.getName().equals(rolesName))
			{
				Enumeration enumer = roles.members();
				while(enumer.hasMoreElements()){
					Principal principal = (Principal) enumer.nextElement();
					if(principal instanceof OtherPrincipal){
						map.put(((OtherPrincipal)principal).getAttribute(), principal);
					} else if (principal instanceof UserPrincipal){
						map.put(DirectoryPrincipal.USER.toString(), principal);						
					} else if (principal instanceof GradePrincipal){
						map.put(DirectoryPrincipal.GRADE.toString(), principal);						
					} else if (principal instanceof NamePrincipal){
						map.put(DirectoryPrincipal.NAME.toString(), principal);						
					} else if (principal instanceof DeptPrincipal){
						map.put(DirectoryPrincipal.DEPARTMENT.toString(), principal);
					} else if (principal instanceof UnitPrincipal){
						map.put(DirectoryPrincipal.UNIT.toString(), principal);
					} else if (principal instanceof EmailPrincipal){
						map.put(DirectoryPrincipal.EMAIL.toString(), principal);						
					} else if (principal instanceof PositionPrincipal){
						map.put(DirectoryPrincipal.POSITION.toString(), principal);						
					} else {
						customMapping(principal, map);
					}
				}
			}
		}
	}

	protected void customMapping(Principal principal, Map<String, Principal> map) {
	}
}
