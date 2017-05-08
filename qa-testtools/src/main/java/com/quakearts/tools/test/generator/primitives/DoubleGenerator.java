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

import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({Double.class, double.class})
public final class DoubleGenerator extends DoubleNumberGenerator<Double> {

	@Override
	public Double generateRandom() {
		return generateRandomDouble();
	}

	@Override
	protected double getMinDouble() {
		return min!=null?min:0;
	}

	@Override
	protected double getMaxDouble() {
		return max!=null?max:0;
	}

	@Override
	protected Double getFromString(String string) {
		return Double.valueOf(string);
	}
	
	@Override
	protected Double getFromDouble(Double number) {
		return number;
	}
}
