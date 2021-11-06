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

import java.io.*;
import java.util.Properties;

public class NetProperties {
    private static Properties props = new Properties();
    static {
    	loadDefaultProperties();
    }

    private NetProperties() { }

    private static void loadDefaultProperties() {
        try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("net.properties")) {
        	if(in != null)
        		props.load(in);
        } catch (Exception e) {
            // Do nothing
        }
    }

    public static String get(String key) {
        String def = props.getProperty(key);
        try {
            return System.getProperty(key, def);
        } catch (IllegalArgumentException | NullPointerException e) {
        	//Do nothing
        }
        return null;
    }

    public static Integer getInteger(String key, int defval) {
        String val = null;

        try {
            val = System.getProperty(key, props.getProperty(key));
        } catch (IllegalArgumentException | NullPointerException e) {
        	//Do nothing
        }

        if (val != null) {
            try {
                return Integer.decode(val);
            } catch (NumberFormatException ex) {
            	//Do nothing
            }
        }
        return Integer.valueOf(defval);
    }

    public static Boolean getBoolean(String key) {
        String val = null;

        try {
            val = System.getProperty(key, props.getProperty(key));
        } catch (IllegalArgumentException | NullPointerException e) {
        	//Do nothing
        }

        if (val != null) {
            try {
                return Boolean.valueOf(val);
            } catch (NumberFormatException ex) {
            	//Do nothing
            }
        }

        return Boolean.FALSE;
    }

}
