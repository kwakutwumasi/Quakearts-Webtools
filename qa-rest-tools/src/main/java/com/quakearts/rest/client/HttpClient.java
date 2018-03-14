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
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

import com.quakearts.rest.client.exception.HttpClientException;

public abstract class HttpClient implements Serializable {

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
	HttpCookie defaultCookie;
	boolean secured;
	String userAgent;
	boolean followRedirects;
	CookieManager cookieManager = new CookieManager();
	
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

	public List<HttpCookie> getCookies(){
		return cookieManager.getCookieStore().getCookies();
	}
	
	public CookieManager getCookieManager() {
		return cookieManager;
	}
	
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType) 
			throws MalformedURLException, IOException, HttpClientException {
		return sendRequest(file, requestValue, method, contentType, null);
	}
		
	protected HttpResponse sendRequest(String file, String requestValue, HttpVerb method, String contentType,
			Map<String, List<String>> additionalHeaders) throws MalformedURLException, IOException, HttpClientException {
		if(host == null)
			throw new HttpClientException("Property host has not been set");	

		if(port == 0)
			throw new HttpClientException("Property port has not been set");	

		if(file == null)
			throw new HttpClientException("Parameter method is required");	

		if(method == null)
			throw new HttpClientException("Parameter method is required");	
		
		if(requiringOutputMethodsInclude(method) && requestValue == null) {
			throw new HttpClientException("Http Verb "+method+" requires requestValue");
		}
		
		HttpURLConnection con;
		
		if(secured){
			HttpsURLConnection scon = (HttpsURLConnection) new URL("https", host, port, file).openConnection();
			if(host.matches("localhost") || host.matches("127.0.0.1"))
				scon.setHostnameVerifier((hostname,session) -> {
						return true;
					});
			con = scon;
		} else {
			con = (HttpURLConnection) new URL("http", host, port, file).openConnection();
		}		

		if(followRedirects)
			con.setInstanceFollowRedirects(followRedirects);
		
		if(username!=null && password !=null){
			con.addRequestProperty("Authorization", "Basic "+(Base64.getEncoder().encodeToString((username+":"+password).getBytes())));
		}
		
		con.addRequestProperty("Accept", "application/json, text/*, application/xml");
		con.addRequestProperty("User-Agent", userAgent==null? "Generic REST Client":userAgent);
		con.addRequestProperty("Date", dateFormat.format(new Date()) + " GMT");
		con.addRequestProperty("Accept-Language", "en-US");
		if(defaultCookie!=null)
			con.addRequestProperty("Cookie", defaultCookie.toString());

		for(HttpCookie cookie:cookieManager.getCookieStore().getCookies()) {
			con.addRequestProperty("Cookie", cookie.toString());
		}
		
		if(additionalHeaders!=null){
			for(Entry<String, List<String>> entry:additionalHeaders.entrySet()){
				for(String value:entry.getValue())
					con.addRequestProperty(entry.getKey(), value);
			}
		}

		con.setRequestMethod(method.name());
		
		if(returningInputMethodsInclude(method))
			con.setDoInput(true);
				
		if (requestValue != null && !requestValue.isEmpty()) {
			if(!requiringOutputMethodsInclude(method)
					&& !optionalOutputMethodsInlude(method) && requestValue != null) {
				throw new HttpClientException("Http Verb "+method+" cannot have a requestValue");
			}
			con.addRequestProperty("Content-Length",
					Integer.toString((requestValue.length() / 8)));
			con.addRequestProperty("Content-Type", contentType);
			con.setDoOutput(true);
			con.getOutputStream().write(requestValue.getBytes());
		}
		
		int responseCode=0;
		con.connect();

		responseCode = con.getResponseCode();
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
		
		if(200<=responseCode 
				&& responseCode<300 
				&& con.getHeaderField("Set-Cookie")!=null){
			for(String value:con.getHeaderFields().get("Set-Cookie"))
				for(HttpCookie cookie: HttpCookie.parse(value))
					cookieManager.getCookieStore().add(null, cookie);
		}
		
		return new HttpResponse(output, con.getResponseMessage(), con.getResponseCode(), con.getHeaderFields());
	}

    public static String prettyPrintJSON(String jsonString){
        int level = 0;
        boolean newline=false, inLiteral=false;
        StringBuilder builder = new StringBuilder();
        char p='\0';
        for(char c:jsonString.toCharArray()){
            if(newline && (c!='}' &&  c!=']' && c!=',' && (c!='{' || p!='['))){           
                builder.append('\n');
                for(int i=0;i<level;i++)
                    builder.append("  ");
                newline = false;
            }
            switch (c) {
            case '"':
                builder.append(c);
                if(!inLiteral)
                	inLiteral=true;
                else {
                	if(p!='\\'){
                		inLiteral = false;
                	}
                }      		
                break;
            case '{':
            case '[':
                builder.append(c);
            	if(!inLiteral){
            		level++;
                	newline = true;
            	}
                break;
            case ',':
                builder.append(c);
            	if(!inLiteral){
            		newline = true;
            	}
                break;
            case '}':
            case ']':
            	if(!inLiteral){
	                level--;
	                if(c!=']' || p!='}'){
	                    builder.append('\n');
	                    for(int i=0;i<level;i++)
	                        builder.append("  ");
	                }
            	}
                builder.append(c);
            	if(!inLiteral){
            		newline = true;
            	}
                break;
            default:
                builder.append(c);
                break;
            }
            p=c;
        }
       
        return builder.toString();
    }
}
