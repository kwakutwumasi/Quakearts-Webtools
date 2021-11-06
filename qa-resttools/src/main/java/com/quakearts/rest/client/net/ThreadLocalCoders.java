/*******************************************************************************
* Copyright (C) 2021 Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com>.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Kwaku Twumasi-Afriyie <kwaku.twumasi@quakearts.com> - initial API and implementation
 ******************************************************************************/
package com.quakearts.rest.client.net;

import java.nio.charset.*;

public class ThreadLocalCoders {

	private static final int CACHE_SIZE = 3;
	private ThreadLocalCoders() {}
	private abstract static class Cache {

		private ThreadLocal<Object[]> cacheThreadLocal = new ThreadLocal<>();
		private final int size;

		Cache(int size) {
			this.size = size;
		}

		abstract Object create(Object name);

		private void moveToFront(Object[] oa, int i) {
			Object ob = oa[i];
			for (int j = i; j > 0; j--)
				oa[j] = oa[j - 1];
			oa[0] = ob;
		}

		abstract boolean hasName(Object ob, Object name);

		Object forName(Object name) {
			Object[] oa = cacheThreadLocal.get();
			if (oa == null) {
				oa = new Object[size];
				cacheThreadLocal.set(oa);
			} else {
				for (int i = 0; i < oa.length; i++) {
					Object ob = oa[i];
					if (ob == null)
						continue;
					if (hasName(ob, name)) {
						if (i > 0)
							moveToFront(oa, i);
						return ob;
					}
				}
			}

			Object ob = create(name);
			oa[oa.length - 1] = ob;
			moveToFront(oa, oa.length - 1);
			return ob;
		}

		void cleanUp() {
			cacheThreadLocal.remove();
		}
	}

	private static Cache encoderCache = new Cache(CACHE_SIZE) {
		boolean hasName(Object ob, Object name) {
			if (name instanceof String)
				return (((CharsetEncoder) ob).charset().name().equals(name));
			if (name instanceof Charset)
				return ((CharsetEncoder) ob).charset().equals(name);
			return false;
		}

		Object create(Object name) {
			if (name instanceof String)
				return Charset.forName((String) name).newEncoder();
			if (name instanceof Charset)
				return ((Charset) name).newEncoder();
			return null;
		}
	};

	public static CharsetEncoder encoderFor(Object name) {
		CharsetEncoder ce = (CharsetEncoder) encoderCache.forName(name);
		if(ce != null)
			ce.reset();
		return ce;
	}
	
	public static void cleanUpThreadLocal() {
		encoderCache.cleanUp();
	}
}
