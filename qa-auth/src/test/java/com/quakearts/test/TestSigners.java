package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.jwt.JWTClaims;
import com.quakearts.webapp.security.jwt.JWTHeader;
import com.quakearts.webapp.security.jwt.exception.JWTException;
import com.quakearts.webapp.security.jwt.factory.JWTFactory;
import com.quakearts.webapp.security.jwt.impl.ESSigner;
import com.quakearts.webapp.security.jwt.impl.HSSigner;
import com.quakearts.webapp.security.jwt.impl.RSSigner;

public class TestSigners {

	@Before
	public void init(){
		header = JWTFactory.getInstance().createEmptyClaimsHeader();
		claims = JWTFactory.getInstance().createEmptyClaims()
				.setAudience("Test")
				.setExpiry(1800)
				.setIssuer("Test Issuer")
				.setSubject("testsubject");
	}
	
	JWTClaims claims;
	JWTHeader header;
	
	@Test
	public void testHSSigners() throws Exception {
		HSSigner signer = new HSSigner();
		signer.setAlgorithim("HS256");
		signer.setParameter("secret", "1234234243");
		
		String token = signer.sign(header, claims);
		signer.verify(token);
		
		assertThat(signer.getClaims().getSubject(), is("testsubject"));
		assertThat(signer.getClaims().getExpiry(), is(1800l));
		assertThat(signer.getClaims().getIssuer(), is("Test Issuer"));
		assertThat(signer.getClaims().getAudience(), is("Test"));

		signer = new HSSigner();
		signer.setAlgorithim("HS384");
		signer.setParameter("secret", "1234234243");
		
		token = signer.sign(header, claims);
		signer.verify(token.getBytes());
		
		signer = new HSSigner();
		signer.setAlgorithim("HS512");
		signer.setParameter("secret", "1234234243");
		
		token = signer.sign(header, claims);
		signer.verify(token);
	}
	
	@Test
	public void testRSSigner() throws Exception {
		RSSigner signer = new RSSigner();
		
		signer.setAlgorithim("RS256");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testrsakey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		String token  = signer.sign(header, claims);
		signer.verify(token);
		
		signer = new RSSigner();
		signer.setAlgorithim("RS384");		
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testrsakey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");		
		
		token  = signer.sign(header, claims);
		signer.verify(token.getBytes());
		
		signer = new RSSigner();
		signer.setAlgorithim("RS512");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testrsakey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");		
		token  = signer.sign(header, claims);
		signer.verify(token);
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testRSSVerifyOnly() throws Exception {
		RSSigner signer = new RSSigner();
		
		signer.setAlgorithim("RS256");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testrsakey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		String token = signer.sign(header, claims);
		
		RSSigner verifier = new RSSigner();
		verifier.setAlgorithim("RS256");
		verifier.setParameter("file", "testpub.keystore");
		verifier.setParameter("alias", "testrsapub");
		verifier.setParameter("password", "test12");
		verifier.setParameter("storeType", "JCEKS");
		verifier.setParameter("keyType", "PUBLICONLY");
		verifier.verify(token);
		
		expectedException.expect(JWTException.class);
		expectedException.expectMessage("Unable to sign: No installed provider supports this key: com.quakearts.webapp.security.jwt.keyprovider.KeystoreKeyPairProvider$NullPrivateKey");
		verifier.sign(header, claims);
	}
	
	@Test
	public void testECSigner() throws Exception {
		ESSigner signer = new ESSigner();
		
		signer.setAlgorithim("ES256");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testeckey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		String token = signer.sign(header, claims);
		signer.verify(token);
		
		signer = new ESSigner();
		signer.setAlgorithim("ES384");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testeckey384");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		token = signer.sign(header, claims);
		signer.verify(token.getBytes());

		signer = new ESSigner();
		signer.setAlgorithim("ES512");
		signer.setParameter("alias", "testeckey512");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		token = signer.sign(header, claims);
		signer.verify(token);
		
		try {
			signer = new ESSigner();
			signer.setAlgorithim("ES512");
			signer.setParameter("alias", "testeckey384");
			signer.setParameter("file", "test.keystore");
			signer.setParameter("password", "test12");
			signer.setParameter("storeType", "JCEKS");

			token = signer.sign(header, claims);
			signer.verify(token.getBytes());
			fail();
		} catch (Exception e) {
		}
	}
	
	@Test
	public void testESSVerifyOnly() throws Exception {
		ESSigner signer = new ESSigner();
		
		signer.setAlgorithim("ES256");
		signer.setParameter("file", "test.keystore");
		signer.setParameter("alias", "testeckey");
		signer.setParameter("password", "test12");
		signer.setParameter("storeType", "JCEKS");
		
		String token = signer.sign(header, claims);
		
		ESSigner verifier = new ESSigner();
		verifier.setAlgorithim("ES256");
		verifier.setParameter("file", "testpub.keystore");
		verifier.setParameter("alias", "testecdsapub");
		verifier.setParameter("password", "test12");
		verifier.setParameter("storeType", "JCEKS");
		verifier.setParameter("keyType", "PUBLICONLY");
		verifier.verify(token);
		
		expectedException.expect(JWTException.class);
		expectedException.expectMessage("Unable to sign: No installed provider supports this key: com.quakearts.webapp.security.jwt.keyprovider.KeystoreKeyPairProvider$NullPrivateKey");
		verifier.sign(header, claims);
	}
}
