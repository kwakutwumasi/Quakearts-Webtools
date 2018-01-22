package com.quakearts.appbase.test.experiments;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.TransactionHandle;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant;
import com.quakearts.appbase.cdi.annotation.TransactionParticipant.TransactionType;

public class TestInjectImpl implements TestInject {
	@Inject
	TestSubInject testSubInject;
	@Inject @TransactionHandle
	UserTransaction transaction;
	
	private static boolean saidHello;
	private static boolean testSubInjectLoaded;
	private static boolean transactionWorked;
	
	@Override
	public void sayHello(){
		saidHello = true;
		if(testSubInject!=null){
			testSubInjectLoaded = true;
		}
		testSubInject.doSomething();
	}
	
	@Override
	@TransactionParticipant(TransactionType.SINGLETON)
	public void testTransaction() {
		try {
			transactionWorked = transaction != null && transaction.getStatus() == Status.STATUS_ACTIVE;
		} catch (SystemException e) {
		}
	}
	
	public static boolean saidHello() {
		return saidHello;
	}
	
	public static boolean testSubInjectLoaded() {
		return testSubInjectLoaded;
	}
	
	public static boolean transactionWorked() {
		return transactionWorked;
	}
}
