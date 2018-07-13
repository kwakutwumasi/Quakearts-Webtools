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

import java.util.Collection;

/**Base interface for random value generators
 * @author kwakutwumasi-afriyie
 *
 * @param <T>
 */
public interface Generator<T> {

	/**Generate and return a random value of type T
	 * @return the random value
	 */
	T generateRandom();
	/**Generate and return a collection of random values of type T
	 * @param size the size of the {@linkplain Collection} to return
	 * @return a {@linkplain Collection}
	 */
	Collection<T> generateRandom(int size);
	/**Sets the name of the suffix of parameters in configuration file reserved for this generator
	 * @param fieldName the suffix appended to paramters
	 * @return this object to chain method calls
	 */
	@Deprecated
	Generator<?> useField(String fieldName);
	/**Sets the name of the suffix of parameters in configuration file reserved for this generator
	 * @param property the suffix appended to paramters
	 * @return this object to chain method calls
	 */
	Generator<?> useGeneratorProperty(String property);
}
