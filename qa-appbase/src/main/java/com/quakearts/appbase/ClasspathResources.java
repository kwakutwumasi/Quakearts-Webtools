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

import java.net.URL;

import com.quakearts.appbase.exception.ConfigurationException;

public interface ClasspathResources {
	URL getLibraryPath(String libraryName) throws ConfigurationException;
	void addLibraryPath(URL libraryPathUrl) throws ConfigurationException;
	void loadClassPath() throws ConfigurationException;
}
