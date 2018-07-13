package test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.quakearts.appbase.Main;
import com.quakearts.rest.client.HttpClient;
import com.quakearts.rest.client.HttpClientBuilder;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;

public class TestRestSecurity {

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
		
		HttpResponse response = clientBearer.sendRequest("/test-authentication-filter-required",
				null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		
		response = clientBasic.sendRequest("/test-authentication-filter-notrequired",
				"test=value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		
		clientBasic.sendRequest("/test-authentication-filter-notrequired",
				"test=value", HttpVerb.POST);
		
		response = client.sendRequest("/test-authentication-filter-required",
				"qa_username=testuser&qa_password=password", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput(), is("Ok"));
		assertThat(TestAuthenticationCacheService.cacheLoaded(), is(true));

		client.sendRequest("/test-authentication-filter-required",
				"qa_username=testuser&qa_password=password", HttpVerb.POST);
		assertThat(TestAuthenticationCacheService.cacheHit(), is(true));
		
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

}
