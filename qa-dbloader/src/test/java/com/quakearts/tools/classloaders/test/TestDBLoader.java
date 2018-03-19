package com.quakearts.tools.classloaders.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.Test;

import com.quakearts.tools.classloaders.DBJarClassLoader;
import com.quakearts.tools.classloaders.hibernate.JarFile;
import com.quakearts.tools.classloaders.utils.JarFileStorer;
import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;
import com.quakearts.webapp.orm.DataStoreFactory;

public class TestDBLoader {

	@Test
	public void test() throws Exception {
		DBJarClassLoader classLoader = new DBJarClassLoader();
		
		JarFileStorer jarFileStorer = new JarFileStorer();
		byte[] jarBytes;
		try(FileInputStream fis = new FileInputStream("testjar.jar");){
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			int read;
			while ((read = fis.read())!=-1) {
				stream.write(read);
			}
			jarBytes = stream.toByteArray();
		}
		
		jarFileStorer.storeJarFile(jarBytes, "testjar.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		Class<?> testClass = Class.forName("com.quakearts.tools.classloaders.test.TestInterfaceImpl", true, classLoader);
		Object objectClass = testClass.newInstance();
		assertThat(objectClass instanceof TestInterface, is(true));
		
		TestInterface testInterface = (TestInterface) objectClass;
		assertThat(testInterface.testMethod(), is("Worked"));
		
		URL url = classLoader
				.getResource("META-INF/services/com.quakearts.tools.classloaders.test.TestInterface");
		assertThat(url != null, is(true));
		
		InputStream is = classLoader
				.getResourceAsStream("META-INF/services/com.quakearts.tools.classloaders.test.TestInterface");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		assertThat(reader.readLine(), is("com.quakearts.tools.classloaders.test.TestInterfaceImpl"));
		
		jarFileStorer.storeJarFile(jarBytes, "testjarV2.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		JarFile[] deleted = jarFileStorer.cleanOrphanJars();
		
		assertThat(deleted.length, is(1));
		assertThat(deleted[0].getJarName(), is("testjar.jar"));
		
		CurrentSessionContextHelper.closeOpenSessions();
	}
}
