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
package com.quakearts.syshub.core.utils;

import java.io.IOException;
import java.io.Serializable;

/**Interface for classes that handle serialization
 * @author kwakutwumasi-afriyie
 *
 */
public interface Serializer {

	/**Convert a {@linkplain Serializable} object to a byte array.
	 * @param object the object to serialize
	 * @return the byte array
	 */
	byte[] toByteArray(Serializable object);

	/**Conver the byte array to an object
	 * @param bytes the bytes to convert
	 * @return the object represented by these bytes
	 * @throws IOException if there was a problem reading the byte array into the stream
	 * @throws ClassNotFoundException if the class represented by the bytes is not on the classpath
	 */
	Object toObject(byte[] bytes) throws IOException, ClassNotFoundException;

}