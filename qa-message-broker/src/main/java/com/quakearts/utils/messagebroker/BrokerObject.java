package com.quakearts.utils.messagebroker;

import java.time.Duration;
import java.time.Instant;

/**A class for holding instances of message classes passed between threads.
 * It saves each message with a unique brokerID and records the creation time.
 * This helps the {@link com.quakearts.utils.messagebroker.MessageBroker MessageBroker}
 * co-ordinate message passing and stale message clean up.
 * @author kwakutwumasi-afriyie
 *
 * @param <M>
 */
public class BrokerObject<M> {
	private M message;
	private String brokerID;
	private Instant birthday = Instant.now();
	
	public BrokerObject(M message, String brokerID) {
		this.message = message;
		this.brokerID = brokerID;
	}
	
	public M getMessage() {
		return message;
	}
	
	public String getBrokerID() {
		return brokerID;
	}
	
	public Duration getAge() {
		return Duration.between(birthday, Instant.now());
	}
}