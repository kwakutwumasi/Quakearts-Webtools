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
package com.quakearts.tools.test.generator.bean;

import java.util.Collection;
import java.util.Collections;

import com.quakearts.tools.test.generator.Generator;

/**The default generator for types that cannot be auto generated
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public class NoObjectGenerator<T> implements Generator<T> {

	@Override
	public T generateRandom() {
		return null;
	}

	@Override
	public Collection<T> generateRandom(int size) {
		return Collections.emptyList();
	}

	@Override
	public Generator<T> useField(String fieldName) {
		return this;
	}

	@Override
	public Generator<?> useGeneratorProperty(String fieldName) {
		return null;
	}
}
