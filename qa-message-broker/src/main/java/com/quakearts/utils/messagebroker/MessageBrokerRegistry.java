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
import java.util.concurrent.TimeUnit;

public interface MessageBrokerRegistry {
	<M> MessageBroker<M> getMessageBroker(Serializable id);
	void createMessageBroker(Serializable id, long timeout, TimeUnit timeUnit);
	void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit);
	void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit,
			long daemonInterval, TimeUnit daemonIntervalUnit, long maxAge, TimeUnit maxAgeUnit);
	void createMessageBroker(Serializable id, int capacity);
}
