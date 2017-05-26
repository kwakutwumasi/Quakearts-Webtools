package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;

import org.junit.Test;

import com.quakearts.webapp.security.auth.JWTLoginModule;
import com.quakearts.webapp.security.auth.JWTPrincipal;
import com.quakearts.webapp.security.auth.UserPrincipal;
import com.quakearts.webapp.security.auth.callback.TokenCallback;

public class TestJWTLoginModule {

	@Test
	public void testLoginModuleSimple() throws Exception {
		JWTLoginModule module = new JWTLoginModule();
		Subject subject = new Subject();
		
		Map<String, Object> options = new HashMap<>(), sharedState = new HashMap<>();
		
		options.put("issuer", "https://test.quakearts.com");
		options.put("audience", "https://test.quakearts.com");
		options.put("algorithm", "HS256");
		options.put("secret", "616161");
		
		sharedState.put("javax.security.auth.login.name", new UserPrincipal("test@quakearts.com"));
		sharedState.put("com.quakearts.LoginOk", Boolean.TRUE);
		
		module.initialize(subject, (c)->{}, sharedState, options);
		module.login();
		module.commit();
		
		assertThat(subject.getPrincipals(JWTPrincipal.class)!=null, is(true));

		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		
		final JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		
		assertThat(jwtPrincipal.getName() !=null, is(true));

		CallbackHandler callbackHandler = (c)->{
			for(Callback callback:c){
				if(callback instanceof TokenCallback){
					((TokenCallback)callback).setTokenData(jwtPrincipal.getName().getBytes());
				}
			}
		};
		
		module = new JWTLoginModule();
		subject = new Subject();
		sharedState = new HashMap<>();

		module.initialize(subject, callbackHandler, sharedState, options);
		module.login();
		module.commit();

		assertThat(subject.getPrincipals(UserPrincipal.class)!=null, is(true));
		
		Set<UserPrincipal> principals = subject.getPrincipals(UserPrincipal.class);
		
		UserPrincipal principal = principals.iterator().next();
		
		assertThat(principal.getName(), is("test@quakearts.com"));		
	}

	@Test
	public void testLoginModuleFull() throws Exception {
		JWTLoginModule module = new JWTLoginModule();
		Subject subject = new Subject();
		
		Map<String, Object> options = new HashMap<>(), sharedState = new HashMap<>();

		sharedState.put("javax.security.auth.login.name", new UserPrincipal("test@quakearts.com"));
		sharedState.put("com.quakearts.LoginOk", Boolean.TRUE);
		
		options.put("issuer", "https://test.quakearts.com");
		options.put("algorithm", "RS256");
		options.put("file", "test.keystore");
		options.put("alias", "testrsakey");
		options.put("password", "test12");
		options.put("file", "test.keystore");
		options.put("storeType", "JCEKS");
		options.put("activate.after", "30");
		options.put("additional.claims", "test1:value1;test2:value2");
		options.put("grace.period", "10");
		options.put("validity", "18000");
		
		module.initialize(subject, (c)->{}, sharedState, options);
		module.login();
		module.commit();

		assertThat(subject.getPrincipals(JWTPrincipal.class)!=null, is(true));
		
		Set<JWTPrincipal> jwtPrincipals = subject.getPrincipals(JWTPrincipal.class);
		
		JWTPrincipal jwtPrincipal = jwtPrincipals.iterator().next();
		
		assertThat(jwtPrincipal.getName()!=null, is(true));
	}
}
