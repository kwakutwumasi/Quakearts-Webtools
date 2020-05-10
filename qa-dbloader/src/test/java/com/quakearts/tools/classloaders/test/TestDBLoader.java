package com.quakearts.tools.classloaders.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Stack;

import org.junit.Test;

import com.quakearts.tools.classloaders.DBJarClassLoader;
import com.quakearts.tools.classloaders.hibernate.JarFile;
import com.quakearts.tools.classloaders.utils.EventType;
import com.quakearts.tools.classloaders.utils.FileStorageEvent;
import com.quakearts.tools.classloaders.utils.JarFileStorer;
import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;
import com.quakearts.webapp.orm.DataStoreFactory;

public class TestDBLoader {
	
	@Test
	public void test() throws Exception {
		Stack<FileStorageEvent> events = new Stack<>(); 
		DBJarClassLoader classLoader = new DBJarClassLoader();
		
		JarFileStorer jarFileStorer = new JarFileStorer()
				.addListener(events::push);
		byte[] jarBytes;
		jarBytes = readFileBytes("test-0.0.1.jar");
		
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.1.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		assertThat(events.size(), is(1));
		FileStorageEvent event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.1.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		
		Class<?> clazz;
		try {
			clazz = Class.forName("test.Elements", true, classLoader);
		} catch (Exception e) {
			fail("Unable to load class: "+e.getMessage());
			return;
		}
		
		assertThat(clazz.getName(), is("test.Elements"));
		
		Class<?> clazz2;
		try {
			clazz2 = Class.forName("test.Elements", true, classLoader);
		} catch (Exception e) {
			fail("Unable to load class: "+e.getMessage());
			return;
		}
		
		assertThat(clazz2 == clazz, is(true));
				
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.1.a.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();		

		assertThat(jarFileStorer.getSummary("<br />").contains("No files were stored.<br />"), is(true));
		assertThat(events.isEmpty(), is(true));
		
		jarBytes = readFileBytes("test-0.0.2.jar");
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.2.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		URL url = classLoader.getResource("test/test.properties");
		assertThat(url != null, is(true));
		
		InputStream is = url.openStream();

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			assertThat(reader.readLine(), is("test=property"));			
		}
		
		is = classLoader.getResourceAsStream("test/test.properties");
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			assertThat(reader.readLine(), is("test=property"));			
		}
		
		assertThat(events.size(), is(2));
		event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/test.properties"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.2.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		
		event = events.pop();
		assertThat(event.getType(), is(EventType.UPDATEDFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.2.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));

		jarBytes = readFileBytes("test-0.0.3.jar");
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.3.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		assertThat(events.size(), is(2));
		event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Producer.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.3.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		event = events.pop();
		assertThat(event.getType(), is(EventType.UPDATEDFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.3.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		CurrentSessionContextHelper.closeOpenSessions();
	}
	
	@Test
	public void testWithDomain() throws Exception {
		Stack<FileStorageEvent> events = new Stack<>(); 
		DBJarClassLoader classLoader = new DBJarClassLoader("test");
		
		JarFileStorer jarFileStorer = new JarFileStorer("test")
				.addListener(events::push);
		byte[] jarBytes;
		jarBytes = readFileBytes("test-0.0.1.jar");
		
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.1.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		assertThat(events.size(), is(1));
		FileStorageEvent event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.1.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		
		Class<?> clazz;
		try {
			clazz = Class.forName("test.Elements", true, classLoader);
		} catch (Exception e) {
			fail("Unable to load class: "+e.getMessage());
			return;
		}
		
		assertThat(clazz.getName(), is("test.Elements"));
		
		Class<?> clazz2;
		try {
			clazz2 = Class.forName("test.Elements", true, classLoader);
		} catch (Exception e) {
			fail("Unable to load class: "+e.getMessage());
			return;
		}
		
		assertThat(clazz2 == clazz, is(true));
				
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.1.a.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();		

		assertThat(jarFileStorer.getSummary("<br />").contains("No files were stored.<br />"), is(true));
		assertThat(events.isEmpty(), is(true));
		
		jarBytes = readFileBytes("test-0.0.2.jar");
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.2.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		URL url = classLoader.getResource("test/test.properties");
		assertThat(url != null, is(true));
		
		InputStream is = url.openStream();

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			assertThat(reader.readLine(), is("test=property"));			
		}
		
		is = classLoader.getResourceAsStream("test/test.properties");
		
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(is))){
			assertThat(reader.readLine(), is("test=property"));			
		}
		
		assertThat(events.size(), is(2));
		event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/test.properties"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.2.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		
		event = events.pop();
		assertThat(event.getType(), is(EventType.UPDATEDFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.2.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));

		jarBytes = readFileBytes("test-0.0.3.jar");
		jarFileStorer.storeJarFile(jarBytes, "test-0.0.3.jar");
		DataStoreFactory.getInstance().getDataStore().flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		assertThat(events.size(), is(2));
		event = events.pop();
		assertThat(event.getType(), is(EventType.NEWFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Producer.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.3.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		event = events.pop();
		assertThat(event.getType(), is(EventType.UPDATEDFILE));
		assertThat(event.getJarFileEntry().getId(), is("test/Elements.class"));
		assertThat(event.getJarFileEntry().getJarFile().getJarName(), is("test-0.0.3.jar"));
		assertThat(event.getJarFileEntry().getJarFile().getJarData(), is(jarBytes));
		CurrentSessionContextHelper.closeOpenSessions();	
	}

	@Test
	public void testCleanOrphanJars() throws Exception {
		JarFileStorer fileStorer = new JarFileStorer("super");
		fileStorer.storeJarFile(readFileBytes("test-replace-0.0.1.jar"), "test-replace-0.0.1.jar");
		CurrentSessionContextHelper.closeOpenSessions();
		fileStorer.storeJarFile(readFileBytes("test-replace-0.0.2.jar"), "test-replace-0.0.2.jar");		
		CurrentSessionContextHelper.closeOpenSessions();
		
		JarFile[] deletedFiles = fileStorer.cleanOrphanJars();
		assertThat(deletedFiles.length, is(1));
		assertThat(deletedFiles[0].getJarName(), is("test-replace-0.0.1.jar"));
		
		try {
			new JarFileStorer().cleanOrphanJars();
		} catch (Exception e) {
			fail("Error cleaning jars for default database");
		}
	}
	
	@Test
	public void testDifferentClassLoaders() throws Exception {
		JarFileStorer fileStorer = new JarFileStorer("test");
		fileStorer.storeJarFile(readFileBytes("test-0.0.4.jar"), "test-0.0.4.jar");
		CurrentSessionContextHelper.closeOpenSessions();
		DBJarClassLoader classLoader = new DBJarClassLoader("test");
		Class<?> elementsClass = classLoader.loadClass("test.Multiple");
		DBJarClassLoader classLoader2 = new DBJarClassLoader("test");
		Class<?> elementsClass2 = classLoader2.loadClass("test.Multiple");
		assertThat(elementsClass != elementsClass2, is(true));
		CurrentSessionContextHelper.closeOpenSessions();
	}

	@Test
	public void testParentClassloader() throws Exception {
		JarFileStorer fileStorer = new JarFileStorer();
		fileStorer.storeJarFile(readFileBytes("test-super-0.0.1.jar"), "test-super-0.0.1.jar", 
				DataStoreFactory.getInstance().getDataStore("super"));
		fileStorer.storeJarFile(readFileBytes("test-child-0.0.1.jar"), "test-child-0.0.1.jar");
		DBJarClassLoader classLoader1 = new DBJarClassLoader("super");
		DBJarClassLoader classLoader2 = new DBJarClassLoader(classLoader1);
		try {			
			classLoader2.getDBJarClass("test.LoadChild");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		fileStorer.storeJarFile(readFileBytes("test-child-0.0.1.jar"), "test-child-0.0.1.jar", 
				DataStoreFactory.getInstance().getDataStore("test"));
		DBJarClassLoader classLoader3 = new DBJarClassLoader("test", classLoader1);
		try {
			classLoader3.getDBJarClass("test.LoadChild");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		CurrentSessionContextHelper.closeOpenSessions();
	}
	
	private byte[] readFileBytes(String fileName) throws IOException, FileNotFoundException {
		byte[] jarBytes;
		try(FileInputStream fis = new FileInputStream("testjars"+File.separator+fileName);){
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			int read;
			while ((read = fis.read())!=-1) {
				stream.write(read);
			}
			jarBytes = stream.toByteArray();
		}
		return jarBytes;
	}
}
