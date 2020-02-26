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
package com.quakearts.rest.client;

import static com.quakearts.rest.client.HttpVerb.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.rest.client.exception.HttpClientException;

/**Base class for creating HTTP clients
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class HttpClient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4822817405423965774L;
	private static final String LOCALHOST = "localhost";
	private static final String LOOPBACK = "127.0.0.1";
	protected static final Logger log = LoggerFactory.getLogger(HttpClient.class);
	int port;
	String host;
	String username;
	String password;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
				"DDD, dd MMM yyyy HH:mm:ss");
	transient HttpCookie defaultCookie;
	boolean secured;
	String userAgent;
	boolean followRedirects;
	boolean matchHostname;
	transient CookieManager cookieManager = new CookieManager();
	int connectTimeout;
	int readTimeout;
	String charset = "UTF-8";
	String acceptLanguage = "en-US";
	
	/**
	 * @return the configured port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the configured host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the default cookie to send during HTTP request processing or the first cookie returned during response processing
	 */
	public HttpCookie getDefaultCookie() {
		return defaultCookie;
	}

	/**
	 * @return true if the HTTP request is secure
	 */
	public boolean isSecured() {
		return secured;
	}

	/**
	 * @return the Basic user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the Basic password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the configured user agent string
	 */
	public String getUserAgent() {
		return userAgent;
	}
	
	/**
	 * @return true if the client will follow redirects
	 */
	public boolean followsRedirects() {
		return followRedirects;
	}
	
	/**
	 * @return true if hostnames must match certificate names. Used only if secured HTTP is in use
	 */
	public boolean matchesHostnames() {
		return matchHostname;
	}

	/**
	 * @return the list of cookies returned during processing
	 */
	public List<HttpCookie> getCookies(){
		return cookieManager.getCookieStore().getCookies();
	}
	
	/**
	 * @return the configured Cookie Manager
	 */
	public CookieManager getCookieManager() {
		return cookieManager;
	}
	
	/** Implementers can call this method to send an HTTP request
	 * @param file the HTTP file element
	 * @param requestValue the HTTP body
	 * @param method the HTTP verb to use (GET, POST, etc)
	 * @param contentType a shortcut for adding an HTTP Content-Type header for content (if present)
	 * @return {@link HttpResponse} the HTTP response
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType) 
			throws IOException, HttpClientException {
		return sendRequest(file, requestValue, method, contentType, null);
	}
		
	/** Implementers can call this method to send an HTTP request
	 * @param file the HTTP file element
	 * @param requestValue the HTTP body
	 * @param method the HTTP verb to use (GET, POST, etc)
	 * @param contentType a shortcut for adding an HTTP Content-Type header for content (if present)
	 * @param additionalHeaders a {@link Map} of a {@link List} of {@link String}s of additional header elements to pass to the request
	 * @return {@link HttpResponse} the HTTP response
	 * @throws IOException if there is an error during communication
	 * @throws HttpClientException if a parameter or process fails
	 */
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType,
			Map<String, List<String>> additionalHeaders) throws IOException, HttpClientException {
		runValidations(file, requestValue, method);
		HttpURLConnection con = prepareConnection(file, requestValue, method, contentType, additionalHeaders);
		int responseCode = connect(con);
		byte[] output = storeOutput(method, con, responseCode);
		storeCookies(con, responseCode);		
		return new HttpResponse(output, con.getResponseMessage(), responseCode, con.getHeaderFields());
	}

	private void runValidations(String file, String requestValue, HttpVerb method) throws HttpClientException {
		if(host == null)
			throw new HttpClientException("Property host has not been set");	

		if(port == 0)
			throw new HttpClientException("Property port has not been set");	

		if(file == null)
			throw new HttpClientException("Parameter file is required");	

		if(method == null)
			throw new HttpClientException("Parameter method is required");	
		
		if(requiringOutputMethodsInclude(method) && requestValue == null) {
			throw new HttpClientException("Http Verb "+method+" requires requestValue");
		}
		log.trace("Sending request to:\n"
				+ "HOST:{}\n"
				+ "PORT:{}\n"
				+ "FILE:{}\n"
				+ "METHOD:{}", 
				host,
				port,
				file,
				method);
	}

	private HttpURLConnection prepareConnection(String file, String requestValue, HttpVerb method, String contentType,
			Map<String, List<String>> additionalHeaders)
			throws IOException, HttpClientException {
		HttpURLConnection con = setupConnection(file);		
		setConnectionProperties(method, con);		
		addUsernameAndPassword(con);
		addGeneralHeaders(con);
		addCookies(con);
		addCustomRequestHeaders(additionalHeaders, con);
		addRequestValue(requestValue, method, contentType, con);
		return con;
	}

	private HttpURLConnection setupConnection(String file) throws IOException {
		HttpURLConnection con;
		if(secured){
			HttpsURLConnection scon = (HttpsURLConnection) new URL("https", host, port, file).openConnection();
			if(matchHostname) {
				scon.setHostnameVerifier((hostname,session) ->  host.matches(LOCALHOST) 
							|| host.matches(LOOPBACK));
			}
			
			con = scon;
		} else {
			con = (HttpURLConnection) new URL("http", host, port, file).openConnection();
		}
		return con;
	}

	private void setConnectionProperties(HttpVerb method, HttpURLConnection con) throws ProtocolException {
		con.setRequestMethod(method.name());
		con.setDoInput(true);
		con.setInstanceFollowRedirects(followRedirects);
		con.setConnectTimeout(connectTimeout);
		con.setReadTimeout(readTimeout);
	}

	private void addUsernameAndPassword(HttpURLConnection con) {
		if(username!=null && password !=null){
			con.addRequestProperty("Authorization", "Basic "+(Base64.getEncoder().encodeToString((username+":"+password).getBytes())));
		}
	}

	private void addGeneralHeaders(HttpURLConnection con) {
		con.addRequestProperty("Accept", "application/json, text/*, application/xml");
		con.addRequestProperty("User-Agent", userAgent==null? "Generic REST Client":userAgent);
		con.addRequestProperty("Date", dateFormat.format(new Date()) + " GMT");
		con.addRequestProperty("Accept-Language", acceptLanguage);
	}

	private void addCookies(HttpURLConnection con) {
		if(defaultCookie!=null)
			con.addRequestProperty("Cookie", defaultCookie.toString());

		for(HttpCookie cookie:cookieManager.getCookieStore().getCookies()) {
			con.addRequestProperty("Cookie", cookie.toString());
		}
	}

	private void addCustomRequestHeaders(Map<String, List<String>> additionalHeaders, HttpURLConnection con) {
		if(additionalHeaders!=null){
			for(Entry<String, List<String>> entry:additionalHeaders.entrySet()){
				for(String value:entry.getValue())
					con.addRequestProperty(entry.getKey(), value);
			}
		}
	}

	private void addRequestValue(String requestValue, HttpVerb method, String contentType, HttpURLConnection con)
			throws HttpClientException, IOException {
		if (requestValue != null && !requestValue.isEmpty()) {
			if(!requiringOutputMethodsInclude(method)
					&& !optionalOutputMethodsInlude(method)) {
				throw new HttpClientException("Http Verb "+method+" cannot have a requestValue");
			}
			String contentLength = Integer.toString((requestValue.length() / 8));
			con.addRequestProperty("Content-Length", contentLength);
			con.addRequestProperty("Content-Type", contentType+"; charset="+charset.toLowerCase());
			con.setDoOutput(true);
			con.getOutputStream().write(requestValue.getBytes(charset));
			log.trace("Request Body:\n"
					+ "Content-Type:{}\n"
					+ "Content-Length:{}\n"
					+ "Content:\n{}\n",
					contentType,
					contentLength,
					requestValue);
		}
	}

	private int connect(HttpURLConnection con) throws IOException {
		int responseCode=0;
		con.connect();
		responseCode = con.getResponseCode();
		log.trace("Response Code:{}", responseCode);
		return responseCode;
	}

	private byte[] storeOutput(HttpVerb method, HttpURLConnection con, int responseCode) throws IOException {
		byte[] output = null;
		InputStream is = null;
		if(responseCode>=200 
				&& responseCode<=299 
				&& returningInputMethodsInclude(method)){
			is = con.getInputStream();
		} else if(responseCode > 399){
			is = con.getErrorStream();
		}
		
		if(is!=null){
			output = processStream(is);
		}		
		return output;
	}

	/** Implementers may override this method to intercept {@linkplain InputStream} processing
	 * This is useful for large response processing, to prevent loading of all bytes into memory
	 * @param is
	 * @return
	 * @throws IOException
	 */
	protected byte[] processStream(InputStream is) throws IOException {
		byte[] output;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int read;
		while ((read = is.read())!=-1) {
			bos.write(read);
		}
		output = bos.toByteArray();
		return output;
	}

	private void storeCookies(HttpURLConnection con, int responseCode) {
		if(200<=responseCode 
				&& responseCode<300 
				&& con.getHeaderField("Set-Cookie")!=null){
			for(String value:con.getHeaderFields().get("Set-Cookie"))
				for(HttpCookie cookie: HttpCookie.parse(value)){
					log.trace("Storing Cookie:\n{}\n", cookie);
					cookieManager.getCookieStore().add(null, cookie);
				}
		}
	}
}
