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
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarInputStream;

import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;
import static com.quakearts.tools.classloaders.utils.UtilityMethods.findZipEntry;
/**A classloader that uses JPA to load and store class files. 
 * Useful for projects that may require dynamic deployment of 
 * plugin-like operations.
 * @author kwakutwumasi-afriyie
 *
 */
public class DBJarClassLoader extends ClassLoader implements AutoCloseable {

	private static final String JAVA_PROTOCOL_HANDLER_PKGS = "java.protocol.handler.pkgs";
	private static final ConcurrentHashMap<String, byte[]> CACHED_JARS = new ConcurrentHashMap<>();
	private String domain;
	
	static {
		String existingHandlers = System.getProperties().contains(JAVA_PROTOCOL_HANDLER_PKGS)
				?System.getProperties().getProperty(JAVA_PROTOCOL_HANDLER_PKGS)+"|":"";
		existingHandlers+="com.quakearts.tools";
		System.setProperty(JAVA_PROTOCOL_HANDLER_PKGS, existingHandlers);
	}
	
	/**Constructor that uses the current thread context classloader as it's parent
	 * 
	 */
	public DBJarClassLoader() {
		this(null, Thread.currentThread().getContextClassLoader());
	}
		
	/**Constructor that uses the supplied classloader as it's parent
	 * @param parent the Classloader to use as parent
	 */
	public DBJarClassLoader(ClassLoader parent){
		this(null, parent);
	}
	
	/**Constructor that uses the current thread context classloader as it's parent
	 * and passes the domain to the underlying {@linkplain DataStore}
	 * @param domain the name of the domain to load
	 */
	public DBJarClassLoader(String domain) {
		this(domain, Thread.currentThread().getContextClassLoader());
	}

	/**Constructor that uses the supplied classloader as it's parent
	 * and passes the domain to the underlying {@linkplain DataStore}
	 * @param domain the name of the domain to load
	 * @param parent the Classloader to use as parent
	 */
	public DBJarClassLoader(String domain, ClassLoader parent) {
		super(parent);
		this.domain = domain;
		Handler.registerClassLoader(this);
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
		try {
			JarFileEntry classObject = loadEntry(className.replace(".", "/") +".class");
			if(classObject==null)
				throw new ClassNotFoundException("Class "+className+" does not exist in database");
			else
				return defineClass(classObject,className);
		} catch (DataStoreException | IOException e) {
			throw new ClassNotFoundException("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles finding class in database.",e);
		}
	}

	protected JarFileEntry loadEntry(String entryName) {
		DataStore store;
		if(domain==null)
			store = DataStoreFactory.getInstance().getDataStore();
		else
			store = DataStoreFactory.getInstance().getDataStore(domain);
		return store.get(JarFileEntry.class, entryName);
	}

	private byte[] getBytes(JarFileEntry jarFileEntry) throws IOException{
		JarInputStream jarStream = getStream(jarFileEntry);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		for(int c=jarStream.read();c!=-1;c=jarStream.read())
			bos.write(c);
		
		return bos.toByteArray();
	}

	protected JarInputStream getStream(JarFileEntry jarFileEntry) throws IOException {
		ByteArrayInputStream in;
		byte[] cachedJar;
		String cachedJarKey = domainToString()+jarFileEntry.getJarId();
		cachedJar = CACHED_JARS.computeIfAbsent(cachedJarKey, 
				key->jarFileEntry.getJarFile().getJarData());
		in = new ByteArrayInputStream(cachedJar);
		
		JarInputStream jarStream = new JarInputStream(in);
		String entryFileName = jarFileEntry.getId();
		if(findZipEntry(entryFileName, jarStream)==null)
			throw new IOException("No such jar entry in jarfile: "+entryFileName);
		return jarStream;
	}

	private Class<?> defineClass(JarFileEntry classObject, String className) throws IOException {
		byte[] classBytes = getBytes(classObject);
		return defineClass(className, classBytes, 0, classBytes.length);
	}

	@Override
	protected URL findResource(String name) {		
		try {
			JarFileEntry entry = loadEntry(name);
			if(entry == null)
				return null;
			
			String url = domain+"/"+name;
			return new URL("classloaders://"+url);
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
	
	@Override
	public void close() throws IOException {
		Handler.unregisterClassLoader(this);
		CACHED_JARS.entrySet()
			.removeIf(entry->entry.getKey().startsWith(domainToString()));
	}

	private String domainToString() {
		return domain==null?"null":domain;
	}
}
