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
package com.quakearts.appbase;

import javax.enterprise.inject.Vetoed;

import com.quakearts.appbase.impl.ClasspathResourcesImpl;

@Vetoed
public class ClassPathSupport {
	private static ClasspathResources classpathResources;

	private ClassPathSupport() {}
	
	public static ClasspathResources getClasspathResources() {
		if(classpathResources == null) {
			classpathResources = new ClasspathResourcesImpl();
			classpathResources.loadClassPath();
		}
		return classpathResources;
	}
}
