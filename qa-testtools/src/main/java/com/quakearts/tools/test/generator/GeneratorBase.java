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
package com.quakearts.tools.test.generator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**Base class for bean/property generation. It provides the basic fields required by a generator
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public abstract class GeneratorBase<T> implements Generator<T> {
	protected final static Random random = new SecureRandom();
	protected String fieldName;
	protected Stack<GeneratorBase<T>> callStack = new Stack<>();
	private int maxCallDepth = 2;
	
	/**Return the maximum depth of a bean tree the generator will go when generating
	 * random data for non-primitive and not standard Java types (String, Collections, Maps, etc)
	 * @return
	 */
	protected int getMaxCallDepth() {
		return maxCallDepth;
	}
	
	@Override
	@Deprecated
	public Generator<T> useField(String fieldName) {
		return useGeneratorProperty(fieldName);
	}
	
	@Override
	public Generator<T> useGeneratorProperty(String property) {
		this.fieldName = property;
		return this;
	}
	
	/**The maximum depth to go down a bean tree when generating random values
	 * @param maxCallDepth
	 * @return this object for method chaining
	 */
	public GeneratorBase<T> useMaxCallDepth(int maxCallDepth) {
		this.maxCallDepth = maxCallDepth;
		return this;
	}
	
	@Override
	public final Collection<T> generateRandom(final int size) {
		if(callStack.size()>getMaxCallDepth())//Prevent too many recursive calls to the singleton generator
			return Collections.emptyList();

		callStack.push(this);
		ArrayList<T> arrayList = new ArrayList<>(size);
		for(int count = 0; count< size; count++){
			T generated = generateRandom();
			if(generated==null)
				break;
			
			arrayList.add(generated);
		}
		callStack.pop();
		return arrayList;
	}
}
