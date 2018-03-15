package com.quakearts.appbase.test.experiments;

import javax.inject.Singleton;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;

@Singleton
public class TestTransactionImpl implements TestTransaction {
	
	@Override
	@Transactional(TransactionType.BEGIN)
	public void beginTransaction() {
	}
	
	@Override
	@Transactional(TransactionType.JOIN)
	public void continueTransaction() {
	}
	
	@Override
	@Transactional(TransactionType.END)
	public void completeTransaction() {
	}
}