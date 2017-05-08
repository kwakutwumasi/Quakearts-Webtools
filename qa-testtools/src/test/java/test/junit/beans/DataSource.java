/*******************************************************************************
* Copyright (C) 2016 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
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
