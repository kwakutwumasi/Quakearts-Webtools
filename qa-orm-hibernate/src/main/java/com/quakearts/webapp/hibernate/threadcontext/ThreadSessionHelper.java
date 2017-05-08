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
package com.quakearts.webapp.hibernate.threadcontext;

import java.util.HashMap;
import java.util.Map;


//import javax.faces.context.FacesContext;

import org.hibernate.engine.spi.SessionFactoryImplementor;

import com.quakearts.webapp.hibernate.MapBasedCurrentSessionContextHelper;

public class ThreadSessionHelper extends MapBasedCurrentSessionContextHelper {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9078120922061282997L;

	public ThreadSessionHelper(SessionFactoryImplementor implementor) {
		super(implementor);
	}

	private static final ThreadLocal<Map<Object, Object>> threadLocalContext = new InheritableThreadLocal<Map<Object, Object>>(){
		protected Map<Object,Object> initialValue() {
			return new HashMap<>();
		};
	};
	
	protected Map<Object, Object> getContextAttributes(){
		return threadLocalContext.get();
	}
	
}
