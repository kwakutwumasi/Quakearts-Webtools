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
package com.quakearts.webapp.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.Not;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.criteria.CriteriaMap;

public abstract class HibernateBean {

	protected <T> List<T> findObjects(Class<T> clazz, CriteriaMap parameters, Session session){
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> queryRoot = query.from(clazz);
		query.select(queryRoot);
		QueryContext<T> context = new QueryContext<>(builder, query, queryRoot);
		
		if(parameters != null){
			handleParameters(parameters, context);
			
			if(parameters.getOrder() != null && parameters.getOrder().length>0){
				List<Order> orderList = new ArrayList<>();
				for (QueryOrder order : parameters.getOrder()) {
					if (order.isAscending())
						orderList.add(builder.asc(handleKey(order.getProperty(), context)));
					else
						orderList.add(builder.desc(handleKey(order.getProperty(), context)));
				}
				query.orderBy(orderList);
			}
		}
		
		if(context.getMaxResults()>0)
			return session.createQuery(context.getQuery())
					.setMaxResults(context.getMaxResults()).getResultList();
		else
			return session.createQuery(context.getQuery()).getResultList();
	}

	private <T> void handleParameters(Map<String, Serializable> parameters, QueryContext<T> context) {
		List<Predicate> criterion = new ArrayList<>();
		for (Entry<String, Serializable> entry : parameters.entrySet()) {
			if (CriteriaMap.MAXRESULTS.equals(entry.getKey())) {
				context.setMaxResults((int) entry.getValue());
			} else {
				handleParameter(entry.getKey(), entry.getValue(), context, criterion);
			}
		}
		context.getQuery().where(criterion.toArray(new Predicate[criterion.size()]));
	}

	@SuppressWarnings("unchecked")
	private<T> void handleParameter(String key, Serializable value, QueryContext<T> context, List<Predicate> criterion) {
		if (CriteriaMap.CONJUNCTION.equals(key) && value instanceof Map) {
			Map<String, Serializable> conjoinedParameters = (Map<String, Serializable>) value;
			handleJunction(conjoinedParameters, CriteriaMap.CONJUNCTION, context, criterion);
		} else if (CriteriaMap.DISJUNCTION.equals(key) && value instanceof Map) {
			Map<String, Serializable> disjoinedParameters = (Map<String, Serializable>) value;
			handleJunction(disjoinedParameters, CriteriaMap.DISJUNCTION, context, criterion);
		} else {
			criterion.add(createCriterion(key, value, context));
		}
	}
	
	/*Algorithm:
	 * 1. check the key for .
	 * 2. if no return key (it is a root attribute)
	 * 3. if it has (it is a sub root attribute), check aliases already created:
	 * 		1. Search list
	 * 4. if found, get the keypart after last but one index of . and return as key
	 * 5. if no alias has been created : 
	 * 		1. create criteria by useing the key. use keypart after last index of . as the alias
	 * 		2. Return keypart after last but one index of . and return as key
	 * 
	 */
	
	@SuppressWarnings("rawtypes")
	private<T> Path handleKey(String key, QueryContext<T> context){
		if(key.indexOf('.')!=-1){
			String[] keyParts = key.split("\\.");

			Path path= context.getRoot().get(keyParts[0]);
			
			for(int index=1;index < keyParts.length; index++)
				path = path.get(keyParts[index]);
						
			return path;
		} else {
			return context.getRoot().get(key);
		}
	}

	private <T> void handleJunction(Map<String, Serializable> junctionParameters, String type, 
			QueryContext<T> context, List<Predicate> criterion) {
		ArrayList<Predicate> junctionCriterion = new ArrayList<>();

		for (Entry<String, Serializable> entry : junctionParameters.entrySet()) {
			handleParameter(entry.getKey(), entry.getValue(), context, junctionCriterion);
		}

		Predicate[] criteriaArray = junctionCriterion.toArray(new Predicate[junctionCriterion.size()]);
		if (CriteriaMap.CONJUNCTION.equals(type)) //Only recognize exact mapping
			criterion.add(context.getBuilder().and(criteriaArray));
		else
			criterion.add(context.getBuilder().or(criteriaArray));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private<T> Predicate createCriterion(String key, Serializable value, QueryContext<T> context) {
		Path path = handleKey(key, context);
		if (value instanceof Choice) {
			return handleChoice(key, value, context);
		} else if (value instanceof Not) {
			return context.getBuilder().not(createCriterion(key,((Not) value).getValue(), context));
		} else if (value instanceof Range) {
			return handleRange(path, value, context);
		} else if (value instanceof VariableString) {
			return context.getBuilder().like(path, ((VariableString) value).getValue());
		} else {
			return context.getBuilder().equal(path, value);
		}
	}

	protected <T> Predicate handleChoice(String key, Serializable value, QueryContext<T> context) {
		Choice choice = (Choice) value;
		if (choice.getChoices().isEmpty())
			throw new HibernateException("Invalid choice list for parameter "+key
					+". Choice list must be greater than zero.");

		if (choice.getChoices().size() == 1) {
			return createCriterion(key, choice.getChoices().get(0), context);
		} else if (choice.getChoices().size() == 2){
			return context.getBuilder().or(createCriterion(key, choice.getChoices().get(0), context),
					createCriterion(key, choice.getChoices().get(1), context));
		} else {
			Predicate[] choiceCriteria = new Predicate[choice.getChoices().size()];
			for (int i = 0; i < choice.getChoices().size(); i++) {
				choiceCriteria[i] = createCriterion(key, choice.getChoices().get(i), context);
			}
			return context.getBuilder().or(choiceCriteria);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> Predicate handleRange(Path path, Serializable value, QueryContext<T> context) {
		Range range = (Range) value;
		if (range.getFrom() != null && range.getTo() == null)
			return context.getBuilder().greaterThanOrEqualTo(path, 
					convert(((Range) value).getFrom()));
		else if (range.getFrom() == null && range.getTo() != null)
			return context.getBuilder().lessThanOrEqualTo(path, 
					convert(((Range) value).getTo()));

		return context.getBuilder().between(path, 
				convert(range.getFrom()), convert(range.getTo()));
	}

	@SuppressWarnings("unchecked")
	private<T> Comparable<T> convert(Serializable value) {
		return (Comparable<T>) value;
	}
}

class QueryContext<T> {
	private CriteriaBuilder builder;
	private CriteriaQuery<T> query;
	private Root<T> root;
	private Set<String> keys = new HashSet<>();
	private int maxResults;
	
	public QueryContext(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> queryRoot) {
		this.builder = builder;
		this.query = query;
		this.root = queryRoot;
	}

	public CriteriaBuilder getBuilder() {
		return builder;
	}
	
	public CriteriaQuery<T> getQuery() {
		return query;
	}
	
	public Root<T> getRoot() {
		return root;
	}

	public Set<String> getKeys() {
		return keys;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}
}
