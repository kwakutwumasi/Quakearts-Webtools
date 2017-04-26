package com.quakearts.test.hibernate;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SalesOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4512580527056224635L;
	@Id
	private int id;
	private Date createDate = new Date();
	private boolean fulfilled;
	@OneToMany
	private Set<SalesPart> parts = new HashSet<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isFulfilled() {
		return fulfilled;
	}

	public void setFulfilled(boolean fulfilled) {
		this.fulfilled = fulfilled;
	}

}
