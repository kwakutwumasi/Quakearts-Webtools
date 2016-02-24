package com.quakearts.webapp.security.auth.jbpm;

import com.quakearts.webapp.security.auth.UserPrincipal;

import java.security.Principal;
import java.security.acl.Group;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.Subject;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
import org.jbpm.security.authentication.DefaultAuthenticationService;

public class SubjectAuthenticationService extends DefaultAuthenticationService{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8689144261178063374L;

	public SubjectAuthenticationService() {
    }

    @SuppressWarnings("rawtypes")
	public String getActorId() {
        String actorid = null;
		Subject subject;
		try {
			subject = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
		} catch (PolicyContextException e) {
			return "";
		}
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

    public void close() {
    }
}
