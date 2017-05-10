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

import java.security.Principal;
import java.security.acl.Group;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;

import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.security.auth.UserPrincipal;

public class TagUtilities {
	public TagUtilities(){
	}
	
	public static String formatDate(Date date,String format){
		if(date==null || format ==null)
			return "";
		return new SimpleDateFormat(format).format(date);
	}

	public static Date now(){
		return new Date();
	}
	
	public static Date dateAdd(Date date, String part, int amount){
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		if(part.trim().equals("y"))
			cal.add(Calendar.YEAR, amount);
		else if(part.trim().equals("M"))
			cal.add(Calendar.MONTH, amount);
		else if(part.trim().equals("d"))
			cal.add(Calendar.DAY_OF_MONTH, amount);
		else if(part.trim().equals("H"))
			cal.add(Calendar.HOUR, amount);
		else if(part.trim().equals("m"))
			cal.add(Calendar.MINUTE, amount);
		else if(part.trim().equals("s"))
			cal.add(Calendar.SECOND, amount);
		
		return cal.getTime();
	}
	
	public static String cid(UIComponent component){
	    FacesContext context = FacesContext.getCurrentInstance();
	    return component.getClientId(context);
	}
	
    @SuppressWarnings("rawtypes")
	public static String getActorId() {
        String actorid = null;
		Subject subject;
		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
	        return "anonymous";
		}
        if(subject!=null){
	        Set principals = subject.getPrincipals(Group.class);
	        for(Iterator i = principals.iterator();i.hasNext();){
	            Group roles = (Group) i.next();
	            if(roles.getName().equals("Roles"))
	            {
	                Enumeration enumer = roles.members();
	                while(enumer.hasMoreElements()){
	                    Object obj = enumer.nextElement();
	                    if(obj instanceof UserPrincipal){ 
	                        actorid =((Principal)obj).getName();
	                        break;
	                    }
	                }
	            }
	        }
	        return actorid;
        }
        return "anonymous";
    }
    
    public static String truncate(String input,int length){
    	return input.length()>length?input.substring(0,length)+"...":input;
    }
    
    @SuppressWarnings("rawtypes")
	public static int length(Object object){
    	if(object instanceof Collection){
    		return ((Collection)object).size();
    	} else if(object instanceof Map){
    		return ((Map)object).size();    		
    	}
    	return 0;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List toList(Set set){
    	if(set == null)
    		return null;
    	
    	List list = new ArrayList();
    	list.addAll(set);
    	return list;
    }
    
    public static void refresh(Object object, String domain){
    	try {
        	if(domain==null){
        		DataStoreFactory.getInstance().getDataStore().refresh(object);
        	} else {
        		DataStoreFactory.getInstance().getDataStore(domain).refresh(object);
        	}
		} catch (DataStoreException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Refresh Error", e.getMessage()));
		}
    }
}
