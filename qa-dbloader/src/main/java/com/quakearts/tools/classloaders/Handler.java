package com.quakearts.tools.classloaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.quakearts.tools.classloaders.hibernate.JarFileEntry;

/**Inner class for creating a {@linkplain URLStreamHandler} for resource URLs returned by this classloader
 * @author kwakutwumasi-afriyie
 *
 */
public class Handler extends URLStreamHandler {

	private static Map<String, DBJarClassLoader> classLoaders = new ConcurrentHashMap<>(); 
	
	protected static void registerClassLoader(DBJarClassLoader classLoader) {
		classLoaders.put(classLoader.getDomain()==null?"null":classLoader.getDomain(), classLoader);
	}

	protected static void unregisterClassLoader(DBJarClassLoader classLoader) {
		classLoaders.remove(classLoader.getDomain()==null?"null":classLoader.getDomain());
	}

	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		DBJarClassLoader classLoader = classLoaders.get(u.getHost());
		if(classLoader != null && !u.getPath().isEmpty()){
			String file = u.getPath().substring(1);
			JarFileEntry jarFileEntry = classLoader.loadEntry(file);
			if(jarFileEntry != null) {
				InputStream inputStream = classLoader.getStream(jarFileEntry);
				return new BytesUrlConnection(inputStream, u);				
			}
		}
		
		throw new IOException("URL "+u+" cannot be found");
	}

	/**Inner class for creating a {@linkplain URLConnection} for resource URLs returned by this classloader
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static class BytesUrlConnection extends URLConnection {
		InputStream inputStream;
		public BytesUrlConnection(InputStream inputStream, URL u) {
			super(u);
			this.inputStream = inputStream;
		}
		
		@Override
		public void connect() throws IOException {
			//Do nothing
		}
		
		@Override
		public InputStream getInputStream() throws IOException {
			return inputStream;
		}
	}	
}
