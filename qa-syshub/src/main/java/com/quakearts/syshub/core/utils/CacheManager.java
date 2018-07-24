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

import org.infinispan.Cache;

/**Interface for cache manager instances
 * @author kwakutwumasi-afriyie
 *
 */
public interface CacheManager {

	/**Return the cache for classes of the specified type, using the specified suffix
	 * @param cacheType The class of objects in this cache
	 * @param suffix a suffix to differentiate caches of the same type
	 * @return the cache
	 */
	<T> Cache<String, T> getCache(Class<T> cacheType, String suffix);

}