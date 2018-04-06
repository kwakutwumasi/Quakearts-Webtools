package test;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.filter.AuthenticationErrorWriter;

public class TestAuthenticationErrorWriter implements AuthenticationErrorWriter {

	@Override
	public void sendError(int code, String message, HttpServletResponse httpResponse) throws IOException {
		httpResponse.setContentType("application/json");
		httpResponse.setStatus(code);
		httpResponse.getWriter().write("{\"message\":\""+message+"\"}");
	}

}
