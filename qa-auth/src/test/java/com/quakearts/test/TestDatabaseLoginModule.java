package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import static com.quakearts.webapp.security.auth.DatabaseLoginModule.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.auth.DatabaseLoginModule;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.util.AttemptChecker;
import com.quakearts.webapp.security.util.HashPassword;

public class TestDatabaseLoginModule extends DatabaseModuleTestBase {
	private static final String FALSE = "false";
	private static final String TRUE = "true";
	private final AtomicBoolean authMode = new AtomicBoolean(true);
	private final AtomicBoolean usernameMode = new AtomicBoolean(true);
	private String authQuery;
	private String rolesQuery;
	private final StringValidator queryValidator = query->{
		if(authMode.get()) {
			assertThat(query, is(authQuery));
		} else {
			assertThat(query, is(rolesQuery));
		}
	};
	private String username;
	private String passwordCompare;
	private final StringValidator setStringValidator = value->{
		if(authMode.get()) {
			if(usernameMode.get()) {
				assertThat(value, is(username));
				usernameMode.set(false);
			} else {
				assertThat(value, is(passwordCompare));
				authMode.set(false);
			}
		} else {
			assertThat(value, is(username));
		}
	};
	private Iterator<String> rolesColumnsIterator;
	private Iterator<String> rolesValuesIterator;
	private StringSupplier getString = name->{
		assertThat(rolesColumnsIterator.next(), is(name));
		return rolesValuesIterator.next();
	};
	
	@Test
	public void testTrack1_1() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		authQuery = "SELECT * FROM USERS_1_1 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_1 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		String password = "test1";
		passwordCompare = new HashPassword(password, "SHA-256", 50, 
				HashPassword.DEFAULT_SALT+username).toString();
		
		generateDataSource("java:/TDBLMT11", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT11")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
		
		assertThat(subject.getPrincipals().size(), is(5));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			assertThat(rolesValues.contains(principal.getName()), is(true));
		}		
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
				
		AttemptChecker.createChecker("java:/TDBLMT11",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBLMT11");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}

	private final AtomicBoolean authResultMode = new AtomicBoolean(true);
	
	@Test
	public void testTrack1_2() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Collections.emptyList();
		authQuery = "SELECT * FROM USERS_1_2 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_2 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kofi";
		String password = "test2";
		passwordCompare = new HashPassword(password, "MD5", 0, 
				"123456").toString();
		
		generateDataSource("java:/TDBLMT12", queryValidator, setStringValidator, 
				()->{
					if(authResultMode.get()) {
						authResultMode.set(false);
						return true;
					} else
						return rolesValuesIterator.hasNext();
				}, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "MD5")
				.add(USERNAME_AS_SALT, FALSE)
				.add(SALT_VALUE, "123456")
				.add(DATABASE_ROLENAME,"TestRoles")
				.add(USE_FIRST_PASS, FALSE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT12")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(MAX_TRY_ATTEMPTS, "6")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwaku"))
				.add("javax.security.auth.login.password", "kwaku".toCharArray())
				.thenBuild();
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
		
		assertThat(subject.getPrincipals().size(), is(1));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		AttemptChecker.createChecker("java:/TDBLMT12",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBLMT12");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(6));
	}
	
	@Test
	public void testTrack1_3() throws Exception {
		authMode.set(false);
		usernameMode.set(true);
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("User","Admin","GodMode");
		authQuery = "SELECT * FROM USERS_1_3 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_3 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwame";
		passwordCompare = "test3";
		
		generateDataSource("java:/TDBLMT13", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, FALSE)
				.add(HASH_ALGORITHM, "MD5")
				.add(USERNAME_AS_SALT, FALSE)
				.add(SALT_VALUE, "123456")
				.add(USE_FIRST_PASS, TRUE)
				.add(LOAD_PROFILE_ONLY, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT13")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.add(DATABASE_DEFAULTROLES, "AppUser")
				.add(MAX_TRY_ATTEMPTS, "5")
				.add(LOCKOUT_TIME, "6000")
				.thenBuild();
		
		Map<String, ?> sharedState = null;
		Subject subject = new Subject();
		subject.getPrincipals().add(new OtherPrincipal("ExistingRole"));
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(passwordCompare.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(6));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			if(principal.getName().startsWith("Existing")||
					principal.getName().equals("AppUser")) {
				continue;
			}
			assertThat(rolesValues.contains(principal.getName()), is(true));
		}		
		
		AttemptChecker.createChecker("java:/TDBLMT13",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBLMT13");
		assertThat(attemptChecker.getLockoutTime(), is(6000));
		assertThat(attemptChecker.getMaxAttempts(), is(5));
	}

	@Test
	public void testTrack1_4() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("User","Admin","GodMode");
		authQuery = "SELECT * FROM USERS_1_4 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_4 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwabena";
		passwordCompare = "test4";
		
		generateDataSource("java:/TDBLMT14", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		Map<String, ?> options = newMap()
				.add(USE_FIRST_PASS, TRUE)
				.add(LOAD_PROFILE_ONLY, FALSE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT14")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.add(DATABASE_DEFAULTROLES, "AppUser;DevUser")
				.add(MAX_TRY_ATTEMPTS, "Invalid")
				.add(LOCKOUT_TIME, "Invalid")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("kwaku"))				
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(passwordCompare.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(3));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		
		for(OtherPrincipal otherPrincipal:subject.getPrincipals(OtherPrincipal.class)) {
			assertTrue(otherPrincipal.getName().equals("AppUser") || otherPrincipal.getName().equals("DevUser"));
		}
				
		AttemptChecker.createChecker("java:/TDBLMT14",0,0);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBLMT14");
		assertThat(attemptChecker.getLockoutTime(), is(3600000));
		assertThat(attemptChecker.getMaxAttempts(), is(4));
	}

	@Test
	public void testTrack1_5() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Arrays.asList("User","Admin","GodMode");
		authQuery = "SELECT * FROM USERS_1_5 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_5 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("rolename","rolename","rolename").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwesi";
		passwordCompare = "test5";
		
		generateDataSource("java:/TDBLMT15", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, FALSE)
				.add(USE_FIRST_PASS, TRUE)
				.add(LOAD_PROFILE_ONLY, FALSE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT15")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.add(DATABASE_DEFAULTROLES, "AppUser")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.password", passwordCompare.toCharArray())				
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName(username);
				} else {
					((PasswordCallback)callback).setPassword(passwordCompare.toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(5));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			if(principal.getName().equals("AppUser"))
				continue;
			
			assertThat(rolesValues.contains(principal.getName()), is(true));
		}
	}

	@Test
	public void testTrack1_6() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		authResultMode.set(true);
		final String rolesColumns = "rolename";
		final List<String> rolesValues = Collections.emptyList();
		authQuery = "SELECT * FROM USERS_1_6 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_6 WHERE username = ?";
		rolesColumnsIterator = rolesValues.iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "yaw";
		String password = "test6";
		passwordCompare = new HashPassword(password, "SHA-256", 0, "ABCDEFGHIJ"+username).toString();
		
		generateDataSource("java:/TDBLMT16", queryValidator, setStringValidator, 
				()->{
					if(authResultMode.get()) {
						authResultMode.set(false);
						return true;
					} else
						return rolesValuesIterator.hasNext();
				}, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(USE_FIRST_PASS, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "Invalid")
				.add(SALT_VALUE, "ABCDEFGHIJ")
				.add(LOAD_PROFILE_ONLY, FALSE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT16")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.add(RESULT_ORIENTATION_POTRAIT, TRUE)
				.add(DATABASE_DEFAULTROLES, "AppUser")
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("javax.security.auth.login.password", password.toCharArray())				
				.thenBuild();
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(2));	
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
		assertThat(subject.getPrincipals(OtherPrincipal.class).iterator().next().getName(), is("AppUser"));			
	}

	@Test
	public void testTrack1_7() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		authQuery = "SELECT * FROM USERS_1_7 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_1_7 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwadwo";
		String password = "test7";
		passwordCompare = new HashPassword(password, "SHA-256", 50, 
				HashPassword.DEFAULT_SALT+username).toString();
		
		generateDataSource("java:/TDBLMT17", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, arguments->null);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT17")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
		
		assertThat(subject.getPrincipals().size(), is(5));
		for(OtherPrincipal principal: subject.getPrincipals(OtherPrincipal.class)) {
			assertThat(principal.getName(), is(""));
		}		
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is(username));
	}

	@Test
	public void testTrack2() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		authQuery = "SELECT * FROM USERS_2 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_2 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwadwo";
		String password = "test7";
		passwordCompare = new HashPassword(password, "SHA-256", 50, 
				HashPassword.DEFAULT_SALT+username).toString();
		
		generateDataSource("java:/TDBLMT2",DEFAULTVALIDATOR,
				()->{ throw new SQLException("Throwing");}, 
				 queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, arguments->null);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT2")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}

	@Test
	public void testTrack3() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
				
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT3")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("willnotrun");
				} else {
					((PasswordCallback)callback).setPassword("willnotrun".toCharArray());
				}
			}
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}

	@Test
	public void testTrack4() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT4")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
	public void testTrack5() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT5")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{
			throw new IOException("Throwing");
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testTrack6() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Username/Password is null.");
		
		final String rolesColumns = "name,staffid,department,title";
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT6")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		runModule();
	}

	@Test
	public void testTrack7() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Username/Password is null.");
		
		final String rolesColumns = "name,staffid,department,title";
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT7")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("willnotrun");
				}
			}
		}, sharedState, options);		
		runModule();
	}

	@Test
	public void testTrack8() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Account is locked out.");
		
		AttemptChecker.createChecker("java:/TDBLMT8", 1, 3600000);
		AttemptChecker attemptChecker = AttemptChecker.getChecker("java:/TDBLMT8");
		
		attemptChecker.incrementAttempts("lockeduser");
		attemptChecker.incrementAttempts("lockeduser");
		
		final String rolesColumns = "name,staffid,department,title";
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT8")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = new HashMap<>();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{
			for(Callback callback: callbacks) {
				if(callback instanceof NameCallback) {
					((NameCallback)callback).setName("lockeduser");
				} else {
					((PasswordCallback)callback).setPassword("wrongpassword".toCharArray());
				}
			}
		}, sharedState, options);
		
		runModule();
	}

	@Test
	public void testTrack9() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Invalid Password.");
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		authQuery = "SELECT * FROM USERS_9 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_9 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		String password = "test1";
		passwordCompare = new HashPassword(password, "SHA-256", 50, 
				HashPassword.DEFAULT_SALT+username).toString();
		
		generateDataSource("java:/TDBLMT9", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA-256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT9")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
	}


	@Test
	public void testTrack10() throws Exception {
		authMode.set(true);
		usernameMode.set(true);
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		authQuery = "SELECT * FROM USERS_9 WHERE username=? AND password=?";
		rolesQuery = "SELECT * FROM ROLES_9 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		String password = "test1";
		passwordCompare = new HashPassword(password, "SHA-256", 50, 
				HashPassword.DEFAULT_SALT+username).toString();
		
		generateDataSource("java:/TDBLMT10", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(DATABASE_USEHASH, TRUE)
				.add(HASH_ALGORITHM, "SHA256")
				.add(USERNAME_AS_SALT, TRUE)
				.add(HASH_ITERATIONS, "50")
				.add(USE_FIRST_PASS, TRUE)
				.add(DATABASE_JNDINAME, "java:/TDBLMT10")
				.add(DATABASE_AUTHENTICATIONQUERY, authQuery)
				.add(DATABASE_ROLESQUERY, rolesQuery)
				.add(DATABASE_ROLESCOLUMNS, rolesColumns)
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
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	private DatabaseLoginModule module;

	@Override
	protected LoginModule getModule() {
		if(module == null)
			module = new DatabaseLoginModule();

		return module;
	}

}