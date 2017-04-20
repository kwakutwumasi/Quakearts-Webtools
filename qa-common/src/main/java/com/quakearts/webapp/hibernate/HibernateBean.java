package com.quakearts.webapp.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		handleParameters(parameters, context);
		if (orders != null) {
			for (QueryOrder order : orders) {
				if (order.isAscending())
					query.addOrder(Order.asc(order.getProperty()));
				else
					query.addOrder(Order.desc(order.getProperty()));
			}
		}

		return (List<T>) query.list();
	}

	private void handleParameters(Map<String, Serializable> parameters, QueryContext context) {
		for (Entry<String, Serializable> entry : parameters.entrySet()) {
			if (entry.getKey() == ParameterMapBuilder.MAXRESULTS) {
				context.getQuery().setMaxResults((int) entry.getValue());
			} else {
				populateQuery(entry, context);
			}
		}
	}

	private void populateQuery(Entry<String, Serializable> entry, QueryContext context) {
		String key = entry.getKey();
		if (key != ParameterMapBuilder.CONJUNCTION 
				&& key != ParameterMapBuilder.DISJUNCTION
				&& key.indexOf(".") != -1) {
			String[] aliases = key.split("\\.");
			Criteria query = context.getQuery();
			for (int count = 0; count < aliases.length - 1; count++) {
				query = query.createCriteria(aliases[count]);
			}
			key = aliases[aliases.length - 1];
			context.getSubCriteriaMap().put(entry.getKey(), query);
		}

		Criterion criterion = handleParameter(key, entry.getValue());
		//query.add(criterion);
	}

	@SuppressWarnings("unchecked")
	private Criterion handleParameter(String key, Serializable value) {
		Criterion criterion;
		if (key == ParameterMapBuilder.CONJUNCTION && value instanceof Map) {
			Map<String, Serializable> conjoinedParameters = (Map<String, Serializable>) value;
			criterion = handleJunction(conjoinedParameters, ParameterMapBuilder.CONJUNCTION);
		} else if (key == ParameterMapBuilder.DISJUNCTION && value instanceof Map) {
			Map<String, Serializable> disjoinedParameters = (Map<String, Serializable>) value;
			criterion = handleJunction(disjoinedParameters, ParameterMapBuilder.DISJUNCTION);
		} else if (value instanceof Choice) {

			Choice choice = (Choice) value;
			if (choice.getChoices().size() == 0)
				return Restrictions.isNull(key);

			if (choice.getChoices().size() == 1)
				criterion = createCriterion(key, choice.getChoices().get(0));
			else if (choice.getChoices().size() == 2)
				criterion = Restrictions.or(createCriterion(key, choice.getChoices().get(0)),
						createCriterion(key, choice.getChoices().get(1)));
			else {
				Criterion[] choiceCriteria = new Criterion[choice.getChoices().size()];
				for (int i = 0; i < choice.getChoices().size(); i++) {
					choiceCriteria[i] = createCriterion(key, choice.getChoices().get(i));
				}
				criterion = Restrictions.or(choiceCriteria);
			}
		} else {
			criterion = createCriterion(key, value);
		}

		return criterion;
	}

	private Criterion handleJunction(Map<String, Serializable> junctionParameters, String type) {
		ArrayList<Criterion> criteria = null;
		criteria = new ArrayList<>();

		for (Entry<String, Serializable> entry : junctionParameters.entrySet()) {
			criteria.add(handleParameter(entry.getKey(), entry.getValue()));
		}

		Criterion[] criteriaArray = criteria.toArray(new Criterion[criteria.size()]);
		if (type == ParameterMapBuilder.CONJUNCTION)
			return (Restrictions.and(criteriaArray));
		else if (type == ParameterMapBuilder.DISJUNCTION)
			return (Restrictions.or(criteriaArray));
		else
			throw new HibernateException("Invalid conjuction type:" + type);
	}

	private Criterion createCriterion(String key, Serializable value) {
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
	private HashMap<String, Criteria> subCriteriaMap = new HashMap<>();

	public QueryContext(Criteria query) {
		this.query = query;
	}

	public Criteria getQuery() {
		return query;
	}

	public HashMap<String, Criteria> getSubCriteriaMap() {
		return subCriteriaMap;
	}

}
