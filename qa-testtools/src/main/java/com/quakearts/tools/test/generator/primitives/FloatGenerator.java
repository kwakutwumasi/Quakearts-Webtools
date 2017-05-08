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

@Generates({Float.class, float.class})
public final class FloatGenerator extends NumberGenerator<Float> {

	@Override
	public Float generateRandom() {
		Float number = random.nextFloat();
		if(max==null && min==null){
			return random.nextFloat();
		} else if(max==null){
			if(min < 0)
				number *= -1;

			number = (number * min) + min;
			
			return number;
		} else if (min==null){
			if(max < 0)
				number *= -1;

			number = number * max;
			
			return number;
		} else {
			float difference = max - min;
			number *= difference;
			number += min;
			
			if(max < 0)
				number *= -1;
			
			return number;
		}
	}

	@Override
	protected Float getFromString(String string) {
		return Float.valueOf(string);
	}

	@Override
	protected boolean isValid(Float max, Float min) {
		return max.floatValue() > min.floatValue();
	}
	
	@Override
	protected Float getFromDouble(Double number) {
		return number.floatValue();
	}
}
