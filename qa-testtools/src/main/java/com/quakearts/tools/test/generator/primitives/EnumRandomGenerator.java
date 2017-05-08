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

import com.quakearts.tools.test.generator.GeneratorBase;
import com.quakearts.tools.test.generator.exception.GeneratorException;

public class EnumRandomGenerator<T extends Enum<T>> extends GeneratorBase<Enum<T>> {

	private Class<T> enumClass;
	
	public EnumRandomGenerator(Class<T> enumClazz) {
		if(!enumClazz.isEnum()
				|| enumClazz.getEnumConstants()==null 
				|| enumClazz.getEnumConstants().length==0)
			throw new GeneratorException("Unable to generate enums for class: "+enumClass.getName()
					+(!enumClazz.isEnum()?". Class is not an Enum":". Class does not have any enum constants."));
		
		this.enumClass = enumClazz;
	}
	
	@Override
	public T generateRandom() {
		T[] constants = enumClass.getEnumConstants();
		return constants[random.nextInt(constants.length)];
	}

}
