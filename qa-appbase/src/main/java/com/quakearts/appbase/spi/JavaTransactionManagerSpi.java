/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.appbase.spi;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.exception.ConfigurationException;

/**The SPI for JTA implementations. Implementers should use this method to bootstrap the JTA implementation
 * @author kwakutwumasi-afriyie
 *
 */
public interface JavaTransactionManagerSpi {
	/**Start the JTA implementation
	 * @throws ConfigurationException if there is an error initializing the instance
	 */
	void initiateJavaTransactionManager();
	/**Shutdown the JTA implementation, cleaning up and releasing held resources.
	 * A successful call to {@link #initiateJavaTransactionManager()} should be possible after this method exits
	 * @throws ConfigurationException if there is an error shutting down the instance
	 */
	void shutdownJavaTransactionManager();
	/**Return an interface to the {@linkplain TransactionManager}
	 * @return the {@linkplain TransactionManager}
	 */
	TransactionManager getTransactionManager();
	/**Return an interface to the {@linkplain UserTransaction}
	 * @return the {@linkplain UserTransaction}
	 */
	UserTransaction getUserTransaction();
}
