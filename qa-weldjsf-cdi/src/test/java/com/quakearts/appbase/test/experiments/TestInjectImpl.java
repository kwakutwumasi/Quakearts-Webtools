/*******************************************************************************
 * Copyright (C) 2018 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>
 * 
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package com.quakearts.appbase.test.experiments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.quakearts.appbase.cdi.annotation.Transaction;
import com.quakearts.appbase.cdi.annotation.Transactional;
import com.quakearts.appbase.cdi.annotation.Transactional.TransactionType;
import com.quakearts.appbase.test.helpers.TestParameter;

public class TestInjectImpl implements TestInject {
	@Inject
	TestSubInject testSubInject;
	@Inject @Transaction
	UserTransaction transaction;
	
	private static boolean saidHello;
	private static boolean testSubInjectLoaded;
	private static boolean transactionWorked;
	
	public static void reset() {
		saidHello = false;
		testSubInjectLoaded = false;
		transactionWorked = false;
	}
	
	@Override
	public void sayHello(){
		saidHello = true;
		if(testSubInject!=null){
			testSubInjectLoaded = true;
		}
		testSubInject.doSomething();
	}
	
	@Override
	@Transactional(TransactionType.SINGLETON)
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
	
	@Override
	public TestParameter pullFromInputStream(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		return new TestParameter().setContent(reader.readLine());
	}
	
	@Override
	public void pushToOutputStream(TestParameter parameter, OutputStream out) throws IOException {
		out.write(parameter.getContent().getBytes());
	}
}
