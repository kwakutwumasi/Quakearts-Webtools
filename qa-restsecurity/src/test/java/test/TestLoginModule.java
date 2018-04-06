package test;

import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import com.quakearts.webapp.security.auth.DirectoryRoles;
import com.quakearts.webapp.security.auth.OtherPrincipal;

public class TestLoginModule implements LoginModule {

	Subject subject;
	CallbackHandler callbackHandler;
	
	public static boolean addDenyRole;
	
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		Group group = new DirectoryRoles("Roles");
		group.addMember(new OtherPrincipal("TestRole"));		
		subject.getPrincipals().add(group);
		subject.getPrincipals().add(group.members().nextElement());
		
		if(addDenyRole) {
			OtherPrincipal otherPrincipal = new OtherPrincipal("TestDenyRole");
			group.addMember(otherPrincipal);		
			subject.getPrincipals().add(group);
			subject.getPrincipals().add(otherPrincipal);
		}
		
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		return true;
	}

}
