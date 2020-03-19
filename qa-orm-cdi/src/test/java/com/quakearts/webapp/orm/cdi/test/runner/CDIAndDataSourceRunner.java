package com.quakearts.webapp.orm.cdi.test.runner;

import javax.enterprise.inject.spi.CDI;

import org.junit.runners.model.InitializationError;

import com.quakearts.webtools.test.AppBaseRunner;

public class CDIAndDataSourceRunner extends AppBaseRunner {

	public CDIAndDataSourceRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createDataSourceProvider();
		createContextDependency();
	}

	@Override
	protected void startServices() {
		startDataSourceProvider();
		startContextDependency();
	}
	
	@Override
	protected Object createTest() throws Exception {
		return CDI.current().select(getTestClass().getJavaClass()).get();
	}

	@Override
	protected void shutdownServices() {
		shutdownContextDependency();
		shutdownDataSourceProvider();
	}
}
