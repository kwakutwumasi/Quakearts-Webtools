package com.quakearts.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.IsNull.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.impl.JWTClaimsImpl;

public class TestJWTClaims {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testJWTClaimsImpl() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getIssuedAt(), is(not(0l)));
	}

	@Test
	public void testSubject() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getSubject(), is(nullValue()));
		jwtClaimsImpl.setSubject("testSubject");
		assertThat(jwtClaimsImpl.getSubject(), is("testSubject"));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"sub\":0}"));
		assertThat(jwtClaimsImpl.getSubject(), is(nullValue()));
	}

	@Test
	public void testIssuer() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getIssuer(), is(nullValue()));
		jwtClaimsImpl.setIssuer("testIssuer");
		assertThat(jwtClaimsImpl.getIssuer(), is("testIssuer"));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"iss\":0}"));
		assertThat(jwtClaimsImpl.getIssuer(), is(nullValue()));		
	}

	@Test
	public void testAudience() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getAudience(), is(nullValue()));
		jwtClaimsImpl.setAudience("testAudience");
		assertThat(jwtClaimsImpl.getAudience(), is("testAudience"));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"aud\":0}"));
		assertThat(jwtClaimsImpl.getAudience(), is(nullValue()));
	}

	@Test
	public void testExpiry() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getExpiry(), is(0l));
		jwtClaimsImpl.setExpiry(100l);
		assertThat(jwtClaimsImpl.getExpiry(), is(100l));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"exp\":false}"));
		assertThat(jwtClaimsImpl.getExpiry(), is(0l));
	}

	@Test
	public void testNotBefore() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getNotBefore(), is(0l));
		jwtClaimsImpl.setNotBefore(101l);
		assertThat(jwtClaimsImpl.getNotBefore(), is(101l));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"nbf\":true}"));
		assertThat(jwtClaimsImpl.getNotBefore(), is(0l));
	}

	@Test
	public void testIssuedAt() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getIssuedAt(), is(notNullValue()));
		jwtClaimsImpl.setIssuedAt(102l);
		assertThat(jwtClaimsImpl.getIssuedAt(), is(102l));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"iat\":true}"));
		assertThat(jwtClaimsImpl.getIssuedAt(), is(0l));
		jwtClaimsImpl.unCompact(encode("{}"));
		assertThat(jwtClaimsImpl.getIssuedAt(), is(0l));
	}

	@Test
	public void testPrivateClaim() {
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		assertThat(jwtClaimsImpl.getPrivateClaim("private"), is(nullValue()));
		jwtClaimsImpl.addPrivateClaim("private", "value");
		assertThat(jwtClaimsImpl.getPrivateClaim("private"), is("value"));
		jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.unCompact(encode("{\"private\":true}"));
		assertThat(jwtClaimsImpl.getPrivateClaim("private"), is(nullValue()));
	}

	@Test
	public void testIterator() {
		Set<String> testValues = new HashSet<String>(Arrays
				.asList("sub","iss","aud","iat","exp","nbf","private"));
		JWTClaims jwtClaims = new JWTClaimsImpl()
				.setAudience("testAudience")
				.setExpiry(100l)
				.setIssuedAt(101l)
				.setIssuer("testIssuer")
				.setNotBefore(102l)
				.setSubject("testSubject")
				.addPrivateClaim("private", "value");
		
		for(JWTClaims.Claim claim: jwtClaims) {
			testValues.remove(claim.getName());
		}
		
		assertThat(testValues.isEmpty(), is(true));
	}

	@Test
	public void testCompact() {
		JWTClaims jwtClaims = new JWTClaimsImpl()
				.setAudience("testAudience")
				.setExpiry(100l)
				.setIssuedAt(101l)
				.setIssuer("testIssuer")
				.setNotBefore(102l)
				.setSubject("testSubject")
				.addPrivateClaim("private", "value");
		
		JWTClaims jwtClaims2 = new JWTClaimsImpl();
		jwtClaims2.unCompact(jwtClaims.compact());
		
		assertThat(jwtClaims2.getAudience(), is(jwtClaims.getAudience()));
		assertThat(jwtClaims2.getExpiry(), is(jwtClaims.getExpiry()));
		assertThat(jwtClaims2.getIssuedAt(), is(jwtClaims.getIssuedAt()));
		assertThat(jwtClaims2.getIssuer(), is(jwtClaims.getIssuer()));
		assertThat(jwtClaims2.getNotBefore(), is(jwtClaims.getNotBefore()));
		assertThat(jwtClaims2.getPrivateClaim("private"), is(jwtClaims.getPrivateClaim("private")));
		assertThat(jwtClaims2.getSubject(), is(jwtClaims.getSubject()));
	}

	@Test
	public void testAddPrivateClaim_aud() throws Exception {
		testAddInvalidName("aud");
	}

	@Test
	public void testAddPrivateClaim_exp() throws Exception {
		testAddInvalidName("exp");
	}
	
	@Test
	public void testAddPrivateClaim_iat() throws Exception {
		testAddInvalidName("iat");
	}
	
	@Test
	public void testAddPrivateClaim_iss() throws Exception {
		testAddInvalidName("iss");
	}
	
	@Test
	public void testAddPrivateClaim_nbf() throws Exception {
		testAddInvalidName("nbf");
	}
	
	@Test
	public void testAddPrivateClaim_sub() throws Exception {
		testAddInvalidName("sub");
	}
		
	@Test
	public void testGetPrivateClaim_aud() throws Exception {
		testGetInvalidName("aud");
	}

	@Test
	public void testGetPrivateClaim_exp() throws Exception {
		testGetInvalidName("exp");
	}
	
	@Test
	public void testGetPrivateClaim_iat() throws Exception {
		testGetInvalidName("iat");
	}
	
	@Test
	public void testGetPrivateClaim_iss() throws Exception {
		testGetInvalidName("iss");
	}
	
	@Test
	public void testGetPrivateClaim_nbf() throws Exception {
		testGetInvalidName("nbf");
	}
	
	@Test
	public void testGetPrivateClaim_sub() throws Exception {
		testGetInvalidName("sub");
	}
	
	private String encode(String string) {
		String encoded = Base64.getUrlEncoder().encodeToString(string.getBytes());
		if(encoded.endsWith("="))
			encoded = encoded.substring(0, encoded.indexOf('='));
		return encoded;
	}
	
	private void testAddInvalidName(String name) {
		expectedException.expect(UnsupportedOperationException.class);
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.addPrivateClaim(name, "value");
	}
	
	private void testGetInvalidName(String name) {
		expectedException.expect(UnsupportedOperationException.class);
		JWTClaimsImpl jwtClaimsImpl = new JWTClaimsImpl();
		jwtClaimsImpl.getPrivateClaim(name);
	}

}
