package com.quakearts.tools.classloaders;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarInputStream;

/**Inner class for creating a {@linkplain URLStreamHandler} for resource URLs returned by this classloader
 * @author kwakutwumasi-afriyie
 *
 */
public class Handler extends URLStreamHandler {

	private static final ConcurrentHashMap<String, InputStream> CACHED_STREAMS 
		= new ConcurrentHashMap<>();

	@Override
	protected URLConnection openConnection(URL u) throws IOException {
		InputStream inputStream = CACHED_STREAMS.remove(u.getHost()+u.getPath());
		if(inputStream != null)
			return new BytesUrlConnection(inputStream, u);
		else
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

	static void cache(String id, JarInputStream inputStream){
		CACHED_STREAMS.put(id, inputStream);
	}
}
