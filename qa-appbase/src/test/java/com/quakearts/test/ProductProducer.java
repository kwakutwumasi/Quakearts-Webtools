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
