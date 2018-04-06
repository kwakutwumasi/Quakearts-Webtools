package test;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestAuthenticationNotRequiredServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8688011757265449422L;
	
	@Inject
	private TestOtherService testOtherService;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		testOtherService.processUnSecured(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		testOtherService.processSecured(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		testOtherService.processSecured(req, resp);
	}
}
