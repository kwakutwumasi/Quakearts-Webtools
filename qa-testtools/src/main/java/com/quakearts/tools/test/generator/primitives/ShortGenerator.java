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

@Generates({Short.class, short.class})
public final class ShortGenerator extends IntegerNumberGenerator<Short> {

	@Override
	public Short generateRandom() {
		return (short) generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min != null? min.shortValue(): 0;
	}

	@Override
	protected int getMaxInt() {
		return max != null? max.shortValue(): 0;
	}

	@Override
	protected Short getFromString(String string) {
		return Short.valueOf(string);
	}
	
	@Override
	protected Short getFromDouble(Double number) {
		return number.shortValue();
	}
}
