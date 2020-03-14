package com.quakearts.webapp.orm.query.criteria;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import com.quakearts.webapp.orm.query.QueryOrder;

public class CriteriaMap extends HashMap<String, Serializable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2817823404998907549L;
	public static final String DISJUNCTION = "com.quakearts.orm.searchparams.DISJUNCTION";
	public static final String CONJUNCTION = "com.quakearts.orm.searchparams.CONJUNCTION";
	public static final String MAXRESULTS = "com.quakearts.orm.searchparams.MAXRESULTS";
	
	private QueryOrder[] order = new QueryOrder[0];
	public void orderBy(QueryOrder... order) {
		this.order = order;
	}
	
	public QueryOrder[] getOrder() {
		return order;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(order);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CriteriaMap other = (CriteriaMap) obj;
		return Arrays.equals(order, other.order);
	}

}
