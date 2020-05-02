package com.quakearts.security.cryptography.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.exception.CryptoResourceRuntimeException;
import com.quakearts.security.cryptography.jpa.EncryptedBytesConverter;
import com.quakearts.security.cryptography.jpa.EncryptedStringConverter;
import com.quakearts.security.cryptography.jpa.EncryptedValue;
import com.quakearts.security.cryptography.jpa.EncryptedValueConverter;
import com.quakearts.security.cryptography.jpa.EncryptedValueStringConverter;
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
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-keystorefile");
		EncryptedStringConverter converter = new EncryptedStringConverter();
		converter.convertToDatabaseColumn("Test");
	}
	
	@Test
	public void testEncryptedTypeBaseThrowErrorOnGetKey() {
		expectedException.expect(DataStoreException.class);
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
	
	@Test
	public void testEncryptedValueConverter() throws Exception {
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		byte[] encrypted = converter.convertToDatabaseColumn(value);
		EncryptedValue plaintext = converter.convertToEntityAttribute(encrypted);
		
		assertThat(plaintext,is(value));
		encrypted = converter.convertToDatabaseColumn(null);
		assertNull(encrypted);
		plaintext = converter.convertToEntityAttribute(encrypted);
		assertNull(plaintext);
	}

	@Test
	public void testEncryptedValueConverterWithNullDataStoreName() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setStringValue("Test");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueConverterWithNullValue() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueConverterWithEmptyValue() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueConverterWithInvalidEncryptedString() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The value returned from the database was tampered with. The dataStoreName could not be found"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		converter.convertToEntityAttribute(CryptoResource.hexAsByte("398671290190277011c9f055c9359629"));
	}
	
	@Test
	public void testEncryptedValueConverterInvalidClassName() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-class");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}

	@Test
	public void testEncryptedValueConverterInvalidInstance() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-instance");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueConverterInvalidKeyUsingConvertToDatabaseColumn() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}

	@Test
	public void testEncryptedValueConverterInvalidKeyUsingConvertToEntityAttribute() throws Exception {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		converter.convertToEntityAttribute(CryptoResource.hexAsByte("7465737473746f72657c398671290190277011c9f055c9359629"));		
	}
	
	@Test
	public void testEncryptedValueConverterInvalidKeyStoreFile() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-keystorefile");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueConverterThrowErrorOnGetKey() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-throw-error-on-get-key");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueConverterNonExistent() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-non-existent");
		EncryptedValueConverter converter = new EncryptedValueConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueStringConverter() throws Exception {
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		String encrypted = converter.convertToDatabaseColumn(value);
		EncryptedValue plaintext = converter.convertToEntityAttribute(encrypted);
		
		assertThat(plaintext,is(value));
		encrypted = converter.convertToDatabaseColumn(null);
		assertNull(encrypted);
		plaintext = converter.convertToEntityAttribute(encrypted);
		assertNull(plaintext);
	}

	@Test
	public void testEncryptedValueStringConverterWithNullDataStoreName() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setStringValue("Test");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueStringConverterWithNullValue() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueStringConverterWithEmptyValue() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The dataStoreName and value are required for EncryptedValue"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		converter.convertToDatabaseColumn(value);
	}
	
	@Test
	public void testEncryptedValueStringConverterWithInvalidEncryptedString() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage(is("The value returned from the database was tampered with. The dataStoreName could not be found"));
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		converter.convertToEntityAttribute("398671290190277011c9f055c9359629");
	}
	
	@Test
	public void testEncryptedValueStringConverterInvalidClassName() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-class");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}

	@Test
	public void testEncryptedValueStringConverterInvalidInstance() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-instance");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueStringConverterInvalidKeyUsingConvertToDatabaseColumn() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}

	@Test
	public void testEncryptedValueStringConverterInvalidKeyUsingConvertToEntityAttribute() throws Exception {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-key");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		converter.convertToEntityAttribute("teststore|398671290190277011c9f055c9359629");		
	}
	
	@Test
	public void testEncryptedValueStringConverterInvalidKeyStoreFile() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-invalid-keystorefile");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueStringConverterThrowErrorOnGetKey() {
		expectedException.expect(DataStoreException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-throw-error-on-get-key");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}
	
	@Test
	public void testEncryptedValueStringConverterNonExistent() {
		expectedException.expect(CryptoResourceRuntimeException.class);
		MockDataStoreFactory.getMap().put("com.quakearts.cryptoname", "test-non-existent");
		EncryptedValueStringConverter converter = new EncryptedValueStringConverter();
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		converter.convertToDatabaseColumn(value);		
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testEncryptedValue() throws Exception {
		EncryptedValue value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		assertThat(value.getDataStoreName(), is("teststore"));
		assertThat(value.getStringValue(), is("Test"));
		assertThat(value.getValue(), is("Test".getBytes()));
		
		value = new EncryptedValue();
		value.setStringValue(null);
		assertNull(value.getValue());
		assertNull(value.getStringValue());
		
		value = new EncryptedValue();
		value.setDataStoreName("teststore");
		value.setStringValue("Test");
		
		EncryptedValue value2 = new EncryptedValue();
		value2.setDataStoreName("teststore");
		value2.setStringValue("Test");
		
		EncryptedValue value3 = new EncryptedValue();
		value3.setDataStoreName("teststore1");
		value3.setStringValue("Test");
		
		EncryptedValue value4 = new EncryptedValue();
		value4.setDataStoreName("teststore");
		value4.setStringValue("Test1");
		
		EncryptedValue value5 = new EncryptedValue();
		value5.setDataStoreName("teststore");

		EncryptedValue value6 = new EncryptedValue();
		value6.setStringValue("Test");
		
		EncryptedValue value7 = new EncryptedValue();
		value7.setDataStoreName("teststore1");
		value7.setStringValue("Test1");
		
		assertThat(value, is(value));
		assertThat(value2, is(value));
		assertTrue(value.hashCode()==value2.hashCode());
		assertThat(value3, is(not(value)));
		assertFalse(value.hashCode()==value3.hashCode());
		assertThat(value4, is(not(value)));
		assertFalse(value.hashCode()==value4.hashCode());
		assertThat(value5, is(not(value)));
		assertFalse(value.hashCode()==value5.hashCode());
		assertThat(value6, is(not(value)));
		assertFalse(value.hashCode()==value6.hashCode());
		assertThat(value7, is(not(value)));
		assertFalse(value.hashCode()==value7.hashCode());
		assertThat(new EncryptedValue(), is(not(value)));
		assertFalse(value.hashCode()==new EncryptedValue().hashCode());
		
		assertFalse(value.equals(""));
		assertFalse(value.equals(null));
		
		assertThat(value.toString(), is("EncryptedValue [dataStoreName="+value.getDataStoreName()
			+", value="+CryptoResource.byteAsHex(value.getValue())+"]"));
	}

}
