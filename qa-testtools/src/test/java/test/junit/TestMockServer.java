package test.junit;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Before;
import org.junit.Test;

import com.quakearts.tools.test.mockserver.MockServer;
import com.quakearts.tools.test.mockserver.MockServerFactory;
import com.quakearts.tools.test.mockserver.configuration.Configuration.MockingMode;
import com.quakearts.tools.test.mockserver.configuration.impl.ConfigurationBuilder;
import com.quakearts.tools.test.mockserver.exception.MockServerRuntimeException;
import com.quakearts.tools.test.mockserver.model.impl.HttpHeaderImpl;
import com.quakearts.tools.test.mockserver.model.impl.HttpMessageBuilder;
import com.quakearts.tools.test.mockserver.model.impl.MockActionBuilder;

public class TestMockServer {

	@Test
	public void testStartAndStopWithAllConfigurationsSet() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configureFromFile("src/test/resources/testmock7.config");
		try {
			mockServer.start();
		} finally {
			mockServer.stop();			
		}
	}

	@Test
	public void testTestStartAndStopWithMinimumConfigurationsSet() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToMock("https://localhost:4080")
						.thenBuild());
		
		try {
			mockServer.start();
		} finally {
			mockServer.stop();			
		}
	}
	
	@Test
	public void testStartTwice() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToMock("https://localhost:4080")
						.thenBuild());
		
		try {
			mockServer.start();
			mockServer.start();
		} finally {
			mockServer.stop();			
		}
	}
	
	@Test(expected=MockServerRuntimeException.class)
	public void testMockingModeWithoutMockActions() throws Exception {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
			.configure(ConfigurationBuilder
					.newConfiguration().setMockingModeAs(MockingMode.MOCK)
					.setURLToMock("https://localhost:4080")
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
						.setURLToMock("https://localhost:4080")
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.addMatcher((httpRequest, httpRequestToMatch)->{
							return httpRequest.getResource().equals(httpRequestToMatch.getResource());
						})
						.addResponseAction((response)->{
							System.out.println("Responding...");
							return response;
						}).addRequest(HttpMessageBuilder
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
				.add(MockActionBuilder.createNewMockAction().addRequest(HttpMessageBuilder
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
									.addHeaders(new HttpHeaderImpl("Content-Type", "application/json"))
									.thenBuild())
								.thenBuild())
						.thenBuild());
		
		try {
			mockServer.start();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/test/mock").openConnection();
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
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test/mock/").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(500));
			assertThat(connection.getResponseMessage(), is("No matching httpRequest found"));
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test/mock/default").openConnection();
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
						.setURLToMock("https://localhost:4080")
						.thenBuild())
				.add(MockActionBuilder.createNewMockAction()
						.addMatcher((httpRequest, httpRequestToMatch)->{
							return httpRequest.getResource().equals(httpRequestToMatch.getResource());
						}).addResponseAction((response)->{
							System.out.println("Responding...");
							return response;
						}).addRequest(HttpMessageBuilder
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
								context.sendHttpError(400, "Bad Message");;
						},(context)->{
							if(context.getHttpRequest().getResource().equals("/test/write/to/output")) {
								context.addHeader("Content-Type", "application/json");
								context.writeToOutput(200, "{\"test\":\"value\"}");
							}
						});
		
		try {
			mockServer.start();
			
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/test/send/http/error").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
	
			assertThat(connection.getResponseCode(), is(404));
			
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test/send/http/error/with/message").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
	
			assertThat(connection.getResponseCode(), is(400));
			assertThat(connection.getResponseMessage(), is("Bad Message"));
			
			connection.disconnect();
		} finally {
			mockServer.stop();			
		}
	}
	
	@Test
	public void testRecordMode() throws Exception {
		MockServer mockingServer = buildAndStartMockingModeServer(),
				recordingServer = buildAndStartRecordModeServer();
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.getDoInput();
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			InputStream in = connection.getInputStream();
			int read;
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));		
			File file = new File("http-message/GET-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();
						
			connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("POST");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.getDoOutput();
			connection.getDoInput();
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));
			file = new File("http-message/POST-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("DELETE");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.getDoInput();
			connection.connect();
			assertThat(connection.getResponseCode(), is(200));
			bos = new ByteArrayOutputStream();
			in = connection.getInputStream();
			while ((read=in.read())!=-1) {
				bos.write(read);
			}
			assertThat(new String(bos.toByteArray()), is("{\"test\":\"value\"}"));
			file = new File("http-message/DELETE-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();

			connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("HEAD");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			assertThat(connection.getResponseCode(), is(204));			
			file = new File("http-message/HEAD-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("HEAD");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.getDoOutput();
			connection.getOutputStream().write("{\"test\":\"value\"}".getBytes());
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));			
			connection.disconnect();
			
			connection = (HttpURLConnection) new URL("http://localhost:8080/test").openConnection();
			connection.setRequestMethod("PUT");
			connection.addRequestProperty("Content-Type", "application/json");
			connection.connect();
			assertThat(connection.getResponseCode(), is(500));
			file = new File("http-message/PUT-https---localhost-4443-test.mock");
			assertThat(file.exists(), is(true));
			file.delete();
			connection.disconnect();
		} finally {
			mockingServer.stop();
			recordingServer.stop();
		}
	}
	
	private MockServer buildAndStartMockingModeServer() {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.MOCK)
						.setURLToMock("https://test.mocking.server")
						.setPortAs(4443)
						.useTLS(true)
						.setKeyStoreAs("conf/tomcat.keystore")
						.setKeyStoreTypeAs("JCEKS")
						.setKeyPasswordAs("password1")
						.thenBuild())
				.addDefaultActions((context)->{
					context.addHeader("Server", "Mock Server V1.0");
				}).add(MockActionBuilder.createNewMockAction()
						.addRequest(HttpMessageBuilder
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
						.addRequest(HttpMessageBuilder
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
						.addRequest(HttpMessageBuilder
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
						.addRequest(HttpMessageBuilder
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
				.thenBuild());
		
		mockServer.start();
		return mockServer;
	}
	
	private MockServer buildAndStartRecordModeServer() {
		MockServer mockServer = MockServerFactory.getInstance().getMockServer()
				.configure(ConfigurationBuilder
						.newConfiguration().setMockingModeAs(MockingMode.RECORD)
						.setURLToMock("https://localhost:4443")
						.thenBuild());
		
		mockServer.start();
		return mockServer;
	}
	
	@Before
	public void setSSLContext() throws Exception {
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
}
