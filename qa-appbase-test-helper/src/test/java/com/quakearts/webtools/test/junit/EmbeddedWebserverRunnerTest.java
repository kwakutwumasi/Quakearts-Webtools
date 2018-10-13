package com.quakearts.webtools.test.junit;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.quakearts.webtools.test.EmbeddedWebServerRunner;
import com.quakearts.webtools.test.helpers.TestClient;
import com.quakearts.webtools.test.helpers.TestClientBuilder;
import com.quakearts.webtools.test.helpers.TestInject;

@RunWith(EmbeddedWebServerRunner.class)
public class EmbeddedWebserverRunnerTest {
	@Inject
	private TestInject inject;
	
	@Test
	public void testInject() throws Exception{
		assertThat(inject, is(notNullValue()));
		assertThat(inject.sayHello(), is("Hi! Runner is working!"));
	}

	@Test
	public void testWebServer() throws Exception {
		TestClient client = new TestClientBuilder()
				.setURLAs("http://localhost:8280")
				.thenBuild();
		
		assertThat(inject.sayHello(), is(client.makeCall()));
	}

}
