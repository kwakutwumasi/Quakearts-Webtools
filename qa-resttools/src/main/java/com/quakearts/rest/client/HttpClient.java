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

import com.quakearts.rest.client.exception.HttpClientException;

/**Base class for creating HTTP clients
 * @author kwakutwumasi-afriyie
 *
 */
public abstract class HttpClient implements Serializable {

	private static final String LOCALHOST = "localhost";
	private static final String LOOPBACK = "127.0.0.1";
	/**
	 * 
	 */
	private static final long serialVersionUID = 4822817405423965774L;
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
	
	public int getPort() {
		return port;
	}

	public String getHost() {
		return host;
	}

	public HttpCookie getDefaultCookie() {
		return defaultCookie;
	}

	public boolean isSecured() {
		return secured;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUserAgent() {
		return userAgent;
	}
	
	public boolean followsRedirects() {
		return followRedirects;
	}
	
	public boolean matchesHostnames() {
		return matchHostname;
	}

	public List<HttpCookie> getCookies(){
		return cookieManager.getCookieStore().getCookies();
	}
	
	public CookieManager getCookieManager() {
		return cookieManager;
	}
	
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType) 
			throws IOException, HttpClientException {
		return sendRequest(file, requestValue, method, contentType, null);
	}
		
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType,
			Map<String, List<String>> additionalHeaders) throws IOException, HttpClientException {
		runValidations(file, requestValue, method);
		HttpURLConnection con = prepareConnection(file, requestValue, method, contentType, additionalHeaders);
		int responseCode = connect(con);
		String output = storeOutput(method, con, responseCode);
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
		con.addRequestProperty("Accept-Language", "en-US");
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
			con.addRequestProperty("Content-Length",
					Integer.toString((requestValue.length() / 8)));
			con.addRequestProperty("Content-Type", contentType);
			con.setDoOutput(true);
			con.getOutputStream().write(requestValue.getBytes());
		}
	}

	private int connect(HttpURLConnection con) throws IOException {
		int responseCode=0;
		con.connect();
		responseCode = con.getResponseCode();
		return responseCode;
	}

	private String storeOutput(HttpVerb method, HttpURLConnection con, int responseCode) throws IOException {
		String output = null;
		InputStream is = null;
		if(responseCode==200 
				&& returningInputMethodsInclude(method)){
			is = con.getInputStream();
		} else if(responseCode > 399){
			is = con.getErrorStream();
		}
		
		if(is!=null){
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int read;
			while ((read = is.read())!=-1) {
				bos.write(read);
			}
			output = new String(bos.toByteArray());
		}
		return output;
	}

	private void storeCookies(HttpURLConnection con, int responseCode) {
		if(200<=responseCode 
				&& responseCode<300 
				&& con.getHeaderField("Set-Cookie")!=null){
			for(String value:con.getHeaderFields().get("Set-Cookie"))
				for(HttpCookie cookie: HttpCookie.parse(value))
					cookieManager.getCookieStore().add(null, cookie);
		}
	}
}
