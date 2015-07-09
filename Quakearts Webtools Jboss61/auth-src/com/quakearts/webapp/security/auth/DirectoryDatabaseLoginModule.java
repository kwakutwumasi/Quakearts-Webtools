package com.quakearts.webapp.security.auth;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.facelets.util.UtilityMethods;
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.util.HashPassword;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DirectoryDatabaseLoginModule implements LoginModule{ 
    private Subject subject;
    private Group rolesgrp;
    private CallbackHandler callbackHandler;
    @SuppressWarnings("rawtypes")
	private Map sharedState;
    @SuppressWarnings({ "rawtypes", "unused" })
	private Map options;
    private boolean loginOk=false, use_first_pass=false;
    private String rolesgrpname,dsjndiname,authenticationquery,rolesquery,hashalgorithm,profilequery;
    private String[] defaultroles;
    private ArrayList<String> roles;
    private HashMap<String,String> userprof;
    private static final Logger log = Logger.getLogger(DirectoryDatabaseLoginModule.class);
    private AttemptChecker checker;
    
    public DirectoryDatabaseLoginModule() {
    }

    @SuppressWarnings("rawtypes")
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options) 
    {
        log.trace("Initializing....");
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        rolesgrpname = (String) options.get("database.rolename");
        dsjndiname = (String) options.get("database.jndiname");
        authenticationquery = (String) options.get("database.authenticationquery");
        rolesquery = (String) options.get("database.rolesquery");
        profilequery = (String) options.get("database.profilequery");
        hashalgorithm = (String) options.get("database.hashalgorithm");

        String defaultroles_str = (String) options.get("database.defaultroles");
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
        
        String maxAttempts_str = (String) options.get("max_try_attempts");
        String lockoutTime_str = (String) options.get("lockout_time");
        
        if(maxAttempts_str == null && lockoutTime_str == null){
        	checker = AttemptChecker.getChecker(dsjndiname);
        }else{
	        int maxAttempts, lockoutTime;
	        try {
				maxAttempts = Integer.parseInt(maxAttempts_str);
			} catch (Exception e) {
				maxAttempts = 4;
			}
			
			try {
				lockoutTime = Integer.parseInt(lockoutTime_str);
			} catch (Exception e) {
				lockoutTime = 3600000;
			}
        	AttemptChecker.createChecker(dsjndiname, maxAttempts, lockoutTime);
        	checker = AttemptChecker.getChecker(dsjndiname);
        }
        
        Object use_first_pass_val = options.get("use_first_pass");
        if(use_first_pass_val != null?use_first_pass_val instanceof String:false)
            use_first_pass = Boolean.parseBoolean((String)use_first_pass_val);
        
        log.trace("Initialization complete.\n"+
                  "\t\tZeDatabaseLoginModule options:\n" +
                  "\t\tdatabase.jndiname: "+dsjndiname+"\n"+
                  "\t\tdatabase.authenticationquery: "+authenticationquery+"\n"+
                  "\t\tdatabase.rolesquery: "+rolesquery+"\n"+
                  "\t\tdatabase.profilequery: "+profilequery+"\n"+
                  "\t\tdatabase.hashalgorithm: "+hashalgorithm+"\n"+
                  "\t\tdatabase.rolename: "+rolesgrpname+"\n");
    }

    @SuppressWarnings("unchecked")
	public boolean login() throws LoginException {
        log.trace("Starting authentication......");
        String username=null;
        byte[] password = null;
        InitialContext icx;
        DataSource ds;
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
                
        try{
            Callback[] callbacks = new Callback[2];

            if(use_first_pass){
                if(sharedState != null){
                    log.trace("Using first pass....");
                    Object loginDN_val = sharedState.get("javax.security.auth.login.name");
                    Object password_val = sharedState.get("javax.security.auth.login.password");
                    username = (loginDN_val!=null && loginDN_val instanceof Principal)?((Principal) loginDN_val).getName():null;
                    password = (password_val!=null && password_val instanceof char[])?new String((char[]) password_val).getBytes():null;
                }
            }
            
            if(!use_first_pass || username==null || password==null){
                NameCallback name = new NameCallback("Enter your username");
                PasswordCallback pass = new PasswordCallback("Enter your password:",false);           
                callbacks[0] = name;
                callbacks[1] = pass;

                try {
                    log.trace("Handling callback....");
                    callbackHandler.handle(callbacks);
                } catch (UnsupportedCallbackException e) {
                    throw new DirectoryLoginException("Callback is not supported", e);
                } catch (IOException e) {
                    throw new DirectoryLoginException("IOException during call back", e);
                }
                
                username = name.getName()==null? name.getDefaultName():name.getName();
                password = (new String(pass.getPassword())).getBytes();
                
                if (sharedState != null){
                    log.trace("Storing state....");
                    UserPrincipal shareduser = new UserPrincipal(username);
                    sharedState.put("javax.security.auth.login.name", shareduser);
                    char[] sharedpass = new String(password).toCharArray();
                    sharedState.put("javax.security.auth.login.password", sharedpass);
                }
            }
                       
            if(username == null || password == null)
                throw new DirectoryLoginException("Login/Password is null.");
            
            if(checker.isLocked(username))
                throw new DirectoryLoginException("Account is lockedout.");
            
            String passwordhash;
            HashPassword hash = new HashPassword(new String(password),hashalgorithm,10,HashPassword.DEFAULT_SALT);

            passwordhash = hash.toString();
            
            if(passwordhash == null)
                throw new DirectoryLoginException("Password cannot be hashed.");
            	
            try {
                userprof = new HashMap<String,String>();
                try{
                    icx = UtilityMethods.getInitialContext();
                    ds = (DataSource) icx.lookup(dsjndiname);
                } catch (NamingException e) {
                    log.error("NamingException during login", e);
                    throw new DirectoryLoginException("",e);
                }
                if(log.isTraceEnabled())
                    log.trace("Verifying username "+username);
                con = ds.getConnection();
                stmt = con.prepareStatement(authenticationquery);
                stmt.setString(1,username);
                stmt.setString(2,passwordhash);
                rs = stmt.executeQuery();
                if(!rs.next()){
                    rs.close();
                    stmt.close();
                    con.close();                    
                    throw new LoginException("Invalid Password.");
                }
                
                rs.close();
                stmt.close();
                stmt = con.prepareStatement(rolesquery);
                stmt.setString(1,username);
                rs = stmt.executeQuery();
                roles = new ArrayList<String>();
                while(rs.next()){
                    roles.add(rs.getString("roles"));
                }

                rs.close();
                stmt.close();
                stmt = con.prepareStatement(profilequery);
                stmt.setString(1,username);
                rs = stmt.executeQuery();
                if(rs.next()){
                    userprof.put("username",username);
                    userprof.put("unit",rs.getString("unit"));
                    userprof.put("department",rs.getString("department"));
                    userprof.put("branch",rs.getString("branch"));
                    userprof.put("grade",rs.getString("grade"));
                    userprof.put("position",rs.getString("position"));
                    userprof.put("email",rs.getString("email"));
                    userprof.put("staffidnum",rs.getString("staffidnum"));
                    userprof.put("name",rs.getString("name"));   
                }
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                log.error("SQLException during login", e);
                throw new DirectoryLoginException("",e);
            } catch (NullPointerException e) {
                log.error("NullPointerException during login.",e);
                throw new DirectoryLoginException("",e);
            }
            password = null;
            loginOk = true;
            log.trace("Login is successful.");
            return loginOk;
        }catch(DirectoryLoginException e){
            try{
                rs.close();
            }catch(Exception ex){
                log.error("ResultSet could not be closed.");
            }
            try{
                stmt.close();
            }catch(Exception ex){
                log.error("PreparedStatement could not be closed.");
            }
            try{
                con.close();
            }catch(Exception ex){
                log.error("Connection could not be closed.");
            }
            password = null;
            log.trace("Login failed.",e);
            return false;
        }
    }

    public boolean commit() {
        if(loginOk){
            String attribute;
            attribute = userprof.get("username");
            UserPrincipal user = (attribute == null)?new UserPrincipal("NONE") : new UserPrincipal(attribute);

            attribute = userprof.get("unit");
            UnitPrincipal unit = (attribute == null)? new UnitPrincipal("NONE"):new UnitPrincipal(attribute);

            attribute = userprof.get("department");
            DeptPrincipal dept = (attribute == null)?new DeptPrincipal("NONE") :new DeptPrincipal(attribute);

            attribute = userprof.get("branch");
            BranchPrincipal branch =  (attribute == null)? new BranchPrincipal("NONE") :new BranchPrincipal(attribute);

            attribute = userprof.get("grade");
            GradePrincipal grade = (attribute == null)? new GradePrincipal("NONE"):new GradePrincipal(attribute);

            attribute = userprof.get("position");
            PositionPrincipal position = (attribute == null)? new PositionPrincipal("NONE"): new PositionPrincipal(attribute);

            attribute = userprof.get("email");
            EmailPrincipal email = (attribute == null)? new EmailPrincipal("NONE"): new EmailPrincipal(attribute);

            attribute = userprof.get("staffidnum");
            StaffNumberPrincipal number = (attribute == null)? new StaffNumberPrincipal("NONE"): new StaffNumberPrincipal(attribute);

            attribute = userprof.get("name");
            NamePrincipal name = (attribute == null)?new NamePrincipal("NO NAME"): new NamePrincipal(attribute);

            Set<Principal> principalset = subject.getPrincipals();
            rolesgrp = new DirectoryRoles(rolesgrpname);                    
            rolesgrp.addMember(user);            
            rolesgrp.addMember(name);
            rolesgrp.addMember(unit);
            rolesgrp.addMember(dept);
            rolesgrp.addMember(branch);
            rolesgrp.addMember(grade);
            rolesgrp.addMember(position);
            rolesgrp.addMember(email);
            rolesgrp.addMember(number);
            
            log.trace("Commiting "+user.getName()+" with profile:\n" +
                      "Name: " +name.getName()+"\n"+
                      "Unit: " +unit.getName()+"\n"+
                      "Department: " +dept.getName()+"\n"+
                      "Branch: " +branch.getName()+"\n"+
                      "Grade: " +grade.getName()+"\n"+
                      "Position: " +position.getName()+"\n"+
                      "E-mail: " +email.getName()+"\n"+
                      "Staff Number: " +number.getName()+"\n");
            
            for(int i=0;i<defaultroles.length;i++){
                rolesgrp.addMember(new OtherPrincipal(defaultroles[i]));
            }
            for(String role:roles){
                rolesgrp.addMember(new OtherPrincipal(role));
            }
            principalset.add(rolesgrp);

            userprof = null;
            roles = null;
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

}
