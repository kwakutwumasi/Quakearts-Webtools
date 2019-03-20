package com.quakearts.webapps.security.rest.tests;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.hamcrest.core.IsInstanceOf.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Permission;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.quakearts.appbase.Main;
import com.quakearts.rest.client.HttpClient;
import com.quakearts.rest.client.HttpClientBuilder;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;
import com.quakearts.tools.test.mocking.proxy.MockingProxyBuilder;
import com.quakearts.webapp.security.rest.SecurityContext;
import com.quakearts.webapp.security.rest.SecurityContextPermission;
import com.quakearts.webapp.security.rest.exception.RestSecurityException;
import com.quakearts.webapp.security.rest.requestwrapper.AuthenticationServletRequestWrapper;
import com.quakearts.webapp.security.rest.util.PluginService;

import test.TestAuthenticationCacheService;
import test.TestLoginModule;
import test.plugin.TestDoubleRegistered;
import test.plugin.TestPluginInterface;
import test.plugin.TestPluginInterfaceImpl;
import test.plugin.TestUnimplemented;

public class RestSecurityTest {

	static class TestHttpClient extends HttpClient {

		/**
		 * 
		 */
		private static final long serialVersionUID = 9188920469199440723L;
		
		Map<String, List<String>> authenticationHeaders = new HashMap<>();
		
		TestHttpClient bearer() {
			return addAuthorizationHeader("Bearer Test");
		}

		TestHttpClient basic() {
			return addAuthorizationHeader("Basic dGVzdDp0ZXN0");
		}

		TestHttpClient addAuthorizationHeader(String value){
			authenticationHeaders.put("Authorization", Arrays.asList(value));
			return this;
		}
		
		public HttpResponse sendRequest(String file, String requestValue, HttpVerb method)
				throws MalformedURLException, IOException, HttpClientException {
			return sendRequest(file, requestValue, method, "application/x-www-form-urlencoded", authenticationHeaders);
		}
	}
	
	static class TestHttpClientBuilder extends HttpClientBuilder<TestHttpClient> {
		private TestHttpClientBuilder() {
			httpClient = new TestHttpClient();
			setHostAs("localhost");
			setPortAs(8080);
		}
		
		public static TestHttpClientBuilder createNewTestHttpClient() {
			return new TestHttpClientBuilder();
		}
		
		@Override
		public TestHttpClient thenBuild() {
			return (TestHttpClient) httpClient;
		}
	}
	
	@Test
	public void testAllFeatures() throws Exception {
		Main.main(new String[] {"test.TestMain","-dontwaitinmain"});
		
		TestHttpClient clientBearer = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild().bearer(),
				clientBasic = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild().basic(),
				client = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild(),
				clientWithInvalidHeader = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild().addAuthorizationHeader("Token dGVzdDp0ZXN0"),
				clientWithInvalidBasicHeader = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild().addAuthorizationHeader("Basic dGVzdDp0ZXN0*-"),
				clientWithPartialBasicHeader = TestHttpClientBuilder.createNewTestHttpClient()
				.thenBuild().addAuthorizationHeader("Basic dGVzdA==");
		
		TestLoginModule.tokenFetched = false;
		HttpResponse response = clientBearer.sendRequest("/test-authentication-filter-required",
				null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		assertTrue(TestLoginModule.tokenFetched);
		
		TestLoginModule.throwLoginException = true;
		response = clientBearer.sendRequest("/test-authentication-filter-required",
				null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(403));
		
		TestLoginModule.passwordFetched = false;
		TestLoginModule.usernameFetched = false;
		response = clientBasic.sendRequest("/test-authentication-filter-notrequired",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		assertTrue(TestLoginModule.passwordFetched);
		assertTrue(TestLoginModule.usernameFetched);
		
		TestLoginModule.throwLoginException = true;
		response = clientBasic.sendRequest("/test-authentication-filter-required",
				null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(403));

		response = client.sendRequest("/test-authentication-filter-required",
				"qa_username=testuser&qa_password=password", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		assertThat(TestAuthenticationCacheService.cacheLoaded(), is(true));

		client.sendRequest("/test-authentication-filter-required",
				"qa_username=testuser&qa_password=password", HttpVerb.POST);
		assertThat(TestAuthenticationCacheService.cacheHit(), is(true));
		
		TestLoginModule.throwLoginException = true;
		response = client.sendRequest("/test-authentication-filter-required",
				"qa_username=testusernew&qa_password=passwordnew", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(403));

		response = client.sendRequest("/test-authentication-filter-required",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(401));
		assertThat(response.getOutput().contains("Missing identity&#47;credential parameters. Authorization is required"), is(true));
		
		response = client.sendRequest("/test-authentication-filter-notrequired",
				null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		
		response = client.sendRequest("/test-authentication-filter-notrequired",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(403));
		assertThat(response.getOutput(), is("{\"message\":\"No Subject loaded for verification\"}"));
		
		TestLoginModule.addDenyRole = true;
		response = clientBasic.sendRequest("/test-authentication-filter-notrequired",
				"test=value", HttpVerb.PUT);
		assertThat(response.getHttpCode(), is(403));
		TestLoginModule.addDenyRole = false;
		
		response = clientWithInvalidHeader.sendRequest("/test-authentication-filter-required",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(403));

		response = clientWithInvalidBasicHeader.sendRequest("/test-authentication-filter-required",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(403));

		response = clientWithPartialBasicHeader.sendRequest("/test-authentication-filter-required",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(403));
	}

	@Test
	public void testAuthType() {
		try {
			assertThat(getWrapper().getAuthType(), is(nullValue()));
		} finally {
			SecurityContext.getCurrentSecurityContext().release();
		}
	}
	
	@Test
	public void testIsUserInRole(){
		try {
			assertFalse(getWrapper().isUserInRole("TestRole"));
		} finally {
			SecurityContext.getCurrentSecurityContext().release();
		}
	}

	@Test
	public void testGetRemoteUser() {
		try {
			assertThat(getWrapper().getRemoteUser(), is(nullValue()));
		} finally {
			SecurityContext.getCurrentSecurityContext().release();
		}
	}

	@Test
	public void testAuthenticateHttpServletResponse() throws Exception {
		HttpServletResponse response = MockingProxyBuilder
				.createMockingInvocationHandlerFor(HttpServletResponse.class)
				.mock("sendError").withVoidMethod((arguments)->{
					int code = arguments.get(0);
					assertThat(code, is(401));
				})
				.thenBuild();
		assertThat(getWrapper().authenticate(response), is(false));	
	}

	@Test(expected=ServletException.class)
	public void testLogin() throws Exception {
		getWrapper().login("test", "test");
	}

	@Test
	public void testGetParameter() {
		assertThat(getWrapper().getParameter("j_password"), is(""));	
		assertThat(getWrapper().getParameter("test"), is("Test"));	
	}

	@Test
	public void testGetParameterValues() {
		assertThat(getWrapper().getParameterValues("j_password"), is(new String[] {""}));
		assertThat(getWrapper().getParameterValues("test"), is(notNullValue()));	
	}

	AuthenticationServletRequestWrapper wrapper;
	public AuthenticationServletRequestWrapper getWrapper() {
		if(wrapper == null) {
			HttpServletRequest request = MockingProxyBuilder
					.createMockingInvocationHandlerFor(HttpServletRequest.class)
					.mock("getParameter").withEmptyMethod(()->{ return "Test";})
					.mock("getParameterValues").withEmptyMethod(()->{ String[] ret = {"Test1","Test2"}; return ret;})
					.thenBuild();
			wrapper = new AuthenticationServletRequestWrapper(request);
		}
		return wrapper;
	}
	
	@SuppressWarnings({ "serial", "unlikely-arg-type" })
	@Test
	public void testSecurityPermission() throws Exception {
		SecurityContextPermission permission = new SecurityContextPermission("name", "actions");
		assertTrue(permission.equals(permission));
		assertFalse(permission.equals(null));
		assertFalse(permission.equals(new SecurityContextPermission("test", "test2")));
		assertFalse(permission.equals(new SecurityContextPermission("test", "actions")));
		assertTrue(permission.equals(new SecurityContextPermission("name", "actions")));
		assertTrue(permission.implies(new SecurityContextPermission("name", "actions")));
		assertFalse(permission.implies(new SecurityContextPermission("test", "actions")));
		assertFalse(permission.equals("test actions"));
		assertFalse(permission.implies(new Permission("") {
			@Override
			public boolean implies(Permission permission) {
				return false;
			}
			@Override
			public int hashCode() {
				return 0;
			}
			@Override
			public String getActions() {
				return null;
			}
			
			@Override
			public boolean equals(Object obj) {
				return false;
			}
		}));
	}
	
	@Test
	public void testPluginService() throws Exception {
		TestPluginInterface pluginInterface = PluginService.loadPlugin(TestPluginInterface.class);
		assertThat(pluginInterface, is(notNullValue()));
		assertThat(pluginInterface, is(instanceOf(TestPluginInterfaceImpl.class)));
		
		TestUnimplemented unimplemented = PluginService.loadPlugin(TestUnimplemented.class);
		assertThat(unimplemented, is(nullValue()));
	}
	
	@Test(expected=RestSecurityException.class)
	public void testPluginServiceDoubleRegistration() throws Exception {
		PluginService.loadPlugin(TestDoubleRegistered.class);
	}
}
