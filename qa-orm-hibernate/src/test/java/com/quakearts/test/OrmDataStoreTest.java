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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.After;
import org.junit.Test;

import com.quakearts.test.hibernate.Brand;
import com.quakearts.test.hibernate.Inventory;
import com.quakearts.test.hibernate.Product;
import com.quakearts.test.hibernate.SalesOrder;
import com.quakearts.test.hibernate.SalesPart;
import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;
import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;
import static com.quakearts.webapp.orm.query.helper.ParameterMapBuilder.createParameters;;

public class OrmDataStoreTest {

	@Test
	public void testDataStore() {
		assertTrue("No factory to generate DataStore", DataStoreFactory.getInstance() != null);

		try {
			DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
			assertTrue("DataStore was null", dataStore != null);
			assertThat("DataStore is not " + HibernateSessionDataStore.class.getName(), dataStore.getClass().getName(),
					is(HibernateSessionDataStore.class.getName()));

			Brand brand = createBrand(1, "Quakearts");
			dataStore.save(brand);
			dataStore.flushBuffers();
			Brand savedBrand = dataStore.get(Brand.class, 1);
			assertThat("No brand with ID 1", savedBrand.getName(), is(brand.getName()));

			dataStore.delete(savedBrand);
			dataStore.flushBuffers();
			savedBrand = dataStore.get(Brand.class, 1);
			assertTrue("Saved brand was not deleted", savedBrand == null);

			dataStore.save(brand);
			dataStore.flushBuffers();
			savedBrand = dataStore.get(Brand.class, 1);
			savedBrand.setName("Audi");
			dataStore.update(savedBrand);
			dataStore.flushBuffers();
			savedBrand = dataStore.get(Brand.class, 1);
			assertThat("Brand was not updated", savedBrand.getName(), is("Audi"));

			brand = createBrand(2, "Quakearts");
			dataStore.saveOrUpdate(brand);
			dataStore.flushBuffers();
			savedBrand = dataStore.get(Brand.class, 2);
			assertThat("No brand with ID 2", savedBrand.getName(), is(brand.getName()));

			savedBrand.setName("Nissan");
			dataStore.saveOrUpdate(savedBrand);
			dataStore.flushBuffers();
			savedBrand = dataStore.get(Brand.class, 2);
			assertThat("Brand was not updated", savedBrand.getName(), is("Nissan"));

			Product product = createProduct(1, savedBrand, "A solid Salon", "Altima");
			dataStore.save(product);
			dataStore.flushBuffers();
			Inventory inventory = createInventory(1, "GHS", 50000, "01/10/2016", product, 5);
			dataStore.save(inventory);

			product = createProduct(2, savedBrand, "A classy SUV", "Pathfinder");
			dataStore.save(product);
			dataStore.flushBuffers();

			inventory = createInventory(2, "GHS", 90000, "04/10/2016", product, 2);
			dataStore.save(inventory);

			product = createProduct(3, savedBrand, "A classy Crossover", "Qashqai");
			dataStore.save(product);
			dataStore.flushBuffers();

			inventory = createInventory(3, "GHS", 70000, "05/10/2016", product, 6);
			dataStore.save(inventory);

			savedBrand = dataStore.get(Brand.class, 1);

			product = createProduct(4, savedBrand, "A fast Salon", "A4");
			dataStore.save(product);
			dataStore.flushBuffers();

			inventory = createInventory(4, "GHS", 90000, "04/10/2016", product, 4);
			dataStore.save(inventory);

			product = createProduct(5, savedBrand, "A luxurious Salon", "A6");
			dataStore.save(product);
			dataStore.flushBuffers();

			inventory = createInventory(5, "GHS", 110000, "05/10/2016", product, 3);
			dataStore.save(inventory);
			dataStore.flushBuffers();

			// SELECT FROM Inventory WHERE product = Product(5,A6)
			List<Inventory> list = dataStore.list(Inventory.class, createParameters().add("product", product).build());

			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(1));

			printInventory(list);

			// SELECT FROM Inventory WHERE quantity = 2 or quantity = 6
			list = dataStore.list(Inventory.class, createParameters().addChoices("quantity", 2, 6).build());

			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));

			printInventory(list);

			// SELECT FROM Inventory WHERE quantity = 4 or product =
			// product(5,A6)
			list = dataStore.list(Inventory.class,
					createParameters().disjoin().add("quantity", 4).add("product", product).build());

			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));
			printInventory(list);

			// SELECT FROM Inventory WHERE quantity = 3 and product =
			// product(5,A6)
			list = dataStore.list(Inventory.class,
					createParameters().conjoin().add("quantity", 3).add("product", product).build());

			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(1));
			printInventory(list);

			// SELECT FROM Inventory WHERE (quantity = 3 and product =
			// product(5,A6)) or (quantity = 6)
			list = dataStore.list(Inventory.class, createParameters().disjoin().conjoin().add("quantity", 3)
					.add("product", product).endjoin().add("quantity", 6).build());

			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));
			printInventory(list);

			// SELECT FROM Inventory WHERE (quantity between 3 and 6)
			list = dataStore.list(Inventory.class, createParameters().addRange("quantity", 3, 6).build());
			assertTrue("Nothing returned", list != null);
			assertThat("Invalid number of items returned:" + list.size(), list.size(), is(4));
			printInventory(list);

			// SELECT FROM Product WHERE (description like %Salon)
			List<Product> products = dataStore.list(Product.class,
					createParameters().addVariableString("description", "%Salon").build());

			assertTrue(products != null);
			assertThat("Invalid number of items returned:" + products.size(), products.size(), is(3));

			System.out.println(String.format("%1$20s|%2$20s|%3$20s|%4$20s", "ID", "Brand", "Name", "Description"));
			System.out.println(new String(new char[90]).replace('\0', '_'));
			for (Product foundProduct : products) {
				System.out.println(String.format("%1$20d|%2$20s|%3$20s|%4$20s", foundProduct.getId(),
						foundProduct.getBrand() != null ? product.getBrand().getName() : null, foundProduct.getName(),
						foundProduct.getDescription()));
			}

			list = dataStore.list(Inventory.class, createParameters().setMaxResults(3).build());
			
			assertThat("Max results did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
			
			list = dataStore.list(Inventory.class, createParameters().disjoin()
					.addRange("quantity", 0, 3)
					.addRange("quantity", 4, 6)
					.setMaxResults(3).build());
			assertThat("Max results did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
			
			SalesOrder salesOrder = new SalesOrder();
			salesOrder.setId(1);
			
			dataStore.save(salesOrder);
			dataStore.flushBuffers();
			
			dataStore.save(createSalesPart(1, product, 20000, salesOrder));
			dataStore.save(createSalesPart(2, dataStore.get(Product.class, 1), 20000, salesOrder));
			dataStore.save(createSalesPart(3, dataStore.get(Product.class, 2), 20000, salesOrder));
			
			dataStore.flushBuffers();
			//SELECT FROM SalesPart WHERE product.brand.name = "Nissan"
			List<SalesPart> salesParts = dataStore.list(SalesPart.class, createParameters().add("product.brand.name", "Nissan").build());
			assertThat("Failed to list with subcriteria",salesParts.size(), is(2));
			
			salesParts = dataStore.list(SalesPart.class, createParameters().disjoin().add("product.name", "Altima")
					.add("product.brand.name", "Audi").build());

			assertThat("Failed to list disjoint with subcriteria", salesParts.size(), is(2));

			salesParts = dataStore.list(SalesPart.class, createParameters().disjoin().add("product.name", "Altima")
					.add("product.id", 2)
					.add("product.brand.name", "Audi")
					.add("product.brand.id", 1).build());

			assertThat("Failed to list disjoint with subcriteria", salesParts.size(), is(3));
		} catch (DataStoreException e) {
			fail("Exception of type " + e.getClass().getName() + " was thrown. Message is " + e.getMessage());
		}
	}

	private void printInventory(List<Inventory> inventories) {
		System.out.println(String.format("%1$20s|%2$20s|%3$20s|%4$20s|%5$20s|%6$20s|%7$20s", "ID", "Brand", "Product",
				"Description", "Price", "Quantity", "Date"));
		System.out.println(new String(new char[150]).replace('\0', '_'));
		for (Inventory inventory : inventories) {
			System.out.println(String.format("%1$20d|%2$20s|%3$20s|%4$20s|%5$5s%6$15.2f|%7$20d|%8$13te/%8$tm/%8$tY",
					inventory.getId(),
					inventory.getProduct() != null && inventory.getProduct().getBrand() != null
							? inventory.getProduct().getBrand().getName() : null,
					inventory.getProduct() != null ? inventory.getProduct().getName() : null,
					inventory.getProduct() != null ? inventory.getProduct().getDescription() : null,
					inventory.getCurrency(), inventory.getPrice(), inventory.getQuantity(), inventory.getDate()));
		}
	}

	@After
	public void tearDown() {
		CurrentSessionContextHelper.closeOpenSessions();
	}

	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private Brand createBrand(int id, String name) {
		Brand brand = new Brand();
		brand.setName(name);
		brand.setId(id);

		return brand;
	}

	private Product createProduct(int id, Brand brand, String description, String name) {
		Product product = new Product();
		product.setBrand(brand);
		product.setDescription(description);
		product.setId(id);
		product.setName(name);

		return product;
	}

	private Inventory createInventory(int id, String currency, double price, String date, Product product,
			int quantity) {
		Inventory inventory = new Inventory();
		inventory.setCurrency(currency);
		inventory.setPrice(price);
		try {
			inventory.setDate(formatter.parse(date));
		} catch (ParseException e) {
			inventory.setDate(new Date());
		}
		inventory.setId(id);
		inventory.setProduct(product);
		inventory.setQuantity(quantity);
		return inventory;
	}
	
	private SalesPart createSalesPart(int id, Product product, double price, SalesOrder salesOrder) {
		SalesPart salesPart = new SalesPart();
		salesPart.setId(id);
		salesPart.setPrice(price);
		salesPart.setProduct(product);
		salesPart.setSalesOrder(salesOrder);
		
		return salesPart;
	}
}
