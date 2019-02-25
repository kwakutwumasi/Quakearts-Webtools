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
	private Instant birthday = Instant.now();
	private boolean picked;
	private boolean stale;
	
	public BrokerObject(M message) {
		this.message = message;
	}
	
	public M getMessage() {
		return message;
	}
	
	public Duration getAge() {
		return Duration.between(birthday, Instant.now());
	}
	
	public boolean hasBeenPicked(long timeout) {
		if(!picked) {
			waitTillTimeout(timeout);
		}
		return picked;
	}

	private synchronized void waitTillTimeout(long timeout) {
		long start = System.currentTimeMillis();
		do {
			try {
				wait(timeout-millisSince(start));
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		} while(!picked && millisSince(start)<timeout);
		stale = !picked;
	}

	private long millisSince(long start) {
		return System.currentTimeMillis()-start;
	}
	
	public synchronized void pick() {
		picked = true;
		notifyAll();
	}
	
	public synchronized boolean isStale() {
		return stale;
	}
}