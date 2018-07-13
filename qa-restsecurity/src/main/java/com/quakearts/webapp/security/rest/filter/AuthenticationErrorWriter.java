package com.quakearts.webapp.security.rest.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**This interface provides a mechanism for writing authentication errors in different formats.
 * This is useful in scenarios where the error message needs to be written in a special format, 
 * such as JSON, XML, HTML or plain text.
 * @author kwakutwumasi-afriyie
 *
 */
public interface AuthenticationErrorWriter {
	void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException;
}
