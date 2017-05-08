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
package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.Date;

import org.junit.Test;

import com.quakearts.test.hibernate.Inventory;
import com.quakearts.test.hibernate.Product;
import com.quakearts.webapp.beansupport.BeanUpdater;
import com.quakearts.webapp.beansupport.exception.BeanUpdaterException;
import com.quakearts.webapp.beansupport.exception.BeanUpdaterInitException;

public class BeanUpdaterTest {

	private Inventory createInventory1(){
		Inventory inventory = new Inventory();
		inventory.setCurrency("USD");
		inventory.setPrice(2);
		
		return inventory;
	}

	private Inventory createInventory2(){
		Inventory inventory = new Inventory();

		inventory.setCurrency("GHS");
		inventory.setDate(new Date());
		inventory.setId(1);
		inventory.setPrice(200.00);
		inventory.setProduct(new Product());
		inventory.setQuantity(3);
		
		return inventory;
	}
	
	@Test
	public void testBeanUpdate() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class);
		} catch (BeanUpdaterInitException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			inventoryUpdater.update(inventory1, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
	}

	@Test
	public void testBeanUpdateDontIgnoreEmpty() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class).dontIgnoreNullAndEmpty();
		} catch (BeanUpdaterInitException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			inventoryUpdater.update(inventory1, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory1.getDate() == null);
		assertTrue(inventory1.getProduct() == null);

		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));
	}

	@Test
	public void testBeanUpdateDontTreatZeroAsNull() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class).dontTreatZeroAsEmpty();
		} catch (BeanUpdaterInitException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			inventoryUpdater.update(inventory1, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(0));
		assertThat(inventory2.getQuantity(), is(0));
	}

}
