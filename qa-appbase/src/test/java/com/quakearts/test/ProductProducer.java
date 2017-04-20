package com.quakearts.test;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.CDI;

public class ProductProducer implements Producer {

	/* (non-Javadoc)
	 * @see com.quakearts.test.Producer#getTestProduct()
	 */
	@Override
	@Produces
	public @Product TestProduct getTestProduct(){
		TestInject inject = CDI.current().select(TestInject.class).get();
		TestProductImpl product = new TestProductImpl();
		product.setTestInject(inject);
		
		return product;
	}
}
