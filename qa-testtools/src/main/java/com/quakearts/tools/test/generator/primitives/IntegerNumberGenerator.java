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
package com.quakearts.tools.test.generator.primitives;

public abstract class IntegerNumberGenerator<T extends Number> extends NumberGenerator<T> {
	protected abstract int getMinInt();

	protected abstract int getMaxInt();

	protected int generateRandomInteger(){
		int number = random.nextInt();
		if(max==null && min==null){
			return random.nextInt();
		} else if(max==null){
			if(getMinInt()>=0)
				number = Math.abs(number);

			number = (number % getMinInt())+getMinInt();
			
			return number;
		} else if (min==null){
			if(getMaxInt()>=0)
				number = Math.abs(number);
			else if(getMaxInt()<0)
				number *=-1;

			number = number  % getMaxInt()+1;
			
			return number;
		} else {
			if(getMaxInt() == getMinInt())
				return getMaxInt();
			
			if(getMinInt()>=0)
				number = Math.abs(number);

			number = number % getMaxInt()+1;
			
			if(number < getMinInt())
				number= (number %(getMaxInt() - getMinInt())) + getMinInt();
				
			return number;
		}
	}

	@Override
	protected boolean isValid(T max, T min) {
		return max.intValue() > min.intValue();
	}
}
