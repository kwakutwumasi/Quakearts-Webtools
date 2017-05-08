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
package com.quakearts.common.exceptionhandler;

/**Exception handler interface
 * @author Kwaku Twumasi
 *
 */
public interface ExceptionHandler {
	/**Handle the exception. The optional params can hold additional objects required to handle the exception
	 * @param e The exception to handle
	 * @param params Parameters required by the handler
	 */
	void handleException(Exception e, Object... params);
}
