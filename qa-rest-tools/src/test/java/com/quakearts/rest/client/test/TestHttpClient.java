package com.quakearts.rest.client.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.rest.client.HttpClient;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;
import com.quakearts.rest.client.test.helpers.MockHttpClient;
import com.quakearts.rest.client.test.helpers.MockHttpClientBuilder;
import com.quakearts.tools.test.mockserver.MockServer;
import com.quakearts.tools.test.mockserver.MockServerFactory;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;
import com.quakearts.tools.test.mockserver.model.impl.MockActionBuilder;

public class TestHttpClient {

	@BeforeClass
	public static void setTrustManager() throws Exception{
		TrustManager[] manager = new TrustManager[] {
				new X509TrustManager() {
					
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					
					@Override
					public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}
					
					@Override
					public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					}
				}
		};
		
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, manager, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	}
	
	@Test
	public void testHttpsLocalHostAnd127001() throws Exception {
		HttpRequest httpRequest = HttpMessageBuilder
				.createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/tlstest")
				.setId("/tlstest")
				.setResponseAs(HttpMessageBuilder
						.createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Successful".getBytes())
						.thenBuild())
				.thenBuild();
		
		MockServer mockServer = MockServerFactory.getInstance()
				.getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration()
						.setMockingModeAs(MockingMode.MOCK)
						.setPortAs(8443)
						.setUseTLSAs(true)
						.setKeyStoreAs("conf/tomcat.keystore")
						.setKeyStoreTypeAs("JCEKS")
						.setKeyStorePasswordAs("password1")
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.addRequest(httpRequest)
								.thenBuild())
				.addDefaultActions((context)->{
					for(HttpHeader header:context.getHttpRequest().getHeaders())
						for(String value:header.getValues())
							context.addHeader(header.getName(), value);
				});
		mockServer.start();
		try {
			MockHttpClient client = new MockHttpClientBuilder()
					.setHostAs("localhost")
					.setPortAs(8443)
					.setSecuredAs(true)
					.thenBuild();
			
			HttpResponse httpResponse = client.sendRequest(httpRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Successful"));
			
			client = new MockHttpClientBuilder()
					.setHostAs("127.0.0.1")
					.setPortAs(8443)
					.setSecuredAs(true)
					.thenBuild();
			httpResponse = client.sendRequest(httpRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Successful"));
		} finally {
			mockServer.stop();
		}
	}
	
	@Test(expected=HttpClientException.class)
	public void testMissingMethod() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder().thenBuild();
		client.sendRequest(new TestHttpRequest());
	}
	
	@Test(expected=HttpClientException.class)
	public void testMissingHost() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder()
				.setPortAs(8080).thenBuild();
		client.sendRequest(new TestHttpRequest(HttpVerb.GET,"/test"));
	}

	@Test(expected=HttpClientException.class)
	public void testMissingPort() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder()
				.setHostAs("localhost").thenBuild();
		client.sendRequest(new TestHttpRequest(HttpVerb.GET,"/test"));
	}

	@Test(expected=HttpClientException.class)
	public void testMissingFile() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder()
				.setHostAs("localhost")
				.setPortAs(8080).thenBuild();

		client.sendRequest(new TestHttpRequest(HttpVerb.GET));
	}

	@Test
	public void testHttpClient() throws Exception {
		List<String> cookieHeaders = new ArrayList<>();
		Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();					
		Cookie cookie = new Cookie("TestCookie1", "TestCookie1");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(3600);
		cookieHeaders.add(cookieProcessor.generateHeader(cookie));
		cookie = new Cookie("TestCookie2", "TestCookie2");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(360);
		cookieHeaders.add(cookieProcessor.generateHeader(cookie));
		cookie = new Cookie("TestCookie3", "TestCookie3");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(360);
		cookieHeaders.add(cookieProcessor.generateHeader(cookie));
		
		HttpRequest basicAuthenticationRequest = HttpMessageBuilder
				.createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/test-basic-authentication")
				.setId("/test-basic-authentication")
				.setResponseAs(HttpMessageBuilder.
						createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Authenticated".getBytes())
						.thenBuild())
				.thenBuild(),
				defaultCookieRequest = HttpMessageBuilder
					.createNewHttpRequest()
					.setMethodAs("GET")
					.setResourceAs("/test-default-cookie")
					.setId("/test-default-cookie")
					.setResponseAs(HttpMessageBuilder
							.createNewHttpResponse()
							.setResponseCodeAs(200)
							.setContentBytes("Authenticated".getBytes())
							.addHeaders(new HttpHeaderImpl("Set-Cookie",cookieHeaders))
							.thenBuild())
					.thenBuild(),
				additionalHeadersRequest = HttpMessageBuilder
						.createNewHttpRequest()
						.setMethodAs("GET")
						.setResourceAs("/test-additional-headers")
						.setId("/test-additional-headers")
						.addHeaders(new HttpHeaderImpl("X-Additional-Header", "additional"))
						.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
								.setResponseCodeAs(200)
								.setContentBytes("Header Present".getBytes())
								.thenBuild())
						.thenBuild(),
				headRequest = HttpMessageBuilder.createNewHttpRequest()
						.setMethodAs("HEAD")
						.setResourceAs("/test-head")
						.setId("/test-head")
						.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
								.setResponseCodeAs(204)
								.thenBuild())
						.thenBuild(),
				requestValueRequest = HttpMessageBuilder.createNewHttpRequest()
						.setMethodAs("POST")
						.setResourceAs("/test-request-value")
						.setId("/test-request-value")
						.setContentBytes("test=true&post=true".getBytes())
						.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
								.setResponseCodeAs(200)
								.setContentBytes("Request Value Present. This is a long response. This is to ensure that the issues that existed before have been fixed".getBytes())
								.thenBuild())
						.thenBuild(),
				errorReturningRequest = HttpMessageBuilder.createNewHttpRequest()
						.setMethodAs("PUT")
						.setResourceAs("/test-error-returning")
						.setId("/test-error-returning")
						.setContentBytes("test=true&post=true".getBytes())
						.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
								.setResponseCodeAs(401)
								.setContentBytes("Error Response Present".getBytes())
								.thenBuild())
						.thenBuild();
		
		MockServer mockServer = MockServerFactory.getInstance()
				.getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration()
						.setMockingModeAs(MockingMode.MOCK)
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.addRequest(basicAuthenticationRequest)
						.addResponseAction((request, response)->{
							if(("Basic "+Base64.getEncoder().encodeToString("test:password".getBytes()))
									.equalsIgnoreCase(request.getHeaderValue("authorization")))
								return null;
							
							return HttpMessageBuilder.createNewHttpResponse()
									.setResponseCodeAs(401)
									.setContentBytes("Not authorized".getBytes())
									.thenBuild();
						}).thenBuild(),
						MockActionBuilder.createNewMockAction()
						.addRequest(defaultCookieRequest)
						.addResponseAction((context, response)->{
							if(context.getHeaderValue("cookie").contains("testCookie"))
								return null;
							return HttpMessageBuilder.createNewHttpResponse()
									.setResponseCodeAs(401)
									.setContentBytes("Not authorized".getBytes())
									.thenBuild();
						}).thenBuild(),
						MockActionBuilder.createNewMockAction()
						.addRequest(additionalHeadersRequest)
						.addResponseAction((request, response)->{
							if(request.getHeaderValue("x-additional-header")!=null)
								return null;
							else
								return HttpMessageBuilder.createNewHttpResponse()
										.setResponseCodeAs(400)
										.setContentBytes("Bad Message".getBytes())
										.thenBuild();
						}).thenBuild(),
						MockActionBuilder.createNewMockAction()
						.addRequest(headRequest).thenBuild(),
						MockActionBuilder.createNewMockAction()
						.addRequest(requestValueRequest)
						.addResponseAction((request, response)->{
							if(request.getContentBytes()!=null
									&& Arrays.equals("test=true&post=true".getBytes(),request.getContentBytes()))
								return null;
							
							return HttpMessageBuilder.createNewHttpResponse()
									.setResponseCodeAs(400)
									.setContentBytes("Bad Message".getBytes()).thenBuild();
						}).thenBuild(),
						MockActionBuilder.createNewMockAction()
						.addRequest(errorReturningRequest)
						.thenBuild());
		mockServer.start();
		try {
			MockHttpClient client = new MockHttpClientBuilder()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setUsernameAs("test")
					.setPasswordAs("password")
					.thenBuild();
					
			HttpResponse httpResponse = client.sendRequest(basicAuthenticationRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Authenticated"));			
			assertThat(httpResponse.getHeaders().isEmpty(), is(false));
			
			client = new MockHttpClientBuilder()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setDefaultCookieAs(new HttpCookie("testCookie", "testCookie"))
					.thenBuild();
			
			httpResponse = client.sendRequest(defaultCookieRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Authenticated"));
			
			List<String> cookies = Arrays.asList("TestCookie1","TestCookie2","TestCookie3");
			
			assertThat(client.getCookies().size(), is(3));
			assertThat(cookies.contains(client.getCookies().get(0).getValue()), is(true));
			assertThat(cookies.contains(client.getCookies().get(1).getValue()), is(true));
			
			client = new MockHttpClientBuilder()
					.setHostAs("localhost")
					.setPortAs(8080)
					.thenBuild();

			httpResponse = client.sendRequest(additionalHeadersRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Header Present"));
			
			httpResponse = client.sendRequest(headRequest);
			assertThat(httpResponse.getHttpCode(), is(204));
			assertThat(httpResponse.getOutput()==null, is(true));
			
			httpResponse = client.sendRequest(requestValueRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Request Value Present. This is a long response. This is to ensure that the issues that existed before have been fixed"));
			
			httpResponse = client.sendRequest(errorReturningRequest);
			assertThat(httpResponse.getHttpCode(), is(401));
			assertThat(httpResponse.getOutput(), is("Error Response Present"));
		} finally {
			mockServer.stop();
		}
	}
	
	@Test(expected=HttpClientException.class)
	public void testVerbRequiringRequestValueWithNoRequestValue() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder()
				.setHostAs("localhost")
				.setPortAs(8080)
				.thenBuild();
		
		client.sendRequest(HttpMessageBuilder
				.createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-post-with-no-request-value")
				.setId("testVerbRequiringRequestValueWithNoRequestValue")
				.thenBuild());
	}
	
	@Test(expected=HttpClientException.class)
	public void testVerbThatCannotHaveRequestValueWithRequestValue() throws Exception {
		MockHttpClient client = new MockHttpClientBuilder()
				.setHostAs("localhost")
				.setPortAs(8080)
				.thenBuild();
		
		client.sendRequest(HttpMessageBuilder
				.createNewHttpRequest()
				.setMethodAs("TRACE")
				.setResourceAs("/test-post-with-no-request-value")
				.setContentBytes("/test-post-with-no-request-value".getBytes())
				.setId("testVerbThatCannotHaveRequestValueWithRequestValue")
				.thenBuild());		
	}
	
	@Test
	public void testPrettyPrint() throws Exception {
		System.out.println(HttpClient.prettyPrintJSON("{" + 
				"\"Name\" : \"Xytrex Co.\"," + 
				"\"Description\" : \"Industrial Cleaning Supply Company\"," + 
				"\"Account Number\" : \"ABC15797531\"," +
				"\"Object:\":{\"Array\":[1,2,3,4]}" +
				"}"));
	}
	
	public static class TestHttpRequest implements HttpRequest {
		public TestHttpRequest() {
		}
		
		HttpVerb verb;
		public TestHttpRequest(HttpVerb verb) {
			this.verb = verb;
		}

		String file;
		public TestHttpRequest(HttpVerb verb, String file) {
			this.verb = verb;
			this.file = file;
		}

		@Override
		public Collection<HttpHeader> getHeaders() {
			return null;
		}
		
		@Override
		public String getHeaderValue(String name) {
			return null;
		}
		
		@Override
		public String getContentEncoding() {
			return null;
		}
		
		@Override
		public String getContent() throws UnsupportedEncodingException {
			return null;
		}
		
		@Override
		public boolean hasParameter(String name) {
			return false;
		}
		
		@Override
		public com.quakearts.tools.test.mockserver.model.HttpResponse getResponse() {
			return null;
		}
		
		@Override
		public String getResource() {
			return file;
		}
		
		@Override
		public String getMethod() {
			if(verb!=null)
				return verb.name();

			return null;
		}
		
		@Override
		public String getId() {
			return null;
		}
		
		@Override
		public byte[] getContentBytes() {
			return null;
		}

		@Override
		public List<String> getURIParameterValue(String name) {
			return null;
		}

	}
}
