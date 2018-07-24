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
package com.quakearts.tools.test.mockserver.model;

import java.util.List;

/**Implemented by classes that hold HTTP header values
 * @author kwakutwumasi-afriyie
 *
 */
public interface HttpHeader {
	/**Get the header name
	 * @return the header name
	 */
	String getName();
	/**Get the first header value. Used for header values that have just one value
	 * @return the first header value
	 */
	String getValue();
	/**Get the list of header values
	 * @return the list of header values
	 */
	List<String> getValues();
}