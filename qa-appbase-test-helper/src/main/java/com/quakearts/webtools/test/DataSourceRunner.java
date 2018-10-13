package com.quakearts.webtools.test;

import org.junit.runners.model.InitializationError;

/**Runner class for tests that require Datasource services
 * @author kwakutwumasi-afriyie
 *
 */
public class DataSourceRunner extends AppBaseRunner {

	public DataSourceRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createDataSourceProvider();
	}

	@Override
	protected void startServices() {
		startDataSourceProvider();
	}

	@Override
	protected void shutdownServices() {
		shutdownDataSourceProvider();
	}
	
}
