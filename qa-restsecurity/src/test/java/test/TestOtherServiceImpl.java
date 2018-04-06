package test;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.cdi.RequireAuthorization;

@Singleton
public class TestOtherServiceImpl implements TestOtherService {

	@Override @RequireAuthorization(allow="TestRole", deny="TestDenyRole")
	public void processSecured(HttpServletRequest request, HttpServletResponse response) throws IOException {
		processUnSecured(request, response);
	}

	@Override
	public void processUnSecured(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
 		response.getWriter().write("Ok");
		response.getWriter().flush();
	}
}
