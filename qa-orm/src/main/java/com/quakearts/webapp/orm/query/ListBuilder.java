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
package com.quakearts.webapp.orm.query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.quakearts.webapp.orm.DataStore;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

/**A fluid API for searching the persistent store. Designed for simple selection criteria.
 * For more fine grained selection criteria, us the {@linkplain DataStore#list(Class, java.util.Map, QueryOrder...)}
 * Method and the {@link ParameterMapBuilder}
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public class ListBuilder<T> {
	private DataStore dataStore;
	private ParameterMapBuilder mapBuilder;
	private Class<T> entityClazz;
	private List<QueryOrder> order = new ArrayList<>();

	public ListBuilder(DataStore dataStore, Class<T> entityClazz) {
		this.dataStore = dataStore;
		this.entityClazz = entityClazz;
		mapBuilder = ParameterMapBuilder.createParameters();
	}
	
	/**A terminal method. Returns the list using the selection criteria specified
	 * @return The list, if any
	 */
	public List<T> thenList(){
		return dataStore.list(entityClazz, mapBuilder.build(), getOrder());
	}
	
	/**A terminal method. Returns the first item in the list matching the selection criteria specified
	 * @return An {@link Optional} containing the item if any
	 */
	public Optional<T> thenGetFirst() {
		 List<T> list = dataStore.list(entityClazz, mapBuilder.build(), getOrder());
		 if(list.isEmpty()) {
			return Optional.empty(); 
		 }
		 T first = list.get(0);
		 return Optional.of(first);
	}
	
	private QueryOrder[] getOrder() {
		return order.toArray(new QueryOrder[order.size()]);
	}

	/**The initial method in the fluid API. Create a filter for the supplied property name
	 * @param propertyName the name of the property to use in the filter
	 * @return a {@link ParameterBuilder}
	 */
	public ParameterBuilder filterBy(String propertyName){
		return new ParameterBuilder(propertyName);
	}
	
	/**Used to specify the order of the returned items
	 * @param order {@link QueryOrder} to define the ordering
	 * @return This builder to chain methods
	 */
	public ListBuilder<T> orderBy(QueryOrder order){
		this.order.add(order);
		return this;
	}
	
	/**Limit the size of the returned list
	 * @param limit The maximum amount of objects to return
	 * @return This builder to chain methods
	 */
	public ListBuilder<T> useAResultLimitOf(int limit){
		mapBuilder.setMaxResults(limit);
		return ListBuilder.this;
	}
	
	/**A fluid API for specifying the selection criteria
	 * @author kwakutwumasi-afriyie
	 *
	 */
	public class ParameterBuilder{
		String parameter;

		ParameterBuilder(String parameter) {
			this.parameter = parameter;
		}
		
		/**Select objects that have values equal to the specified value
		 * @param value a serializable value to compare
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> withAValueEqualTo(Serializable value){
			mapBuilder.add(parameter, value);
			return ListBuilder.this;
		}
		
		/**Select objects that have string values like the specified value
		 * @param value the String to match
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> withAValueLike(String value){
			mapBuilder.addVariableString(parameter, value);
			return ListBuilder.this;
		}
		
		/**Entry method for the fluid API for specifying a range of values
		 * @return the @{link RangeBuilder}
		 */
		public RangeBuilder withValues(){
			return new RangeBuilder(parameter);
		}
		
		/**A list of values either one of which to match
		 * @param parameters
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> withAValueEqualToOneOf(Serializable... parameters){
			mapBuilder.addChoices(parameter, parameters);
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
		
		RangeBuilder(String parameter) {
			this.parameter = parameter;
		}

		/**The entry method for the fluid API for specifying a range
		 * @param value
		 * @return This builder to chain methods
		 */
		public RangeBuilder between(Serializable value) {
			range = new Range();
			range.from(value);
			return this;
		}
		
		/**Terminal method for the fluid API for specifying a range
		 * @param value
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> and(Serializable value){
			if(range==null)
				throw new UnsupportedOperationException("Call between() before calling and()");
			range.to(value);
			mapBuilder.add(parameter, range);
			return ListBuilder.this;
		}
		
		/**The fluid API method for specifying a range beginning from the value
		 * @param value
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> startingFrom(Serializable value){
			mapBuilder.addRange(parameter, value, null);
			return ListBuilder.this;
		}

		/**The fluid API method for specifying a range ending at the value
		 * @param value
		 * @return The @{link ListBuilder} to chain methods
		 */
		public ListBuilder<T> upTo(Serializable value){
			mapBuilder.addRange(parameter, null, value);
			return ListBuilder.this;
		}
	}
}
