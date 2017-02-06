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

public abstract class HibernateBean {

	public static class Range implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -7151788060480527063L;
		private Serializable from, to;

		public Serializable getFrom() {
			return from;
		}

		public void setFrom(Serializable from) {
			this.from = from;
		}

		public Serializable getTo() {
			return to;
		}

		public void setTo(Serializable to) {
			this.to = to;
		}
		
		public Range from(Serializable from){
			this.from = from;
			return this;
		}
		
		public Range to(Serializable to){
			this.to = to;
			return this;
		}
		
		public boolean isEmpty(){
			return from == null || to == null;
		}
	}
	
	public static class Choice implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 388266923206203510L;
		private List<Serializable> choices = new ArrayList<>();
		
		public List<Serializable> getChoices() {
			return choices;
		}
		
		public Choice or(Serializable value){
			choices.add(value);
			return this;
		}
		
		public boolean isEmpty(){
			return choices.isEmpty();
		}
	}
	
	public static class VariableString implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 5170445172572016710L;
		private String value;

		public VariableString(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	
	public static class QueryOrder implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7468698834542995985L;
		private String property;
		private boolean ascending;
		
		public QueryOrder(String property, boolean ascending) {
			this.property = property;
			this.ascending = ascending;
		}

		public String getProperty() {
			return property;
		}
		
		public void setProperty(String property) {
			this.property = property;
		}
		
		public boolean isAscending() {
			return ascending;
		}
		
		public void setAscending(boolean ascending) {
			this.ascending = ascending;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findObjects(Class<T> clazz, Map<String, Serializable> parameters, Session session, QueryOrder... orders) throws HibernateException{
		Criteria query = session.createCriteria(clazz);
		for(Entry<String, Serializable> entry:parameters.entrySet()){
			if(entry.getValue() instanceof Choice) {
				Choice choice = (Choice)entry.getValue();
				if(choice.choices.size()==0)
					continue;
				
				if(choice.choices.size()==1)
					query.add(getCriterion(entry.getKey(), choice.choices.get(0)));
				else if(choice.choices.size()==2)
					query.add(Restrictions.or(getCriterion(entry.getKey(), choice.choices.get(0)),
							getCriterion(entry.getKey(), choice.choices.get(0))
						));
				else {
					Criterion[] criterion = new Criterion[choice.choices.size()];
					for(int i=0; i<choice.choices.size();i++){
						criterion[i] = getCriterion(entry.getKey(), choice.choices.get(i));
					}
					query.add(Restrictions.or(criterion));
				}
			} else {
				query.add(getCriterion(entry.getKey(), entry.getValue()));
			}
		}
		
		if(orders!=null){
			for(QueryOrder order:orders){
				if(order.ascending)
					query.addOrder(Order.asc(order.property));
				else
					query.addOrder(Order.desc(order.property));
			}
		}
		
		return (List<T>) query.list();
	}
	
	private Criterion getCriterion(String key, Serializable value){
		if(value instanceof Range){
			Range range = (Range) value;
			if(range.from!=null && range.to==null)
				return Restrictions.gt(key, ((Range)value).from);
			else if(range.from==null && range.to!=null)
				return Restrictions.lt(key, ((Range)value).to);
			
			return Restrictions.between(key, range.from, range.to);
		} else if(value instanceof VariableString) {
			return Restrictions.ilike(key, ((VariableString)value).value);
		} else {
			return Restrictions.eq(key, value);
		}
	}

}
