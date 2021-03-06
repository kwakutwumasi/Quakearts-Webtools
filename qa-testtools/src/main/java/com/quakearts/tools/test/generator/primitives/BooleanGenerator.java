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
import com.quakearts.tools.test.generator.annotation.Generates;

@Generates({boolean.class, Boolean.class})
public class BooleanGenerator extends GeneratorBase<Boolean> {

	@Override
	public Boolean generateRandom() {
		return random.nextInt(2)==1?Boolean.TRUE:Boolean.FALSE;
	}

}
