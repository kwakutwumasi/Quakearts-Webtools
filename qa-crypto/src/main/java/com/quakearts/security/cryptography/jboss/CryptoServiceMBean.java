package com.quakearts.security.cryptography.jboss;

import java.util.Properties;
import org.jboss.system.ServiceMBean;
import com.quakearts.security.cryptography.CryptoResource;

public interface CryptoServiceMBean extends ServiceMBean{
	public CryptoResource getResource() throws Exception;
	public String getInstance();
	public void setInstance(String instance);
	public String getKeyProviderClass();
	public void setKeyProviderClass(String keyProviderClass);
	public String attestService();
	public void setJndiName(String jndiName);
	public String getJndiName();
	public Properties getProperties();
	public void setProperties(Properties properties);
}
