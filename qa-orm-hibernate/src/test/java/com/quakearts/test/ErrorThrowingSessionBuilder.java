package com.quakearts.test;

import java.lang.reflect.Proxy;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ErrorThrowingSessionBuilder {
	private ErrorThrowingSessionBuilder() {}
	
	public static interface ExceptionThrower {
		Object throwException() throws HibernateException;
	}
	
	public static Session createSession(ExceptionThrower exceptionThrower){
		return (Session) Proxy.newProxyInstance(ErrorThrowingSessionBuilder
				.class.getClassLoader(), 
				new Class[]{Session.class}, (proxy, object, args)->{
					return exceptionThrower.throwException();
				});
	}
	
	public static Session createSessionWithTransaction(ExceptionThrower exceptionThrower){
		return (Session) Proxy.newProxyInstance(ErrorThrowingSessionBuilder
				.class.getClassLoader(), 
				new Class[]{Session.class}, (proxy, object, args)->{
					return createTransactionProxy(exceptionThrower);
				});
	}

	private static Transaction createTransactionProxy(ExceptionThrower exceptionThrower) {
		return (Transaction) Proxy.newProxyInstance(ErrorThrowingSessionBuilder
				.class.getClassLoader(), 
				new Class[]{Transaction.class}, (proxy, object, args)->{
					return exceptionThrower.throwException();
				});
	}
}
