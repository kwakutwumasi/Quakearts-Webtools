package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;
import com.quakearts.appbase.internal.properties.impl.AppBasePropertiesLoaderImpl;
import com.quakearts.appbase.test.helpers.TestBean;
import com.quakearts.appbase.test.helpers.TestBeanReadOnly;
import com.quakearts.appbase.test.helpers.rules.SystemEnvironmentRule;

public class TestAppBasePropertiesLoader {

	String fileLocation = "src"+File.separator+"test"+File.separator+"resources";
	
	@Test
	public void testGetAllConfigurationProperties() {		
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		List<ConfigurationPropertyMap> propertiesMaps = loader.getAllConfigurationProperties(fileLocation, ".test.json", "Test");
		assertThat(propertiesMaps.size(), is(2));
	}

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();
	
	@Test
	public void testListConfigurationFiles() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		
		List<File> files = loader.listConfigurationFiles(fileLocation, ".test.json", "Test");		
		assertThat(files.size(), is(2));
		
		File file = files.get(0);
		
		assertThat(file.getName().equals("test1.test.json") || file.getName().equals("test2.test.json"), is(true));		
		loader.listConfigurationFiles(temporaryFolder.getRoot().getPath()+"target"+File.separator+"new_conf", ".test.json", "Test");
		
		File testLocation = new File(temporaryFolder.getRoot().getPath()+"target"+File.separator+"new_conf");
		assertThat(testLocation.exists(), is(true));
		assertThat(testLocation.isDirectory(), is(true));
	}
	
	@Test
	public void testLoadParametersFromFile() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		
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

	@Rule
	public SystemEnvironmentRule systemEnv = new SystemEnvironmentRule();
	
	@Test
	public void testLoadParametersFromEnvironment() throws Exception {
		systemEnv.set("test.variable").as("variable")
				.set("test.variableTwo").as(":123")
				.set("test.variable3").as("{\"test\":\"value\"}")
				.set("test_variable4").as("variable4")
				.set("test_variable_five").as("variableFive")
				.set("TEST_VARIABLE_SIX").as("variableSix")
				.set("TEST_VARIABLESEVEN").as("variableseven")
				.set("TEST_VARIABLE__EIGHT").as("variableeight")
				.set("TEST_VARIABLE__LAST_NINE").as("variablenine");
		
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		
		ConfigurationPropertyMap map1 = loader.loadParametersFromEnvironment("TEST");
		ConfigurationPropertyMap map2 = loader.loadParametersFromEnvironment("TEST");
		
		assertThat(map1, is(map2));
		
		assertThat(map1.size(), is(9));
		assertThat(map1.containsKey("variable"), is(true));
		assertThat(map1.getString("variable"), is("variable"));
		assertThat(map1.containsKey("variableTwo"), is(true));
		assertThat(map1.getInt("variableTwo"), is(123));
		assertThat(map1.containsKey("variable3"), is(true));
		assertThat(map1.getSubConfigurationPropertyMap("variable3"), is(notNullValue()));
		assertThat(map1.getSubConfigurationPropertyMap("variable3").containsKey("test"), is(true));
		assertThat(map1.getSubConfigurationPropertyMap("variable3").getString("test"), is("value"));
		assertThat(map1.containsKey("variable4"), is(true));
		assertThat(map1.getString("variable4"), is("variable4"));
		assertThat(map1.containsKey("variableFive"), is(true));
		assertThat(map1.getString("variableFive"), is("variableFive"));
		assertThat(map1.containsKey("variableSix"), is(true));
		assertThat(map1.getString("variableSix"), is("variableSix"));
		assertThat(map1.containsKey("variableseven"), is(true));
		assertThat(map1.getString("variableseven"), is("variableseven"));
		assertThat(map1.getString("variable.eight"), is("variableeight"));
		assertThat(map1.getString("variable.lastNine"), is("variablenine"));
	}
	
	@Test
	public void testLoadParametersFromEnvironmentWithNoVariables() throws Exception {
		try {
			systemEnv.clearAll();
			AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
			ConfigurationPropertyMap map1 = loader.loadParametersFromEnvironment("TEST");
			assertThat(map1.isEmpty(), is(true));
		} finally {
			systemEnv.reset();
		}
	}
	
	@Test(expected=ConfigurationException.class)
	public void testLoadParametersFromEnvironmentWithVariableEqualPrefix() throws Exception {
		try {
			systemEnv.set("test").as("variable");
			
			new AppBasePropertiesLoaderImpl().loadParametersFromEnvironment("TEST");
		} finally {
			systemEnv.reset();
		}
	}
	
	@Test
	public void testLoadParametersFromEnvironmentWithVariableEqualNull() throws Exception {
		try {
			systemEnv.set("test.variable1").as("");
			
			ConfigurationPropertyMap map1 = new AppBasePropertiesLoaderImpl().loadParametersFromEnvironment("TEST");
			assertThat(map1.containsKey("variable1"), is(true));
			assertThat(map1.getString("variable1"), is(""));
		} finally {
			systemEnv.reset();
		}
	}
	
	@Test(expected = ConfigurationException.class)
	public void testDirectoryAsAfile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		
		loader.listConfigurationFiles(fileLocation+File.separator+"test1.test.json", ".test.json", "Test");		
	}
	
	@Test(expected = ConfigurationException.class)
	public void testLoadInvalidFile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();		
		loader.loadParametersFromFile(new File(fileLocation+File.separator+"test.configuration"));
	}
	
	@Test(expected = ConfigurationException.class)
	public void testLoadNonExistentFile() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();
		loader.loadParametersFromFile(new File(fileLocation+File.separator+"test.json"));
	}

	@Test
	public void testPopulateBean() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();		
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
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test2.test.json"));
	
		TestBeanReadOnly testBean = new TestBeanReadOnly();
		
		propertiesMap.populateBean(testBean, "");
		
		assertThat(testBean.getTestString() == null, is(true));
	}	

	@Test(expected = IllegalArgumentException.class)
	public void testGetWithPrimitiveClassType() throws Exception {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoaderImpl();		
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

	@Test
	public void testFileHasInvalidSpecialObject() throws Exception {
		ConfigurationPropertyMap map = loadString("{\"amap\":{\"long\":23}}");
		
		assertThat(map.size(), is(1));
		assertThat(map.containsKey("amap"), is(true));
		assertThat(map.getSubConfigurationPropertyMap("amap"), is(notNullValue()));
		ConfigurationPropertyMap submap = map.getSubConfigurationPropertyMap("amap");
		assertThat(submap.size(), is(1));
		assertThat(submap.containsKey("long"), is(true));
		assertThat(submap.getInt("long"), is(23));
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
		return new AppBasePropertiesLoaderImpl().loadParametersFromReader("test", new StringReader(string));
	}
}
