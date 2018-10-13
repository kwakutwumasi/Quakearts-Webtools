package com.quakearts.webtools.test;

import javax.enterprise.inject.spi.CDI;

import org.junit.runners.model.InitializationError;

/**Runner class for tests that require the full qa-appbase services
 * @author kwakutwumasi-afriyie
 *
 */
public class AllServicesRunner extends AppBaseRunner{

	public AllServicesRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createDataSourceProvider();
		createEmbeddedWebServer();
	}

	@Override
	protected void startServices() {
		startDataSourceProvider();
		startEmbeddedWebServer();
	}
	
	@Override
	protected Object createTest() throws Exception {
		return CDI.current().select(getTestClass().getJavaClass()).get();
	}

	@Override
	protected void shutdownServices() {
		shutdownEmbeddedWebServer();
		shutdownDataSourceProvider();
	}
}
