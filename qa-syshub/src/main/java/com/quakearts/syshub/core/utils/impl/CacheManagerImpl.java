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
package com.quakearts.syshub.core.utils.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import org.infinispan.Cache;
import org.infinispan.commons.configuration.ClassWhiteList;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import com.quakearts.syshub.core.utils.CacheManager;
import com.quakearts.syshub.core.utils.CacheWhiteListProvider;

@Singleton
public class CacheManagerImpl implements CacheManager {
	private static EmbeddedCacheManager cacheContainer;

	private static synchronized CacheContainer getCacheContainer() {
		if (cacheContainer == null) {
			cacheContainer = new DefaultCacheManager(new GlobalConfigurationBuilder()
					.serialization()
					.marshaller(new JavaSerializationMarshaller(new ClassWhiteList(loadWhiteList())))
					.defaultCacheName("global.default")
					.build(), new ConfigurationBuilder()
					// Eviction configuration
					.memory().evictionStrategy(EvictionStrategy.REMOVE).evictionType(EvictionType.COUNT).size(1000)
					// Expiration...is this neccessary?
					.expiration().lifespan(3, TimeUnit.DAYS).reaperEnabled(true).wakeUpInterval(1, TimeUnit.DAYS)
					// Save items to file. Good for managing high loads
					.persistence().passivation(true).addSingleFileStore()
					.async().enable().preload(true).location("syshub_log_cache").build());

			cacheContainer.start();
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				if (cacheContainer != null) {
					cacheContainer.stop();
				}
			}));

		}
		return cacheContainer;
	}

	private static List<String> loadWhiteList() {
		List<String> whiteList = new ArrayList<>(Arrays.asList("com.quakearts.syshub.model.*"));
		ServiceLoader<CacheWhiteListProvider> whiteListProviders = ServiceLoader.load(CacheWhiteListProvider.class);
		Iterator<CacheWhiteListProvider> whiteListProvidersIterators = whiteListProviders.iterator();
		while (whiteListProvidersIterators.hasNext()) {
			whiteList.addAll(whiteListProvidersIterators.next().getWhiteList());
		}
		
		return whiteList;
	}

	/* (non-Javadoc)
	 * @see com.quakearts.syshub.core.utils.CacheManager#getCache(java.lang.Class, java.lang.String)
	 */
	@Override
	public <T> Cache<String, T> getCache(Class<T> cacheType, String suffix) {
		return getCacheContainer().getCache(cacheType.getName() + (suffix != null ? suffix : ""));
	}

}
