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
package com.quakearts.webapp.orm.query.criteria;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.Not;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;

/**A fluid API for defining persistent store selection criteria
 * @author kwakutwumasi-afriyie
 *
 */
public class CriteriaMapBuilder {
	private CriteriaMap criteriaMap;
	private Deque<CriteriaMap> stack = new ArrayDeque<>();
	private ParameterBuilder builder;

	/**Initial method in the build chain. Create a {@link CriteriaMapBuilder}
	 * @return a {@link CriteriaMapBuilder}
	 */
	public static CriteriaMapBuilder createCriteria(){
		return new CriteriaMapBuilder();
	}

	private CriteriaMapBuilder() {
		criteriaMap = new CriteriaMap();
	}
	
	/**Convenience method to express the intent of the criteria that follows
	 * It has no effect.
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder requireThat(){
		return this;
	}
	
	/**Convenience method to express the intent of the criteria that follows
	 * It has no effect.
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder require(){
		return this;
	}
	
	/**Convenience method to express the intent of the criteria that follows. 
	 * It has no effect.
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder and(){
		return this;
	}
	
	/**Convenience method to express the intent of the criteria that follows.
	 * It has no effect.
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder or(){
		return this;
	}
	
	/**Specify that any one of the following criteria can be matched.
	 * Note that until {@link #closeSet()} is called, any of the criteria following this call
	 * can be true for an entity to be added to the result list. This includes criteria that follows
	 * {@link #requireAllOfTheFollowing()}. In such instances the expression:
	 * <br />
	 * dataStore.find(Inventory.class).using(theFollowingCriteria()<br />
	 * .requireAnyOfTheFollowing()<br />
	 *	.property("quantity").mustBeEqualTo(6)<br />
	 *	.and() <br />
	 *  .requireAllOfTheFollowing()<br />
	 *	.property("quantity").mustBeEqualTo(3)<br />
	 *	.property("product").mustBeEqualTo("A6")<br />
	 *	.closeSet()<br />
	 *	.finish())<br />
	 *	.thenList(); <br />
	 * <br />
	 * will be similar to saying<br />
	 * <br />
	 * 	find all Inventory entries with quantity = 6 OR (quantity = 3 AND product = "A6")
	 * 
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder requireAnyOfTheFollowing(){
		newParameters(CriteriaMap.DISJUNCTION);
		return this;
	}

	/**Specify that all of the following criteria must be matched.
	 * Note that until {@link #closeSet()} is called, all of the criteria following this call
	 * must be true for an entity to be added to the result list. This includes criteria that follows
	 * {@link #requireAnyOfTheFollowing()}. In such instances the expression:
	 * <br />
	 * dataStore.find(Inventory.class).using(theFollowingCriteria()<br />
	 * .requireAllOfTheFollowing()<br />
	 *	.property("quantity").mustBeEqualTo(6)<br />
	 *	.and() <br />
	 *  .requireAnyOfTheFollowing()<br />
	 *	.property("quantity").mustBeEqualTo(3)<br />
	 *	.property("product").mustBeEqualTo("A6")<br />
	 *	.closeSet()<br />
	 *	.finish())<br />
	 *	.thenList(); <br />
	 * <br />
	 * will be similar to saying<br />
	 * <br />
	 * 	find all Inventory entries with quantity = 6 AND (quantity = 3 OR product = "A6")
	 * 
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder requireAllOfTheFollowing(){
		newParameters(CriteriaMap.CONJUNCTION);
		return this;
	}

	/**End any call to {@link #requireAllOfTheFollowing()} or {@link #requireAnyOfTheFollowing()}
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder closeSet(){
		if(!stack.isEmpty()){
			criteriaMap = stack.pop();
		}
		return this;	
	}

	/**Set the maximum number of items to return
	 * @param max
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder setMaxResultsAs(int max){
		CriteriaMap parametersToSet = getTopParameter();
		
		parametersToSet.put(CriteriaMap.MAXRESULTS, max);
		return this;
	}

	/**Specify the order in which the results will be ordered 
	 * @param order
	 * @return This builder for method chaining
	 */
	public CriteriaMapBuilder orderBy(QueryOrder... order) {
		CriteriaMap parametersToSet = getTopParameter();
		parametersToSet.orderBy(order);
		return this;
	}

	/**Terminal method. Return the CriteriaMap
	 * @return the {@link CriteriaMap}
	 */
	public CriteriaMap finish() {
		if(!stack.isEmpty()){
			criteriaMap = stack.getFirst();
			stack.clear();
		}
		
		return criteriaMap;
	}
	
	/**First method in call chain to add a criteria
	 * @param propertyName
	 * @return {@link ParameterBuilder} for method chaining
	 */
	public ParameterBuilder property(String propertyName){
		return getBuilder().property(propertyName);
	}
	
	/**
	 * @author kwaku
	 *
	 */
	public class ParameterBuilder {
		private String propertyName;
		private RangeBuilder rangeBuilder;
				
		private RangeBuilder getRangeBuilder() {
			if(rangeBuilder == null)
				rangeBuilder = new RangeBuilder();
			
			return rangeBuilder;
		}

		private ParameterBuilder property(String propertyName){
			this.propertyName = propertyName;
			return this;
		}
		
		/**Terminal call in the method chain. Creates a criteria that requires that the
		 * property be equal to the value specified
		 * @param value
		 * @return {@link CriteriaMapBuilder} for method chaining
		 */
		public CriteriaMapBuilder mustBeEqualTo(Serializable value){
			criteriaMap.put(propertyName, value);
			return CriteriaMapBuilder.this;
		}
		
		/**Terminal call in the method chain. Creates a criteria that requires that the
		 * property contains the value specified
		 * @param value
		 * @return {@link CriteriaMapBuilder} for method chaining
		 */
		public CriteriaMapBuilder mustBeLike(String value){
			return mustBeEqualTo(new VariableString(value));
		}
		
		/**Creates a criteria that requires that the property value be between 
		 * specified values. This is the first in a sub chain
		 * @param value
		 * @return {@link RangeBuilder} for method chaining
		 */
		public RangeBuilder mustBeBetween(Serializable from) {
			return getRangeBuilder().setFrom(from);
		}
		
		public class RangeBuilder {
			private Serializable from;
			
			private RangeBuilder setFrom(Serializable from) {
				this.from = from;
				return this;
			}

			/**Terminal call in the method chain. Creates a criteria that 
			 * requires that the property value be between specified values
			 * @param value
			 * @return {@link CriteriaMapBuilder} for method chaining
			 */
			public CriteriaMapBuilder and(Serializable to) {
				return mustBeEqualTo(new Range().from(from).to(to));
			}
		}
		
		/**Terminal call in the method chain. Creates a criteria that requires that the
		 * property value matches at least one of the values specified
		 * @param value
		 * @return {@link CriteriaMapBuilder} for method chaining
		 */
		public CriteriaMapBuilder mustBeEqualToOneOf(Serializable...values){
			Choice choice = new Choice();
			for(Serializable value:values){
				choice.or(value);
			}
			
			return mustBeEqualTo(choice);
		}
		
		/**Terminal call in the method chain. Creates a criteria that requires that the
		 * property value not match the value specified
		 * @param value
		 * @return {@link CriteriaMapBuilder} for method chaining
		 */
		public CriteriaMapBuilder mustNotBeEqualTo(Serializable value){
			return mustBeEqualTo(new Not(value));
		}
	}
	
	protected CriteriaMap getTopParameter() {
		CriteriaMap parametersToSet = this.criteriaMap;
		if(!stack.isEmpty()){
			parametersToSet = stack.getFirst();
		}
		return parametersToSet;
	}
	
	private ParameterBuilder getBuilder() {
		if(builder == null)
			builder = new ParameterBuilder();
		
		return builder;
	}

	private void newParameters(String type){
		CriteriaMap newparameters = new CriteriaMap();
		criteriaMap.put(type, newparameters);
		stack.push(criteriaMap);
		criteriaMap = newparameters;
	}
}
