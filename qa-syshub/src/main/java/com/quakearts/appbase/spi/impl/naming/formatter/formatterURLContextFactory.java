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
package com.quakearts.appbase.spi.impl.naming.formatter;


import java.util.Hashtable;

import javax.enterprise.inject.Vetoed;
import javax.naming.NamingException;
import org.apache.naming.NamingContext;

import com.quakearts.appbase.spi.impl.naming.URLContextFactoryBase;

/**JNDI context factory for {@link com.quakearts.syshub.core.MessageFormatter}
 * @author kwakutwumasi-afriyie
 *
 */
@Vetoed
public class formatterURLContextFactory extends URLContextFactoryBase {

	private static NamingContext context;
	private static final String TRANSCONTEXT = "formatter:";
	
	@SuppressWarnings({ "unchecked"})
	protected NamingContext getContext(Hashtable<?, ?> environment) throws NamingException {
		if(context==null){
			context = new NamingContext((Hashtable<String, Object>)environment, TRANSCONTEXT);
			context.bind(TRANSCONTEXT, context);
		}
		
		return context;
	}

}
