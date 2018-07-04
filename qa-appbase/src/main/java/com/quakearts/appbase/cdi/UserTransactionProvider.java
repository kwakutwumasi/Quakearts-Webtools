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
package com.quakearts.appbase.cdi;

import javax.enterprise.inject.Produces;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.spi.factory.JavaTransactionManagerSpiFactory;

/**CDI producer that provides intances of {@linkplain UserTransaction}
 * @author kwakutwumasi-afriyie
 *
 */
public class UserTransactionProvider {

	@Produces
	public @Transaction UserTransaction getUserTransaction(){
		return JavaTransactionManagerSpiFactory.getInstance()
			.getJavaTransactionManagerSpi()
			.getUserTransaction();
	}
}
