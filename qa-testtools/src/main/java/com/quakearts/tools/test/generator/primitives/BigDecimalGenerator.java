/*******************************************************************************
 * Copyright (C) 2017 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.tools.test.generator.primitives;

import java.math.BigDecimal;

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates(BigDecimal.class)
public final class BigDecimalGenerator extends DoubleNumberGenerator<BigDecimal> {

	@Override
	public BigDecimal generateRandom() {
		return new BigDecimal(generateRandomDouble());
	}
	
	protected double getMinDouble(){
		return min != null? min.doubleValue():0;
	}

	protected double getMaxDouble(){
		return max != null? max.doubleValue():0;
	}
	
	@Override
	protected BigDecimal getFromString(String string) {
		return new BigDecimal(string);
	}
	
	@Override
	protected BigDecimal getFromDouble(Double number) {
		return new BigDecimal(number);
	}
}
