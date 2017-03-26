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
