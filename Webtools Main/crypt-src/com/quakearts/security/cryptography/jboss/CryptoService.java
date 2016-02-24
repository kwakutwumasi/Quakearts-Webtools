package com.quakearts.security.cryptography.jboss;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.NoSuchPaddingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.naming.NonSerializableFactory;
import org.jboss.system.ServiceMBeanSupport;

import com.quakearts.security.cryptography.CryptoResource;
import com.quakearts.security.cryptography.CryptoUtils;
import com.quakearts.security.cryptography.factory.KeyProviderFactory;
import com.quakearts.security.cryptography.provider.KeyProvider;

public class CryptoService extends ServiceMBeanSupport implements CryptoServiceMBean{
    
	private Key key;
	private String instance, keyProviderClass;
	private KeyProvider provider;
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
    	    	
    	provider = KeyProviderFactory.createKeyProvider(keyProviderClass);
    	
    	if(provider == null){
    		log.warn("keyProviderProperties is null. Key Provider may not startup properly.");
    	}else{
    		provider.setProperties(properties);
    	}
    	
    	key = provider.getKey();
    	
    	rebind();
    }
      
	@Override
	protected void stopService() throws Exception {
		unbind(jndiName);
	}

	@Override
	public CryptoResource getResource() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return new CryptoResource(key, instance);
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
			return new CryptoResource(key,instance).doEncrypt("Cipher check");			
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
        NonSerializableFactory.rebind(rootCtx, jndiName, new CryptoProxyImpl(this));
        log.info("Bound CryptoService to jndi name "+jndiName);
    }

    private void unbind(String jndiName)
    {
        try {
            InitialContext rootCtx = CryptoUtils.getInitialContext();
            rootCtx.unbind(jndiName);
            NonSerializableFactory.unbind(jndiName);
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
