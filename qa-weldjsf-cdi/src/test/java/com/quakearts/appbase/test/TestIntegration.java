package com.quakearts.appbase.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;

import org.junit.Test;

import com.quakearts.appbase.Main;
import com.quakearts.appbase.test.experiments.TestInjectImpl;
import com.quakearts.appbase.test.experiments.TestSubInjectDecorator;
import com.quakearts.appbase.test.experiments.TestSubInjectImpl;
import com.quakearts.appbase.test.helpers.TestHttpClient;
import com.quakearts.appbase.test.helpers.TestHttpClientBuilder;
import com.quakearts.appbase.test.helpers.TestMain;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;

public class TestIntegration {

	@Test
	public void testJSFServices() throws Exception {
		Main.main(new String[] {TestMain.class.getName(),"-dontwaitinmain"});

		TestHttpClient client = new TestHttpClientBuilder()
				.setHostAs("localhost")
				.setPortAs(8180)
				.thenBuild();
		
		HttpResponse response = client.sendRequest("/test/test.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(response.getOutput().contains("OK"), is(true));
		assertThat(TestInjectImpl.saidHello(), is(true));
		assertThat(TestInjectImpl.testSubInjectLoaded(), is(true));		
		assertThat(TestInjectImpl.transactionWorked(), is(true));
		assertThat(TestSubInjectImpl.hasDoneSomething(), is(true));
		assertThat(TestSubInjectDecorator.decoratedSubInject(), is(true));
		
		response = client.sendRequest("/test/testRequest.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		
		String responseOutput = response.getOutput();
		
		response = client.sendRequest("/test/testRequest.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(responseOutput, is(not(response.getOutput())));
		
		response = client.sendRequest("/test/testSession.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		responseOutput = response.getOutput();
		
		response = client.sendRequest("/test/testSession.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(responseOutput, is(response.getOutput()));
		
		client.getCookieManager().getCookieStore().removeAll();
		
		response = client.sendRequest("/test/testSession.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(responseOutput, is(not(response.getOutput())));
		
		response = client.sendRequest("/test/testConversation.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		responseOutput = extractHashCode(response.getOutput());

		response = client.sendRequest("/test/testConversation.jsf?cid=1", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(responseOutput, is(extractHashCode(response.getOutput())));

		response = client.sendRequest("/test/testConversation.jsf", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(responseOutput, is(not(extractHashCode(response.getOutput()))));
	}
	
	private String extractHashCode(String response) {
		int bodyIndex = response.indexOf("<body>") + 7;
		int endIndex = response.indexOf("<", bodyIndex);
		return response.substring(bodyIndex,endIndex);
	}

}
