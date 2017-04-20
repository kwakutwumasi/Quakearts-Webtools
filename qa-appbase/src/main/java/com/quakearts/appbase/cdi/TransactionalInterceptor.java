package com.quakearts.appbase.cdi;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;

@Transactional @Interceptor
public class TransactionalInterceptor {

	@Transaction
	private UserTransaction transaction;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		Transactional transactional = context.getMethod().getAnnotation(Transactional.class);
		if(transactional == null)
			transactional = context.getMethod().getDeclaringClass().getAnnotation(Transactional.class);
		
		if(transactional == null)
			throw new SystemException("Transactional attribute cannot be found on method "
					+context.getMethod().getName()
					+" or on "
					+context.getMethod().getDeclaringClass().getName());
		
		boolean proceed = false;
		
		if(transaction.getStatus() == Status.STATUS_NO_TRANSACTION) {
			if(transactional.value() == TransactionType.END)
				throw new SystemException("Transaction is not active for "
						+context.getMethod().getName()
						+" of "
						+context.getMethod().getDeclaringClass().getName());
			
			transaction.begin();
			proceed = true;
		} else if(transaction.getStatus() == Status.STATUS_COMMITTED
				|| transaction.getStatus() == Status.STATUS_COMMITTING) {
				throw new SystemException("Transaction has been commited. Cannot execute "
						+context.getMethod().getName()
						+" for "
						+context.getMethod().getDeclaringClass().getName());			
		} else if(transaction.getStatus() == Status.STATUS_PREPARED
				|| transaction.getStatus() == Status.STATUS_PREPARING
				||transaction.getStatus() == Status.STATUS_ACTIVE) {
			proceed = true;
		} else if(transaction.getStatus() == Status.STATUS_UNKNOWN) {
			throw new SystemException("Transaction state is unknown. Cannot execute "
					+context.getMethod().getName()
					+" for "
					+context.getMethod().getDeclaringClass().getName());						
		}
		
		if(!proceed)
			return null;
		
		Object object = context.proceed();
		
		if(transactional.value() ==TransactionType.END 
				|| transactional.value() == TransactionType.SINGLETON){
			if(transaction.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
				transaction.rollback();
			} else {
				transaction.commit();
			}
		}
		
		return object;
	}
}
