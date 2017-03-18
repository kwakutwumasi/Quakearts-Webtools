package com.quakearts.webapp.hibernate;

import java.io.Serializable;
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

public abstract class HibernateBean {

	@SuppressWarnings("unchecked")
	protected <T> List<T> findObjects(Class<T> clazz, Map<String, Serializable> parameters, Session session, QueryOrder... orders) throws HibernateException{
		Criteria query = session.createCriteria(clazz);
		for(Entry<String, Serializable> entry:parameters.entrySet()){
			if(entry.getValue() instanceof Choice) {
				Choice choice = (Choice)entry.getValue();
				if(choice.getChoices().size()==0)
					continue;
				
				if(choice.getChoices().size()==1)
					query.add(getCriterion(entry.getKey(), choice.getChoices().get(0)));
				else if(choice.getChoices().size()==2)
					query.add(Restrictions.or(getCriterion(entry.getKey(), choice.getChoices().get(0)),
							getCriterion(entry.getKey(), choice.getChoices().get(0))
						));
				else {
					Criterion[] criterion = new Criterion[choice.getChoices().size()];
					for(int i=0; i<choice.getChoices().size();i++){
						criterion[i] = getCriterion(entry.getKey(), choice.getChoices().get(i));
					}
					query.add(Restrictions.or(criterion));
				}
			} else {
				query.add(getCriterion(entry.getKey(), entry.getValue()));
			}
		}
		
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
