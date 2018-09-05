package test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsInstanceOf.*;

import java.io.IOException;
import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.requestwrapper.AuthenticationServletRequestWrapper;

public class TestAuthenticationRequiredServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8688011757265449422L;
	
	@Inject
	private TestService testService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		assertThat(req, is(instanceOf(AuthenticationServletRequestWrapper.class)));
		assertThat(req.getAuthType(), is("com.quakearts.security.AUTHENTICATION"));
		assertThat(req.getHeader("user-agent"), is("Generic REST Client"));
		assertThat(req.getHeaders("user-agent"), is(notNullValue()));
		assertThat(req.getHeader("Authorization"), is(""));
		assertThat(req.getHeader("authorization"), is(""));
		Enumeration<String> authorizations = req.getHeaders("authorization");
		assertThat(authorizations, is(notNullValue()));	
		while (authorizations.hasMoreElements()) {
			String authorization = (String) authorizations.nextElement();
			assertThat(authorization, is(""));	
		}

		authorizations = req.getHeaders("Authorization");
		assertThat(authorizations, is(notNullValue()));	
		while (authorizations.hasMoreElements()) {
			String authorization = (String) authorizations.nextElement();
			assertThat(authorization, is(""));	
		}

		assertThat(req.authenticate(resp), is(true));
		assertThat(req.isUserInRole("TestRole"), is(true));
		assertThat(req.isUserInRole("NoTestRole"), is(false));
		assertThat(req.getUserPrincipal().getName(), is("testuser"));
		assertThat(req.getRemoteUser(), is("testuser"));
		
		
		testService.process(req, resp);
	}
}
