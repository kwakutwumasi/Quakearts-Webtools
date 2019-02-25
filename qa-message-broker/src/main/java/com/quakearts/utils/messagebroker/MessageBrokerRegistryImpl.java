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
package com.quakearts.utils.messagebroker;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MessageBrokerRegistryImpl implements MessageBrokerRegistry {
	
	private static final Map<Serializable, MessageBroker<?>> brokerRegistry = new ConcurrentHashMap<>();
	private static final Logger log = LoggerFactory.getLogger(MessageBroker.class);
	
	@SuppressWarnings({ "unchecked"})
	@Override
	public synchronized <M> MessageBroker<M> getMessageBroker(Serializable id) {
		log.trace("Getting MessageBroker with ID {}", id);
		return (MessageBroker<M>) brokerRegistry.computeIfAbsent(id,this::createMessageBroker);
	}

	private <M> MessageBroker<M> createMessageBroker(Serializable id){
		log.trace("Creating MessageBroker with ID {}", id);
		return new MessageBroker<>();
	}
	
	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity){
		log.trace("Creating MessageBroker with ID {} and capacity {}", id, capacity);
		MessageBroker<?> broker = new MessageBroker<>(capacity);
		brokerRegistry.put(id, broker);
	}

	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit){
		log.trace("Creating MessageBroker with ID {}, capacity {} and timeout of {} {}", id, capacity, timeout, timeUnit);
		MessageBroker<?> broker = new MessageBroker<>(capacity, timeout, timeUnit);
		brokerRegistry.put(id, broker);
	}

	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit,
			long daemonInterval, TimeUnit daemonIntervalUnit, long maxAge, TimeUnit maxAgeUnit){
		log.trace("Creating MessageBroker with ID {}, capacity {}, timeout of {} {}, daemon interval {} {} and max age {} {}", 
				id, capacity, timeout, timeUnit, daemonInterval, daemonIntervalUnit, maxAge, maxAgeUnit);
		MessageBroker<?> broker = new MessageBroker<>(capacity, timeout, timeUnit, 
				daemonInterval, daemonIntervalUnit,
				maxAge, maxAgeUnit);
		brokerRegistry.put(id, broker);
	}

	@Override
	public void createMessageBroker(Serializable id, long timeout, TimeUnit timeUnit){
		log.trace("Creating MessageBroker with ID {} and timeout of {} {}", id, timeout, timeUnit);
		MessageBroker<?> broker = new MessageBroker<>(timeout, timeUnit);
		brokerRegistry.put(id, broker);
	}
}
