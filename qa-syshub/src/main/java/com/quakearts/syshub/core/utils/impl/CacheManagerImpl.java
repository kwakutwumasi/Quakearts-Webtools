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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import org.infinispan.Cache;
import org.infinispan.commons.CacheConfigurationException;
import org.infinispan.commons.configuration.ClassWhiteList;
import org.infinispan.commons.marshall.JavaSerializationMarshaller;
import org.infinispan.commons.marshall.Marshaller;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.eviction.EvictionType;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.configuration.cache.Configuration;

import com.quakearts.appbase.exception.ConfigurationException;
import com.quakearts.syshub.core.utils.CacheManager;
import com.quakearts.syshub.core.utils.CacheWhiteListProvider;

@Singleton
public class CacheManagerImpl implements CacheManager {
	private EmbeddedCacheManager embeddedCacheManager;
	private Configuration configuration;
	private Set<String> cacheNames = new HashSet<>();

	private EmbeddedCacheManager getEmbeddedCacheManager() {
		if (embeddedCacheManager == null) {
			createConfiguration();

			embeddedCacheManager = new DefaultCacheManager(new GlobalConfigurationBuilder()
					.serialization()
					.marshaller(createMarshaller())
					.defaultCacheName("global.default")
					.build(), configuration);
			
			embeddedCacheManager.start();
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				if (embeddedCacheManager != null) {
					embeddedCacheManager.stop();
				}
			}));

		}
		return embeddedCacheManager;
	}

	/**Create the {@linkplain Configuration} for the embedded cache
	 * Implementers can override to customize configuration of
	 * {@linkplain Configuration}
	 * 
	 */
	protected void createConfiguration() {
		configuration = new ConfigurationBuilder()
				// Eviction configuration
				.memory()
					.evictionStrategy(EvictionStrategy.REMOVE)
					.evictionType(EvictionType.COUNT)
					.size(Integer.getInteger("cache.memory.size", 1000))
				// Expiration...is this neccessary?
				.expiration().lifespan(Integer.getInteger("cache.expiration.lifespan.days", 5), TimeUnit.DAYS)
				.reaperEnabled(true).wakeUpInterval(Integer.getInteger("cache.reaper.interval.days", 1), TimeUnit.DAYS)
				// Save items to file. Good for managing high loads
				.persistence()
					.passivation(false)
					.addSingleFileStore()
						.location("syshub_log_cache")
							.async()
							.enable()
							.preload(true)
							.segmented(false)
							.shared(false)
				.build();
	}

	/**Create the {@linkplain Marshaller} for the embedded cache
	 * Implementers can override to customize the
	 * {@linkplain Marshaller}
	 * 
	 */
	protected Marshaller createMarshaller() {
		return new JavaSerializationMarshaller(new ClassWhiteList(loadWhiteList()));
	}

	private static List<String> loadWhiteList() {
		List<String> whiteList = new ArrayList<>();
		whiteList.add("com.quakearts.syshub.model.*");
		whiteList.add("java.lang.*");
		whiteList.add("java.util.*");
		whiteList.add("java.sql.*");
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
	public synchronized <T> Cache<String, T> getCache(Class<T> cacheType, String suffix) {
		String name = cacheType.getName() + (suffix != null ? suffix : "");
		return getCacheByName(name);
	}

	private <T> Cache<String, T> getCacheByName(String name) {
		try {
			if (!cacheNames.contains(name)) {
				getEmbeddedCacheManager().defineConfiguration(name, configuration);
				cacheNames.add(name);
			}
			
			return getEmbeddedCacheManager().getCache(name);
		} catch (CacheConfigurationException e) {
			throw new ConfigurationException("Unable to configure cache "+name, e);
		}
	}

}
