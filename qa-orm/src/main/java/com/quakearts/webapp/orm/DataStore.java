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
package com.quakearts.webapp.orm;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.quakearts.webapp.orm.exception.DataStoreException;
import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;
import com.quakearts.webapp.orm.query.criteria.CriteriaMapBuilder;

/** An abstraction of object storage technologies. Created to hide the complexities of the many object-database
 * techonolgies available. One API to interface with any data store.
 * @author kwakutwumasi-afriyie
 *
 */
public interface DataStore {
	/**Save and object to the persistent data store
	 * @param object the object to store
	 * @throws DataStoreException
	 */
	void save(Object object);
	/**Retrieve a single object identified by the id
	 * @param clazz The type of object to return
	 * @param id The id of the object in the persistent data store
	 * @return an instance of the class
	 * @throws DataStoreException
	 */
	<E> E get(Class<E> clazz, Serializable id);
	/**Update the object in the persistent data store
	 * @param object The object to update
	 * @throws DataStoreException
	 */
	void update(Object object);
	/**Delete the object in the persistent data store
	 * @param object The object to delete
	 * @throws DataStoreException
	 */
	void delete(Object object);
	/**Returns all objects of the specified class in the persistent store
	 * @param clazz
	 * @return
	 */
	<E> List<E> list(Class<E> clazz);
	/** A low level method for listing objects using complex selection criteria
	 * @param clazz the kind of entities to return
	 * @param parameters a special map containing the parameters, 
	 * 	their values, and the kind of selection criteria to use
	 * @param orders A list of values used to order the returned list.
	 * @return A {@link List} of object instances of the class
	 * @throws DataStoreException
	 */
	
	/**A fluid API for defining the selection criteria
	 * @param clazz
	 * @return A {@link ListBuilder} used to define the selection criteria
	 */
	<E> ListBuilder<E> find(Class<E> clazz);
	/**A useful method for saving objects that may or may not already exist in the persistent store
	 * @param object to save or update
	 * @throws DataStoreException
	 */
	void saveOrUpdate(Object object);
	
	/**Sometimes an object may be out of sync with the persistent store.
	 * This method returns a freshly synchronized instance of the object. Replace the 
	 * object with the returned object
	 * @param object The freshly synchronized object
	 * @return
	 * @throws DataStoreException
	 */
	<E> E refresh(E object);
	/**Some persistent stores hold objects in a buffer and only commit them to the store 
	 * when it is most efficient to do so. This method forces items held in the buffer 
	 * into the persistent store
	 * @throws DataStoreException
	 */
	void flushBuffers();
	/**Some data stores require special operations beyond the scope of this API
	 * These operations can be executed by special {@link DataStoreFunction}s
	 * @param function
	 * @throws DataStoreException
	 */
	void executeFunction(DataStoreFunction function);
	/**Fetch a property used to configure the data store
	 * @param propertyName
	 * @return The property, if any.
	 */
	String getConfigurationProperty(String propertyName);
	/**Cancel pending saves/updates/deletes. Can be used to prevent previous save/update/delete
	 * actions from being permanently persisted. This action should, for example,
	 * set the transaction as roll-back-only in a database transaction environment
	 * @throws DataStoreException
	 */
	void clearBuffers();
	
	@FunctionalInterface
	public interface Function<T> {
		List<T> list(Class<T> entityClazz, CriteriaMap criteriaMap);
	}
	
	/**A fluid API for searching the persistent store. Designed for simple selection criteria.
	 * For more fine grained selection criteria, us the {@linkplain DataStore#list(Class, java.util.Map, QueryOrder...)}
	 * Method and the {@link CriteriaMapBuilder}
	 * @author kwakutwumasi-afriyie
	 *
	 * @param <T>
	 */
	public class ListBuilder<T> {
		private Function<T> function;
		protected Class<T> entityClazz;
		private CriteriaMapBuilder mapBuilder;
		private ParameterBuilder parameterBuilder;
		private CriteriaMap criteriaMap;

		public ListBuilder(Class<T> entityClazz, Function<T> function) {
			this.function = function;
			this.entityClazz = entityClazz;
		}
		
		/**A terminal method. Returns the list using the selection criteria specified
		 * @return The list, if any
		 */
		public List<T> thenList() {
			return function.list(entityClazz, criteriaMap == null?
					getMapBuilder().finish(): criteriaMap);
		}
		
		/**Use the provided CriteriaMap instance
		 * @param criteriaMap
		 * @return This builder to chain methods
		 */
		public ListBuilder<T> using(CriteriaMap criteriaMap) {
			this.criteriaMap = criteriaMap;
			return this;
		}

		/**A terminal method. Returns the first item in the list matching the selection criteria specified
		 * @return An {@link Optional} containing the item if any
		 */
		public Optional<T> thenGetFirst() {
			 List<T> list = thenList();
			 if(list.isEmpty()) {
				return Optional.empty(); 
			 }
			 T first = list.get(0);
			 return Optional.of(first);
		}
		
		/**Change the mode of search from requiring all filters
		 * to using any one filter. This must be called BEFORE
		 * any filters are declared to take effect
		 * @return This builder to chain methods
		 */
		public ListBuilder<T> usingAnyMatchingFilter(){
			getMapBuilder().requireAnyOfTheFollowing();
			return this;
		}
		
		/**The initial method in the fluid API. Create a filter for the supplied property name
		 * @param propertyName the name of the property to use in the filter
		 * @return a {@link ParameterBuilder}
		 */
		public ParameterBuilder filterBy(String propertyName) {
			if(parameterBuilder == null)
				parameterBuilder = new ParameterBuilder();
			
			return parameterBuilder.setPropertyName(propertyName);
		}
		
		/**Used to specify the order of the returned items
		 * @param order {@link QueryOrder} to define the ordering
		 * @return This builder to chain methods
		 */
		public ListBuilder<T> orderBy(QueryOrder... order) {
			getMapBuilder().orderBy(order);
			return this;
		}
		
		/**Limit the size of the returned list
		 * @param limit The maximum amount of objects to return
		 * @return This builder to chain methods
		 */
		public ListBuilder<T> useAResultLimitOf(int limit) {
			getMapBuilder().setMaxResultsAs(limit);
			return ListBuilder.this;
		}
		
		private CriteriaMapBuilder getMapBuilder() {
			if(mapBuilder == null)
				mapBuilder = CriteriaMapBuilder.createCriteria();

			return mapBuilder;
		}

		/**A fluid API for specifying the selection criteria
		 * @author kwakutwumasi-afriyie
		 *
		 */
		public class ParameterBuilder {
			String parameter;
			private RangeBuilder rangeBuilder;

			private RangeBuilder getRangeBuilder() {
				if(rangeBuilder == null)
					rangeBuilder = new RangeBuilder();
				
				return rangeBuilder;
			}
			
			ParameterBuilder setPropertyName(String parameter) {
				this.parameter = parameter;
				return this;
			}
			
			/**Select objects that have values equal to the specified value
			 * @param value a serializable value to compare
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueEqualTo(Serializable value) {
				getMapBuilder().property(parameter).mustBeEqualTo(value);
				return ListBuilder.this;
			}
			
			/**Select objects that do not have values equal to the specified value
			 * @param value a serializable value to compare
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueNotEqualTo(Serializable value) {
				getMapBuilder().property(parameter).mustNotBeEqualTo(value);
				return ListBuilder.this;
			}
			
			/**Select objects that have string values like the specified value
			 * @param value the String to match
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueLike(String value){
				getMapBuilder().property(parameter).mustBeLike(value);
				return ListBuilder.this;
			}
			
			/**Select objects that do not have string values like the specified value
			 * @param value the String to match
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueNotLike(String value){
				getMapBuilder().property(parameter).mustNotBeEqualTo(new VariableString(value));
				return ListBuilder.this;
			}
			
			/**Entry method for the fluid API for specifying a range of values
			 * @return the @{link RangeBuilder}
			 */
			public RangeBuilder withValues() {
				return getRangeBuilder().setParameter(parameter);
			}
			
			/**Entry method for the fluid API for specifying a range of values
			 * that should not be contained in the result objects
			 * @return the @{link RangeBuilder}
			 */
			public RangeBuilder withValuesNot() {
				return getRangeBuilder().setParameterNot(parameter);
			}
			
			/**A list of values either one of which to match
			 * @param parameters
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueEqualToOneOf(Serializable... parameters) {
				getMapBuilder().property(parameter).mustBeEqualToOneOf(parameters);
				return ListBuilder.this;
			}
			
			/**A list of values none of which to match
			 * @param parameters
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> withAValueNotEqualToOneOf(Serializable... parameters) {
				Choice choice = new Choice();
				for(Serializable choiceParameter:parameters){
					choice.or(choiceParameter);
				}
				getMapBuilder().property(parameter).mustNotBeEqualTo(choice);
				return ListBuilder.this;
			}
		}
		
		/**A fluid API for specifying ranges
		 * @author kwakutwumasi-afriyie
		 *
		 */
		public class RangeBuilder {
			Range range;
			String parameter;
			boolean not;
			SubRangeBuilder subRangeBuilder;
			
			private SubRangeBuilder getSubRangeBuilder() {
				if(subRangeBuilder == null)
					subRangeBuilder = new SubRangeBuilder();
				
				return subRangeBuilder;
			}
			
			RangeBuilder setParameter(String parameter) {
				not = false;
				this.parameter = parameter;
				return this;
			}

			RangeBuilder setParameterNot(String parameter) {
				not = true;
				this.parameter = parameter;
				return this;
			}

			/**The entry method for the fluid API for specifying a range
			 * @param value
			 * @return This builder to chain methods
			 */
			public SubRangeBuilder between(Serializable value) {
				range = new Range();
				range.from(value);
				return getSubRangeBuilder();
			}
			
			public class SubRangeBuilder {
				/**Terminal method for the fluid API for specifying a range
				 * @param value
				 * @return The @{link ListBuilder} to chain methods
				 */
				public ListBuilder<T> and(Serializable value){
					range.to(value);
					if(not){
						getMapBuilder().property(parameter).mustNotBeEqualTo(range);
					} else {
						getMapBuilder().property(parameter).mustBeEqualTo(range);
					}
					return ListBuilder.this;
				}
			}
			
			/**The fluid API method for specifying a range beginning from the value
			 * @param value
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> startingFrom(Serializable value){
				if(not){
					getMapBuilder().property(parameter).mustNotBeEqualTo(new Range().from(value));
				} else {
					getMapBuilder().property(parameter).mustBeEqualTo(new Range().from(value));
				}
				return ListBuilder.this;
			}

			/**The fluid API method for specifying a range ending at the value
			 * @param value
			 * @return The @{link ListBuilder} to chain methods
			 */
			public ListBuilder<T> upTo(Serializable value){
				if(not){
					getMapBuilder().property(parameter).mustNotBeEqualTo(new Range().to(value));
				} else {				
					getMapBuilder().property(parameter).mustBeEqualTo(new Range().to(value));
				}
				return ListBuilder.this;
			}
		}
	}
}
