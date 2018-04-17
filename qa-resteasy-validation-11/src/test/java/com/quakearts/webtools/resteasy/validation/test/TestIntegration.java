package com.quakearts.webtools.resteasy.validation.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.quakearts.appbase.Main;
import com.quakearts.rest.client.HttpResponse;
import com.quakearts.rest.client.HttpVerb;
import com.quakearts.webtools.resteasy.test.experiments.TestInjectImpl;
import com.quakearts.webtools.resteasy.validation.test.helpers.TestHttpClient;
import com.quakearts.webtools.resteasy.validation.test.helpers.TestHttpClientBuilder;
import com.quakearts.webtools.resteasy.validation.test.helpers.TestMain;
import com.quakearts.webtools.resteasy.validation.test.helpers.TestResource;
import com.quakearts.webtools.resteasy.validation.test.helpers.TestResourceParent;

public class TestIntegration {

	@Test
	public void testRestIntegration() throws Exception {
		Main.main(new String[] {TestMain.class.getName(),"-dontwaitinmain"});

		TestHttpClient client = new TestHttpClientBuilder()
				.setHostAs("localhost")
				.setPortAs(8180)
				.thenBuild();
		
		HttpResponse response = client.sendRequest("/test/rest/test", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(TestResource.getterWorked(), is(true));
		
		response = client.sendRequest("/test/rest/test/1", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(200));
		assertThat(TestResource.getworked(), is(true));

		TestResource.reset();
		
		response = client.sendRequest("/test/rest/test/0", null, HttpVerb.GET);
		assertThat(response.getHttpCode(), is(400));
		assertThat(TestResource.getworked(), is(false));

		response = client.sendRequest("/test/rest/test", "Test value", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(200));
		assertThat(TestResource.postworked(), is(true));
		
		TestResource.reset();
		
		response = client.sendRequest("/test/rest/test", "Test", HttpVerb.POST);
		assertThat(response.getHttpCode(), is(400));
		assertThat(TestResource.postworked(), is(false));

		response = client.sendRequest("/test/rest/test", "Test value", HttpVerb.PUT);
		assertThat(response.getHttpCode(), is(200));
		assertThat(TestInjectImpl.processedTestParameter(), is(true));
		
		response = client.sendRequest("/test/rest/test", null, HttpVerb.OPTIONS);
		assertThat(response.getHttpCode(), is(200));
		assertThat(TestResourceParent.optionsWorked(), is(true));

	}

}
