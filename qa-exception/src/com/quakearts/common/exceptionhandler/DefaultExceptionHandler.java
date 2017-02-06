package com.quakearts.common.exceptionhandler;

import java.util.logging.Level;

/**Default exception handler. Prints to a java.util.log
 * @author Kwaku Twumasi
 *
 */
public class DefaultExceptionHandler extends ExceptionHandlerBase {

	@Override
	public void handleException(Exception e, Object... params) {
		loggger.log(Level.SEVERE, "Exception of type " + e.getClass().getName() 
				+ " was thrown. Message is " + e.getMessage(), e);
	}

}
