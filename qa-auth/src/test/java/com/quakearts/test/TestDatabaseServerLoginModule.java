package com.quakearts.test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static com.quakearts.webapp.security.auth.DatabaseServerLoginModule.*;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;

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

import com.quakearts.webapp.security.auth.DatabaseServerLoginModule;
import com.quakearts.webapp.security.auth.DirectoryRoles;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.util.AttemptChecker;

public class TestDatabaseServerLoginModule extends DatabaseModuleTestBase {
	private static final BooleanSupplier RETURNNONE = ()->false;
	private static final String FALSE = "false";
	private static final String TRUE = "true";
	private String rolesQuery;
	private final StringValidator queryValidator = query->{
		assertThat(query, is(rolesQuery));
	};
	private String username;
	private String password;
	private final ArgumentValidator getConnectionValidator = arguments->{
		String setUsername = arguments.get(0);
		String setPassword = arguments.get(1);
		assertThat(username, is(setUsername));
		assertThat(password, is(setPassword));
	};
	
	private final StringValidator setStringValidator = value->{
		assertThat(value, is(username));
	};
	private Iterator<String> rolesColumnsIterator;
	private Iterator<String> rolesValuesIterator;
	private StringSupplier getString = name->{
		assertThat(rolesColumnsIterator.next(), is(name));
		return rolesValuesIterator.next();
	};

	@Test
	public void testTrack1_1() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		rolesQuery = "SELECT * FROM ROLES_1_1 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		password = "test1";
		
		generateDataSource("java:/TDBSLMT11", getConnectionValidator,queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT11")
				.add(DATABASE_DEFAULTROLES, "AppUser")
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(AUTHENTICATE_ONLY, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(3));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		assertThat(subject.getPrincipals(OtherPrincipal.class).iterator().next().getName(), is("AppUser"));			
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));

		Enumeration<? extends Principal> principals = roles.members();
		int count = 0;
		while (principals.hasMoreElements()) {
			++count;
			Principal principal = principals.nextElement();
			if(principal instanceof UserPrincipal) {
				assertThat(principal.getName(), is(username));		
			} else {
				assertThat(principal.getName(), is("AppUser"));		
			}
		}
		assertThat(count, is(2));
		
		AttemptChecker.createChecker("java:/TDBSLMT11",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBSLMT11");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}

	@Test
	public void testTrack1_2() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		rolesQuery = "SELECT * FROM ROLES_1_2 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwame";
		password = "test2";
		
		generateDataSource("java:/TDBSLMT12", getConnectionValidator,queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT12")
				.add(AUTHENTICATE_ONLY, FALSE)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(MAX_TRY_ATTEMPTS, "8")
				.add(DATABASE_CASE_SENSITIVITY, "none")
				.add(DATABASE_ROLENAME, "TestRole")
				.thenBuild();
		
		Map<String, ?> sharedState = null;
		Subject subject = new Subject();
		DirectoryRoles existingRoles = new DirectoryRoles("TestRole");
		existingRoles.addMember(new OtherPrincipal("Existing"));
		subject.getPrincipals().add(existingRoles);
		DirectoryRoles notMatchingGroup = new DirectoryRoles("ExistingGroup");
		notMatchingGroup.addMember(new OtherPrincipal("AnotherExisting"));
		subject.getPrincipals().add(notMatchingGroup);
		subject.getPrincipals().add(new OtherPrincipal("ExistingRole"));;
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(password.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		assertThat(subject.getPrincipals(OtherPrincipal.class).size(), is(2));	
		for(OtherPrincipal principal:subject.getPrincipals(OtherPrincipal.class)) {
			assertTrue(principal.getName().startsWith("Existing"));		
		}
		
		for(DirectoryRoles roles:subject.getPrincipals(DirectoryRoles.class)) {
			if(roles.getName().equals("TestRole")) {
				assertThat(roles, is(existingRoles));	
				int count = 0;
				
				Enumeration<? extends Principal> principals = roles.members();
				while (principals.hasMoreElements()) {
					++count;
					Principal principal = principals.nextElement();
					if(principal instanceof UserPrincipal) {
						assertThat(principal.getName(), is(username));		
					} else {
						assertThat(principal.getName(), is("Existing"));		
					}
				}
				assertThat(count, is(2));
			}
		}
		
		AttemptChecker.createChecker("java:/TDBSLMT12",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBSLMT12");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(8));
	}
	
	@Test
	public void testTrack1_3() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		rolesQuery = "SELECT * FROM ROLES_1_3 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "KWESI";
		password = "TEST3";
		
		generateDataSource("java:/TDBSLMT13", getConnectionValidator,queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT13")
				.add(DATABASE_DEFAULTROLES, "AppUser;DevUser")
				.add(AUTHENTICATE_ONLY, FALSE)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_CASE_SENSITIVITY, "upper")
				.add(MAX_TRY_ATTEMPTS, "7")
				.add(LOCKOUT_TIME, "80000")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwaku"))
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username.toLowerCase());
				} else {
					((PasswordCallback)callback).setPassword(password.toLowerCase()
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(8));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			if(principal.getName().equals("AppUser")
					|| principal.getName().equals("DevUser"))
				continue;
			
			assertThat(rolesValues.contains(principal.getName()), is(true));
		}		
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));
		Enumeration<? extends Principal> principals = roles.members();
		int count = 0;
		
		while (principals.hasMoreElements()) {
			++count;
			Principal principal = principals.nextElement();
			if(principal instanceof UserPrincipal) {
				assertThat(principal.getName(), is(username));	
			} else if(principal.getName().equals("AppUser")
					|| principal.getName().equals("DevUser")) {
			} else {
				assertThat(rolesValues.contains(principal.getName()), is(true));
			}
		}
		assertThat(count, is(7));	
		
		AttemptChecker.createChecker("java:/TDBSLMT13",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBSLMT13");
		assertThat(attemptChecker.getLockoutTime(), is(80000));
		assertThat(attemptChecker.getMaxAttempts(), is(7));
	}

	@Test
	public void testTrack1_4() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		rolesQuery = "SELECT * FROM ROLES_1_4 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kofi";
		password = "test4";
		
		generateDataSource("java:/TDBSLMT14", getConnectionValidator,queryValidator, setStringValidator, 
				RETURNNONE, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, FALSE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT14")
				.add(DATABASE_DEFAULTROLES, "DevUser")
				.add(AUTHENTICATE_ONLY, FALSE)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_CASE_SENSITIVITY, "lower")
				.add(MAX_TRY_ATTEMPTS, "Invalid")
				.add(RESULT_ORIENTATION_POTRAIT, FALSE)
				.add(LOCKOUT_TIME, "Invalid")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwaku"))
				.add("javax.security.auth.login.password", "kwaku")
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username.toUpperCase());
				} else {
					((PasswordCallback)callback).setPassword(password.toUpperCase()
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(3));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		assertThat(subject.getPrincipals(OtherPrincipal.class).iterator().next().getName(), is("DevUser"));			
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));

		Enumeration<? extends Principal> principals = roles.members();
		int count = 0;
		while (principals.hasMoreElements()) {
			++count;
			Principal principal = principals.nextElement();
			if(principal instanceof UserPrincipal) {
				assertThat(principal.getName(), is(username));		
			} else {
				assertThat(principal.getName(), is("DevUser"));		
			}
		}
		assertThat(count, is(2));
		
		AttemptChecker.createChecker("java:/TDBSLMT14",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBSLMT14");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}
	
	@Test
	public void testTrack1_5() throws Exception {
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("Admin","GodMode","SecuredAssets");
		rolesQuery = "SELECT * FROM ROLES_1_5 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "yaw";
		password = "test5";
		
		generateDataSource("java:/TDBSLMT15", getConnectionValidator,queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT15")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_CASE_SENSITIVITY, "different")
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("javax.security.auth.login.password", password.toCharArray())
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("kwaku");
				} else {
					((PasswordCallback)callback).setPassword("kwaku"
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			assertThat(rolesValues.contains(principal.getName()), is(true));
		}	
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));

		Enumeration<? extends Principal> principals = roles.members();
		int count = 0;
		
		while (principals.hasMoreElements()) {
			++count;
			Principal principal = principals.nextElement();
			if(principal instanceof UserPrincipal) {
				assertThat(principal.getName(), is(username));	
			} else {
				assertThat(rolesValues.contains(principal.getName()), is(true));
			}
		}
		assertThat(count, is(4));	
	}
	
	@Test
	public void testTrack1_6() throws Exception {
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("Admin","GodMode","SecuredAssets");
		rolesQuery = "SELECT * FROM ROLES_1_6 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwabena";
		password = "test6";
		
		generateDataSource("java:/TDBSLMT16", getConnectionValidator, queryValidator, setStringValidator, 
				RETURNNONE, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT16")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(password
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(2));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		assertThat(subject.getPrincipals(OtherPrincipal.class).size(), is(0));			
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));
		assertThat(roles.members().nextElement().getName(), is(username));
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testTrack2() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Login/Password is null");
		final String rolesColumns = "rolename";
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT2")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			
		}, sharedState, options);
		runModule();		
	}
	
	@Test
	public void testTrack3() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Login/Password is null");
		final String rolesColumns = "rolename";
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT3")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("kwaku");
				} 
			}
		}, sharedState, options);
		runModule();		
	}

	@Test
	public void testTrack4() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Account is locked out.");
		final String rolesColumns = "rolename";
		
		AttemptChecker.createChecker("java:/TDBSLMT4",0,3000);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBSLMT4");
		attemptChecker.incrementAttempts("kwaku");
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT4")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("kwaku");
				} else {
					((PasswordCallback)callback).setPassword("password"
							.toCharArray());
				}
			}
		}, sharedState, options);
		runModule();		
	}
	
	@Test
	public void testTrack5() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Username/password is invalid");
		final String rolesColumns = "rolename";
		
		generateDataSource("java:/TDBSLMT5", args->{throw new SQLException("Cannot connect");},queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT5")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("kwaku");
				} else {
					((PasswordCallback)callback).setPassword("password"
							.toCharArray());
				}
			}
		}, sharedState, options);
		runModule();		
	}
	
	@Test
	public void testTrack6() throws Exception {
		final String rolesColumns = "rolename";
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT6")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("kwaku");
				} else {
					((PasswordCallback)callback).setPassword("password"
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack7() throws Exception {
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("Admin","GodMode","SecuredAssets");
		rolesQuery = "SELECT * FROM ROLES_7 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwabena";
		password = "test6";
		
		generateDataSource("java:/TDBSLMT7", getConnectionValidator, queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, (string)->{throw new SQLException("Invalid Query");});
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT7")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(password
							.toCharArray());
				}
			}
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack8() throws Exception {
		final String rolesColumns = "rolename";
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT6")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				throw new UnsupportedCallbackException(callback);
			}
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack9() throws Exception {
		final String rolesColumns = "rolename";
		
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBSLMT6")
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			throw new IOException();
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	private DatabaseServerLoginModule loginModule;
	
	@Override
	protected LoginModule getModule() {
		if(loginModule == null)
			loginModule = new DatabaseServerLoginModule();
		
		return loginModule;
	}

}
