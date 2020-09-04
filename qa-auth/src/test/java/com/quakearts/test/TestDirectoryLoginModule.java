package com.quakearts.test;

import static com.quakearts.webapp.security.auth.DirectoryLoginModule.*;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.test.TestLDAPConnectionFactory.Mode;
import com.quakearts.webapp.security.auth.DirectoryLoginModule;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.ldap.LDAPObject;
import com.quakearts.webapp.security.util.HashPassword;

public class TestDirectoryLoginModule extends LoginModuleTest {
	private static final String FALSE = "false";
	private static final String TRUE = "true";

	/*
				.add(LDAP_SERVERPARAMETER, "")
				.add(LDAP_PORTPARAMETER, "")//ldapPortString
				.add(LDAP_KEYSTOREPARAMETER, "")//keyStorePath
				.add(LDAP_SSL_USEPARAMETER, "")//usessl
				.add(LDAP_SEARCH_DNPARAMETER, "")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "")//searchpass
				.add(LDAP_CREDENTIAL_PARAMETER, "")//passwordParam
				.add(LDAP_FILTERPARAMETER, "")//filterParam
				.add(LDAP_ALLOW_ANONYMOUSBINDPARAMETER, "")//allowEmptyPassword
				.add(LDAP_USEHASH, "")//usehash
				.add(HASH_ALGORITHM, "")//iterationsValue
				.add(SALT_VALUE, "")
				.add(USERNAME_AS_SALT, "")
				.add(HASH_ITERATIONS, "")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "")//searchbasedn
				.add(MAX_TRY_ATTEMPTSPARAMETER, "")//maxAttemptsString
				.add(LOCKOUT_TIMEPARAMETER, "")//lockoutTimeString
				.add(DIRECTORY_ATTRIBUTESPARAMETER,"")//attributesString
				.add(DIRECTORY_ROLENAMEPARAMETER, "")//rolesgrpname
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "")//defaultrolesString
				.add(USE_FIRST_PASSPARAMETER, "")//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, "")//usecompare
	 */
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testTrack1_1() throws Exception {
		String username="kwaku";
		String password="test1";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_KEYSTOREPARAMETER, "/test/keystore")//keyStorePath
				.add(LDAP_SSL_USEPARAMETER, TRUE)//usessl
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom1,dc=test1")//searchbasedn
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom1,dc=test1,ou=testdn1,uid=administrator")//searchuser
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(LDAP_ALLOW_ANONYMOUSBINDPARAMETER, FALSE)//allowEmptyPassword
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, FALSE)//usecompare
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory().setAttrsAs(new String[0])
			.setBaseAs("dc=testcom1,dc=test1")
			.setDNAs("dc=testcom1,dc=test1,ou=testdn1,uid="+username)
			.setPasswdAs(password)
			.setSearchDNAs("dc=testcom1,dc=test1,ou=testdn1,uid=administrator")
			.setSearchDNPasswordAs("")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setModeAs(Mode.LOAD)
			.setPortAs(636)
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom1,dc=test1,ou=testdn1,uid="+username));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
		
		assertThat(System.getProperties().getProperty("javax.net.ssl.trustStore"), is("/test/keystore"));
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(1));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("localhost",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost");
		assertThat(attemptChecker.getLockoutTime(), is(3_600_000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	
		getFactory().setThrowErrorOnDisconnectAs(true)
			.setModeAs(Mode.LOAD);
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(new char[0]);
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(1));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
				
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Password is empty.");
		
		getFactory().setPasswdAs(""); 
		sharedState = new HashMap<>();
		subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(new char[0]);
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack1_2() throws Exception {
		String username="kwame";
		String password="";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SSL_USEPARAMETER, FALSE)
				.add(LDAP_SERVERPARAMETER, "localhost.2")
				.add(LDAP_SSL_USEPARAMETER, FALSE)//usessl
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom2,dc=test2")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(LDAP_ALLOW_ANONYMOUSBINDPARAMETER, TRUE)//allowEmptyPassword
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email;dept;computer")
				.add(MAX_TRY_ATTEMPTSPARAMETER, "6")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser")
				.thenBuild();
		
		Map<String, ?> sharedState = null;
		
		getFactory()
			.setBaseAs("dc=testcom2,dc=test2")
			.setDNAs("dc=testcom2,dc=test2,ou=testdn2,uid="+username)
			.setPasswdAs(password)
			.setSearchDNAs("")
			.setSearchDNPasswordAs("")
			.setFilterAs("username="+username)
			.setHostAs("localhost.2")
			.setModeAs(Mode.LOAD)
			.setPortAs(389)
			.setAttrsAs("fname;lname;email;dept;computer".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom2,dc=test2,ou=testdn2,uid="+username)
					.addAttributeEntry("fname", "Kwame")
					.addAttributeEntry("lname", "Nyame")
					.addAttributeEntry("email", "kwame@server.com")
					.addAttributeEntry("dept", "IT")
					.addAttributeEntry("computer", "N123495"));
		
		Subject subject = new Subject();
		subject.getPrincipals().add(new OtherPrincipal("ExistingRole"));
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(7));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("localhost.2",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost.2");
		assertThat(attemptChecker.getLockoutTime(), is(3_600_000));
		assertThat(attemptChecker.getMaxAttempts(), is(6));
	}

	@Test
	public void testTrack1_3() throws Exception {
		String username="kofi";
		String password="test3";
		
		System.getProperties().setProperty("javax.net.ssl.trustStore","test3/keystore3");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost.3")
				.add(LDAP_SSL_USEPARAMETER, TRUE)//usessl
				.add(LDAP_KEYSTOREPARAMETER, "test2/keystore2")
				.add(LDAP_PORTPARAMETER, "123")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom3,dc=test3,ou=testdn3,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password3")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom3,dc=test3")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(LDAP_ALLOW_ANONYMOUSBINDPARAMETER, TRUE)//allowEmptyPassword
				.add(USE_FIRST_PASSPARAMETER, FALSE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(HASH_ALGORITHM, "SHA-256")
				.add(LDAP_USEHASH, TRUE)
				.add(HASH_ITERATIONS, "20")
				.add(USERNAME_AS_SALT, TRUE)
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(LOCKOUT_TIMEPARAMETER, "6000")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwame"))
				.add("javax.security.auth.login.password", "kwame".toCharArray())
				.thenBuild();
		
		getFactory()
			.setThrowErrorOnDisconnectAs(true)
			.setBaseAs("dc=testcom3,dc=test3")
			.setDNAs("dc=testcom3,dc=test3,ou=testdn3,uid="+username)
			.setSearchDNAs("dc=testcom3,dc=test3,ou=testdn3,uid=administrator")
			.setSearchDNPasswordAs("password3")
			.setFilterAs("username="+username)
			.setHostAs("localhost.3")
			.setPasswdAs(new HashPassword(password, "SHA-256", 20, HashPassword.DEFAULT_SALT+username).toString())
			.setModeAs(Mode.LOAD)
			.setPortAs(123)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom3,dc=test3,ou=testdn3,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
		
		assertThat(System.getProperties().getProperty("javax.net.ssl.trustStore"), is("test3/keystore3"));
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("localhost.3",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost.3");
		assertThat(attemptChecker.getLockoutTime(), is(6_000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}
	
	@Test
	public void testTrack1_4() throws Exception {
		String username="kwabena";
		String password="test4";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost.4")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom4,dc=test4,ou=testdn4,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password4")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom4,dc=test4")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(HASH_ALGORITHM, "SHA-512")
				.add(LDAP_USEHASH, TRUE)
				.add(SALT_VALUE, "987654321")
				.add(USERNAME_AS_SALT, FALSE)
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(LOCKOUT_TIMEPARAMETER, "8000")
				.add(MAX_TRY_ATTEMPTSPARAMETER, "5")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwame"))
				.thenBuild();
		
		getFactory()
			.setBaseAs("dc=testcom4,dc=test4")
			.setDNAs("dc=testcom4,dc=test4,ou=testdn4,uid="+username)
			.setSearchDNAs("dc=testcom4,dc=test4,ou=testdn4,uid=administrator")
			.setSearchDNPasswordAs("password4")
			.setFilterAs("username="+username)
			.setHostAs("localhost.4")
			.setPasswdAs(new HashPassword(password, "SHA-512", 0, "987654321").toString())
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom4,dc=test4,ou=testdn4,uid="+username)
					.addAttributeEntry("fname", "Kwabena")
					.addAttributeEntry("lname", "Kwabena")
					.addAttributeEntry("email", "kwabena@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("localhost.4",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost.4");
		assertThat(attemptChecker.getLockoutTime(), is(8_000));
		assertThat(attemptChecker.getMaxAttempts(), is(5));
	}
	
	@Test
	public void testTrack1_5() throws Exception {
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost.5")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(HASH_ALGORITHM, "MD5")
				.add(LDAP_USEHASH, TRUE)
				.add(SALT_VALUE, "ABCDEFGH")
				.add(USERNAME_AS_SALT, FALSE)
				.add(HASH_ITERATIONS, "Invalid")
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(LOCKOUT_TIMEPARAMETER, "Invalid")
				.add(MAX_TRY_ATTEMPTSPARAMETER, "Invalid")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("javax.security.auth.login.password", password.toCharArray())
				.thenBuild();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost.5")
			.setPasswdAs(new HashPassword(password, "MD5", 0, "ABCDEFGH").toString())
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("notused");
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword("notused".toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("localhost.5",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost.5");
		assertThat(attemptChecker.getLockoutTime(), is(3_600_000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}
	
	@Test
	public void testTrack2() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Login/Password is null.");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(HASH_ALGORITHM, "MD5")
				.add(LDAP_USEHASH, TRUE)
				.add(SALT_VALUE, "ABCDEFGH")
				.add(USERNAME_AS_SALT, FALSE)
				.add(HASH_ITERATIONS, "Invalid")
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(new HashPassword(password, "MD5", 0, "ABCDEFGH").toString())
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{}, sharedState, options);
				
		runModule();
	}
	
	@Test
	public void testTrack3() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Login/Password is null.");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, FALSE)//usecompare
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				}
			}
		}, sharedState, options);
				
		runModule();
	}
	
	@Test
	public void testTrack4() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Invalid password");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)//usecompare
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setThrowErrorOnDisconnectAs(true)
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword("wrong".toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
	}
	
	@Test
	public void testTrack5() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Invalid password");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword("wrong".toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
	}
	
	@Test
	public void testTrack6() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Invalid password");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(LDAP_COMPARE_USEPARAMETER, TRUE)
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setThrowErrorOnCompareAs(true)
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword("wrong".toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
	}
	
	@Test
	public void testTrack7() throws Exception {
		String username="administrator";
		String password="password5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setThrowErrorOnDisconnectAs(true)
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.AUTHENTICATE)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
						
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
	}
	
	@Test
	public void testTrack8() throws Exception {
		String username="administrator";
		String password="password5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.AUTHENTICATE)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					throw new UnsupportedCallbackException(callback);
				}
			}
		}, sharedState, options);
						
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
	}
	
	@Test
	public void testTrack9() throws Exception {
		String username="administrator";
		String password="password5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost")
			.setPasswdAs(password)
			.setModeAs(Mode.AUTHENTICATE)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			throw new IOException();
		}, sharedState, options);
						
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
	}
	
	@Test
	public void testTrack10() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Account has been locked out.");
		String username="yaw";
		String password="test5";
		
		System.getProperties().remove("javax.net.ssl.trustStore");
		Map<String, ?> options = newMap()
				.add(LDAP_SERVERPARAMETER, "localhost.10")
				.add(LDAP_PORTPARAMETER, "248")
				.add(LDAP_SEARCH_DNPARAMETER, "dc=testcom5,dc=test5,ou=testdn5,uid=administrator")//searchuser
				.add(LDAP_SEARCH_ACCPARAMETER, "password5")
				.add(LDAP_SEARCH_BASE_DNPARAMETER, "dc=testcom5,dc=test5")//searchbasedn
				.add(LDAP_FILTERPARAMETER, "username")//filterParam
				.add(USE_FIRST_PASSPARAMETER, TRUE)//useFirstPass
				.add(DIRECTORY_ATTRIBUTESPARAMETER, "fname;lname;email")
				.add(DIRECTORY_DEFAULTROLESPARAMETER, "AppUser;DevUser")
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		
		getFactory()
			.setThrowErrorOnCompareAs(true)
			.setBaseAs("dc=testcom5,dc=test5")
			.setDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
			.setSearchDNAs("dc=testcom5,dc=test5,ou=testdn5,uid=administrator")
			.setSearchDNPasswordAs("password5")
			.setFilterAs("username="+username)
			.setHostAs("localhost.10")
			.setPasswdAs(password)
			.setModeAs(Mode.LOAD)
			.setPortAs(248)
			.setAttrsAs("fname;lname;email".split(";"))
			.setResultsAs(new LDAPObject()
					.withDNAs("dc=testcom5,dc=test5,ou=testdn5,uid="+username)
					.addAttributeEntry("fname", "Kofi")
					.addAttributeEntry("lname", "Babone")
					.addAttributeEntry("email", "kofi@server.com"));
		
		Subject subject = new Subject();

		AttemptChecker.createChecker("localhost.10",0,8000);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("localhost.10");
		
		attemptChecker.incrementAttempts("yaw");
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else if(callback instanceof PasswordCallback) {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
				
		runModule();
	}

	private TestLDAPConnectionFactory factory;
	
	public TestLDAPConnectionFactory getFactory() {
		if(factory == null)
			factory = new TestLDAPConnectionFactory();
			
		return factory;
	}
	
	private DirectoryLoginModule loginModule;
	
	@Override
	protected LoginModule getModule() {
		if(loginModule==null) {
			loginModule = new DirectoryLoginModule()
					.setLdapConnectionFactoryAs(getFactory());
		}
		
		return loginModule;
	}
}
