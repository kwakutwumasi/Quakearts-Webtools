package com.quakearts.webtools.test;

import javax.enterprise.inject.spi.CDI;

import org.junit.runners.model.InitializationError;

public class EmbeddedWebServerRunner extends AppBaseRunner {

	public EmbeddedWebServerRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createEmbeddedWebServer();
	}

	@Override
	protected void startServices() {
		startEmbeddedWebServer();
	}

	@Override
	protected Object createTest() throws Exception {
		return CDI.current().select(getTestClass().getJavaClass()).get();
	}

	@Override
	protected void shutdownServices() {
		shutdownEmbeddedWebServer();
	}
}
