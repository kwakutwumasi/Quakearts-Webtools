package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.test.helpers.TestBean;
import com.quakearts.appbase.test.helpers.TestBeanReadOnly;

public class TestAppBasePropertiesLoader {

	String fileLocation = "src"+File.separator+"test"+File.separator+"resources";
	
	@Test
	public void testGetAllConfigurationProperties() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		List<ConfigurationPropertyMap> propertiesMaps = loader.getAllConfigurationProperties(fileLocation, ".test.json", "Test");
		
		assertThat(propertiesMaps.size(), is(2));
	}

	@Test
	public void testListConfigurationFiles() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		List<File> files = loader.listConfigurationFiles(fileLocation, ".test.json", "Test");		
		assertThat(files.size(), is(2));
		
		File file = files.get(0);
		
		assertThat(file.getName().equals("test1.test.json") || file.getName().equals("test2.test.json"), is(true));
		
		File testLocation = new File("target/new_conf");
		if(testLocation.exists())
			testLocation.delete();
		
		loader.listConfigurationFiles("target/new_conf", ".test.json", "Test");
		
		testLocation = new File("target/new_conf");
		assertThat(testLocation.exists(), is(true));
		assertThat(testLocation.isDirectory(), is(true));
		testLocation.delete();
	}
	
	@Test
	public void testLoadParametersFromFile() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test1.test.json"));
		assertThat(propertiesMap.size(), is(10));
		assertThat(propertiesMap.containsKey("stringproperty"), is(true));
		assertThat(propertiesMap.get("stringproperty"), is("property"));
		assertThat(propertiesMap.getString("stringproperty"), is("property"));
		assertThat(propertiesMap.containsKey("integerproperty"), is(true));
		assertThat(propertiesMap.get("integerproperty"), is(1));
		assertThat(propertiesMap.containsKey("doubleproperty"), is(true));
		assertThat(propertiesMap.get("doubleproperty"), is(2.00d));
		assertThat(propertiesMap.containsKey("floatproperty"), is(true));
		assertThat(propertiesMap.get("floatproperty").getClass() == Float.class, is(true));
		assertThat(propertiesMap.containsKey("booleanproperty"), is(true));
		assertThat(propertiesMap.get("booleanproperty"), is(true));
		assertThat(propertiesMap.containsKey("specialproperty.binary"), is(true));
		assertThat(propertiesMap.containsKey("longproperty"), is(true));
		assertThat(propertiesMap.get("longproperty"), is(2000000l));
		assertThat(Map.class.isAssignableFrom(propertiesMap.get("specialproperty.map").getClass()), is(true));
		assertThat(propertiesMap.get("specialproperty.binary").getClass() == byte[].class, is(true));
		assertThat(List.class.isAssignableFrom(propertiesMap.get("specialproperty.list").getClass()), is(true));
		assertThat(Set.class.isAssignableFrom(propertiesMap.get("specialproperty.set").getClass()), is(true));
	}

	@Test(expected = ConfigurationException.class)
	public void testDirectoryAsAfile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		loader.listConfigurationFiles(fileLocation+File.separator+"test1.test.json", ".test.json", "Test");		
	}
	
	@Test(expected = ConfigurationException.class)
	public void testLoadInvalidFile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();		
		loader.loadParametersFromFile(new File(fileLocation+File.separator+"test.configuration"));
	}
	
	@Test(expected = ConfigurationException.class)
	public void testLoadNonExistentFile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		loader.loadParametersFromFile(new File(fileLocation+File.separator+"test.json"));
	}

	@Test
	public void testPopulateBean() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test2.test.json"));
	
		TestBean testBean = new TestBean();
		
		propertiesMap.populateBean(testBean, null);
		
		assertThat(testBean.getTestString(), is("String"));
		assertThat(testBean.getTestInt(), is(1));
		assertThat(testBean.getTestDouble(), is(2.00d));
		assertThat(testBean.getTestFloat() == 0, is(false));
		assertThat(testBean.isTestBoolean(), is(true));
		assertThat(testBean.getTestBinary() == null, is(false));
		assertThat(testBean.getTestLong(), is(2000000l));
		assertThat(testBean.getTestChar(), is('c'));
		assertThat(testBean.getTestByte(), is((byte)1));
		assertThat(testBean.getTestShort(), is((short)1));
		assertThat(testBean.getTestBigInteger() == null, is(false));
		
		propertiesMap = loadString("{\"test.testString\":\"String\"}");
		propertiesMap.populateBean(testBean, "test");		
		assertThat(testBean.getTestString(), is("String"));
		
		propertiesMap = loadString("{\"test.testChar\":\"\"}");
		propertiesMap.populateBean(testBean, "test");
		assertThat(testBean.getTestChar(), is('\0'));

		propertiesMap = loadString("{\"test.testChar\":\"    \"}");
		propertiesMap.populateBean(testBean, "test");
		assertThat(testBean.getTestChar(), is('\0'));

		propertiesMap = new ConfigurationPropertyMap();
		propertiesMap.populateBean(testBean, "test");
		assertThat(testBean.getTestChar(), is('\0'));
	}
	
	@Test
	public void testPopulateBeanWithReadOnlyMethod() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test2.test.json"));
	
		TestBeanReadOnly testBean = new TestBeanReadOnly();
		
		propertiesMap.populateBean(testBean, "");
		
		assertThat(testBean.getTestString() == null, is(true));
	}	

	@Test(expected = IllegalArgumentException.class)
	public void testGetWithPrimitiveClassType() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test2.test.json"));
		
		propertiesMap.get("testInt", int.class);
	}
	
	@Test(expected = ConfigurationException.class)
	public void testFileIsNonJsonObject() throws Exception {
		loadString("\"A valid Json\"");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testFileHasUnqualifiedArrayType() throws Exception {
		loadString("{\"anArray\":[1,2,3]}");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testFileHasSpecialTypeWithEmptyObject() throws Exception {
		loadString("{\"abinary\":{\"binary\":{}}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testFileHasEmptyObject() throws Exception {
		loadString("{\"anemptyobject\":{}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testFileHasWronglyFormattedBinary() throws Exception {
		loadString("{\"abinary\":{\"binary\":\"Not a valid Binary\"}}");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testFileHasInvalidBinary() throws Exception {
		loadString("{\"abinary\":{\"binary\":23}}");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testFileHasInvalidMap() throws Exception {
		loadString("{\"amap\":{\"map\":23}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testFileHasInvalidList() throws Exception {
		loadString("{\"alist\":{\"list\":23}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testFileHasInvalidSet() throws Exception {
		loadString("{\"aset\":{\"set\":23}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testFileHasInvalidSpecialObject() throws Exception {
		loadString("{\"amap\":{\"long\":23}}");
	}

	@Test(expected = ConfigurationException.class)
	public void testGetLongWithInvalidLong() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"long\":\"300000000000\"}");
		map.getLong("long");
	}

	@Test(expected = ConfigurationException.class)
	public void testGetIntWithInvalidInt() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"int\":\"300000000000\"}");
		map.getInt("int");
	}

	@Test(expected = ConfigurationException.class)
	public void testGetBooleanWithInvalidBoolean() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"boolean\":\"300000000000\"}");
		map.getBoolean("boolean");
	}
	
	@Test(expected = ConfigurationException.class)
	public void testGetDoubleWithInvalidDouble() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"double\":\"300000000000\"}");
		map.getDouble("double");
	}

	@Test(expected = ConfigurationException.class)
	public void testGetFloatWithInvalidFloat() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"float\":\"300000000000\"}");
		map.getFloat("float");
	}

	@Test(expected = ConfigurationException.class)
	public void testGetWithClassThatHasNoStringConstructor() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"date\":\"10/11/2017\"}");
		map.get("date", TestBean.class);
	}
	
	@Test
	public void testGetNullPrimitiveOrStringOrBytes() throws Exception {
		ConfigurationPropertyMap map = new ConfigurationPropertyMap();
		
		assertThat(map.getInt("non existent"), is(0));
		assertThat(map.getBoolean("non existent"), is(false));
		assertThat(map.getDouble("non existent"), is(0d));
		assertThat(map.getFloat("non existent"), is((float)0));
		assertThat(map.getLong("non existent"), is(0l));
		assertThat(map.get("non existent", String.class) == null, is(true));
	}
	
	private ConfigurationPropertyMap loadString(String string) throws IOException {
		return new AppBasePropertiesLoader().loadParametersFromReader("test", new StringReader(string));
	}
}
