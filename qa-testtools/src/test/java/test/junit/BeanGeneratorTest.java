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
package test.junit;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.*;
import org.junit.Test;

import com.quakearts.tools.test.generator.BeanGenerator;
import com.quakearts.tools.test.generator.primitives.StringGenerator;

import test.junit.beans.Account;
import test.junit.beans.Customer;
import test.junit.beans.DataSource;
import test.junit.beans.NonAnnotatedClass;
import test.junit.beans.Parent;
import test.junit.beans.Person;

public class BeanGeneratorTest {

	@Test
	public void testBeanGenerator() throws Exception {
		
		Person person;
		try {
			BeanGenerator<Person> generator = new BeanGenerator<>(Person.class).init();
			person = generator.generateRandom();
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating person");
			return;
		}
		
		assertThat("Could not create Person.", person != null, is(true));
		assertThat("Could not create Person.", person.getName() != null, is(true));
		assertThat("Could not create Person.", person.getDateOfBirth() != null, is(true));

		for(int count=0; count < 1000; count++){
			BeanGenerator<Person> generator = new BeanGenerator<>(Person.class)
					.use(new StringGenerator().useField("names")).forField("name").init();
			person = generator.generateRandom();
	
			assertThat("Did not use list:names",("Sophia;Jackson;Emma;Aiden;Olivia;Lucas;Ava;Liam;Mia;Noah;"
					+ "Isabella;Ethan;Riley;Mason;Aria;Caden;Zoe;Oliver;"
					+ "Charlotte;Elijah;Lily;Grayson;Layla;Jacob;Amelia;"
					+ "Michael;Emily;Benjamin;Madelyn;Carter;Aubrey;James;"
					+ "Adalyn;Jayden;Madison;Logan;Chloe;Alexander;Harper;"
					+ "Caleb;Abigail;Ryan;Aaliyah;Luke;Avery;Daniel;Evelyn;"
					+ "Jack;Kaylee;William;Ella;Owen;Ellie;Gabriel;Scarlett;"
					+ "Matthew;Arianna;Connor;Hailey;Jayce;Nora;Isaac;Addison;"
					+ "Sebastian;Brooklyn;Henry;Hannah;Muhammad;Mila;Cameron;"
					+ "Leah;Wyatt;Elizabeth;Dylan").contains(person.getName()) && person.getName().length()<30, is(true));
		}
	}

	@Test
	public void testBeanGeneratorAnnotations() throws Exception {
		Customer customer;
		for(int count=0; count < 1000; count++){
			try {
				BeanGenerator<Customer> generator = new BeanGenerator<>(Customer.class).init();
				customer = generator.generateRandom();
			} catch (Throwable e) {
				fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles generating person");
				return;
			}
				
			assertThat("Could not create Customer.", customer != null, is(true));
			assertThat("Could not create Customer.", customer.getName() != null, is(true));
			assertThat("Could not create Customer.", customer.getAccountNumber() != null, is(true));			
			assertThat("Could not create Customer.", customer.getAccountCreateDate() != null, is(true));
	
			assertThat("Invalid id:"+customer.getId(), 1<=customer.getId() && customer.getId()<=10, is(true));
			
			assertThat("Did not use list:names",("Sophia;Jackson;Emma;Aiden;Olivia;Lucas;Ava;Liam;Mia;Noah;"
					+ "Isabella;Ethan;Riley;Mason;Aria;Caden;Zoe;Oliver;"
					+ "Charlotte;Elijah;Lily;Grayson;Layla;Jacob;Amelia;"
					+ "Michael;Emily;Benjamin;Madelyn;Carter;Aubrey;James;"
					+ "Adalyn;Jayden;Madison;Logan;Chloe;Alexander;Harper;"
					+ "Caleb;Abigail;Ryan;Aaliyah;Luke;Avery;Daniel;Evelyn;"
					+ "Jack;Kaylee;William;Ella;Owen;Ellie;Gabriel;Scarlett;"
					+ "Matthew;Arianna;Connor;Hailey;Jayce;Nora;Isaac;Addison;"
					+ "Sebastian;Brooklyn;Henry;Hannah;Muhammad;Mila;Cameron;"
					+ "Leah;Wyatt;Elizabeth;Dylan").contains(customer.getName()) && customer.getName().length()<30, is(true));
			
			assertThat("Did not use list:account numbers", "102938499,29849395,3849329340,23824984954,942092340540".contains(customer.getAccountNumber()),
					is(true));

			assertThat("Enum was not generated", customer.getCustomerClass()!=null,
					is(true));
		}
	}
	
	@Test
	public void testBeanGeneratorCollection() throws Exception {
		DataSource dataSource;
		for(int count=0; count < 1000; count++){
			try {		
				BeanGenerator<DataSource> generator = new BeanGenerator<>(DataSource.class).init();
				dataSource = generator.generateRandom();	
			} catch (Throwable e) {
				fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles generating dataSource");
				return;
			}
	
			assertThat("Cusomer list is empty", dataSource.getCustomers().size()==5, is(true));
			assertThat("Person list is empty", dataSource.getEntities()!= null && dataSource.getEntities().size()==5, is(true));		
		}
	}

	
	@Test
	public void testBeanGeneratorNestedBeans() throws Exception {
		Account account;
		for(int count=0; count < 1000; count++){
			try {
				BeanGenerator<Account> generator = new BeanGenerator<>(Account.class).init();
				account = generator.generateRandom();
			} catch (Throwable e) {
				fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
						+ ". Exception occured whiles generating nested beans");
				return;
			}
			
			assertThat("Transactions list is empty",account.getTransactions()!= null &&  !account.getTransactions().isEmpty(), is(true));
			assertThat("Notes list is empty", !account.getNotes().isEmpty(), is(true));
		}
	}
	
	@Test
	public void testNonAnnotatedClass() throws Exception {
		try {
			BeanGenerator<NonAnnotatedClass> generator = new BeanGenerator<>(NonAnnotatedClass.class).init();
			generator.generateRandom();
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating nested beans");
		}
	}

	@Test
	public void testRecursiveDepth() throws Exception {
		try {
			BeanGenerator<Parent> generator = new BeanGenerator<>(Parent.class).init();
			Parent parent = generator.generateRandom();
			
			System.out.println(parent);
		} catch (Throwable e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage()
					+ ". Exception occured whiles generating nested beans");
		}
	}
}
