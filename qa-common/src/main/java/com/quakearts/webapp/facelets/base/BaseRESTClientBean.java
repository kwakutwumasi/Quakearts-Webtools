/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.webapp.facelets.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.quakearts.webapp.facelets.util.Base64;

public abstract class BaseRESTClientBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4822817405423965774L;
	private int port;
	private String host;
	private String username;
	private String password;
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
				"DDD, dd MMM yyyy HH:mm:ss");
	private String defaultCookie;
	private boolean secured;
	private String userAgent;
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDefaultCookie() {
		return defaultCookie;
	}

	public void setDefaultCookie(String cookie) {
		this.defaultCookie = cookie;
	}

	public boolean isSecured() {
		return secured;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSecured(boolean secured) {
		this.secured = secured;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public static class RESTResponse {
		private String output;
		private String message;
		private int httpCode;
		private Map<String, List<String>> headers = new HashMap<>();
		
		private RESTResponse(String output, String message, int httpCode, Map<String, List<String>> headers) {
			this.output = output;
			this.message = message;
			this.httpCode = httpCode;
			if(headers!=null)
				this.headers = headers;
		}

		public String getOutput() {
			return output;
		}

		public String getMessage() {
			return message;
		}

		public int getHttpCode() {
			return httpCode;
		}

		public List<String> getHeaders(String headerName){
			return headers.get(headerName);
		}
		
		public String getHeader(String headerName){
			List<String> headerFields = headers.get(headerName);
			return headerFields != null && !headerFields.isEmpty()? headerFields.iterator().next():null;
		}
	}
	
	protected RESTResponse sendRequest(String file, String requestValue, String method, String contentType) 
			throws MalformedURLException, IOException {
		return sendRequest(file, requestValue, method, contentType, null);
	}
		
	protected RESTResponse sendRequest(String file, String requestValue, String method, String contentType,
			Map<String, String> additionalHeaders) throws MalformedURLException, IOException {
		HttpURLConnection con;
		
		if(secured){
			HttpsURLConnection scon = (HttpsURLConnection) new URL("https", host, port, file).openConnection();
			scon.setHostnameVerifier(new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return hostname.equals(host);
				}
			});
			con = scon;
		} else {
			con = (HttpURLConnection) new URL("http", host, port, file).openConnection();
		}		
		
		if(username!=null && password !=null){
			con.addRequestProperty("Authorization", "Basic "+(Base64.encode(username+":"+password)));
		}
		con.addRequestProperty("Accept", "application/json, text/*, application/xml");
		con.addRequestProperty("User-Agent", userAgent==null? "Generic REST Client":userAgent);
		con.addRequestProperty("Date", dateFormat.format(new Date()) + " GMT");
		con.addRequestProperty("Accept-Language", "en-US");
		if(defaultCookie!=null)
			con.addRequestProperty("Cookie", defaultCookie);

		if(additionalHeaders!=null){
			for(Entry<String, String> entry:additionalHeaders.entrySet()){
				con.addRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		con.setRequestMethod(method);
		con.setDoInput(true);
		if (requestValue != null && !requestValue.isEmpty()) {
			con.addRequestProperty("Content-Length",
					Integer.toString((requestValue.length() / 8)));
			con.addRequestProperty("Content-Type", contentType);
			con.setDoOutput(true);
			con.getOutputStream().write(requestValue.getBytes());
		}
		
		int responseCode=0;
		con.connect();

		responseCode = con.getResponseCode();
		con.getResponseMessage();
		String output = null;
		if(200<=responseCode && responseCode<300 && con.getHeaderField("Set-Cookie")!=null){
			defaultCookie = con.getHeaderField("Set-Cookie");
			defaultCookie = defaultCookie.substring(0, defaultCookie.indexOf(";"));
		}

		InputStream is;
		if(responseCode==200){
			is = con.getInputStream();
		} else{
			is = con.getErrorStream();
		}
		
		if(is!=null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			output = response.toString();
		}
		
		return new RESTResponse(output, con.getResponseMessage(), con.getResponseCode(), con.getHeaderFields());
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
