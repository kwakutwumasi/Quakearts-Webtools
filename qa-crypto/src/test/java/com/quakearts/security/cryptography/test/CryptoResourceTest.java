package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.crypto.spec.SecretKeySpec;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.IllegalCryptoActionException;
import com.quakearts.security.cryptography.exception.KeyProviderException;
import com.quakearts.tools.test.generator.primitives.StringGenerator;
import com.quakearts.tools.test.generator.primitives.StringRandomGenerator;

public class CryptoResourceTest {

	private ExecutorService executor;
	
	@Before
	public void init() {
		executor = Executors.newFixedThreadPool(10);
	}
	
	@After
	public void finish() {
		if(executor!=null)
			executor.shutdown();
	}
	
	public class TestTriplet {
		private String plainText, cipherText, decryptedPlainText;
		
		public TestTriplet(StringRandomGenerator<String> generator, CryptoResource cryptoResource) throws IllegalCryptoActionException {
			plainText = generator.generateRandom();
			cipherText = cryptoResource.doEncrypt(plainText);
			decryptedPlainText = cryptoResource.doDecrypt(cipherText);
		}
		
		public String getPlainText() {
			return plainText;
		}

		public String getCipherText() {
			return cipherText;
		}

		public String getDecryptedPlainText() {
			return decryptedPlainText;
		}
		
	}
	
	@Test
	public void testCryptoResourceAESCBCPKCS5Padding() throws Exception {
		final StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		final CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CBC/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceAESCFBPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CFB/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceAESOFBPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/OFB/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceAESCTRPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES/CTR/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceAES() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getAESKey(), "AES");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}
//
	@Test
	public void testCryptoResourceBlowfishCBCPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getBlowfishKey(), "Blowfish/CBC/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceBlowfishCFBPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getBlowfishKey(), "Blowfish/CFB8/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceBlowfishOFBPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getBlowfishKey(), "Blowfish/OFB32/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceBlowfishCTRPKCS5Padding() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getBlowfishKey(), "Blowfish/CTR/PKCS5Padding");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}

	@Test
	public void testCryptoResourceBlowfish() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		CryptoResource cryptoResource = new CryptoResource(getBlowfishKey(), "Blowfish");
		List<Future<TestTriplet>> testTripletFutures = new CopyOnWriteArrayList<>();
		for(int i=0;i<100;i++) {
			testTripletFutures.add(executor.submit(()->{
				return new TestTriplet(generator, cryptoResource);
			}));
		}
		
		for(Future<TestTriplet> testTripletFuture:testTripletFutures) {
			TestTriplet testTriplet = testTripletFuture.get();
			assertThat(testTriplet.getDecryptedPlainText(), is(testTriplet.getPlainText()));
		}
	}
	
	@Test
	public void testHextoByteByteToHex() throws Exception {
		StringRandomGenerator<String> generator = new StringGenerator().useLength(32);
		for(int i=0;i<1000;i++) {
			String random = generator.generateRandom();
			assertThat(new String(CryptoResource.hexAsByte(CryptoResource.byteAsHex(random.getBytes())))
					, is(random));
		}
	}
	
	private Key getBlowfishKey() throws KeyProviderException {
		byte[] key;
		try {
			key = "testkey".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new KeyProviderException(e);
		}

		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new KeyProviderException(e);
		}

		key = sha.digest(key);
		key = Arrays.copyOf(key, 4); // use only first 32 bits

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "Blowfish");
		return secretKeySpec;
	}

	private Key getAESKey() throws KeyProviderException {
		byte[] key;
		try {
			key = "testkey".getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new KeyProviderException(e);
		}

		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new KeyProviderException(e);
		}

		key = sha.digest(key);
		key = Arrays.copyOf(key, 16); // use only first 128 bits

		SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
		return secretKeySpec;
	}
}
