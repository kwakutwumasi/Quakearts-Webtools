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

/**Registry for {@linkplain MessageBroker}s. Used to retrieve or create MessageBrokers
 * @author kwakutwumasi-afriyie
 *
 */
public interface MessageBrokerRegistry {
	/**Get a message broker identified by the ID. If the broker exists, it is returned.
	 * If not a new broker is created, stored with the ID and returned.
	 * @param id the ID of the broker to search for.
	 * @return
	 */
	<M> MessageBroker<M> getMessageBroker(Serializable id);
	/**Create a message broker using the supplied parameters
	 * @param id the ID of the broker to store in the registry
	 * @param timeout the amount of time to wait for messages to be available before timing out
	 * @param timeUnit the unit of time for the timeout parameter
	 */
	void createMessageBroker(Serializable id, long timeout, TimeUnit timeUnit);
	/**Create a message broker using the supplied parameters
	 * @param id the ID of the broker to store in the registry
	 * @param capacity the maximum number of unprocessed messages to hold in the broker's queue
	 * @param timeout the amount of time to wait for messages to be available before timing out
	 * @param timeUnit the unit of time for the timeout parameter
	 */
	void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit);
	/**Create a message broker using the supplied parameters
	 * @param id the ID of the broker to store in the registry
	 * @param capacity the maximum number of unprocessed messages to hold in the broker's queue
	 * @param timeout the amount of time to wait for messages to be available before timing out
	 * @param timeUnit the unit of time for the timeout parameter
	 * @param daemonInterval the amount of time between registry cleanup operations
	 * @param daemonIntervalUnit the unit of time for the daemonInterval parameter
	 * @param maxAge the amount of time a message in the broker queue is valid for, before being cleared out.
	 * @param maxAgeUnit the unit of time for the maxAge parameter
	 */
	void createMessageBroker(Serializable id, int capacity, long timeout, TimeUnit timeUnit,
			long daemonInterval, TimeUnit daemonIntervalUnit, long maxAge, TimeUnit maxAgeUnit);
	/**Create a message broker using the supplied parameters
	 * @param id the ID of the broker to store in the registry
	 * @param capacity the maximum number of unprocessed messages to hold in the broker's queue
	 */
	void createMessageBroker(Serializable id, int capacity);
}
