/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
/* All classes in this package and subpackages were copied from the JDK 8 Implementation
 * of HttpURLConnection and HTTPSURLConnection.
 * Since those packages are internal JDK classes and not API, modifying their behavior without
 * raising warnings and errors and doing it in a portable way was impossible
 * The main reason was to modify a single, mindless piece of code that changed every request
 * with a body and a GET method to a POST request. This posed problems for other mindless
 * REST API's that had GET methods with a Body.
 * AAAAArgh!
 */
package com.quakearts.rest.client.net;