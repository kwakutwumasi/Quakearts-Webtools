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
package com.quakearts.syshub.core;

import java.io.Closeable;

import com.quakearts.syshub.exception.ProcessingException;

/**An iterator that can be closed.
 * @author kwakutwumasi-afriyie
 *
 */
public interface CloseableIterator extends Closeable {
	/**Tests for more {@linkplain Result}s in this iterator
	 * @return true if the next call to next will return a {@linkplain Result}
	 */
	boolean hasNext();
	/**Get the next {@linkplain Result} in this iterator
	 * @return the {@linkplain Result}
	 * @throws ProcessingException if there is an error while reading the next result
	 */
	Result<?> next() throws ProcessingException;
}
