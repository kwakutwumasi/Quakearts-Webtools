package com.quakearts.appbase.cdi.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Transactional {
	TransactionType value() default TransactionType.JOIN;
	
	public static enum TransactionType{
		/**Indicates that the method is part of a transaction chain.
		 * If a transaction is already active do nothing.
		 * If not start a transaction
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
