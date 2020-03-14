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

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.quakearts.test.hibernate.Brand;
import com.quakearts.test.hibernate.Inventory;
import com.quakearts.test.hibernate.Product;
import com.quakearts.test.hibernate.SalesOrder;
import com.quakearts.test.hibernate.SalesPart;
import com.quakearts.webapp.hibernate.CurrentSessionContextHelper;
import com.quakearts.webapp.hibernate.HibernateHelper;
import com.quakearts.webapp.hibernate.HibernateSessionDataStore;
import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFactory;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;

import static com.quakearts.test.ErrorThrowingSessionBuilder.*;
import static com.quakearts.webapp.orm.query.QueryOrder.property;
import static com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder.createCriteria;

public class OrmDataStoreTest {

	@Test
	public void testDataStore() throws Exception {
		assertTrue("No factory to generate DataStore", DataStoreFactory.getInstance() != null);

		DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
		runTest(dataStore);
	}
	
	@Test
	public void testDataStoreWithDomain() throws Exception {
		assertTrue("No factory to generate DataStore", DataStoreFactory.getInstance() != null);

		DataStore dataStore = DataStoreFactory.getInstance().getDataStore("testdomain");
		runTest(dataStore);
	}

	protected void runTest(DataStore dataStore) {
		assertTrue("DataStore was null", dataStore != null);
		assertThat("DataStore is not " + HibernateSessionDataStore.class.getName(), dataStore.getClass().getName(),
				is(HibernateSessionDataStore.class.getName()));

		Brand brand = createBrand(1, "Quakearts");
		dataStore.save(brand);
		dataStore.flushBuffers();
		Brand savedBrand = dataStore.get(Brand.class, 1);
		assertThat("No brand with ID 1", savedBrand, is(notNullValue()));
		assertThat("Brand name does not match", savedBrand.getName(), is(brand.getName()));

		brand = dataStore.refresh(savedBrand);
		assertThat("Brand name does not match", savedBrand.getName(), is(brand.getName()));
		
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
		assertThat("No brand with ID 2", savedBrand, is(notNullValue()));
		assertThat("Brand name does not match", savedBrand.getName(), is(brand.getName()));

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

		List<Inventory> list = dataStore.list(Inventory.class);		
		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(5));
		printInventory(list);
		
		// SELECT FROM Inventory WHERE product = Product(5,A6)
		list = dataStore.find(Inventory.class)
				.using(createCriteria().property("product").mustBeEqualTo(product)
				.finish())
			.thenList();

		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(1));
		printInventory(list);

		// SELECT FROM Inventory WHERE quantity = 2 or quantity = 6
		list = dataStore.find(Inventory.class).using(
					createCriteria()
						.property("quantity").mustBeEqualToOneOf(2, 6)
					.orderBy(property("id").descending())
					.finish())
				.thenList();

		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));

		assertThat(list.iterator().next().getProduct().getName(), is("Qashqai"));
		
		printInventory(list);

		// SELECT FROM Inventory WHERE quantity = 4 or product =
		// product(5,A6)
		list = dataStore.find(Inventory.class).using(createCriteria()
					.requireAnyOfTheFollowing()
						.property("quantity").mustBeEqualTo(4)
						.property("product").mustBeEqualTo(product)
					.orderBy(property("quantity").ascending())
					.finish())
			.thenList();

		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));
		assertThat(list.iterator().next().getProduct().getName(), is("A6"));

		printInventory(list);

		// SELECT FROM Inventory WHERE quantity = 3 and product =
		// product(5,A6)
		list = dataStore.find(Inventory.class).using(createCriteria()
				.requireAllOfTheFollowing()
					.property("quantity").mustBeEqualTo(3)
					.property("product").mustBeEqualTo(product).finish())
			.thenList();

		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(1));
		printInventory(list);

		// SELECT FROM Inventory WHERE (quantity = 3 and product =
		// product(5,A6)) or (quantity = 6)
		list = dataStore.find(Inventory.class)
				.using(createCriteria()
					.requireAnyOfTheFollowing()
						.property("quantity").mustBeEqualToOneOf(6)
						.or()
							.requireAllOfTheFollowing()
								.property("quantity").mustBeEqualTo(3)
								.and()
								.property("product").mustBeEqualTo(product)
							.closeSet()
						.finish())
				.thenList();

		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(2));
		printInventory(list);

		// SELECT FROM Inventory WHERE (quantity between 3 and 6)
		list = dataStore.find(Inventory.class)
				.using(createCriteria().requireThat()
						.property("quantity").mustBeBetween(3).and(6)
					.finish())
				.thenList();
		assertTrue("Nothing returned", list != null);
		assertThat("Invalid number of items returned:" + list.size(), list.size(), is(4));
		printInventory(list);

		// SELECT FROM Product WHERE (description like %Salon)
		List<Product> products = dataStore.find(Product.class)
				.using(createCriteria()
						.require().property("description").mustBeLike("%Salon")
					.finish())
				.thenList();

		assertTrue(products != null);
		assertThat("Invalid number of items returned:" + products.size(), products.size(), is(3));

		System.out.println(String.format("%1$20s|%2$20s|%3$20s|%4$20s", "ID", "Brand", "Name", "Description"));
		System.out.println(new String(new char[90]).replace('\0', '_'));
		for (Product foundProduct : products) {
			System.out.println(String.format("%1$20d|%2$20s|%3$20s|%4$20s", foundProduct.getId(),
					foundProduct.getBrand() != null ? product.getBrand().getName() : null, foundProduct.getName(),
					foundProduct.getDescription()));
		}

		list = dataStore.find(Inventory.class)
				.using(createCriteria().setMaxResultsAs(3)
			.finish()).thenList();
		assertThat("Max results did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
		printInventory(list);

		list = dataStore.find(Inventory.class)
				.using(createCriteria()
						.requireAnyOfTheFollowing()
						.property("quantity").mustBeBetween(0).and(3)
						.property("quantity").mustBeBetween(4).and(6)
						.setMaxResultsAs(3)
					.finish())
				.thenList();
		assertThat("Max results did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
		printInventory(list);
		
		SalesOrder salesOrder = new SalesOrder();
		salesOrder.setId(1);
		
		dataStore.save(salesOrder);
		dataStore.flushBuffers();
		
		dataStore.save(createSalesPart(1, product, 20000, salesOrder));
		dataStore.save(createSalesPart(2, dataStore.get(Product.class, 1), 20000, salesOrder));
		dataStore.save(createSalesPart(3, dataStore.get(Product.class, 2), 20000, salesOrder));
		
		dataStore.flushBuffers();
		//SELECT FROM SalesPart WHERE product.brand.name = "Nissan"
		List<SalesPart> salesParts = dataStore.find(SalesPart.class)
				.using(createCriteria()
						.property("product.brand.name").mustBeEqualTo("Nissan")
			.finish()).thenList();
		assertThat("Failed to list with subcriteria",salesParts.size(), is(2));
		printSalesPart(salesParts);
		
		salesParts = dataStore.find(SalesPart.class)
				.using(createCriteria().requireAnyOfTheFollowing()
						.property("product.name").mustBeEqualTo("Altima")
						.property("product.brand.name").mustBeEqualTo("Audi")
					.finish())
				.thenList();

		assertThat("Failed to list disjoint with subcriteria", salesParts.size(), is(2));
		printSalesPart(salesParts);

		salesParts = dataStore.find(SalesPart.class)
				.using(createCriteria().requireAnyOfTheFollowing()
						.property("product.name").mustBeEqualTo("Altima")
						.property("product.id").mustBeEqualTo(2)
						.property("product.brand.name").mustBeEqualTo("Audi")
						.property("product.brand.id").mustBeEqualTo(1)
					.finish())
				.thenList();

		assertThat("Failed to list disjoint with subcriteria", salesParts.size(), is(3));
		printSalesPart(salesParts);

		assertThat(dataStore.getConfigurationProperty("com.quakearts.test"), is("TestValue"));
		
		list = dataStore.find(Inventory.class)
			.filterBy("quantity").withValues().startingFrom(4)
			.thenList();
		
		assertThat("Starting from did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
		printInventory(list);
		
		list = dataStore.find(Inventory.class)
				.filterBy("quantity").withValues().upTo(4)
				.thenList();
			
		assertThat("Up to did not work. Returned "+list.size()+" instead of 3", list.size(), is(3));
		printInventory(list);
		
		list = dataStore.find(Inventory.class).usingAnyMatchingFilter()
				.filterBy("product.brand.name").withAValueEqualTo("Audi")
				.filterBy("product.id").withAValueEqualTo(2)
				.filterBy("product.brand.id").withAValueEqualTo(1)
			.thenList();
		assertThat("Failed to list disjoint with subcriteria", salesParts.size(), is(3));
		printInventory(list);

		list = dataStore.find(Inventory.class)
				.filterBy("product.brand.name").withAValueNotEqualTo("Nissan")
			.thenList();
		assertThat("Failed to list products with brand names not equal to Nissan", list.size(), is(2));
		printInventory(list);
		
		list = dataStore.find(Inventory.class)
				.filterBy("quantity").withValuesNot().between(3).and(6)
			.thenList();
		assertThat("Failed to list products with quantities less than 3 or greater than 6", list.size(), is(1));
		printInventory(list);
		
		list = dataStore.find(Inventory.class)
				.filterBy("quantity").withValuesNot().startingFrom(3)
			.thenList();
		assertThat("Failed to list products with quantities less than 3", list.size(), is(1));
		printInventory(list);

		list = dataStore.find(Inventory.class)
				.filterBy("price").withValuesNot().upTo(70_000d)
			.thenList();
		assertThat("Failed to list products with prices greater than 70,000", list.size(), is(3));
		printInventory(list);
		
		list = dataStore.find(Inventory.class)
				.filterBy("product.name").withAValueNotLike("%finder")
			.thenList();
		assertThat("Failed to list products with name not like 'finder'", list.size(), is(4));
		printInventory(list);

		list = dataStore.find(Inventory.class)
				.filterBy("product.name").withAValueNotEqualToOneOf("A6","A4","Qashqai")
			.thenList();
		assertThat("Failed to list products not named 'A6' or 'A4' or 'Qashqai'", list.size(), is(2));
		printInventory(list);
	}

	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void testInvalidChoice() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(
				new HibernateException("Invalid choice list for parameter id. "
						+ "Choice list must be greater than zero.")));
		DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
		dataStore.find(Inventory.class).using(CriteriaMapBuilder
				.createCriteria().property("id").mustBeEqualTo(new Choice())
				.finish())
			.thenList();
	}
	
	
	
	private Matcher<? extends Throwable> isCause(HibernateException hibernateException) {
		return new BaseMatcher<Throwable>() {

			@Override
			public boolean matches(Object item) {
				return item.getClass() == HibernateException.class
						&& ((HibernateException)item).getMessage() != null
						&& ((HibernateException)item).getMessage().equals(hibernateException.getMessage());
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("HibernateException with message "+hibernateException.getMessage());
			}
			
		};
	}

	@Test
	public void testDataStoreFunction() throws Exception {
		DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
		
		dataStore.executeFunction((dataStoreConnection)->{
			Connection connection = dataStoreConnection.getConnection(Connection.class);
			try {
				ResultSet resultSet = connection.getMetaData().getSchemas();
				System.out.println("Schemas");
				if(resultSet.next())
				do {
					System.out.println(resultSet.getString(1));
				} while(resultSet.next());
			} catch (SQLException e) {
				fail("Exception "+e.getClass().getName());
			}
		});
		
		dataStore.executeFunction((dataStoreConnection)->{
			dataStoreConnection.getConnection(Session.class);
		});
	}
	
	@Test
	public void testClearBuffers() throws Exception {
		DataStore dataStore = DataStoreFactory.getInstance().getDataStore();
		Brand brand1 = createBrand(21, "Quakearts");
		dataStore.save(brand1);
		Brand brand2 = createBrand(22, "QuakeartsII");
		dataStore.save(brand2);
		dataStore.flushBuffers();
		CurrentSessionContextHelper.closeOpenSessions();
		
		dataStore = DataStoreFactory.getInstance().getDataStore();
		Brand brand3 = createBrand(23, "QuakeartsIII");
		dataStore.save(brand3);
		Brand brand1Update = createBrand(21, "QuakeartsI");
		dataStore.update(brand1Update);
		dataStore.delete(brand2);
		dataStore.clearBuffers();
		CurrentSessionContextHelper.closeOpenSessions();

		dataStore = DataStoreFactory.getInstance().getDataStore();
		Brand savedBrand = dataStore.get(Brand.class, 21);
		assertThat("No brand with ID 21", savedBrand, is(notNullValue()));
		assertThat("Brand name does not match", savedBrand.getName(), is(brand1.getName()));
		savedBrand = dataStore.get(Brand.class, 22);
		assertThat("No brand with ID 22", savedBrand, is(notNullValue()));
		assertThat("Brand name does not match", savedBrand.getName(), is(brand2.getName()));
		
		savedBrand = dataStore.get(Brand.class, 23);
		assertThat("Brand with ID 22 was created", savedBrand, is(nullValue()));		
	}
	
	@Test
	public void testOtherHibernateORMMethods() throws Exception {
		Configuration configuration = HibernateHelper.getCurrentConfiguration();
		assertTrue(configuration == HibernateHelper.getCurrentConfiguration());
				
		configuration = HibernateHelper.getConfiguration("testdomain");
		assertTrue(configuration == HibernateHelper.getConfiguration("testdomain"));
		
		HibernateSessionDataStore dataStore = (HibernateSessionDataStore) DataStoreFactory
				.getInstance().getDataStore();
		
		assertThat(dataStore.getSession(), is(notNullValue()));
		assertThat(dataStore.getDomain(), is(nullValue()));

		dataStore = (HibernateSessionDataStore) DataStoreFactory
				.getInstance().getDataStore("testdomain");
		
		assertThat(dataStore.getSession(), is(notNullValue()));
		assertThat(dataStore.getDomain(), is("testdomain"));
		
		assertThat(CurrentSessionContextHelper.getInstance(), is(notNullValue()));
		assertThat(CurrentSessionContextHelper.getInstance("testdomain"), is(notNullValue()));
	}
	
	@Test
	public void testSaveHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Save Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).save(new Inventory());
	}
	
	@Test
	public void testGetHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Get Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).get(Inventory.class, 1);
	}

	@Test
	public void testUpdateHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Update Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).update(new Inventory());
	}
	
	@Test
	public void testDeleteHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Delete Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).delete(new Inventory());
	}

	@Test
	public void testSaveOrUpdateHibernateException() throws Exception {
		HibernateException exception = new HibernateException("SaveOrUpdate Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).saveOrUpdate(new Inventory());
	}
	
	@Test
	public void testRefershHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Refresh Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).refresh(new Inventory());
	}
	
	@Test
	public void testFlushHibernateException() throws Exception {
		HibernateException exception = new HibernateException("Flush Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).flushBuffers();
	}
	
	@Test
	public void testExecuteFunctionHibernateException() throws Exception {
		HibernateException exception = new HibernateException("ExecuteFunction Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).executeFunction(connection->{});
	}
	
	@Test
	public void testExecuteFunctionDataStoreException() throws Exception {
		expectedException.expect(DataStoreException.class);
		expectedException.expectMessage("Unsupported connection class: java.lang.String");
		DataStoreFactory.getInstance().getDataStore()
			.executeFunction(connection->{
				connection.getConnection(String.class);
			});
	}
	
	@Test
	public void testClearBuffersHibernateException() throws Exception {
		HibernateException exception = new HibernateException("ClearBuffers Exception");
		expectedException.expect(DataStoreException.class);
		expectedException.expectCause(isCause(exception));
		getDataStoreWithInjected(createSession(()->{
			throw exception;
		})).clearBuffers();
	}

	private DataStore getDataStoreWithInjected(Session injectee) throws Exception {
		return inject(injectee, DataStoreFactory.getInstance().getDataStore());
	}

	private DataStore inject(Session injectee, DataStore dataStore) throws Exception {
		Field sessionField = dataStore.getClass().getDeclaredField("session");
		sessionField.setAccessible(true);
		sessionField.set(dataStore, injectee);
		return dataStore;
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
	
	private void printSalesPart(List<SalesPart> salesParts) {
		System.out.println(String.format("%1$20s|%2$20s|%3$20s|%4$20s|%5$20s|%6$20s", "ID", "Brand", "Product",
				"Description", "Sales Order ID", "Price"));
		System.out.println(new String(new char[130]).replace('\0', '_'));
		for (SalesPart salePart : salesParts) {
			System.out.println(String.format("%1$20d|%2$20s|%3$20s|%4$20s|%5$20d|%6$5s %7$15.2f",
					salePart.getId(),
					salePart.getProduct() != null && salePart.getProduct().getBrand() != null
							? salePart.getProduct().getBrand().getName() : null,
					salePart.getProduct() != null ? salePart.getProduct().getName() : null,
					salePart.getProduct() != null ? salePart.getProduct().getDescription() : null,
					salePart.getSalesOrder().getId(),salePart.getCurrency(), salePart.getPrice()));
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
		salesPart.setCurrency("GHS");
		salesPart.setPrice(price);
		salesPart.setProduct(product);
		salesPart.setSalesOrder(salesOrder);
		
		return salesPart;
	}
}
