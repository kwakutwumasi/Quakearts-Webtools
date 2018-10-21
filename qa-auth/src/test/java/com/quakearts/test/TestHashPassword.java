package com.quakearts.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.hamcrest.core.Is.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.webapp.security.util.HashPassword;

public class TestHashPassword {
 
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testHashPassword() {
		HashPassword hashPassword = new HashPassword();
		hashPassword.setAlgorithm("SHA-256");
		assertThat(hashPassword.getAlgorithm(), is("SHA-256"));
		hashPassword.setIterations(10);
		assertThat(hashPassword.getIterations(), is(10));
		hashPassword.setPlainText("Test");
		assertThat(hashPassword.getPlainText(), is("Test"));
		hashPassword.setSalt("Test");
		assertThat(hashPassword.getSalt(), is("Test"));
		
		assertThat(hashPassword.toString(), is(new HashPassword("Test","SHA-256",10,"Test").toString()));
		assertThat(hashPassword.toBytes(), is(new HashPassword("Test","SHA-256",10,"Test").toBytes()));
		hashPassword.setSalt(null);
		assertThat(hashPassword.toString(), is(new HashPassword("Test","SHA-256",10,HashPassword.DEFAULT_SALT).toString()));
	}

	@Test
	public void testMain() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Logger logger = Logger.getLogger(HashPassword.class.getName());
		Handler handler = new Handler() {
			
			@Override
			public void publish(LogRecord record) {
				try {
					bos.write(record.getMessage().getBytes());
				} catch (IOException e) {
				}
			}
			
			@Override
			public void flush() {
			}
			
			@Override
			public void close() throws SecurityException {
			}
		};
		logger.addHandler(handler);
		try {
			HashPassword.main(new String[]{});
			assertThat(new String(bos.toByteArray())
					.contains("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]"), is(true));
			bos.reset();
			HashPassword.main(new String[]{"Test","-alg"});
			assertThat(new String(bos.toByteArray())
					.contains("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]"), is(true));
			bos.reset();
			HashPassword.main(new String[]{"Test","-val","Test"});
			assertThat(new String(bos.toByteArray())
					.contains("Invalid argument: -val"), is(true));
			assertThat(new String(bos.toByteArray())
					.contains("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]"), is(true));
			bos.reset();
			HashPassword.main(new String[]{"Test","-iter","-alg"});
			assertThat(new String(bos.toByteArray())
					.contains("Usage - hash hashstring [-alg algorithim] [-salt salt] [-iter iterations]"), is(true));
			assertThat(new String(bos.toByteArray())
					.contains("Invalid integer"), is(true));
			bos.reset();
			HashPassword.main(new String[]{"Test"});
			assertThat(new String(bos.toByteArray())
					.contains("Hash: "+new HashPassword("Test", "MD5", 0, HashPassword.DEFAULT_SALT)), is(true));
			bos.reset();
			HashPassword.main(new String[]{"Test","-alg","SHA-512","-iter","30","-salt","SALT"});
			assertThat(new String(bos.toByteArray())
					.contains("Hash: "+new HashPassword("Test", "SHA-512", 30, "SALT")), is(true));
			bos.reset();
			assertThat(new HashPassword("Test", "SHA512", 30, "SALT").toString(),is(""));
			assertThat(new HashPassword("Test", "SHA512", 30, "SALT").toBytes(),is(new byte[0]));
		} finally {	
			logger.removeHandler(handler);
		}
	}
}
