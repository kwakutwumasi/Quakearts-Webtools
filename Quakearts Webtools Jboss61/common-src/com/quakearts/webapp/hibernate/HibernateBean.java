package com.quakearts.webapp.hibernate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.quakearts.webapp.facelets.util.UtilityMethods;

public class HibernateBean {

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
	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T> findObjects(Class<T> clazz,Map<String, Serializable> parameters, Session session) throws HibernateException, IOException{
		Criteria query = session.createCriteria(clazz);
		for(Entry<String, Serializable> entry:parameters.entrySet()){
			if(entry.getValue() instanceof Choice) {
				Choice choice = (Choice)entry.getValue();
				if(choice.choices.size()==0)
					continue;
				
				if(choice.choices.size()==1)
					query.add(Restrictions.eq(entry.getKey(), choice.choices.get(1)));
				else {
					Criterion criterion = Restrictions.or(getCriterion(entry.getKey(), choice.choices.get(0)), 
							getCriterion(entry.getKey(), choice.choices.get(1)));
					for(int i=2; i<choice.choices.size()-1;i++){
						criterion=Restrictions.or(criterion, getCriterion(entry.getKey(), choice.choices.get(i)));
					}
				}
			} else {
				query.add(getCriterion(entry.getKey(), entry.getValue()));
			}
		}
		
		return (List<T>) query.list();
	}
	
	private Criterion getCriterion(String key, Serializable value){
		if(value instanceof Range){
			Range range = (Range) value;
			return Restrictions.between(key, range.from, range.to);
		} else if(value instanceof String) {
			return Restrictions.ilike(key, value);
		} else {
			return Restrictions.eq(key, value);
		}
	}
	
	protected UserTransaction beginTransaction() throws NamingException, SystemException, NotSupportedException{
		UserTransaction transaction = UtilityMethods.getTransaction();
		if(transaction.getStatus()==Status.STATUS_NO_TRANSACTION){
			transaction.begin();
			return transaction;
		}
		return null;
	}
	
	
	protected void endTransaction(UserTransaction transaction){
		try {
			if(transaction.getStatus()==Status.STATUS_MARKED_ROLLBACK)
				transaction.rollback();
			else
				if(transaction.getStatus()==Status.STATUS_ACTIVE){
					transaction.begin();
				}
		} catch (SystemException | NotSupportedException e) {
			
		}
	}

}
