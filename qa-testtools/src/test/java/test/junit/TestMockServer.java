package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.quakearts.tools.test.mockserver.MockServer;
import com.quakearts.tools.test.mockserver.MockServerFactory;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.impl.MockServerServlet;
import com.quakearts.tools.test.mockserver.impl.TestMockingServlet;
import com.quakearts.tools.test.mockserver.model.HttpRequest;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;
import com.quakearts.tools.test.mockserver.model.impl.MockActionBuilder;
import com.quakearts.tools.test.mockserver.store.impl.MockServletHttpMessageStore;

public class TestMockServer {

	@Test
	public void testStartAndStopWithAllConfigurationsSet() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configureFromFile("src/test/resources/testmock7.config");
		try {
			mockServer.start();
			
			HttpsURLConnection connection = (HttpsURLConnection) new URL("https://localhost:5080/").openConnection();
			connection.setHostnameVerifier((hostname,sslSession)-> {return true;});
			connection.setRequestMethod("GET");
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));
			connection.disconnect();
		} finally {
			mockServer.stop();			
		}
	}

	@Test
	public void testTestStartAndStopWithMinimumConfigurationsSetAndStartTwice() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToRecord("https://localhost:4080")
						.thenBuild());
		
		try {
			mockServer.start();
			mockServer.start();
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/").openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));
			connection.disconnect();
		} finally {
			mockServer.stop();			
		}
	}
	
	@Test(expected=MockServerRuntimeException.class)
	public void testMockingModeWithoutMockActions() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
			.configure(ConfigurationBuilder
					.newConfiguration().setMockingModeAs(MockingMode.MOCK)
					.setURLToRecord("https://localhost:4080")
					.thenBuild());
		try {
			mockServer.start();
		} finally {
			mockServer.stop();			
		}
	}

	@Test
	public void testMockingMode() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.MOCK)
						.setURLToRecord("https://localhost:4080")
						.setPortAs(4082)
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setMatcherAs((httpRequest, httpRequestToMatch)->{
							return httpRequest.getResource().equals(httpRequestToMatch.getResource());
						})
						.setResponseActionAs((context, response)->{
							System.out.println("Responding...");
							return response;
						}).setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId")
								.setMethodAs("GET")
								.setResourceAs("/test/mock")
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.setResponseAs(HttpMessageBuilder
									.createNewHttpResponse()
									.setContentBytes("{\"status\":\"ok\"}".getBytes())
									.setResponseCodeAs(200)
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
									.thenBuild())
								.thenBuild())
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setMatcherAs((httpRequest, httpRequestToMatch)->{
							return httpRequest.getResource().equals(httpRequestToMatch.getResource());
						}).setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId")
								.setMethodAs("POST")
								.setResourceAs("/test/mock/default")
								.setContentBytes("{\"test\":\"value\"}".getBytes())
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.setResponseAs(HttpMessageBuilder
									.createNewHttpResponse()
									.setContentBytes("{\"status\":\"ok\"}".getBytes())
									.setResponseCodeAs(200)
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"),
											new HttpHeaderImpl("Set-Cookie", Arrays.asList("Cookie=Cookie1", "Cookie=Cookie2")))
									.thenBuild())
								.thenBuild())
						.thenBuild());
		
		try {
			mockServer.start();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:4082/test/mock").openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			int read;
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"status\":\"ok\"}"));
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4082/test/mock/notfound").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(500));
			
			connection = (HttpURLConnection) new URL("http://localhost:4082/test/mock/default").openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"status\":\"ok\"}"));
			assertThat(connection.getHeaderFields().containsKey("Set-Cookie"), is(true));
			assertThat(connection.getHeaderFields().get("Set-Cookie").size(), is(2));
			connection.disconnect();
		} finally {
			mockServer.stop();			
		}
	}

	@Test
	public void testDefaultActions() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.MOCK)
						.setURLToRecord("https://localhost:4080")
						.setPortAs(4081)
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setMatcherAs((httpRequest, httpRequestToMatch)->{
							return httpRequest.getResource().equals(httpRequestToMatch.getResource());
						}).setResponseActionAs((context, response)->{
							System.out.println("Responding...");
							return response;
						}).setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId")
								.setMethodAs("GET")
								.setResourceAs("/test/mock")
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.setResponseAs(HttpMessageBuilder
									.createNewHttpResponse()
									.setContentBytes("{\"status\":\"ok\"}".getBytes())
									.setResponseCodeAs(200)
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
									.thenBuild())
								.thenBuild())
						.thenBuild()).addDefaultActions((context)->{
							if(context.getHttpRequest().getResource().equals("/test/send/http/error"))
								context.sendHttpError(404);
						},(context)->{
							if(context.getHttpRequest().getResource().equals("/test/send/http/error/with/message"))
								context.sendHttpError(400, "Bad Message");
						},(context)->{
							if(context.getHttpRequest().getResource().equals("/test/write/to/output")) {
								context.addHeader("Content-Type", "application/json");
								context.writeToOutput(200, "{\"test\":\"value\",\"more\":\"values\", \"even-more\":\"values\",\"overflowing\":\"values\"}"); 
							}
						});
		
		try {
			mockServer.start();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:4081/test/send/http/error").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
	
			assertThat(connection.getResponseCode(), is(404));
			
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4081/test/send/http/error/with/message").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
	
			assertThat(connection.getResponseCode(), is(400));			
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4081/test/write/to/output").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int read;
			while ((read=connection.getInputStream().read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\",\"more\":\"values\", \"even-more\":\"values\",\"overflowing\":\"values\"}"));
			connection.disconnect();

		} finally {
			mockServer.stop();			
		}
	}
	
	@Test
	public void testRecordMode() throws Exception {
		MockServer recordingServer = buildAndStartRecordModeServer();
		MockServer recordingServerWithContractsDishonored = buildAndStartRecordModeServerWithContractsDishonored();
		Tomcat mockingServer = buildAndStartMockingModeServer(4443);
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			int read;
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));		
			connection.disconnect();
			File file = new File("http-messages"+File.separator+"GET-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			HttpRequest httpRequest = MockServletHttpMessageStore.getInstance().findRequestIdentifiedBy("GET-https://localhost:4443/test");
			assertThat(httpRequest.getMethod(), is("GET"));
			assertThat(httpRequest.getResource(), is("/test"));
			assertThat(httpRequest.getHeaders()!=null, is(true));
			assertThat(httpRequest.getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getContentBytes() == null, is(true));
			assertThat(httpRequest.getResponse()!=null, is(true));
			assertThat(httpRequest.getResponse().getResponseCode(), is(200));
			assertThat(httpRequest.getResponse().getHeaders()!=null, is(true));
			assertThat(httpRequest.getResponse().getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getResponse().getContent(), is("{\"test\":\"value\"}"));
			file.delete();
						
			connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("POST");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"status\":\"ok\"}"));
			connection.disconnect();

			file = new File("http-messages"+File.separator+"POST-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));			
			httpRequest = MockServletHttpMessageStore.getInstance().findRequestIdentifiedBy("POST-https://localhost:4443/test");
			assertThat(httpRequest.getMethod(), is("POST"));
			assertThat(httpRequest.getResource(), is("/test"));
			assertThat(httpRequest.getHeaders()!=null, is(true));
			assertThat(httpRequest.getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getContent(), is("{\"test\":\"value\"}"));
			assertThat(httpRequest.getResponse()!=null, is(true));
			assertThat(httpRequest.getResponse().getResponseCode(), is(200));
			assertThat(httpRequest.getResponse().getHeaders()!=null, is(true));
			assertThat(httpRequest.getResponse().getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getResponse().getContent(), is("{\"status\":\"ok\"}"));
			file.delete();
			
			connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("DELETE");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"status\":\"ok\"}"));
			file = new File("http-messages"+File.separator+"DELETE-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();

			connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("HEAD");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			assertThat(connection.getResponseCode(), is(204));			
			file = new File("http-messages"+File.separator+"HEAD-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("HEAD");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));			
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4084/test").openConnection();
			connection.setRequestMethod("PUT");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));
			file = new File("http-messages"+File.separator+"PUT-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(false));
			file.delete();
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:4085/test-dishonored").openConnection();
			connection.setRequestMethod("POST");
			connection.connect();
			assertThat(connection.getResponseCode(), is(204));
			connection.disconnect();
									
			connection = (HttpURLConnection) new URL("http://localhost:4085/test-dishonored").openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			assertThat(connection.getResponseCode(), is(204));
			connection.disconnect();
		} finally {
			recordingServer.stop();
			recordingServerWithContractsDishonored.stop();
			try {
				mockingServer.stop();
			} catch (Exception e) {
			}
		}
	}
	
	@Test
	public void testMixedMode() throws Exception {
		MockServer recordingServer = buildAndStartMixedModeServer();
		Tomcat mockingServer = buildAndStartMockingModeServer(4444);
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:4086/test").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			int read;
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));		
			connection.disconnect();
			File file = new File("http-messages"+File.separator+"GET-https---localhost-4444-test.mock");
			assertThat(file.exists(), is(true));
			HttpRequest httpRequest = MockServletHttpMessageStore.getInstance().findRequestIdentifiedBy("GET-https://localhost:4444/test");
			assertThat(httpRequest.getMethod(), is("GET"));
			assertThat(httpRequest.getResource(), is("/test"));
			assertThat(httpRequest.getHeaders()!=null, is(true));
			assertThat(httpRequest.getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getContentBytes() == null, is(true));
			assertThat(httpRequest.getResponse()!=null, is(true));
			assertThat(httpRequest.getResponse().getResponseCode(), is(200));
			assertThat(httpRequest.getResponse().getHeaders()!=null, is(true));
			assertThat(httpRequest.getResponse().getHeaders().isEmpty(), is(false));
			assertThat(httpRequest.getResponse().getContent(), is("{\"test\":\"value\"}"));
			file.delete();
			
			connection = (HttpURLConnection) new URL("http://localhost:4086/test-notrecorded").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.setDoInput(true);
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));		
			connection.disconnect();
		} finally {
			recordingServer.stop();
			try {
				mockingServer.stop();
			} catch (Exception e) {
			}
		}
	}
	
	private Tomcat buildAndStartMockingModeServer(int port) {
		TestMockingServlet testMockingServlet = new TestMockingServlet()
				.addDefaultActions((context)->{
					context.addHeader("Server", "Mock Server V1.0");
				}).add(MockActionBuilder.createNewMockAction()
					.setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId1")
								.setMethodAs("GET")
								.setResourceAs("/test")
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.setResponseAs(HttpMessageBuilder
									.createNewHttpResponse()
									.setContentBytes("{\"test\":\"value\"}".getBytes())
									.setResponseCodeAs(200)
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
									.thenBuild())
								.thenBuild())
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId2")
								.setMethodAs("POST")
								.setResourceAs("/test")
								.setContentBytes("{\"test\":\"value\"}".getBytes())
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.addHeaders(new HttpHeaderImpl("Content-Length", "16"))
								.setResponseAs(HttpMessageBuilder
									.createNewHttpResponse()
									.setContentBytes("{\"status\":\"ok\"}".getBytes())
									.setResponseCodeAs(200)
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
									.thenBuild())
								.thenBuild())
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setRequestAs(HttpMessageBuilder
							.createNewHttpRequest()
							.setId("testId3")
							.setMethodAs("DELETE")
							.setResourceAs("/test")
							.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
							.setResponseAs(HttpMessageBuilder
								.createNewHttpResponse()
								.setContentBytes("{\"status\":\"ok\"}".getBytes())
								.setResponseCodeAs(200)
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.thenBuild())
							.thenBuild())
				.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.setRequestAs(HttpMessageBuilder
							.createNewHttpRequest()
							.setId("testId4")
							.setMethodAs("HEAD")
							.setResourceAs("/test")
							.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
							.setResponseAs(HttpMessageBuilder
								.createNewHttpResponse()
								.setResponseCodeAs(204)
								.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
								.thenBuild())
							.thenBuild())
				.thenBuild())
				//POST without input
				.add(MockActionBuilder.createNewMockAction()
						.setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId5")
								.setMethodAs("POST")
								.setResourceAs("/test-dishonored")
								.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
										.setResponseCodeAs(204)
										.thenBuild())
								.thenBuild())
						.thenBuild())
				//DELETE with input
				.add(MockActionBuilder.createNewMockAction()
						.setRequestAs(HttpMessageBuilder
								.createNewHttpRequest()
								.setId("testId5")
								.setMethodAs("DELETE")
								.setResourceAs("/test-dishonored")
								.setContentBytes("{\"test\":\"value\"}".getBytes())
								.setResponseAs(HttpMessageBuilder.createNewHttpResponse()
										.setResponseCodeAs(204)
										.thenBuild())
								.thenBuild())
						.thenBuild());
		Tomcat tomcat = new Tomcat();

		File file = new File("tomcat-mock-test");
		File webappsFile = new File(file,"webapps");
		
		if(!webappsFile.exists()) {
			webappsFile.mkdir();
		}

		try {
			tomcat.setBaseDir(file.getCanonicalPath());
		} catch (IOException e) {
			throw new MockServerRuntimeException("Unable to start embedded server", e);
		}

		tomcat.setPort(port);
		
		SSLHostConfig hostConfig = new SSLHostConfig();
		hostConfig.setProtocols("TLSv1.2");
		hostConfig.setCertificateKeystoreType("JCEKS");
		hostConfig.setCertificateKeystorePassword("password1");
		hostConfig.setCertificateKeystoreFile("conf/tomcat.keystore");
		
		tomcat.getConnector().addSslHostConfig(hostConfig);
		tomcat.getConnector().setScheme("https");
		tomcat.getConnector().setProperty("SSLEnabled", "true");
		tomcat.getConnector().setSecure(true);				

		Context context = tomcat.addContext("", "");

		tomcat.addServlet("", MockServerServlet.class.getSimpleName(), testMockingServlet);
		context.addServletMappingDecoded("/*", MockServerServlet.class.getSimpleName());
					
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			throw new MockServerRuntimeException("Unable to start embedded server", e);
		}
        
        new Thread(()->{
            tomcat.getServer().await();
        }).start();

		return tomcat;
	}
	
	private MockServer buildAndStartRecordModeServer() {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToRecord("https://localhost:4443")
						.setPortAs(4084)
						.thenBuild());
		
		mockServer.start();
		return mockServer;
	}
	
	private MockServer buildAndStartMixedModeServer() {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.MIXED)
						.setURLToRecord("https://localhost:4444")
						.setPortAs(4086)
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
								.setRequestAs(HttpMessageBuilder
										.createNewHttpRequest()
										.setId("testId1")
										.setMethodAs("GET")
										.setResourceAs("/test-notrecorded")
										.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
										.setResponseAs(HttpMessageBuilder
											.createNewHttpResponse()
											.setContentBytes("{\"test\":\"value\"}".getBytes())
											.setResponseCodeAs(200)
											.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
											.thenBuild())
										.thenBuild())
								.thenBuild());
		
		mockServer.start();
		return mockServer;
	}
	
	private MockServer buildAndStartRecordModeServerWithContractsDishonored() {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToRecord("https://localhost:4443")
						.setDisHonorRESTContractAs(true)
						.setPortAs(4085)
						.thenBuild());
		
		mockServer.start();
		return mockServer;
	}
	
	static SSLSocketFactory defaultSocketFactory;
	
	@BeforeClass
	public static void setSSLContext() throws Exception {
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
		defaultSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
		HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	}
	
	@AfterClass
	public static void restore() {
		HttpsURLConnection.setDefaultSSLSocketFactory(defaultSocketFactory);
	}
}
