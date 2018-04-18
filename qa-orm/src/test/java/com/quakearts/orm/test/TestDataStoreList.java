package com.quakearts.orm.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStoreFunction;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

public class TestDataStoreList {

	@Test
	public void testFluidAPI() throws Exception {
		TestDataStore dataStore = new TestDataStore();
		List<ORMTest> list = dataStore.find(ORMTest.class)
			.filterBy("testValue").withAValueEqualTo("Test Value")
			.filterBy("testVariable").withAValueLike("Test Variable")
			.filterBy("testRangeBetween").withValues().between(0).and(1)
			.filterBy("testRangeFrom").withValues().startingFrom(0)
			.filterBy("testRangeTo").withValues().upTo(1)
			.filterBy("testChoices").withAValueEqualToOneOf("Choice1","Choice2","Choice3")
			.useAResultLimitOf(3)
			.orderBy(new QueryOrder("testOrder1", false))
			.orderBy(new QueryOrder("testOrder2", true))
			.thenList();
		
		assertThat(list.size(), is(3));

		Optional<ORMTest> optional = dataStore.find(ORMTest.class)
				.filterBy("testValue").withAValueEqualTo("Test Value")
				.filterBy("testVariable").withAValueLike("Test Variable")
				.filterBy("testRangeBetween").withValues().between(0).and(1)
				.filterBy("testRangeFrom").withValues().startingFrom(0)
				.filterBy("testRangeTo").withValues().upTo(1)
				.filterBy("testChoices").withAValueEqualToOneOf("Choice1","Choice2","Choice3")
				.useAResultLimitOf(3)
				.orderBy(new QueryOrder("testOrder1", false))
				.orderBy(new QueryOrder("testOrder2", true))
				.thenGetFirst();
		
		assertThat(optional.isPresent(), is(true));
		assertThat(optional.get(), is(new ORMTest("1")));
	}

	class TestDataStore implements DataStore {

		@Override
		public void save(Object object) throws DataStoreException {
		}

		@Override
		public <Entity> Entity get(Class<Entity> clazz, Serializable id) throws DataStoreException {
			return null;
		}

		@Override
		public void update(Object object) throws DataStoreException {
		}

		@Override
		public void delete(Object object) throws DataStoreException {
		}

		@SuppressWarnings("unchecked")
		@Override
		public <Entity> List<Entity> list(Class<Entity> clazz, Map<String, Serializable> parameters,
				QueryOrder... orders) throws DataStoreException {
			assertThat(clazz == ORMTest.class, is(true));
			assertThat(parameters, is(notNullValue()));
			assertThat(parameters.size(), is(7));
			
			assertThat(parameters.containsKey(ParameterMapBuilder.MAXRESULTS), is(true));
			assertThat(parameters.get(ParameterMapBuilder.MAXRESULTS), is(3));
			assertThat(parameters.containsKey("testValue"), is(true));
			assertThat(parameters.get("testValue"), is("Test Value"));
			assertThat(parameters.containsKey("testVariable"), is(true));
			assertThat(parameters.get("testVariable") instanceof VariableString, is(true));
			assertThat(((VariableString)parameters.get("testVariable")).getValue(), is("Test Variable"));
			assertThat(parameters.containsKey("testRangeBetween"), is(true));
			assertThat(parameters.get("testRangeBetween") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeBetween")).getFrom(), is(0));
			assertThat(((Range)parameters.get("testRangeBetween")).getTo(), is(1));
			assertThat(parameters.containsKey("testRangeFrom"), is(true));
			assertThat(parameters.get("testRangeFrom") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeFrom")).getFrom(), is(0));
			assertThat(parameters.containsKey("testRangeTo"), is(true));
			assertThat(parameters.get("testRangeTo") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeTo")).getTo(), is(1));
			assertThat(parameters.containsKey("testChoices"), is(true));
			assertThat(parameters.get("testChoices") instanceof Choice, is(true));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().size(), is(3));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(0), is("Choice1"));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(1), is("Choice2"));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(2), is("Choice3"));
			
			assertThat(orders, is(notNullValue()));
			assertThat(orders.length, is(2));
			assertThat(orders[1].isAscending(), is(true));
			
			
			return (List<Entity>) Arrays.asList(new ORMTest("1"), new ORMTest("2"),new ORMTest("3"));
		}

		@Override
		public void saveOrUpdate(Object object) throws DataStoreException {
		}

		@Override
		public <Entity> Entity refresh(Entity object) throws DataStoreException {
			return null;
		}

		@Override
		public void flushBuffers() throws DataStoreException {
		}

		@Override
		public void executeFunction(DataStoreFunction function) throws DataStoreException {
		}

		@Override
		public String getConfigurationProperty(String propertyName) {
			return null;
		}

		@Override
		public void clearBuffers() throws DataStoreException {
		}
		
	}
}
