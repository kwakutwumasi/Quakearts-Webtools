package com.quakearts.webapp.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
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
	protected <T> List<T> findObjects(Class<T> clazz, Map<String, Serializable> parameters, Session session, QueryOrder... orders) throws HibernateException{
		Criteria query = session.createCriteria(clazz);
		handleParameters(parameters, query);
		if(orders!=null){
			for(QueryOrder order:orders){
				if(order.isAscending())
					query.addOrder(Order.asc(order.getProperty()));
				else
					query.addOrder(Order.desc(order.getProperty()));
			}
		}
		
		return (List<T>) query.list();
	}
	
	private void handleParameters(Map<String, Serializable> parameters, Criteria query){
		Criterion criterion;
		
		for(Entry<String, Serializable> entry:parameters.entrySet()){
			criterion = handleParameters(entry);
			query.add(criterion);
		}		
	}
	
	@SuppressWarnings("unchecked")
	private Criterion handleParameters(Entry<String, Serializable> entry) {
		Criterion criterion;
		if(entry.getKey() == ParameterMapBuilder.CONJUNCTION && entry.getValue() instanceof Map){
			Map<String, Serializable> conjoinedParameters = (Map<String, Serializable>) entry.getValue();
			criterion = handleParameters(conjoinedParameters, ParameterMapBuilder.CONJUNCTION);
		} else if(entry.getKey() == ParameterMapBuilder.DISJUNCTION && entry.getValue() instanceof Map){
			Map<String, Serializable> disjoinedParameters = (Map<String, Serializable>) entry.getValue();
			criterion = handleParameters(disjoinedParameters, ParameterMapBuilder.DISJUNCTION);		
		} else if(entry.getValue() instanceof Choice) {
			Choice choice = (Choice)entry.getValue();
			if(choice.getChoices().size()==0)
				return Restrictions.isNull(entry.getKey());
			
			if(choice.getChoices().size()==1)
				criterion = getCriterion(entry.getKey(), choice.getChoices().get(0));
			else if(choice.getChoices().size()==2)
				criterion = Restrictions.or(getCriterion(entry.getKey(), choice.getChoices().get(0)),
						getCriterion(entry.getKey(), choice.getChoices().get(1))
					);
			else {
				Criterion[] choiceCriteria = new Criterion[choice.getChoices().size()];
				for(int i=0; i<choice.getChoices().size();i++){
					choiceCriteria[i] = getCriterion(entry.getKey(), choice.getChoices().get(i));
				}
				criterion = Restrictions.or(choiceCriteria);
			}
		} else {
			criterion = getCriterion(entry.getKey(), entry.getValue());
		}
		
		return criterion;
	}

	private Criterion handleParameters(Map<String, Serializable> conjoinedParameters, String type) {
		ArrayList<Criterion> criteria = null;
		criteria = new ArrayList<>();
		
		for(Entry<String, Serializable> entry:conjoinedParameters.entrySet()){
			criteria.add(handleParameters(entry));
		}
		
		Criterion[] criteriaArray = criteria.toArray(new Criterion[criteria.size()]);
		if(type == ParameterMapBuilder.CONJUNCTION) 
			return (Restrictions.and(criteriaArray));
		else if(type == ParameterMapBuilder.DISJUNCTION)
			return (Restrictions.or(criteriaArray));
		else
			throw new HibernateException("Invalid conjuction type:"+type);
	}

	private Criterion getCriterion(String key, Serializable value){
		if(value instanceof Range){
			Range range = (Range) value;
			if(range.getFrom()!=null && range.getTo()==null)
				return Restrictions.gt(key, ((Range)value).getFrom());
			else if(range.getFrom()==null && range.getTo()!=null)
				return Restrictions.lt(key, ((Range)value).getTo());
			
			return Restrictions.between(key, range.getFrom(), range.getTo());
		} else if(value instanceof VariableString) {
			return Restrictions.ilike(key, ((VariableString)value).getValue());
		} else {
			return Restrictions.eq(key, value);
		}
	}

}
