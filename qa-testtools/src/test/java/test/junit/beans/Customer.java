/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package test.junit.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.quakearts.tools.test.generator.annotation.UseGeneratorProperty;
import com.quakearts.tools.test.generator.primitives.configuration.GenerateWith;

public class Customer {
	private int id;
	private String name;
	private String accountNumber;
	@JsonFormat(shape=Shape.STRING, pattern="dd/MM/yyyy")
	private Date accountCreateDate;
	@JsonFormat(shape=Shape.NUMBER_FLOAT, pattern="#.00")
	private double balance;
	private CustomerClass customerClass;

	@GenerateWith(max=10, min=1)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@UseGeneratorProperty("names")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@GenerateWith(strings={"102938499","29849395","3849329340","23824984954","942092340540"})
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getAccountCreateDate() {
		return accountCreateDate;
	}

	public void setAccountCreateDate(Date accountCreateDate) {
		this.accountCreateDate = accountCreateDate;
	}

	@GenerateWith(max=1500, min=50)
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CustomerClass getCustomerClass() {
		return customerClass;
	}
	
	public void setCustomerClass(CustomerClass customerClass) {
		this.customerClass = customerClass;
	}
}
