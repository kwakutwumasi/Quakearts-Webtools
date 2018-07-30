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
package com.quakearts.appbase.classpath;

import java.net.URL;

import com.quakearts.appbase.exception.ConfigurationException;


/**Implementations of this class should traverse the classpath and catalogue every library/folder within it.
 * The names of the library files should be matched to their URL's, to make it easy for modules to find library
 * files within the classpath.
 * @author kwakutwumasi-afriyie
 *
 */
public interface ClasspathResources {
	
	/**Get the URL of a library. The name of the library to use will be implementation specific.
	 * The default implementation uses the file name as the library name.
	 * @param libraryName the name of the library
	 * @return a URL object that can be used to retrieve the contents of the library
	 * @throws ConfigurationException if there is an error retrieving the library
	 */
	URL getLibraryPath(String libraryName) throws ConfigurationException;
	/**Add a library to the store. The URL will be used to generate the library name
	 * @param libraryPathUrl the URL of the library
	 * @throws ConfigurationException if there is an error storing the library
	 */
	void addLibraryPath(URL libraryPathUrl) throws ConfigurationException;
	/**API method called to instantiate classpath scanning
	 * @throws ConfigurationException
	 */
	void loadClassPath() throws ConfigurationException;
}
