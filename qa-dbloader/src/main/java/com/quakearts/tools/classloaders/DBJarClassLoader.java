/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.tools.classloaders;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipInputStream;
import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.tools.classloaders.utils.UtilityMethods;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;

/**A classloader that uses JPA to load and store class files. 
 * Useful for projects that may require dynamic deployment of 
 * plugin-like operations.
 * @author kwakutwumasi-afriyie
 *
 */
public class DBJarClassLoader extends ClassLoader {

	private static final ConcurrentHashMap<Long, byte[]> CACHED_JARS = new ConcurrentHashMap<>();
	private String domain;
	
	/**Constructor that uses the current thread context classloader as it's parent
	 * 
	 */
	public DBJarClassLoader() {
		super(Thread.currentThread().getContextClassLoader());
	}
	
	/**Constructor that uses the current thread context classloader as it's parent
	 * and passes the domain to the underlying {@linkplain DataStore}
	 * @param domain the name of the domain to load
	 */
	public DBJarClassLoader(String domain) {
		super(Thread.currentThread().getContextClassLoader());
		this.domain = domain;
	}
	
	/**Get a class from storage using the passed in name
	 * @param name the name of the class in persistent storage
	 * @return The class
	 * @throws ClassNotFoundException if the class cannot be found in persistent storage
	 */
	public Class<?> getDBJarClass(String name) throws ClassNotFoundException{
		return loadClass(name, true);
	}
	
	@Override
	protected Class<?> findClass(String className) throws ClassNotFoundException {
		try{
			JarFileEntry classObject = loadEntry(className.replace(".", "/") +".class");
			if(classObject==null)
				throw new ClassNotFoundException("Class "+className+" does not exist in database");
			else
				return defineClass(classObject,className);
		}catch (DataStoreException e) {
			throw new ClassNotFoundException("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles finding class in database.",e);
		} catch (IOException e) {
			throw new ClassNotFoundException("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles loading class from jar stream.",e);
		}
	}

	private JarFileEntry loadEntry(String entryName) {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		return store.get(JarFileEntry.class, entryName);
	}

	private byte[] getBytes(JarFileEntry jarFileEntry) throws IOException{
		ByteArrayInputStream in;
		byte[] cachedJar;
		if((cachedJar = CACHED_JARS.get(jarFileEntry.getJarId()))!=null)
			in = new ByteArrayInputStream(cachedJar);
		else
			in = new ByteArrayInputStream(jarFileEntry.getJarFile().getJarData());

		ZipInputStream jarStream = new ZipInputStream(in);
		String entryFileName = jarFileEntry.getId();
		if(UtilityMethods.findZipEntry(entryFileName, jarStream)==null)
			throw new IOException("No such jar entry in jarfile: "+entryFileName);
		else{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			for(int c=jarStream.read();c!=-1;c=jarStream.read())
				bos.write(c);
			
			return bos.toByteArray();
		}
	}

	private Class<?> defineClass(JarFileEntry classObject, String className) throws IOException {
		byte[] classBytes = getBytes(classObject);
		return defineClass(className, classBytes, 0, classBytes.length);
	}

	@Override
	protected URL findResource(String name) {
		
		byte[] bytes;		
		try {
			JarFileEntry entry = loadEntry(name);
			if(entry==null)
				return null;
			
			bytes = getBytes(entry);
			return new URL(null, "jardbresource://jar.file.id"+entry.getJarId()+"/"+name, new BytesUrlStreamHandler(bytes));
		} catch (IOException e1) {
			return null;
		}
	}

	/**Getter for domain
	 * @return the configured domain
	 */
	public String getDomain() {
		return domain;
	}

	/**Setter for domain
	 * @param domain the new/updated domain to use
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**Inner class for creating a {@linkplain URLStreamHandler} for resource URLs returned by this classloader
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static class BytesUrlStreamHandler extends URLStreamHandler {
		byte[] bytes;
		public BytesUrlStreamHandler(byte[] bytes) {
			this.bytes = bytes;
		}
		protected URLConnection openConnection(URL u) throws IOException {
			return new BytesUrlConnection(bytes, u);
		}
	}

	/**Inner class for creating a {@linkplain URLConnection} for resource URLs returned by this classloader
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static class BytesUrlConnection extends URLConnection {
		byte[] bytes = null;
		public BytesUrlConnection(byte[] bytes, URL u) {
			super(u);
			this.bytes = bytes;
		}
		
		@Override
		public void connect() throws IOException {
			//Do nothing
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(bytes);
		}
	}	
}
