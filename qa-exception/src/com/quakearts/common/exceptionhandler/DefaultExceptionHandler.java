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

import java.util.logging.Level;

/**Default exception handler. Prints to a java.util.log
 * @author Kwaku Twumasi
 *
 */
public class DefaultExceptionHandler extends ExceptionHandlerBase {

	/* (non-Javadoc)
	 * @see com.quakearts.common.exceptionhandler.ExceptionHandler#handleException(java.lang.Exception, java.lang.Object[])
	 */
	@Override
	public void handleException(Exception e, Object... params) {
		loggger.log(Level.SEVERE, "Exception of type " + e.getClass().getName() 
				+ " was thrown. Message is " + e.getMessage(), e);
	}

}
