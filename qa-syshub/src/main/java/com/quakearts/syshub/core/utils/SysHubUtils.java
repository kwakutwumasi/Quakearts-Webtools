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
package com.quakearts.syshub.core.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quakearts.appbase.exception.ConfigurationException;

public class SysHubUtils {		
	private SysHubUtils(){
	}
	
    private static final Logger log = LoggerFactory.getLogger("SystemHub");
	public static Logger getLog() {
		return log;
	}
		

	private static final InitialContext initialContext = createInitialContext();
	
	private static InitialContext createInitialContext() {
		try {
			return new InitialContext();
		} catch (NamingException e) {
			throw new ConfigurationException("Initial context unavailable.", e);
		}
	}

	public static InitialContext getInitialContext() {
		return initialContext;
	}
	
	public static InputStream getResource(String name){
		log.debug("Getting resource "+name);
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}
	
	public static OutputStream getOutputstream(String name) throws Exception{
		OutputStream out = null;
		URL url = Thread.currentThread().getContextClassLoader().getResource(name);
		if(url!=null){
			out = new FileOutputStream(URLDecoder.decode(url.getFile(),"UTF-8"));
		}
		return out;
	}

}
