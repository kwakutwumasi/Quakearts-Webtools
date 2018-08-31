package test;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.quakearts.webapp.security.rest.SecurityContext;
import com.quakearts.webapp.security.rest.cdi.RequireAuthorization;
import com.quakearts.webapp.security.rest.requestwrapper.AuthenticationServletRequestWrapper;

@Singleton
@RequireAuthorization(allow="TestRole")
public class TestServiceImpl implements TestService {
	/* (non-Javadoc)
	 * @see test.TestService#process(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		assertThat(request instanceof AuthenticationServletRequestWrapper, is(true));
		assertThat(SecurityContext.getCurrentSecurityContext(), is(notNullValue()));
		SecurityContext context = SecurityContext.getCurrentSecurityContext();
		assertThat(context.getIdentity(), is("testuser"));			
		
		assertThat(context.getApplication(), is("/test-authentication-filter-required"));
		assertThat(context.getApplicationContext(), is("Test-Authentication-Filter"));
		assertThat(context.getHost(), is("127.0.0.1"));
		assertThat(context.getPort(), is(8080));		
		assertThat(context.getRemoteHost(), is("127.0.0.1"));
		assertThat(context.getRemotePort()>0, is(true));
		assertThat(context.getSubject(), is(notNullValue()));

		assertThat(context.getUserPrincipal(), is(notNullValue()));
		assertThat(context.getUserPrincipal().getName(), is("testuser"));
		
		response.setContentType("text/plain");
 		response.getWriter().write("Ok");
		response.getWriter().flush();
	}
}
