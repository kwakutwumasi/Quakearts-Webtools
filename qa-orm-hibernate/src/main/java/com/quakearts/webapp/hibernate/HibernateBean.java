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
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.quakearts.webapp.orm.query.Choice;
import com.quakearts.webapp.orm.query.QueryOrder;
import com.quakearts.webapp.orm.query.Range;
import com.quakearts.webapp.orm.query.VariableString;
import com.quakearts.webapp.orm.query.helper.ParameterMapBuilder;

public abstract class HibernateBean {

	@SuppressWarnings("unchecked")
	protected <T> List<T> findObjects(Class<T> clazz, Map<String, Serializable> parameters, Session session,
			QueryOrder... orders) throws HibernateException {
		Criteria query = session.createCriteria(clazz);
		QueryContext context = new QueryContext(query);
		if(parameters != null)
			handleParameters(parameters, context);
		
		if (orders != null) {
			for (QueryOrder order : orders) {
				if (order.isAscending())
					query.addOrder(Order.asc(order.getProperty()));
				else
					query.addOrder(Order.desc(order.getProperty()));
			}
		}

		return (List<T>) context.getQuery().list();
	}

	private void handleParameters(Map<String, Serializable> parameters, QueryContext context) {
		for (Entry<String, Serializable> entry : parameters.entrySet()) {
			if (entry.getKey() == ParameterMapBuilder.MAXRESULTS) {
				context.getQuery().setMaxResults((int) entry.getValue());
			} else {
				context.getQuery().add(handleParameter(entry.getKey(), entry.getValue(), context));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Criterion handleParameter(String key, Serializable value, QueryContext context) {
		Criterion criterion;
		if (key == ParameterMapBuilder.CONJUNCTION && value instanceof Map) {
			Map<String, Serializable> conjoinedParameters = (Map<String, Serializable>) value;
			criterion = handleJunction(conjoinedParameters, ParameterMapBuilder.CONJUNCTION, context);
		} else if (key == ParameterMapBuilder.DISJUNCTION && value instanceof Map) {
			Map<String, Serializable> disjoinedParameters = (Map<String, Serializable>) value;
			criterion = handleJunction(disjoinedParameters, ParameterMapBuilder.DISJUNCTION, context);
		} else if (value instanceof Choice) {
			Choice choice = (Choice) value;
			if (choice.getChoices().size() == 0)
				throw new HibernateException("Invalid choice list for parameter"+key
						+". Choice list must be greater than zero.");

			if (choice.getChoices().size() == 1)
				criterion = createCriterion(key, choice.getChoices().get(0), context);
			else if (choice.getChoices().size() == 2)
				criterion = Restrictions.or(createCriterion(key, choice.getChoices().get(0), context),
						createCriterion(key, choice.getChoices().get(1), context));
			else {
				Criterion[] choiceCriteria = new Criterion[choice.getChoices().size()];
				for (int i = 0; i < choice.getChoices().size(); i++) {
					choiceCriteria[i] = createCriterion(key, choice.getChoices().get(i), context);
				}
				criterion = Restrictions.or(choiceCriteria);
			}
		} else {
			criterion = createCriterion(key, value, context);
		}

		return criterion;
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
	
	private String handleKey(String key, QueryContext context){
		if(key.indexOf(".")!=-1){
			String[] keyParts = key.split("\\.");			
			if(!context.getKeys().contains(key)){
				StringBuilder associationPathBuilder = new StringBuilder(keyParts[0]);
				for(int index=1;index<keyParts.length-1; index++){
					associationPathBuilder.append(".").append(keyParts[index]);
				}
				context.getQuery().createCriteria(associationPathBuilder.toString(), 
						keyParts[keyParts.length-2]);
				context.getKeys().add(key);
			}
			
			return keyParts[keyParts.length-2]+"."+keyParts[keyParts.length-1];
		} else {
			return key;
		}
	}

	private Criterion handleJunction(Map<String, Serializable> junctionParameters, String type, QueryContext context) {
		ArrayList<Criterion> criteria = null;
		criteria = new ArrayList<>();

		for (Entry<String, Serializable> entry : junctionParameters.entrySet()) {
			criteria.add(handleParameter(entry.getKey(), entry.getValue(), context));
		}

		Criterion[] criteriaArray = criteria.toArray(new Criterion[criteria.size()]);
		if (type == ParameterMapBuilder.CONJUNCTION)
			return (Restrictions.and(criteriaArray));
		else if (type == ParameterMapBuilder.DISJUNCTION)
			return (Restrictions.or(criteriaArray));
		else
			throw new HibernateException("Invalid conjuction type:" + type);
	}

	private Criterion createCriterion(String key, Serializable value, QueryContext context) {
		key = handleKey(key, context);
		if (value instanceof Range) {
			Range range = (Range) value;
			if (range.getFrom() != null && range.getTo() == null)
				return Restrictions.gt(key, ((Range) value).getFrom());
			else if (range.getFrom() == null && range.getTo() != null)
				return Restrictions.lt(key, ((Range) value).getTo());

			return Restrictions.between(key, range.getFrom(), range.getTo());
		} else if (value instanceof VariableString) {
			return Restrictions.ilike(key, ((VariableString) value).getValue());
		} else {
			return Restrictions.eq(key, value);
		}
	}
}

class QueryContext {
	private Criteria query;
	private Set<String> keys = new HashSet<>();
	
	public QueryContext(Criteria query) {
		this.query = query;
	}

	public Criteria getQuery() {
		return query;
	}

	public Set<String> getKeys() {
		return keys;
	}
}
