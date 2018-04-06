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

@Singleton
public class MessageBrokerRegistryImpl implements MessageBrokerRegistry {
	
	private static final Map<Serializable, MessageBroker<?>> brokerRegistry = new ConcurrentHashMap<>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public synchronized <M> MessageBroker<M> getMessageBroker(Serializable id) {
		MessageBroker broker =  brokerRegistry.get(id);
		if(broker == null) {
			broker = new MessageBroker<>();
			brokerRegistry.put(id, broker);
		}
		
		return broker;
	}

	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity){
		MessageBroker<?> broker = new MessageBroker<>(capacity);
		brokerRegistry.put(id, broker);
	}

	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit){
		MessageBroker<?> broker = new MessageBroker<>(capacity, timeout, timeUnit);
		brokerRegistry.put(id, broker);
	}

	@Override
	public synchronized void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit,
			long daemonInterval, TimeUnit daemonIntervalUnit, long maxAge, TimeUnit maxAgeUnit){
		MessageBroker<?> broker = new MessageBroker<>(capacity, timeout, timeUnit, 
				daemonInterval, daemonIntervalUnit,
				maxAge, maxAgeUnit);
		brokerRegistry.put(id, broker);
	}

	@Override
	public void createMessageBroker(Serializable id, long timeout, TimeUnit timeUnit){
		MessageBroker<?> broker = new MessageBroker<>(timeout, timeUnit);
		brokerRegistry.put(id, broker);
	}
}
