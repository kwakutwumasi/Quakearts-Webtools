package com.quakearts.test.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Brand implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1955785003764918174L;
	@Id
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
