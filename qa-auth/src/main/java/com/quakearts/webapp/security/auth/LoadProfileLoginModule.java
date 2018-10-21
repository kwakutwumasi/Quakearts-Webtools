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
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Set;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.quakearts.webapp.security.util.UtilityMethods;

import java.util.Iterator;

public class LoadProfileLoginModule implements LoginModule{ 
    private static final Logger log = Logger.getLogger(LoadProfileLoginModule.class.getName());
	public static final String RESULT_ORIENTATION_POTRAITPARAMETER = "result_orientation_potrait";
	public static final String DATABASE_ROLESCOLUMNSPARAMETER = "database.rolescolumns";
	public static final String DATABASE_ROLESQUERYPARAMETER = "database.rolesquery";
	public static final String DATABASE_JNDINAMEPARAMETER = "database.jndiname";
	public static final String DATABASE_ROLENAMEPARAMETER = "database.rolename";
	
	private Subject subject;
    private Group rolesgrp;
    private boolean loginOk=false;
    private HashMap<String,String> userprof = new HashMap<>();
    private Map<String, ?> sharedState;

    private String rolesgrpname;
	private String  dsjndiname;
	private String  rolesquery;
	private String  rolescolumns;
    private boolean resOrientPort;
    
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) 
    {
        this.subject = subject;
        this.sharedState = sharedState;
        rolesgrpname = (String) options.get(DATABASE_ROLENAMEPARAMETER);
        dsjndiname = (String) options.get(DATABASE_JNDINAMEPARAMETER);
        rolesquery = (String) options.get(DATABASE_ROLESQUERYPARAMETER);
        rolescolumns = (String) options.get(DATABASE_ROLESCOLUMNSPARAMETER);
        
        setCommonOptions(options);        
    }

	private void setCommonOptions(Map<String, ?> options) {
		resOrientPort = Boolean.parseBoolean((String)options.get(RESULT_ORIENTATION_POTRAITPARAMETER));                
        if (rolesgrpname == null)
        	rolesgrpname = "Roles";       
	}

	public boolean login() throws LoginException {
        if(sharedState == null){
        	return false;
        }
                
        try {            
            DataSource ds = getDataSource(); 
            String username = loadUsernameAndPassword();
            if(loginOk){
	            loadRoles(username, ds);
            }
        } catch(LoginOperationException e){
        	loginOk = false;
            log.log(Level.SEVERE, "Login had exceptions.",e);
        }
        return loginOk;          	
    }

	private DataSource getDataSource() throws LoginOperationException {
		DataSource ds;
		try{
		    InitialContext icx;
		    icx = UtilityMethods.getInitialContext();
		    ds = (DataSource) icx.lookup(dsjndiname);
		} catch (NamingException e) {
		    throw new LoginOperationException("Unable to load DataSource",e);
		}
		return ds;
	}

	private String loadUsernameAndPassword() {
		String username;
		Object loginPrincipal = sharedState.get("javax.security.auth.login.name");
		username = (loginPrincipal instanceof Principal)?((Principal) loginPrincipal).getName():null;
		Object loginOkObject = sharedState.get("com.quakearts.LoginOk");

		if(loginOkObject!=null)
			loginOk = (Boolean)(loginOkObject) && username!=null;
		
		return username;
	}

	private void loadRoles(String username, DataSource ds) throws LoginOperationException {
		try(Connection con = ds.getConnection(); PreparedStatement stmt=con.prepareStatement(rolesquery);) {
		    stmt.setString(1,username);
		    extractResults(username, stmt);
		} catch (SQLException e) {
		    throw new LoginOperationException("SQLException during login",e);
		}
	}

	private void extractResults(String username, PreparedStatement stmt) 
			throws SQLException, LoginOperationException {
		try(ResultSet rs = stmt.executeQuery()){
		    if(!resOrientPort){
		        handleResultsLandscape(username, rs);
		    } else {
		        handleResultsPortrait(username, rs);
		    }
		}
	}

	private void handleResultsLandscape(String username, ResultSet rs) throws SQLException, LoginOperationException {
		if(rs.next()){
		    String[] roles = rolescolumns.split(",");
		    for(String role:roles){
		        userprof.put(role,rs.getString(role));
		    }
		} else {
		    throw new LoginOperationException("No profile found for "+username);
		}
	}

	private void handleResultsPortrait(String username, ResultSet rs) throws SQLException, LoginOperationException {
		if(rs.next()){
		    int i=1;
		    do {
		    	userprof.put(""+(i++), rs.getString(rolescolumns));
		    } while(rs.next());
		} else {
		    throw new LoginOperationException("No profile found for "+username);	                	
		}
	}

	public boolean commit() {
        if(loginOk){
            Set<Principal> principalset = subject.getPrincipals();
            
			findRolesGroup(principalset);				
            
            if(rolesgrp == null){
                createRolesGroup(principalset);
            }
                       
            loadRoles();

			addPrincipalSetToSubject(principalset);
            userprof = null;
            return true;
        } else {
            return false;
        }
    }

	private void findRolesGroup(Set<Principal> principalset) {
		for (Iterator<Principal> i = principalset.iterator(); i.hasNext();) {
			Object obj = i.next();
			if (obj instanceof Group && ((Group) obj).getName().equals(rolesgrpname)) {
				rolesgrp = (Group) obj;
				break;
			}
		}
	}

	private void createRolesGroup(Set<Principal> principalset) {
		rolesgrp = new DirectoryRoles(rolesgrpname);
		principalset.add(rolesgrp);
	}

	private void loadRoles() {
		userprof.entrySet().forEach(entry->{
			OtherPrincipal principal;
			principal = new OtherPrincipal(entry.getValue()!=null?
		    		entry.getValue():"",entry.getKey());
		    rolesgrp.addMember(principal);
		});
	}

	private void addPrincipalSetToSubject(Set<Principal> principalset) {
		Enumeration<? extends Principal> members = rolesgrp.members();
		while (members.hasMoreElements()) {
			Principal type = members.nextElement();
			principalset.add(type);				
		}
	}
    
	public boolean abort() {
    	subject = null;
        rolesgrp = null;
        loginOk = false;
        userprof = new HashMap<>();
        sharedState = null;

        return true;
    }

	public boolean logout() {
       return abort();
    }
}
