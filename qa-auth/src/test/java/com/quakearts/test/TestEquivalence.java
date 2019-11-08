package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.webapp.security.auth.DirectoryPrincipal;
import com.quakearts.webapp.security.auth.DirectoryRoles;
import com.quakearts.webapp.security.auth.EmailPrincipal;
import com.quakearts.webapp.security.auth.NamePrincipal;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;

public class TestEquivalence {

	@Test
	public void test() {
		NamePrincipal namePrincipal = new NamePrincipal("Kwaku");
		EmailPrincipal emailPrincipal = new EmailPrincipal("kwaku@email.com");
		UserPrincipal userPrincipal = new UserPrincipal("kwaku");
		OtherPrincipal otherPrincipal = new OtherPrincipal("Other");
		OtherPrincipal otherPrincipal2 = new OtherPrincipal("Other2", "other2");
		
		assertThat(namePrincipal, is(namePrincipal));
		assertThat(namePrincipal.hashCode(), is(namePrincipal.hashCode()));
		assertThat(emailPrincipal, is(emailPrincipal));
		assertThat(emailPrincipal.hashCode(), is(emailPrincipal.hashCode()));
		assertThat(userPrincipal, is(userPrincipal));
		assertThat(userPrincipal.hashCode(), is(userPrincipal.hashCode()));
		assertThat(otherPrincipal, is(otherPrincipal));
		assertThat(otherPrincipal.hashCode(), is(otherPrincipal.hashCode()));
		assertThat(otherPrincipal2, is(otherPrincipal2));
		assertThat(otherPrincipal2.hashCode(), is(otherPrincipal2.hashCode()));

		assertFalse(namePrincipal.equals(null));
		assertFalse(emailPrincipal.equals(null));
		assertFalse(userPrincipal.equals(null));
		assertFalse(otherPrincipal.equals(null));
		assertFalse(otherPrincipal2.equals(null));

		Object namePrincipalObject = namePrincipal, 
				emailPrincipalObject = emailPrincipal,
				userPrincipalObject = userPrincipal, 
				otherPrincipalObject = otherPrincipal,
				otherPrincipal2Object = otherPrincipal2;
		
		assertFalse(namePrincipalObject.equals(""));
		assertFalse(emailPrincipalObject.equals(""));
		assertFalse(userPrincipalObject.equals(""));
		assertFalse(otherPrincipalObject.equals(""));
		assertFalse(otherPrincipal2Object.equals(""));
		
		assertFalse(namePrincipal.equals(new NamePrincipal("Kofi")));
		assertFalse(emailPrincipal.equals(new EmailPrincipal("kofi@email.com")));
		assertFalse(userPrincipal.equals(new UserPrincipal("kofi")));
		assertFalse(otherPrincipal.equals(new OtherPrincipal("Other3")));
		assertFalse(otherPrincipal2.equals(new OtherPrincipal("Other3", "other3")));
		
		assertFalse(otherPrincipal2.equals(new OtherPrincipal("Other3", "other2")));
		assertFalse(otherPrincipal2.equals(new OtherPrincipal("Other2", "other3")));

		assertThat(otherPrincipal2, is(new OtherPrincipal("Other2", "other2")));
		assertThat(otherPrincipal2.hashCode(), is(new OtherPrincipal("Other2", "other2").hashCode()));
		assertThat(namePrincipal, is(new NamePrincipal("Kwaku")));
		assertThat(namePrincipal.hashCode(), is(new NamePrincipal("Kwaku").hashCode()));
		assertThat(emailPrincipal, is(new EmailPrincipal("kwaku@email.com")));
		assertThat(emailPrincipal.hashCode(), is(new EmailPrincipal("kwaku@email.com").hashCode()));
		assertThat(userPrincipal, is(new UserPrincipal("kwaku")));
		assertThat(userPrincipal.hashCode(), is(new UserPrincipal("kwaku").hashCode()));
		assertThat(otherPrincipal, is(new OtherPrincipal("Other")));
		assertThat(otherPrincipal.hashCode(), is(new OtherPrincipal("Other").hashCode()));
		
		assertFalse(new NameAttributeOverridePrincipal(null, "test").equals(new NameAttributeOverridePrincipal("test", "test")));
		assertFalse(new NameAttributeOverridePrincipal("test", null).equals(new NameAttributeOverridePrincipal("test", "test")));
		assertThat(new NameAttributeOverridePrincipal("test", null), is(new NameAttributeOverridePrincipal("test", null)));
		assertThat(new NameAttributeOverridePrincipal("test", null).hashCode(), is(new NameAttributeOverridePrincipal("test", null).hashCode()));
		assertThat(new NameAttributeOverridePrincipal(null, "test"), is(new NameAttributeOverridePrincipal(null, "test")));
		assertThat(new NameAttributeOverridePrincipal(null, "test").hashCode(), is(new NameAttributeOverridePrincipal(null, "test").hashCode()));
		
		DirectoryRoles directoryRoles = new DirectoryRoles("Roles");
		
		assertFalse(directoryRoles.equals(null));
		assertThat(directoryRoles, is(directoryRoles));
		assertThat(directoryRoles.hashCode(), is(directoryRoles.hashCode()));
		assertFalse(((Object)directoryRoles).equals(""));

		DirectoryRoles directoryRoles2 = new DirectoryRoles(null);
		assertThat(directoryRoles, is(directoryRoles2));
		assertThat(directoryRoles.hashCode(), is(directoryRoles2.hashCode()));
		DirectoryRoles directoryRoles3 = new DirectoryRoles("Roles3");
		assertFalse(directoryRoles.equals(directoryRoles3));

		directoryRoles.addMember(namePrincipal);
		directoryRoles.addMember(emailPrincipal);
		directoryRoles.addMember(otherPrincipal);
		directoryRoles.addMember(userPrincipal);
		
		DirectoryRoles directoryRoles4 = new DirectoryRoles("Roles");
		directoryRoles4.addMember(namePrincipal);
		directoryRoles4.addMember(emailPrincipal);
		directoryRoles4.addMember(otherPrincipal);
		
		assertFalse(directoryRoles.equals(directoryRoles4));		
		DirectoryRoles directoryRoles5 = new DirectoryRoles("Roles");
		directoryRoles5.addMember(namePrincipal);
		directoryRoles5.addMember(emailPrincipal);
		directoryRoles5.addMember(otherPrincipal2);
		directoryRoles5.addMember(userPrincipal);
		assertFalse(directoryRoles.equals(directoryRoles5));		

		DirectoryRoles directoryRoles6 = new DirectoryRoles("Roles");
		directoryRoles6.addMember(namePrincipal);
		directoryRoles6.addMember(emailPrincipal);
		directoryRoles6.addMember(otherPrincipal);
		directoryRoles6.addMember(userPrincipal);
		assertThat(directoryRoles, is(directoryRoles6));
		assertThat(directoryRoles.hashCode(), is(directoryRoles6.hashCode()));
		
		assertTrue(directoryRoles.isMember(userPrincipal));
		assertFalse(directoryRoles.isMember(otherPrincipal2));
		assertTrue(directoryRoles6.removeMember(userPrincipal));
		assertThat(directoryRoles6, is(directoryRoles4));
		assertThat(directoryRoles6.hashCode(), is(directoryRoles4.hashCode()));
		
		assertTrue(directoryRoles4.addMember(userPrincipal));
		assertThat(directoryRoles, is(directoryRoles4));
		assertThat(directoryRoles.hashCode(), is(directoryRoles4.hashCode()));
		assertFalse(directoryRoles4.addMember(userPrincipal));
		assertThat(directoryRoles, is(directoryRoles4));
		assertThat(directoryRoles.hashCode(), is(directoryRoles4.hashCode()));
	}
	
	class NameAttributeOverridePrincipal
		extends DirectoryPrincipal {
		private String name, attribute;
		
		public NameAttributeOverridePrincipal(String name, String attribute) {
			super(null);
			this.attribute = attribute;
			this.name = name;
		}
		
		@Override
		public String getAttribute() {
			return attribute;
		}
		
		@Override
		public String getName() {
			return name;
		}
	}
}
