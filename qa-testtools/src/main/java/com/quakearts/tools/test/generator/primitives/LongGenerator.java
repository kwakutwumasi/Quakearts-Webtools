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

@Generates({Long.class, long.class})
public final class LongGenerator extends IntegerNumberGenerator<Long> {

	@Override
	public Long generateRandom() {
		return (long) generateRandomInteger();
	}

	@Override
	protected int getMinInt() {
		return min!=null?min.intValue():0;
	}

	@Override
	protected int getMaxInt() {
		return max!=null?max.intValue():0;
	}
	
	@Override
	protected Long getFromString(String string) {
		return Long.valueOf(string);
	}
	
	@Override
	protected Long getFromDouble(Double number) {
		return number.longValue();
	}
}
