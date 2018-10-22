package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.novell.ldap.LDAPException;
import com.quakearts.webapp.security.ldap.LDAPConnection;
import com.quakearts.webapp.security.ldap.LDAPObject;
import com.quakearts.webapp.security.ldap.impl.DefaultLDAPConnectionFactory;

public class TestDefaultLDAPConnection {

	private static boolean skipTest;
	
	@BeforeClass
	public static void startup() throws Exception {
		try(Socket socket = new Socket("10.0.3.103", 389)){
		} catch (IOException e) {
			skipTest = true;			
		}
	}

	@Test
	public void testBind() throws Exception {
		if(!skipTest) {
			DefaultLDAPConnectionFactory factory = new DefaultLDAPConnectionFactory();
			LDAPConnection<LDAPException> connection = factory.createConnection(false);
			connection.connect("10.0.3.103", 389);
			connection.bind("uid=admin,ou=system", "P@$$w0rd1".getBytes());
			LDAPObject object = connection.search("dc=quakearts,dc=com", 
					"uid=kwakutwumasi", new String[] {"cn","sn","mail","telephoneNumber"}, true);
			assertThat(object.getDN(), is("cn=Kwaku+sn=Twumasi-Afriyie,ou=ExecutiveManagement,ou=Technology,c=GH,o=QuakeArts.com,dc=quakearts,dc=com"));
			List<String[]> attributes = object.getAttributeEntries();
			assertThat(attributes.get(0)[0], is("cn"));
			assertThat(attributes.get(0)[1], is("Kwaku"));
			assertThat(attributes.get(1)[0], is("sn"));
			assertThat(attributes.get(1)[1], is("Twumasi-Afriyie"));
			assertThat(attributes.get(2)[0], is("mail"));
			assertThat(attributes.get(2)[1], is("kwaku@quakearts.com"));
			assertThat(attributes.get(3)[0], is("telephoneNumber"));
			assertThat(attributes.get(3)[1], is("233207604491"));
			connection.disconnect();
			connection.bind(object.getDN(), "password1".getBytes());
			connection.disconnect();
		} else {
			System.out.println("Test has been skipped");
		}
	}

	@Test
	public void testCompare() throws Exception {
		if(!skipTest) {
			DefaultLDAPConnectionFactory factory = new DefaultLDAPConnectionFactory();
			LDAPConnection<LDAPException> connection = factory.createConnection(false);
			connection.connect("10.0.3.103", 389);
			connection.bind("uid=admin,ou=system", "P@$$w0rd1".getBytes());
			LDAPObject object = connection.search("dc=quakearts,dc=com", 
					"uid=kofibabone", new String[] {"cn","sn","mail","telephoneNumber"}, true);
			assertThat(object.getDN(), is("cn=Kofi+sn=Bababone,ou=Developers,ou=Technology,c=GH,o=QuakeArts.com,dc=quakearts,dc=com"));
			List<String[]> attributes = object.getAttributeEntries();
			assertThat(attributes.get(0)[0], is("cn"));
			assertThat(attributes.get(0)[1], is("Kofi"));
			assertThat(attributes.get(1)[0], is("sn"));
			assertThat(attributes.get(1)[1], is("Bababone"));
			assertThat(attributes.get(2)[0], is("mail"));
			assertThat(attributes.get(2)[1], is("kofi@quakearts.com"));
			assertThat(attributes.get(3)[0], is("telephoneNumber"));
			assertThat(attributes.get(3)[1], is("233207604493"));
			assertThat(connection.compare(object.getDN(), 
					new String[] {"userPKCS12","0b14d501a594442a01c6859541bcb3e8164d183d32937b851835442f69d5c94e"}),
					is(true));
			connection.disconnect();
		} else {
			System.out.println("Test has been skipped");
		}
	}

}
