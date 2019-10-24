/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie (kwaku.twumasi@quakearts.com)
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.rest.client.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.awaitility.Awaitility.*;
import static com.quakearts.tools.test.mockserver.model.impl.MockActionBuilder.*;
import static com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder.*;

import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.Cookie;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.rest.client.exception.HttpClientException;
import com.quakearts.rest.client.exception.HttpClientRuntimeException;
import com.quakearts.rest.client.test.helpers.MockHttpClient;
import com.quakearts.rest.client.test.helpers.MockHttpClientBuilder;
import com.quakearts.rest.client.test.helpers.MockHttpObjectClient;
import com.quakearts.rest.client.test.helpers.MockHttpObjectClientBuilder;
import com.quakearts.rest.client.test.helpers.MockResponse;
import com.quakearts.tools.test.mockserver.MockServer;
import com.quakearts.tools.test.mockserver.MockServerFactory;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.model.HttpHeader;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;

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
		HttpRequest httpRequest = createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/tlstest")
				.setId("/tlstest")
				.setResponseAs(createNewHttpResponse()
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
				.add(createNewMockAction()
						.setRequestAs(httpRequest)
								.thenBuild())
				.addDefaultActions((context)->{
					for(HttpHeader header:context.getHttpRequest().getHeaders())
						for(String value:header.getValues())
							context.addHeader(header.getName(), value);
				});
		mockServer.start();
		try {
			MockHttpClient client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8443)
					.setSecuredAs(true)
					.thenBuild();
			
			assertThat(client.getCookieManager(), is(notNullValue()));
			assertThat(client.getCookies(), is(notNullValue()));
			assertThat(client.getDefaultCookie(), is(nullValue()));
			assertThat(client.getHost(), is("localhost"));
			assertThat(client.getPassword(), is(nullValue()));
			assertThat(client.getPort(), is(8443));
			assertThat(client.getUserAgent(), is(nullValue()));
			assertThat(client.getUsername(), is(nullValue()));
			assertThat(client.isSecured(), is(true));
			assertThat(client.matchesHostnames(), is(false));
			assertThat(client.followsRedirects(), is(false));
			
			HttpResponse httpResponse;
			try {
				httpResponse = client.sendRequest(httpRequest);
				fail("Did not throw ssl handshake error.");
			} catch (SSLHandshakeException e) {
			}

			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8443)
					.setSecuredAs(true)
					.setMatchesHostnameAs(true)
					.thenBuild();
			
			httpResponse = client.sendRequest(httpRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Successful"));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setURLAs("https://127.0.0.1:8443")
					.setMatchesHostnameAs(true)
					.thenBuild();
			httpResponse = client.sendRequest(httpRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Successful"));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("127.0.0.1")
					.setPortAs(8443)
					.setSecuredAs(true)
					.setMatchesHostnameAs(true)
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
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost")
				.setPortAs(8080).thenBuild();

		client.sendRequest(new TestHttpRequest(null, "/test"));
	}
	
	@Test(expected=HttpClientException.class)
	public void testMissingHost() throws Exception {
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setPortAs(8080).thenBuild();
		client.sendRequest(new TestHttpRequest(HttpVerb.GET,"/test"));
	}

	@Test(expected=HttpClientException.class)
	public void testMissingPort() throws Exception {
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost").thenBuild();
		client.sendRequest(new TestHttpRequest(HttpVerb.GET,"/test"));
	}

	@Test(expected=HttpClientException.class)
	public void testMissingFile() throws Exception {
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost")
				.setPortAs(8080).thenBuild();

		client.sendRequest(new TestHttpRequest(HttpVerb.GET, null));
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
		
		HttpRequest basicAuthenticationRequest = createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/test-basic-authentication")
				.setId("/test-basic-authentication")
				.setResponseAs(
						createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Authenticated".getBytes())
						.thenBuild())
				.thenBuild();
		HttpRequest defaultCookieRequest = createNewHttpRequest()
					.setMethodAs("GET")
					.setResourceAs("/test-default-cookie")
					.setId("/test-default-cookie")
					.setResponseAs(createNewHttpResponse()
							.setResponseCodeAs(200)
							.setContentBytes("Authenticated".getBytes())
							.addHeaders(new HttpHeaderImpl("Set-Cookie",cookieHeaders))
							.thenBuild())
					.thenBuild();
		HttpRequest additionalHeadersRequest = createNewHttpRequest()
						.setMethodAs("GET")
						.setResourceAs("/test-additional-headers")
						.setId("/test-additional-headers")
						.addHeaders(new HttpHeaderImpl("X-Additional-Header", "additional"))
						.setResponseAs(createNewHttpResponse()
								.setResponseCodeAs(200)
								.setContentBytes("Header Present".getBytes())
								.thenBuild())
						.thenBuild();
		HttpRequest headRequest = createNewHttpRequest()
						.setMethodAs("HEAD")
						.setResourceAs("/test-head")
						.setId("/test-head")
						.setResponseAs(createNewHttpResponse()
								.setResponseCodeAs(204)
								.thenBuild())
						.thenBuild();
		HttpRequest head404Request = createNewHttpRequest()
				.setMethodAs("HEAD")
				.setResourceAs("/test-head-404")
				.setId("/test-head-404")
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(404)
						.thenBuild())
				.thenBuild();
		HttpRequest requestValueRequest = createNewHttpRequest()
						.setMethodAs("POST")
						.setResourceAs("/test-request-value")
						.setId("/test-request-value")
						.setContentBytes("test=true&post=true".getBytes())
						.setResponseAs(createNewHttpResponse()
								.setResponseCodeAs(200)
								.setContentBytes("Request Value Present. This is a long response. This is to ensure that the issues that existed before have been fixed".getBytes())
								.thenBuild())
						.thenBuild();
		HttpRequest errorReturningRequest = createNewHttpRequest()
						.setMethodAs("PUT")
						.setResourceAs("/test-error-returning")
						.setId("/test-error-returning")
						.setContentBytes("test=true&post=true".getBytes())
						.setResponseAs(createNewHttpResponse()
								.setResponseCodeAs(401)
								.setContentBytes("Error Response Present".getBytes())
								.thenBuild())
						.thenBuild();
		HttpRequest redirectingRequest = createNewHttpRequest()
						.setMethodAs("POST")
						.setResourceAs("/test-redirecting-request")
						.setId("/test-redirecting-request")
						.setContentBytes("test=true&post=true".getBytes())
						.setResponseAs(createNewHttpResponse()
								.setResponseCodeAs(301)
								.addHeaders(new HttpHeaderImpl("Location", "http://localhost:8080/test-redirecting-response"))
								.thenBuild())
						.thenBuild();
		HttpRequest redirectingRequestResponse = createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/test-redirecting-response")
				.setId("/test-redirecting-response")
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Redirect complete".getBytes())
						.thenBuild())
				.thenBuild();		
		HttpRequest post201Content = createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-post-content")
				.setId("/test-post-content")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(201)
						.setContentBytes("Successful".getBytes())
						.thenBuild())
				.thenBuild();	
		HttpRequest put201Content = createNewHttpRequest()
				.setMethodAs("PUT")
				.setResourceAs("/test-put-content")
				.setId("/test-put-content")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(201)
						.setContentBytes("Successful".getBytes())
						.thenBuild())
				.thenBuild();
		HttpRequest post201NoContent = createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-post-no-content")
				.setId("/test-post-no-content")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(201).thenBuild())
				.thenBuild();		
		HttpRequest put201NoContent = createNewHttpRequest()
				.setMethodAs("PUT")
				.setResourceAs("/test-put-no-content")
				.setId("/test-put-no-content")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(201).thenBuild())
				.thenBuild();
		HttpRequest get200Timeout = createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/test-request-timeout")
				.setId("/test-request-timeout")
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Did not timeout".getBytes()).thenBuild())
				.thenBuild();
		
		MockServer mockServer = MockServerFactory.getInstance()
				.getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration()
						.setMockingModeAs(MockingMode.MOCK)
						.thenBuild())
				.add(createNewMockAction()
						.setRequestAs(basicAuthenticationRequest)
						.setResponseActionAs((request, response)->{
							if(("Basic "+Base64.getEncoder().encodeToString("test:password".getBytes()))
									.equalsIgnoreCase(request.getHeaderValue("authorization"))
									&& "Custom Agent".equals(request.getHeaderValue("user-agent")))
								return null;
														
							return createNewHttpResponse()
									.setResponseCodeAs(401)
									.setContentBytes("Not authorized".getBytes())
									.thenBuild();
						}).thenBuild(),
					createNewMockAction()
						.setRequestAs(defaultCookieRequest)
						.setResponseActionAs((context, response)->{
							if(context.getHeaderValue("cookie").contains("testCookie"))
								return null;
							return createNewHttpResponse()
									.setResponseCodeAs(401)
									.setContentBytes("Not authorized".getBytes())
									.thenBuild();
						}).thenBuild(),
					createNewMockAction()
						.setRequestAs(additionalHeadersRequest)
						.setResponseActionAs((request, response)->{
							if(request.getHeaderValue("x-additional-header")!=null)
								return null;
							else
								return createNewHttpResponse()
										.setResponseCodeAs(400)
										.setContentBytes("Bad Message".getBytes())
										.thenBuild();
						}).thenBuild(),
					createNewMockAction()
						.setRequestAs(headRequest).thenBuild(),
					createNewMockAction()
						.setRequestAs(head404Request).thenBuild(),
					createNewMockAction()
						.setRequestAs(requestValueRequest)
						.setResponseActionAs((request, response)->{
							if(request.getContentBytes()!=null
									&& Arrays.equals("test=true&post=true".getBytes(),request.getContentBytes()))
								return null;
							
							return createNewHttpResponse()
									.setResponseCodeAs(400)
									.setContentBytes("Bad Message".getBytes()).thenBuild();
						}).thenBuild(),
					createNewMockAction()
						.setRequestAs(errorReturningRequest)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(redirectingRequest)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(redirectingRequestResponse)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(post201Content)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(post201NoContent)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(put201Content)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(put201NoContent)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(get200Timeout)
						.setResponseActionAs((request, response)->{
							long start = System.currentTimeMillis();
							await().atLeast(1, TimeUnit.SECONDS).until(()-> System.currentTimeMillis()-start>1000);							
							return response;
						})
						.thenBuild());
		
		mockServer.start();
		try {
			MockHttpClient client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setUsernameAs("test")
					.setPasswordAs("password")
					.setUserAgentAs("Custom Agent")
					.thenBuild();
		
			assertThat(client.isSecured(), is(false));

			HttpResponse httpResponse = client.sendRequest(basicAuthenticationRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Authenticated"));			
			assertThat(httpResponse.getHeaders().isEmpty(), is(false));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setUsernameAs("test")
					.setUserAgentAs("Custom Agent")
					.thenBuild();
			
			httpResponse = client.sendRequest(basicAuthenticationRequest);
			assertThat(httpResponse.getHttpCode(), is(401));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setPasswordAs("password")
					.setUserAgentAs("Custom Agent")
					.thenBuild();
			
			httpResponse = client.sendRequest(basicAuthenticationRequest);
			assertThat(httpResponse.getHttpCode(), is(401));
			
			HttpCookie httpCookie = new HttpCookie("testCookie", "testCookie");
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setDefaultCookieAs(httpCookie)
					.thenBuild();
			
			assertThat(client.getDefaultCookie(), is(httpCookie));
			
			httpResponse = client.sendRequest(defaultCookieRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Authenticated"));
			
			List<String> cookies = Arrays.asList("TestCookie1","TestCookie2","TestCookie3");
			
			assertThat(client.getCookies().size(), is(3));
			assertThat(cookies.contains(client.getCookies().get(0).getValue()), is(true));
			assertThat(cookies.contains(client.getCookies().get(1).getValue()), is(true));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setURLAs("http://localhost:8080")
					.thenBuild();

			httpResponse = client.sendRequest(additionalHeadersRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Header Present"));
			
			httpResponse = client.sendRequest(headRequest);
			assertThat(httpResponse.getHttpCode(), is(204));
			assertThat(httpResponse.getOutput(), is(notNullValue()));
			assertThat(httpResponse.getOutput().isEmpty(), is(true));

			httpResponse = client.sendRequest(head404Request);
			assertThat(httpResponse.getHttpCode(), is(404));
			assertThat(httpResponse.getOutput(), is(notNullValue()));
			assertThat(httpResponse.getOutput().isEmpty(), is(true));

			httpResponse = client.sendRequest(requestValueRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Request Value Present. This is a long response. This is to ensure that the issues that existed before have been fixed"));
			
			httpResponse = client.sendRequest(post201Content);
			assertThat(httpResponse.getHttpCode(), is(201));
			assertThat(httpResponse.getOutput(), is("Successful"));
			
			httpResponse = client.sendRequest(put201Content);
			assertThat(httpResponse.getHttpCode(), is(201));
			assertThat(httpResponse.getOutput(), is("Successful"));
			
			httpResponse = client.sendRequest(post201NoContent);
			assertThat(httpResponse.getHttpCode(), is(201));
			assertThat(httpResponse.getOutput(), is(""));
			
			httpResponse = client.sendRequest(put201NoContent);
			assertThat(httpResponse.getHttpCode(), is(201));
			assertThat(httpResponse.getOutput(), is(""));

			requestValueRequest = createNewHttpRequest()
					.setMethodAs("POST")
					.setResourceAs("/test-request-value")
					.setId("/test-request-value")
					.setContentBytes("".getBytes())
				.thenBuild();
			
			httpResponse = client.sendRequest(requestValueRequest);
			assertThat(httpResponse.getHttpCode(), is(400));
			
			httpResponse = client.sendRequest(errorReturningRequest);
			assertThat(httpResponse.getHttpCode(), is(401));
			assertThat(httpResponse.getOutput(), is("Error Response Present"));
			
			client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost")
				.setPortAs(8080)
				.setFollowRedirectAs(true)
				.thenBuild();
			
			assertThat(client.followsRedirects(), is(true));
			
			httpResponse = client.sendRequest(redirectingRequest);
			assertThat(httpResponse.getHttpCode(), is(200));
			assertThat(httpResponse.getOutput(), is("Redirect complete"));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.thenBuild();
			
			httpResponse = client.sendRequest(redirectingRequest);
			assertThat(httpResponse.getHttpCode(), is(301));
			assertThat(httpResponse.getOutput(), is(notNullValue()));
			assertThat(httpResponse.getOutput().isEmpty(), is(true));
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8081)
					.setConnectTimeoutAs(0.5)
					.thenBuild();
			final MockHttpClient lambdaClient = client;
			await().atMost(1, TimeUnit.SECONDS)
				.until(()->{
						try {
							lambdaClient.sendRequest(additionalHeadersRequest);
						} catch (SocketTimeoutException e) {
							return true;
						}
						return false;
					}
				);
			
			client = MockHttpClientBuilder.getInstance()
					.createNewHttpClient()
					.setHostAs("localhost")
					.setPortAs(8080)
					.setReadTimeoutAs(0.5)
					.thenBuild();
			
			final MockHttpClient lambdaClient2 = client;
			await().atMost(1, TimeUnit.SECONDS)
				.until(()->{
					try {
						lambdaClient2.sendRequest(get200Timeout);
					} catch (SocketTimeoutException e) {
						return true;
					}
					return false;
				});
			
		} finally {
			mockServer.stop();
		}
	}
	
	@Test
	public void testMockHttpObject() throws Exception {
		HttpRequest post200WithContent = createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-mock-object?return=200")
				.setId("/test-mock-object?return=200")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Test Response".getBytes())
						.thenBuild())
				.thenBuild();

		HttpRequest post200WithoutContent = createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-mock-object")
				.setId("POST-/test-mock-object")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Test Response".getBytes())
						.thenBuild())
				.thenBuild();

		HttpRequest post400 = createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-mock-object?return=400")
				.setId("/test-mock-object?return=400")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(400)
						.setContentBytes("Test Response".getBytes())
						.thenBuild())
				.thenBuild();
		
		HttpRequest getEmpty200 = createNewHttpRequest()
				.setMethodAs("GET")
				.setResourceAs("/test-mock-object")
				.setId("GET-/test-mock-object")
				.setContentBytes("Test".getBytes())
				.setResponseAs(createNewHttpResponse()
						.setResponseCodeAs(200)
						.setContentBytes("Test Response".getBytes())
						.thenBuild())
				.thenBuild();
		
		MockServer mockServer = MockServerFactory.getInstance()
				.getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration()
						.setMockingModeAs(MockingMode.MOCK)
						.setPortAs(8082)
						.thenBuild())
				.add(createNewMockAction()
						.setRequestAs(post200WithContent)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(post200WithoutContent)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(post400)
						.thenBuild(),
					createNewMockAction()
						.setRequestAs(getEmpty200)
						.thenBuild());
		mockServer.start();
		try {
			MockHttpObjectClient client = new MockHttpObjectClientBuilder()
					.setURLAs("http://localhost:8082")
					.thenBuild();
			
			MockResponse response = client.sendRequest(post200WithContent, "200");
			assertThat(response.getData(), is(post200WithContent.getResponse().getContent()));
			assertThat(response.getReturnCode(), is(post200WithContent.getResponse().getResponseCode()));

			try {				
				response = client.sendRequest(post400, "400");
				fail("Exception was not thrown");
			} catch (HttpClientException e) {
				assertThat(e.getMessage(), is(post400.getResponse().getContent()));
			}

			response = client.sendRequest(post200WithoutContent);
			assertThat(response.getData(), is(post200WithoutContent.getResponse().getContent()));
			assertThat(response.getReturnCode(), is(post200WithoutContent.getResponse().getResponseCode()));

			response = client.sendRequest();
			assertThat(response.getData(), is(getEmpty200.getResponse().getContent()));
			assertThat(response.getReturnCode(), is(getEmpty200.getResponse().getResponseCode()));
			
			try {
				client.setReturn100(true);
				response = client.sendRequest(post200WithoutContent);
				fail("Exception was not thrown");
			} catch (HttpClientException e) {
				assertThat(e.getMessage(), is("Test Response"));
			}
			
			try {
				client.setReturn100(false);
				client.throwHttpClientException();
				fail("Exception was not thrown");
			} catch (HttpClientException e) {
				assertTrue(e.getCause() instanceof NoSuchMethodException);
				assertThat(e.getMessage(), is("Unable to coerce HTTP response to java.lang.String"));
			}
		} finally {
			mockServer.stop();
		}
	}
	
	@Test(expected=HttpClientException.class)
	public void testVerbRequiringRequestValueWithNoRequestValue() throws Exception {
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost")
				.setPortAs(8080)
				.thenBuild();
		
		client.sendRequest(createNewHttpRequest()
				.setMethodAs("POST")
				.setResourceAs("/test-post-with-no-request-value")
				.setId("testVerbRequiringRequestValueWithNoRequestValue")
				.thenBuild());
	}
	
	@Test(expected=HttpClientException.class)
	public void testVerbThatCannotHaveRequestValueWithRequestValue() throws Exception {
		MockHttpClient client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient()
				.setHostAs("localhost")
				.setPortAs(8080)
				.thenBuild();
		
		client.sendRequest(createNewHttpRequest()
				.setMethodAs("TRACE")
				.setResourceAs("/test-post-with-no-request-value")
				.setContentBytes("/test-post-with-no-request-value".getBytes())
				.setId("testVerbThatCannotHaveRequestValueWithRequestValue")
				.thenBuild());		
	}
	
	@Test(expected=HttpClientRuntimeException.class)
	public void testBuildWithNonHttpHttpsURL() throws Exception {
		MockHttpClientBuilder.getInstance()
			.createNewHttpClient()
			.setURLAs("file:pom.xml");
	}
	
	@Test(expected=HttpClientRuntimeException.class)
	public void testBuildWithMalformedURL() throws Exception {
		MockHttpClientBuilder.getInstance()
			.createNewHttpClient()
			.setURLAs("http/pom.xml");
	}
	
	@Test
	public void testHttpResponse() throws Exception {
		Map<String, List<String>> headers= new HashMap<>();
		headers.put("header-with-values", Arrays.asList("Test"));
		headers.put("empty-header", Collections.emptyList());
		
		HttpResponse httpResponse = new HttpResponse("Test output".getBytes(), 
				"test message", 200, headers);
		assertThat(httpResponse.getOutput(), is("Test output"));
		assertThat(httpResponse.getMessage(), is("test message"));
		assertThat(httpResponse.getHttpCode(), is(200));
		assertThat(httpResponse.getHeaders(), is(headers));
		assertThat(httpResponse.getHeaderList("header-with-values"), 
				is(headers.get("header-with-values")));
		assertThat(httpResponse.getHeader("header-with-values"), is("Test"));
		assertThat(httpResponse.getHeader("empty-header"), is(nullValue()));
		assertThat(httpResponse.getHeader("non-existent-header"), is(nullValue()));
		
		httpResponse = new HttpResponse(null, null, 0, null);
		assertThat(httpResponse.getOutput(), is(""));
	}
	
	@Test
	public void testURLPort() throws Exception {
		MockHttpClient client = MockHttpClientBuilder
				.getInstance().createNewHttpClient()
			.setURLAs("https://www.quakearts.com").thenBuild();
		
		assertThat(client.getPort(), is(443));
		client = MockHttpClientBuilder.getInstance()
				.createNewHttpClient().setURLAs("http://demo.quakearts.com")
				.thenBuild();
		assertThat(client.getPort(), is(80));		
	}
	
	public static class TestHttpResponse implements com.quakearts.tools.test.mockserver.model.HttpResponse {

		@Override
		public Collection<HttpHeader> getHeaders() {
			return Collections.emptyList();
		}

		@Override
		public String getHeaderValue(String name) {
			return null;
		}

		@Override
		public List<String> getHeaderValues(String name) {
			return Collections.emptyList();
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
		public byte[] getContentBytes() {
			return null;
		}

		@Override
		public int getResponseCode() {
			return 100;
		}
		
	}
	
	public static class TestHttpRequest implements HttpRequest {
		HttpVerb verb;
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
			return new TestHttpResponse();
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

		@Override
		public List<String> getHeaderValues(String name) {
			return null;
		}

	}
}
