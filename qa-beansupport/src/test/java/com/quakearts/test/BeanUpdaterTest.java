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
package com.quakearts.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.quakearts.test.hibernate.Inventory;
import com.quakearts.test.hibernate.Product;
import com.quakearts.webapp.beansupport.BeanUpdater;
import com.quakearts.webapp.beansupport.exception.BeanUpdaterException;

public class BeanUpdaterTest {

	private Inventory createInventory1(){
		Inventory inventory = new Inventory();
		inventory.setCurrency("USD");
		inventory.setPrice(2d);
		
		return inventory;
	}

	private Inventory createInventory2(){
		Inventory inventory = new Inventory();

		inventory.setCurrency("GHS");
		inventory.setDate(new Date());
		inventory.setId(1);
		inventory.setPrice(200.00d);
		inventory.setProduct(createProduct2());
		inventory.setQuantity(3);
		
		return inventory;
	}
	
	private Inventory createInventory3(){
		Inventory inventory = new Inventory();
		inventory.setCurrency("");
		
		return inventory;
	}
	
	private Product createProduct1() {
		Product product = new Product();		
		product.setOtherNotes(new ArrayList<>());
		product.setOtherDetails(new HashMap<>());
		
		return product;
	}

	private Product createProduct2() {
		Product product = new Product();
		
		product.setDescription("A great product");
		product.setId(1);
		product.setName("The product 1");
		product.setOtherNotes(Arrays.asList("A Functional device","Works wonders"));
		HashMap<String, String> otherDetails = new HashMap<>();
		otherDetails.put("Product ID", "123456");
		product.setOtherDetails(otherDetails);
		
		return product;
	}

	private Product createProduct3() {
		Product product = new Product();
		product.setOtherNotes(Arrays.asList("A Limited device","Does not work"));
		HashMap<String, String> otherDetails = new HashMap<>();
		otherDetails.put("Product ID", "54321");
		product.setOtherDetails(otherDetails);
		return product;
	}

	@Test
	public void testBeanUpdateIgnoreEmptyAndNullTreatZeroAsEmpty() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2(),
				inventory3 = createInventory3();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			assertThat(inventoryUpdater.update(inventory1, inventory2), is(true));
			assertThat(inventoryUpdater.update(inventory1, inventory2), is(false));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory1.getDate() != inventory2.getDate());
		assertTrue(inventory1.getProduct() != inventory2.getProduct());
		
		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));

		try {
			assertThat(inventoryUpdater.update(inventory3, inventory2), is(false));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}

		assertTrue(inventory1.getDate() != inventory2.getDate());
		assertTrue(inventory1.getProduct() != inventory2.getProduct());
		
		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));

		BeanUpdater<Product> productUpdater;
		try {
			productUpdater = new BeanUpdater<>(Product.class);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		Product product1 = createProduct1(); 
		Product product2 = createProduct2();
		Product product3 = createProduct3();
		try {
			assertThat(productUpdater.update(product1, product2), is(false));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}

		assertThat(product2.getDescription(), is("A great product"));
		assertThat(product2.getId(), is(1));
		assertThat(product2.getName(), is("The product 1"));
		assertTrue(product2.getOtherDetails()!=product1.getOtherDetails());
		assertTrue(product2.getOtherNotes()!=product1.getOtherNotes());

		try {			
			assertThat(productUpdater.update(product3, product2), is(true));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}

		assertThat(product2.getDescription(), is("A great product"));
		assertThat(product2.getId(), is(1));
		assertThat(product2.getName(), is("The product 1"));
		assertTrue(product2.getOtherDetails()==product3.getOtherDetails());
		assertTrue(product2.getOtherNotes()==product3.getOtherNotes());
	}
	
	@Test
	public void testBeanUpdateAndGetChanges() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			Map<String, Object> changes = inventoryUpdater.updateAndGetChanges(inventory1, inventory2);
			assertThat(changes.containsKey("currency"), is(true));
			assertThat(changes.get("currency"), is("GHS"));
			assertThat(changes.containsKey("price"), is(true));
			assertThat(changes.get("price"), is(200.00d));
			
			changes = inventoryUpdater.updateAndGetChanges(inventory1, inventory2);
			assertThat(changes.isEmpty(), is(true));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory1.getDate() != inventory2.getDate());
		assertTrue(inventory1.getProduct() != inventory2.getProduct());
		
		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));
	}

	@Test
	public void testBeanUpdateDontIgnoreEmpty() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2(),
				inventory3 = createInventory3();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class).dontIgnoreNullAndEmpty();
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			inventoryUpdater.update(inventory1, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory2.getDate() == null);
		assertTrue(inventory2.getProduct() == null);

		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));
		
		try {
			inventoryUpdater.update(inventory3, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory2.getDate() == null);
		assertTrue(inventory2.getProduct() == null);

		assertThat(inventory3.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(1));
		assertThat(inventory2.getQuantity(), is(3));
		
		BeanUpdater<Product> productUpdater;
		try {
			productUpdater = new BeanUpdater<>(Product.class);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		Product product1 = createProduct1(); 
		Product product2 = createProduct2();
		try {
			assertThat(productUpdater.update(product1, product2), is(false));
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}

		assertThat(product2.getDescription(), is("A great product"));
		assertThat(product2.getId(), is(1));
		assertThat(product2.getName(), is("The product 1"));
		assertTrue(product2.getOtherDetails()==product2.getOtherDetails());
		assertTrue(product2.getOtherNotes()==product2.getOtherNotes());
	}

	@Test
	public void testBeanUpdateDontTreatZeroAsNull() {
		Inventory inventory1 = createInventory1(), inventory2 = createInventory2();
		
		BeanUpdater<Inventory> inventoryUpdater;
		try {
			inventoryUpdater = new BeanUpdater<>(Inventory.class).dontTreatZeroAsEmpty();
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
			return;
		}
		
		try {
			inventoryUpdater.update(inventory1, inventory2);
		} catch (BeanUpdaterException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
		
		assertTrue(inventory2.getDate() != null);
		assertTrue(inventory2.getProduct() != inventory1.getProduct());

		assertThat(inventory1.getCurrency(), is(inventory2.getCurrency()));
		assertThat(inventory1.getPrice(), is(inventory2.getPrice()));
		
		assertThat(inventory2.getId(), is(0));
		assertThat(inventory2.getQuantity(), is(0));
	}

}
