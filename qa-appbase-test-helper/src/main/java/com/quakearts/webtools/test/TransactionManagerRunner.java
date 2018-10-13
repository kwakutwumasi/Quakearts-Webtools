package com.quakearts.webtools.test;

import org.junit.runners.model.InitializationError;

/**Runner class for tests that require transaction manager services
 * @author kwakutwumasi-afriyie
 *
 */
public class TransactionManagerRunner extends AppBaseRunner {

	public TransactionManagerRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void createServices() {
		createJavaTransactionManager();
	}

	@Override
	protected void startServices() {
		startJavaTransactionManager();
	}

	@Override
	protected void shutdownServices() {
		shutdownJavaTransactionManager();
	}
}
