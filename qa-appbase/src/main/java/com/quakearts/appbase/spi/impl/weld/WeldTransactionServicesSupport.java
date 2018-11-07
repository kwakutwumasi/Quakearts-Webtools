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
package com.quakearts.appbase.spi.impl.weld;

import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.transaction.UserTransaction;
import org.jboss.weld.transaction.spi.TransactionServices;
import com.quakearts.appbase.Main;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

public class WeldTransactionServicesSupport implements TransactionServices {

	public WeldTransactionServicesSupport() {
		Main.log.info("WeldTransactionServicesSupport started");
	}
	
	@Override
	public void cleanup() {
		//Do nothing
	}

	@Override
	public void registerSynchronization(Synchronization synchronizedObserver) {
		try {
			Transaction transaction = JavaTransactionManagerSpiFactory
					.getInstance()
					.getJavaTransactionManagerSpi()
					.getTransactionManager()
					.getTransaction();
			if(transaction.getStatus()==Status.STATUS_ACTIVE)
				transaction.registerSynchronization(synchronizedObserver);
		} catch (SystemException | IllegalStateException | RollbackException e) {
			Main.log.error("Register synchronization error",e);
		}
	}

	@Override
	public boolean isTransactionActive() {
		try {
			Transaction transaction = JavaTransactionManagerSpiFactory
					.getInstance()
					.getJavaTransactionManagerSpi()
					.getTransactionManager()
					.getTransaction();
			return transaction.getStatus()==Status.STATUS_ACTIVE;
		} catch (SystemException | IllegalStateException e) {
			Main.log.error("Is transaction active error",e);
		}
		return false;
	}

	@Override
	public UserTransaction getUserTransaction() {
		return JavaTransactionManagerSpiFactory
				.getInstance()
				.getJavaTransactionManagerSpi()
				.getUserTransaction();
	}

}
