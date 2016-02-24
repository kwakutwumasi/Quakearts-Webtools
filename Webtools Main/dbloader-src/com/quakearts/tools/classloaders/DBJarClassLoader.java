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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.quakearts.tools.classloaders.hibernate.JarFileEntry;
import com.quakearts.tools.classloaders.utils.UtilityMethods;
import com.quakearts.webapp.hibernate.HibernateHelper;

public class DBJarClassLoader extends ClassLoader {

	private static final ConcurrentHashMap<Long, byte[]> CACHED_JARS = new ConcurrentHashMap<Long, byte[]>();
	private String domain;
	
	public DBJarClassLoader() {
		super(Thread.currentThread().getContextClassLoader());
	}
	
	public DBJarClassLoader(String domain) {
		super(Thread.currentThread().getContextClassLoader());
		this.domain = domain;
	}
	
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
		}catch (HibernateException e) {
			throw new ClassNotFoundException("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles finding class in database.",e);
		} catch (IOException e) {
			throw new ClassNotFoundException("Exception of type " + e.getClass().getName()
					+ " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles loading class from jar stream.",e);
		}
	}

	private JarFileEntry loadEntry(String entryName) throws HibernateException, IOException {
		Session session;
		if(domain==null)
			session = HibernateHelper.getCurrentSession();
		else
			session = HibernateHelper.getSession(domain);
		return (JarFileEntry)session.get(JarFileEntry.class,entryName);
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static class BytesUrlStreamHandler extends URLStreamHandler {
		byte[] bytes;
		public BytesUrlStreamHandler(byte[] bytes) {
			this.bytes = bytes;
		}
		protected URLConnection openConnection(URL u) throws IOException {
			return new BytesUrlConnection(bytes, u);
		}
	}

	public static class BytesUrlConnection extends URLConnection {
		byte[] bytes = null;
		public BytesUrlConnection(byte[] bytes, URL u) {
			super(u);
			this.bytes = bytes;
		}
		
		public void connect() throws IOException {
		}
		
		public InputStream getInputStream() throws IOException {
			return new ByteArrayInputStream(bytes);
		}
	}	
}
