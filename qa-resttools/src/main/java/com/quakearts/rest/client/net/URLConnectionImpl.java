/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net;

import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public abstract class URLConnectionImpl extends URLConnection {

    private static final String CONTENT_TYPE = "content-type";
	private static final String ALREADY_CONNECTED = "Already connected";
	private String contentType;
    private int contentLength = -1;

    protected MessageHeader properties;

    protected URLConnectionImpl (URL u) {
        super(u);
        properties = new MessageHeader();
    }

    @Override
    public void setRequestProperty(String key, String value) {
        if(connected)
            throw new IllegalAccessError(ALREADY_CONNECTED);
        if (key == null)
            throw new NullPointerException ("key cannot be null");
        properties.set(key, value);
    }

    @Override
    public void addRequestProperty(String key, String value) {
        if (connected)
            throw new IllegalStateException(ALREADY_CONNECTED);
        if (key == null)
            throw new NullPointerException ("key is null");
    }

    @Override
    public String getRequestProperty(String key) {
        if (connected)
            throw new IllegalStateException(ALREADY_CONNECTED);
        return null;
    }

    @Override
    public Map<String,List<String>> getRequestProperties() {
        if (connected)
            throw new IllegalStateException(ALREADY_CONNECTED);
        return Collections.emptyMap();
    }

    @Override
    public String getHeaderField(String name) {
        try {
            getInputStream();
        } catch (Exception e) {
            return null;
        }
        return properties == null ? null : properties.findValue(name);
    }

    @Override
    public String getHeaderFieldKey(int fieldNumber) {
        try {
            getInputStream();
        } catch (Exception e) {
            return null;
        }
        return properties == null ? null : properties.getKey(fieldNumber);
    }

    @Override
    public String getHeaderField(int fieldNumber) {
        try {
            getInputStream();
        } catch (Exception e) {
            return null;
        }
        return properties == null ? null : properties.getValue(fieldNumber);
    }

    @Override
    public String getContentType() {
        if (contentType == null)
            contentType = getHeaderField(CONTENT_TYPE);
        
        if (contentType == null) {
            String guessedContentType = null;
            try {
                guessedContentType = guessContentTypeFromStream(getInputStream());
            } catch(java.io.IOException e) {
            	//Do nothing
            }
            
            String foundContentEncoding = properties.findValue("content-encoding");
            
            if (guessedContentType == null) {
                guessedContentType = properties.findValue(CONTENT_TYPE);

                if (guessedContentType == null) {
                    if (url.getFile().endsWith("/")) {
                        guessedContentType = "text/html";
                    } else {
                        guessedContentType = guessContentTypeFromName(url.getFile());
                    }
                }
            }

            setUknownIfNeccessary(guessedContentType, foundContentEncoding);
        }
        return contentType;
    }

	private void setUknownIfNeccessary(String ct, String ce) {
		if (ct == null || ce != null &&
		        !(ce.equalsIgnoreCase("7bit")
		          || ce.equalsIgnoreCase("8bit")
		          || ce.equalsIgnoreCase("binary")))
		    ct = "content/unknown";
		setContentType(ct);
	}

    public void setContentType(String type) {
        contentType = type;
        properties.set(CONTENT_TYPE, type);
    }

    @Override
    public int getContentLength() {
        try {
            getInputStream();
        } catch (Exception e) {
            return -1;
        }
        int l = contentLength;
        if (l < 0) {
            try {
                l = Integer.parseInt(properties.findValue("content-length"));
                setContentLength(l);
            } catch(Exception e) {
            	//Do nothing
            }
        }
        return l;
    }

    protected void setContentLength(int length) {
        contentLength = length;
        properties.set("content-length", String.valueOf(length));
    }

    private static HashMap<String,Void> proxiedHosts = new HashMap<>();

    public static synchronized void setProxiedHost(String host) {
        proxiedHosts.put(host.toLowerCase(), null);
    }

    public static synchronized boolean isProxiedHost(String host) {
        return proxiedHosts.containsKey(host.toLowerCase());
    }
}
