package com.quakearts.test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static com.quakearts.webapp.security.auth.LoadProfileLoginModule.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.spi.LoginModule;

import org.junit.Test;

import com.quakearts.webapp.security.auth.LoadProfileLoginModule;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;

public class TestLoadProfileLoginModule extends DatabaseModuleTestBase {
	private static final String FALSE = "false";
	private static final String TRUE = "true";
	private String rolesQuery;
	private final StringValidator queryValidator = query->{
		assertThat(query, is(rolesQuery));
	};
	private String username;
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
		
		generateDataSource("java:/TLPLMT11", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, FALSE)
				//.add(DATABASE_ROLENAMEPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT11")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(4));
		for(OtherPrincipal otherPrincipal:subject.getPrincipals(OtherPrincipal.class)) {
			assertThat(rolesValues.contains(otherPrincipal.getName()), is(true));
		}		
	}

	@Test
	public void testTrack1_2() throws Exception {
		final String rolesColumns = "name,staffid,department,title";
		final List<String> rolesValues = Arrays.asList("Kwaku","1234","IT","Head");
		rolesQuery = "SELECT * FROM ROLES_1_2 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList(rolesColumns.split(",")).iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kofi";
		
		generateDataSource("java:/TLPLMT12", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, FALSE)
				.add(DATABASE_ROLENAMEPARAMETER, "LoadRoles")
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT12")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(0));
	}

	@Test
	public void testTrack1_3() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_1_3 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwabena";
		
		generateDataSource("java:/TLPLMT13", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT13")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(3));
		for(OtherPrincipal otherPrincipal:subject.getPrincipals(OtherPrincipal.class)) {
			assertThat(rolesValues.contains(otherPrincipal.getName()), is(true));
		}		
	}
	
	@Test
	public void testTrack1_4() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_1_4 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "yaw";
		
		generateDataSource("java:/TLPLMT14", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT14")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(0));
	}
	
	@Test
	public void testTrack1_5() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_1_3 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwadwo";
		
		generateDataSource("java:/TLPLMT15", queryValidator, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT15")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		subject.getPrincipals().add(new OtherPrincipal("ExistingRole"));

		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals().size(), is(4));
		for(OtherPrincipal otherPrincipal:subject.getPrincipals(OtherPrincipal.class)) {
			if(!otherPrincipal.getName().startsWith("Existing"))
				assertThat(rolesValues.contains(otherPrincipal.getName()), is(true));
		}		
	}
	
	@Test
	public void testTrack2() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_2 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		generateDataSource("java:/TLPLMT2", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT2")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = null;
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}

	@Test
	public void testTrack3() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_3 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		generateDataSource("java:/TLPLMT3", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT3")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.FALSE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack4() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_4 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		generateDataSource("java:/TLPLMT4", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT4")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack5() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_5 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		generateDataSource("java:/TLPLMT5", (query)->{throw new SQLException();}, setStringValidator, 
				rolesValuesIterator::hasNext, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT5")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack6() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_6 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT6")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal(username))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack7() throws Exception {
		final String rolesColumns = "roles";
		final List<String> rolesValues = Arrays.asList("AppUser","DevUser","GodMode");
		rolesQuery = "SELECT * FROM ROLES_7 WHERE username = ?";
		rolesColumnsIterator = Arrays.asList("roles","roles","roles").iterator();
		rolesValuesIterator = rolesValues.iterator();
		username = "kwaku";
		
		generateDataSource("java:/TLPLMT7", queryValidator, setStringValidator, 
				()->false, getString);
		
		Map<String, ?> options = newMap()
				.add(RESULT_ORIENTATION_POTRAITPARAMETER, TRUE)
				.add(DATABASE_JNDINAMEPARAMETER, "java:/TLPLMT7")
				.add(DATABASE_ROLESQUERYPARAMETER, rolesQuery)
				.add(DATABASE_ROLESCOLUMNSPARAMETER, rolesColumns)
				.thenBuild();
		
		Map<String, ?> sharedState = newMap()
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		Subject subject = new Subject();
		getModule().initialize(subject, callbacks->{}, sharedState, options);
		
		runModule();
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	private LoadProfileLoginModule loginModule;
	
	@Override
	protected LoginModule getModule() {
		if(loginModule == null)
			loginModule = new LoadProfileLoginModule();
		
		return loginModule;
	}

}
