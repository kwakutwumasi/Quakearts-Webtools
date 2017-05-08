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
