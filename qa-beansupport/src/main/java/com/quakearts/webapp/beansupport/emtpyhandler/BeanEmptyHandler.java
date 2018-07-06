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
package com.quakearts.webapp.beansupport.emtpyhandler;

/**Interface for custom empty handlers.
 * @author kwakutwumasi-afriyie
 *
 * @param <V>
 */
public interface BeanEmptyHandler<V> {
	/**Verify that the value passed to the method represents an empty value
	 * @param value the property of the bean to verify
	 * @return true if the value represents an empty value
	 */
	boolean isEmpty(V value);
}
