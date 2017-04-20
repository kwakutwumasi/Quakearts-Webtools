package com.quakearts.test;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

@ApplicationScoped
public class TestMainBean {

	@Inject TestInject testInject;
	@Inject @Product TestProduct testProduct;
	@Inject Producer producer;
	
	public void init(){
		System.out.println(testInject.sayHello());
		System.out.println(testProduct.myStatus());

		System.out.println(testProduct.getTestInject()+":"+(testProduct.getTestInject()!=null?testProduct.getTestInject().sayHello():""));

		TestProduct product = producer.getTestProduct();
		System.out.println(product.getTestInject()+":"+(product.getTestInject()!=null?product.getTestInject().sayHello():""));
		product = producer.getTestProduct();
		System.out.println(product.getTestInject()+":"+(product.getTestInject()!=null?product.getTestInject().sayHello():""));
		product = producer.getTestProduct();
		System.out.println(product.getTestInject()+":"+(product.getTestInject()!=null?product.getTestInject().sayHello():""));
		
		product = CDI.current().select(TestProduct.class).get();
		System.out.println(product.getTestInject()+":"+(product.getTestInject()!=null?product.getTestInject().sayHello():""));
		
	}
	
	public TestInject getTestInject() {
		return testInject;
	}
	
	public TestProduct getTestProduct() {
		return testProduct;
	}
	
}
