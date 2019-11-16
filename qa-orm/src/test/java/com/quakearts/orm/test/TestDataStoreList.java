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
import com.quakearts.webapp.orm.query.ListBuilder;
import com.quakearts.webapp.orm.query.Not;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

public class TestDataStoreList {

	@Test
	public void testFluidAPI() throws Exception {
		TestDataStore dataStore = new TestDataStore();
		List<ORMTest> list = generateListFilter(dataStore)
			.thenList();
		
		assertThat(list.size(), is(3));

		Optional<ORMTest> optional = generateListFilter(dataStore)
				.thenGetFirst();
		
		assertThat(optional.isPresent(), is(true));
		assertThat(optional.get(), is(new ORMTest("1")));
	}

	protected ListBuilder<ORMTest> generateListFilter(TestDataStore dataStore) {
		return dataStore.find(ORMTest.class).usingAnyMatchingFilter()
			.filterBy("testValue").withAValueEqualTo("Test Value")
			.filterBy("testValueNot").withAValueNotEqualTo("Not Test Value")
			.filterBy("testVariable").withAValueLike("Test Variable")
			.filterBy("testVariableNot").withAValueNotLike("Not Test Variable")
			.filterBy("testRangeBetween").withValues().between(0).and(1)
			.filterBy("testRangeBetweenNot").withValuesNot().between(0).and(1)
			.filterBy("testRangeFrom").withValues().startingFrom(0)
			.filterBy("testRangeFromNot").withValuesNot().startingFrom(0)
			.filterBy("testRangeTo").withValues().upTo(1)
			.filterBy("testRangeToNot").withValuesNot().upTo(1)
			.filterBy("testChoices").withAValueEqualToOneOf("Choice1","Choice2","Choice3")
			.filterBy("testChoicesNot").withAValueNotEqualToOneOf("Choice1","Choice2","Choice3")
			.useAResultLimitOf(3)
			.orderBy(new QueryOrder("testOrder1", false))
			.orderBy(new QueryOrder("testOrder2", true));
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
			assertThat(parameters.size(), is(2));
			assertThat(parameters.containsKey(ParameterMapBuilder.DISJUNCTION), is(true));
			assertTrue(Map.class.isAssignableFrom(parameters.get(ParameterMapBuilder.DISJUNCTION).getClass()));
			assertThat(parameters.containsKey(ParameterMapBuilder.MAXRESULTS), is(true));
			assertThat(parameters.get(ParameterMapBuilder.MAXRESULTS), is(3));
			parameters = (Map<String, Serializable>) 
					parameters.get(ParameterMapBuilder.DISJUNCTION);
			assertThat(parameters.size(), is(12));

			assertThat(parameters.containsKey("testValue"), is(true));
			assertThat(parameters.get("testValue"), is("Test Value"));
			
			assertThat(parameters.containsKey("testValueNot"), is(true));
			assertThat(parameters.get("testValueNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testValueNot")).getValue(), is("Not Test Value"));

			assertThat(parameters.containsKey("testVariable"), is(true));
			assertThat(parameters.get("testVariable") instanceof VariableString, is(true));
			assertThat(((VariableString)parameters.get("testVariable")).getValue(), is("Test Variable"));

			assertThat(parameters.containsKey("testVariableNot"), is(true));
			assertThat(parameters.get("testVariableNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testVariableNot")).getValue() instanceof VariableString, is(true));
			assertThat(((VariableString)((Not)parameters.get("testVariableNot")).getValue()).getValue(), is("Not Test Variable"));
			
			assertThat(parameters.containsKey("testRangeBetween"), is(true));
			assertThat(parameters.get("testRangeBetween") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeBetween")).getFrom(), is(0));
			assertThat(((Range)parameters.get("testRangeBetween")).getTo(), is(1));

			assertThat(parameters.containsKey("testRangeBetweenNot"), is(true));
			assertThat(parameters.get("testRangeBetweenNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testRangeBetweenNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)parameters.get("testRangeBetweenNot")).getValue()).getFrom(), is(0));
			assertThat(((Range)((Not)parameters.get("testRangeBetweenNot")).getValue()).getTo(), is(1));
			
			assertThat(parameters.containsKey("testRangeFrom"), is(true));
			assertThat(parameters.get("testRangeFrom") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeFrom")).getFrom(), is(0));

			assertThat(parameters.containsKey("testRangeFromNot"), is(true));
			assertThat(parameters.get("testRangeFromNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testRangeFromNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)parameters.get("testRangeFromNot")).getValue()).getFrom(), is(0));
			
			assertThat(parameters.containsKey("testRangeTo"), is(true));
			assertThat(parameters.get("testRangeTo") instanceof Range, is(true));
			assertThat(((Range)parameters.get("testRangeTo")).getTo(), is(1));
			
			assertThat(parameters.containsKey("testRangeToNot"), is(true));
			assertThat(parameters.get("testRangeToNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testRangeToNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)parameters.get("testRangeToNot")).getValue()).getTo(), is(1));

			assertThat(parameters.containsKey("testChoices"), is(true));
			assertThat(parameters.get("testChoices") instanceof Choice, is(true));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().size(), is(3));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(0), is("Choice1"));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(1), is("Choice2"));
			assertThat(((Choice)parameters.get("testChoices")).getChoices().get(2), is("Choice3"));

			assertThat(parameters.containsKey("testChoicesNot"), is(true));
			assertThat(parameters.get("testChoicesNot") instanceof Not, is(true));
			assertThat(((Not)parameters.get("testChoicesNot")).getValue() instanceof Choice, is(true));
			assertThat(((Choice)((Not)parameters.get("testChoicesNot")).getValue()).getChoices().size(), is(3));
			assertThat(((Choice)((Not)parameters.get("testChoicesNot")).getValue()).getChoices().get(0), is("Choice1"));
			assertThat(((Choice)((Not)parameters.get("testChoicesNot")).getValue()).getChoices().get(1), is("Choice2"));
			assertThat(((Choice)((Not)parameters.get("testChoicesNot")).getValue()).getChoices().get(2), is("Choice3"));

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
