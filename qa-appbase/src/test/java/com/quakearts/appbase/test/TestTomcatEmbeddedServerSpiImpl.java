package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.junit.Test;

import com.quakearts.appbase.spi.EmbeddedWebServerSpi;
import com.quakearts.appbase.spi.impl.TomcatEmbeddedServerSpiImpl;

public class TestTomcatEmbeddedServerSpiImpl {

	@Test
	public void testTomcatEmbeddedServerSpiImpl() {
		EmbeddedWebServerSpi serverSpi = new TomcatEmbeddedServerSpiImpl();
		serverSpi.initiateEmbeddedWebServer();
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
			assertThat(connection.getHeaderField("Server"), is("Test Server"));
			assertThat(connection.getHeaderField("X-Powered-By") != null, is(true));
		} catch (IOException e) {
			fail("Unable to connect:" +e.getMessage());
		}

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/testRoot").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
		} catch (IOException e) {
			fail("Unable to connect:" +e.getMessage());
		}
		
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8190, "/testssl/test").openConnection();
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
		} catch (IOException e) {
			fail("Unable to connect:" +e.getMessage());
		}

		try {
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
			
			HttpsURLConnection connection = (HttpsURLConnection) new URL("https", "localhost", 8543, "/testssl/test").openConnection();
			
			connection.setSSLSocketFactory(context.getSocketFactory());
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     			
			connection.setHostnameVerifier((string, sslContext)->{
				return true;
			});
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));
		} catch (NoSuchAlgorithmException | IOException | KeyManagementException e) {
			fail("Unable to connect:" +e.getMessage());
		}

		try {
			HttpURLConnection connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test-secured").openConnection();

			connection.connect();
			
			assertThat(connection.getResponseCode(), is(401));			

			connection = (HttpURLConnection) new URL("http", "localhost", 8180, "/test/test-secured").openConnection();
			
			connection.addRequestProperty("Authorization","Basic "+Base64.getEncoder().encodeToString("test:test".getBytes()));
			
			connection.connect();
			
			assertThat(connection.getResponseCode(), is(200));			
		} catch (IOException e) {
			fail("Unable to connect:" +e.getMessage());
		}
	}
	
}
