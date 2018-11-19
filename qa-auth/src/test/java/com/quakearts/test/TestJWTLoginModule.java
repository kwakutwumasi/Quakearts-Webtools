package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import static com.quakearts.webapp.security.auth.JWTLoginModule.*;
import static com.quakearts.webapp.security.jwt.RegisteredNames.*;

import java.security.Principal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.auth.DirectoryRoles;
import com.quakearts.webapp.security.auth.EmailPrincipal;
import com.quakearts.webapp.security.auth.JWTLoginModule;
import com.quakearts.webapp.security.auth.JWTPrincipal;
import com.quakearts.webapp.security.auth.NamePrincipal;
import com.quakearts.webapp.security.auth.OtherPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.callback.TokenCallback;
import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.JWTSigner;
import com.quakearts.webapp.security.jwt.JWTVerifier;
import com.quakearts.webapp.security.jwt.factory.JWTFactory;

public class TestJWTLoginModule extends LoginModuleTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testTrack1_1() throws Exception {
		Subject subject = new Subject();
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "616161")
				.thenBuild(); 
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("test1@quakearts.com"))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		
		getModule().initialize(subject, c->{}, sharedState, options);
		runModule();
		
		assertThat(subject.getPrincipals(JWTPrincipal.class).size(), is(1));
		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		final JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		assertThat(jwtPrincipal.getName() !=null, is(true));

		JWTVerifier jwtVerifier = JWTFactory.getInstance().getVerifier("HS256", options);		
		jwtVerifier.verify(jwtPrincipal.getName().getBytes());

		JWTHeader header = jwtVerifier.getHeader();
		
		assertThat(header.getType(), is("JWT"));
		assertThat(header.getAlgorithm(), is("HS256"));		
		JWTClaims claims = jwtVerifier.getClaims();
		assertThat(claims.getAudience(), is(JWTLoginModule.class.getName()));
		long iat = System.currentTimeMillis() / 1000;
		long expiry = iat+900;
		assertThat(expiry-claims.getExpiry()<=1, is(true));
		assertThat(iat-claims.getIssuedAt()<=1, is(true));
		assertThat(claims.getIssuer(), is(JWTLoginModule.class.getName()));
		assertThat(claims.getNotBefore(), is(0l));
		assertThat(claims.getSubject(), is("test1@quakearts.com"));
		
		assertThat(subject.getPrincipals(DirectoryRoles.class).size(), is(1));
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("Roles"));

		Enumeration<? extends Principal> principals = roles.members();
		int count=0;
		while (principals.hasMoreElements()) {
			Principal principal = principals.nextElement();
			assertThat(principal, is(jwtPrincipal));
			count++;
		}
		assertThat(count, is(1));
		
		subject = new Subject();
		sharedState = new HashMap<>();

		getModule().initialize(subject,  c->{
			for(Callback callback:c){
				if(callback instanceof TokenCallback){
					((TokenCallback)callback).setTokenData(jwtPrincipal.getName().getBytes());
				}
			}
		}, sharedState, options);
		runModule();

		assertThat(subject.getPrincipals(UserPrincipal.class).size(), is(1));
		UserPrincipal principal = subject.getPrincipals(UserPrincipal.class).iterator().next();
		assertThat(principal.getName(), is("test1@quakearts.com"));	
		
		subject = new Subject();
		sharedState = null;

		getModule().initialize(subject,  c->{
			for(Callback callback:c){
				if(callback instanceof PasswordCallback){
					((PasswordCallback)callback).setPassword(jwtPrincipal.getName().toCharArray());
				}
			}
		}, sharedState, options);
		runModule();

		assertThat(subject.getPrincipals(UserPrincipal.class)!=null, is(true));
		principal = subject.getPrincipals(UserPrincipal.class).iterator().next();
		assertThat(principal.getName(), is("test1@quakearts.com"));
	}

	@Test
	public void testTrack1_2() throws Exception {
		Subject subject = new Subject();
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "ES256")
				.add("file", "test.keystore")
				.add("alias", "testeckey")
				.add("password", "test12")
				.add("storeType", "JCEKS")
				.add(ADDITIONALCLAIMSPARAMETER, "")
				.add(ROLESGROUPNAMEPARAMETER, "TestRoles")
				.add(GRACEPERIODPARAMETER, "Invalid")
				.add(VALIDITYPARAMETER, "Invalid")
				.add(VALIDITY_PERIODPARAMETER, "In Valid")
				.add(ACTIVATEAFTERPARAMETER, "Invalid")
				.add(ACTIVATEAFTERPERIODPARAMETER, "In Valid")
				.thenBuild(); 
		Map<String, ?> sharedState = newMap()
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();
		
		getModule().initialize(subject, c->{}, sharedState, options);
		runModule();
		
		assertThat(subject.getPrincipals(JWTPrincipal.class).size(), is(1));
		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		final JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		assertThat(jwtPrincipal.getName() !=null, is(true));
		
		JWTVerifier jwtVerifier = JWTFactory.getInstance().getVerifier("ES256", options);		
		jwtVerifier.verify(jwtPrincipal.getName().getBytes());

		JWTHeader header = jwtVerifier.getHeader();
		
		assertThat(header.getType(), is("JWT"));
		assertThat(header.getAlgorithm(), is("ES256"));		
		JWTClaims claims = jwtVerifier.getClaims();
		assertThat(claims.getAudience(), is(JWTLoginModule.class.getName()));
		long iat = System.currentTimeMillis() / 1000;
		long expiry = iat+900;
		assertThat(expiry-claims.getExpiry()<=1, is(true));
		assertThat(iat-claims.getIssuedAt()<=1, is(true));
		assertThat(claims.getIssuer(), is(JWTLoginModule.class.getName()));
		assertThat(claims.getNotBefore(), is(0l));
		assertThat(claims.getSubject(), is(nullValue()));	
		
		assertThat(subject.getPrincipals(DirectoryRoles.class).size(), is(1));
		DirectoryRoles roles = subject.getPrincipals(DirectoryRoles.class).iterator().next();
		assertThat(roles.getName(), is("TestRoles"));

		Enumeration<? extends Principal> principals = roles.members();
		int count=0;
		while (principals.hasMoreElements()) {
			Principal principal = principals.nextElement();
			assertThat(principal, is(jwtPrincipal));
			count++;
		}
		assertThat(count, is(1));
	}

	@Test
	public void testTrack1_3() throws Exception {
		Subject subject = new Subject();
		DirectoryRoles roles = new DirectoryRoles("Roles");
		roles.addMember(new NamePrincipal("Kwaku Appiah"));
		roles.addMember(new EmailPrincipal("kwaku@appiah.com"));
		roles.addMember(new OtherPrincipal("Admin", "admin-role"));
		roles.addMember(()->"Test3");
		subject.getPrincipals().add(roles);
		subject.getPrincipals().add(new DirectoryRoles("OtherRoles"));
		subject.getPrincipals().add(new OtherPrincipal("Ignored"));
		
		Map<String, ?> options = newMap()
				.add(ISSUERPARAMETER, "https://test.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://demo.quakearts.com")
				.add(ALGORITHMPARAMETER, "RS256")
				.add("file", "test.keystore")
				.add("alias", "testrsakey")
				.add("password", "test12")
				.add("file", "test.keystore")
				.add("storeType", "JCEKS")
				.add(ACTIVATEAFTERPERIODPARAMETER, "300 Seconds")
				.add(ADDITIONALCLAIMSPARAMETER, "test1:value1;test2:value2")
				.add(GRACEPERIODPARAMETER, "10")
				.add(VALIDITY_PERIODPARAMETER, "30 Minutes")
			.thenBuild();
		Map<String, ?> sharedState = newMap()
			.add("javax.security.auth.login.name", new UserPrincipal("test3@quakearts.com"))
			.add("com.quakearts.LoginOk", Boolean.TRUE)
		.thenBuild();
		
		getModule().initialize(subject, c->{}, sharedState, options);
		runModule();

		assertThat(subject.getPrincipals(JWTPrincipal.class).size(), is(1));
		
		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		
		assertThat(jwtPrincipal.getName()!=null, is(true));
		
		JWTVerifier jwtVerifier = JWTFactory.getInstance().getVerifier("RS256", options);		
		jwtVerifier.verify(jwtPrincipal.getName().getBytes());

		JWTHeader header = jwtVerifier.getHeader();
		
		assertThat(header.getType(), is("JWT"));
		assertThat(header.getAlgorithm(), is("RS256"));		
		JWTClaims claims = jwtVerifier.getClaims();
		assertThat(claims.getAudience(), is("https://demo.quakearts.com"));
		long iat = System.currentTimeMillis() / 1000;
		long expiry = iat+1800;
		long notBefore = iat+300;
		assertThat(expiry-claims.getExpiry()<=1, is(true));
		assertThat(iat-claims.getIssuedAt()<=1, is(true));
		assertThat(claims.getIssuer(), is("https://test.quakearts.com"));
		assertThat(notBefore-claims.getNotBefore()<=1, is(true));
		assertThat(claims.getSubject(), is("test3@quakearts.com"));
		assertThat(claims.getPrivateClaim("name"), is("Kwaku Appiah"));
		assertThat(claims.getPrivateClaim("mail"), is("kwaku@appiah.com"));
		assertThat(claims.getPrivateClaim("admin-role"), is("Admin"));
		assertThat(claims.getPrivateClaim("test1"), is("value1"));
		assertThat(claims.getPrivateClaim("test2"), is("value2"));
		assertThat(claims.getPrivateClaim("test3"), is("Test3"));
	}
	
	@Test
	public void testTrack1_4() throws Exception {
		Subject subject = new Subject();	
		Map<String, ?> options = newMap()
				.add(ISSUERPARAMETER, "https://api.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://app.quakearts.com")
				.add(ALGORITHMPARAMETER, "RS256")
				.add("file", "test.keystore")
				.add("alias", "testrsakey")
				.add("password", "test12")
				.add("file", "test.keystore")
				.add("storeType", "JCEKS")
				.add(ACTIVATEAFTERPARAMETER, "30")
				.add(ADDITIONALCLAIMSPARAMETER, "test1:value1;test2:value2;  :testInvalid1;testInvalid2: ;invalid3")
				.add(GRACEPERIODPARAMETER, "10")
				.add(VALIDITYPARAMETER, "180")
			.thenBuild();
		Map<String, ?> sharedState = newMap()
			.add("javax.security.auth.login.name", new UserPrincipal("test4@quakearts.com"))
			.add("com.quakearts.LoginOk", Boolean.TRUE)
		.thenBuild();
		
		getModule().initialize(subject, (c)->{}, sharedState, options);
		runModule();

		assertThat(subject.getPrincipals(JWTPrincipal.class).size(), is(1));
		
		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		
		assertThat(jwtPrincipal.getName()!=null, is(true));
		
		JWTVerifier jwtVerifier = JWTFactory.getInstance().getVerifier("RS256", options);		
		jwtVerifier.verify(jwtPrincipal.getName().getBytes());

		JWTHeader header = jwtVerifier.getHeader();
		
		assertThat(header.getType(), is("JWT"));
		assertThat(header.getAlgorithm(), is("RS256"));		
		JWTClaims claims = jwtVerifier.getClaims();
		assertThat(claims.getAudience(), is("https://app.quakearts.com"));
		long iat = System.currentTimeMillis() / 1000;
		long expiry = iat+180;
		long notBefore = iat+30;
		assertThat(expiry-claims.getExpiry()<=1, is(true));
		assertThat(iat-claims.getIssuedAt()<=1, is(true));
		assertThat(claims.getIssuer(), is("https://api.quakearts.com"));
		assertThat(notBefore-claims.getNotBefore()<=1, is(true));
		assertThat(claims.getSubject(), is("test4@quakearts.com"));
	}
	
	@Test
	public void testTrack1_5() throws Exception {
		Map<String, ?> sharedState = null;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://jwt.quakearts.com")
				.add(AUD, "https://payment.quakearts.com")
				.add(EXP, 0l)
				.add(NBF, (System.currentTimeMillis()/1000))
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "ES256")
				.add("file", "test.keystore")
				.add("alias", "testeckey")
				.add("password", "test12")
				.add("storeType", "JCEKS")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://payment.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("ES256", options);
		String token = jwtSigner.sign(header, claims);
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
		
		assertThat(subject.getPrincipals(UserPrincipal.class).size(), is(1));
		assertThat(subject.getPrincipals(UserPrincipal.class).iterator().next().getName(), is("test5@quakearts.com"));
		assertThat(subject.getPrincipals(OtherPrincipal.class).size(), is(1));
		assertThat(subject.getPrincipals(OtherPrincipal.class).iterator().next().getName(), is("PaymentChange"));
	}

	@Test
	public void testTrack3() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Credentials are missing");
		
		Subject subject = new Subject();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("test5@quakearts.com"))
				.thenBuild();

		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "ES256")
				.add("file", "test.keystore")
				.add("alias", "testeckey")
				.add("password", "test12")
				.add("storeType", "JCEKS")
			.thenBuild();
		
		getModule().initialize(subject, c->{}, sharedState, options);
		runModule();	
	}
	
	@Test
	public void testTrack4() throws Exception {
		Subject subject = new Subject();
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "616161")
				.thenBuild(); 
		Map<String, ?> sharedState = new HashMap<>();
		
		getModule().initialize(subject, c->{
			throw new UnsupportedCallbackException(c[0]);
		}, sharedState, options);
		
		assertThat(getModule().login(), is(false));
		assertThat(getModule().commit(), is(false));
		assertThat(getModule().logout(), is(true));
		assertThat(subject.getPrincipals().isEmpty(), is(true));
	}
	
	@Test
	public void testTrack5() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Unable to verify token: Incomplete setup. Missing: key;");
		Map<String, ?> sharedState = null;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://jwt.quakearts.com")
				.add(AUD, "https://payment.quakearts.com")
				.add(EXP, (System.currentTimeMillis()/1000)+1800)
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "secret.test")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://payment.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("HS256", options);
		String token = jwtSigner.sign(header, claims);
		
		options.remove("secret");
		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack6() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Issuer is not recognized");
		Map<String, ?> sharedState = null;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://api.quakearts.com")
				.add(AUD, "https://payment.quakearts.com")
				.add(EXP, (System.currentTimeMillis()/1000)+1800)
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "secret.test")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://payment.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("HS256", options);
		String token = jwtSigner.sign(header, claims);		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack7() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Audience is not recognized");
		Map<String, ?> sharedState = null;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://jwt.quakearts.com")
				.add(AUD, "https://app.quakearts.com")
				.add(EXP, (System.currentTimeMillis()/1000)+1800)
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "secret.test")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://api.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("HS256", options);
		String token = jwtSigner.sign(header, claims);		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack8() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Token has expired");
		Map<String, ?> sharedState = null;
		
		long iat = (System.currentTimeMillis()/1000)-2000;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://jwt.quakearts.com")
				.add(AUD, "https://api.quakearts.com")
				.add(IAT, iat)
				.add(EXP, iat+1800)
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "secret.test")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://api.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("HS256", options);
		String token = jwtSigner.sign(header, claims);		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack9() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Token is not active");
		Map<String, ?> sharedState = null;
		
		JWTHeader header = JWTFactory.getInstance().createEmptyClaimsHeader();
		JWTClaims claims = JWTFactory.getInstance().createJWTClaimsFromMap(newMap()
				.add(SUB, "test5@quakearts.com")
				.add(ISS, "https://jwt.quakearts.com")
				.add(AUD, "https://api.quakearts.com")
				.add(EXP, (System.currentTimeMillis()/1000)+1800)
				.add(NBF, (System.currentTimeMillis()/1000)+200)
				.add("role", "PaymentChange")
				.thenBuild());
		
		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "HS256")
				.add("secret", "secret.test")
				.add(ISSUERPARAMETER, "https://jwt.quakearts.com")
				.add(AUDIENCEPARAMETER, "https://api.quakearts.com")				
			.thenBuild();
		
		JWTSigner jwtSigner = JWTFactory.getInstance().getSigner("HS256", options);
		String token = jwtSigner.sign(header, claims);		
		Subject subject = new Subject();
		
		getModule().initialize(subject, callbacks->{
			for(Callback callback:callbacks) {
				if(callback instanceof TokenCallback) {
					((TokenCallback)callback).setTokenData(token.getBytes());
				}
			}
		}, sharedState, options);
		
		runModule();
	}
	
	@Test
	public void testTrack10() throws Exception {
		expectedException.expect(LoginException.class);
		expectedException.expectMessage("Unable to sign: Incomplete setup. Missing: file;");
		
		Subject subject = new Subject();
		
		Map<String, ?> sharedState = newMap()
				.add("javax.security.auth.login.name", new UserPrincipal("test5@quakearts.com"))
				.add("com.quakearts.LoginOk", Boolean.TRUE)
				.thenBuild();

		Map<String, ?> options = newMap()
				.add(ALGORITHMPARAMETER, "ES256")
				.add("alias", "testeckey")
				.add("password", "test12")
				.add("storeType", "JCEKS")
			.thenBuild();
		
		getModule().initialize(subject, c->{}, sharedState, options);
		runModule();	
	}
	
	private JWTLoginModule loginModule;
	
	@Override
	protected LoginModule getModule() {
		if(loginModule == null)
			loginModule = new JWTLoginModule();
			
		return loginModule;
	}
}