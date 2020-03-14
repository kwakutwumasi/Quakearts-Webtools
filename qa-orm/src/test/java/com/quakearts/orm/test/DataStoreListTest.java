package com.quakearts.orm.test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.*;

import static com.quakearts.webapp.orm.query.QueryOrder.property;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.DataStore.ListBuilder;
import com.quakearts.webapp.orm.DataStoreFunction;
import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.Not;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;

public class DataStoreListTest {

	@Test
	public void testFluidAPI() throws Exception {
		TestDataStore dataStore = new TestDataStore();
		List<ORMTestObject> list = generateListFilter(dataStore)
			.thenList();
		
		assertThat(list.size(), is(3));

		Optional<ORMTestObject> optional = generateListFilter(dataStore)
				.thenGetFirst();
		
		assertThat(optional.isPresent(), is(true));
		assertThat(optional.get(), is(new ORMTestObject("1")));
	}

	protected ListBuilder<ORMTestObject> generateListFilter(TestDataStore dataStore) {
		return dataStore.find(ORMTestObject.class).usingAnyMatchingFilter()
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
			.orderBy(property("testOrder1").descending(), 
					property("testOrder2").ascending());
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
		
		@Override
		public <E> ListBuilder<E> find(Class<E> clazz) {
			return new ListBuilder<>(clazz, this::list);
		}

		@SuppressWarnings("unchecked")
		private <Entity> List<Entity> list(Class<Entity> clazz, CriteriaMap criteriaMap) throws DataStoreException {
			assertThat(clazz == ORMTestObject.class, is(true));
			assertThat(criteriaMap, is(notNullValue()));
			assertThat(criteriaMap.size(), is(2));
			assertThat(criteriaMap.containsKey(CriteriaMap.DISJUNCTION), is(true));
			assertTrue(Map.class.isAssignableFrom(criteriaMap.get(CriteriaMap.DISJUNCTION).getClass()));
			assertThat(criteriaMap.containsKey(CriteriaMap.MAXRESULTS), is(true));
			assertThat(criteriaMap.get(CriteriaMap.MAXRESULTS), is(3));
			
			assertThat(criteriaMap.getOrder(), is(notNullValue()));
			assertThat(criteriaMap.getOrder().length, is(2));
			assertThat(criteriaMap.getOrder()[0].getProperty(), is("testOrder1"));
			assertThat(criteriaMap.getOrder()[0].isDescending(), is(true));
			assertThat(criteriaMap.getOrder()[1].getProperty(), is("testOrder2"));
			assertThat(criteriaMap.getOrder()[1].isAscending(), is(true));

			criteriaMap = (CriteriaMap) criteriaMap.get(CriteriaMap.DISJUNCTION);
			assertThat(criteriaMap.size(), is(12));

			assertThat(criteriaMap.containsKey("testValue"), is(true));
			assertThat(criteriaMap.get("testValue"), is("Test Value"));
			
			assertThat(criteriaMap.containsKey("testValueNot"), is(true));
			assertThat(criteriaMap.get("testValueNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testValueNot")).getValue(), is("Not Test Value"));

			assertThat(criteriaMap.containsKey("testVariable"), is(true));
			assertThat(criteriaMap.get("testVariable") instanceof VariableString, is(true));
			assertThat(((VariableString)criteriaMap.get("testVariable")).getValue(), is("Test Variable"));

			assertThat(criteriaMap.containsKey("testVariableNot"), is(true));
			assertThat(criteriaMap.get("testVariableNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testVariableNot")).getValue() instanceof VariableString, is(true));
			assertThat(((VariableString)((Not)criteriaMap.get("testVariableNot")).getValue()).getValue(), is("Not Test Variable"));
			
			assertThat(criteriaMap.containsKey("testRangeBetween"), is(true));
			assertThat(criteriaMap.get("testRangeBetween") instanceof Range, is(true));
			assertThat(((Range)criteriaMap.get("testRangeBetween")).getFrom(), is(0));
			assertThat(((Range)criteriaMap.get("testRangeBetween")).getTo(), is(1));

			assertThat(criteriaMap.containsKey("testRangeBetweenNot"), is(true));
			assertThat(criteriaMap.get("testRangeBetweenNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testRangeBetweenNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)criteriaMap.get("testRangeBetweenNot")).getValue()).getFrom(), is(0));
			assertThat(((Range)((Not)criteriaMap.get("testRangeBetweenNot")).getValue()).getTo(), is(1));
			
			assertThat(criteriaMap.containsKey("testRangeFrom"), is(true));
			assertThat(criteriaMap.get("testRangeFrom") instanceof Range, is(true));
			assertThat(((Range)criteriaMap.get("testRangeFrom")).getFrom(), is(0));

			assertThat(criteriaMap.containsKey("testRangeFromNot"), is(true));
			assertThat(criteriaMap.get("testRangeFromNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testRangeFromNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)criteriaMap.get("testRangeFromNot")).getValue()).getFrom(), is(0));
			
			assertThat(criteriaMap.containsKey("testRangeTo"), is(true));
			assertThat(criteriaMap.get("testRangeTo") instanceof Range, is(true));
			assertThat(((Range)criteriaMap.get("testRangeTo")).getTo(), is(1));
			
			assertThat(criteriaMap.containsKey("testRangeToNot"), is(true));
			assertThat(criteriaMap.get("testRangeToNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testRangeToNot")).getValue() instanceof Range, is(true));
			assertThat(((Range)((Not)criteriaMap.get("testRangeToNot")).getValue()).getTo(), is(1));

			assertThat(criteriaMap.containsKey("testChoices"), is(true));
			assertThat(criteriaMap.get("testChoices") instanceof Choice, is(true));
			assertThat(((Choice)criteriaMap.get("testChoices")).getChoices().size(), is(3));
			assertThat(((Choice)criteriaMap.get("testChoices")).getChoices().get(0), is("Choice1"));
			assertThat(((Choice)criteriaMap.get("testChoices")).getChoices().get(1), is("Choice2"));
			assertThat(((Choice)criteriaMap.get("testChoices")).getChoices().get(2), is("Choice3"));

			assertThat(criteriaMap.containsKey("testChoicesNot"), is(true));
			assertThat(criteriaMap.get("testChoicesNot") instanceof Not, is(true));
			assertThat(((Not)criteriaMap.get("testChoicesNot")).getValue() instanceof Choice, is(true));
			assertThat(((Choice)((Not)criteriaMap.get("testChoicesNot")).getValue()).getChoices().size(), is(3));
			assertThat(((Choice)((Not)criteriaMap.get("testChoicesNot")).getValue()).getChoices().get(0), is("Choice1"));
			assertThat(((Choice)((Not)criteriaMap.get("testChoicesNot")).getValue()).getChoices().get(1), is("Choice2"));
			assertThat(((Choice)((Not)criteriaMap.get("testChoicesNot")).getValue()).getChoices().get(2), is("Choice3"));
			
			return (List<Entity>) Arrays.asList(new ORMTestObject("1"), new ORMTestObject("2"),new ORMTestObject("3"));
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

		@Override
		public <E> List<E> list(Class<E> clazz) {
			return null;
		}
		
	}
}
