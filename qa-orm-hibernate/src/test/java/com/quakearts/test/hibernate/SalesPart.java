package com.quakearts.test.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SalesPart {
	@Id
	private int id;
	@ManyToOne
	private SalesOrder salesOrder;
	@ManyToOne
	private Product product;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
