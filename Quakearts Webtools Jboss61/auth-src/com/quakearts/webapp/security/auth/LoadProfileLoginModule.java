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
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

import com.quakearts.webapp.security.util.UtilityMethods;

import java.util.Iterator;

public class LoadProfileLoginModule implements LoginModule{ 
    private Subject subject;
    private Group rolesgrp;
    @SuppressWarnings("rawtypes")
	private Map sharedState;
    @SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
    private boolean loginOk=false, require_password_change=false, change_password=false;
    private String rolesgrpname, dsjndiname, rolesquery, rolescolumns, changePasswordRole;
    private String[] defaultroles=null;
    private HashMap<String,String> userprof = new HashMap<String,String>();
    private static final Logger log = Logger.getLogger(LoadProfileLoginModule.class);
    private boolean resOrientPort;
    
    public LoadProfileLoginModule() {
    }

    @SuppressWarnings("rawtypes")
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) 
    {
        log.trace("Initializing....");
        this.subject = subject;
        this.sharedState = sharedState;
        this.options = options;
        rolesgrpname = (String) options.get("database.rolename");
        dsjndiname = (String) options.get("database.jndiname");
        rolesquery = (String) options.get("database.rolesquery");
        rolescolumns = (String) options.get("database.rolescolumns");
        String defaultroles_str = (String) options.get("database.defaultroles");
        

        resOrientPort = Boolean.parseBoolean((String)options.get("result_orientation_potrait"));
        require_password_change = Boolean.parseBoolean((String)options.get("require_password_change"));
        
        if(require_password_change){
        	changePasswordRole = (String)options.get("changePasswordRole");
        }
        
        require_password_change = require_password_change && (changePasswordRole!=null);
        
        StringTokenizer tokenizer;
        
        if (rolesgrpname == null)
         rolesgrpname = new String("Roles");
        
        if(defaultroles_str != null){
         tokenizer = new StringTokenizer(defaultroles_str,";",false);
         defaultroles = new String[tokenizer.countTokens()];
         
         for(int i=0;i<defaultroles.length;i++){
             defaultroles[i] = tokenizer.nextToken();
         }
        }
        
        
        log.trace("Initialization complete.\n"+
                  "\t\tZeDatabaseLoginModule options:\n" +
                  "\t\tdatabase.jndiname: "+dsjndiname+"\n"+
                  "\t\tdatabase.rolesquery: "+rolesquery+"\n"+
                  "\t\tdatabase.rolename: "+rolesgrpname+"\n"+
                  "\t\tresult_orientation_potrait: "+resOrientPort+"\n");
    }

	public boolean login() throws LoginException {
        log.trace("Starting loading......");
        String username=null;
        InitialContext icx;
        DataSource ds;
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
                
        try{
            try{
                icx = UtilityMethods.getInitialContext();
                ds = (DataSource) icx.lookup(dsjndiname);
                con = ds.getConnection();
            } catch (NamingException e) {
                log.error("NamingException during login", e);
                throw new DirectoryLoginException("",e);
            } catch (SQLException e) {
                log.error("SQLException during login", e);
                throw new DirectoryLoginException("",e);
            }

            if(sharedState != null){
                log.trace("Using first pass....");
                Object loginDN_val = sharedState.get("javax.security.auth.login.name");
                Object changePasswordObject = sharedState.get("com.zenithbank.ChangePassword");
                username = (loginDN_val!=null && loginDN_val instanceof Principal)?((Principal) loginDN_val).getName():null;
                change_password = (changePasswordObject==null?false:(Boolean) (changePasswordObject));
                Object loginOkObject = sharedState.get("com.zenithbank.LoginOk");

                loginOk = (loginOkObject==null?false:(Boolean)(loginOkObject));
            }
            if(loginOk){
	            try {
	                log.trace("Getting roles for "+username+"...");
	                if(rolesquery !=null){
		                stmt = con.prepareStatement(rolesquery);
		                stmt.setString(1,username);
		                rs = stmt.executeQuery();
		                
		                if(!resOrientPort){
			                if(rs.next()){
			                    log.trace("Got profile. Loading....");
			                    String[] roles = stringToArray(rolescolumns,",");
			                    for(String role:roles){
			                        userprof.put(role,rs.getString(role));
			                    }
			                }else{
			                    throw new DirectoryLoginException("No profile found for "+username);
			                }
		                } else {
			                if(rs.next()){
			                    log.trace("Got profile. Loading....");
			                    int i=1;
			                    do{
			                    	userprof.put(""+(i++), rs.getString(rolescolumns));
			                    }while(rs.next());
			                    log.trace("Got roles: "+userprof);
			                }else{
			                    throw new DirectoryLoginException("No profile found for "+username);	                	
			                }
		                }
		                rs.close();
		                stmt.close();
	                }
	                con.close();
	            } catch (SQLException e) {
	                log.error("SQLException during login", e);
	                throw new DirectoryLoginException("",e);
	            } catch (NullPointerException e) {
	                log.error("NullPointerException during login.",e);
	                throw new DirectoryLoginException("",e);
	            }
	            
	            return loginOk;
            } else {
                log.trace("Login failed.");
                return false;          	
            }
        } catch(Exception e){
            log.trace("Login failed.",e);
            return false;
        } finally{
            try{
                con.close();
            }catch(Exception ex){
            }
        }
    }

    @SuppressWarnings("rawtypes")
	public boolean commit() {
        if(loginOk){
            log.trace("Comitting...");
            StringBuffer buffer = new StringBuffer();
            Set<Principal> principalset = subject.getPrincipals();
            
            log.trace("Fetching already existing roles group...");
            for(Iterator i=principalset.iterator();i.hasNext();){
                Object obj = i.next();
                if(obj instanceof DirectoryRoles){
                    rolesgrp = (DirectoryRoles) obj;
                    log.trace("Found existing roles group: "+rolesgrp.getName());
                    break;
                }
            }
            
            if(rolesgrp == null){
                log.trace("Creating roles group...");
                rolesgrp = new DirectoryRoles(rolesgrpname);
            }
                       
            String name;
            OtherPrincipal principal;
            if(change_password){
                principal = new OtherPrincipal(changePasswordRole,"changePassword");
                rolesgrp.addMember(principal);            	
            } else {
	            for(String key:userprof.keySet()){
	                name = userprof.get(key);
	                principal = new OtherPrincipal(name!=null?name:"",key);
	                rolesgrp.addMember(principal);
	                if(log.isTraceEnabled())
	                	buffer.append("OtherPrincipal Attribute: "+key+" - Name:"+name+"\n\t\t");
	            }

	            if(defaultroles!=null)
		            for(String role:defaultroles){
		                principal = new OtherPrincipal(role,"default");
		                rolesgrp.addMember(principal);   
		                if(log.isTraceEnabled())
		                	buffer.append("OtherPrincipal Attribute: default - Name:"+role+"\n\t\t");
		            }
            }
                        
            principalset.add(rolesgrp);
            if(log.isTraceEnabled())
            	log.trace("Added \n\t\t" +buffer+"\n\t\tto rolesgrp "+rolesgrp.getName());
            userprof = null;
            return true;
        }else{
            return false;
        }
    }
    
    @SuppressWarnings("rawtypes")
	public boolean abort() {
        if(loginOk){
            userprof = null;
            Set princ = subject.getPrincipals();
            princ.remove(rolesgrp);
            return true;
        }else
            return false;
    }

    @SuppressWarnings("rawtypes")
	public boolean logout() {
        if(loginOk){
            userprof = null;
            Set princ = subject.getPrincipals();
            princ.remove(rolesgrp);
            return true;
        }else
            return false;
    }

    private static String[] stringToArray(String string, String delim){
        StringTokenizer tokenizer = new StringTokenizer(string,delim);
        String[] arrayofstring = null;
        int i = tokenizer.countTokens();
        if(i>0)
        {
            arrayofstring = new String[i];
            i=0;
            while(tokenizer.hasMoreTokens())
            {
                arrayofstring[i++] = tokenizer.nextToken();
            }
        }
        return arrayofstring;
    }

}
