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
package com.quakearts.security.cryptography.jboss;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.system.ServiceMBeanSupport;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.CryptoUtils;
import com.quakearts.security.cryptography.factory.CrytpoServiceFactory;

public class CryptoService extends ServiceMBeanSupport implements CryptoServiceMBean{
    
	private String instance, keyProviderClass;
	private String jndiName;
	private Properties properties;
	
	public CryptoService(){
        super();
    }
    
	@Override
    public void startService() throws Exception
    {
    	if(instance == null)
    		throw new NullPointerException("You must provide a valid instance.");
    	if(keyProviderClass == null)
    		throw new NullPointerException("You must provide a valid key provider class.");
    	    	    	
    	rebind();
    }
      
	@Override
	protected void stopService() throws Exception {
		unbind(jndiName);
	}

	@Override
	public CryptoResource getResource() throws Exception {
		return CrytpoServiceFactory.getInstance().getCryptoResource(instance, keyProviderClass, properties, jndiName);
	}

	@Override
	public String getInstance() {
		return instance;
	}

	@Override
	public void setInstance(String instance) {
		this.instance = instance;		
	}

	@Override
	public String getKeyProviderClass() {
		return keyProviderClass;
	}

	@Override
	public void setKeyProviderClass(String keyProviderClass) {
		this.keyProviderClass = keyProviderClass;
	}

	@Override
	public String attestService() {
		try {
			return CrytpoServiceFactory.getInstance().getCryptoResource(instance, keyProviderClass, properties, jndiName).doEncrypt("Cipher check");			
		} catch (Exception e) {
			return "Exception "+e.getClass().getName()+". "+e.getMessage();
		}
	}

	@Override
	public void setJndiName(String jndiName) {
		if(jndiName==null)
			return;
		if(jndiName.startsWith("java:/"))
			this.jndiName = jndiName.trim();
		else
			this.jndiName = "java:/"+jndiName.trim();		
	}

	@Override
	public String getJndiName() {
		return jndiName;
	}

	private void rebind() throws NamingException
    {
        InitialContext rootCtx = CryptoUtils.getInitialContext();
        rootCtx.bind(jndiName, new CryptoProxyImpl(this));
        log.info("Bound CryptoService to jndi name "+jndiName);
    }

    private void unbind(String jndiName)
    {
        try {
            InitialContext rootCtx = CryptoUtils.getInitialContext();
            rootCtx.unbind(jndiName);
            log.info("Unbound CryptoService");
        } catch(NamingException e) {
            log.error(e.getMessage(),e);
        }
    }

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
