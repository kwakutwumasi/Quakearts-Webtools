package com.quakearts.webtools.test;

import javax.enterprise.inject.spi.CDI;

import org.junit.runners.model.InitializationError;

/**Runner class for tests that require CDI services in qa-appbase
 * @author kwakutwumasi-afriyie
 *
 */
public class CDIRunner extends AppBaseRunner {
	
	public CDIRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createContextDependency();
	}

	@Override
	protected void startServices() {
		startContextDependency();
	}
	
	@Override
	protected Object createTest() throws Exception {
		return CDI.current().select(getTestClass().getJavaClass()).get();
	}

	@Override
	protected void shutdownServices() {
		shutdownContextDependency();
	}
}
