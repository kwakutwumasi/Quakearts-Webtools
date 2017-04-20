package test.junit.beans;

import java.util.LinkedList;
import java.util.List;

import com.quakearts.tools.test.generator.annotation.CollectionType;
import com.quakearts.tools.test.generator.annotation.Size;

public class DataSource {
	private List<Entity> Entities;
	private List<Customer> customers = new LinkedList<>();

	@CollectionType(Entity.class)
	@Size(5)
	public List<Entity> getEntities() {
		return Entities;
	}

	public void setEntities(List<Entity> Entitys) {
		this.Entities = Entitys;
	}

	@CollectionType(Customer.class)
	@Size(5)
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

}
