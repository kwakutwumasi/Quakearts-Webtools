package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.jpa.EncryptedBytesConverter;
import com.quakearts.security.cryptography.jpa.EncryptedStringConverter;
import com.quakearts.security.cryptography.test.datasource.MockDataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

public class JPAConverterTests {

	@BeforeClass
	public static void setupDataSource() {
		new MockDataStoreFactory();
	}
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testEncryptedBytesConverter() {
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		byte[] encrypted = converter.convertToDatabaseColumn("Test".getBytes());
		byte[] plaintext = converter.convertToEntityAttribute(encrypted);
		
		assertArrayEquals("Test".getBytes(), plaintext);
		encrypted = converter.convertToDatabaseColumn(null);
		assertNull(encrypted);
		plaintext = converter.convertToEntityAttribute(encrypted);
		assertNull(plaintext);
	}

	@Test
	public void testEncryptedStringConverter() {
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		String encrypted = converter.convertToDatabaseColumn("Test");
		String plaintext = converter.convertToEntityAttribute(encrypted);
		
		assertEquals("Test", plaintext);
		encrypted = converter.convertToDatabaseColumn(null);
		assertNull(encrypted);
		plaintext = converter.convertToEntityAttribute(encrypted);
		assertNull(plaintext);
	}
	
	@Test
	public void testEncryptedTypeBaseInvalidClassName() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-class");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		converter.convertToDatabaseColumn("Test".getBytes());		
	}

	@Test
	public void testEncryptedTypeBaseInvalidInstance() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-instance");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		converter.convertToDatabaseColumn("Test");
	}
	
	@Test
	public void testEncryptedBytesConverterInvalidKeyUsingConvertToDatabaseColumn() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		converter.convertToDatabaseColumn("Test".getBytes());		
	}

	@Test
	public void testEncryptedBytesConverterInvalidKeyUsingConvertToEntityAttribute() throws Exception {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		converter.convertToEntityAttribute(CryptoResource.hexAsByte("398671290190277011c9f055c9359629"));		
	}

	@Test
	public void testEncryptedStringConverterInvalidKeyUsingConvertToDatabaseColumn() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		converter.convertToDatabaseColumn("Test");		
	}

	@Test
	public void testEncryptedStringConverterInvalidKeyUsingConvertToEntityAttribute() throws Exception {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		converter.convertToEntityAttribute("398671290190277011c9f055c9359629");		
	}
	
	@Test
	public void testEncryptedTypeBaseInvalidKeyStoreFile() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-keystorefile");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		converter.convertToDatabaseColumn("Test");
	}
	
	@Test
	public void testEncryptedTypeBaseThrowErrorOnGetKey() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-throw-error-on-get-key");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		converter.convertToDatabaseColumn("Test".getBytes());		
	}
	
	@Test
	public void testEncryptedTypeBaseNonExistent() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-non-existent");
		EncryptedBytesConverter converter = new EncryptedBytesConverter();
		converter.convertToDatabaseColumn("Test".getBytes());		
	}

}
