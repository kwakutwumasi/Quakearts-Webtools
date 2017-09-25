package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.util.List;
import org.junit.Test;

import com.quakearts.appbase.internal.properties.AppBasePropertiesLoader;
import com.quakearts.appbase.internal.properties.ConfigurationPropertyMap;

public class TestAppBasePropertiesLoader {

	String fileLocation = "src"+File.separator+"test"+File.separator+"resources";
	
	@Test
	public void testGetAllConfigurationProperties() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		List<ConfigurationPropertyMap> propertiesMaps = loader.getAllConfigurationProperties(fileLocation, ".test.json", "Test");
		
		assertThat(propertiesMaps.size(), is(2));
		
		ConfigurationPropertyMap propertiesMap = propertiesMaps.get(0);
		assertThat(propertiesMap.size(), is(7));
		assertThat(propertiesMap.containsKey("stringproperty"), is(true));
		assertThat(propertiesMap.get("stringproperty"), is("property"));
		assertThat(propertiesMap.containsKey("integerproperty"), is(true));
		assertThat(propertiesMap.get("integerproperty"), is(1));
		assertThat(propertiesMap.containsKey("doubleproperty"), is(true));
		assertThat(propertiesMap.get("doubleproperty"), is(2.00d));
		assertThat(propertiesMap.containsKey("floatproperty"), is(true));
		assertThat(propertiesMap.get("floatproperty").getClass() == Float.class, is(true));
		assertThat(propertiesMap.containsKey("booleanproperty"), is(true));
		assertThat(propertiesMap.get("booleanproperty"), is(true));
		assertThat(propertiesMap.containsKey("specialproperty.binary"), is(true));
		assertThat(propertiesMap.get("specialproperty.binary").getClass() == byte[].class, is(true));
		assertThat(propertiesMap.containsKey("specialproperty.long"), is(true));
		assertThat(propertiesMap.get("specialproperty.long"), is(2000000l));
	}
	

	@Test
	public void testListConfigurationFiles() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		List<File> files = loader.listConfigurationFiles(fileLocation, ".test.json", "Test");		
		assertThat(files.size(), is(2));
		
		File file = files.get(0);
		
		assertThat(file.getName().equals("test1.test.json") || file.getName().equals("test2.test.json"), is(true));
	}
	
	@Test
	public void testLoadParametersFromFile() {
		AppBasePropertiesLoader loader = new AppBasePropertiesLoader();
		
		ConfigurationPropertyMap propertiesMap = loader.loadParametersFromFile(new File(fileLocation+File.separator+"test1.test.json"));
		assertThat(propertiesMap.size(), is(7));
		assertThat(propertiesMap.containsKey("stringproperty"), is(true));
		assertThat(propertiesMap.get("stringproperty"), is("property"));
		assertThat(propertiesMap.containsKey("integerproperty"), is(true));
		assertThat(propertiesMap.get("integerproperty"), is(1));
		assertThat(propertiesMap.containsKey("doubleproperty"), is(true));
		assertThat(propertiesMap.get("doubleproperty"), is(2.00d));
		assertThat(propertiesMap.containsKey("floatproperty"), is(true));
		assertThat(propertiesMap.get("floatproperty").getClass() == Float.class, is(true));
		assertThat(propertiesMap.containsKey("booleanproperty"), is(true));
		assertThat(propertiesMap.get("booleanproperty"), is(true));
		assertThat(propertiesMap.containsKey("specialproperty.binary"), is(true));
		assertThat(propertiesMap.get("specialproperty.binary").getClass() == byte[].class, is(true));
		assertThat(propertiesMap.containsKey("specialproperty.long"), is(true));
		assertThat(propertiesMap.get("specialproperty.long"), is(2000000l));	
	}

}
