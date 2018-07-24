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
package com.quakearts.appbase.cdi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**Annotation to decorate methods to be wrapped in a {@link javax.transaction.UserTransaction UserTransaction}.
 * The value must be one of the {@linkplain TransactionType} enums
 * @author kwakutwumasi-afriyie
 *
 */
@InterceptorBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Transactional {
	@Nonbinding
	TransactionType value() default TransactionType.JOIN;
	
	/**The type of transaction wrapping required
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public static enum TransactionType{
		/**Indicates that the method is at the start of a transaction chain.
		 * If a transaction is already active throw an error.
		 * If not start a transaction
		 */
		BEGIN,
		/**Indicates that the method is part of a transaction chain.
		 * If a transaction is not already active throw an error.
		 *
		 */
		JOIN,
		/**Indicates that the method or class should be encapsulated 
		 * in a single transaction. If a transaction is already active
		 * do nothing
		 */
		SINGLETON,
		/**Indicates that the method is at the end of a transaction
		 * If a transaction is not active an error will be thrown.
		 * Commit or rollback the transaction depending on transaction state
		 */
		END
	}
}
